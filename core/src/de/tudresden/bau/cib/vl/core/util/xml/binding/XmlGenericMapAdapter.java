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

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapEntryType;
import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapType;

/**
 * Taken from {@link http://javaevangelist.blogspot.de/2011/12/java-tip-of-day-generic-jaxb-map-v.html}
 *
 * @param <K>
 * @param <V>
 */
public class XmlGenericMapAdapter<K, V> extends XmlAdapter<GenericMapType<K, V>, Map<K, V>> {
	 
    @Override
    public Map<K, V> unmarshal(GenericMapType<K, V> v) throws Exception {
        HashMap<K, V> map = new HashMap<K, V>();
 
        for (GenericMapEntryType<K, V> mapEntryType : v.getEntry()) {
            map.put(mapEntryType.getKey(), mapEntryType.getValue());
        }
        return map;
    }
 
    @Override
    public GenericMapType<K,V> marshal(Map<K, V> v) throws Exception {
        GenericMapType<K, V> mapType = new GenericMapType<K, V>();
 
        for (Map.Entry<K, V> entry : v.entrySet()) {
            GenericMapEntryType<K, V> mapEntryType = new GenericMapEntryType<K, V>();
            mapEntryType.setKey(entry.getKey());
            mapEntryType.setValue(entry.getValue());
            mapType.getEntry().add(mapEntryType);
        }
        return mapType;
    }
}
