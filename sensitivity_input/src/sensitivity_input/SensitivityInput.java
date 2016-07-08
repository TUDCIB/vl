/**
 * 
 */
package sensitivity_input;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.xpath.XPathExpressionException;

/**
 * @author Frank Noack
 *
 */
public class SensitivityInput {
	
	/** Constructor
	 * 
	 */
	public SensitivityInput() {
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//SensitivityInput_Nandrad sIN = new SensitivityInput_Nandrad();
		
		SensitivityInput_Therakles sIT = new SensitivityInput_Therakles();
	}

}
