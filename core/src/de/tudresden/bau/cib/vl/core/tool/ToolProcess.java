package de.tudresden.bau.cib.vl.core.tool;

import java.util.concurrent.Callable;


public abstract class ToolProcess<T> implements Callable<T>{	
	
	protected String pathToBatchFile;
	protected String batchFileName;
	
	public ToolProcess(String pathToBatchFile, String batchFileName) {
		this.pathToBatchFile = pathToBatchFile;
		this.batchFileName = batchFileName;
	}
}
