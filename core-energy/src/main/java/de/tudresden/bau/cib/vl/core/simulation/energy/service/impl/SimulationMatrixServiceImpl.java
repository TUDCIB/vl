package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.SIfc2x3.EIfczone;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.simmatrix.DistanceUnit;
import de.tudresden.bau.cib.simmatrix.DocumentRoot;
import de.tudresden.bau.cib.simmatrix.FileFormat;
import de.tudresden.bau.cib.simmatrix.MaterialUnit;
import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElementGroup;
import de.tudresden.bau.cib.simmatrix.TElements;
import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TLayer;
import de.tudresden.bau.cib.simmatrix.TOccupancyType;
import de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TSpaceGroup;
import de.tudresden.bau.cib.simmatrix.TSpaces;
import de.tudresden.bau.cib.simmatrix.TTarget;
import de.tudresden.bau.cib.simmatrix.TTargetList;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TUsage;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.TargetType;
import de.tudresden.bau.cib.simmatrix.TimePeriod;
import de.tudresden.bau.cib.simmatrix.simmatrixFactory;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;
import de.tudresden.bau.cib.simmatrix.util.simmatrixResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SimulationMatrixService;
import de.tudresden.bau.cib.vl.core.util.Pair;


public class SimulationMatrixServiceImpl implements SimulationMatrixService {
	
	private static simmatrixFactory factory = simmatrixFactory.eINSTANCE;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(SimulationMatrixServiceImpl.class);
	
	
	private int createIntegerUniqueIdentifier() {
		Random r = new Random();
		int id = r.nextInt();
		while(id < 0) id = r.nextInt();
		return id;
	}	

	@Override
	public TSimulationMatrix createMatrix(String id) {					
		TSimulationMatrix matrix = factory.createTSimulationMatrix();
		matrix.setId(id);
		matrix.setType(Type.DesignAlternatives.getName());	
		// create needed sections
		// Variables
		getOrCreateVariablesSection(matrix);
			
		return matrix;
	}
	
	private ResourceSet initResourceSet() {
		// Create a resource set to hold the resources.
		ResourceSet resourceSet = new ResourceSetImpl();
		
		// Register the appropriate resource factory to handle all file extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
			 new simmatrixResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put
			(simmatrixPackage.eNS_URI, 
			 simmatrixPackage.eINSTANCE);
		return resourceSet;
	}
	
	@Override
	public TSimulationMatrix loadMatrix(URL url) throws URISyntaxException {
		ResourceSet resourceSet = initResourceSet();
		URI uri = URI.createURI(url.toURI().toString());

		Resource resource = resourceSet.getResource(uri, true);
		LOG.debug("Loaded simulation matrix from URL: {}", new Object[]{uri});
		DocumentRoot doc = (DocumentRoot) resource.getContents().get(0);
		return doc.getSimulationMatrix();
	}
	
	@Override
	public TSimulationMatrix loadMatrix(String filePath) {
		ResourceSet resourceSet = initResourceSet();
		URI uri = URI.createFileURI(filePath);

		Resource resource = resourceSet.getResource(uri, true);
		LOG.debug("Loaded simulation matrix from path: {}", new Object[]{uri});
		DocumentRoot doc = (DocumentRoot) resource.getContents().get(0);
		return doc.getSimulationMatrix();
	}

	@Override
	public void addTargets(Collection<? extends EIfcspatialstructureelement> ifcEntities, 
			TSimulationMatrix matrix) throws SimulationMatrixException {
		try {
			// Targets
			TTargets targets = matrix.getTargets();
			if(targets == null) targets = factory.createTTargets();
			
			//targetlist 1
			TTargetList targetList1 = factory.createTTargetList();
			targetList1.setId(""+createIntegerUniqueIdentifier());
			for(EIfcspatialstructureelement element : ifcEntities){
				String guid = element.getGlobalid(element);
				TTarget target = factory.createTTarget();
				target.setValue(guid);			
				if(element instanceof EIfcspace) {
					target.setType(TargetType.SPACE);
				} else if(element instanceof EIfcbuildingstorey) {
					target.setType(TargetType.STOREY);
				} else if(element instanceof EIfcbuilding) {
					target.setType(TargetType.BUILDING);
				} else if(element instanceof EIfczone) {
					target.setType(TargetType.ZONE);
				}
				targetList1.getTarget().add(target);
			}
			targets.getTargetList().add(targetList1);
			// set targets
			matrix.setTargets(targets);
		} catch (SdaiException e) {
			throw new SimulationMatrixException(e);
		}
	}
	
	
	
