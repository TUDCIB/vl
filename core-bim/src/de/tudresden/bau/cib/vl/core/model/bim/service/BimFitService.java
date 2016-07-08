package de.tudresden.bau.cib.vl.core.model.bim.service;

import java.net.URL;

import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.parser.Ifc2x3Parser;

public interface BimFitService {

	Ifc2x3DataModelProxy parseIfc2x3File(URL url) throws IfcException;

	Ifc2x3Parser createIfc2x3Parser();
}
