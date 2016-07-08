/**
 * 
 */
package sensitivity_input;

/**
 * This class is the abstract class to which the different variants belong.
 * 
 * @author Frank Noack
 *
 */
public abstract class Variant {
	private String iD;
	private String type;
	
	/**
	 * constructor
	 */
	public Variant( String variantId, String variantType ) {
		iD = variantId;
		type = variantType;
	}

	/**
	 * @return the iD
	 */
	public String getiD() {
		return iD;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(String iD) {
		this.iD = iD;
	}

	/**
	 * @return the variantType
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param variantType the variantType to set
	 */
	public void setType(String variantType) {
		this.type = variantType;
	}

}
