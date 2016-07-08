package de.tudresden.bau.cib.vl.gui.common;

public interface Constants {

	String PLACEHOLDER_PREFIX 				= "de.tudresden.bau.cib.vl.gui";
	String PLACEHOLDER_WILDCARD 			= "**"; 
	
	String PLACEHOLDER_TOP					= "ui.view.top";
	String PLACEHOLDER_WINDOW_MAIN 			= "ui.view.main";
	String PLACEHOLDER_WINDOW_LEFT 			= "ui.view.data";
	String PLACEHOLDER_WINDOW_BOTTOM 		= "ui.view.properties";
	String PLACEHOLDER_WINDOW_BOTTOM_LEFT 	= "ui.view.support";
	String PLACEHOLDER_WINDOW_TOP 			= "ui.view.additional_top";
	String PLACEHOLDER_WINDOW_RIGHT_BOTTOM 	= "ui.view.additional_bottom";
	
	// add placeholders for the views, ':' stands for multiple instance views
	// top
	String PLACEHOLDER_TOP_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_TOP+".*";
//	main
	String PLACEHOLDER_WINDOW_MAIN_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_MAIN+".*";
//	left
	String PLACEHOLDER_WINDOW_LEFT_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_LEFT+".*";
//	bottom
	String PLACEHOLDER_WINDOW_BOTTOM_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_BOTTOM+".*";
//	bottom left
	String PLACEHOLDER_WINDOW_BOTTOM_LEFT_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_BOTTOM_LEFT+".*";
//	right top
	String PLACEHOLDER_WINDOW_TOP_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_TOP+".*";
//	right bottom
	String PLACEHOLDER_WINDOW_RIGHT_BOTTOM_FULL = PLACEHOLDER_PREFIX+"."+PLACEHOLDER_WILDCARD+"."+PLACEHOLDER_WINDOW_RIGHT_BOTTOM+".*";
}
