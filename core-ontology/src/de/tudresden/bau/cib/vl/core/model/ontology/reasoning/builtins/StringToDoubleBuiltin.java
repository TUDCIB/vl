package de.tudresden.bau.cib.vl.core.model.ontology.reasoning.builtins;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.reasoner.rulesys.BindingEnvironment;
import com.hp.hpl.jena.reasoner.rulesys.BuiltinException;
import com.hp.hpl.jena.reasoner.rulesys.RuleContext;
import com.hp.hpl.jena.reasoner.rulesys.Util;
import com.hp.hpl.jena.reasoner.rulesys.builtins.BaseBuiltin;

public class StringToDoubleBuiltin extends BaseBuiltin {
	

	@Override
	public boolean bodyCall(Node[] nodes, int length, RuleContext context) {
		if(length < 2)  throw new BuiltinException(this, context, "Must have 2 arguments to " + getName());
		BindingEnvironment env = context.getEnv();

		checkArgs(length, context);

		Node n1 = getArg(0, nodes, context);

		if (n1.isLiteral()) {
			Object v1 = n1.getLiteralValue();

			if (v1 instanceof String) {
				String text = (String)v1;
				double doubleValue = Double.parseDouble(text);
				Node doubleNode = Util.makeDoubleNode(doubleValue);
				return env.bind(nodes[1], doubleNode);
			}
		}
		return false;
	}
	
    protected String getString(Node n, RuleContext context) {
       if (n.isLiteral()) {
            return n.getLiteralLexicalForm();
        } else {
          throw new BuiltinException(this, context, getName() + " takes only literal arguments");
        }
    }

	@Override
	public int getArgLength() {
		return 2;
	}

	@Override
	public String getName() {
		return "strToDouble";
	}

	@Override
	public String getURI() {
		return BASE_URI + getName();
	}

	@Override
	public void headAction(Node[] nodes, int length, RuleContext rc) {
		throw new BuiltinException(this, rc, "builtin " + getName() + " not usable in rule heads");
	}

	@Override
	public boolean isMonotonic() {
		return true;
	}

	@Override
	public boolean isSafe() {
		return true;
	}

}
