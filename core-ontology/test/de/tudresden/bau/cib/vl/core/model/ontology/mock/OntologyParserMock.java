package de.tudresden.bau.cib.vl.core.model.ontology.mock;

import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;

/**
 * Mock class for exporting the parser without having the ParserService.<br/>
 * NOTE: Only for testing purposes!!!
 * 
 * @author Ken
 *
 */
public class OntologyParserMock {

	public static Parser createParser(String workingDirectoryPath, String format) {
		return OwlParser.createParser(workingDirectoryPath, format);
	}
}
