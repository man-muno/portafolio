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
 * Captures the <code>foo[@name=<i>expr</i>]</code> expression. These
 * expressions are handled in a special way when applied to beans
 * or maps.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathNameAttributeTest extends XPathOperationEqual 
{
	
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------    
    public final static String EQUAL= "equal";

    public XPathNameAttributeTest(XPathExpression namePath, XPathExpression nameValue) 
    {
        super(namePath, nameValue);
    }

    public XPathExpression getNameTestExpression() 
    {
        return args[1];
    }

    /**
     * @see Expression#computeContextDependent()
     */
    public boolean computeContextDependent() 
    {
        return true;
    }
}
