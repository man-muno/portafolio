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
 * Implementation of Expression for the operation "=".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2009/01/29 21:38:42 $
 */
public class XPathOperationEqual extends XPathOperationCompare 
{
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
    
    public final static String EQUAL= "equal";
    
    public XPathOperationEqual(XPathExpression arg1, XPathExpression arg2) 
    {
        super(arg1, arg2);
    }

    public Object computeValue(BPELContext context) {
        return equal(context, args[0], args[1]) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    protected int getPrecedence() {
        return 2;
    }

    protected boolean isSymmetric() {
        return true;
    }
    
    public String getSymbol() {
        return "=";
    }

	@Override
	public String getType() 
	{		
		return EQUAL;
	}

	@Override
	public boolean isContextDependent() 
	{		
		return computeContextDependent();
	}
}
