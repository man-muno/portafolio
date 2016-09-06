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


/**
 * An  element of the parse tree that represents an expression path, which is a
 * path that starts with an expression like a function call: <code>getFoo(.)
 * /bar</code>.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathExpressionPath extends XPathPath {

    private XPathExpression expression;
    private XPathExpression predicates[];

    private boolean basicKnown = false;
    private boolean basic;

    public XPathExpressionPath(
    		XPathExpression expression,
    		XPathExpression[] predicates,
        XPathStep[] steps) 
    {
        super(steps);
        this.expression = expression;
        this.predicates = predicates;
    }

    public XPathExpression getExpression() {
        return expression;
    }

    /**
     * Predicates are the expressions in brackets that may follow
     * the root expression of the path.
     */
    public XPathExpression[] getPredicates() {
        return predicates;
    }

    /**
     * Returns true if the root expression or any of the
     * predicates or the path steps are context dependent.
     */
    public boolean computeContextDependent() {
        if (expression.isContextDependent()) {
            return true;
        }
        if (predicates != null) {
            for (int i = 0; i < predicates.length; i++) {
                if (predicates[i].isContextDependent()) {
                    return true;
                }
            }
        }
        return super.computeContextDependent();
    }

    /**
     * Recognized paths formatted as <code>$x[3]/foo[2]</code>.  The
     * evaluation of such "simple" paths is optimized and streamlined.
     */
    public boolean isSimpleExpressionPath() {
        if (!basicKnown) {
            basicKnown = true;
            basic = isSimplePath() && areBasicPredicates(getPredicates());
        }
        return basic;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        if (expression instanceof XPathCoreOperation
            || expression instanceof XPathExpressionPath
            || expression instanceof XPathLocationPath) {
            buffer.append('(');
            buffer.append(expression);
            buffer.append(')');
        }
        else {
            buffer.append(expression);
        }
        if (predicates != null) {
            for (int i = 0; i < predicates.length; i++) {
                buffer.append('[');
                buffer.append(predicates[i]);
                buffer.append(']');
            }
        }

        XPathStep steps[] = getSteps();
        if (steps != null) {
            for (int i = 0; i < steps.length; i++) {
                buffer.append("/");
                buffer.append(steps[i]);
            }
        }
        return buffer.toString();
    }

    public Object compute(BPELContext context) {
        return expressionPath(context, false);
    }

    public Object computeValue(BPELContext context) {
        return expressionPath(context, true);
    }

    /**
     * Walks an expression path (a path that starts with an expression)
     */
    protected Object expressionPath(
        BPELContext evalContext,
        boolean firstMatch) 
    {
        Object value = expression.compute(evalContext);
/*        EvalContext context;
        if (value instanceof InitialContext) {
            // This is an optimization. We can avoid iterating through a 
            // collection if the context bean is in fact one.
            context = (InitialContext) value;
        }
        else if (value instanceof EvalContext) {
            // UnionContext will collect all values from the "value" context
            // and treat the whole thing as a big collection.
            context =
                new UnionContext(
                    evalContext,
                    new ElementMemory[] {(ElementMemory) value });
        }
        else {
            context = evalContext.getRootContext().getConstantContext(value);
        }*/

        /*if (firstMatch
            && isSimpleExpressionPath()
            ) 
        {
            EvalContext ctx = context;
            NodePointer ptr = (NodePointer) ctx.getSingleNodePointer();
            if (ptr != null
                && (ptr.getIndex() == NodePointer.WHOLE_COLLECTION
                    || predicates == null
                    || predicates.length == 0)) {
                return SimplePathInterpreter.interpretSimpleExpressionPath(
                    evalContext,
                    ptr,
                    predicates,
                    getSteps());
            }
        }
        if (predicates != null) {
            for (int j = 0; j < predicates.length; j++) {
                context = new PredicateContext(context, predicates[j]);
            }
        }
        if (firstMatch) {
            return getSingleNodePointerForSteps(context);
        }
        else {
            return evalSteps(context);
        }*/ return null;
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isContextDependent() {
		// TODO Auto-generated method stub
		return false;
	}
}