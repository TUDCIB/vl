package de.tudresden.bau.cib.vl.gui.gis.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.tudresden.bau.cib.ui.widgets.google.GMap;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.gis.controller.LocationController;

public class GeoLocationView extends AbstractView<LocationController> {

	public static final String ID = Constants.PLACEHOLDER_PREFIX+".gis."+Constants.PLACEHOLDER_WINDOW_MAIN+".Geolocation";
	private Display display;
	private String location;
	private GMap gmap;
	private LocationController controller;

	@Override
	protected LocationController createController() {
		controller = new LocationController();
		return controller;
	}

	@Override
	public void createPartControl(Composite parent) {	
		gmap = new GMap(parent, SWT.NONE);	
		this.display = parent.getDisplay();
	}

	@Override
	public void setFocus() {
		gmap.setFocus();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String newLocation) {
		this.location = newLocation;
		if(location != null) {
			display.asyncExec(
				new Runnable() {
					public void run(){
						gmap.setAddress(location);
				}
			});	
		}
	}
}
