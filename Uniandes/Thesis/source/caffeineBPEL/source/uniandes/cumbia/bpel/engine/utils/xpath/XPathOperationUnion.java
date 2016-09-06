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


/**
 * Implementation of Expression for the operation "|".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathOperationUnion extends XPathCoreOperation {

    public XPathOperationUnion(XPathExpression args[]) {
        super(args);
    }

    public Object computeValue(BPELContext context) {
        /*EvalContext argCtxs[] = new EvalContext[args.length];
        for (int i = 0; i < args.length; i++) {
            Object value = args[i].compute(context);
            if (value instanceof EvalContext) {
                argCtxs[i] = (EvalContext) value;
            }
            else {
                argCtxs[i] = context.getRootContext().getConstantContext(value);
            }
        }
        return new UnionContext(context.getRootContext(), argCtxs);*/
    	return null;
//    	TODO ARREGLAR
    }
    
    protected int getPrecedence() {
        return 7;
    }

    protected boolean isSymmetric() {
        return true;
    }
    
    public String getSymbol() {
        return "|";
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
