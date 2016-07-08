package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapListEntryType;

/**
 * Taken from {@link http://javaevangelist.blogspot.de/2011/12/java-tip-of-day-generic-jaxb-map-v.html}
 *
 * @param <K>
 * @param <V>
 */
public class GenericMapListType<K,V> {
	 
    private List<GenericMapListEntryType<K,V>> entry = new ArrayList<GenericMapListEntryType<K,V>>();
 
    public GenericMapListType() {
    }
 
    public GenericMapListType(Map<K, List<V>> map) {
        for (Map.Entry<K, List<V>> e : map.entrySet()) {
            entry.add(new GenericMapListEntryType<K,V>(e));
        }
    }
 
    public List<GenericMapListEntryType<K,V>> getEntry() {
        return entry;
    }
 
    public void setEntry(List<GenericMapListEntryType<K,V>> entry) {
        this.entry = entry;
    }
}
