package de.tudresden.bau.cib.vl.core.model.processing;


public abstract class EnergyPostProcessingInputStream extends PostProcessingInputStream {

	protected int code;
	
	public EnergyPostProcessingInputStream(int code, String path) {
		super(path);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode()+code;
	}


}
