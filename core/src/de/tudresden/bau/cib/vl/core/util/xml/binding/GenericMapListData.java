package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.tudresden.bau.cib.vl.core.util.xml.binding.XmlGenericMapListAdapter;

@XmlRootElement(name="mapdata")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericMapListData<K,V> {
	 
	@XmlJavaTypeAdapter(XmlGenericMapListAdapter.class)
	Map<K,List<V>> map;
	 
	public Map<K,List<V>> getMap() {
		return map;
	}
	 
	public void setMap(Map<K,List<V>> map) {
		this.map = map;
	}
	
	public void put(K key, List<V> value) {
		if(map == null) map = new HashMap<K, List<V>>();
		map.put(key, value);
	}
}