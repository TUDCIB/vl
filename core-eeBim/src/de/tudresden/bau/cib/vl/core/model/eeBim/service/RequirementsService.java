package de.tudresden.bau.cib.vl.core.model.eeBim.service;

import java.io.File;
import java.io.IOException;

public interface RequirementsService {

	void readExcelFile(File file) throws IOException;
}
