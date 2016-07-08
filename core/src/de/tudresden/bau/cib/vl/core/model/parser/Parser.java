package de.tudresden.bau.cib.vl.core.model.parser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.Model;




public abstract class Parser {	
	
	protected String workingDirectoryPath;
	
	protected Parser(String workingDirectoryPath) {
		this.workingDirectoryPath = workingDirectoryPath;
	}
	
	public abstract String getType();

	/**
	 * @param filePath
	 * @return The Model as object type.
	 * @throws ParsingException
	 */
	public abstract Model parse(String filePath) throws ParsingException, FileNotFoundException;
	
	/**
	 * @param inputstream
	 * @return The Model as object type.
	 * @throws ParsingException
	 */
	public abstract Model parse(InputStream inputstream) throws ParsingException;
	
	/**
	 * Optional method for stopping the Parser explicitly.
	 * 
	 * @throws ParsingException
	 */
	public abstract void stop() throws ParsingException;
	
	/**
	 * General method for creating identifiers randomly.
	 * @return A unique identifier.
	 */
	protected String createModelId() {
		return UUID.randomUUID().toString();
	}
}
