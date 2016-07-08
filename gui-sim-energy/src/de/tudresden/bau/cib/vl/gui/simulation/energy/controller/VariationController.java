package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElementGroup;
import de.tudresden.bau.cib.simmatrix.TLayer;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.util.Pair;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.utility.VariationTreeNode;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.VariationView;

public class VariationController extends AbstractViewController<VariationView> {

	private TSimulationMatrix model;	
	private Ifc2x3DataModelProxy ifcModel;
	
	private TemplateService templateService;
	
	private int countVariation = 0;
	private int countCombination = 0;
	

	
	private TreeNode invisibleRoot;
	private ArrayList<VariationTreeNode> selectedNodes;
	
	protected static Logger LOG = LoggerFactory.getLogger(SimulationResultController.class);	
	
	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {		
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.SIMULATION_MATRIX_MODEL);	
		events.add(CommunicationEvents.IFC_MODEL);	
		return events;
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
	LOG.debug("Retrieving data: {} with topic: {}", 
				new Object[]{dataMap, event.getName()});
	Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
	
	switch(event) {
		case SIMULATION_MATRIX_MODEL: 
		{
			TSimulationMatrix simMatrix = (TSimulationMatrix) data;
			if(simMatrix != null)
				setModel(simMatrix);
		}		
		break;
		case IFC_MODEL: 
		{
			ifcModel = (Ifc2x3DataModelProxy) data;	
		}
		
		
		break;	
		default:
			break;
	}
		
	}


	public TSimulationMatrix getModel() {
		return model;
	}

	public void setModel(TSimulationMatrix model) {
		this.model = model;	
		
		createModelGUI(model);
		
		
		view.setModel(invisibleRoot);
		view.setNumberOfCombination(countCombination);
	}
	
	private void createModelGUI(TSimulationMatrix model)
	{
		invisibleRoot =  new TreeNode("");		
		countCombination = 0;
		
		if( model instanceof TSimulationMatrix)
		{								
			
			TSimulationMatrix matrix = (TSimulationMatrix)  model;
			TCombinations combinations = matrix.getCombinations();
			if(combinations != null)
			{					
			
				EList<TCombination> lstCombinations= combinations.getCombination();
				
				for(TCombination combination : lstCombinations)
				{
					
					VariationTreeNode nodeComb = new VariationTreeNode("Combination [" + combination.getId() + "]", combination.getId());
					invisibleRoot.addChild(nodeComb);					
					
					countCombination++;
					
					for(TVariant variant : combination.getVariant())
					{
						String aref = variant.getAREF();
						String vref = variant.getVREF();		
						
						VariationTreeNode nodeVariant = new VariationTreeNode("Variant");
						nodeComb.addChild(nodeVariant);	
						
						TVariables variables = matrix.getVariables();
						
						if( variables != null )
						{
							
							//Construction
							TConstructionType constType = null;
							TConstructionTypeVariant constTypeVar = null;							
							ConstructionTemplate constTempl = null;
						
							TConstructionTypes constructionTypes = variables.getConstructionTypeVariables();
						
							if(constructionTypes != null)
							{									
								
								boolean outerBreak = false;
								for (TConstructionType constTypeHelp : constructionTypes.getConstructionType())
								{
									
									if(outerBreak) break;
									
									for( TConstructionTypeVariant constTypeVarHelp : constTypeHelp.getConstructionTypeVariant())
									{
										if (constTypeVarHelp.getId().compareTo(vref) == 0)
										{
											constType = constTypeHelp;
											constTypeVar = constTypeVarHelp;		
											
											constTempl = templateService.findConstructionTemplateByUri(constType.getSource(), 
													templateService.listConstructionResources());
											
											outerBreak = true;
											break;
											
										}
									}							
								}
							}	
							
							
							TWeatherDataSetVariant weatherVar = null;
							ClimateLocationTemplate climateTempl = null;
							
							if(variables.getWeatherVariables() != null)
							{									
								
								
								for (TWeatherDataSetVariant weatherVarHelp : matrix.getVariables().getWeatherVariables().getWeatherDataSetVariant())
								{		
									if (weatherVarHelp.getId().compareTo(vref) == 0)
									{
										
										weatherVar = weatherVarHelp;											
										climateTempl = templateService.findTemplateByUri(weatherVar.getValue(), 
																		templateService.listClimateResources());
										
									
										break;
										
									}																
								}
							}
							
							
							if(constType != null)
							{
//								VariationTreeNode nodeConstType = (VariationTreeNode) nodeVariant.getChildById(constType.getSource());
//								if(nodeConstType == null)
//								{
//									
//									nodeConstType = new VariationTreeNode("Construction ["+ constTempl.getName() + "]",constType.getSource());
//								
//									nodeVariant.addChild(nodeConstType);
//								}									
							
								if(constTypeVar != null)
								{
									VariationTreeNode nodeConstTypeVar = (VariationTreeNode) nodeVariant.getChildById(constTypeVar.getId());
									if(nodeConstTypeVar == null)
									{
										nodeConstTypeVar = new VariationTreeNode("Construction Variation [" +constTypeVar.getId() + "] of construction [" + constTempl.getName() + "]", constTypeVar.getId());									
										nodeVariant.addChild(nodeConstTypeVar);									
										
										String name = "Layer";
										for(TLayer layer : constTypeVar.getLayer())
										{													
											double thickness = layer.getThickness();
											MaterialTemplate matTempl = null;
											if(constTempl != null && StringUtils.isNotEmpty(layer.getValue()))
											{
												 matTempl = templateService.findTemplateByUri(layer.getValue().trim(),  
														 					templateService.listMaterialsOfConstruction(constTempl));
											}	
												 
											if(matTempl != null)
											{
												name = name+" '" + matTempl.getName()+ "': " + String.valueOf(layer.getThickness()) + 
															'[' + layer.getUnit().toString() + ']';
											} else {
												name += ": "+thickness;
											}
											
											VariationTreeNode layerNode = new VariationTreeNode(name);														
											nodeConstTypeVar.addChild(layerNode);
											
										}
										
										
									}	
								}
							} // Add Construction END
							
							if(weatherVar != null)
							{
								VariationTreeNode nodeClimateVar =  (VariationTreeNode) nodeVariant.getChildById(weatherVar.getId());
								if(nodeClimateVar == null)
								{
									
									nodeClimateVar = new VariationTreeNode("Climate Variation [" + weatherVar.getId() + "]", weatherVar.getId());												
									nodeVariant.addChild(nodeClimateVar);		
									
									
									String name ="Unknown";
									if(climateTempl != null)
										name = climateTempl.getName();
									
									VariationTreeNode nodeClimate = new VariationTreeNode("Climate ["+ name + "]",weatherVar.getValue());									
									nodeClimateVar.addChild(nodeClimate);
								}
							} // Add Climate END						
							
						
							if(aref != null && !aref.isEmpty())
							{								
								for (TElementGroup elemGroup :	matrix.getAssignmentGroups().getElements().getElementGroup())
								{
									if (elemGroup.getId().compareTo(aref) == 0)
									{
										
										VariationTreeNode nodeElems = (VariationTreeNode) nodeVariant.getChildByName("Elements");
										if(nodeElems == null)
										{
											nodeElems = new VariationTreeNode("Elements");
											nodeVariant.addChild(nodeElems);
										}
										
										
										
										for(String elemIfcId : elemGroup.getElement())
										{
											String name = "Unknown";
											String elemType = "Unknown type";
											
											VariationTreeNode nodeIFC = (VariationTreeNode) nodeElems.getChildById(elemIfcId);
											if(nodeIFC == null)
											{										
												EIfcroot elem = getIFCElement(elemIfcId);
												
												if(elem != null)
												{
												
													String [] className = elem.getClass().toString().split("\\.");
													String [] tmp = className[className.length -1].split("Ifc");
													elemType = tmp[tmp.length-1];		
												
												
												
																									
													try {
														
														if(elem instanceof EIfcspatialstructureelement)														
															name = ((EIfcspatialstructureelement)elem).getLongname((EIfcspatialstructureelement)elem);
														else													
															name = elem.getName(elem);
													} catch (SdaiException e1) {												
														LOG.error(e1.getMessage());												
													}		
												}
													
												nodeIFC = new VariationTreeNode(elemType + "[" + name + "]", elemIfcId);										
										
												nodeElems.addChild(nodeIFC);
												
											}										
											
										}
									}
								}
								
							} // Add Elements END						
								
						}
						else
						{
						
							
							VariationTreeNode root = new VariationTreeNode("No Variables defined in Simulation Matrix");
							invisibleRoot.addChild(root);
						}
						
					}
				}
			}//Combination exists
			else
			{
			
				
				VariationTreeNode root = new VariationTreeNode("No Combinations defined in Simulation Matrix");
				invisibleRoot.addChild(root);
			}
			
			
			
		}
		else
		{
			
			
			VariationTreeNode root = new VariationTreeNode("No Simulation Matrix loaded");
			invisibleRoot.addChild(root);
		}	
	}

	private void createModelGUIOLd(TSimulationMatrix model) {
		
		invisibleRoot =  new TreeNode("");		
		countVariation = 0;
		
		if( model instanceof TSimulationMatrix)
		{								
			
			TSimulationMatrix matrix = (TSimulationMatrix)  model;
			
			if(matrix.getCombinations() != null)
			{					
			
				EList<TCombination> lstCombinations= matrix.getCombinations().getCombination();
				
				for(TCombination combination : lstCombinations)
				{
					for(TVariant variant : combination.getVariant())
					{
						String aref = variant.getAREF();
						String vref = variant.getVREF();								
										
						
						if( matrix.getVariables() != null )
						{
							
							//Construction
							TConstructionType constType = null;
							TConstructionTypeVariant constTypeVar = null;	
							
							ConstructionTemplate constTempl = null;
						
						
							if(matrix.getVariables().getConstructionTypeVariables() != null)
							{									
								
								boolean outerBreak = false;
								for (TConstructionType constTypeHelp : matrix.getVariables().getConstructionTypeVariables().getConstructionType())
								{
									
									if(outerBreak) break;
									
									for( TConstructionTypeVariant constTypeVarHelp : constTypeHelp.getConstructionTypeVariant())
									{
										if (constTypeVarHelp.getId().compareTo(vref) == 0)
										{
											constType = constTypeHelp;
											constTypeVar = constTypeVarHelp;		
											
											constTempl = templateService.findConstructionTemplateByUri(constType.getSource(), 
													templateService.listConstructionResources());
											
											outerBreak = true;
											break;
											
										}
									}							
								}
							}	
							
							
							TWeatherDataSetVariant weatherVar = null;
							ClimateLocationTemplate climateTempl = null;
							
							if(matrix.getVariables().getWeatherVariables() != null)
							{									
								
								
								for (TWeatherDataSetVariant weatherVarHelp : matrix.getVariables().getWeatherVariables().getWeatherDataSetVariant())
								{		
									if (weatherVarHelp.getId().compareTo(vref) == 0)
									{
										
										weatherVar = weatherVarHelp;											
										climateTempl = templateService.findTemplateByUri(weatherVar.getValue(), 
																		templateService.listClimateResources());
										
									
										break;
										
									}																
								}
							}													
						
							
							//No Assignments to IFCElements
							//Need of Global-TreeNode
							if(aref == null || aref.isEmpty())
							{
								VariationTreeNode globalVarTreeNode = (VariationTreeNode) invisibleRoot.getChild("Global Variations");
								if(globalVarTreeNode == null)
								{
									globalVarTreeNode = new VariationTreeNode("Global Variations");
									invisibleRoot.addChild(globalVarTreeNode);
								}
							
								
								if(weatherVar != null)
								{
									VariationTreeNode nodeClimateVar =  (VariationTreeNode) globalVarTreeNode.getChildById(weatherVar.getId());
									if(nodeClimateVar == null)
									{
										
										nodeClimateVar = new VariationTreeNode("Climate Variation [" + weatherVar.getId() + "]", weatherVar.getId());												
										globalVarTreeNode.addChild(nodeClimateVar);											
										
										nodeClimateVar.setPair(Pair.of(weatherVar.getId(), ""));
										
										countVariation++;
										
										String name ="Unknown";
										if(climateTempl != null)
											name = climateTempl.getName();
										
										VariationTreeNode nodeClimate = new VariationTreeNode("Climate ["+ name + "]",weatherVar.getValue());
										
										nodeClimateVar.addChild(nodeClimate);
									}
								} // Add Climate END		
								
							}
							else
							{
								VariationTreeNode elemVarTreeNode = (VariationTreeNode) invisibleRoot.getChild("Element Variations");
								if(elemVarTreeNode == null)
								{
									elemVarTreeNode = new VariationTreeNode("Element Variations");
									invisibleRoot.addChild(elemVarTreeNode);
								}

								
								for (TElementGroup elemGroup :	matrix.getAssignmentGroups().getElements().getElementGroup())
								{
									if (elemGroup.getId().compareTo(aref) == 0)
									{
										for(String elemIfcId : elemGroup.getElement())
										{
											
											VariationTreeNode nodeIFC = (VariationTreeNode) elemVarTreeNode.getChildById(elemIfcId);
											if(nodeIFC == null)
											{										
												EIfcroot elem = getIFCElement(elemIfcId);
												
												String [] className = elem.getClass().toString().split("\\.");
												String [] tmp = className[className.length -1].split("Ifc");
												String elemType = tmp[tmp.length-1];
												
												
												String name = "Unknown";
												try {
													
													if(elem instanceof EIfcspatialstructureelement)														
														name = ((EIfcspatialstructureelement)elem).getLongname((EIfcspatialstructureelement)elem);
													else													
														name = elem.getName(elem);
												} catch (SdaiException e1) {												
													LOG.error(e1.getMessage());												
												}											
												
													nodeIFC = new VariationTreeNode(elemType + "[" + name + "]", elemIfcId);
											
											
												elemVarTreeNode.addChild(nodeIFC);
											}
											
											
											if(constType != null)
											{
												VariationTreeNode nodeConstType = (VariationTreeNode) nodeIFC.getChildById(constType.getSource());
												if(nodeConstType == null)
												{
													
													nodeConstType = new VariationTreeNode("Construction ["+ constTempl.getName() + "]",constType.getSource());
												
													nodeIFC.addChild(nodeConstType);
												}									
											
												if(constTypeVar != null)
												{
													VariationTreeNode nodeConstTypeVar = (VariationTreeNode) nodeConstType.getChildById(constTypeVar.getId());
													if(nodeConstTypeVar == null)
													{
														nodeConstTypeVar = new VariationTreeNode("Construction Variation [" +constTypeVar.getId() + "]", constTypeVar.getId());
													
														nodeConstType.addChild(nodeConstTypeVar);
														
														nodeConstTypeVar.setPair(Pair.of(constTypeVar.getId(), elemIfcId));
														
														countVariation++;
														
														String name = "Layer";
														for(TLayer layer : constTypeVar.getLayer())
														{													
															
															MaterialTemplate matTempl = null;
															if(constTempl != null)
															{
																 matTempl = templateService.findTemplateByUri(layer.getValue(),  
																		 					templateService.listMaterialsOfConstruction(constTempl));
															}	
																 
															if(matTempl != null)
															{
																name = "Layer '" + matTempl.getName()+ "': " + String.valueOf(layer.getThickness()) + 
																			'[' + layer.getUnit().toString() + ']';
															}																
															
															VariationTreeNode layerNode = new VariationTreeNode(name);														
															nodeConstTypeVar.addChild(layerNode);
															
														}
														
														
													}	
												}
											} // Add Constrcution END
											
											if(weatherVar != null)
											{
												VariationTreeNode nodeClimateVar = (VariationTreeNode) nodeIFC.getChildById(weatherVar.getId());
												if(nodeClimateVar == null)
												{
													
													nodeClimateVar = new VariationTreeNode("Climate Variation [" + weatherVar.getId() + "]", weatherVar.getId());												
													nodeIFC.addChild(nodeClimateVar);											
													
													countVariation++;
													
													nodeClimateVar.setPair(Pair.of(weatherVar.getId(), elemIfcId));
													
													String name ="Unknown";
													if(climateTempl != null)
														name = climateTempl.getName();
													
													VariationTreeNode nodeClimate = new VariationTreeNode("Climate ["+ name + "]",weatherVar.getValue());
													
													nodeClimateVar.addChild(nodeClimate);
												}
											} // Add Climate END
											
											//TODO Ocuupancy
											
											
											
										}
									}
								}
								
							} //Assignemt exit END						
								
						}
						else
						{
						
							
							VariationTreeNode root = new VariationTreeNode("No Variables defined in Simulation Matrix");
							invisibleRoot.addChild(root);
						}
						
					}
				}
			}//Combination exists
			else
			{
			
				
				VariationTreeNode root = new VariationTreeNode("No Combinations defined in Simulation Matrix");
				invisibleRoot.addChild(root);
			}
			
			
			
		}
		else
		{
			
			
			VariationTreeNode root = new VariationTreeNode("No Simulation Matrix loaded");
			invisibleRoot.addChild(root);
		}	
		
	}
	
	private EIfcroot getIFCElement(String ifcID) 
	{
		
		EIfcroot ret = null;
		
		if(ifcModel != null)
		{
			Set<String> set = new HashSet<String>();
			set.add(ifcID);
			try {
				Set<EIfcroot> res = ifcModel.getIfcEntitiesByGlobalIds(set);
				if(res.size()==1)
				{
					for(EIfcroot root : res)
					{
						ret = root;					
					}
				}
			} catch (IfcException e) {
				LOG.error(e.getMessage());			
			} 
		}		
		
		return ret;
	}

	public void setSelectedNodes(ArrayList<VariationTreeNode> selectedNodes) {
		this.selectedNodes = new ArrayList<VariationTreeNode>(selectedNodes);
		
	}

	public void publishRemoveVariantOld()
	{
		
		List<Pair<String,String>> ids = new ArrayList<Pair<String,String>>();
		
		for(VariationTreeNode node : selectedNodes)
		{
			if(node.getPair() != null)
			{
				ids.add(node.getPair());
			}
		}
		
		sendSync(CommunicationEvents.SIMULATION_MATRIX_REMOVE_VARIANT,ids);
	}
	
	public void publishRemoveCombination()
	{
		
		List<String> ids = new ArrayList<String>();
		
		for(VariationTreeNode node : selectedNodes)
		{
					ids.add(node.getId());			
		}
		
		sendSync(CommunicationEvents.SIMULATION_MATRIX_REMOVE_COMBINATION,ids);
	}
	
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
}
