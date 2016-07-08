//package de.tudresden.bau.cib.vl.gui.gis.service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import de.tudresden.bau.cib.vl.gui.common.interfaces.IPlugin;
//import de.tudresden.bau.cib.vl.gui.gis.view.GeoLocationView;
//
//public class GisViewPlugin implements IPlugin {
//
//	public static final String ID = "de.tudresden.bau.cib.vl.gui.gis";
//	public static final String SYMBOLIC_NAME = "gui-gis";
//	
//	
//	@Override
//	public String getId() {
//		return ID;
//	}
//
//	@Override
//	public String getName() {
//		return SYMBOLIC_NAME;
//	}
//
//	@Override
//	public void start() {
//
//	}
//
//	@Override
//	public void stop() {
//
//	}
//
//	@Override
//	public String[] getProvidedViewIdentifiers() {
//		Set<String> ids = new HashSet<String>();
//		ids.add(GeoLocationView.ID);
//		return ids.toArray(new String[ids.size()]);
//	}
//
//}
