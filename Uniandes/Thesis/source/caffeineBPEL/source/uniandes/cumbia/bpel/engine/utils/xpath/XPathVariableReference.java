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

import uniandes.cumbia.bpel.elements.process.IProcess;

/**
 * An element of the compile tree holding a variable reference.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.2 $ $Date: 2008/10/16 21:51:12 $
 */
public class XPathVariableReference extends XPathExpression {

    private XPathQName varName;

    public XPathVariableReference(XPathQName varName) {
        this.varName = varName;
    }

    public XPathQName getVariableName() {
        return varName;
    }

    public String toString() {
        return "$" + varName;
    }

    public boolean isContextDependent() {
        return false;
    }

    public boolean computeContextDependent() {
        return false;
    }

    public Object compute(BPELContext context) {
        return computeValue(context);
    }

    /**
     * Returns the value of the variable.
     */
    public Object computeValue(BPELContext context) 
    {
    	Object variable= searchData(varName.getName(), context.getElement());
        return variable;
    }


	@Override
	public String getType() 
	{
		return null;
	}
	
	
	/**
	 * Returns the variable with the id Specified. This method first looks in the process parent, if
	 * is not there it looks in the process grand parent and continuos until find the variable or it is not
	 * find anytinh   
	 * @param name The name of the variable to search
	 * @return The variable with the name id provided or null if it is not found
	 */
	protected Object searchData(String name, IProcess process)
	{
		return process.getData( name );
	}
}