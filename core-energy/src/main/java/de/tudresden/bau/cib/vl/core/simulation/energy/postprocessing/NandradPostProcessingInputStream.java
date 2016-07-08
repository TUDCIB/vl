package de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.processing.EnergyPostProcessingInputStream;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService;

/**
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 * @author Meike Zehlike
 *
 */
public class NandradPostProcessingInputStream extends EnergyPostProcessingInputStream {
	
	private boolean foundIndicesLine = false;
	private final String delimiter;
	private HashMap<Integer, String> indiceAndIfcGuid;
	private int startYear;

	
	public NandradPostProcessingInputStream(int code, String path, int startYear) {
		super(code, path);
		this.delimiter = new String("\\s+"); // any whitespace
		this.startYear = startYear;
	}
	
	private void fillLocationIdentifierMap(String quantityText) {
		this.indiceAndIfcGuid = new HashMap<Integer, String>();
		
		String[] quantitySplits = quantityText.split("\\Q|\\E"); //	1 '-01.09 CLOS. {Id:Ifc=1EmtIacnDA2xYAOvxVlEVs}'
		for(String quantitySplit : quantitySplits) {
			String indiceText = StringUtils.substringBefore(quantitySplit, "'");
			indiceText = indiceText.trim();
			try {
				int currentIndice = Integer.parseInt(indiceText);
				String commaText = StringUtils.substringBetween(quantitySplit, "'", "'");
				String braceText = StringUtils.substringBetween(commaText, "{Id:Ifc=", "}");
				if(braceText != null) {
					indiceAndIfcGuid.put(currentIndice, braceText);
				}				
			} catch(NumberFormatException nfe) {
//				LOG.error(nfe.getMessage(), nfe);
			}			
		}
	}

	@Override
	protected List<MeasuredUnit> process() throws IOException {
		List<MeasuredUnit> units = new ArrayList<MeasuredUnit>();
		FileReader fr = new FileReader(path);

		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String startYear = new String();
		String quantities = new String();
		String quantity_KW = new String();
		String timeUnit = new String();
		String valueUnit = new String();

		row: while ((line = br.readLine()) != null) {

			/**
			 * read meta data
			 * */

			if (line.contains("START_YEAR")) {
				String[] s = line.split(" = ");
				startYear = s[1];
				startYear = ""+this.startYear;
				continue row;
			}

			if (line.contains("QUANTITY_KW")) {
				String[] s = line.split(" = ");
				quantity_KW = s[1];
				continue row;
			}

			if (line.contains("QUANTITY")) {
				int firstEqual = line.indexOf("=")+1;
				quantities = line.substring(firstEqual);
				continue row;
			}

			if (line.contains("TIME_UNIT")) {
				String[] s = line.split(" = ");
				timeUnit = s[1];
				continue row;
			}

			if (line.contains("VALUE_UNIT")) {
				String[] s = line.split(" = ");
				valueUnit = s[1];
				continue row;
			}

			/**
			 * Create 1 data structure per room, a MeasuredUnit
			 * */
			if (!foundIndicesLine && line.contains("INDICES")) {
				String[] s = line.split("=");

				//					fill the map
				fillLocationIdentifierMap(quantities);

				s = s[1].split(delimiter);
				int arraySize = s.length;
				int i = 1; // s[0] -> "INDICES =",
				while (i < arraySize) {
					String indice = s[i];
					String locId = null;
					int indiceNr = Integer.parseInt(indice);
					if(indiceAndIfcGuid.containsKey(indiceNr)) {
						locId = indiceAndIfcGuid.get(indiceNr);
					} else {
//						LOG.warn("Quantity: {} No IFC GlobalId for indice nr: {}",
//								new Object[]{quantities, indiceNr});
					}
					//						String locId = getLocationIdentifier(quantities, Integer.parseInt(indice));
					if(locId == null) {		
						locId = EnergyResultService.NO_ROOM_IDENTIFIER;
					}
					units.add(new MeasuredUnit(locId));
					units.get(i - 1).setQuantity(quantity_KW);
					units.get(i - 1).setStartYear(new Integer(startYear));
					//						units.get(i - 1).setStartYear(2012);
					units.get(i - 1).setTimeUnit(timeUnit);
					units.get(i - 1).setValueUnit(valueUnit);
					i++;
				}
				foundIndicesLine = true;

			} else {
				// this branch is only executed, if line INDICES has been
				// found, otherwise there
				// would be no ID for MeasuredUnit Object
				if (foundIndicesLine) {

					/**
					 * This Branch writes the respective column of
					 * temperature values and some meta data into each unit
					 * 
					 * */

					String[] valueArray = line.split(delimiter);
					int valueArraySize = valueArray.length;

					if (valueArraySize == 1)
						continue row; // jump over empty line 15

					if ((valueArraySize - 1) != units.size()) {
//						LOG.warn("Number of IDs and columns in table doesn't match");
					}

					int i = 1; 
					while (i < valueArraySize) {
						String time = valueArray[0].trim();
						if(time.equals("0")) continue row; // jump over first value row because this is not needed
						String value_i = valueArray[i];
						units.get(i - 1).addValue(time, value_i);
						i++;
					}
				}
			}
		}
		if (units.size() == 0) {
//			LOG.warn("Line containing INDICES was missing in input file: {}",path);
		}
		br.close();
		fr.close();
		return units;
	}
}
