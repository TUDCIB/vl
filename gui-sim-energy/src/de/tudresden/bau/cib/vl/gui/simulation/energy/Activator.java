package de.tudresden.bau.cib.vl.gui.simulation.energy;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gui-rcp-sim-energy"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private BundleContext context;
	
	public static final String RESOURCES_PATH = "resources"+File.separator;
	public static final String ICONS_PATH = RESOURCES_PATH+"icons"+File.separator;
	public static final String ICONS_16x16_PATH = ICONS_PATH+"16x16"+File.separator;
	public static final String ICONS_22x22_PATH = ICONS_PATH+"22x22"+File.separator;
	public static final String ICONS_24x24_PATH = ICONS_PATH+"24x24"+File.separator;
	public static final String ICONS_32x32_PATH = ICONS_PATH+"32x32"+File.separator;
	public static final String ICONS_64x64_PATH = ICONS_PATH+"64x64"+File.separator;
	public static final String ICONS_128x128_PATH = ICONS_PATH+"128x128"+File.separator;
	
	/**
	 * The constructor
	 */
	public Activator() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		this.context = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public BundleContext getContext() {
		return context;
	}
}