	private TConstructionType getOrCreateConstructionType(String uri, EList<TConstructionType> constructionTypeList) {
		for(int i = 0; i < constructionTypeList.size(); i++) {
			TConstructionType constructionType = constructionTypeList.get(i);
			String cmpUri = constructionType.getSource();
			if(uri.equals(cmpUri)) return constructionType;
		}
		// no construction type found => create new entry
		TConstructionType constructionType = factory.createTConstructionType();
		constructionType.setSource(uri);
		constructionTypeList.add(constructionType);
		return constructionType;
	}
	
	private DistanceUnit getDistanceUnitOfMaterial(MaterialLayer layer) {
		String unit = layer.getUnit();
		if(unit.equalsIgnoreCase("m")) return DistanceUnit.M;
		if(unit.equalsIgnoreCase("cm")) return DistanceUnit.CM;
		if(unit.equalsIgnoreCase("mm")) return DistanceUnit.MM;
		return null;
	}
	
	private TConstructionTypes getOrCreateConstructionTypeVariables(TSimulationMatrix matrix) {
		TVariables variables = getOrCreateVariablesSection(matrix);
		TConstructionTypes constructionTypes = variables.getConstructionTypeVariables();
		if(constructionTypes == null) {
			constructionTypes = factory.createTConstructionTypes();
			variables.setConstructionTypeVariables(constructionTypes);
		}
		return constructionTypes;
	}
	
	private TWeather getOrCreateWeatherVariables(TSimulationMatrix matrix) {
		TVariables variables = getOrCreateVariablesSection(matrix);
		
		TWeather weatherVariables = variables.getWeatherVariables();
		if(weatherVariables == null) {
			weatherVariables = factory.createTWeather();
			variables.setWeatherVariables(weatherVariables);
		}
		return weatherVariables;
	}

	@Override
	public String addConstructionTypeVariant(ConstructionTemplate constructionResource, TSimulationMatrix matrix) throws SimulationMatrixException {
		TConstructionTypes constructionTypes = getOrCreateConstructionTypeVariables(matrix);
		
		EList<TConstructionType> constructionTypeList = constructionTypes.getConstructionType();
		
		// first search for already existing construction type
		TConstructionType constructionType = getOrCreateConstructionType(constructionResource.getSourceFileUri(), constructionTypeList);
		
		// always create new variants (even if the variant is the same)
		TConstructionTypeVariant constructionTypeVariant = factory.createTConstructionTypeVariant();
		constructionTypeVariant.setId(""+createIntegerUniqueIdentifier());
		for (Map.Entry<Integer, MaterialLayer> entry : constructionResource.getMaterialLayers().entrySet()) {
			MaterialLayer matlayer = entry.getValue();

			TLayer layer = factory.createTLayer();
			layer.setUnit(getDistanceUnitOfMaterial(matlayer));
			layer.setThickness(round(matlayer.getThickness(), 3));
			layer.setValue(matlayer.getTemplate().getSourceFileUri());
			constructionTypeVariant.getLayer().add(layer);
		}
		constructionType.getConstructionTypeVariant().add(constructionTypeVariant);
		constructionTypeList.add(constructionType);
		return constructionTypeVariant.getId();
	}
	
	@Override
	public String addConstructionTypeVariant(String constructionUri, Map<Integer, MaterialLayer> layers, TSimulationMatrix matrix) {
		TConstructionTypes constructionTypes = getOrCreateConstructionTypeVariables(matrix);
		TConstructionType constructionType = getOrCreateConstructionType(constructionUri, constructionTypes.getConstructionType());
		EList<TConstructionTypeVariant> constructionTypeVariantList = constructionType.getConstructionTypeVariant();
		TConstructionTypeVariant variant = factory.createTConstructionTypeVariant();
		variant.setId(""+createIntegerUniqueIdentifier());
		EList<TLayer> matrixLayers = variant.getLayer();
		for (Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
			MaterialLayer layer = entry.getValue();

			MaterialTemplate material = layer.getTemplate();
			
			TLayer matrixLayer = factory.createTLayer();
			matrixLayer.setThickness(round(layer.getThickness(), 3));
			matrixLayer.setUnit(DistanceUnit.M);
			matrixLayer.setValue(material.getSourceFileUri());
			
			matrixLayers.add(matrixLayer);
		}
		constructionTypeVariantList.add(variant);
		return variant.getId();
	}
	
