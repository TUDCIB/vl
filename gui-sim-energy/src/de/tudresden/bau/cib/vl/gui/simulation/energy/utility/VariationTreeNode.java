package de.tudresden.bau.cib.vl.gui.simulation.energy.utility;

import de.tudresden.bau.cib.vl.core.util.Pair;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;

public class VariationTreeNode extends TreeNode {
	
	
	private Pair<String, String> pair = null;

	public VariationTreeNode(String name) {
		super(name);
		
	}
	
	public VariationTreeNode(String name, String id) {
		super(name, id);
		
	}

	public Pair<String, String> getPair() {
		return pair;
	}

	public void setPair(Pair<String, String> pair) {
		this.pair = pair;
	}

}
