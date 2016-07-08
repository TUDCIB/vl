package de.tudresden.bau.cib.vl.core.simulation.energy.model;

import java.util.Locale;



public class Material implements Comparable<Material>{
	
	private int category;
	private String name;
	private boolean organic;
	private float aw;
	private float w80;
	private float wsat;
	private float cT;
	private float rho;
	private float mu1;
	private float mu2;
	private float lambda;
	private float nu;
	private float eDyn;
	private float e;
	private float eta;
	private float porosity;
	private String alpha;
	private String source;
	private String manufacturer;
	private String description;
	private int id;

	
	public Material(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		Locale locale = Locale.getDefault();
		String acronym = locale.getLanguage();
//		Example: "DE: Verbundmörtel |EN: Compound Mortar"
		acronym = acronym.toUpperCase()+":"; // DE:
		String delimiter = "\\|"; // delimiter is '|'
		String[] localizedNames = name.split(delimiter);
		for(String s : localizedNames) {
			String tmpName = s.toUpperCase();
			int acronymIndex = tmpName.lastIndexOf(acronym);
			if(acronymIndex > -1) {
				name = s.substring(acronym.length()).trim();
				return name;
			}
		}

		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public boolean isOrganic() {
		return organic;
	}

	public void setOrganic(boolean organic) {
		this.organic = organic;
	}

	public float getAw() {
		return aw;
	}

	public void setAw(float aw) {
		this.aw = aw;
	}

	public float getW80() {
		return w80;
	}

	public void setW80(float w80) {
		this.w80 = w80;
	}

	public float getWsat() {
		return wsat;
	}

	public void setWsat(float wsat) {
		this.wsat = wsat;
	}

	public float getcT() {
		return cT;
	}

	public void setcT(float cT) {
		this.cT = cT;
	}

	public float getRho() {
		return rho;
	}

	public void setRho(float rho) {
		this.rho = rho;
	}

	public float getMu1() {
		return mu1;
	}

	public void setMu1(float mu1) {
		this.mu1 = mu1;
	}

	public float getMu2() {
		return mu2;
	}

	public void setMu2(float mu2) {
		this.mu2 = mu2;
	}

	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lambda) {
		this.lambda = lambda;
	}

	public float getNu() {
		return nu;
	}

	public void setNu(float nu) {
		this.nu = nu;
	}

	public float geteDyn() {
		return eDyn;
	}

	public void seteDyn(float eDyn) {
		this.eDyn = eDyn;
	}

	public float getE() {
		return e;
	}

	public void setE(float e) {
		this.e = e;
	}

	public float getEta() {
		return eta;
	}

	public void setEta(float eta) {
		this.eta = eta;
	}

	public float getPorosity() {
		return porosity;
	}

	public void setPorosity(float porosity) {
		this.porosity = porosity;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Material) {
			Material mat = (Material) obj;
			if(mat.name.equals(name)&& mat.hashCode() == hashCode()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "ID = " + id + ", Name = "+ name +", Rho = " + rho; 
	}

	@Override
	public int compareTo(Material o) {
		return name.compareToIgnoreCase(o.name);
	}

}
