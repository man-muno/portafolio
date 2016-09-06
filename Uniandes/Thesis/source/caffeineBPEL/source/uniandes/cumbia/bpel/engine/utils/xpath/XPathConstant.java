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
 * A compile tree element containing a constant number or string.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathConstant extends XPathExpression {

    private Object value;
    
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
    
    public final static String CONSTANT= "constant";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public XPathConstant(Number number) 
    {
        value = number;
    }

    public XPathConstant(String string) 
    {
        value = string;
    }
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	/**
	 * Returns the type of the expression
	 * @return The type of the expression
	 */
	public String getType()
	{
		return CONSTANT;
	}


    /**
     * Returns the value of the constant.
     */
    public Object compute(BPELContext context) 
    {
        return value;
    }

    /**
     * Returns false
     */
    public boolean isContextDependent() 
    {
        return false;
    }


    public String toString()
{
        if (value instanceof Number) 
        {
            double doubleValue = ((Number) value).doubleValue();
            long longValue = ((Number) value).longValue();
            if (doubleValue == longValue) 
            {
                return String.valueOf(longValue);
            }
            else 
            {
                return String.valueOf(doubleValue);
            }
        }
        else {
            return "'" + value + "'";
        }
    }
}