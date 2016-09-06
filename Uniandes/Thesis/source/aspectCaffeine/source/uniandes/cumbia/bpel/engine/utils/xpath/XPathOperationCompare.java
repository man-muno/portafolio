/*
 * Copyright 2002-2004 The Apache Software Foundation
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.jxpath.Pointer;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.InfoSetUtil;
import org.apache.commons.jxpath.ri.axes.InitialContext;
import org.apache.commons.jxpath.ri.axes.SelfContext;

/**
 * Common superclass for the implementations of Expression for the operations
 * "=" and "!=".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2009/01/29 21:38:42 $
 */
public abstract class XPathOperationCompare extends XPathCoreOperation {

    public XPathOperationCompare(XPathExpression arg1, XPathExpression arg2) {
        super(new XPathExpression[] { arg1, arg2 });
    }

    /**
     * Compares two values
     */
    protected boolean equal(
    		BPELContext context,
        XPathExpression left,
        XPathExpression right) 
    {
        Object l = left.compute(context);
        Object r = right.compute(context);


        if (l instanceof InitialContext || l instanceof SelfContext) {
            l = ((EvalContext) l).getSingleNodePointer();
        }

        if (r instanceof InitialContext || r instanceof SelfContext) {
            r = ((EvalContext) r).getSingleNodePointer();
        }

        if (l instanceof Collection) {
            l = ((Collection) l).iterator();
        }

        if (r instanceof Collection) {
            r = ((Collection) r).iterator();
        }

        if ((l instanceof Iterator) && !(r instanceof Iterator)) {
            return contains((Iterator) l, r);
        }
        else if (!(l instanceof Iterator) && (r instanceof Iterator)) {
            return contains((Iterator) r, l);
        }
        else if (l instanceof Iterator && r instanceof Iterator) {
            return findMatch((Iterator) l, (Iterator) r);
        }

        return equal(l, r);
    }

    protected boolean contains(Iterator it, Object value) {
        while (it.hasNext()) {
            Object element = it.next();
            if (equal(element, value)) {
                return true;
            }
        }
        return false;
    }

    protected boolean findMatch(Iterator lit, Iterator rit) {
        HashSet left = new HashSet();
        while (lit.hasNext()) {
            left.add(lit.next());
        }
        while (rit.hasNext()) {
            if (contains(left.iterator(), rit.next())) {
                return true;
            }
        }
        return false;
    }

    protected boolean equal(Object l, Object r) {
        if (l instanceof Pointer && r instanceof Pointer) {
            if (l.equals(r)) {
                return true;
            }
        }

        if (l instanceof Pointer) {
            l = ((Pointer) l).getValue();
        }

        if (r instanceof Pointer) {
            r = ((Pointer) r).getValue();
        }

        if (l == r) {
            return true;
        }

//        System.err.println("COMPARING VALUES: " + l + " " + r);
        if (l instanceof Boolean || r instanceof Boolean) {
            return (InfoSetUtil.booleanValue(l) == InfoSetUtil.booleanValue(r));
        }
        else if (l instanceof Number || r instanceof Number) {
            return (InfoSetUtil.doubleValue(l) == InfoSetUtil.doubleValue(r));
        }
        else if (l instanceof String || r instanceof String) {
            return (
                InfoSetUtil.stringValue(l).equals(InfoSetUtil.stringValue(r)));
        }
        else if (l == null) {
            return r == null;
        }
        return l.equals(r);
    }

}
