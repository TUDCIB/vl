/*
 * Copyright 2011 John Yeary <jyeary@bluelotussoftware.com>.
 * Copyright 2011 Bluelotus Software, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: XmlGenericMapAdapter.java 399 2011-12-03 04:22:50Z jyeary $
 */
package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * @author Ken Baumgaertel 
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 * @param <K>
 * @param <V>
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GenericMapListEntryType<K,V> {
 
    private K key;
    private List<V> value;
 
    public GenericMapListEntryType() {
    }
 
    public GenericMapListEntryType(Map.Entry<K, List<V>> e) {
        key = e.getKey();
        value = e.getValue();
    }
 
    @XmlElement
    public K getKey() {
        return key;
    }
 
    public void setKey(K key) {
        this.key = key;
    }
 
    @XmlElement
    public List<V> getValue() {
        return value;
    }
 
    public void setValue(List<V> value) {
        this.value = value;
    }
}
