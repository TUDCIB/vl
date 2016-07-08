//package de.tudresden.bau.cib.vl.gui.bim.service;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.gui.bim.view.IFCTreeView;
//import de.tudresden.bau.cib.vl.gui.bim.view.X3DView;
//import de.tudresden.bau.cib.vl.gui.common.interfaces.IPlugin;
//
//
//
//public class BimViewPlugin implements IPlugin {
//	
//	public static final String RESOURCES_PATH = "resources"+File.separator;
//	public static final String ICONS_PATH = RESOURCES_PATH+"icons"+File.separator;
//	public static final String ICONS_16x16_PATH = ICONS_PATH+"16x16"+File.separator;
//	public static final String ICONS_22x22_PATH = ICONS_PATH+"22x22"+File.separator;
//	public static final String ICONS_24x24_PATH = ICONS_PATH+"24x24"+File.separator;
//	public static final String ICONS_32x32_PATH = ICONS_PATH+"32x32"+File.separator;
//	public static final String ICONS_64x64_PATH = ICONS_PATH+"64x64"+File.separator;
//	public static final String ICONS_128x128_PATH = ICONS_PATH+"128x128"+File.separator;
//	
//	public static final String ID = "de.tudresden.bau.cib.vl.gui.bim";
//	public static final String SYMBOLIC_NAME = "gui-bim";
//	
//	private static final Logger LOG = LoggerFactory.getLogger(BimViewPlugin.class);
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
////		ids.add(BimViewPart.ID);
//		ids.add(X3DView.ID);
//		ids.add(IFCTreeView.ID);
//		return ids.toArray(new String[ids.size()]);
//	}
//
//}
