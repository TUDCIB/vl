package de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

public class ClimateResourceIndividualFactory extends AbstractResourceIndividualFactory {

	public ClimateResourceIndividualFactory(OntologyModel ontModel) {
		super(ontModel);
	}
	
	public Individual createClimateIndividual(ClimateLocationTemplate climateResource) throws FactoryException {
		Individual clInd = createIndividual(climateResource, EeBIMOntoVocabulary.CLIMATE_LOCATION.getURI());

//		add attributes
		String country = climateResource.getCountry();
		String town = climateResource.getTown();
		Float latitude = climateResource.getLatitude();
		Float longitude = climateResource.getLongitude();
		Float albedo = climateResource.getAlbedo();
		Float altitude = climateResource.getAltitude();
		Float orientation = climateResource.getOrientation();
		String region = climateResource.getRegion();
		if(country != null) {
			DatatypeProperty countryProperty = EeBIMOntoVocabulary.COUNTRY;
			if(countryProperty != null) {
				clInd.addLiteral(countryProperty, country);
			}
		}
		if(town != null) {
			DatatypeProperty townProperty = EeBIMOntoVocabulary.TOWN;
			if(townProperty != null) {
				clInd.addLiteral(townProperty, town);
			}
		}
		if(region != null) {
			DatatypeProperty regionProperty = EeBIMOntoVocabulary.REGION;
			if(regionProperty != null) {
				clInd.addLiteral(regionProperty, region);
			}
		}
		if(latitude != null) {
			DatatypeProperty latitudeProperty = EeBIMOntoVocabulary.LATITUDE;
			if(latitudeProperty != null) {
				clInd.addLiteral(latitudeProperty, latitude);
			}
		}
		if(longitude != null) {
			DatatypeProperty longitudeProperty = EeBIMOntoVocabulary.LONGITUDE;
			if(longitudeProperty != null) {
				clInd.addLiteral(longitudeProperty, longitude);
			}
		}
		if(albedo != null) {
			DatatypeProperty albedoProperty = EeBIMOntoVocabulary.ALBEDO;
			if(albedoProperty != null) {
				clInd.addLiteral(albedoProperty, albedo);
			}
		}
		if(altitude != null) {
			DatatypeProperty altitudeProperty = EeBIMOntoVocabulary.ALTITUDE;
			if(altitudeProperty != null) {
				clInd.addLiteral(altitudeProperty, altitude);
			}
		}
		if(orientation != null) {
			DatatypeProperty orientationProperty = EeBIMOntoVocabulary.ORIENTATION;
			if(orientationProperty != null) {
				clInd.addLiteral(orientationProperty, orientation);
			}
		}
		return clInd;
	}

}
