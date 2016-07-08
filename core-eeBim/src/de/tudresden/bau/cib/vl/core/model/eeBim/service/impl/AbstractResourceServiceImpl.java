package de.tudresden.bau.cib.vl.core.model.eeBim.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;

public abstract class AbstractResourceServiceImpl implements TemplateService {
	
	protected ConfigurationService configurationService;
	protected UserService userService;
	protected FileService fileService;
	

	@Override
	public ClimateLocationTemplate findClimate(String uri, List<ClimateLocationTemplate> climateResources) {
		for(ClimateLocationTemplate climate : climateResources) {
			if(uri.equalsIgnoreCase(climate.getSourceFileUri())) return climate;
		}
		return null;
	}
	
	@Override
	public ClimateLocationTemplate findClimateTemplate(double latitude, double longitude, List<ClimateLocationTemplate> templates) {
		ClimateLocationTemplate climateTemplate = null;
		
		for(ClimateLocationTemplate clt : templates) {
//			CURRENTLY ALWAYS USE MUNICH
			if(clt.getRegion().contains("Munich")) {
				climateTemplate = clt;
			}
		}
		return climateTemplate;
	}
	
	@Override
	public ConstructionTemplate findConstructionTemplateByName(String tplName,
			Collection<ConstructionTemplate> constructionTemplates) {
		for(ConstructionTemplate template : constructionTemplates) {
			String currentName = template.getSourceFileUri();
			if(currentName.equalsIgnoreCase(tplName)) {
				return template;
			}
		}
		return null;
	}
	
	@Override
	public ConstructionTemplate findConstructionTemplateByUri(String uri,
			Collection<ConstructionTemplate> constructionTemplates) {
		for(ConstructionTemplate template : constructionTemplates) {
			String currentUri = template.getSourceFileUri();
			if(currentUri.equalsIgnoreCase(uri)) {
				return template;
			}
		}
		return null;
	}
	
	@Override
	public MaterialTemplate findMaterialTemplate(String tplName, List<MaterialTemplate> materialTemplates) throws ParsingException, IOException {
		for(MaterialTemplate template : materialTemplates) {
			String filePath = template.getSourceFileUri();
			File matFile = new File(filePath);
			if(matFile != null) {
				if(tplName.equals(matFile.getName())) {
					return template;
				}
			}
		}
		return null;
	}
	
	@Override
	public <T extends Resource> T findTemplateByUri(String uri, Collection<T> resources) {
		for(T tpl : resources) {
			String tplUri = tpl.getSourceFileUri();
			if(tplUri.equalsIgnoreCase(uri)) return tpl;
		}
		return null;		
	}
	
	@Override
	public double[] generateSamples(double meanValue, double standardDeviation,
			int numberOfSamples) {
		// call stochastic sample generator
		// limit the standard deviation to 10 % of the mean
		if ( meanValue <= 3 * standardDeviation ) {
			standardDeviation = meanValue / 10;
		}
    	double propertySamples[]  = new double[ numberOfSamples ];
    	
    	// random number generator 
    	Random rGen = new Random();
        
    	for ( int i = 0; i < numberOfSamples; i++ ) {
    		
    		if ( i < 5) {
	    		switch ( i ) {
	    		case 0: 
	    			propertySamples[ i ] = rGen.nextGaussian() * standardDeviation + meanValue;
	    			break;
	    		case 1:
	    			propertySamples[ i ] = meanValue - 3 * standardDeviation;
	    			break;
	    		case 2: 
	    			propertySamples[ i ] = meanValue + 3 * standardDeviation;
	    			break;
	    		case 3:
	    			propertySamples[ i ] = meanValue - 2 * standardDeviation;
	    			break;
	    		case 4:
	    			propertySamples[ i ] = meanValue + 2 * standardDeviation;
	    			break;
	    		}
    		}
    		else {
    			propertySamples[ i ] = rGen.nextGaussian() * standardDeviation + meanValue;
    		}
    	}
    			
    	return propertySamples;
	}
	
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

}
