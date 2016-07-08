package de.tudresden.bau.cib.vl.core.model.eeBim.template;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.bau.cib.vl.core.model.energy.Schedule;


/**
 * Space type templates define the usage of the rooms. In HESMOS we take the DIN for classify the templates.
 * These templates are all saved in one XML document.
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class SpaceTypeTemplate extends RemoteResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368536516996158287L;


	public static final String TYPE_SPACETYPE = "SpaceType";
	private String HeatingControlMode;
	private String CoolingControlMode;
	private String ShortDescription;
	private String LongDescription;
	private List<Schedule> schedules;
	
	
	@Override
	public void setSourceFileUri(String sourceFilePath) {
//		Overridden because these templates are all saved in one XML which breaks our identifiers for the link model
		this.sourceFileUri = sourceFilePath;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		setId(name);
	}
	
	@Override
	public String toString() {
		return TYPE_SPACETYPE +"("+super.toString()+")";
	}

	public String getHeatingControlMode() {
		return HeatingControlMode;
	}

	public void setHeatingControlMode(String heatingControlMode) {
		HeatingControlMode = heatingControlMode;
	}

	public String getCoolingControlMode() {
		return CoolingControlMode;
	}

	public void setCoolingControlMode(String coolingControlMode) {
		CoolingControlMode = coolingControlMode;
	}

	public String getShortDescription() {
		return ShortDescription;
	}

	public void setShortDescription(String shortDescription) {
		ShortDescription = shortDescription;
	}

	public String getLongDescription() {
		return LongDescription;
	}

	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = new ArrayList<Schedule>(schedules);
	}

}
