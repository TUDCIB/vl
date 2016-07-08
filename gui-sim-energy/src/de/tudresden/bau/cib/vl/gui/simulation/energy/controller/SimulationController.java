//package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;
//
//import java.util.Set;
//
//import org.eclipse.rap.rwt.SessionSingletonBase;
//import org.eclipse.rap.rwt.SingletonUtil;
//
//import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
//
//public class SimulationController {
//
//	private Ifc2x3DataModelProxy ifcModel;
//	private Set<String> selectedGuids;
//
//	/**
//	 * Private constructor because of singleton pattern.
//	 */
//	private SimulationController() {
//
//	}
//	
//	public static SimulationController getInstance() {
//		return SingletonUtil.getSessionInstance(SimulationController.class);
//	}
//
//	public Ifc2x3DataModelProxy getIfcModel() {
//		return ifcModel;
//	}
//
//	public void setIfcModel(Ifc2x3DataModelProxy ifcModel) {
//		this.ifcModel = ifcModel;
//	}
//
//	public Set<String> getSelectedGuids() {
//		return selectedGuids;
//	}
//
//	public void setSelectedGuids(Set<String> selectedGuids) {
//		this.selectedGuids = selectedGuids;
//	}
//}
