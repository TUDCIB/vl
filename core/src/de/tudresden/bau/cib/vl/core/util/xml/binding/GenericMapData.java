package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericList;
import de.tudresden.bau.cib.vl.core.util.xml.binding.XmlGenericMapAdapter;

@XmlRootElement(name="mapdata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(GenericList.class)
public class GenericMapData<K,V> {
	 
	@XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
	Map<K,V> map;
	 
	public Map<K,V> getMap() {
		return map;
	}
	 
	public void setMap(Map<K,V> map) {
		this.map = map;
	}
	
	public void put(K key, V value) {
		if(this.map == null) this.map = new HashMap<K, V>();
		map.put(key, value);
	}
}