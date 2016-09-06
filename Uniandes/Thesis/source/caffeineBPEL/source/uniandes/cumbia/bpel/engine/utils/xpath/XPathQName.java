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
 * A qualified name: a combination of an optional namespace prefix
 * and an local name.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2008/09/08 09:51:48 $
 */
public class XPathQName 
{
    private String prefix;
    private String name;

    public XPathQName(String qualifiedName) 
    {
        int index = qualifiedName.indexOf(':');
        if (index == -1) {
            prefix = null;
            name = qualifiedName;
        }
        else {
            prefix = qualifiedName.substring(0, index);
            name = qualifiedName.substring(index + 1);
        }
    }

    public XPathQName(String prefix, String localName) {
        this.prefix = prefix;
        this.name = localName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if (prefix != null) {
            return prefix + ':' + name;
        }
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof XPathQName)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        XPathQName that = (XPathQName) object;
        if (!this.name.equals(that.name)) {
            return false;
        }

        if ((this.prefix == null && that.prefix != null)
            || (this.prefix != null && !this.prefix.equals(that.prefix))) {
            return false;
        }

        return true;
    }
}