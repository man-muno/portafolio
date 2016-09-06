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

import org.apache.commons.jxpath.ri.InfoSetUtil;

/**
 * Implementation of Expression for the operation "&gt;".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2009/01/29 21:38:42 $
 */
public class XPathOperationGreaterThan extends XPathCoreOperation {

    public XPathOperationGreaterThan(XPathExpression arg1, XPathExpression arg2) {
        super(new XPathExpression[] { arg1, arg2 });
    }

    public Object computeValue(BPELContext context) {
        double l = InfoSetUtil.doubleValue(args[0].compute(context));
        double r = InfoSetUtil.doubleValue(args[1].compute(context));
        return l > r ? Boolean.TRUE : Boolean.FALSE;
    }
    
    protected int getPrecedence() {
        return 3;
    }

    protected boolean isSymmetric() {
        return false;
    }
    
    public String getSymbol() {
        return ">";
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
