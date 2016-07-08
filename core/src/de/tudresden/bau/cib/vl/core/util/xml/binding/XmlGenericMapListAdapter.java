package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapListEntryType;
import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapListType;

/**
 * Taken from {@link http://javaevangelist.blogspot.de/2011/12/java-tip-of-day-generic-jaxb-map-v.html}
 *
 * @param <K>
 * @param <V>
 */
public class XmlGenericMapListAdapter<K,V> extends XmlAdapter<GenericMapListType<K,V>, Map<K, List<V>>> {
	 
    @Override
    public Map<K, List<V>> unmarshal(GenericMapListType<K,V> v) throws Exception {
        HashMap<K, List<V>> map = new HashMap<K, List<V>>();
 
        for (GenericMapListEntryType<K,V> mapEntryType : v.getEntry()) {
            map.put(mapEntryType.getKey(), mapEntryType.getValue());
        }
        return map;
    }
 
    @Override
    public GenericMapListType<K,V> marshal(Map<K, List<V>> v) throws Exception {
        GenericMapListType<K,V> mapType = new GenericMapListType<K,V>();
 
        for (Map.Entry<K, List<V>> entry : v.entrySet()) {
            GenericMapListEntryType<K,V> mapEntryType = new GenericMapListEntryType<K,V>();
            mapEntryType.setKey(entry.getKey());
            mapEntryType.setValue(entry.getValue());
            mapType.getEntry().add(mapEntryType);
        }
        return mapType;
    }
}
