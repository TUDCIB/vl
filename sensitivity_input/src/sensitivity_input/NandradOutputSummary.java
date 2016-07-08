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
import java.util.Collections;

//import org.apache.commons.lang3.StringUtils;

/**
 * This class provides some specific methods for the processing of output files
 * of NANDRAD passive simulation.
 * 
 * @author Frank Noack
 *
 */
public class NandradOutputSummary {

	/**
	 * URL of the NANDRAD calculation
	 */
	private URL simProject;
	/**
	 * files with stat. results of NANDRAD calculation
	 */
	private ArrayList< URL > statDataFiles;
	/**
	 * @see ZonesOfInterest
	 */
	public ZonesOfInterest zonesOfInterestSummary; 
	public ArrayList< OutputQuantityOfZones > outputQuantities;
	/**
	 * an array with all calculated zones and their results
	 * <p>
	 * the results are from NANDRAD output summary and summary files of a 
	 * particular quantity 
	 * 
	 * @see ResultsOfZone
	 */
	public ArrayList< ResultsOfZone > resultsOfZones;
	
	/**
	 * constructor
	 * @throws IOException 
	 */
	public NandradOutputSummary () /*throws IOException*/ {
		this.simProject = null;
		this.zonesOfInterestSummary = new ZonesOfInterest();
		this.outputQuantities = new ArrayList< OutputQuantityOfZones >();
		this.resultsOfZones = new ArrayList< ResultsOfZone >();
		this.statDataFiles = new ArrayList< URL >();
	}
	
