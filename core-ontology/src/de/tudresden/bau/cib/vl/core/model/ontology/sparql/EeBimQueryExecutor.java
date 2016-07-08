package de.tudresden.bau.cib.vl.core.model.ontology.sparql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyExceptionCode;

/**
 * Class with defined queries to retrieve data from the ontology.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class EeBimQueryExecutor extends QueryExecutor {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(EeBimQueryExecutor.class);
	
	private static final String BIMONTO_NS = Ifc2x3DataModelProxy.NS_IFCOWL;
	private static final String EEBIM_NS = EnergyEnhancedBimModel.NS_EEBIM;
	
	private static final String BIMONTO_PREFIX = "BIMOnto";
	private static final String EEBIM_PREFIX = "eeBIM";
	private static final String RDF_PREFIX = "rdf";
	private static final String RDFS_PREFIX = "rdfs";
	private static final String OWL_PREFIX = "owl";

	public enum VARIABLES {
		x, 
		guid, 
		name, 
		longname, 
		description, 
		tplPath, 
		latitude, 
		longitude, 
		albedo, 
		orientation, 
		altitude, 
		country, 
		region, 
		town,
		spaceType
	}
	
	private EeBimQueryExecutor() {
		throw new AssertionError();
	}
	
	private static String getPrefixNSDefinition() {
		return 
		"PREFIX "+BIMONTO_PREFIX+": <"+BIMONTO_NS+"> " +
		"PREFIX "+EEBIM_PREFIX+": <"+EEBIM_NS+"> " +
		"PREFIX "+OWL_PREFIX+": <http://www.w3.org/2002/07/owl#> " +
		"PREFIX "+RDFS_PREFIX+": <http://www.w3.org/2000/01/rdf-schema#> " +
		"PREFIX "+RDF_PREFIX+": <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
	}
	
	public static boolean isIfcClass(String ifcClassName, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
			"SELECT ?"+VARIABLES.x+" "+
			"WHERE {" +
			"      ?"+VARIABLES.x+"	a 					"+BIMONTO_PREFIX+":"+ifcClassName+" ." +
			"      }";
		
		LOG.trace("Query for checking if it is a: {} -> {}", new Object[]{ifcClassName, queryString});
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = qe.execSelect();	
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		qe.close();	
		return resultList != null ? true : false;
	}
	
	public static List<Map<String,String>> findBuildings(OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.guid+" ?"+VARIABLES.name+" "+ //?"+VARIABLES.longname+" ?"+VARIABLES.description+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 								"+BIMONTO_PREFIX+":IfcBuilding ;" +
		"						"+BIMONTO_PREFIX+":Name 			?lblName ;" +
//		"						"+IFCOWL_PREFIX+":LongName		?lblLongName ;" +
//		"						"+IFCOWL_PREFIX+":Description	?txtDescClass ;" +
		"						"+BIMONTO_PREFIX+":GlobalId 		?guidClass ." +
		"	   ?lblName			"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.name+" ." +
//		"	   ?lblLongName 	"+IFCOWL_PREFIX+":StringValue 	?"+VARIABLES.longname+" ." +
		"	   ?guidClass		"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.guid+" ." +	
//		"	   ?txtDescClass	"+IFCOWL_PREFIX+":StringValue	?"+VARIABLES.description+" ." +
		"      }";
		
		LOG.trace("Query for finding all buildings : {}", queryString);
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = qe.execSelect();	
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		qe.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findRooms(OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.guid+" ?"+VARIABLES.name+" ?"+VARIABLES.longname+" ?"+VARIABLES.description+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 								"+BIMONTO_PREFIX+":IfcSpace ;" +
		"						"+BIMONTO_PREFIX+":Name 			?lblName ;" +
		"						"+BIMONTO_PREFIX+":LongName		?lblLongName ;" +
		"						"+BIMONTO_PREFIX+":Description	?txtDescClass ;" +
		"						"+BIMONTO_PREFIX+":GlobalId 		?guidClass ." +
		"	   ?lblName			"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.name+" ." +
		"	   ?lblLongName 	"+BIMONTO_PREFIX+":StringValue 	?"+VARIABLES.longname+" ." +
		"	   ?guidClass		"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.guid+" ." +	
		"	   ?txtDescClass	"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.description+" ." +
		"      }";
		
		LOG.trace("Query for finding all rooms : {}", queryString);
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = qe.execSelect();	
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		qe.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findSpaceTypeTemplate(String roomGuid, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.spaceType+" ?"+VARIABLES.name+" ?"+VARIABLES.tplPath+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 						"+BIMONTO_PREFIX+":IfcSpace ;" +
		"						"+BIMONTO_PREFIX+":GlobalId 		?guidClass ;" +
		"	   					"+EEBIM_PREFIX+":hasSpaceType		?"+VARIABLES.spaceType+" ." +
		"	   ?guidClass		"+BIMONTO_PREFIX+":StringValue		\""+roomGuid+"\" ." +	
		"	   ?"+VARIABLES.spaceType+"		"+EEBIM_PREFIX+":pathToTemplate	?"+VARIABLES.tplPath+" ;" +
		"	   					"+EEBIM_PREFIX+":name				?"+VARIABLES.name+" ." +
		"      }";
		LOG.trace("Query for finding the space type template of the room: {} => {}", 
				new Object[]{roomGuid, queryString});
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = qe.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		qe.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findClimateLocation(String optionalIfcSiteGuid, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.latitude+" ?"+VARIABLES.longitude+" ?"+VARIABLES.region+" ?"+VARIABLES.tplPath+" " + ((optionalIfcSiteGuid == null) ? "?"+VARIABLES.guid+" " : "")+
		"WHERE {";
		if(optionalIfcSiteGuid == null) {
			queryString += 				
				"	?ifcSite		a							"+BIMONTO_PREFIX+":IfcSite ;"+
				"					"+BIMONTO_PREFIX+":GlobalId 			?guidClass ;" +
				"					"+EEBIM_PREFIX+":hasClimateLocation	?x ." +
				"	?guidClass		"+BIMONTO_PREFIX+":StringValue			?"+VARIABLES.guid+" .";
		} else {
			queryString +=
				"	?guidClass		"+BIMONTO_PREFIX+":StringValue			\""+optionalIfcSiteGuid+"\" .";
		}
		queryString +=
		"      ?"+VARIABLES.x+"	a 									"+EEBIM_PREFIX+":ClimateLocation ;" +
//		"	   					"+EEBIM_PREFIX+":albedo				?"+VARIABLES.albedo+" ;" +	
		"	   					"+EEBIM_PREFIX+":longitude			?"+VARIABLES.longitude+" ;" +	
		"	   					"+EEBIM_PREFIX+":latitude			?"+VARIABLES.latitude+" ;" +	
//		"	   					"+EEBIM_PREFIX+":orientation		?"+VARIABLES.orientation+" ;" +
//		"	   					"+EEBIM_PREFIX+":altitude			?"+VARIABLES.altitude+" ;" +
//		"	   					"+EEBIM_PREFIX+":country			?"+VARIABLES.country+" ;" +
		"	   					"+EEBIM_PREFIX+":region				?"+VARIABLES.region+" ;" +	
//		"	   					"+EEBIM_PREFIX+":town				?"+VARIABLES.town+" ;" +	
		"	   					"+EEBIM_PREFIX+":pathToTemplate		?"+VARIABLES.tplPath+" ." +	
		"      }";
		
		LOG.trace("Query for finding the climate template of the IFC site: {} => {}",
				new Object[]{optionalIfcSiteGuid, queryString});
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = qe.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		qe.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findConstructionTemplate(String buildingElementGuid, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.tplPath+" ?"+VARIABLES.name+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	"+BIMONTO_PREFIX+":GlobalId					?beGuidClass ;" +	
		"	   					"+EEBIM_PREFIX+":hasConstruction			?tpl ." +
		"	   ?beGuidClass		"+BIMONTO_PREFIX+":StringValue				\""+buildingElementGuid+"\" ." +	
		"	   ?tpl				"+EEBIM_PREFIX+":pathToTemplate				?"+VARIABLES.tplPath+" ;" +
		"	   					"+EEBIM_PREFIX+":name						?"+VARIABLES.name+" ." +
		"      }";
		
		LOG.trace("Query for finding the construction template of the building element: {} => {}",
				new Object[]{buildingElementGuid, queryString});
		Query query = QueryFactory.create(queryString);
		
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findConstructionsRelatedToRoom(String roomGuid, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.guid+" ?"+VARIABLES.tplPath+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 											"+BIMONTO_PREFIX+":IfcRelSpaceBoundary ;" +
		"						"+BIMONTO_PREFIX+":RelatedBuildingElement	?relatedElement ;" +
		"						"+BIMONTO_PREFIX+":RelatingSpace				?relatingSpace ." +
		"	   ?relatingSpace	"+BIMONTO_PREFIX+":GlobalId					?spaceGuidClass ." +	
		"	   ?spaceGuidClass	"+BIMONTO_PREFIX+":StringValue				\""+roomGuid+"\" ." +	
		"	   ?relatedElement	"+BIMONTO_PREFIX+":GlobalId					?elemGuidClass ;" +
		"	   					"+EEBIM_PREFIX+":hasConstruction			?tpl ." +
		"	   ?elemGuidClass	"+BIMONTO_PREFIX+":StringValue				?"+VARIABLES.guid+" ." +	
		"	   ?tpl				"+EEBIM_PREFIX+":pathToTemplate				?"+VARIABLES.tplPath+" ." +
		"      }";
		
		LOG.trace("Query for finding constrcutions related to the room: {} => {}",
				new Object[]{roomGuid,queryString});
		Query query = QueryFactory.create(queryString);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findConstructions(OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.tplPath+" ?"+VARIABLES.guid+" ?"+VARIABLES.name+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	"+BIMONTO_PREFIX+":GlobalId					?beGuidClass ;" +	
		"	   					"+EEBIM_PREFIX+":hasConstruction			?tpl ." +
		"	   ?beGuidClass		"+BIMONTO_PREFIX+":StringValue				?"+VARIABLES.guid+" ." +	
		"	   ?tpl				"+EEBIM_PREFIX+":pathToTemplate				?"+VARIABLES.tplPath+" ;" +
		"	   					"+EEBIM_PREFIX+":name						?"+VARIABLES.name+" ." +
		"      }";
		
		LOG.trace("Query for finding the construction template => {}",
				new Object[]{queryString});
		Query query = QueryFactory.create(queryString);
		
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return toMap(resultList);
	}
	
	public static List<Map<String,String>> findMaterialTemplatesRelatedToBuildingElement(String buildingElementGuid, OntologyModel model) {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.x+" ?"+VARIABLES.tplPath+" ?"+VARIABLES.name+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 										"+BIMONTO_PREFIX+":IfcBuildingElement ;" +
		"	   					"+BIMONTO_PREFIX+":GlobalId				?beGuidClass ;" +	
		"	   					"+EEBIM_PREFIX+":hasConstruction		?cTpl ." +
		"	   ?beGuidClass		"+BIMONTO_PREFIX+":StringValue			\""+buildingElementGuid+"\" ." +	
		"	   ?cTpl			"+EEBIM_PREFIX+":hasMaterial			?mTpl ."+
		"	   ?mTpl			"+EEBIM_PREFIX+":pathToTemplate			?"+VARIABLES.tplPath+" ;" +
		"	   					"+EEBIM_PREFIX+":name					?"+VARIABLES.name+" ." +
		"      }";
		
		LOG.trace("Query for finding the material templates related to the building element: {} => {}",
				new Object[]{buildingElementGuid,queryString});
		Query query = QueryFactory.create(queryString);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return toMap(resultList);
	}
	
	public static String findGlobalId(String ifcRootUri, OntologyModel model) throws InformationNotFoundInOntologyException {
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.guid+" " +
		"WHERE {" +
		"	   <"+ifcRootUri+"> "+BIMONTO_PREFIX+":GlobalId 				?guidClass ." +
		"	   ?guidClass		"+BIMONTO_PREFIX+":StringValue			?"+VARIABLES.guid+" ." +	
		"      }";
		
		LOG.trace("Query for finding the GlobalId of: {} => {}",
				new Object[]{ifcRootUri, queryString});
		Query query = QueryFactory.create(queryString);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		if(resultList != null) {
			if(resultList.size() > 1) throw new InformationNotFoundInOntologyException(InformationNotFoundInOntologyExceptionCode.SPARQL_MULTIPLE_VALUES_FOUND, ""+resultList);
			return toMap(resultList.get(0)).get(VARIABLES.guid);
		} else {
			throw new InformationNotFoundInOntologyException(InformationNotFoundInOntologyExceptionCode.SPARQL_NO_RESULT);
		}
	}	
	
	public static List<QuerySolution> getConstructionBased(OntologyModel ontology) {
		List<QuerySolution> returnList = new ArrayList<QuerySolution>();
		
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT  ?ConstName ?ConstId ?IfcId  ?IfcName ?IfcType	    						" +
		"		WHERE																		" +
		"		{																			" +
		"			?IfcId		"+RDF_PREFIX+":type				?IfcType .					" +
		"			?IfcType	"+RDFS_PREFIX+":subClassOf*		"+BIMONTO_PREFIX+":IfcRoot .	" +
		"			OPTIONAL																" +	
		"			{																		" +		
		"				?IfcId		"+BIMONTO_PREFIX+":Name			?v .					" +
		"				?v			"+BIMONTO_PREFIX+":StringValue	?IfcName 				" +
		"			}																		" +	
		"			?IfcId		"+EEBIM_PREFIX+":hasConstruction	?ConstId .				" +
		"			?ConstId 	a 					"+EEBIM_PREFIX+":Construction ;			" +
		"						"+EEBIM_PREFIX+":name				?ConstName ;			" +
		"		}													";
		
		LOG.trace("Query to construction based data => {}",queryString);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();			
		while(results.hasNext()) returnList.add(results.next()); //WORKAROUND TO FILL ResultSet 
		queryExecution.close();
		return returnList;
	}
	
	public static List<QuerySolution> getOccupancyBased(OntologyModel ontology) {
		List<QuerySolution> returnList = new ArrayList<QuerySolution>();
		
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT  ?SpaceTypeName ?SpaceTypeId ?IfcId  ?IfcName ?IfcType	    " +
		"		WHERE												" +
		"		{													" +
		"			?IfcId		"+RDF_PREFIX+":type			?IfcType .		" +
		"			?IfcType	"+RDFS_PREFIX+":subClassOf*	"+BIMONTO_PREFIX+":IfcRoot .	" +
		"			OPTIONAL										" +	
		"			{												" +		
		"				?IfcId		"+BIMONTO_PREFIX+":Name			?v .		" +
		"				?v			"+BIMONTO_PREFIX+":StringValue		?IfcName 	" +
		"			}												" +
		"			?IfcId		"+EEBIM_PREFIX+":hasSpaceType		?SpaceTypeId .	" +
		"			?SpaceTypeId 	a 				"+EEBIM_PREFIX+":SpaceType ;	" +
		"						"+EEBIM_PREFIX+":name				?SpaceTypeName ;" +
		"		}													";
		
		LOG.trace("Query to construction based data => {}",queryString);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();			
		while(results.hasNext()) returnList.add(results.next()); //WORKAROUND TO FILL ResultSet 
		queryExecution.close();
		return returnList;
	}
	
	public static List<QuerySolution> getClimateBased(OntologyModel ontology) {
		List<QuerySolution> returnList = new ArrayList<QuerySolution>();
		
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT  ?ClimateLocationName ?ClimateLocationId ?IfcId  ?IfcName ?IfcType	    " +
		"		WHERE												" +
		"		{													" +
		"			?IfcId			"+RDF_PREFIX+":type			?IfcType .	" +
		"			?IfcType	"+RDFS_PREFIX+":subClassOf*	"+BIMONTO_PREFIX+":IfcRoot .	" +
		"			OPTIONAL										" +	
		"			{												" +		
		"				?IfcId		"+BIMONTO_PREFIX+":Name			?v 	.	" +
		"				?v			"+BIMONTO_PREFIX+":StringValue		?IfcName 	" +
		"			}												" +
		"			?IfcId		"+EEBIM_PREFIX+":hasClimateLocation	?ClimateLocationId .		" +
		"			?ClimateLocationId 	a 				"+EEBIM_PREFIX+":ClimateLocation ;		" +
		"						"+EEBIM_PREFIX+":name				?ClimateLocationName  ;			" +
		"		}													";
		
		LOG.trace("Query to construction based data => {}",queryString);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();			
		while(results.hasNext()) returnList.add(results.next()); //WORKAROUND TO FILL ResultSet 
		queryExecution.close();
		return returnList;
	}

	public static List<QuerySolution> getIfcBased(OntologyModel ontology) {
		List<QuerySolution> returnList = new ArrayList<QuerySolution>();
		
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT  ?IfcIdSite ?IfcNameSite                                                                              "+
		"		?IfcIdBuilding ?Climate ?ClimateName ?IfcNameBuilding                                                        "+
		"		?IfcIdStorey ?IfcNameStorey                                                                                  "+
		"		?IfcIdSpace ?IfcNameSpace ?SpaceType ?SpaceTypeName                                                          "+
		"		?IfcIdBuildingElement ?IfcTypeBuildingElement ?IfcNameBuildingElement ?Construction ?ConstructionName        "+
		"WHERE												                                                                 "+
		"{													                                                                 "+
		"			                                                                                                         "+
		"	?IfcIdSite		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcSite.	                                                     "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdSite		"+BIMONTO_PREFIX+":Name			?x .	                                                             "+
		"		?x		"+BIMONTO_PREFIX+":StringValue			?IfcNameSite	                                                         "+
		"	}	                                                                                                             "+
		"                                                                                                                    "+
		"	?IfcIdBuilding		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcBuilding .	                                                     "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdBuilding			"+BIMONTO_PREFIX+":Name			?y .	                                                         "+
		"		?y		"+BIMONTO_PREFIX+":StringValue			?IfcNameBuilding	                                                         "+
		"	}	                                                                                                             "+
		"	                                                                                                                 "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdSite	"+EEBIM_PREFIX+":hasClimateLocation	?Climate .                                                           "+
		"		?Climate	"+EEBIM_PREFIX+":name 					?ClimateName                                                         "+
		"	}                                                                                                                "+
		"	                                                                                                                 "+
		"	                                                                                                                 "+
		"                                                                                                                    "+
		"	?IfcIdAgg		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcRelAggregates;	                                                     "+
		"			"+BIMONTO_PREFIX+":RelatingObject		?IfcIdBuilding;                                                                  "+
		"			"+BIMONTO_PREFIX+":RelatedObjects		?IfcIdStorey.                                                                    "+
		"	?IfcIdStorey		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcBuildingStorey.                                                   "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdStorey			"+BIMONTO_PREFIX+":Name			?z .	                                                         "+
		"		?z		"+BIMONTO_PREFIX+":StringValue			?IfcNameStorey	                                                             "+
		"	}	                                                                                                             "+
		"	                                                                                                                 "+
		"	?IfcIdAgg1		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcRelAggregates;	                                                     "+
		"			"+BIMONTO_PREFIX+":RelatingObject		?IfcIdStorey;                                                                    "+
		"			"+BIMONTO_PREFIX+":RelatedObjects		?IfcIdSpace.                                                                     "+
		"	?IfcIdSpace		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcSpace.                                                                "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdSpace			"+BIMONTO_PREFIX+":LongName			?w .	                                                         "+
		"		?w		"+BIMONTO_PREFIX+":StringValue			?IfcNameSpace	                                                             "+
		"	}                                                                                                                "+
		"	                                                                                                                 "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdSpace	"+EEBIM_PREFIX+":hasSpaceType	?SpaceType .                                                                     "+
		"		?SpaceType	"+EEBIM_PREFIX+":name 					?SpaceTypeName                                                       "+
		"	}                                                                                                                "+
		"	                                                                                                                 "+
		"	?IfcIdSB		"+RDF_PREFIX+":type			"+BIMONTO_PREFIX+":IfcRelSpaceBoundary;	                                                 "+
		"			"+BIMONTO_PREFIX+":RelatingSpace		?IfcIdSpace;                                                                     "+
		"			"+BIMONTO_PREFIX+":RelatedBuildingElement		?IfcIdBuildingElement.                                                   "+
		"	?IfcIdBuildingElement		"+RDF_PREFIX+":type			?IfcTypeBuildingElement.                                         "+
		"	?IfcTypeBuildingElement	"+RDFS_PREFIX+":subClassOf*	"+BIMONTO_PREFIX+":IfcRoot.	                                                     "+
		"			                                                                                                         "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdBuildingElement			"+BIMONTO_PREFIX+":Name			?u .	                                                 "+
		"		?u		"+BIMONTO_PREFIX+":StringValue			?IfcNameBuildingElement	                                                     "+
		"	}                                                                                                                "+
		"	                                                                                                                 "+
		"	OPTIONAL                                                                                                         "+
		"	{                                                                                                                "+
		"		?IfcIdBuildingElement "+EEBIM_PREFIX+":hasConstruction	?Construction .                                                  "+
		"		?Construction		  "+EEBIM_PREFIX+":name 				?ConstructionName                                                "+
		"	}                                                                                                                "+
		"	                                                                                                                 "+
		"}                                                                                                                   ";
		
		LOG.trace("Query to IFC based data => {}",queryString);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();			
		while(results.hasNext()) returnList.add(results.next()); //WORKAROUND TO FILL ResultSet 
		queryExecution.close();
		return returnList;
	}
	
	/**
	 * Find building elements with specific summary.
	 * @param ontology
	 * @param summaryType 0:Critical, 1:Warning, 2:Information
	 * @return
	 */
	public static List<Map<String,String>> findBuildingElementsWithSummary(OntologyModel ontology, int summaryType) {
		String type = null;
		switch(summaryType) {
		case 0: type = "Critical"; break;
		case 1: type = "Warning"; break;
		case 2: type = "Information"; break;
		default: break;
		}
		List<QuerySolution> resultList = null;
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT ?"+VARIABLES.guid+" " +
		"WHERE {" +
		"      ?x				a 					"+BIMONTO_PREFIX+":IfcBuildingElement ;" +
		"						"+BIMONTO_PREFIX+":GlobalId 	?guidClass ;" +
		"						"+EEBIM_PREFIX+":hasSummary	"+EEBIM_PREFIX+":"+type+" . "	+
		"	   ?guidClass		"+BIMONTO_PREFIX+":StringValue	?"+VARIABLES.guid+" ." +	
		"      }";
		// http://141.30.143.53/ontology/eeBim#hasSummary, http://141.30.143.53/ontology/eeBim#Critical
		LOG.trace("Query for finding the {} elements => {}", 
				new Object[]{type, queryString});
		Query query = QueryFactory.create(queryString);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return toMap(resultList);
	}

	public static List<QuerySolution> getElementsWithNoAssignments(OntologyModel ontology) {
		List<QuerySolution> returnList = new ArrayList<QuerySolution>();
		
		String queryString = 
			getPrefixNSDefinition() +
		"SELECT  ?IfcId  ?IfcName ?IfcType	    									" +
		"		WHERE																" +
		"		{																	" +
		"			{																" +
		"				?IfcId		"+RDF_PREFIX+":type			?IfcType .						" +
		"				?IfcType	"+RDFS_PREFIX+":subClassOf*	"+BIMONTO_PREFIX+":IfcBuildingElement .		" +
		"				OPTIONAL														" +	
		"				{																" +		
		"					?IfcId		"+BIMONTO_PREFIX+":Name			?v .						" +
		"					?v			"+BIMONTO_PREFIX+":StringValue		?IfcName 					" +
		"				}																" +	
		"				FILTER NOT EXISTS {												" +
		"					{?IfcId	"+EEBIM_PREFIX+":hasConstruction	?con}							" +
		"				}																" +
		"			}																	" +
		"			UNION																" +
		"			{																	" +
		"				?IfcId		"+RDF_PREFIX+":type			?IfcType .						" +
		"				?IfcType	a					"+BIMONTO_PREFIX+":IfcSite .					" +
		"				OPTIONAL														" +	
		"				{																" +		
		"					?IfcId		"+BIMONTO_PREFIX+":Name			?v .						" +
		"					?v			"+BIMONTO_PREFIX+":StringValue		?IfcName 					" +
		"				}																" +	
		"				FILTER NOT EXISTS {												" +
		"					{?IfcId	"+EEBIM_PREFIX+":hasClimateLocation	?cli}						" +
		"				}																" +
		"			}																	" +
		"			UNION																" + 
		"			{																	" +
		"				?IfcId		"+RDF_PREFIX+":type			?IfcType .						" +
		"				?IfcType	"+RDFS_PREFIX+":subClassOf*	"+BIMONTO_PREFIX+":IfcSpace .					" +
		"				OPTIONAL														" +	
		"				{																" +		
		"					?IfcId		"+BIMONTO_PREFIX+":Name			?v .						" +
		"					?v			"+BIMONTO_PREFIX+":StringValue		?IfcName 					" +
		"				}																" +	
		"				FILTER NOT EXISTS {												" +
		"					{?IfcId	"+EEBIM_PREFIX+":hasSpaceType	?occ}								" +
		"				}																" +
		"			}																	" +
		"		}";
		
		LOG.trace("Query to construction based data => {}",queryString);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, ontology.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();			
		while(results.hasNext()) returnList.add(results.next()); //WORKAROUND TO FILL ResultSet 
		queryExecution.close();
		return returnList;
	}
	
}
