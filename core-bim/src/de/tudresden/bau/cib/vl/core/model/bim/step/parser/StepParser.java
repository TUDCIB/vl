package de.tudresden.bau.cib.vl.core.model.bim.step.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jsdai.lang.SdaiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.step.StepDataModelProxy;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;


public abstract class StepParser extends Parser {
	
	public static final String STEP = "step";
	
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(StepParser.class);

	protected de.tudresden.bau.cib.parser.StepParser parser;
	
	protected StepParser(String workingDirectoryPath) {
		super(workingDirectoryPath);
		if(parser == null) {
			parser = new de.tudresden.bau.cib.parser.StepParser(workingDirectoryPath);
		} else {
			try {
				parser.stop();
			} catch (SdaiException e) {
				LOGGER.error("["+this.getClass().getSimpleName()+"] "+e.getMessage());
			}
			parser = new de.tudresden.bau.cib.parser.StepParser(workingDirectoryPath);
		}
	}

	@Override
	public StepDataModelProxy parse(String filePath) throws ParsingException {
		try {
			FileInputStream in = new FileInputStream(filePath);
			StepDataModelProxy model = (StepDataModelProxy) parse(in);
			model.setSourceFilePath(filePath);
			return model;
		} catch (FileNotFoundException e1) {
			throw new ParsingException(e1);
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

	public de.tudresden.bau.cib.parser.StepParser getParser() {
		return parser;
	}

	@Override
	public String getType() {
		return STEP;
	}

}
