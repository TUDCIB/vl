package de.tudresden.bau.cib.vl.gui.gis.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcpostaladdress;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.lang.A_integer;
import jsdai.lang.SdaiException;

import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
import de.tudresden.bau.cib.vl.gui.gis.Activator;
import de.tudresden.bau.cib.vl.gui.gis.exception.NoLocationFoundException;
import de.tudresden.bau.cib.vl.gui.gis.view.GeoLocationView;

public class LocationController extends AbstractViewController<GeoLocationView> {
	
	private Logger LOG = LoggerFactory.getLogger(LocationController.class);
	
	private Ifc2x3DataModelProxy ifcModel;
	
	
	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.IFC_MODEL);
		return events;
	}
	
	@Override
	public void setView(GeoLocationView view) {
		super.setView(view);
		if(ifcModel == null) {
			Runnable r = new Runnable() {		
				@Override
				public void run() {
					CommunicationService service = CommunicationService.getInstance();
					service.retrieveLatestData(CommunicationEvents.IFC_MODEL, LocationController.this);
				}
			};
			// wait 10s until loading
			Display.getDefault().timerExec(10000, r);
		}
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
		LOG.debug("Receive event: {} with data: {}", new Object[]{event.getName(), dataMap});
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		if(data != null) {
			switch(event) {
			case IFC_MODEL:
				ifcModel = (Ifc2x3DataModelProxy)data;
				try {
					String location = getLocation(ifcModel);
					if(location != null) view.setLocation(location);
				} catch (NoLocationFoundException e) {
					LOG.error("{}",e.getMessage(), e);
				}
				break;
			default:
				break;
			}	
		}
	}
	
	private String getLocation(Model model) throws NoLocationFoundException {
		if(model instanceof Ifc2x3DataModelProxy) {
			Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) model;
			try {
	//			site location for Google Maps
				EIfcsite site = ifcModel.getSite();
				if(site == null) throw new NoLocationFoundException("No IfcSite in IFC model");
				String location = "";
				StringBuilder sb = new StringBuilder();	
				if(site.testSiteaddress(site)){
					EIfcpostaladdress address = site.getSiteaddress(site);
					String country = address.getCountry(address).toString();
					String town = address.getTown(address).toString();
					String region = address.getRegion(address).toString();					
					location = country+","+town+","+region;
				} else if(site.testReflatitude(site)&&site.testReflongitude(site)) {
					A_integer latitudes = site.getReflatitude(site);
					for(int i = 1; i < (latitudes.getMemberCount()+1); i++) {
						if(i < 4) { // we don't want millionth of seconds
							int value = latitudes.getByIndex(i);
							sb.append(value < 10 ? "0"+value : value);
							if(i == 1) {
								sb.append(".");
							}
						}
					}
					sb.append(",");				
					A_integer longitudes = site.getReflongitude(site);
					for(int i = 1; i < (longitudes.getMemberCount()+1); i++) {
						if(i < 4) { // we don't want millionth of seconds
							int value = longitudes.getByIndex(i);							
							sb.append(value < 10 ? "0"+value : value);
							if(i == 1) {
								sb.append(".");
							}
						}
					}					
					location = sb.toString();
					LOG.info("Geographical position is: {}",location);
					return location;
				}				
			} catch (SdaiException e) {
				throw new NoLocationFoundException(e);
			} catch (IfcException ie) {
				throw new NoLocationFoundException(ie);
			}
		}
		return null;
	}
}
