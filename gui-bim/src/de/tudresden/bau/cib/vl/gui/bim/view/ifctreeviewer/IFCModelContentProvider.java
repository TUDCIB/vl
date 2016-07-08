package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcproject;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;

public class IFCModelContentProvider extends IfcContentProvider {

	private IFCTreeNode invisibleRoot;
	
	private static final String UNKNOWN = "unknown";	
	private static final String PROJECT = "Project";
	private static final String SITE = "Site";
	private static final String BUILDING = "Building";
	private static final String STOREY = "Storey";
	private static final String ROOM = "Room";
	private static final String FACADE = "Facade";
	private static final String WALL = "Wall";
	private static final String INTERNAL_WALL = "Internal Walls";
	private static final String EXTERNAL_WALL = "External Walls";
	
	
	public IFCModelContentProvider(ContentMode mode) {
		super(mode);
	}
	
	public Object[] getElements(Object parent)
	{
		
		if(parent instanceof Ifc2x3DataModelProxy)
		{
			
			Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) parent;			
			invisibleRoot = new IFCTreeNode("");
			
			
			switch(mode)
			{
				case SPATIALSTRUCTURE: 			getElementsBySpatialStructure(ifcModel); break;
				case SPATIALSTRUCTURE_FACADE:	getElementsBySpatialStructureAndFacade(ifcModel); break;
				case INTERNAL_EXTERNAL_WALLS:  	getElementsByInternalExternalWalls(ifcModel); break;
				case ROOMS:  					getElementsByRooms(ifcModel); break;
				case FACADE: 					getElementsByFacade(ifcModel); break;
				default: break;
			}
			
			
		}
		else
		{
			
			invisibleRoot = new IFCTreeNode("");
			
			IFCTreeNode root = new IFCTreeNode("No IFC model loaded");
			invisibleRoot.addChild(root);
		}	
			
			
			
