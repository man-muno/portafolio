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
 * Implementation of Expression for the operation "div".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathOperationDivide extends XPathCoreOperation 
{
	public final static String DIVIDE= "divide";
    public XPathOperationDivide(XPathExpression arg1, XPathExpression arg2) {
        super(new XPathExpression[] { arg1, arg2 });
    }

    public Object computeValue(BPELContext context) {
        double l = InfoSetUtil.doubleValue(args[0].compute(context));
        double r = InfoSetUtil.doubleValue(args[1].compute(context));
        return new Double(l / r);
    }
    
    protected int getPrecedence() {
        return 5;
    }

    protected boolean isSymmetric() {
        return false;
    }
    
    public String getSymbol() {
        return "div";
    }



	@Override
	public String getType() 
	{		
		return DIVIDE;
	}

	@Override
	public boolean isContextDependent() 
	{		
		return computeContextDependent();
	}
}
