package de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles;

public class TheraklesWindow {
	
	private int id;
	private String name;
	private float f;
	private float g;
	private float U;
	

	public TheraklesWindow(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getF() {
		return f;
	}

	public void setF(float f) {
		this.f = f;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getU() {
		return U;
	}

	public void setU(float u) {
		U = u;
	}
}
