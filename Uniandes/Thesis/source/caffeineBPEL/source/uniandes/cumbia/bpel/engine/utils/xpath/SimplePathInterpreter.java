/*
 * Copyright 1999-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uniandes.cumbia.bpel.engine.utils.xpath;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.jxpath.ri.InfoSetUtil;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.beans.PropertyOwnerPointer;

import uniandes.cumbia.bpel.elements.IBasicElement;

/**
 * An evaluation mechanism for simple XPaths, which
 * is much faster than the usual process. It is only used for
 * xpaths which have no context-dependent parts, consist entirely of
 * <code>child::name</code> and <code>self::node()</code> steps with
 * predicates that either integer or have the form <code>[@name = ...]</code>.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class SimplePathInterpreter {

    // Because of the complexity caused by the variety of situations
    // that need to be addressed by this class, we attempt to break up
    // the class into individual methods addressing those situations
    // individually.  The names of the methods are supposed to
    // give brief descriptions of those situations.

    private static final QName QNAME_NAME = new QName(null, "name");
    private static final int PERFECT_MATCH = 1000;

    // Uncomment this variable and the PATH = ... lines in
    // the two following methods in order to be able to print the
    // currently evaluated path for debugging of this class
//    private static String PATH;       // Debugging

    /**
     * Interpret a simple path that starts with the given root and
     * follows the given steps. All steps must have the axis "child::"
     * and a name test.  They can also optionally have predicates
     * of type [@name=expression] or simply [expression] interpreted
     * as an index.
     */
    public static Object interpretSimpleLocationPath(
            BPELContext context, Object root, XPathStep steps[])
    {
//        PATH = createNullPointer(context, root, steps, 0).toString();  // Dbg
        Object pointer = doStep(context, root, steps, 0);
//        return valuePointer(pointer);
        return pointer;
    }

    /**
     * Interpret the steps of a simple expression path that
     * starts with the given root, which is the result of evaluation
     * of the root expression of the expression path, applies the
     * given predicates to it and then follows the given steps.
     * All steps must have the axis "child::" or "attribute::"
     * and a name test.  They can also optionally have predicates
     * of type [@name=...] or simply [...] interpreted as an index.
     */
    public static Object interpretSimpleExpressionPath(
                BPELContext context, IBasicElement root,
                XPathExpression predicates[], XPathStep[] steps)
    {
//        PATH = createNullPointerForPredicates(context, root,
//                    steps, -1, predicates, 0).toString();  // Debugging
        Object pointer =
            doPredicate(context, root, steps, -1, predicates, 0);
//        return valuePointer(pointer);
        return pointer;
    }

    /**
     * Recursive evaluation of a path. The general plan is:
     * Look at the current step,
     * find nodes that match it,
     * iterate over those nodes and
     * for each of them call doStep again for subsequent steps.
     */
    private static Object doStep(
            BPELContext context, Object parent,
            XPathStep steps[], int currentStep)
    {
        if (parent == null) {
            return null;
        }

        if (currentStep == steps.length) {
            // We have reached the end of the list of steps
            return parent;
        }

        // Open all containers
        //parent = valuePointer(parent);
        
        XPathStep step = steps[currentStep];
        XPathExpression predicates[] = step.getPredicates();

        // Divide and conquer: the process is broken out into
        // four major use cases.
        // 1. Current step has no predicates and
        //    the root is a property owner (e.g. bean or map)
        // 2. Current step has predicates and
        //    the root is a property owner (e.g. bean or map)
        // 3. Current step has no predicates and
        //    the root is an InfoSet standard node (e.g. DOM Node)
        // 4. Current step has predicates and
        //    the root is an InfoSet standard node (e.g. DOM Node)

        if (parent instanceof PropertyOwnerPointer) {
            if (predicates == null || predicates.length == 0) {
                return null; //TODO COMPLETAR
            }
            else {
                return doStepPredicatesPropertyOwner(
                    context,
                    (PropertyOwnerPointer) parent,
                    steps,
                    currentStep);
            }
        }
        else {
            if (predicates == null || predicates.length == 0) {
                return doStepNoPredicatesStandard(
                    context,
                    parent,
                    steps,
                    currentStep);
            }
            else {
                return doStepPredicatesStandard(
                    context,
                    parent,
                    steps,
                    currentStep);
            }
        }
    }

    /**
     * We have a step that starts with a property owner (bean, map, etc) and has
     * no predicates.  The name test of the step may map to a scalar property
     * or to a collection.  If it is a collection, we should apply the tail of
     * the path to each element until we find a match. If we don't find
     * a perfect match, we should return the "best quality" pointer, which
     * has the longest chain of steps mapping to existing nodes and the shortes
     * tail of Null* pointers.
     */
    private static NodePointer doStepNoPredicatesPropertyOwner(
                BPELContext context, Object parentPointer,
                XPathStep[] steps, int currentStep)
    {
    	/*XPathStep step = steps[currentStep];
        Object childPointer =
            createChildPointerForStep(parentPointer, step);

        if (!childPointer.isActual()) {
            // The property does not exist - create a null pointer.
            return createNullPointer(
                context,
                parentPointer,
                steps,
                currentStep);
        }
        else if (currentStep == steps.length - 1) {
            // If this is the last step - we are done, we found it
            return childPointer;
        }
        else if (childPointer.isCollection()) {
            // Iterate over all values and
            // execute remaining steps for each node,
            // looking for the best quality match
            int bestQuality = 0;
            childPointer = (NodePointer) childPointer.clone();
            NodePointer bestMatch = null;
            int count = childPointer.getLength();
            for (int i = 0; i < count; i++) {
                childPointer.setIndex(i);
                NodePointer pointer =
                    doStep(context, childPointer, steps, currentStep + 1);
                int quality = computeQuality(pointer);
                if (quality == PERFECT_MATCH) {
                    return pointer;
                }
                else if (quality > bestQuality) {
                    bestQuality = quality;
                    bestMatch = (NodePointer) pointer.clone();
                }
            }
            if (bestMatch != null) {
                return bestMatch;
            }
            // This step did not find anything - return a null pointer
            return createNullPointer(context, childPointer, steps, currentStep);
        }
        else {
            // Evaluate subsequent steps
            return doStep(context, childPointer, steps, currentStep + 1);
        }*/
    	return null; //TODO POR COMPLETAR 
    }

    /**
     * A path that starts with a standard InfoSet node (e.g. DOM Node) and
     * has no predicates.  Get a child iterator and apply the tail of
     * the path to each element until we find a match. If we don't find
     * a perfect match, we should return the "best quality" pointer, which
     * has the longest chain of steps mapping to existing nodes and the shortes
     * tail of Null* pointers.
     */
    private static Object doStepNoPredicatesStandard(
                BPELContext context, Object parentPointer,
                XPathStep[] steps, int currentStep)
    {
        XPathStep step = steps[currentStep];

        /*if (step.getAxis() == Compiler.AXIS_SELF) {
            return doStep(context, parentPointer, steps, currentStep + 1);
        }

        int bestQuality = 0;
        NodePointer bestMatch = null;
        Iterator it = getNodeIterator(context, parentPointer, step);
        if (it != null) {
            while (it.hasNext()) 
            {
                Object childPointer = it.next();
                if (steps.length == currentStep + 1) {
                    // If this is the last step - we are done, we found it
                    return childPointer;
                }
                Object pointer = doStep(
                        context, childPointer, steps, currentStep + 1);
                int quality = computeQuality(pointer);
                if (quality == PERFECT_MATCH) {
                    return pointer;
                }
                else if (quality > bestQuality) {
                    bestQuality = quality;
                    bestMatch = (NodePointer) pointer.clone();
                }
            }
        }

        if (bestMatch != null) {
            return bestMatch;
        }

        return createNullPointer(
                context, parentPointer, steps, currentStep);*/
        return null; //TODO COPLETAR
    }

    /**
     * A path that starts with a property owner. The method evaluates
     * the first predicate in a special way and then forwards to
     * a general predicate processing method.
     */
    private static NodePointer doStepPredicatesPropertyOwner(
            BPELContext context, PropertyOwnerPointer parentPointer,
            XPathStep[] steps, int currentStep)
    
    {
    	/*
        Step step = steps[currentStep];
        Expression predicates[] = step.getPredicates();

        NodePointer childPointer =
            createChildPointerForStep(parentPointer, step);
        if (!childPointer.isActual()) {
            // Property does not exist - return a null pointer
            return createNullPointer(
                context,
                parentPointer,
                steps,
                currentStep);
        }

        // Evaluate predicates
        return doPredicate(
            context,
            childPointer,
            steps,
            currentStep,
            predicates,
            0);*/
    		return null; //TODO ARREGLAR
    }

    private static NodePointer createChildPointerForStep(
                Object parentPointer, XPathStep step)
    {
        /*int axis = step.getAxis();
        if (axis == Compiler.AXIS_CHILD || axis == Compiler.AXIS_ATTRIBUTE) {
            NodePointer childPointer;
            XPathQName name = ((XPathNodeNameTest) step.getNodeTest()).getNodeName();
            if (axis == Compiler.AXIS_ATTRIBUTE && isLangAttribute(name)) {
                childPointer = new LangAttributePointer(parentPointer);
            }
            else {
                childPointer = parentPointer.getPropertyPointer();
                ((PropertyPointer) childPointer).setPropertyName(
                    name.toString());
                childPointer.setAttribute(axis == Compiler.AXIS_ATTRIBUTE);
            }
            return childPointer;
        }
        else {
            return parentPointer;
        }*/
    	return null; //TODO POR HACER
    }

    /**
     * A path that starts with a standard InfoSet node, e.g. a DOM Node.
     * The method evaluates the first predicate in a special way and
     * then forwards to a general predicate processing method.
     */
    private static Object doStepPredicatesStandard(
            BPELContext context, Object parent,
            XPathStep[] steps, int currentStep)
    {
        XPathStep step = steps[currentStep];
        XPathExpression predicates[] = step.getPredicates();

        int axis = step.getAxis();
        if (axis == Compiler.AXIS_SELF) {
            return doPredicate(
                context,
                parent,
                steps,
                currentStep,
                predicates,
                0);
        }

        XPathExpression predicate = predicates[0];
        
        Object attributeValue= null; 

        // Optimize for a single predicate to avoid building a list
        // and to allow the direct access to the index'th element
        // in the case of a simple subscript predecate
        // It is a very common use case, so it deserves individual
        // attention
        if (predicates.length == 1) {
                                    
            if (predicate instanceof XPathNameAttributeTest) { // [@name = key]
                String key = keyFromPredicate(context, predicate);
                String keyU= convertToUpperCase(key); 
                
                Class clazz= parent.getClass();
                
                Method method= null;
				try {
					method = clazz.getMethod("get"+keyU, new Class[0]);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                
                try {
					attributeValue= method.invoke(parent, new Object[0]);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
            }
            else {
                int index = indexFromPredicate(context, predicate);
                Field[] fields= parent.getClass().getDeclaredFields();
                if (index>=0 && index<fields.length) 
                {
                	Field field= fields[index]; 
                	try {
						attributeValue= field.get(parent);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                     
                }
            }
            
            if (attributeValue != null) {
                return doStep(context, attributeValue, steps, currentStep + 1);
            }
        }
        else {
            Iterator it = getNodeIterator(context, parent, step);
            if (it != null) {
                List list = new ArrayList();
                while (it.hasNext()) {
                    list.add(it.next());
                }
                Object pointer =
                    doPredicatesStandard(
                        context,
                        list,
                        steps,
                        currentStep,
                        predicates,
                        0);
                if (pointer != null) {
                    return pointer;
                }
            }
        }
        return createNullPointer(context, parent, steps, currentStep);
    }

    /**
     * Evaluates predicates and proceeds with the subsequent steps
     * of the path.
     */
    private static Object doPredicate(
                BPELContext context, Object parent,
                XPathStep[] steps, int currentStep,
                XPathExpression predicates[], int currentPredicate)
    {
        if (currentPredicate == predicates.length) {
            return doStep(context, parent, steps, currentStep + 1);
        }

        XPathExpression predicate = predicates[currentPredicate];
        if (predicate instanceof XPathNameAttributeTest) { // [@name = key1]
            return doPredicateName(
                context,
                parent,
                steps,
                currentStep,
                predicates,
                currentPredicate);
        }
        else { // [index]
            return doPredicateIndex(
                context,
                parent,
                steps,
                currentStep,
                predicates,
                currentPredicate);
        }
    }

    private static Object doPredicateName(
            BPELContext context, Object parent,
            XPathStep[] steps, int currentStep,
            XPathExpression[] predicates, int currentPredicate)
    {
 /*   	XPathExpression predicate = predicates[currentPredicate];
        String key = keyFromPredicate(context, predicate);
        Object child = parent;
        if (child instanceof PropertyOwnerPointer) {
            PropertyPointer pointer =
                ((PropertyOwnerPointer) child).getPropertyPointer();
            pointer.setPropertyName(key);
            if (pointer.isActual()) {
                return doPredicate(
                    context,
                    pointer,
                    steps,
                    currentStep,
                    predicates,
                    currentPredicate + 1);
            }
        }
        else if (child instanceof List) {
            // For each node in the collection, perform the following:
            // if the node is a property owner, apply this predicate to it;
            // if the node is a collection, apply this predicate to each elem.;
            // if the node is not a prop owner or a collection,
            //  see if it has the attribute "name" with the right value,
            //  if so - proceed to the next predicate
            NodePointer bestMatch = null;
            int bestQuality = 0;
            child = (NodePointer) child.clone();
            int count = child.getLength();
            for (int i = 0; i < count; i++) {
                child.setIndex(i);
                NodePointer valuePointer = valuePointer(child);
                NodePointer pointer;
                if ((valuePointer instanceof PropertyOwnerPointer)
                    || valuePointer.isCollection()) {
                    pointer =
                        doPredicateName(
                            context,
                            valuePointer,
                            steps,
                            currentStep,
                            predicates,
                            currentPredicate);
                }
                else if (isNameAttributeEqual(valuePointer, key)) {
                    pointer =
                        doPredicate(
                            context,
                            valuePointer,
                            steps,
                            currentStep,
                            predicates,
                            currentPredicate + 1);
                }
                else {
                    pointer = null;
                }
                if (pointer != null) {
                    int quality = computeQuality(pointer);
                    if (quality == PERFECT_MATCH) {
                        return pointer;
                    }
                    if (quality > bestQuality) {
                        bestMatch = (NodePointer) pointer.clone();
                        bestQuality = quality;
                    }
                }
            }
            if (bestMatch != null) {
                return bestMatch;
            }
        }
        else {
            // If the node is a standard InfoSet node (e.g. DOM Node),
            // employ doPredicates_standard, which will iterate through
            // the node's children and apply all predicates
            NodePointer found =
                doPredicatesStandard(
                    context,
                    Collections.singletonList(child),
                    steps,
                    currentStep,
                    predicates,
                    currentPredicate);
            if (found != null) {
                return found;
            }
        }
        // If nothing worked - return a null pointer
        return createNullPointerForPredicates(
            context,
            child,
            steps,
            currentStep,
            predicates,
            currentPredicate);*/
    	return null; //TODO ARREGLAR
    }

    /**
     * Called exclusively for standard InfoSet nodes, e.g. DOM nodes
     * to evaluate predicate sequences like [@name=...][@name=...][index].
     */
    private static Object doPredicatesStandard(
                BPELContext context, List parents,
                XPathStep[] steps, int currentStep,
                XPathExpression predicates[], int currentPredicate)
    {
        if (parents.size() == 0) {
            return null;
        }

        // If all predicates have been processed, take the first
        // element from the list of results and proceed to the
        // remaining steps with that element.
        if (currentPredicate == predicates.length) {
            Object pointer = parents.get(0);
            return doStep(context, pointer, steps, currentStep + 1);
        }

        XPathExpression predicate = predicates[currentPredicate];
        if (predicate instanceof XPathNameAttributeTest) {
            String key = keyFromPredicate(context, predicate);
            List newList = new ArrayList();
            for (int i = 0; i < parents.size(); i++) {
                Object pointer = parents.get(i);
                if (isNameAttributeEqual(pointer, key)) {
                    newList.add(pointer);
                }
            }
            if (newList.size() == 0) {
                return null;
            }
            return doPredicatesStandard(
                context,
                newList,
                steps,
                currentStep,
                predicates,
                currentPredicate + 1);
        }
        else {
            // For a subscript, simply take the corresponding
            // element from the list of results and
            // proceed to the remaining predicates with that element
            int index = indexFromPredicate(context, predicate);
            if (index < 0 || index >= parents.size()) {
                return null;
            }
            Object ptr = parents.get(index);
            return doPredicate(
                context,
                ptr,
                steps,
                currentStep,
                predicates,
                currentPredicate + 1);
        }
    }

    /**
     * Evaluate a subscript predicate: see if the node is a collection and
     * if the index is inside the collection
     */
    private static Object doPredicateIndex(
            BPELContext context, Object parent,
            XPathStep[] steps, int currentStep,
            XPathExpression[] predicates, int currentPredicate)
    {
    	/*XPathExpression predicate = predicates[currentPredicate];
        int index = indexFromPredicate(context, predicate);
        NodePointer pointer = parent;
        if (isCollectionElement(pointer, index)) {
            pointer = (NodePointer) pointer.clone();
            pointer.setIndex(index);
            return doPredicate(
                context,
                pointer,
                steps,
                currentStep,
                predicates,
                currentPredicate + 1);
        }
        return createNullPointerForPredicates(
            context,
            parent,
            steps,
            currentStep,
            predicates,
            currentPredicate);*/
    	return null; 
    }

    /**
     * Extract an integer from a subscript predicate. The returned index
     * starts with 0, even though the subscript starts with 1.
     */
    private static int indexFromPredicate(
        BPELContext context,
        XPathExpression predicate) 
    {
        Object value = predicate.compute(context);

        if (value instanceof Number) {
            return (int) (InfoSetUtil.doubleValue(value) + 0.5) - 1;
        }
        else if (InfoSetUtil.booleanValue(value)) {
            return 0;
        }

        return -1;
    }

    /**
     * Extracts the string value of the expression from a predicate like
     * [@name=expression].
     */
    private static String keyFromPredicate(BPELContext context,
                XPathExpression predicate) 
    {
        XPathExpression expr =
            ((XPathNameAttributeTest) predicate).getNameTestExpression();
        return expr.compute(context).toString();
    }

    /**
     * For a pointer that matches an actual node, returns 0.
     * For a pointer that does not match an actual node, but whose
     * parent pointer does returns -1, etc.
     */
    private static int computeQuality(NodePointer pointer) {
        int quality = PERFECT_MATCH;
        while (pointer != null && !pointer.isActual()) {
            quality--;
            pointer = pointer.getImmediateParentPointer();
        }
        return quality;
    }

    /**
     * Returns true if the pointer has an attribute called "name" and
     * its value is equal to the supplied string.
     */
    private static boolean isNameAttributeEqual(
        Object pointer,
        String name) 
    {
    	Field[] fields= pointer.getClass().getDeclaredFields();
    	
    	for(int i=0; i<fields.length; i++)
    	{
    		if(fields[i].getName().toLowerCase().endsWith(name))
    		{
    			return true; 
    		}
    		
    	}
    	
    	return false;     	        
    }

    /**
     * Returns true if the pointer is a collection and the index is
     * withing the bounds of the collection.
     */
    private static boolean isCollectionElement(
        NodePointer pointer,
        int index) 
    {
        return pointer.isActual()
            && (index == 0
                || (pointer.isCollection()
                    && index >= 0
                    && index < pointer.getLength()));
    }

    /**
     * For an intermediate pointer (e.g. PropertyPointer, ContainerPointer)
     * returns a pointer for the contained value.
     */
    private static NodePointer valuePointer(NodePointer pointer) {
        return pointer == null ? null : pointer.getValuePointer();
    }

    /**
     * Creates a "null pointer" that
     * a) represents the requested path and
     * b) can be used for creation of missing nodes in the path.
     */
    public static Object createNullPointer(
            BPELContext context, Object parent, XPathStep[] steps,
            int currentStep)
    {
/*        if (currentStep == steps.length) {
            return parent;
        }

        parent = valuePointer(parent);

        XPathStep step = steps[currentStep];

        int axis = step.getAxis();
        if (axis == Compiler.AXIS_CHILD || axis == Compiler.AXIS_ATTRIBUTE) {
            NullPropertyPointer pointer = new NullPropertyPointer(parent);
            QName name = ((NodeNameTest) step.getNodeTest()).getNodeName();
            pointer.setPropertyName(name.toString());
            pointer.setAttribute(axis == Compiler.AXIS_ATTRIBUTE);
            parent = pointer;
        }
        // else { it is self::node() }

        Expression predicates[] = step.getPredicates();
        return createNullPointerForPredicates(
            context,
            parent,
            steps,
            currentStep,
            predicates,
            0);*/
    	
    	return null;
    }

    /**
     * Creates a "null pointer" that starts with predicates.
     */
    private static NodePointer createNullPointerForPredicates(
            BPELContext context, NodePointer parent,
            XPathStep[] steps, int currentStep,
            XPathExpression predicates[], int currentPredicate)
    {
        /*for (int i = currentPredicate; i < predicates.length; i++) {
            Expression predicate = predicates[i];
            if (predicate instanceof NameAttributeTest) {
                String key = keyFromPredicate(context, predicate);
                parent = valuePointer(parent);
                NullPropertyPointer pointer = new NullPropertyPointer(parent);
                pointer.setNameAttributeValue(key);
                parent = pointer;
            }
            else {
                int index = indexFromPredicate(context, predicate);
                if (parent instanceof NullPropertyPointer) {
                    parent.setIndex(index);
                }
                else {
                    parent = new NullElementPointer(parent, index);
                }
            }
        }
        // Proceed with the remaining steps
        return createNullPointer(
                    context, parent, steps, currentStep + 1);*/
    	return null; 
    }

    private static Iterator getNodeIterator(
        BPELContext context,
        Object pointer,
        XPathStep step) 
    {
    	Field[] fields= pointer.getClass().getDeclaredFields();
        
        List fieldList= new ArrayList(); 
        
        for(int i=0; i<fields.length; i++)
        {
        	Object attribute;
			try {
				attribute = fields[i].get(pointer);
				fieldList.add(attribute);
			} 
			catch (IllegalArgumentException e) 
			{			
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{			
				e.printStackTrace();
			}
        	
        }
        if (step.getAxis() == Compiler.AXIS_CHILD) {
            XPathNodeTest nodeTest = step.getNodeTest();
            XPathQName qname = ((XPathNodeNameTest) nodeTest).getNodeName();
            String prefix = qname.getPrefix();
            if (prefix != null) {
/*            	int nameSpaceURIID= context.getProcessUtils().getVariableIdGenerator().getId(prefix);
                DataString namespaceURIData = (DataString) context.getElement().getLocalMemory().get(nameSpaceURIID);
                String namespaceURI= (String) namespaceURIData.getValue(); 
                nodeTest = new XPathNodeNameTest(qname, namespaceURI);*/

            }
            
            
            
            return fieldList.iterator();
        }
        else { // Compiler.AXIS_ATTRIBUTE
            if (!(step.getNodeTest() instanceof XPathNodeNameTest)) {
                throw new UnsupportedOperationException(
                    "Not supported node test for attributes: "
                        + step.getNodeTest());
            }
            return fieldList.iterator();
        }
    }

    private static boolean isLangAttribute(XPathQName name) {
        return name.getPrefix() != null
            && name.getPrefix().equals("xml")
            && name.getName().equals("lang");
    }
    
    /**
     * Converts the first letter of the String to uopper case
     * @param word The string to convert
     * @return The string with the first letter in upper case
     */
    private static String convertToUpperCase(String word)
	{
    	char[] newWord= word.toCharArray(); 
    	
    	newWord[0]= Character.toUpperCase(newWord[0]); 
    	
    	return new String(newWord); 
	}
    
}