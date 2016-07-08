package de.tudresden.bau.cib.vl.core.model.ontology.sparql;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.sparql.core.BasicPattern;


/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public class QueryBuilder {

	
	private StringBuilder query;
	private boolean prefixCompleted = false;
	private boolean selectStatementCompleted = false;
	private boolean whereClauseCompleted = false;
	
	
	public QueryBuilder() {
		query = new StringBuilder();
	}
	
	
	public QueryBuilder addPrefix(String prefixName, String uri) {
		if(prefixCompleted) throw new RuntimeException("It is not allowed to add a prefix after defining the select statement or where clause");
		query.append("PREFIX "+prefixName+": <"+uri+"> ");
		query.append("\n");
		return this;
	}
	
	public QueryBuilder addSelect(String... varNames) {
		if(selectStatementCompleted) throw new RuntimeException("It is not allowed to add a select statement after defining the where clause");
		prefixCompleted = true;
		query.append("SELECT ");
		for(String varName : varNames) {
			query.append("?"+varName+" ");
		}
		query.append("\n");
		return this;
	}
	
	public QueryBuilder beginWhere() {
		if(whereClauseCompleted) throw new RuntimeException("It is not allowed to add another where clause");
		selectStatementCompleted = true;
		query.append("WHERE { ");
		query.append("\n");
		return this;
	}
	
	public QueryBuilder endWhere() {
		if(whereClauseCompleted) throw new RuntimeException("It is not allowed to add another where clause");
		query.append("} ");
		whereClauseCompleted = true;
		return this;
	}
	
	public QueryBuilder addWhere(Triple... triples) {
		if(whereClauseCompleted) throw new RuntimeException("It is not allowed to add another where clause");
		
		for(int i = 1; i <= triples.length; i++) {
			Triple t = triples[i-1];
			query.append(t.getSubject()+" "+t.getPredicate()+" "+t.getObject());
			query.append(" . ");
			query.append("\n");
		}
		return this;
	}
	
	public QueryBuilder addWhere(BasicPattern bp) {
		return addWhere(bp.getList().toArray(new Triple[bp.size()]));
	}
	
	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="query"
	 */
	public String getQuery() {
		if(!(prefixCompleted && selectStatementCompleted && whereClauseCompleted)) throw new RuntimeException("Query incomplete\n"+query.toString());
		return query.toString();
	}
}
