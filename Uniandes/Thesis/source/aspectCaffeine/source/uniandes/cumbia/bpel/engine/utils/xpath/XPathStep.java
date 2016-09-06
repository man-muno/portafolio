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

import org.apache.commons.jxpath.ri.Compiler;

/**
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2009/01/29 21:38:42 $
 */
public class XPathStep {
    private int axis;
    private XPathNodeTest nodeTest;
    private XPathExpression[] predicates;

    protected XPathStep(int axis, XPathNodeTest nodeTest, XPathExpression[] predicates) {
        this.axis = axis;
        this.nodeTest = nodeTest;
        this.predicates = predicates;
    }

    public int getAxis() {
        return axis;
    }

    public XPathNodeTest getNodeTest() {
        return nodeTest;
    }

    public XPathExpression[] getPredicates() {
        return predicates;
    }

    public boolean isContextDependent() {
        if (predicates != null) {
            for (int i = 0; i < predicates.length; i++) {
                if (predicates[i].isContextDependent()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int axis = getAxis();
        if (axis == Compiler.AXIS_CHILD) {
            buffer.append(nodeTest);
        }
        else if (axis == Compiler.AXIS_ATTRIBUTE) {
            buffer.append('@');
            buffer.append(nodeTest);
        }
        else if (axis == Compiler.AXIS_SELF
                && nodeTest instanceof XPathNodeTypeTest
                && ((XPathNodeTypeTest) nodeTest).getNodeType()
                    == Compiler.NODE_TYPE_NODE) {
            buffer.append(".");
        }
        else if (axis == Compiler.AXIS_PARENT
                && nodeTest instanceof XPathNodeTypeTest
                && ((XPathNodeTypeTest) nodeTest).getNodeType()
                    == Compiler.NODE_TYPE_NODE) {
            buffer.append("..");
        }
        else if (axis == Compiler.AXIS_DESCENDANT_OR_SELF
                && nodeTest instanceof XPathNodeTypeTest
                && ((XPathNodeTypeTest) nodeTest).getNodeType()
                    == Compiler.NODE_TYPE_NODE 
                && (predicates == null || predicates.length == 0)) {
            buffer.append("");
        }
        else {
            buffer.append(axisToString(axis));
            buffer.append("::");
            buffer.append(nodeTest);
        }
        XPathExpression[] predicates = getPredicates();
        if (predicates != null) {
            for (int i = 0; i < predicates.length; i++) {
                buffer.append('[');
                buffer.append(predicates[i]);
                buffer.append(']');
            }
        }
        return buffer.toString();
    }

    public static String axisToString(int axis) {
        switch (axis) {
            case Compiler.AXIS_SELF :
                return "self";
            case Compiler.AXIS_CHILD :
                return "child";
            case Compiler.AXIS_PARENT :
                return "parent";
            case Compiler.AXIS_ANCESTOR :
                return "ancestor";
            case Compiler.AXIS_ATTRIBUTE :
                return "attribute";
            case Compiler.AXIS_NAMESPACE :
                return "namespace";
            case Compiler.AXIS_PRECEDING :
                return "preceding";
            case Compiler.AXIS_FOLLOWING :
                return "following";
            case Compiler.AXIS_DESCENDANT :
                return "descendant";
            case Compiler.AXIS_ANCESTOR_OR_SELF :
                return "ancestor-or-self";
            case Compiler.AXIS_FOLLOWING_SIBLING :
                return "following-sibling";
            case Compiler.AXIS_PRECEDING_SIBLING :
                return "preceding-sibling";
            case Compiler.AXIS_DESCENDANT_OR_SELF :
                return "descendant-or-self";
        }
        return "UNKNOWN";
    }
}