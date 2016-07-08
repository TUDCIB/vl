package de.tudresden.bau.cib.vl.core.model.ontology.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;


/**
 * Parser for all OWL files.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OwlParser extends Parser {

	private OntModel ontModel;
	public static OntModelSpec DEFAULT_MODEL_SPEC = OntModelSpec.OWL_DL_MEM;
	private final String format;



	private OwlParser(String workingDirectoryPath, String format) {
		super(workingDirectoryPath);
		this.format = format;
	}


	@Override
	public String getType() {
		return OntologyService.OWL;
	}
	
	public OntologyModel createModel(String... owlPaths) throws FileNotFoundException {
		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);
//		Model tdbModel = TDBFactory.createDataset(workingDirectoryPath).getDefaultModel();
//		ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF, tdbModel);
		
		String id = createModelId();
		OntologyModel ontologyModel = new OntologyModel(id, ontModel);
		return ontologyModel;
	}
	
	public OntologyModel createModel(Map<String,String> owlBaseNSToFilePaths) throws FileNotFoundException {
		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);	
		String id = createModelId();
		OntologyModel ontologyModel = new OntologyModel(id, ontModel);
		ontologyModel.loadSubModels(owlBaseNSToFilePaths);
		return ontologyModel;
	}
	
	@Override
	public OntologyModel parse(String filePath) throws ParsingException, FileNotFoundException {
		File file = new File(filePath);
		if(!file.exists()) throw new FileNotFoundException("File: '" + filePath + "' not found");
		
// 		use the FileManager to find the input file
		OntDocumentManager manager = OntDocumentManager.getInstance();
//		TODO this solves UnknownHostException problem if ontology files are on local machine
//		try {
//			String ifcURL = URI.create("file:D:/Daten/tomcat-data/vl/resources/ontology/IFC2X3_TC1_reworked.owl").toURL().toString();
//			manager.addAltEntry("http://iai.org/ifcOWL", ifcURL);
//			String eeBimURL = URI.create("file:D:/Daten/tomcat-data/vl/resources/ontology/eeBim.owl").toURL().toString();
//			manager.addAltEntry("http://ontology.cib.bau.tu-dresden.de/eeBim", eeBimURL);
//		} catch (MalformedURLException e) {
//			throw new ParsingException(e);
//		}
		
//		InputStream in = FileManager.get().open(filePath);
		InputStream in = manager.getFileManager().open(filePath);
		OntologyModel model = parse(in);
		model.setSourceFilePath(filePath);
		String uuid = UUID.nameUUIDFromBytes(filePath.getBytes()).toString();
		model.setId(uuid);
		return model;
	}
	
	@Override
	public OntologyModel parse(InputStream inputstream) {
		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);
		ontModel.read(inputstream, null, format);
		String id = createModelId();
		OntologyModel ontologyModel = new OntologyModel(id, ontModel);
		return ontologyModel;
	}


	@Override
	public void stop() throws ParsingException {
		if(ontModel != null) {
			ontModel.close();
		}
	}
	
	public static OwlParser createParser(String workingDirectoryPath, String format) {
		return new OwlParser(workingDirectoryPath, format);
	}

}
