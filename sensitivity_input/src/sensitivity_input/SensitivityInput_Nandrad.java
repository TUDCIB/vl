/**
 * 
 */
package sensitivity_input;

import java.io.IOException;
import java.net.URL;

/**
 * @author Frank Noack
 *
 */
public class SensitivityInput_Nandrad {
	
    /**
     * Constructor
     * 
     * @throws IOException
     */
	public SensitivityInput_Nandrad() throws IOException {
//		URL firstQuantityFile = new URL( "file", "localhost", 
//				"E:\\ises\\sensitivity_analysis\\output_passive_nandrad\\"
//				+ "ConstructionHeatConductionGains_summary.txt" );
//		URL secondQuantityFile = new URL( "file", "localhost", 
//				"E:\\ises\\sensitivity_analysis\\output_passive_nandrad\\"
//				+ "WindowSWRadGains_summary.txt" );
//		URL thirdQuantityFile = new URL( "file", "localhost", 
//				"E:\\ises\\sensitivity_analysis\\output_passive_nandrad\\"
//				+ "OperativeTemperature_summary.txt" );
//		URL summaryPassiveSimFile = new URL( "file", "localhost", 
//				"E:\\ises\\sensitivity_analysis\\output_passive_nandrad\\"
//				+ "ZonesOfInterest_summary.txt" );

		URL firstQuantityFile = new URL( "file", "localhost", 
				"e:\\ises\\skript\\ISES_Cloud\\project\\ISES_project_passive\\results\\"
				+ "ConstructionHeatConductionGains_Summary.txt" );
		URL secondQuantityFile = new URL( "file", "localhost", 
				"e:\\ises\\skript\\ISES_Cloud\\project\\ISES_project_passive\\results\\"
				+ "WindowSWRadGains_Summary.txt" );
		URL thirdQuantityFile = new URL( "file", "localhost", 
				"e:\\ises\\skript\\ISES_Cloud\\project\\ISES_project_passive\\results\\"
				+ "OperativeTemperature_Summary.txt" );
		URL summaryPassiveSimFile = new URL( "file", "localhost", 
				"e:\\ises\\skript\\ISES_Cloud\\project\\ISES_project_passive\\results\\"
				+ "ZonesOfInterest_Summary.txt" );
		
		// passive Nandrad simulation
		NandradOutputSummary nandradOutput = new NandradOutputSummary();		
		
		nandradOutput.ReadQuantityFile( firstQuantityFile );
		nandradOutput.ReadQuantityFile( secondQuantityFile );
		nandradOutput.ReadQuantityFile( thirdQuantityFile );
		nandradOutput.ReadSummaryFile(summaryPassiveSimFile);
		
		nandradOutput.createSensitivityInputFile( 
				"E:\\testout\\QuantitiesOfZones_CloudProject.csv" );
	}

}
