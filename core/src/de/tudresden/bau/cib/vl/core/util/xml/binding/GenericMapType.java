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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapEntryType;

/**
 * Taken from {@link http://javaevangelist.blogspot.de/2011/12/java-tip-of-day-generic-jaxb-map-v.html}
 *
 * @param <K>
 * @param <V>
 */
public class GenericMapType<K, V> {
	 
    private List<GenericMapEntryType<K, V>> entry = new ArrayList<GenericMapEntryType<K, V>>();
 
    public GenericMapType() {
    }
 
    public GenericMapType(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            entry.add(new GenericMapEntryType<K, V>(e));
        }
    }
 
    public List<GenericMapEntryType<K, V>> getEntry() {
        return entry;
    }
 
    public void setEntry(List<GenericMapEntryType<K, V>> entry) {
        this.entry = entry;
    }
}
