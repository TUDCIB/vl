//package de.tudresden.bau.cib.vl.core.model.ontology.parser;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.UUID;
//
//import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.ontology.OntModelSpec;
//import com.hp.hpl.jena.query.Dataset;
//import com.hp.hpl.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.ModelFactory;
//import com.hp.hpl.jena.tdb.TDBFactory;
//import com.hp.hpl.jena.tdb.TDBLoader;
//import com.hp.hpl.jena.tdb.base.file.Location;
//import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
//import com.hp.hpl.jena.tdb.transaction.DatasetGraphTransaction;
//
//import de.tudresden.bau.cib.vl.core.exception.ParsingException;
//import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
//import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
//import de.tudresden.bau.cib.vl.core.model.parser.Parser;
//
//
///**
// * Parser for all OWL files.
// *
// * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
// *
// */
//public class TDBParser extends Parser {
//
//	private OntModel ontModel;
//	private Dataset dataSet;
//	public static OntModelSpec DEFAULT_MODEL_SPEC = OntModelSpec.OWL_DL_MEM;
////	public static OntModelSpec DEFAULT_MODEL_SPEC = OntModelSpec.OWL_DL_MEM_RULE_INF;
//
//
//
//	private TDBParser(String workingDirectoryPath) {
//		super(workingDirectoryPath);
//	}
//
//	@Override
//	public String getType() {
//		return OntologyService.OWL;
//	}
//	
//	public OntologyModel createModel(String... owlPaths) throws FileNotFoundException {
//		createOntModel();
//		
//		String id = createModelId();
//		OntologyModel ontologyModel = new OntologyModel(id, ontModel, dataSet);
//		ontologyModel.loadSchemesOfLinkedModels(owlPaths);
//		return ontologyModel;
//	}
//	
//	private void createOntModel() {
//		File f = new File(workingDirectoryPath+"/TDB/");
//		if(!f.exists())f.mkdirs();
////		OntModel model = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC);
//		this.dataSet = TDBFactory.createDataset(workingDirectoryPath+"/TDB/"+UUID.randomUUID());
//		// start reading
////		this.dataSet.begin(ReadWrite.WRITE);
//		Model tdbModel = this.dataSet.getDefaultModel();
//		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC, tdbModel);
////		this.dataSet.end();
//	}
//	
//	@Override
//	public OntologyModel parse(InputStream inputstream) {
////		createOntModel();
//
//        Location location = new Location(workingDirectoryPath+"/TDB/"+UUID.randomUUID());
//        DatasetGraphTransaction dst = (DatasetGraphTransaction)TDBFactory.createDatasetGraph(location);
//        DatasetGraphTDB dsg = dst.getBaseDatasetGraph();
//		TDBLoader.load(dsg, inputstream, false);
//		Dataset dataset = TDBFactory.createDataset(location);
//		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC, dataset.getDefaultModel());
//
//		String id = createModelId();
//		OntologyModel ontologyModel = new OntologyModel(id, ontModel, dataSet);
//		return ontologyModel;
//	}
//	
//	@Override
//	public OntologyModel parse(String tdbDirectory) {
//        Location location = new Location(tdbDirectory);
////        DatasetGraphTransaction dst = (DatasetGraphTransaction)TDBFactory.createDatasetGraph(location);
////        DatasetGraphTDB dsg = dst.getBaseDatasetGraph();
////		TDBLoader.load(dsg, inputstream, false);
//		Dataset dataset = TDBFactory.createDataset(location);
//		ontModel = ModelFactory.createOntologyModel(DEFAULT_MODEL_SPEC, dataset.getDefaultModel());
//
//		String id = createModelId();
//		OntologyModel ontologyModel = new OntologyModel(id, ontModel, dataSet);
//		return ontologyModel;
//	}
//
//
//	@Override
//	public void stop() throws ParsingException {
//		if(ontModel != null) {
//			ontModel.close();
//		}
//	}
//	
//	public static TDBParser createParser(String workingDirectoryPath) {
//		return new TDBParser(workingDirectoryPath);
//	}
//
//}