		return getChildren(invisibleRoot);
	}		
	

	private void getElementsBySpatialStructure(Ifc2x3DataModelProxy ifcModel)
	{
		try 
		{
			
				
			int storeyCount = 1;
			int roomCount = 1;				
			
			
//			// 1. Level = Project	
//			
//			EIfcproject project = ifcModel.getIfcProject();
//			IFCTreeNode treeProject = new IFCTreeNode(PROJECT + " [" + project.getName(project) + "]");
//			treeProject.setIfcElement(project);
//			invisibleRoot.addChild(treeProject);
			
			// 2. Level = Site
			
			EIfcsite site= ifcModel.getSite();
			IFCTreeNode treeSite = null;
			if(site != null)
			{					
				treeSite = new IFCTreeNode(SITE + " [" +  site.getName(site) + "]");
				treeSite.setIfcElement(site);
				invisibleRoot.addChild(treeSite);
				
			
			}
			
			// 3. Level = Buildings
			
			Map<String,EIfcbuilding> buildings=  ifcModel.getBuildings();
			for(EIfcbuilding building : buildings.values()){
				
				IFCTreeNode treeBuilding = new IFCTreeNode(BUILDING + " [" + (building.testName(building) ? building.getName(building) : "no name") + "]");
				treeBuilding.setIfcElement(building);
				if(treeSite != null)
					treeSite.addChild(treeBuilding);
				else
					invisibleRoot.addChild(treeBuilding);
				
				
				storeyCount = 1;
			
				// 4.1. Level = Storeys
			
				EIfcbuildingstorey[] storeys =ifcModel.getBuildingStoreySort(building);
				for(EIfcbuildingstorey storey : storeys) {
					String name = UNKNOWN;
					if(storey.testName(storey)) {
						name = storey.getName(storey);
					}
						
					IFCTreeNode treeStoreyElement = new IFCTreeNode(STOREY + " [" + name + "]");
					treeStoreyElement.setIfcElement(storey);
					treeBuilding.addChild(treeStoreyElement);
					
					roomCount = 1;
						
					// 5.1 Level = Room (Space)
					
					//List<EIfcspace> spacesInStorey = ((IFCController)controller).getSpacesInStorey(storey);
					List<EIfcspace> spacesInStorey = Arrays.asList(ifcModel.getSpacesInStorey(storey));
					for(EIfcspace space : spacesInStorey) {
						String spaceName = UNKNOWN;
						if(space.testLongname(space)) {
							spaceName = space.getLongname(space);
						}
						
						IFCTreeNode treeSpaceElement = new IFCTreeNode(ROOM + " " + storeyCount + "." + roomCount + " [" + spaceName + "]");
						treeStoreyElement.addChild(treeSpaceElement);
						treeSpaceElement.setIfcElement(space);									
						
						
						Set<EIfcelement> elementsBoundToSpace = ifcModel.getBoundingElementsOfSpace(space);							
						for(EIfcelement elem : elementsBoundToSpace) {
							String elemName = UNKNOWN;
							if(elem.testName(elem)) {
								elemName = elem.getName(elem);									
							}														
								
							String [] className = elem.getClass().toString().split("\\.");
							String [] tmp = className[className.length -1].split("Ifc");
							String n = tmp[tmp.length-1];
							
							IFCTreeNode tl = (IFCTreeNode) treeSpaceElement.getChild(n);
							if(tl != null)
							{
								
								int help = tl.getChildCount() +1;
								
								IFCTreeNode treeElement = new IFCTreeNode(n + " " + roomCount + "." + help + " [" +elemName + "]");								
								treeElement.setIfcElement(elem);
								tl.addChild(treeElement);
								
							}
							else
							{										
								
								IFCTreeNode node = new IFCTreeNode(n);
								IFCTreeNode treeElement = new IFCTreeNode(n + " " + roomCount + ".1 " + " [" +elemName + "]");
								node.addChild(treeElement);
								treeSpaceElement.addChild(node);
								treeElement.setIfcElement(elem);
							}	
							
						}//Elements	
						
						roomCount++;
						
					} //Room
					
					storeyCount++;
					
				}//Storey				
				
			}//Building		
		
		} 
		catch(SdaiException e)
		{
			e.printStackTrace();
		}
		catch (IfcException e)
		{
			e.printStackTrace();
		} 
	

	}
	
	private void getElementsBySpatialStructureAndFacade(Ifc2x3DataModelProxy ifcModel)
	{
		try 
		{
			
				
			int storeyCount = 1;
			int roomCount = 1;				
			
			
			// 1. Level = Project	
			
			EIfcproject project = ifcModel.getIfcProject();
			IFCTreeNode treeProject = new IFCTreeNode(PROJECT + " [" + project.getName(project) + "]");
			treeProject.setIfcElement(project);
			invisibleRoot.addChild(treeProject);
			
			// 2. Level = Site
			
			EIfcsite site= ifcModel.getSite();
			IFCTreeNode treeSite = null;
			if(site != null)
			{					
				treeSite = new IFCTreeNode(SITE + " [" +  site.getName(site) + "]");
				treeSite.setIfcElement(site);
				treeProject.addChild(treeSite);
				
			
			}
			
			// 3. Level = Buildings
			
			Map<String,EIfcbuilding> buildings=  ifcModel.getBuildings();
			for(EIfcbuilding building : buildings.values()){
				
				IFCTreeNode treeBuilding = new IFCTreeNode(BUILDING + " [" + building.getName(building) + "]");
				treeBuilding.setIfcElement(building);
				if(treeSite != null)
					treeSite.addChild(treeBuilding);
				else
					treeProject.addChild(treeBuilding);
				
				
				storeyCount = 1;
			
				// 4.1. Level = Storeys
			
				EIfcbuildingstorey[] storeys =ifcModel.getBuildingStoreySort(building);
				for(EIfcbuildingstorey storey : storeys) {
					String name = UNKNOWN;
					if(storey.testName(storey)) {
						name = storey.getName(storey);
					}
						
					IFCTreeNode treeStoreyElement = new IFCTreeNode(STOREY + " [" + name + "]");
					treeStoreyElement.setIfcElement(storey);
					treeBuilding.addChild(treeStoreyElement);
					
					roomCount = 1;
						
					// 5.1 Level = Room (Space)
					
					//List<EIfcspace> spacesInStorey = ((IFCController)controller).getSpacesInStorey(storey);
					List<EIfcspace> spacesInStorey = Arrays.asList(ifcModel.getSpacesInStorey(storey));
					for(EIfcspace space : spacesInStorey) {
						String spaceName = UNKNOWN;
						if(space.testLongname(space)) {
							spaceName = space.getLongname(space);
						}
						
						IFCTreeNode treeSpaceElement = new IFCTreeNode(ROOM + " " + storeyCount + "." + roomCount + " [" + spaceName + "]");
						treeStoreyElement.addChild(treeSpaceElement);
						treeSpaceElement.setIfcElement(space);									
						
						
						Set<EIfcelement> elementsBoundToSpace = ifcModel.getBoundingElementsOfSpace(space);							
						for(EIfcelement elem : elementsBoundToSpace) {
							String elemName = UNKNOWN;
							if(elem.testName(elem)) {
								elemName = elem.getName(elem);									
							}														
								
							String [] className = elem.getClass().toString().split("\\.");
							String [] tmp = className[className.length -1].split("Ifc");
							String n = tmp[tmp.length-1];
							
							IFCTreeNode tl = (IFCTreeNode) treeSpaceElement.getChild(n);
							if(tl != null)
							{
								
								int help = tl.getChildCount() +1;
								
								IFCTreeNode treeElement = new IFCTreeNode(n + " " + roomCount + "." + help + " [" +elemName + "]");								
								treeElement.setIfcElement(elem);
								tl.addChild(treeElement);
								
							}
							else
							{										
								
								IFCTreeNode node = new IFCTreeNode(n);
								IFCTreeNode treeElement = new IFCTreeNode(n + " " + roomCount + ".1 " + " [" +elemName + "]");
								node.addChild(treeElement);
								treeSpaceElement.addChild(node);
								treeElement.setIfcElement(elem);
							}	
							
						}//Elements	
						
						roomCount++;
						
					} //Room
					
					storeyCount++;
					
				}//Storey
				
				//4.2 Level = Facade
				IFCTreeNode treeFacade = new IFCTreeNode(FACADE);						
										
				
				Set<EIfcelement> outerElements = ifcModel.getElementsInBuilding(building.getGlobalid(building));
				for(EIfcelement outerElement: outerElements){
					
					if(!ifcModel.isFacadeElement(outerElement))
						continue;
					
					String elemName = UNKNOWN;
					if(outerElement.testName(outerElement)) {
						elemName = outerElement.getName(outerElement);									
					}														
						
					String [] className = outerElement.getClass().toString().split("\\.");
					String [] tmp = className[className.length -1].split("Ifc");
					String n = tmp[tmp.length-1];
					
					IFCTreeNode tl = (IFCTreeNode) treeFacade.getChild(n);
					if(tl != null)
					{
						int help = tl.getChildCount() +1;
						
						IFCTreeNode treeElement = new IFCTreeNode(n + " 1." + help + " [" +elemName + "]");								
						treeElement.setIfcElement(outerElement);
						tl.addChild(treeElement);
						
					}
					else
					{
						IFCTreeNode node = new IFCTreeNode(n);
						IFCTreeNode treeElement = new IFCTreeNode(n + " 1.1" + " [" +elemName + "]");
						node.addChild(treeElement);
						treeFacade.addChild(node);
						treeElement.setIfcElement(outerElement);
					}							
				}		
				
				if(treeFacade.hasChildren())
					treeBuilding.addChild(treeFacade);
				
				
			}//Building		
		
		} 
		catch(SdaiException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IfcException e)
		{
			e.printStackTrace();
		} 
	

	}

	
	private void getElementsByFacade(Ifc2x3DataModelProxy ifcModel)
	{
		try 
		{			
			
			// 3. Level = Buildings
			
			Map<String,EIfcbuilding> buildings=  ifcModel.getBuildings();
			for(EIfcbuilding building : buildings.values()){
				
				IFCTreeNode treeBuilding = new IFCTreeNode(BUILDING + " [" + building.getName(building) + "]");
				treeBuilding.setIfcElement(building);				
				invisibleRoot.addChild(treeBuilding);
				
			
		
				
				//4.2 Level = Facade
				IFCTreeNode treeFacade = new IFCTreeNode(FACADE);						
										
				
				Set<EIfcelement> outerElements = ifcModel.getElementsInBuilding(building.getGlobalid(building));
				for(EIfcelement outerElement: outerElements){
					
					if(!ifcModel.isFacadeElement(outerElement))
						continue;
					
					String elemName = UNKNOWN;
					if(outerElement.testName(outerElement)) {
						elemName = outerElement.getName(outerElement);									
					}														
						
					String [] className = outerElement.getClass().toString().split("\\.");
					String [] tmp = className[className.length -1].split("Ifc");
					String n = tmp[tmp.length-1];
					
					IFCTreeNode tl = (IFCTreeNode) treeFacade.getChild(n);
					if(tl != null)
					{
						int help = tl.getChildCount() +1;
						
						IFCTreeNode treeElement = new IFCTreeNode(n + " 1." + help + " [" +elemName + "]");								
						treeElement.setIfcElement(outerElement);
						tl.addChild(treeElement);
						
					}
					else
					{
						IFCTreeNode node = new IFCTreeNode(n);
						IFCTreeNode treeElement = new IFCTreeNode(n + " 1.1" + " [" +elemName + "]");
						node.addChild(treeElement);
						treeFacade.addChild(node);
						treeElement.setIfcElement(outerElement);
					}							
				}		
				
				if(treeFacade.hasChildren())
					treeBuilding.addChild(treeFacade);
				
				
			}//Building		
		
		} 
		catch(SdaiException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IfcException e)
		{
			e.printStackTrace();
		} 
	

	}
	
	private void getElementsByInternalExternalWalls(Ifc2x3DataModelProxy ifcModel)
	{
		try 
		{				
			
			int storeyCount = 1;
			int wallCount = 1;			
		
			
			// Buildings			
			Map<String,EIfcbuilding> buildings=  ifcModel.getBuildings();
			for(EIfcbuilding building : buildings.values()){
				
				IFCTreeNode treeBuilding = new IFCTreeNode(BUILDING + " [" + building.getName(building) + "]");
				treeBuilding.setIfcElement(building);				
				invisibleRoot.addChild(treeBuilding);
			
				
				
				storeyCount = 1;
			
				// Storeys			
				EIfcbuildingstorey[] storeys =ifcModel.getBuildingStoreySort(building);
				for(EIfcbuildingstorey storey : storeys) {
					String name = UNKNOWN;
					if(storey.testName(storey)) {
						name = storey.getName(storey);
					}
					
					
					IFCTreeNode treeStoreyElement = new IFCTreeNode(STOREY + " [" + name + "]");
					treeStoreyElement.setIfcElement(storey);
					treeBuilding.addChild(treeStoreyElement);
					
					
					IFCTreeNode internalWalls = new IFCTreeNode(INTERNAL_WALL);
					IFCTreeNode externalWalls = new IFCTreeNode(EXTERNAL_WALL);
					
					wallCount=1;
					
					Set<EIfcelement> elements = ifcModel.getElementsInStorey(storey);
					for(EIfcelement elem : elements)
					{
						if(elem instanceof EIfcwall)
						{
							
						
							
							IFCTreeNode treeWall = new IFCTreeNode(WALL + " " + storeyCount + "." + wallCount +" [" + elem.getName(elem) + "]");
							treeWall.setIfcElement(elem);
							
							if(ifcModel.isFacadeElement(elem))
							{
								externalWalls.addChild(treeWall);
							}
							else
							{
								internalWalls.addChild(treeWall);
							}
						}
						
						wallCount++;
					}
					
					treeStoreyElement.addChild(internalWalls);
					treeStoreyElement.addChild(externalWalls);			
					
					
					storeyCount++;
				}
				
				
			}
		
		} 
		catch(SdaiException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IfcException e)
		{
			e.printStackTrace();
		}
	}
	
	private void getElementsByRooms(Ifc2x3DataModelProxy ifcModel)
	{
		try 
		{				
			
			int storeyCount = 1;
			int roomCount = 1;			
		
			
			// Buildings			
			Map<String,EIfcbuilding> buildings=  ifcModel.getBuildings();
			for(EIfcbuilding building : buildings.values()){
				
				IFCTreeNode treeBuilding = new IFCTreeNode(BUILDING + " [" + building.getName(building) + "]");
				treeBuilding.setIfcElement(building);				
				invisibleRoot.addChild(treeBuilding);
			
				
				
				storeyCount = 1;
			
				// Storeys			
				EIfcbuildingstorey[] storeys =ifcModel.getBuildingStoreySort(building);
				for(EIfcbuildingstorey storey : storeys) {
					String name = UNKNOWN;
					if(storey.testName(storey)) {
						name = storey.getName(storey);
					}
						
					IFCTreeNode treeStoreyElement = new IFCTreeNode(STOREY + " [" + name + "]");
					treeStoreyElement.setIfcElement(storey);
					treeBuilding.addChild(treeStoreyElement);
					
					roomCount = 1;
						
					//Room (Space)					
					List<EIfcspace> spacesInStorey = Arrays.asList(ifcModel.getSpacesInStorey(storey));
					for(EIfcspace space : spacesInStorey) {
						String spaceName = UNKNOWN;
						if(space.testLongname(space)) {
							spaceName = space.getLongname(space);
						}
						
						IFCTreeNode treeSpaceElement = new IFCTreeNode(ROOM + " " + storeyCount + "." + roomCount + " [" + spaceName + "]");
						treeStoreyElement.addChild(treeSpaceElement);
						treeSpaceElement.setIfcElement(space);		
						
						
						roomCount++;
					}
					
					storeyCount++;
				}
				
				
			}
		
		} 
		catch(SdaiException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IfcException e)
		{
			e.printStackTrace();
		}
	}

}
