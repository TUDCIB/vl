package de.tudresden.bau.cib.vl.gui.core.util;

import org.eclipse.core.runtime.IAdaptable;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;


public class TreeNodeFile extends TreeNode {

	private FileInformation treeFile;

	public TreeNodeFile(String name) {
		super(name);
	}
	
	public FileInformation getFile() {
		return treeFile;
	}



	public void setFile(FileInformation treeFile) {
		this.treeFile = treeFile;
	}
	
	

}