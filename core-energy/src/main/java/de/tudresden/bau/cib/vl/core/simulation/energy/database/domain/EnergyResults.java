package de.tudresden.bau.cib.vl.core.simulation.energy.database.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class EnergyResults implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -118221876734447114L;

	/**
	 * Unique identifier
	 */
	private Integer id;
	
	/**
	 * Related simulation
	 */
	private Integer simulationId;
	
	private TreeMap<Long, Double> timeToValue;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	void setId(Integer id) {
		this.id = id;
	}
	
//	public ArrayList<KeyValuePair> getKeyValuePairs() {
//		return keyValuePairs;
//	}
//
//	@ManyToMany(mappedBy="id")
//	public void setKeyValuePairs(ArrayList<KeyValuePair> keyValuePairs) {
//		this.keyValuePairs = keyValuePairs;
//	}
	
	public void addResultValue(Long timeInMilliseconds, Double value) {
		this.timeToValue.put(timeInMilliseconds, value);
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public Map<Long, Double> getTimeToValue() {
		return timeToValue;
	}

	public void setTimeToValue(Map<Long, Double> values) {
		this.timeToValue = new TreeMap<Long, Double>(values);
	}
	
	public Integer getSimulationId() {
		return simulationId;
	}
	
	public void setSimulationId(Integer simulationId) {
		this.simulationId = simulationId;
	}
}
