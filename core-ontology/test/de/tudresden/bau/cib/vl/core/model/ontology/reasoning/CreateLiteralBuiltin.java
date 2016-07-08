package de.tudresden.bau.cib.vl.core.model.ontology.reasoning;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.reasoner.rulesys.BindingEnvironment;
import com.hp.hpl.jena.reasoner.rulesys.RuleContext;
import com.hp.hpl.jena.reasoner.rulesys.Util;
import com.hp.hpl.jena.reasoner.rulesys.builtins.BaseBuiltin;

public class CreateLiteralBuiltin extends BaseBuiltin {

	@Override
	public String getName() {
		return "makeLiteral";
	}
	
	@Override
	public int getArgLength() {
		return 3;
	}
	
	@Override
	public boolean bodyCall(Node[] args, int length, RuleContext context) {
        BindingEnvironment env = context.getEnv();
        Node n1 = getArg(0, args, context);
        Node n2 = getArg(1, args, context);
        Node n3 = getArg(2, args, context);
        
        if (n1.isVariable() && n2.isURI() && n3.isLiteral()) {
            Object v1 = n1.getURI();
            Object v2 = n2.getURI();
            Object v3 = n3.getLiteralValue();
            

            Node newNode = Util.makeDoubleNode((double) v3);
            Triple triple = new Triple(n1, n2, newNode);
            context.getGraph().add(triple);
            return env.bind(args[2], newNode);
        }
        return false;
	}

}
