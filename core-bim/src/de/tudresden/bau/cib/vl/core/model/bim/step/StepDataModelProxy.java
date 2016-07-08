package de.tudresden.bau.cib.vl.core.model.bim.step;

import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.model.StepDataModel;
import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.exception.StepException;


/**
 * Abstract wrapper class for the StepDataModel of the Bimfit library.
 * 
 * @author Ken
 *
 */
public abstract class StepDataModelProxy extends Model {

	private StepDataModel stepModel;
	
	
	public StepDataModelProxy(StepDataModel stepModel) {
		this.stepModel = stepModel;
	}
	
	public String[] getAttributeNames(EEntity entity) throws StepException {
		try {
			return stepModel.getAttributeNames(entity);
		} catch (SdaiException e) {
			throw new StepException(e);
		}
	}
	
	public EEntity[] getElements() {
		return stepModel.getEntities();
	}
	
	public Object getValueOfEntity(EEntity entity, String attributeName, boolean deepSearch) throws StepException {
		try {
			return stepModel.getValueOfAttribute(entity, attributeName, deepSearch);
		} catch (SdaiException e) {
			throw new StepException(e);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		StepDataModelProxy other = (StepDataModelProxy)obj;
		if(this.stepModel.equals(other.stepModel)) {
			return true;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return stepModel.hashCode();
	}
	
	public StepDataModel getUnderlyingModel() {
		return stepModel;
	}
}
