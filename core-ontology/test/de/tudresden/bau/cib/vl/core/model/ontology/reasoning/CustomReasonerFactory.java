package de.tudresden.bau.cib.vl.core.model.ontology.reasoning;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerFactory;
import com.hp.hpl.jena.reasoner.rulesys.BuiltinRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class CustomReasonerFactory implements ReasonerFactory {

    private static final String RULE_LOC = "resources/rules/enev.rules";

    static {
        BuiltinRegistry.theRegistry.register(new CreateLiteralBuiltin());
    }
    
	@Override
	public Reasoner create(Resource configuration) {
		configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
		configuration.addProperty(ReasonerVocabulary.PROPruleSet,  RULE_LOC);
        final GenericRuleReasoner reasoner = new GenericRuleReasoner(this, configuration);
        reasoner.setRules(Rule.rulesFromURL(RULE_LOC));
		return reasoner;
	}

	@Override
	public Model getCapabilities() {
		return GenericRuleReasonerFactory.theInstance().getCapabilities();
	}

	@Override
	public String getURI() {
	    // TODO NOTE this is just a suggestion
        return "de.tudresden.bau.cib.Reasoner";
	}

}
