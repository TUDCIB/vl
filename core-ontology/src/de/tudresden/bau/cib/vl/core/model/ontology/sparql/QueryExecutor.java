package de.tudresden.bau.cib.vl.core.model.ontology.sparql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;

/**
 * Class with defined queries to retrieve data from the ontology.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class QueryExecutor {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutor.class);
	
	protected QueryExecutor() {
		throw new AssertionError();
	}
	
	protected static List<Map<String,String>> toMap(List<QuerySolution> solutions) {
		if(solutions == null) return null;
		List<Map<String,String>> results = null;	
		for(QuerySolution qs : solutions) {
			Iterator<String> iterator = qs.varNames();
			Map<String,String> map = null;
			while(iterator.hasNext()) {				
				String varName = iterator.next();
				RDFNode node = qs.get(varName);
				String value = null;
				if(!node.isResource()) {
					value = node.asLiteral().getString();
				} else {
					value = node.asResource().getURI();
				}
				if(map == null) map = new HashMap<String, String>();
				map.put(varName, value);
			}
			if(results == null) results = new ArrayList<Map<String, String>>();
			results.add(map);
		}
		return results;
	}
	
	protected static Map<String,String> toMap(QuerySolution solution) {
		if(solution == null) return null;
		Iterator<String> iterator = solution.varNames();
		Map<String,String> map = null;
		while(iterator.hasNext()) {				
			String varName = iterator.next();
			RDFNode node = solution.get(varName);
			String value = null;
			if(!node.isResource()) {
				value = node.asLiteral().getString();
			} else {
				value = node.asResource().getURI();
			}
			if(map == null) map = new HashMap<String, String>();
			map.put(varName, value);
		}
		return map;
	}
	
	/**
	 * General search method which takes a query to find the requested information in the model.
	 * @param query
	 * @param model
	 * @return A list with solutions if found.
	 */
	public static List<QuerySolution> find(String query, OntologyModel model) {	
		List<QuerySolution> resultList = new ArrayList<QuerySolution>();
		LOG.trace("Query that will be applied for search => {}", query);
		Query q = QueryFactory.create(query);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(q, model.getUnderlyingModel());
		ResultSet results = queryExecution.execSelect();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		return resultList;
	}
	
	/**
	 * Constructs a sub model from a query and a resource model.
	 * @param query Must contain a construct e.g. "construct where { <http://dbpedia.org/resource/Mount_Monadnock> a ?type } limit 5"
	 * @param model
	 * @return The model with the results
	 */
	public static Model subModel(String query, OntologyModel model) {
		LOG.trace("Query that will be applied for search => {}", query);
		Query q = QueryFactory.create(query);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(
				q, model.getUnderlyingModel());
		Model result = queryExecution.execConstruct();
		queryExecution.close();	
		return result;
	}
	
}