	private double round(double value, int position) {
		double conversionNumber = Math.pow(10, position);
		double newValue = value * (conversionNumber);
		double convertedVal = Math.round(newValue)/conversionNumber;
		return convertedVal;
	}
	
	@Override
	public String addWeatherDataSetVariant(String uri, TSimulationMatrix matrix) {
		TWeather weatherVariables = getOrCreateWeatherVariables(matrix);
		TWeatherDataSetVariant variant = factory.createTWeatherDataSetVariant();
		variant.setId(""+createIntegerUniqueIdentifier());
		variant.setValue(uri);
		
		weatherVariables.getWeatherDataSetVariant().add(variant);
		return variant.getId();
	}
	
	@Override
	public void addWindowType(TWindowType windowType, TSimulationMatrix matrix) {
		TWindowTypes windowTypes = getOrCreateWindowTypes(matrix);

		
		EList<TWindowType> windowTypeList = windowTypes.getWindowType();
		TWindowType sameWindowType = null;
		for(TWindowType wt : windowTypeList) {
			if(wt.getName().equalsIgnoreCase(windowType.getName())) { 
				sameWindowType = wt;
				break;
			}	
		}
		if(sameWindowType == null) { // window type doesn't already exists
			windowTypeList.add(windowType);
		} else { // add the variants to the already existing WindowType
			sameWindowType.getWindowTypeVariant().addAll(windowType.getWindowTypeVariant());
		}
	}
	
	private TWindowTypes getOrCreateWindowTypes(TSimulationMatrix matrix) {
		TVariables variables = getOrCreateVariablesSection(matrix);
		TWindowTypes windowTypes = variables.getWindowTypeVariables();
		if(windowTypes == null) {
			windowTypes = factory.createTWindowTypes();
			variables.setWindowTypeVariables(windowTypes);
		}
		return windowTypes;
	}
	
	/**
	 * Creates the variables section if no one is in the matrix or returns the existing one.
	 * @param matrix
	 * @return Variables section.
	 */
	private TVariables getOrCreateVariablesSection(TSimulationMatrix matrix) {
		TVariables variables = matrix.getVariables();
		if(variables == null) {
			variables = factory.createTVariables();
			matrix.setVariables(variables);
		}
		return variables;
	}
	
	@Override
	public String addWindowTypeVariant(String windowTypeName,
			TWindowTypeVariant variant, TSimulationMatrix matrix) {
		TWindowType windowType = getOrCreateWindowType(windowTypeName, matrix);
		windowType.getWindowTypeVariant().add(variant);
		return variant.getId();
	}
	
