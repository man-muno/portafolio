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

import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.axes.InitialContext;

/**
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathLocationPath extends XPathPath {

    private boolean absolute;

    public XPathLocationPath(boolean absolute, XPathStep[] steps) {
        super(steps);
        this.absolute = absolute;
    }

    public boolean isAbsolute() {
        return absolute;
    }

    public boolean computeContextDependent() {
        if (!absolute) {
            return true;
        }

        return super.computeContextDependent();
    }

    public String toString() {
        /*StringBuffer buffer = new StringBuffer();
        Step steps[] = getSteps();
        if (steps != null) {
            for (int i = 0; i < steps.length; i++) {
                if (i > 0 || absolute) {
                    buffer.append('/');
                }
                buffer.append(steps[i]);
            }
        }
        return buffer.toString();
*/    
    	return null;
//    	TODO ARREGLAR	
    }

    public Object compute(EvalContext context) {
        // Create a chain of contexts
        EvalContext rootContext;
        if (isAbsolute()) {
            rootContext = context.getRootContext().getAbsoluteRootContext();
        }
        else {
            rootContext = new InitialContext(context);
        }
        return evalSteps(rootContext);
    }


    public Object computeValue(EvalContext context) {
        // Create a chain of contexts
        /*EvalContext rootContext;
        if (isAbsolute()) {
            rootContext = context.getRootContext().getAbsoluteRootContext();
        }
        else {
            rootContext = new InitialContext(context);
        }
        return getSingleNodePointerForSteps(rootContext);*/
    	return null;
//    	TODO ARREGLAR
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

	@Override
	public Object compute(BPELContext context) {
		// TODO Auto-generated method stub
		return null;
	}
}