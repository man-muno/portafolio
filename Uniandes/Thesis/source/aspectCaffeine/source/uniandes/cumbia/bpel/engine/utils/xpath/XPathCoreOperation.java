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
 * The common subclass for tree elements representing core operations like "+",
 * "- ", "*" etc.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2009/01/29 21:38:42 $
 */
public abstract class XPathCoreOperation extends XPathOperation 
{        
    public XPathCoreOperation(XPathExpression args[]) 
    {
        super(args);
    }

    public Object compute(BPELContext context) {
        return computeValue(context);
    }

    public abstract Object computeValue(BPELContext context);
    
    /**
     * Returns the XPath symbol for this operation, e.g. "+", "div", etc.
     */
    public abstract String getSymbol();
    
    /**
     * Returns true if the operation is not sensitive to the order of arguments,
     * e.g. "=", "and" etc, and false if it is, e.g. "&lt;=", "div".
     */
    protected abstract boolean isSymmetric();
    
    /**
     * Computes the precedence of the operation.
     */
    protected abstract int getPrecedence();
    
    public String toString() {
        if (args.length == 1) {
            return getSymbol() + parenthesize(args[0], false);
        }
        else {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    buffer.append(' ');
                    buffer.append(getSymbol());
                    buffer.append(' ');
                }
                buffer.append(parenthesize(args[i], i == 0));
            }
            return buffer.toString();
        }
    }
    
    private String parenthesize(XPathExpression expression, boolean left) {
        if (!(expression instanceof XPathCoreOperation)) {
            return expression.toString();
        }
        XPathCoreOperation op = (XPathCoreOperation) expression;
        int myPrecedence = getPrecedence();
        int thePrecedence = op.getPrecedence();

        boolean needParens = true;
        if (myPrecedence < thePrecedence) {
            needParens = false;
        }
        else if (myPrecedence == thePrecedence) {
            if (isSymmetric()) {
                needParens = false;
            }
            else {
                needParens = !left;
            }
        }

        if (needParens) {
            return "(" + expression.toString() + ")";
        }
        else {
            return expression.toString();
        }
    }    
}