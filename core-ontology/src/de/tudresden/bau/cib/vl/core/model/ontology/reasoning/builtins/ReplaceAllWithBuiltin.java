package de.tudresden.bau.cib.vl.core.model.ontology.reasoning.builtins;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.reasoner.rulesys.BuiltinException;
import com.hp.hpl.jena.reasoner.rulesys.RuleContext;
import com.hp.hpl.jena.reasoner.rulesys.builtins.BaseBuiltin;
import com.hp.hpl.jena.util.iterator.ClosableIterator;

/**
 * Replaces all triple with a corresponding subject and predicate with a given object. That means it replaces all found triples
 * with one new triple with the given object or create a new triple if none was found.
 * After that the status is always <b>ONE</b> (subject predicate object)
 * @author Ken
 *
 */
public class ReplaceAllWithBuiltin extends BaseBuiltin {
	

	@Override
	public boolean bodyCall(Node[] nodes, int length, RuleContext context) {
		if(length < 3)  throw new BuiltinException(this, context, "Must have 3 arguments to " + getName());

		checkArgs(length, context);

		Node subject = getArg(0, nodes, context);
		Node predicate = getArg(1, nodes, context);
		Node newObject = getArg(2, nodes, context);
		
		List<Triple> oldTriples = new ArrayList<Triple>();
		boolean isOk = false;
		ClosableIterator<Triple> iterator = context.find(subject, predicate, null);
		while(iterator.hasNext()) {
			Triple oldTriple = iterator.next();	
			oldTriples.add(oldTriple);
		}
		if(oldTriples.size() > 0) {
			for(Triple oldTriple : oldTriples) {
				context.remove(oldTriple);
			}
			isOk = true;
		}
		if(newObject != null) {
			Triple newTriple = new Triple(subject,predicate, newObject);
			context.add(newTriple);
		}
		return isOk;
	}

	@Override
	public int getArgLength() {
		return 3;
	}

	@Override
	public String getName() {
		return "replaceAllWith";
	}

	@Override
	public String getURI() {
		return BASE_URI + getName();
	}

	@Override
	public void headAction(Node[] nodes, int length, RuleContext context) {
		throw new BuiltinException(this, context, "builtin " + getName() + " not usable in rule heads");
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
