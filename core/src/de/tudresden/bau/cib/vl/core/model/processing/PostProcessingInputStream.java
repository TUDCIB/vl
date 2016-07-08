package de.tudresden.bau.cib.vl.core.model.processing;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;


public abstract class PostProcessingInputStream implements Callable<List<MeasuredUnit>> {

	protected String path;
	
	
	public PostProcessingInputStream(String path) {
		this.path = path;
	}
	
//	public static TimeUnit getTimeUnitfromString(String time) {
//		if(time.equalsIgnoreCase("h")) return TimeUnit.HOURS;
//		if(time.equalsIgnoreCase("d")) return TimeUnit.DAYS;
//		return null;
//	}
	
	@Override
	public List<MeasuredUnit> call() throws Exception {
		return process();
	}
	
	protected abstract List<MeasuredUnit> process() throws IOException;
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		PostProcessingInputStream other = (PostProcessingInputStream) obj;
		return other.hashCode() == this.hashCode() && this.path.equals(other.path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
