package de.tudresden.bau.cib.vl.core.model.ontology.reasoning;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.rulesys.BuiltinRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.builtins.ReplaceAllWithBuiltin;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.builtins.StringToDoubleBuiltin;

public class Reasoner {

	private static final Logger LOG = LoggerFactory.getLogger(Reasoner.class);
	private Model schema;
	private OntologyModel model;
	private InfModel inf;
	
	
	public Reasoner(OntologyModel model) {
		this(null, model);
	}
	
	public Reasoner(Model schema, OntologyModel model) {
		this.schema = schema;
		this.model = model;
		addCustomBuiltins();
	}
	
	public Reasoner init() {
		LOG.debug("Init transitive Reasoner with following rules");		
		com.hp.hpl.jena.reasoner.Reasoner jenaReasoner = ReasonerRegistry.getTransitiveReasoner();		
		return instantiate(jenaReasoner);
	}
	
	public Reasoner init(URL ruleUrl) {
		List<URL> urls = new ArrayList<URL>();
		urls.add(ruleUrl);
		return init(urls);
	}
	
	public Reasoner init(Collection<URL> ruleUrls) {
		LOG.debug("Init Reasoner with {} "+ruleUrls.size()+" rules", 
				new Object[]{ruleUrls});	
		List<Rule> rules = new ArrayList<Rule>();
		for(URL url : ruleUrls) {
			rules.addAll(Rule.rulesFromURL(url.toString()));
		}
		return init(rules);
	}
	
	public Reasoner init(String rules) {
		LOG.debug("Init Reasoner with following rules: {}",
				new Object[]{rules});
		List<Rule> ruleList = Rule.parseRules(rules);
		return init(ruleList);
	}
	
	public Reasoner init(List<Rule> rules) {
		com.hp.hpl.jena.reasoner.Reasoner jenaReasoner = new GenericRuleReasoner(rules);		
		return instantiate(jenaReasoner);
	}
	
	private Reasoner instantiate(com.hp.hpl.jena.reasoner.Reasoner reasoner) {
		if(schema != null) {
			inf = ModelFactory.createInfModel(reasoner, schema, model.getUnderlyingModel());
		} else {
			inf = ModelFactory.createInfModel(reasoner, model.getUnderlyingModel());
		}
		reasoner.setDerivationLogging(true);
	    return this;
	}
	
	private void addCustomBuiltins() {
		BuiltinRegistry.theRegistry.register(new StringToDoubleBuiltin());
		BuiltinRegistry.theRegistry.register(new ReplaceAllWithBuiltin());
	}
	
	public InfModel getInferenceModel() {
		return inf;
	}
	
	public boolean isValid() {
		ValidityReport validity = getValidityReport();
		return validity.isValid();
	}
	
	public ValidityReport getValidityReport() {
	    ValidityReport validity = inf.validate();
	    return validity;
	}
}
