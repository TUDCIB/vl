/**
 * 
 */
package sensitivity_input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * This class contains the methods for preparing the output of several 
 * Therakles simulations for the sensitivity analysis.  
 * 
 * @author Frank Noack
 *
 */
public class TheraklesOutputSummary {

	private String ifcZoneId;
	public ArrayList< ResultsOfTheraklesSim > resultsOfTheraklesSims;
	public ArrayList< ResultsOfTheraklesSim > selectedSims;
	public Map< String, String > simIdOfCombinationId;
	public Map< String, String > combinationIdOfSimId;
	public Map< String, String > kPIUnits;
	// to proof if all values of an eKPI are zero and than don't write
	// this eKPI to teh .csv file for Granlund
	public ArrayList< Boolean > isAllNullColumn;


	/**
	 * 
	 * @param summaryFile the file with Therakles output summary
	 * @param simulationMatrix the simulation matrix
	 * 
	 * constructor
	 */
	public TheraklesOutputSummary( URL summaryFile ) {
		this.resultsOfTheraklesSims = new ArrayList< ResultsOfTheraklesSim >();
		this.selectedSims = new ArrayList< ResultsOfTheraklesSim >();
		this.simIdOfCombinationId = new HashMap< String, String >();
		this.combinationIdOfSimId = new HashMap< String, String >();
		this.isAllNullColumn = new ArrayList< Boolean >();

		this.kPIUnits = new HashMap< String, String >();
		//		this.kPIUnits.put("eKPI_111", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_112", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_113", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_121", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_122", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_123", "kWh / m\u00B2 \u0394 t" );
		//		this.kPIUnits.put("eKPI_111", "kWh / m\u00B2 \u0394 t" );
		this.kPIUnits.put("eKPI_111", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_112", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_113", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_121", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_122", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_123", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_111", "kWh / m\u00B2 dt" );
		this.kPIUnits.put("eKPI_21", "\u00B0C" );
		this.kPIUnits.put("eKPI_22", "\u00B0C" );
		this.kPIUnits.put("eKPI_23", "h" );
		this.kPIUnits.put("eKPI_24", "h" );
		this.kPIUnits.put("eKPI_31", "\u20AC / m\u00B2 \u0394 t" );

		String fileName = summaryFile.getFile();
		BufferedReader fR = null;
		// find out those columns which contains only 0
		try {
			InputStream in = summaryFile.openStream();

			// create a file reader
			fR = new BufferedReader( new InputStreamReader(in));

			// delimiter used is ","
			final String DELIMITER = ",";
			String line = "";

			// skip the next empty lines
			do {
				line = fR.readLine();
			}
			while ( ! line.startsWith("CombinationID") );

			// last read line is the header of the table
			String[] headerEntries = line.split( DELIMITER );
			ArrayList< String > eKPILabels = new ArrayList< String >();
			ArrayList< String > eKPIUnits = new ArrayList< String >();
			for ( int i = 1; i < headerEntries.length; i++ ) {
				int leftBracket = headerEntries[ i ].indexOf("(");
				int rigthBracket = headerEntries[ i ].indexOf(")");
				//			eKPILabels.add( headerEntries[ i ].trim() );
				eKPILabels.add( headerEntries[ i ].substring( 0, leftBracket - 1 ).trim() );
				eKPIUnits.add( headerEntries[ i ].substring( leftBracket + 1, 
						rigthBracket ) );
			}

			// at the beginning it is assumed that all values are zero
			for( int i = 0; i < eKPILabels.size(); i++ ) {
				this.isAllNullColumn.add( true );
			}

			// read the rest of the file line by line
			int counter = 0;
			// loop over the lines breaks when all eKPIs contain values unequal 0.0
			while( ( line = fR.readLine() ) != null && counter < eKPILabels.size() ) {
				// get all entries of that line	
				String[] entries = line.split( DELIMITER );
				for( int i = 1; i < entries.length; i++ ) {
					if ( ! ( Double.valueOf( entries[ i ] ) == 0.0 ) ) {
						isAllNullColumn.set(i-1, false);
						counter++;
					}
				}
			}
		}
		catch ( Exception e ) {
			e.printStackTrace();
		} finally {		
			try {
				fR.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// now read build the output summary
		try {
			InputStream in = summaryFile.openStream();

			// create a file reader
			fR = new BufferedReader( new InputStreamReader(in));

			// delimiter used is ","
			final String DELIMITER = ",";
			String line = "";


			// extract the IFC ID from the file name
			int endOfIfcId = fileName.indexOf( "summary");
			this.setIfcZoneId( fileName.substring( 0, endOfIfcId - 1 ) );


			// skip the next empty lines
			do {
				line = fR.readLine();
			}
			while ( ! line.startsWith("CombinationID") );
			//		| 
			//				! line.startsWith("Combination Id") |
			//				! line.startsWith("CombinationId") );

			// last read line is the header of the table
			String[] headerEntries = line.split( DELIMITER );
			ArrayList< String > eKPILabels = new ArrayList< String >();
			ArrayList< String > eKPIUnits = new ArrayList< String >();
			for ( int i = 1; i < headerEntries.length; i++ ) {
				int leftBracket = headerEntries[ i ].indexOf("(");
				int rigthBracket = headerEntries[ i ].indexOf(")");
				//			eKPILabels.add( headerEntries[ i ].trim() );
				eKPILabels.add( headerEntries[ i ].substring( 0, leftBracket - 1 ).trim() );
				eKPIUnits.add( headerEntries[ i ].substring( leftBracket + 1, 
						rigthBracket ) );
			}


			// read the rest of the file line by line
			int counter = 0;
			while( ( line = fR.readLine() ) != null ) {
				// get all entries of that line	
				ArrayList< Quantity > eKPIs = new ArrayList< Quantity >();

				String[] entries = line.split( DELIMITER );
				// get the ID of the simulation or combination resp.
				String combinationId = entries[ 0 ];
				for( int i = 1; i < entries.length; i++ ) {

					//					eKPIs.add( new Quantity( eKPILabels.get( i-1 ), 
					//							this.kPIUnits.get( eKPILabels.get( i-1 ) ), 
					//							Double.valueOf( entries[ i ] ) ) );
					eKPIs.add( new Quantity( eKPILabels.get( i-1 ), 
							eKPIUnits.get( i-1 ), Double.valueOf( entries[ i ] ) ) );
				}
				this.resultsOfTheraklesSims.add( new ResultsOfTheraklesSim ( 
						this.getIfcZoneId(), combinationId, eKPIs ) );
				//				this.resultsOfTheraklesSims.add( new ResultsOfTheraklesSim ( 
				//						this.getIfcZoneId(), combinationId, simId, eKPIs ) );
				this.simIdOfCombinationId.put(combinationId, String.valueOf(counter) );
				this.combinationIdOfSimId.put( String.valueOf(counter), combinationId );
				counter++;
			}
		}
		catch ( Exception e ) {
			e.printStackTrace();
		} finally {		
			try {
				fR.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}


	/**
	 * This method delivers the IDs of the selected combinations from Granlund 
	 * tool.
	 * It reads the downloaded cases csv file. A separate array with the 
	 * selected outputs of Therakles will be filled.
	 * 
	 * 
	 * @param casesFile the downloaded csv file from the Granlund tool
	 */
	public ArrayList< String > getSelectedCombIds( String casesFileName) {

		//String fileName = casesFile.getFile();

		// create a file reader
		BufferedReader fR = null;
		try {
			fR = new BufferedReader( new FileReader( casesFileName ));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// delimiter used is ","
		final String DELIMITER = ",";
		String line = "";

		// read IDs of the combinations and put the selected 
		// ResultsOfTheraklesSim to the list
		ArrayList< String > combIDs = new ArrayList< String >();
		try {
			while( ( line = fR.readLine() ) != null ) {

				String[] entries = line.split( DELIMITER );
				// get the column with the combination IDs
				String iD = entries[ 0 ];
				for ( ResultsOfTheraklesSim rTS : this.resultsOfTheraklesSims ) {
					if ( rTS.getCombinationId().equals(this.combinationIdOfSimId.get(iD) ) ){
						combIDs.add(rTS.getCombinationId() );
						this.selectedSims.add( rTS );
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fR.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return combIDs;
	}

	/**
	 * This method create a whole input for the sensitivity analysis tool of 
	 * Granlund as one string.
	 * 
	 * @return the whole input string
	 */
	public String createSensitivityInputString() {

		SensitivityInputWriter sIW = new SensitivityInputWriter();

		// create list of eKPI 
		for ( int i = 0; i < this.resultsOfTheraklesSims.get(0).getkPIs().size(); i++ ) {
			if ( this.isAllNullColumn.get( i ) ) continue;
			String name = this.resultsOfTheraklesSims.get(0).getkPIs().get( i ).name;
			int index = name.indexOf("_", 9);
			String shortName = name.substring(0 , index);
			sIW.addToKpiList( shortName );

			//			sIW.addToKpiList(this.resultsOfTheraklesSims.get(0).getkPIs().get( i ).name +
			//					" (" + this.resultsOfTheraklesSims.get(0).getkPIs().get( i ).unit + ")" );
		}

		sIW.addToParameterList("Simulation ID");

		//??? hier müssten für den header auch die Bezeichnungen der zur 
		// combination gehörenden Parameter ausgelesen werden, wenn die einzelnen
		// Parameter mit in die csv Datei rein sollen
		// die Werte der Parameter selbst werden dann unten in der for-Schleife 
		// mit der Methode getCombinationParameters aus der matrix gelesen

		sIW.writeHeader();
		sIW.writeln();

		int counter = 1;
		for ( ResultsOfTheraklesSim rTS : this.resultsOfTheraklesSims ) {
			// write the mandatory ID in the csv file
			sIW.writeField(this.simIdOfCombinationId.get( rTS.getCombinationId() ) );
			//			sIW.writeField( String.valueOf( counter ) );

			// write an ID which represents the combination ID from Therakles
			// output (the original combination ID can't be used because it 
			// contains also letters and not only numbers)
			// this ID act as the mandatory parameter 
			//			sIW.writeField("\"" + "Simulation ID " + 
			//					this.simIdOfCombinationId.get( rTS.getCombinationId() ).toString() 
			//					+ "\"" );
			//sIW.writeField(this.simIdOfCombinationId.get( rTS.getCombinationId() ) );
						sIW.writeField( rTS.getCombinationId() );

			//			// create random numbers for testing
			//			RandomDataGenerator rDG = new RandomDataGenerator();
			//			double lower = 0.0;
			//			double upper = 0.0;
						
//			for ( Quantity q : rTS.getkPIs() ){
//			    sIW.writeField(Double.toString( q.value ) );
			ArrayList< Quantity > eKPIList = rTS.getkPIs();					
            for ( int i = 0; i < eKPIList.size(); i++ ) {						
            	if ( this.isAllNullColumn.get( i ) ) continue;
            	sIW.writeField( Double.toString( eKPIList.get( i ).value ) );

				//				// write the random test values
				//				switch ( q.name ) {
				//				case "eKPI_111":
				//					lower = 14000.0;
				//					upper = 40000.0;
				//					break;
				//				case "eKPI_112":
				//					lower = 20000.0;
				//					upper = 60000.0;
				//					break;
				//				case "eKPI_113":
				//					lower = 25000.0;
				//					upper = 70000.0;
				//					break;
				//				case "eKPI_121":
				//					lower = 14000.0;
				//					upper = 40000.0;
				//					break;
				//				case "eKPI_122":
				//					lower = 20000.0;
				//					upper = 60000.0;
				//					break;
				//				case "eKPI_123":
				//					lower = 25000.0;
				//					upper = 70000.0;
				//					break;
				//				case "eKPI_21":
				//					lower = 18.0;
				//					upper = 24.0;
				//					break;
				//				case "eKPI_22":
				//					lower = 5.0;
				//					upper = 15.0;
				//					break;
				//				case "eKPI_23":
				//					lower = 4000.0;
				//					upper = 8700.0;
				//					break;
				//				case "eKPI_24":
				//					lower = 4000.0;
				//					upper = 8700.0;
				//					break;
				//				case "eKPI_31":
				//					lower = 1000.0;
				//					upper = 8000.0;
				//					break;
				//				}
				//				sIW.writeField( Double.toString( ( rDG.nextUniform(lower, upper) ) ) );
			}
			//			for ( Quantity q : rTS.getParameters() ){
			//				this.writeField(Double.toString( q.value ) );
			//			}
			sIW.writeln();
			counter++;
		}

		return sIW.getSensitivityInputString();

	}

	/**
	 * This method writes the whole senstivityInputString to a csv file.
	 * 
	 * @param	csvInputFileName		the target for writing
	 */
	public void createSensitivityInputFile( String csvInputFileName ) {

		try {
			PrintWriter writer = new PrintWriter( csvInputFileName );
			writer.print( this.createSensitivityInputString() );
			writer.close();
		}
		catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
	}


	/**
	 * This method provides the simulation parameters which belong to the simulation 
	 * configuraton.
	 * 
	 * @param simMatrixFileName the file name of the simulation matrix
	 * @param cId the combination ID
	 * 
	 * @return an ArrayList of variants 
	 * @see sensitivity_input.Variant
	 * 
	 * @throws XPathExpressionException
	 *  
	 */
	public static ArrayList< Variant > getCombinationParameters( 
			URL simMatrixFileName, 
			String cId ) throws XPathExpressionException {

		ArrayList< Variant > combinationVariants = new ArrayList< Variant >();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);

		DocumentBuilder builder;

		Document  sensitivityMatrix = null;

		String matrixFileName = simMatrixFileName.getFile();
		try {
			builder = factory.newDocumentBuilder();
			sensitivityMatrix = builder.parse( matrixFileName );

			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();

			// Create XPath object
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr = xpath.compile( "//Combination[@id = '" + cId + "']/Variant" );
			NodeList variants = (NodeList)expr.evaluate( sensitivityMatrix, 
					XPathConstants.NODESET );
			for ( int i = 0; i < variants.getLength(); i++ ) {
				NamedNodeMap nmm = variants.item( i ).getAttributes();
				String variantId = new String();
				for (int j = 0; j < nmm.getLength(); j++) {
					Node attribute = nmm.item(j);
					if (attribute.getNodeType() != Node.ATTRIBUTE_NODE) {
						continue;
					}
					String nodeName = attribute.getNodeName();
					if (nodeName.equals("VREF")) {
						variantId = attribute.getTextContent();
					}
				}

				// read the variants
				expr = xpath.compile( "//Variables//*[@id = '" + variantId + "']" );
				Node variantNode = (Node)expr.evaluate( sensitivityMatrix, 
						XPathConstants.NODE );
				String variantType = variantNode.getNodeName();

				// get the subnodes (resp. parameters) of the variants 
				expr = xpath.compile( "//Variables//*[@id = '" + variantId + "']/*" );
				NodeList parameterNodes = (NodeList)expr.evaluate( sensitivityMatrix, XPathConstants.NODESET );

				// I thought that the following line should work, but it doesn't ???
				// therefore the code above
				//                NodeList parameterNodes = variantNode.getChildNodes();

				switch ( variantType ) {
				case "WindowTypeVariant": {
					ArrayList< Quantity > properties = new ArrayList< Quantity >();
					for (int j = 0; j < parameterNodes.getLength(); j++ ){
						String name =  parameterNodes.item(j).getNodeName();
						String unit = new String();
						if ( parameterNodes.item(j).hasAttributes() ) {
							Attr attr = (Attr) parameterNodes.item(j).
									getAttributes().getNamedItem("unit");
							if (attr != null) {
								unit = attr.getValue();                      
							}   
						}
						Double value = Double.parseDouble( parameterNodes.
								item(j).getTextContent() );
						properties.add( new Quantity( name, unit, value ) );
					}
					combinationVariants.add( new WindowTypeVariant( variantId, 
							"window", properties ));
					break;
				}
				case "ConstructionTypeVariant": {
					ArrayList< Layer > layers = new ArrayList< Layer >();
					for (int j = 0; j < parameterNodes.getLength(); j++ ){
						String unit = new String();
						Double thickness = 0.0;
						String specification = new String( parameterNodes.item(j).
								getTextContent() );;
								if ( parameterNodes.item(j).hasAttributes() ) {
									Attr attr = (Attr) parameterNodes.item(j)
											.getAttributes().getNamedItem("unit");
									if (attr != null) {
										unit = attr.getValue();                      
									}   
									attr = (Attr) parameterNodes.item(j).
											getAttributes().getNamedItem("thickness");
									if (attr != null) {
										thickness = Double.parseDouble( attr.getValue() );                      
									}
								}
								layers.add( new Layer( unit, thickness, specification ) );
					}
					combinationVariants.add( new ConstructionTypeVariant ( variantId,
							"construction", layers ) );

					break;
				}
				case "ScheduleTypeVariant": {
					ArrayList< ScheduleQuantity > sQuantities = 
							new ArrayList< ScheduleQuantity >();
					for (int j = 0; j < parameterNodes.getLength(); j++ ){
						String name =  parameterNodes.item(j).getNodeName();
						String unit = new String();
						String scheduleType = new String();
						String schedulePeriod = new String();
						String scheduleValues = new String( parameterNodes.item(j).
								getTextContent() );;
								if ( parameterNodes.item(j).hasAttributes() ) {
									Attr attr = (Attr) parameterNodes.item(j)
											.getAttributes().getNamedItem("unit");
									if (attr != null) {
										unit = attr.getValue();                      
									}   
									attr = (Attr) parameterNodes.item(j).
											getAttributes().getNamedItem("type");
									if (attr != null) {
										scheduleType = attr.getValue();                      
									}
									attr = (Attr) parameterNodes.item(j).
											getAttributes().getNamedItem("period");
									if (attr != null) {
										schedulePeriod = attr.getValue();                      
									}
								}
								sQuantities.add( new ScheduleQuantity( name, unit, 
										scheduleType, schedulePeriod, scheduleValues ));
					}
					combinationVariants.add( new ScheduleTypeVariant ( variantId,
							"schedule", sQuantities ) );
					break;
				}
				case "ConsantTypeVariant": {
					break;
				}
				case "OccupancyTypeVariant": {
					break;
				}
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return combinationVariants;
	}



	/**
	 * @return the ifcZoneId
	 */
	public String getIfcZoneId() {
		return ifcZoneId;
	}


	/**
	 * @param ifcId the ifcZoneId to set
	 */
	public void setIfcZoneId(String ifcId) {
		this.ifcZoneId = ifcId;
	}

}
