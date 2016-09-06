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


public abstract class XPathExpression 
{


    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	/**
	 * Returns the type of the expression
	 * @return The type of the expression
	 */
	public abstract String getType();
	
	/**
	 * Returns the value of the expression using the context provided
	 * @param context The context
	 * @return The value of the evalution
	 */
	public abstract Object compute(BPELContext context);
	
	public abstract boolean isContextDependent(); 
}