	/**
	 * This method reads an output file of NANDRAD with a particular quantity.
	 * Up to now ConstructionHeatConductionGains, WindowSWRadGains and
	 * OperativeTemperature are regarded.
	 * 
	 * @param quantitySummaryFile URL of the NANDRAD output summary file
	 * @throws IOException 
	 */
	public void ReadQuantityFile( URL quantitySummaryFile ) throws IOException {
		
			String fileName = quantitySummaryFile.getFile();
			// create a file reader
			BufferedReader fR = new BufferedReader( new FileReader( fileName ));

//			// get the name of outputQuantity from file name
//			int underscore = fileName.indexOf("_");
//			this.outputQuantities.add( new OutputQuantity( fileName.substring( 0 , underscore ) ) );
		
			// delimiter used is ","
			final String DELIMITER = ",";
			String line = "";

			// read the first lines
			String quantityName = new String();
			String statData = new String();
			do {
				try {
					line = fR.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if ( line.startsWith("Summary output for Nandrad passive Simulation of project" ) ) {
					int l = "Summary output for Nandrad passive Simulation of project".length();
					int indexOfEnding = line.substring( l + 1 ).indexOf(".");
					String name = line.substring( l + 1 ).substring( 0, indexOfEnding - 1 );
					if ( this.simProject == null ) {
						this.simProject = new URL( "file", "localhost", line.substring( l + 1 ) );
					}
					else if (! this.simProject.getFile().substring(0, indexOfEnding - 1 ).
							equalsIgnoreCase( name ) ) {
						System.err.println( "Error: Nandrad Results are not from the same project!" );
						System.exit(0);
					}
				}
				else if ( line.startsWith( "Statistic data series summary of NANDRAD results file" ) ) {
					int l = "Statistic data series summary of NANDRAD results file".length();
					statData = line.substring( l + 1);
					this.statDataFiles.add( new URL("file", "localhost", line.substring( l + 1) ) );
				}
				else if ( line.startsWith("Statistic data series summary for output quantity:" ) ){
					int l = "Statistic data series summary for output quantity:".length();
					quantityName = line.substring(  l + 1 ).trim();
				}
			} 
			while ( ! line.startsWith( "Zone Index") );
			
			this.outputQuantities.add( new OutputQuantityOfZones( quantityName, 
					new URL("file", "localhost", statData) ) );
			int thisQuantityIndex = this.outputQuantities.size() - 1;
			
			// last read line is the header of the table
			String[] headerEntries = line.split( DELIMITER );
			ArrayList< String > names = new ArrayList< String >();
			ArrayList< String > units = new ArrayList< String >();
			for ( int i = 2; i < headerEntries.length; i++ ) {
				int leftBracket = headerEntries[ i ].indexOf("[");
				int rigthBracket = headerEntries[ i ].indexOf("]");
				names.add( quantityName.concat( 
						"_" + headerEntries[ i ].substring( 1, leftBracket ) ) );
				units.add( headerEntries[ i ].substring(leftBracket + 1, rigthBracket ) );
			}
			// set the outputQuantity.unit of the current outputQuantity to the first read unit
			// because the unit of all entries (Min, Max, ...) is the same
			this.outputQuantities.get(thisQuantityIndex).setUnit(units.get( 0 ) );
			
			try {
				// read the rest of the file line by line
				while( ( line = fR.readLine() ) != null ) {
					// get all entries of that line
					String[] entries = line.split( DELIMITER );

					Integer index  = Integer.valueOf( entries[ 0 ]);
					String ifcKey  = entries[ 1 ];
					
					// look for the this element in array of resultsOfZones
					int i = 0;
					while ( i < this.resultsOfZones.size() && 
							! this.resultsOfZones.get( i ).getIFCKey().equals( ifcKey ) ) {
						i++;
					}
					// set new resultsOfZones entry if that entry doesn't exist yet
					if ( i >= this.resultsOfZones.size() ) {
						this.resultsOfZones.add( new ResultsOfZone( index, ifcKey ) );
					}
					// set the quantity entries for that zone
					for ( int j = 2; j < entries.length; j++ ) {
						this.resultsOfZones.get( i ).quantities.add( 
								new Quantity( names.get( j - 2 ) , units.get( j - 2 ), 
										Double.valueOf( entries[ j ] ) ) ); 
					}

					if ( quantityName.equals( "ConstructionHeatConductionGains" ) ) {
						this.outputQuantities.get( thisQuantityIndex ).zoneIndices.add( index );
						this.outputQuantities.get( thisQuantityIndex ).IFCKeys.add( ifcKey );
						this.outputQuantities.get( thisQuantityIndex ).sumOfAnnualLosses.add( 
								Double.valueOf( entries[ 2 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).sumOfAnnualGains.add( 
								Double.valueOf( entries[ 3 ] ) );
					}
					if ( quantityName.equals( "WindowSWRadGains" ) ) {
						this.outputQuantities.get( thisQuantityIndex ).zoneIndices.add( index );
						this.outputQuantities.get( thisQuantityIndex ).IFCKeys.add( ifcKey );
						this.outputQuantities.get( thisQuantityIndex ).sumOfAnnualLosses.add( 
								Double.valueOf( entries[ 2 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).sumOfAnnualGains.add( 
								Double.valueOf( entries[ 3 ] ) );
					}
					if ( quantityName.equals( "OperativeTemperature" ) ) {
						this.outputQuantities.get( thisQuantityIndex ).zoneIndices.add( index );
						this.outputQuantities.get( thisQuantityIndex ).IFCKeys.add( ifcKey );
						this.outputQuantities.get( thisQuantityIndex ).Median.add( 
								Double.valueOf( entries[ 2 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).Min.add( 
								Double.valueOf( entries[ 3 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).Max.add( 
								Double.valueOf( entries[ 4 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).FirstQuartile.add( 
								Double.valueOf( entries[ 5 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).ThirdQuartile.add( 
								Double.valueOf( entries[ 6 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).WhiskerMin.add( 
								Double.valueOf( entries[ 7 ] ) );
						this.outputQuantities.get( thisQuantityIndex ).WhiskerMax.add( 
								Double.valueOf( entries[ 8 ] ) );
					}
					Collections.sort( this.resultsOfZones );
				}
			}
			catch ( Exception e ) {
				e.printStackTrace();
			}
			
			fR.close();
	}

	/**
	 * This method reads the NANDRAD summary output file with the selected zones
	 * of interest and the corresponding quantities.
	 * 
	 * @param summaryFile
	 * @throws IOException 
	 */
	public void ReadSummaryFile( URL summaryFile ) throws IOException {
		
		//String fileName = summaryFile.getFile();
		// create a file reader
		//BufferedReader fR = new BufferedReader( new FileReader( fileName ));
		
		InputStream in = summaryFile.openStream();
		InputStreamReader inReader = new InputStreamReader(in);
		BufferedReader fR = new BufferedReader(inReader);

		// delimiter used is ","
		final String DELIMITER = ",";
		String line = "";

		// read the first lines
		do {
			try {
				line = fR.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		while ( ! line.startsWith( "Zones of Interest are") );
		
		try {
			// read the rest of the file line by line
			while( ( line = fR.readLine() ) != null ) {
				// get all entries of that line
				String[] entries = line.split( DELIMITER );

				Integer index  = Integer.valueOf( entries[ 0 ]);
				String ifcKey  = entries[ 1 ].trim();
				// if there is no GlobalId we skip it
				if( ifcKey.isEmpty() ) continue;
				//if(StringUtils.isEmpty(ifcKey)) continue;
				String outstandingQuantity = entries[ 2 ];
				String valAndUnit = entries[ 3 ];
				// get the unit
				int leftBracket = valAndUnit.indexOf("[");
				int rigthBracket = valAndUnit.indexOf("]");
				String unit = valAndUnit.substring(leftBracket + 1, rigthBracket );
				Double value = Double.valueOf( valAndUnit.substring(0, leftBracket - 1 ) ); 
				
				this.zonesOfInterestSummary.zoneIndices.add( index );
				this.zonesOfInterestSummary.IFCKeys.add( ifcKey );
				this.zonesOfInterestSummary.outstandigQuantity.add( outstandingQuantity );
				this.zonesOfInterestSummary.value.add( value );
				this.zonesOfInterestSummary.unit.add( unit );
				
				// look for the this element in array of resultsOfZones
				int i = 0;
				while ( i < this.resultsOfZones.size() && 
						! this.resultsOfZones.get( i ).getIFCKey().equals( ifcKey ) ) {
					i++;
				}
				// the element exists already
				if ( i < this.resultsOfZones.size() ) {
					// append some values
					this.resultsOfZones.get( i ).isZoneOfInterest = true;
					this.resultsOfZones.get( i ).outstandingQuantities.add( 
							new Quantity( outstandingQuantity, unit, value ) );
				}
				// this element does not exist yet
				else {
					// append a new one and the appropriate values
					this.resultsOfZones.add( new ResultsOfZone( index, ifcKey ) );
					this.resultsOfZones.get( i ).isZoneOfInterest = true;
					this.resultsOfZones.get( i ).outstandingQuantities.add( 
							new Quantity( outstandingQuantity, unit, value ) );
				}
			}
			Collections.sort( this.resultsOfZones );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		
		fR.close();
	}
	
	/**
	 * This method creates a input string for the sensitivity analysis tool of 
	 * Granlund 
	 */
	public String createSensitivityInputString() {
		
		SensitivityInputWriter sIW = new SensitivityInputWriter();
		
		// create list of eKPI
		// read the names of the ascertained quantities from the first zone in 
		// resultsOfZones (all ascertained zones have the same quantities)
		for ( Quantity quantity : this.resultsOfZones.get(0).getQuantities() ) {
			sIW.addToKpiList( quantity.name + " (" + quantity.unit + ")" );
		}
		
		// add the zone index as parameter (at least one parameter is required)
		sIW.addToParameterList("ZoneIndices");
		sIW.addToParameterList("isZoneOfInterest");

		sIW.writeHeader();
		sIW.writeln();
		
		// write the eKPI values for each zone line by line
		int counter = 1;
		for ( ResultsOfZone resOfZone : this.resultsOfZones ) {
			sIW.writeField( String.valueOf( counter ) );
			sIW.writeField( String.valueOf( resOfZone.getIndex() ) );
			if ( resOfZone.isZoneOfInterest == false ) {
				sIW.writeField( "0");
			}
			else {
				sIW.writeField( "1" );
			}
			for ( Quantity quantity : resOfZone.getQuantities() ) {
				sIW.writeField( String.valueOf( quantity.value ) );
			}
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
	 * @return the simProject
	 */
	public URL getSimProject() {
		return simProject;
	}

	/**
	 * @param simProject the simProject to set
	 */
	public void setSimProject(URL simProject) {
		this.simProject = simProject;
	}

}