	@Override
	public String addWindowTypeVariant(String windowTypeName,
			String glassFraction, String glassFractionUnit,
			String frameFraction, String frameFractionUnit,
			String thermalTransmittance, String thermalTransmittanceUnit,
			String solarHeatGain, String solarHeatGainUnit,
			String shadingFactor, String shadingFactorUnit,
			TSimulationMatrix matrix) {
		TWindowTypeVariant windowTypeVariant = factory.createTWindowTypeVariant();
		windowTypeVariant.setId(""+createIntegerUniqueIdentifier());

		// glass fraction
		if(StringUtils.isNotEmpty(glassFraction) && StringUtils.isNotEmpty(glassFractionUnit)) {
			TFloatWithUnits glassFractionType = factory.createTFloatWithUnits();
			glassFractionType.setUnit(MaterialUnit.getByLiteral(glassFractionUnit));
			double val = round(Double.parseDouble(glassFraction), 3);
			glassFractionType.setValue(val);
			windowTypeVariant.setGlassFraction(glassFractionType);
		}
		// frame fraction
		if(StringUtils.isNotEmpty(frameFraction) && StringUtils.isNotEmpty(frameFractionUnit)) {
			TFloatWithUnits frameFractionType = factory.createTFloatWithUnits();
			frameFractionType.setUnit(MaterialUnit.getByLiteral(frameFractionUnit));
			double val = round(Double.parseDouble(frameFraction), 3);
			frameFractionType.setValue(val);
			windowTypeVariant.setFrameFraction(frameFractionType);	
		}

		// thermal transmittance
		if(StringUtils.isNotEmpty(thermalTransmittance) && StringUtils.isNotEmpty(thermalTransmittanceUnit)) {
			TFloatWithUnits thermalTransmittanceType = factory.createTFloatWithUnits();
			thermalTransmittanceType.setUnit(MaterialUnit.getByLiteral(thermalTransmittanceUnit));
			double val = round(Double.parseDouble(thermalTransmittance), 3);
			thermalTransmittanceType.setValue(val);
			windowTypeVariant.setThermalTransmittance(thermalTransmittanceType);
		}

		// solar heat gain coefficient
		if(StringUtils.isNotEmpty(solarHeatGain) && StringUtils.isNotEmpty(solarHeatGainUnit)) {
			TFloatWithUnits solarHeatType = factory.createTFloatWithUnits();
			solarHeatType.setUnit(MaterialUnit.getByLiteral(solarHeatGainUnit));
			double val = round(Double.parseDouble(solarHeatGain), 3);
			solarHeatType.setValue(val);
			windowTypeVariant.setSolarHeatGainCoefficient(solarHeatType);
		}

		// shading factor
		if(StringUtils.isNotEmpty(shadingFactor) && StringUtils.isNotEmpty(shadingFactorUnit)) {
			TFloatWithUnits shadingFactorType = factory.createTFloatWithUnits();
			shadingFactorType.setUnit(MaterialUnit.getByLiteral(shadingFactorUnit));
			double val = round(Double.parseDouble(shadingFactor), 3);
			shadingFactorType.setValue(val);
			windowTypeVariant.setShadingFactor(shadingFactorType);
		}
		return addWindowTypeVariant(windowTypeName, windowTypeVariant, matrix);
	}
	
	private TWindowType getOrCreateWindowType(String name, TSimulationMatrix matrix) {
		TWindowTypes windowTypes = getOrCreateWindowTypes(matrix);
		EList<TWindowType> windowTypeList = windowTypes.getWindowType();
		for(TWindowType windowType : windowTypeList) {
			if(windowType.getName().equalsIgnoreCase(name)) return windowType;
		}
		// create window type
		TWindowType windowType = factory.createTWindowType();
		windowType.setName(name);
		addWindowType(windowType, matrix);
		return windowType;
	}

	@Override
	public String createAssignmentGroup(Set<String> elementIds,
			AssignmentGroup group, TSimulationMatrix matrix)
			throws SimulationMatrixException {
		String groupId = null;
		TAssignmentGroups assignmentGroups = matrix.getAssignmentGroups();
		if(assignmentGroups == null) {
			assignmentGroups = factory.createTAssignmentGroups();
			matrix.setAssignmentGroups(assignmentGroups);
		}		
		switch(group) {
		case SpaceGroup:
			TSpaces spaces = assignmentGroups.getSpaces();
			if(spaces == null) {
				spaces = factory.createTSpaces();
				assignmentGroups.setSpaces(spaces);
			}
			EList<TSpaceGroup> spaceGroups = spaces.getSpaceGroup();
			// always create a new group even if such a group already exists
			TSpaceGroup spaceGroup = factory.createTSpaceGroup();
			groupId = ""+createIntegerUniqueIdentifier();
			spaceGroup.setId(groupId);
			for(String elementId : elementIds) {
				if(StringUtils.isNotEmpty(elementId)) {
					spaceGroup.getSpace().add(elementId);
				}
			}
			spaceGroups.add(spaceGroup);
			break;
		case ElementGroup:
			// search <Elements>
			TElements elements = assignmentGroups.getElements();
			if(elements == null) {
				elements = factory.createTElements();
				assignmentGroups.setElements(elements);
			}
			EList<TElementGroup> elementGroups = elements.getElementGroup();
			// create always a new group even if such a group already exists
			// <ElementGroup>
			TElementGroup elementGroup = factory.createTElementGroup();
			groupId = ""+createIntegerUniqueIdentifier();
			elementGroup.setId(groupId);
			for(String elementId : elementIds) {
				if(StringUtils.isNotEmpty(elementId)) {
					elementGroup.getElement().add(elementId);
				}
			}
			elementGroups.add(elementGroup);
			break;
		default: throw new IllegalArgumentException("Please specify an assignment group");
		}
		return groupId;
	}

