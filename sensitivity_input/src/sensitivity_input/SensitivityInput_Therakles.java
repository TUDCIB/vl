/**
 * 
 */
package sensitivity_input;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author noack
 *
 */
public class SensitivityInput_Therakles {
	
	public SensitivityInput_Therakles() throws MalformedURLException {
		
//        // input file from consoles
//		Scanner scanInput = new Scanner(System.in);
//		System.out.println( "input file: " );
//		String fname = scanInput.nextLine();
//		URL summaryTheraklesSimFile = new URL( "file", "localhost", 
//				"e:\\workspace\\ises_workflow_2\\src\\project\\SimMa\\"
//				+ fname );
//		scanInput.close();
		

        String theraklesSummaryFolder = new String( 
        		"e:\\ises\\skript\\ISES_shoe_box\\project\\TheraklesProjects\\" );
        String theraklesSimMaFolder = new String( 
        		"e:\\ises\\skript\\ISES_shoe_box\\project\\SimMa\\" );

		URL simulationMatrix = new URL( "file", "localhost",
				theraklesSimMaFolder + "ISES_project.simmatrix" );

        File folder = new File(theraklesSummaryFolder);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.getName().contains( "summary" ) && file.getName().endsWith("txt") ) {
		    	String summaryFile = file.getPath();
				URL summaryTheraklesSimFile = new URL( "file", "localhost", 
						summaryFile );
				String Zone = file.getName().substring( 0, 
						file.getName().indexOf(".") );
				String csvFileName = theraklesSummaryFolder + Zone +
						"_Input_Granlund.csv" ;
				
				TheraklesOutputSummary tOS = new TheraklesOutputSummary( 
						summaryTheraklesSimFile);
//				String sensitivityInput = tOS.createSensitivityInputString();
				tOS.createSensitivityInputFile( csvFileName );
		    }
		}


//		URL summaryTheraklesSimFile = new URL( "file", "localhost", 
//				"E:\\workspace\\ises_workflow_3\\project\\SimMa\\"
//				+ "1K09Lowj17oPhxavDeNqC6_summary.txt" );
//		URL simulationMatrix = new URL( "file", "localhost",
//				"E:\\workspace\\ises_workflow_3\\project\\SimMa\\"
//						+ "ISES_project.simmatrix" );
//
//
//		String csvFileName = "E:\\workspace\\ises_workflow_3\\project\\SimMa\\"
//				+ "1K09Lowj17oPhxavDeNqC6_Input_Granlund.csv";
//		
//		TheraklesOutputSummary tOS = new TheraklesOutputSummary( 
//				summaryTheraklesSimFile);
//		
//		String sensitivityInput = tOS.createSensitivityInputString();
//		tOS.createSensitivityInputFile( csvFileName );
		
//		try {
//			ArrayList< Variant > parameters = 
//					TheraklesOutputSummary.getCombinationParameters( 
//							simulationMatrix, 
//							tOS.combinationIdOfSimId.get("0") );
//		} catch (XPathExpressionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		
	}

}
