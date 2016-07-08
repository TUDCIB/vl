package sensitivity_input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Provides methods to create the input for the sensitivity 
 * analysis tool from Granlund.
 *
 * @author Frank Noack
 *
 */
public class SensitivityInputWriter {
	
	private File sensitivityInputFile;

	/**
	 * field separator character, set as ',' for this csv
	 */
	private String separator;
//	/**
//	 * field separator character, set as ',' for this csv
//	 */
//	private char separator;

	/**
	 * character used to start a comment line, set as "#"
	 */
	private String comment;
	/**
	 * gives the delimiter for the line; is per default set to
     * the system property 'line.separator'
	 */
	private String lineSeparator;
	/**
	 * true if there was a field previously written to this line,
     * that means there is a pending comma to be written
	 */
	private boolean inPreviousField;
    /**
     * the whole content of the input for the sensitivity analysis as one String
     */
	private String sensitivityInputString;
	/**
	 * the list of KPIs
	 */
	private ArrayList< String > kpiList;
	/**
	 * the list of parameters 
	 */
	private ArrayList< String > parameterList;
	/**
	 * PrintWriter that prints the fields
	 */
	private PrintWriter csvWriter;
	
	
    /**
	 * Constructor
	 * 
	 * @param	file	the csv file where the data will be written
	 *              
    */	
	public SensitivityInputWriter( String file ) {
		
		this.sensitivityInputFile = new File( file );
		
		this.sensitivityInputString = new String();
		this.separator = ",";
		this.comment = "# ";
		this.lineSeparator = "\n ";
		this.inPreviousField = false;
		this.kpiList = new ArrayList< String >();
		this.parameterList = new ArrayList< String >();
		
		try {
			this.csvWriter = new PrintWriter( this.sensitivityInputFile );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    /**
	 * Constructor
	 * 
	 * @param	file	the csv file where the data will be written
	 *              
    */	
	public SensitivityInputWriter() {
		
		this.sensitivityInputString = new String();
		this.separator = ",";
		this.comment = "# ";
		this.lineSeparator = "\n";
		this.inPreviousField = false;
		this.kpiList = new ArrayList< String >();
		this.parameterList = new ArrayList< String >();
	}


	/**
	 * @return	the	kpiList
	 */
	public ArrayList< String > getKpiList() {
		return kpiList;
	}

	/**
	 * @param	kpiList	the kpiList to set
	 */
	public void setKpiList(ArrayList< String > kpiList) {
		for ( Iterator< String > kpiListIt = kpiList.iterator(); kpiListIt.hasNext(); ) {
			this.kpiList.add("kpi_" + kpiListIt.next() );
		}
	}

	/**
	 * @param	kpi	the kpi to add
	 */
    public void addToKpiList( String kpi ) {
    	this.kpiList.add( "kpi_" + kpi );
    }

	/**
	 * @return	the parameterList
	 */
	public ArrayList< String > getParameterList() {
		return parameterList;
	}

	/**
	 * @param	parameterList	the parameterList to set
	 */
	public void setParameterList(ArrayList< String > parameterList) {
		for ( Iterator< String > parameterListIt = parameterList.iterator(); parameterListIt.hasNext(); ) {
			this.parameterList.add( "val_" + parameterListIt.next() );
		}
	}

	/**
	 * @param	parameter	the parameter to add
	 */
    public void addToParameterList( String parameter ) {
//    	this.parameterList.add( "val_" + parameter );
    	this.parameterList.add( parameter );
    }


	/**
	 * Writes the header of the csv file.
	 */
	public void writeHeader() {
		
		ArrayList< String > header = new ArrayList< String >();

		// first field is an ID
		header.add("ID");
		// second field is the combination ID
		header.addAll(this.parameterList);
		// list of KPIs
		header.addAll( this.kpiList );
		// list of parameters
//		header.addAll( this.parameterList );
		
		for ( Iterator< String > headerIt = header.iterator(); headerIt.hasNext(); ) {
			this.writeField(headerIt.next().toString() );
		}
	}
	
	public void writeSimLn( ArrayList< String > valOfSim ) {
		this.writeln();
		for ( Iterator< String > valOfSimIt = valOfSim.iterator(); valOfSimIt.hasNext(); ) {
			this.writeField(valOfSimIt.next().toString() );
		}
		
	}
	
   /**
    * Writes a new line in the CVS output file.
    */
    public void writeln() {
       this.inPreviousField = false;
       this.sensitivityInputString = this.sensitivityInputString.concat(
    		   this.lineSeparator);
    } 
    
    
    /**
     * Writes a single line of comma separated values.
     * 
     * @param	line	containing an array of field values 
     */
    public void writeln(String[] line) {

       for( int i = 0; i < line.length; i++ ) {
    	   this.sensitivityInputString = this.sensitivityInputString.concat( 
    			   line[ i ] );
        } 

       // write newLine
       this.writeln(); 
    }

   
	/**
	 * Writes a single comment line to the file given by the <code>text</code>.
	 * This is the text leaded by the <code>comment char + " "</code>, 
	 * given in the constructor.
	 * 
	 * @param	text	contains the comment text.
	 */
	   public void writeCommentln(String text) {
		  // close open line since we need to start a new one for comment
	      if ( this.inPreviousField) this.writeln(); 
	      this.csvWriter.print( this.comment );
	      //wasPreviousField = false; // to prevent a comma after the comment sign
	      this.writeField( text );
	      this.writeln();
	   } 
	   
	  /**
	   * Write one csv field to the file, followed by a separator
	   * unless it is the last field on the line. Lead and trailing
	   * blanks will be removed.
	   *
	   * @param	s	The string to write.  Any additional quotes or
	   *			embedded quotes will be provided by write.      
	   */
	   public void writeField ( String s ) {

		   if ( this.inPreviousField )
			   this.sensitivityInputString = this.sensitivityInputString.concat( 
					   this.separator );
		   else if ( s == null ) {
			   this.sensitivityInputString = this.sensitivityInputString.concat(
					   "" );
			   // return;
		   }
		   
		   // print the field
		   this.sensitivityInputString = this.sensitivityInputString.concat( s );
		   
		   // not to print trailing comma later
		   this.inPreviousField = true;
	   }
	   
	
	 /**
	  * Close the PrintWriter.
	  */
	  public void close() {
	     if ( this.csvWriter != null ) {
	        this.csvWriter.close();
	        this.csvWriter = null;
	     }
	  }

	/**
	 * @return the sensitivityInputString
	 */
	public String getSensitivityInputString() {
		return sensitivityInputString;
	}

	/**
	 * @param sensitivityInputString the sensitivityInputString to set
	 */
	public void setSensitivityInputString(String sensitivityInputString) {
		this.sensitivityInputString = sensitivityInputString;
	}

}
