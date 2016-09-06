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
 * Implementation of Expression for the operation "+".
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathOperationAdd extends XPathCoreOperation 
{
	public final static String ADD= "add";
	
    public XPathOperationAdd(XPathExpression[] args) {
        super(args);
    }

    public Object computeValue(BPELContext context) {
        double s = 0.0;
        for (int i = 0; i < args.length; i++) {
            s += InfoSetUtil.doubleValue(args[i].compute(context));
        }
        return new Double(s);
    }
    
    protected int getPrecedence() {
        return 4;
    }

    protected boolean isSymmetric() {
        return true;
    }
    
    public String getSymbol() {
        return "+";
    }

	@Override
	public String getType() 
	{
		return ADD;
	}

	@Override
	public boolean isContextDependent() 
	{	
		return computeContextDependent();
	}
}