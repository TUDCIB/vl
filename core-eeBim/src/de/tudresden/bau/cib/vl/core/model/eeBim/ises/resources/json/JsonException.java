package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.json;
import java.io.PrintStream;
import java.io.PrintWriter;


public class JsonException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** the underlying cause of the exception */
    private Throwable cause;

    /** the java file which caused the problem */
    private String fileName;

    /** the object name which caused the problem */
    private String elementName;

    /** the line number in the code of the error */
    private int lineNumber = -1;
    
    /** the column number in the code of the error */
    private int columnNumber = -1;
    
    public JsonException() {
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }
    
    public JsonException(Throwable cause) {
        super(cause.getLocalizedMessage());
        this.cause = cause;
    }
    
    public JsonException(Throwable cause, String fileName, String elementName, int columnNumber, int lineNumber) {
        this(cause.getLocalizedMessage(), cause, fileName, elementName, columnNumber, lineNumber);
    }
    
    public JsonException(String reason, Throwable cause, String fileName, String elementName, int columnNumber, int lineNumber) {
        super( (reason==null?cause.getClass().getName():reason) );
        this.cause = cause;
        this.fileName = fileName;
        this.elementName = elementName;
        this.columnNumber = columnNumber;
        this.lineNumber = lineNumber;
    }
    
    public JsonException(String reason, String fileName, String elementName, int columnNumber, int lineNumber) {
        super(reason);
        this.fileName = fileName;
        this.elementName = elementName;
        this.columnNumber = columnNumber;
        this.lineNumber = lineNumber;
    }
    
    public Throwable getCause() {
        return cause;
    }

    
    /** 
     * @return the line number of the tag 
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /** 
     * Sets the line number of the tag 
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /** 
     * @return the column number of the tag 
     */
    public int getColumnNumber() {
        return columnNumber;
    }
    
    /** 
     * Sets the column number of the tag 
     */
    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /** 
     * @return the Jelly file which caused the problem 
     */
    public String getFileName() {
        return fileName;
    }

    /** 
     * Sets the Jelly file which caused the problem 
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    

    /** 
     * @return the element name which caused the problem
     */
    public String getElementName() {
        return elementName;
    }

    /** 
     * Sets the element name which caused the problem
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    
    
    public String getMessage() {
        return fileName + ":" + lineNumber + ":" + columnNumber + ": <" + elementName + "> " + getReason();
    }

    public String getReason() {
        return super.getMessage();
    }

    // #### overload the printStackTrace methods...
    public void printStackTrace(PrintWriter s) { 
        synchronized (s) {
            super.printStackTrace(s);
            if  (cause != null) {
                s.println("Root cause");
                cause.printStackTrace(s);
            }
        }
    }
        
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            super.printStackTrace(s);
            if  (cause != null) {
                s.println("Root cause");
                cause.printStackTrace(s);
            }
        }
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (cause != null) {
            System.out.println("Root cause");
            cause.printStackTrace();
        }
    }

}
