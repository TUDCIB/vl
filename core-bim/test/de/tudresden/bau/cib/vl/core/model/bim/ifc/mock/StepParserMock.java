package de.tudresden.bau.cib.vl.core.model.bim.ifc.mock;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.parser.Ifc2x3Parser;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;

/**
 * Mock class for exporting the parser without having the ParserService.<br/>
 * NOTE: Only for testing purposes!!!
 * 
 * @author Ken
 *
 */
public class StepParserMock {

	public static Parser createIfcParser(String workingDirectoryPath) {
		return Ifc2x3Parser.createParser(workingDirectoryPath);
	}
}
