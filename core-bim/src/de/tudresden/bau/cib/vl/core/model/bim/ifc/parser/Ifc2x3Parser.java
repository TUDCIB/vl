package de.tudresden.bau.cib.vl.core.model.bim.ifc.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;


public class Ifc2x3Parser extends de.tudresden.bau.cib.vl.core.model.bim.step.parser.StepParser {
	
	public static final String IFC = "ifc";

	private Ifc2x3DataModelProxy model;

	private Ifc2x3Parser(String workingDirectoryPath) {
		super(workingDirectoryPath);
	}

	@Override
	public Ifc2x3DataModelProxy parse(String filePath) throws ParsingException {
		try {
			FileInputStream in = new FileInputStream(filePath);
			Ifc2x3DataModelProxy model = (Ifc2x3DataModelProxy) parse(in);
			model.setSourceFilePath(filePath);
			return model;
		} catch (FileNotFoundException e1) {
			throw new ParsingException("File: "+filePath+" not found", e1);
		}
	}
	
	@Override
	public Ifc2x3DataModelProxy parse(InputStream inputstream) throws ParsingException {
		try {
			model = new Ifc2x3DataModelProxy(parser.loadStream(inputstream));
			return model;
		} catch (de.tudresden.bau.cib.exceptions.parser.ParsingException e) {
			throw new ParsingException(e);
		}
	}

	@Override
	public void stop() throws ParsingException {
		try {
			parser.stop();
		} catch (SdaiException e) {
			throw new ParsingException(e);
		}
	}

	@Override
	public String getType() {
		return IFC;
	}

	public static Ifc2x3Parser createParser(String workingDirectoryPath) {
		return new Ifc2x3Parser(workingDirectoryPath);
	}

}
