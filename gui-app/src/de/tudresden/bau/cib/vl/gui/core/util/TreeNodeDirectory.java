package de.tudresden.bau.cib.vl.gui.core.util;

import java.util.ArrayList;

public class TreeNodeDirectory extends TreeNode {
	
	private String pathDirectory;
	
	public String getPathDirectory() {
		return pathDirectory;
	}

	public void setPathDirectory(String pathDirectory) {
		this.pathDirectory = pathDirectory;
	}

	public TreeNodeDirectory(String name) {
		super(name);
	}
}