	@Override
	public void addCombinations(String varRefId, String keyRefId, TSimulationMatrix matrix) throws SimulationMatrixException {
		TCombinations combinations = matrix.getCombinations();
		if(combinations == null) {
			combinations = factory.createTCombinations();
			matrix.setCombinations(combinations);
		}
		TCombination combination = factory.createTCombination();
		combination.setId(""+createIntegerUniqueIdentifier());
		TVariant variant = factory.createTVariant();
		variant.setVREF(varRefId); // required
		if(keyRefId != null) variant.setAREF(keyRefId); // optional
		EList<TVariant> variantList = combination.getVariant();
		variantList.add(variant);
		combinations.getCombination().add(combination);
	}
	
	@Override
	public void removeCombinationId(String combinationId, TSimulationMatrix matrix) {
		TCombinations combinations = matrix.getCombinations();
		EList<TCombination> combinationList = combinations.getCombination();
		for(TCombination combination : combinationList) {
			if(combinationId.equalsIgnoreCase(combination.getId())) {
				combinationList.remove(combination);
			}
		}
	}
	
	
	@Override
	public void removeCombinationIds(Collection<String> combinationIds, TSimulationMatrix matrix) {
		TCombinations combinations = matrix.getCombinations();
		EList<TCombination> combinationList = combinations.getCombination();
		
		TCombination combination = null;
		Iterator<TCombination> iter = combinationList.iterator();
		while(iter.hasNext())
		{
			combination = iter.next();
			if(combinationIds.contains(combination.getId())) 
				iter.remove();
		}
	
	}
	
	public void removeVariantFromCombination(String variationId, String ifcElementId, TSimulationMatrix matrix)
	{	
				
		TCombinations combinations = matrix.getCombinations();
		EList<TCombination> combinationList = combinations.getCombination();
		TCombination combination = null;
		Iterator<TCombination> iterComb = combinationList.iterator();
		while(iterComb.hasNext())
		{							
			
			combination = iterComb.next();		
			
			EList<TVariant> variantList = combination.getVariant();
			TVariant variant = null;
			Iterator<TVariant> iterVar = variantList.iterator();
			while(iterVar.hasNext())
			{						
				variant = iterVar.next();
				
				if(variant.getVREF().equalsIgnoreCase(variationId))
				{
					if(variant.getAREF() != null && !variant.getAREF().isEmpty())
					{						
						
						if(ifcElementId == null || ifcElementId.isEmpty())
						{
							iterVar.remove();
						}
						else
						{
							EList<TElementGroup> groups = matrix.getAssignmentGroups().getElements().getElementGroup();
							
							TElementGroup group = null;
							Iterator<TElementGroup> iterElemGr = groups.iterator();
							while( iterElemGr.hasNext())
							{
								group = iterElemGr.next();
								
								if(group.getId().equalsIgnoreCase(variant.getAREF()))	
								{
									
										iterVar.remove();
									
								}							
							}	
						}						
					}
				}	
				
			}
			
			if(variantList.isEmpty())
			{
				iterComb.remove();
			}
		}

	}
	
	
//	public void removeVariantFromCombination(String variationId, String ifcElementId, TSimulationMatrix matrix)
//	{	
//				
//		TCombinations combinations = matrix.getCombinations();
//		EList<TCombination> combinationList = combinations.getCombination();
//		TCombination combination = null;
//		Iterator<TCombination> iterComb = combinationList.iterator();
//		while(iterComb.hasNext())
//		{							
//			
//			combination = iterComb.next();		
//			
//			EList<TVariant> variantList = combination.getVariant();
//			TVariant variant = null;
//			Iterator<TVariant> iterVar = variantList.iterator();
//			while(iterVar.hasNext())
//			{						
//				variant = iterVar.next();
//				
//				if(variant.getVREF().equalsIgnoreCase(variationId))
//				{
//					if(variant.getAREF() != null && !variant.getAREF().isEmpty())
//					{						
//						
//						if(ifcElementId == null || ifcElementId.isEmpty())
//						{
//							variantList.remove(variant);
//						}
//						else
//						{
//							EList<TElementGroup> groups = matrix.getAssignmentGroups().getElements().getElementGroup();
//							
//							TElementGroup group = null;
//							Iterator<TElementGroup> iterElemGr = groups.iterator();
//							while( iterElemGr.hasNext())
//							{
//								group = iterElemGr.next();
//								
//								if(group.getId().equalsIgnoreCase(variant.getAREF()))	
//								{
//									group.getElement().remove(ifcElementId);
//									
//									if(group.getElement().isEmpty())
//									{									
//										
//										iterElemGr.remove();
//										iterVar.remove();
//									}
//								}							
//							}	
//						}						
//					}
//				}	
//				
//			}
//			
//			if(variantList.isEmpty())
//			{
//				iterComb.remove();
//			}
//		}
//
//	}
	
	
	@Override
	public void saveMatrix(java.net.URI uri, TSimulationMatrix matrix) throws SimulationMatrixException {
		try {
			ResourceSet resourceSet = initResourceSet();
			
			// create simmatrix
			Resource resource = resourceSet.createResource(URI.createURI(uri.toString()));
			
			DocumentRoot documentRoot = factory.createDocumentRoot();
			
			documentRoot.setSimulationMatrix(matrix);
			resource.getContents().add(documentRoot);
			
			File file = new File(uri);
			FileOutputStream fos = new FileOutputStream(file);
			resource.save(fos, null);
		} catch(IOException e) {
			throw new SimulationMatrixException(e);
		}
	}

