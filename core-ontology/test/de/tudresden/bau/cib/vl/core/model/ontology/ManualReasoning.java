package de.tudresden.bau.cib.vl.core.model.ontology;

import java.io.PrintWriter;
import java.util.Iterator;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.RDFSRuleReasonerFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class ManualReasoning {

	
    /** Illustrate different ways of finding a reasoner */
	@Test
    public void test1() {
        String NS = "urn:x-hp-jena:eg/";
        
        // Build a trivial example data set
        Model rdfsExample = ModelFactory.createDefaultModel();
        Property p = rdfsExample.createProperty(NS, "p");
        Property q = rdfsExample.createProperty(NS, "q");
        rdfsExample.add(p, RDFS.subPropertyOf, q);
        rdfsExample.createResource(NS+"a")
                   .addProperty(p, "foo");
        
        // Create an RDFS inference model the easy way
//        InfModel inf = ModelFactory.createRDFSModel(rdfsExample);
        // Create an RDFS inference model the hard way
        Resource config = ModelFactory.createDefaultModel()
                          .createResource()
                          .addProperty(ReasonerVocabulary.PROPsetRDFSLevel, "simple");
        Reasoner reasoner = RDFSRuleReasonerFactory.theInstance().create(config);
        // Set the parameter the easier way
//        reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
//                              ReasonerVocabulary.RDFS_SIMPLE);
        InfModel inf = ModelFactory.createInfModel(reasoner, rdfsExample);
        Resource a = inf.getResource(NS+"a");
        Statement s = a.getProperty(q);
        System.out.println("Statement: " + s);
    }
    
    /** illustrate generic rules and derivation tracing */
	@Test
    public void test3() {
        // Test data
        String egNS = PrintUtil.egNS;   // Namespace for examples
        Model rawData = ModelFactory.createDefaultModel();
        Property p = rawData.createProperty(egNS, "p");
        Resource A = rawData.createResource(egNS + "A");
        Resource B = rawData.createResource(egNS + "B");
        Resource C = rawData.createResource(egNS + "C");
        Resource D = rawData.createResource(egNS + "D");
        A.addProperty(p, B);
        B.addProperty(p, C);
        C.addProperty(p, D);
        
        // Rule example
        String rules = "[rule1: (?a eg:p ?b) (?b eg:p ?c) -> (?a eg:p ?c)]";
        Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
        reasoner.setDerivationLogging(true);
        InfModel inf = ModelFactory.createInfModel(reasoner, rawData);
        
        PrintWriter out = new PrintWriter(System.out);
        for (StmtIterator i = inf.listStatements(A, p, D); i.hasNext(); ) {
            Statement s = i.nextStatement(); 
            System.out.println("Statement is " + s);
            for (Iterator<Derivation> id = inf.getDerivation(s); id.hasNext(); ) {
                Derivation deriv = id.next();
                deriv.printTrace(out, true);
            }
        }
        out.flush();
    }
    
    /** Another generic rules illustration */
	@Test
    public void test4() {
        // Test data
        String egNS = PrintUtil.egNS;   // Namespace for examples
        Model rawData = ModelFactory.createDefaultModel();
        Property first = rawData.createProperty(egNS, "concatFirst");
        Property second = rawData.createProperty(egNS, "concatSecond");
        Property p = rawData.createProperty(egNS, "p");
        Property q = rawData.createProperty(egNS, "q");
        Property r = rawData.createProperty(egNS, "r");
        Resource A = rawData.createResource(egNS + "A");
        Resource B = rawData.createResource(egNS + "B");
        Resource C = rawData.createResource(egNS + "C");
        A.addProperty(p, B);
        B.addProperty(q, C);
        r.addProperty(first, p);
        r.addProperty(second, q);
        
        // Rule example for
        String rules = 
            "[r1: (?c eg:concatFirst ?p), (?c eg:concatSecond ?q) -> " +            
        "     [r1b: (?x ?c ?y) <- (?x ?p ?z) (?z ?q ?y)] ]";        
        Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
//        reasoner.setParameter(ReasonerVocabulary.PROPtraceOn, Boolean.TRUE);
        InfModel inf = ModelFactory.createInfModel(reasoner, rawData);
//        System.out.println("OK = " + inf.contains(A, r, C));
        Iterator<Statement> list = inf.listStatements(A, null, (RDFNode)null);
        System.out.println("A * * =>");
        while (list.hasNext()) {
            System.out.println(" - " + list.next());
        }
    }
}
