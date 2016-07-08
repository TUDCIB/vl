package de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles;

import de.tudresden.bau.cib.vl.core.model.Model;


public class TheraklesClimateLocation extends Model {

	private String country;
	private String town;
	private String region;
	private String latitude;
	private String longitude;
	private float orientation;
	private float albedo;
	private float altitude;
	public static final String NS_CLIMATELOCATION = "http://ontology.cib.bau.tu-dresden.de/eeBim#ClimateLocation";
	public static final String TYPE_CLIMATELOCATION = "ClimateLocation";
	
	
	public TheraklesClimateLocation(String pathToTemplate) {
		this.sourceFilePath = pathToTemplate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public float getOrientation() {
		return orientation;
	}

	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	public float getAlbedo() {
		return albedo;
	}

	public void setAlbedo(float albedo) {
		this.albedo = albedo;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	@Override
	public String getModelType() {
		return TYPE_CLIMATELOCATION;
	}

	@Override
	public String getNameSpace() {
		return NS_CLIMATELOCATION;
	}

}
