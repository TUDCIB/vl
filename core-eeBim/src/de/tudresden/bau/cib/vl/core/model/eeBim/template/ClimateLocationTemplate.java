package de.tudresden.bau.cib.vl.core.model.eeBim.template;




public class ClimateLocationTemplate extends RemoteResource {

	public static final String TYPE_CLIMATELOCATION = "ClimateLocation";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119073487470873107L;
	private String country;
	private String town;
	private String region;
	private Float latitude;
	private Float longitude;
	private Float orientation;
	private Float albedo;
	private Float altitude;


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

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getOrientation() {
		return orientation;
	}

	public void setOrientation(Float orientation) {
		this.orientation = orientation;
	}

	public Float getAlbedo() {
		return albedo;
	}

	public void setAlbedo(Float albedo) {
		this.albedo = albedo;
	}

	public Float getAltitude() {
		return altitude;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}
	
	@Override
	public final void setSourceFileUri(String directoryPath) {
		super.setSourceFileUri(directoryPath);
	}
	
	@Override
	public String toString() {
		return super.toString()+" region: "+region+" latitude: "+latitude+" longitude: "+longitude;
	}

}
