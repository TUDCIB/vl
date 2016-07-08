package de.tudresden.bau.cib.ui.widgets.google;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

import de.tudresden.bau.cib.ui.widgets.Widget;

/**
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class GMap extends Widget {
	
	private Browser browser;
	
	public static class GeoPoint {
		private float latitude;
		private float longitude;
		
		public GeoPoint(float lat, float lng) {
			this.latitude = lat;
			this.longitude = lng;
		}
		
		public float getLatitude() {
			return latitude;
		}
		
		public float getLongitude() {
			return longitude;
		}
	}
	
	private GeoPoint geoPoint;
	private String centerLocation;
	private Integer zoom = 11;
	private static final String BASE_URL = "https://www.google.de/maps/";
	private static final String API_KEY = "AIzaSyBq5mPDbWi-VwRM3dI5PtToZ3sY4Q7DuLI";
	private static final String DEFAULT_SIZE = "800x600";

	private static final String EMBED_API_SCRIPT = 
			"<script charset='UTF-8' src='https://www.google.com/jsapi' type='text/javascript'/>";
	private static final String EMBED_MAPS_API_SCRIPT = 
			"<script charset='UTF-8' src='https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false' type='text/javascript'/>";
	

	public GMap(final Composite parent, final int style) {
		super(parent, style);
		init();
	}

	private void init() {
		if(browser == null) {
			browser = new Browser(this, SWT.NONE);
//			browser.setUrl(BASE_URL+"?size="+DEFAULT_SIZE+"&key="+API_KEY);
			browser.setUrl("https://www.google.de/maps/");
			browser.setSize(800, 600);
//			browser.setUrl("https://www.google.com/maps/embed/v1/place?key="+API_KEY+"&center=Dresden");
		}
	}
	
	private void createDiv() {
		String html = "";
		html += "<div id='gmapDiv' style='height: 100%; width: 100%; position: relative; overflow: hidden;'";
	}

	public GeoPoint getGeoPoint() {
		checkWidget();
		return geoPoint;
	}

	public void setGeoPoint(final GeoPoint point) {
		checkWidget();
		this.geoPoint = point;
		
		init();
//		https://maps.googleapis.com/maps/api/staticmap?center=New+York,NY&zoom=13&size=800x600&key=AIzaSyBq5mPDbWi-VwRM3dI5PtToZ3sY4Q7DuLI

		String url = BASE_URL+"?";
		url += url+"center="+point.latitude+","+point.longitude;
		url += "&zoom="+getZoom();
		url += "&size="+DEFAULT_SIZE;
		url += "&key="+API_KEY;
		browser.setUrl(url);
	}
	
	

	public void setCenterLocation(GeoPoint point) {
		checkWidget();
		this.centerLocation = point.latitude+","+point.longitude;
	}

	public GeoPoint getCenterLocation() {
		checkWidget();
		String[] coordinates = centerLocation.split(",");
		if(coordinates.length > 2) throw new IllegalArgumentException("Center location points cannot be evaluated: "+centerLocation);
		GeoPoint point = new GeoPoint(Float.valueOf(coordinates[0]), Float.valueOf(coordinates[1]));
		return point;
	}

	public Integer getZoom() {
		checkWidget();
		return zoom;
	}

	public void setZoom(Integer zoom) {
		checkWidget();
		this.zoom = zoom;
		setGeoPoint(getGeoPoint());
	}
	
	public void setAddress(String address) {
		String url = BASE_URL+"?";
		url += url+"center="+address;
		url += "&zoom="+getZoom();
		url += "&size="+DEFAULT_SIZE;
		url += "&key="+API_KEY;
		browser.setUrl(url);
	}
}