	@Override
	public void removeVariantFromCombination(List<Pair<String,String>> ids, TSimulationMatrix matrix){		
		for(Pair<String, String> pair : ids){
			removeVariantFromCombination(pair.getLeft(), pair.getRight(), matrix);
		}		
	}

	@Override
	public String addOccupancyTypeVariant(String occupancyUri, TimePeriod period, FileFormat type, TSimulationMatrix matrix) {
		TUsage usageTypes = getOrCreateUsage(matrix);
		
		EList<TOccupancyType> occupancyTypeList = usageTypes.getOccupancyType();
		TOccupancyType occupancyType = getOrCreateOccupancyType(occupancyUri, occupancyTypeList);
		
		EList<TOccupancyTypeVariant> occupancyTypeVariantList = occupancyType.getOccupancyTypeVariant();
		TOccupancyTypeVariant occupancyTypeVariant = factory.createTOccupancyTypeVariant();
		occupancyTypeVariant.setId(""+createIntegerUniqueIdentifier());
		if(period != null) occupancyTypeVariant.setPeriod(period);
		if(type != null) occupancyTypeVariant.setType(type);
//		occupancyTypeVariant.setValue("1728437598");	
		occupancyTypeVariantList.add(occupancyTypeVariant);
		
		return occupancyTypeVariant.getId();
	}
	
	private TUsage getOrCreateUsage(TSimulationMatrix matrix) {
		TVariables variables = getOrCreateVariablesSection(matrix);
		TUsage usageTypes = variables.getUsageTypeVariables();
		if(usageTypes == null) {
			usageTypes = factory.createTUsage();
			variables.setUsageTypeVariables(usageTypes);
		}
		return usageTypes;
	}

	private TOccupancyType getOrCreateOccupancyType(String uri, EList<TOccupancyType> occupancyTypeList) {		
		TOccupancyType occupancyType = null;
		
		for(int i = 0; i < occupancyTypeList.size(); i++) {
			occupancyType = occupancyTypeList.get(i);
			String cmpUri = occupancyType.getSource();
			if(uri.equals(cmpUri)) return occupancyType;
		}
		// no construction type found => create new entry
		occupancyType = factory.createTOccupancyType();
		occupancyType.setSource(uri);
		occupancyTypeList.add(occupancyType);
		return occupancyType;
	}

}
