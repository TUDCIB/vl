package de.tudresden.bau.cib.vl.gui.common.appearance;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;

public class AppearanceFactory {

	/**
	 * The shared instance.
	 */
	private static AppearanceFactory SINGLETON = null;
	
	private static Display display = Display.getCurrent();

//	####### COLORS #######
	public static final Color GREEN = new Color(display, new RGB(153, 204, 0));
	public static final Color BLACK = new Color(display, new RGB(255,255,255));
	public static final Color LIGHT_GREY = new Color(display, new RGB(238,238,238));
	
//	fonts
	public static Font DEFAULT_FONT_NORMAL = new Font(Display.getCurrent(), new FontData("Arial", 10, SWT.NORMAL));
	public static Font DEFAULT_FONT_BOLD = new Font(Display.getCurrent(), new FontData("Arial", 10, SWT.BOLD));
	public static Font DEFAULT_FONT_LARGE_BOLD = new Font(Display.getCurrent(), new FontData("Arial", 12, SWT.BOLD));
	public static Font DEFAULT_FONT_TITLE1_BOLD = new Font(Display.getCurrent(), new FontData("Arial", 24, SWT.BOLD));
	public static Font DEFAULT_FONT_TITLE2_BOLD = new Font(Display.getCurrent(), new FontData("Arial", 16, SWT.BOLD));
	public static Font DEFAULT_FONT_TITLE3_BOLD = new Font(Display.getCurrent(), new FontData("Arial", 14, SWT.BOLD));

	/**
	 * Private constructor because of singleton pattern.
	 */
	private AppearanceFactory() {
	}

	/**
	 * Retrieves the single instance of AppearanceFactory.
	 * @return The instance of AppearanceFactory.
	 */
	public static AppearanceFactory getInstance() {
		if (SINGLETON == null)
			SINGLETON = new AppearanceFactory();
		return SINGLETON;
	}
	
	/**
	 * @param symbolicPluginName
	 * @param imagePath Path to the image (with directory, file name and file extension)
	 * @return
	 */
	public ImageDescriptor getImageDescriptor(String symbolicPluginName, String imagePath) {
		Bundle bundle = Platform.getBundle(symbolicPluginName);
		URL fullPathString = BundleUtility.find(bundle, imagePath);
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(fullPathString);
		return imageDescriptor;
	}
	
	/**
	 * @param symbolicPluginName
	 * @param imagePath Path to the image (with directory, file name and file extension)
	 * @return
	 */
	public Image createImage(String symbolicPluginName, String imagePath) {
		ImageData imageData = getImageData(symbolicPluginName, imagePath);
		return new Image(display, imageData);
	}
	
	private ImageData getImageData(String symbolicPluginName, String imagePath) {
		ImageDescriptor imageDescriptor = getImageDescriptor(symbolicPluginName, imagePath);
		return imageDescriptor.getImageData();
	}
	
	/**
	 * @param symbolicPluginName Plugin name
	 * @param imagePath Path to the image (with directory, file name and file extension)
	 * @param width Width
	 * @param height Height
	 * @return New image based on the given path and size.
	 */
	public Image createImage(String symbolicPluginName, String imagePath, int width, int height) {
		ImageData imageData = getImageData(symbolicPluginName, imagePath);
		imageData = imageData.scaledTo(width, height);
		return new Image(display, imageData);
	}
}
