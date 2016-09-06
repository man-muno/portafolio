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
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathNodeNameTest extends XPathNodeTest {
    private XPathQName qname;
    private String namespaceURI;

    public XPathNodeNameTest(XPathQName qname) {
        this.qname = qname;
    }
    
    public XPathNodeNameTest(XPathQName qname, String namespaceURI) {
        this.qname = qname;
        this.namespaceURI = namespaceURI;
    }

    public XPathQName getNodeName() {
        return qname;
    }
    
    public String getNamespaceURI() {
        return namespaceURI;
    }
    
    public boolean isWildcard() {
        return qname.getName().equals("*");
    }

    public String toString() {
        return qname.toString();
    }
}