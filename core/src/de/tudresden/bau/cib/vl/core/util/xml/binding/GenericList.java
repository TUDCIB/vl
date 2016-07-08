package de.tudresden.bau.cib.vl.core.util.xml.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import de.tudresden.bau.cib.vl.core.util.xml.binding.GenericMapData;

/**
 * Generic List for responses.
 * Taken from {@link http://stackoverflow.com/questions/1603404/using-jaxb-to-unmarshal-marshal-a-liststring}
 * 
 *
 * @param <T>
 */
@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(GenericMapData.class)
public class GenericList<T> {
	@XmlElement(name="item")
    protected List<T> list;

    public GenericList(){
    	
    }

    public GenericList(List<T> list){
        this.list=list;
    }
    
    public GenericList(Set<T> set){
    	this.list = new ArrayList<T>(set);
    }

//    @XmlElement(name="item")
    public List<T> getList(){
        return list;
    }

	public void setList(List<T> list) {
		this.list = list;
	}
}
