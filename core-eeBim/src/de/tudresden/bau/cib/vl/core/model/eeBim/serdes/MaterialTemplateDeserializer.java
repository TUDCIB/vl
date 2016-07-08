/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;

/**
 * The Class MaterialTemplateDeserializer.
 */
public final class MaterialTemplateDeserializer {

	
	/**
	 * Instantiates a new material template deserializer.
	 */
	private MaterialTemplateDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param fileUrl the file url
	 * @return the material template
	 * @throws ParsingException the parsing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MaterialTemplate deserialize(URL fileUrl) throws ParsingException, IOException {
		MaterialTemplate template = deserialize(fileUrl.openStream());
		template.setSourceFileUri(fileUrl.toString());
		return template;
	}
	
	/**
	 * Deserialize.
	 *
	 * @param in the input stream. Finally this stream will be closed.
	 * @return the material template
	 * @throws ParsingException the parsing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static MaterialTemplate deserialize(InputStream in) throws ParsingException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		MaterialTemplate template = new MaterialTemplate();
		
		while ((line = br.readLine()) != null) {
			if (line.contains("NAME")) {
				String s[] = line.split("=");
				if(s.length < 2) throw new ParsingException("The material template contains no name");
				String name = s[1].trim();
							
				template.setName(name);
			} else if(line.contains("LAMBDA")) {
				String s[] = line.split("=");
				if(s.length == 2) {
					String lambdaText = s[1].trim();	
					if(StringUtils.isNotEmpty(lambdaText)) {
						lambdaText = lambdaText.replace("W/mK", "").trim();
						double lambda = NumberUtils.toDouble(lambdaText);
						template.setLambda(lambda);
					}
				}
			} else if(line.contains("AW")) {
				String s[] = line.split("=");
				if(s.length == 2) {
					String awText = s[1].trim();	
					double aw = NumberUtils.toDouble(awText);
					template.setAW(aw);
				}
			}
		}
		in.close();
		return template;
	}
	
	/**
	 * Deserialize.
	 *
	 * @param filePath the file path
	 * @return the material template
	 * @throws ParsingException the parsing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MaterialTemplate deserialize(String filePath) throws ParsingException, IOException {
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		MaterialTemplate template = deserialize(in);
		template.setSourceFileUri(filePath);
		return template;
	}
}
