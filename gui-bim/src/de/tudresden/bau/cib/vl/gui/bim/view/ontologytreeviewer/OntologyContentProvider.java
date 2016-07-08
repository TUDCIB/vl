package de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer;

import java.io.File;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;

import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.gui.Activator;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;



public class OntologyContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	
	private final Image IMG_LAYERS = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
		  	Activator.ICONS_32x32_PATH+File.separator+"layers.png", 16, 16);
	private final Image IMG_CLIMATE = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
		  	Activator.ICONS_32x32_PATH+File.separator+"climate.png", 16, 16); 
	private final Image IMG_OCC = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
		  	Activator.ICONS_32x32_PATH+File.separator+"occupancy.png", 16, 16);
	private final Image IMG_SITE = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			  Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"site.png", 16, 16); 
	private final Image IMG_BLDG = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			  Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"building.png", 16, 16);
	private final Image IMG_STOREY = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			  Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"storey.png", 16, 16);
	private final Image IMG_SPACE = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			  Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"space.png", 16, 16);
	private final Image IMG_FOLDER = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	private final Image IMG_WALL = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"wall1.png", 16, 16);
	private final Image IMG_COL = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"column4.png", 16, 16);
	private final Image IMG_DOOR = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"door.png", 16, 16);
	private final Image IMG_WDOW = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"window.png", 16, 16);
	private final Image IMG_SLAB = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"slab.png", 16, 16);
	
	private OntologyTreeNode invisibleRoot;
	
	private static final String UNKNOWN = "Unknown";	
	private static final String PROJECT = "Project";
	private static final String SITE = "Site";
	private static final String BUILDING = "Building";
	private static final String STOREY = "Storey";
	private static final String ROOM = "Room";
	private static final String FACADE = "Facade";
	private static final String WALL = "Wall";
	private static final String INTERNAL_WALL = "Internal Walls";
	private static final String EXTERNAL_WALL = "External Walls";
	private static final String CLIMATE = "Climate";
	private static final String SPACETYPE = "Occupancy";
	private static final String CONSTRUCTION = "Construction";
	
	
	private ContentMode mode;	
	
	
	public OntologyContentProvider(ContentMode mode)
	{
		this.mode = mode;
		
	}
	
	public Object[] getElements(Object parent)
	{
		
		if(parent instanceof OntologyModel)
		{
			
			OntologyModel ontologyModel = (OntologyModel) parent;			
			invisibleRoot = new OntologyTreeNode("");
			
			
			switch(mode)
			{
				case IFC: 			getElementsByIfc(ontologyModel); break;
				case CONSTRUCTION:	getElementsByConstruction(ontologyModel); break;
				case OCCUPANCY:  	getElementsByOccupancy(ontologyModel); break;
				case CLIMATE:  		getElementsByClimate(ontologyModel); break;
				case NOT_ASSIGNED:  getElementsWithNoAssignments(ontologyModel); break;
				default: break;
			}
			
			
		}
		else
		{
			
			invisibleRoot = new OntologyTreeNode("");
			
			OntologyTreeNode root = new OntologyTreeNode("No Ontology model loaded");
			invisibleRoot.addChild(root);
		}				
			
		return getChildren(invisibleRoot);
	}		
	

	
	private void getElementsWithNoAssignments(OntologyModel ontologyModel) {
		//Aufruf von QueryExecutor
		List<QuerySolution> result = EeBimQueryExecutor.getElementsWithNoAssignments(ontologyModel);
		
		//Ergebnisse durchiterieren und Baum aufbauen	
		
		RDFNode node = null;
		
		String strIfcId = null;
		String strIfcName = null;
		String strIfcType = null;
		
		for(QuerySolution solution : result) {			
			node = solution.get("IfcId");			
			if(!node.isResource()) {
				strIfcId = node.asLiteral().getString();
			} else {
				strIfcId = node.asResource().getURI();
			}		
			node = solution.get("IfcName");	
			if(node != null) {
				if(!node.isResource()) {
					strIfcName = node.asLiteral().getString();
				} else {
					strIfcName = node.asResource().getURI();
				}
			} else {
				strIfcName = "Unknown";
			}
			
			node = solution.get("IfcType");			
			if(!node.isResource()) {
				strIfcType = node.asLiteral().getString();
			} else {
				strIfcType = node.asResource().getURI();
			}
			
			String[] array = strIfcType.split("#Ifc");
			if(array.length == 2) {
				strIfcType = array[1];
			}
			
			OntologyTreeNode nodeType = (OntologyTreeNode) invisibleRoot.getChild(strIfcType);
			OntologyTreeNode nodeIfc = null;
			
			if(nodeType == null){						
				nodeType = new OntologyTreeNode(strIfcType);			
				nodeType.setImage(IMG_FOLDER);
//				nodeType.setImage(getImageByName(strIfcType));
				invisibleRoot.addChild(nodeType);
			}	
			 
			nodeIfc = new OntologyTreeNode("[" + strIfcName + "]", strIfcId);
			nodeIfc.setImage(getImageByName(strIfcType));
			nodeType.addChild(nodeIfc);						 	
		}
	}


	private void getElementsByClimate(OntologyModel ontologyModel) {
		//Aufruf von QueryExecutor
		List<QuerySolution> result = EeBimQueryExecutor.getClimateBased(ontologyModel);
		
		//Ergebnisse durchiterieren und Baum aufbauen	
		
		RDFNode node = null;
		
		String strClimateLocationName = null;
		String strClimateLocationId = null;
		String strIfcId = null;
		String strIfcName = null;
		String strIfcType = null;
		
		for(QuerySolution solution : result)
		{
			node = solution.get("ClimateLocationName");			
			if(!node.isResource()) 
				strClimateLocationName = node.asLiteral().getString();
			else
				strClimateLocationName = node.asResource().getURI();
			
			node = solution.get("ClimateLocationId");			
			if(!node.isResource()) 
				strClimateLocationId = node.asLiteral().getString();
			else
				strClimateLocationId = node.asResource().getURI();
			
			node = solution.get("IfcId");			
			if(!node.isResource()) 
				strIfcId = node.asLiteral().getString();
			else
				strIfcId = node.asResource().getURI();
			
			node = solution.get("IfcName");	
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcName = node.asLiteral().getString();
				else
					strIfcName = node.asResource().getURI();
			}
			else
				strIfcName = "Unknown";
			
			node = solution.get("IfcType");			
			if(!node.isResource()) 
				strIfcType = node.asLiteral().getString();
			else
				strIfcType = node.asResource().getURI();	
			
			
			String[] array = strIfcType.split("#");
			if(array.length == 2)
				strIfcType = array[1];
			
			
			OntologyTreeNode nodeConst = (OntologyTreeNode) invisibleRoot.getChild(strClimateLocationName);
			OntologyTreeNode nodeIfc = null;
			OntologyTreeNode nodeIfcType = null;
			
			if(nodeConst == null)
			{
				nodeConst = new OntologyTreeNode(strClimateLocationName, strClimateLocationId);
				nodeConst.setImage(IMG_CLIMATE);	
				invisibleRoot.addChild(nodeConst);
			}
				
			
			String elemName ="";
			 nodeIfcType = (OntologyTreeNode) nodeConst.getChild(strIfcType);			 
			 if(nodeIfcType == null)
			 {
				 
				String [] tmp = strIfcType.split("Ifc");
				elemName = tmp[tmp.length-1];
				 
				 nodeIfcType = new OntologyTreeNode(strIfcType, "",elemName);
				 nodeIfcType.setImage(IMG_FOLDER);
				 nodeConst.addChild(nodeIfcType);
			 }	 
			 
			 nodeIfc = new OntologyTreeNode("[" + strIfcName + "]", strIfcId);
			 nodeIfc.setTriple( new Triple (Node.createURI(strIfcId),
											EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION.asNode(),
											Node.createURI(strClimateLocationId)));
			 nodeIfc.setImage(getImageByName(strIfcType));
			 nodeIfcType.addChild(nodeIfc);				
						
		}		
	}

	private void getElementsByOccupancy(OntologyModel ontologyModel) {
		//Aufruf von QueryExecutor
		List<QuerySolution> result = EeBimQueryExecutor.getOccupancyBased(ontologyModel);
		
		//Ergebnisse durchiterieren und Baum aufbauen	
		
		RDFNode node = null;
		
		String strSpaceTypeName = null;
		String strSpaceTypeId = null;
		String strIfcId = null;
		String strIfcName = null;
		String strIfcType = null;
		
		for(QuerySolution solution : result)
		{
			node = solution.get("SpaceTypeName");			
			if(!node.isResource()) 
				strSpaceTypeName = node.asLiteral().getString();
			else
				strSpaceTypeName = node.asResource().getURI();
			
			node = solution.get("SpaceTypeId");			
			if(!node.isResource()) 
				strSpaceTypeId = node.asLiteral().getString();
			else
				strSpaceTypeId = node.asResource().getURI();
			
			node = solution.get("IfcId");			
			if(!node.isResource()) 
				strIfcId = node.asLiteral().getString();
			else
				strIfcId = node.asResource().getURI();
			
			node = solution.get("IfcName");			
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcName = node.asLiteral().getString();
				else
					strIfcName = node.asResource().getURI();
			}
			else
				strIfcName = "Unknown";
			
			node = solution.get("IfcType");			
			if(!node.isResource()) 
				strIfcType = node.asLiteral().getString();
			else
				strIfcType = node.asResource().getURI();	
		
			String[] array = strIfcType.split("#");
			if(array.length == 2)
				strIfcType = array[1];
			
			OntologyTreeNode nodeConst = (OntologyTreeNode) invisibleRoot.getChild(strSpaceTypeName);
			OntologyTreeNode nodeIfc = null;
			OntologyTreeNode nodeIfcType = null;
			
			if(nodeConst == null)
			{
				nodeConst = new OntologyTreeNode(strSpaceTypeName, strSpaceTypeId);
				nodeConst.setImage(IMG_OCC);
				invisibleRoot.addChild(nodeConst);
			}	
			
			String elemName = "";
			 nodeIfcType = (OntologyTreeNode) nodeConst.getChild(strIfcType);				 
			 if(nodeIfcType == null)
			 {
				 
					String [] tmp = strIfcType.split("Ifc");
					elemName = tmp[tmp.length-1];
				 
				 
				 nodeIfcType = new OntologyTreeNode(strIfcType, "", elemName);
				 nodeConst.addChild(nodeIfcType);
			 }	 
			
			 nodeIfc = new OntologyTreeNode("[ " + strIfcName +"]", strIfcId);
			 nodeIfc.setTriple( new Triple(Node.createURI(strIfcId),
												EeBIMOntoVocabulary.HAS_SPACE_TYPE.asNode(),
												Node.createURI(strSpaceTypeId)));
			 nodeIfc.setImage(getImageByName(strIfcType));
			 nodeIfcType.addChild(nodeIfc);					
		}		
	}

	private void getElementsByConstruction(OntologyModel ontologyModel) {
		//Aufruf von QueryExecutor
		List<QuerySolution> result = EeBimQueryExecutor.getConstructionBased(ontologyModel);
		
		//Ergebnisse durchiterieren und Baum aufbauen	
		
		RDFNode node = null;
		
		String strConstName = null;
		String strConstId = null;
		String strIfcId = null;
		String strIfcName = null;
		String strIfcType = null;
		
		for(QuerySolution solution : result)
		{
			node = solution.get("ConstName");			
			if(!node.isResource()) 
				strConstName = node.asLiteral().getString();
			else
				strConstName = node.asResource().getURI();
			
			node = solution.get("ConstId");			
			if(!node.isResource()) 
				strConstId = node.asLiteral().getString();
			else
				strConstId = node.asResource().getURI();
			
			node = solution.get("IfcId");			
			if(!node.isResource()) 
				strIfcId = node.asLiteral().getString();
			else
				strIfcId = node.asResource().getURI();
			
			node = solution.get("IfcName");	
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcName = node.asLiteral().getString();
				else
					strIfcName = node.asResource().getURI();
			}
			else
				strIfcName = "Unknown";
			
			node = solution.get("IfcType");			
			if(!node.isResource()) 
				strIfcType = node.asLiteral().getString();
			else
				strIfcType = node.asResource().getURI();	
			
			String[] array = strIfcType.split("#");
			if(array.length == 2)
				strIfcType = array[1];
		
			
			
			OntologyTreeNode nodeConst = (OntologyTreeNode) invisibleRoot.getChild(strConstName);
			OntologyTreeNode nodeIfc = null;
			OntologyTreeNode nodeIfcType = null;
			
			if(nodeConst == null)
			{		
				
				
				nodeConst = new OntologyTreeNode(strConstName, strConstId);
				nodeConst.setImage(IMG_LAYERS);
				invisibleRoot.addChild(nodeConst);
			}	
			
			String elemName ="";
			 nodeIfcType = (OntologyTreeNode) nodeConst.getChild(strIfcType);				 
			 if(nodeIfcType == null)
			 {
				String [] tmp = strIfcType.split("Ifc");
				elemName = tmp[tmp.length-1];
				 
				 
				 nodeIfcType = new OntologyTreeNode(strIfcType,"", elemName);
				 nodeIfcType.setImage(IMG_FOLDER);
				 nodeConst.addChild(nodeIfcType);
			 } 
			 
			nodeIfc = new OntologyTreeNode("[" + strIfcName + "]", strIfcId);
			nodeIfc.setTriple( new Triple(Node.createURI(strIfcId),
											  EeBIMOntoVocabulary.HAS_CONSTRUCTION.asNode(),
											  Node.createURI(strConstId)));
			 nodeIfc.setImage(getImageByName(strIfcType));
			nodeIfcType.addChild(nodeIfc);						 	
		}
	}

	private void getElementsByIfc(OntologyModel ontologyModel) {
		List<QuerySolution> result = EeBimQueryExecutor.getIfcBased(ontologyModel);
		RDFNode node = null;
		
		String strIfcIdSite           = null;
		String strIfcNameProject         = null;                                                                      
		String strIfcIdBuilding          = null;
		String strClimate                = null;
		String strClimateName            = null;
		String strIfcNameBuilding        = null;                                                
		String strIfcIdStorey            = null;
		String strIfcNameStorey          = null;                                                                        
		String strIfcIdSpace             = null;
		String strIfcNameSpace           = null;
		String strSpaceType              = null;
		String strSpaceTypeName          = null;                                                
		String strIfcIdBuildingElement   = null;
		String strIfcTypeBuildingElement = null;
		String strIfcNameBuildingElement = null;
		String strConstruction           = null;
		String strConstructionName       = null;
		
		boolean hasClimate = false;
		boolean hasSpaceType = false;
		boolean hasConstruction = false;
		
		for(QuerySolution solution : result)
		{			
			
			node = solution.get("IfcIdSite");			
			if(!node.isResource()) 
				strIfcIdSite = node.asLiteral().getString();
			else
				strIfcIdSite  = node.asResource().getURI();
				
			node = solution.get("IfcNameSite");	
			if(node != null) //OPTIONAL
			{
				if(!node.isResource()) 
					strIfcNameProject  = node.asLiteral().getString();
				else
					strIfcNameProject  = node.asResource().getURI();	
			}
			else
				strIfcNameProject = UNKNOWN;
				
			node = solution.get("IfcIdBuilding");			
			if(!node.isResource()) 
				strIfcIdBuilding  = node.asLiteral().getString();
			else
				strIfcIdBuilding  = node.asResource().getURI();		
				
			node = solution.get("Climate");	
			if(node != null)
			{
				
				hasClimate = true;
				
				if(!node.isResource()) 
					strClimate  = node.asLiteral().getString();
				else
					strClimate  = node.asResource().getURI();	
				
				node = solution.get("ClimateName");			
				if(!node.isResource()) 
					strClimateName  = node.asLiteral().getString();
				else
					strClimateName  = node.asResource().getURI();				
				
			}
			else
				hasClimate = false;

	
				
			node = solution.get("IfcNameBuilding");		
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcNameBuilding  = node.asLiteral().getString();
				else
					strIfcNameBuilding  = node.asResource().getURI();
			}
			else
				strIfcNameBuilding = UNKNOWN;
				
			node = solution.get("IfcIdStorey");			
			if(!node.isResource()) 
				strIfcIdStorey  = node.asLiteral().getString();
			else
				strIfcIdStorey  = node.asResource().getURI();		
				
			node = solution.get("IfcNameStorey");	
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcNameStorey  = node.asLiteral().getString();
				else
					strIfcNameStorey  = node.asResource().getURI();		
			}
			else
				strIfcNameStorey = UNKNOWN;
				
			node = solution.get("IfcIdSpace");			
			if(!node.isResource()) 
				strIfcIdSpace  = node.asLiteral().getString();
			else
				strIfcIdSpace  = node.asResource().getURI();	

			node = solution.get("IfcNameSpace");
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcNameSpace  = node.asLiteral().getString();
				else
					strIfcNameSpace  = node.asResource().getURI();		
			}
			else
				strIfcNameSpace = UNKNOWN;
				
			node = solution.get("SpaceType");	
			if(node != null)
			{
				hasSpaceType = true;
				
				if(!node.isResource()) 
					strSpaceType  = node.asLiteral().getString();
				else
					strSpaceType  = node.asResource().getURI();		
				
				node = solution.get("SpaceTypeName");			
				if(!node.isResource()) 
					strSpaceTypeName  = node.asLiteral().getString();
				else
					strSpaceTypeName  = node.asResource().getURI();	
				
			}
			else
				hasSpaceType = false;
				

			node = solution.get("IfcIdBuildingElement");			
			if(!node.isResource()) 
				strIfcIdBuildingElement  = node.asLiteral().getString();
			else
				strIfcIdBuildingElement  = node.asResource().getURI();	
				
			node = solution.get("IfcTypeBuildingElement");			
			if(!node.isResource()) 
				strIfcTypeBuildingElement  = node.asLiteral().getString();
			else
				strIfcTypeBuildingElement  = node.asResource().getURI();	
			
			
			String[] array = strIfcTypeBuildingElement.split("#");
			if(array.length == 2)
				strIfcTypeBuildingElement = array[1];
			

			node = solution.get("IfcNameBuildingElement");	
			if(node != null)
			{
				if(!node.isResource()) 
					strIfcNameBuildingElement  = node.asLiteral().getString();
				else
					strIfcNameBuildingElement  = node.asResource().getURI();				
				
			}
			else
				strIfcNameBuildingElement = UNKNOWN;
				
			node = solution.get("Construction");	
			if(node != null)
			{
				hasConstruction = true;
				
				if(!node.isResource()) 
					strConstruction  = node.asLiteral().getString();
				else
					strConstruction  = node.asResource().getURI();	
					
				node = solution.get("ConstructionName");			
				if(!node.isResource()) 
					strConstructionName  = node.asLiteral().getString();
				else
					strConstructionName  = node.asResource().getURI();	
			}
			else
				hasConstruction = false;
			
			
			OntologyTreeNode nodeSite = (OntologyTreeNode) invisibleRoot.getChild(strIfcIdSite);
			if(nodeSite == null)
			{
				nodeSite = new OntologyTreeNode(strIfcNameProject, strIfcIdSite, SITE + " ["+ strIfcNameProject + "]");
				nodeSite.setImage(IMG_SITE);
				invisibleRoot.addChild(nodeSite);
			}
			
				
			OntologyTreeNode nodeBuilding = (OntologyTreeNode) nodeSite.getChild(strIfcIdBuilding);				
			if(nodeBuilding == null)
			{
				nodeBuilding = new OntologyTreeNode(strIfcNameBuilding, strIfcIdBuilding, BUILDING + " ["+ strIfcNameBuilding+ "]");
				nodeBuilding.setImage(IMG_BLDG);
				nodeSite.addChild(nodeBuilding);	
			}	
			
			if(hasClimate)
			{					
					OntologyTreeNode nodeClimate =  (OntologyTreeNode) nodeSite.getChild(strClimateName);	
					if(nodeClimate == null)
					{
						nodeClimate = new OntologyTreeNode(strClimateName, strClimate, CLIMATE + " [" + strClimateName + "]");
						nodeClimate.setTriple( new Triple(Node.createURI(strIfcIdBuilding),
								  						  EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION.asNode(),
								  						  Node.createURI(strClimate)));
						nodeClimate.setImage(IMG_CLIMATE);					
						nodeSite.addChild(nodeClimate);	
					}		
				
			}				
									
				
				
			OntologyTreeNode nodeStorey =  (OntologyTreeNode) nodeBuilding.getChild(strIfcIdStorey);	
			if(nodeStorey == null)
			{
				nodeStorey = new OntologyTreeNode(strIfcNameStorey, strIfcIdStorey,STOREY + " [" + strIfcNameStorey + "]");
				nodeStorey.setImage(IMG_STOREY);
				nodeBuilding.addChild(nodeStorey);
			}
				
			OntologyTreeNode nodeSpace = (OntologyTreeNode) nodeStorey.getChild(strIfcIdSpace);	
			if(nodeSpace == null)
			{
				nodeSpace = new OntologyTreeNode(strIfcNameSpace, strIfcIdSpace, ROOM + " [" + strIfcNameSpace + "]");
				nodeSpace.setImage(IMG_SPACE);
				nodeStorey.addChild(nodeSpace);	
				
			}
			
			if(hasSpaceType)
			{	
					OntologyTreeNode nodeSpaceType = (OntologyTreeNode) nodeSpace.getChild(strSpaceTypeName);
					if(nodeSpaceType == null)
					{
						nodeSpaceType = new OntologyTreeNode(strSpaceTypeName, strSpaceType, SPACETYPE + " [" + strSpaceTypeName + "]");
						nodeSpaceType.setTriple( new Triple(Node.createURI(strIfcIdSpace),
								  						  EeBIMOntoVocabulary.HAS_SPACE_TYPE.asNode(),
								  						  Node.createURI(strSpaceType)));
						nodeSpaceType.setImage(IMG_OCC);	
						nodeSpace.addChild(nodeSpaceType);
					}				
						
			}
					
						
			String elemName ="";			
			OntologyTreeNode nodeType = (OntologyTreeNode) nodeSpace.getChild(strIfcTypeBuildingElement);
			if(nodeType == null)
			{
				
				String [] tmp = strIfcTypeBuildingElement.split("Ifc");
				elemName = tmp[tmp.length-1];				
				
				nodeType = new OntologyTreeNode(strIfcTypeBuildingElement, "", elemName);
				nodeType.setImage(IMG_FOLDER);;
				nodeSpace.addChild(nodeType);
			}	
	

				
		
		
			OntologyTreeNode nodeElem = (OntologyTreeNode) nodeType.getChild(strIfcIdBuildingElement);
			if(nodeElem == null)
			{
				nodeElem= new OntologyTreeNode("[" + strIfcNameBuildingElement + "]", strIfcIdBuildingElement);
				nodeElem.setImage(getImageByName(strIfcTypeBuildingElement));
				nodeType.addChild(nodeElem);
			}
			
			if(hasConstruction)
			{						
				
				OntologyTreeNode nodeConst = (OntologyTreeNode) nodeElem.getChild(strConstructionName);
				if(nodeConst == null)
				{
					nodeConst = new OntologyTreeNode(strConstructionName, strConstruction, CONSTRUCTION + " [" + strConstructionName + "]");
					nodeConst.setTriple( new Triple(Node.createURI(strIfcIdBuildingElement),
							  						  EeBIMOntoVocabulary.HAS_CONSTRUCTION.asNode(),
							  						  Node.createURI(strConstruction)));
					nodeConst.setImage(IMG_LAYERS);	
					nodeElem.addChild(nodeConst);	
				}
				
				
				
			}
	
		}//for
	
	}



	public void inputChanged(Viewer v, Object oldInput, Object newInput)
	{
		
	}
	
	public void dispose()
	{

	}	

	
	public Object getParent(Object child)
	{
		
		if(child instanceof TreeNode)
			return ((TreeNode)child).getParent();			
	
		return null;
	}
	
	public Object [] getChildren(Object parent)
	{

		if (parent instanceof TreeNode) {
			return ((TreeNode)parent).getChildren();
		}
		
		return new Object[0];
	}
	
	public boolean hasChildren(Object parent)
	{

		if (parent instanceof TreeNode)
			return ((TreeNode)parent).hasChildren();			
		return false;
	}
	
	private Image getImageByName(String elemName) {

		
		if(elemName.contains("Site"))
		{
			return IMG_SITE;
		}
		if(elemName.contains("Wall"))
		{
			return IMG_WALL;
		}	
		if(elemName.contains("Slab"))
		{
			return IMG_SLAB;
		}	
		if(elemName.contains("Window"))
		{
			return IMG_WDOW;
		}
		if(elemName.contains("Door"))
		{
			return IMG_DOOR;
		}
		if(elemName.contains("Column"))
		{
			return IMG_COL;
		}
		if(elemName.contains("Space")) {
			return IMG_SPACE;
		}
		return null;
	
	}



}
