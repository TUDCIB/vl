package de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Energy Results given as a Therakles Simulation Result file *.rmxml.txt
 * 
 * @author ken
 *
 */
public class TheraklesEnergyResults {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TheraklesEnergyResults.class);
	private File energyResultsFile;
	
	private TreeMap<Integer, List<Number>> energyResultMap = null;
	

	public TheraklesEnergyResults(File energyResults) {
		this.energyResultsFile = energyResults;
		readResults();
	}
	
	private void readResults(){
		energyResultMap = new TreeMap<Integer, List<Number>>();
		try {
//			It is a TAB-separated file with 32 columns

			FileReader fileReader = new FileReader(energyResultsFile);
			BufferedReader br = new BufferedReader(fileReader); 
			
			String s = br.readLine();  // first line is the header
			String[] headers = s.split("\t");
			for(int i = 0; i < headers.length; i++) {
				energyResultMap.put(i, new ArrayList<Number>());
			}
			while((s = br.readLine()) != null) { // next lines are values
				String[] rowData = s.split("\t");
				
				for(int i = 0; i < rowData.length; i++) {
					Double value = 0.0;
					try {
						value = Double.parseDouble(rowData[i]);
					} catch (NumberFormatException nfe) {
						LOGGER.warn("Unspecified Number Format: "+nfe.getMessage());
					}
					energyResultMap.get(i).add(value);
				}
				
////				first row contains text
//				for(int i = 0; i < rowData.length; i++) {
//					if(!rowData[i].matches("(-?[0-9]+.?[0-9]*)")) {
////						no number -> table header
//						energyResultMap.put(i, new ArrayList<Number>());
//					} else {
//						energyResultMap.get(i).add(Double.parseDouble(rowData[i]));
//					}
//				}
				
			} 
			fileReader.close(); 
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
	}
	
	public List<Number> getResults(ENERGYVALUES key) {
		return energyResultMap.get(key.ordinal());
	}
	
	public Map<Integer, List<Number>> getAllResults(){
		return energyResultMap;
	}
	
	public File getEnergyResultsFile() {
		return energyResultsFile;
	}
	
//	[0] Time/date [d]	
//	[1] Ambient temperature [degC]	
//	[2] Room air temperature [degC]	
//	[3] Operative temperature [degC]	
//	[4] Global radiation on horizontal surface (gross) [W/m2] (with respect to surface area)	
//	[5] Natural ventilation loads [W/m2]	
//	[6] Mechanical ventilation loads [W/m2]	
//	[7] Heating loads [W/m2]	
//	[8] Equipment loads [W/m2]	
//	[9] Occupants loads [W/m2]	
//	[10] Energy consumption of A/C equipment [W/m2]	
//	[11] Outside wall #1 surface temperature [degC]	
//	[12] Inside wall #1 surface temperature [degC]	
//	[13] Transmission load from surface of wall #1 [W/m2]	
//	[14] Transmission loads through window of wall #1 [W/m2]	
//	[15] Radiation loads through window of wall #1 [W/m2]	
//	[16] Imposed radiation load on exterior surface of wall #1 [W/m2] (with respect to wall surface area)	
//  [17] Global radiation load on exterior surface (gross) of wall #1 [W/m2] (with respect to wall surface area)	
//  [18] Outside wall #2 surface temperature [degC]	
//  [19] Inside wall #2 surface temperature [degC]	
//  [20] Transmission load from surface of wall #2 [W/m2]	
//  [21] Transmission loads through window of wall #2 [W/m2]	
//  [22] Radiation loads through window of wall #2 [W/m2]	
//  [23] Imposed radiation load on exterior surface of wall #2 [W/m2] (with respect to wall surface area)	
//  [24] Global radiation load on exterior surface (gross) of wall #2 [W/m2] (with respect to wall surface area)	
//  [25] Outside wall #3 surface temperature [degC]	
//  [26] Inside wall #3 surface temperature [degC]	
//  [27] Transmission load from surface of wall #3 [W/m2]	
//  [28] Transmission loads through window of wall #3 [W/m2]	
//  [29] Radiation loads through window of wall #3 [W/m2]	
//  [30] Imposed radiation load on exterior surface of wall #3 [W/m2] (with respect to wall surface area)	
//  [31] Global radiation load on exterior surface (gross) of wall #3 [W/m2] (with respect to wall surface area)
	public static enum ENERGYVALUES {
		TIME {
		    public String toString() {
		        return "Time/date [d]";
		    }
		},
		AMBIENT_TEMPERATURE {
		    public String toString() {
		        return "Ambient temperature [degC]";
		    }
		},
		ROOM_AIR_TEMPERATURE {
		    public String toString() {
		        return "Room air temperature [degC]";
		    }
		},
		OPERATIVE_TEMPERATURE {
		    public String toString() {
		        return "Operative temperature [degC]";
		    }
		},	
		GLOBAL_RADIATION {
		    public String toString() {
		        return "Global radiation on horizontal surface (gross) [W/m2] (with respect to surface area)";
		    }
		},
		NATURAL_VENTILATION {
		    public String toString() {
		        return "Natural ventilation loads [W/m2]";
		    }
		},
		MECHANICAL_VENTILATION {
		    public String toString() {
		        return "Mechanical ventilation loads [W/m2]	";
		    }
		},
		HEATING_LOADS {
		    public String toString() {
		        return "Heating loads [W/m2]	";
		    }
		},
		EQUIPMENT_LOADS {
		    public String toString() {
		        return "Equipment loads [W/m2]";
		    }
		},
		OCCUPANT_LOADS {
		    public String toString() {
		        return "Occupants loads [W/m2]";
		    }
		},
		ENERGY_CONSUMPTION {
		    public String toString() {
		        return "Energy consumption of A/C equipment [W/m2]	";
		    }
		},
		OUTSIDE_WALL_1 {
		    public String toString() {
		        return "Outside wall #1 surface temperature [degC]	";
		    }
		},
		INSIDE_WALL_1 {
		    public String toString() {
		        return "Inside wall #1 surface temperature [degC]	";
		    }
		},
		TRANSMISSION_LOAD_FROM_SURFACE_OF_WALL_1 {
		    public String toString() {
		        return "Transmission load from surface of wall #1 [W/m2]";
		    }
		},
		TRANSMISSION_LOADS_THROUGH_WINDOW_1 {
		    public String toString() {
		        return "Transmission loads through window of wall #1 [W/m2]	";
		    }
		},
		RADIATION_LOADS_THROUGH_WINDOW_1 {
		    public String toString() {
		        return "Radiation loads through window of wall #1 [W/m2]	";
		    }
		},
		IMPOSED_RADIATION_LOAD_1 {
		    public String toString() {
		        return "Imposed radiation load on exterior surface of wall #1 [W/m2] (with respect to wall surface area)";
		    }
		},
		GLOBAL_RADIATION_LOAD_1 {
		    public String toString() {
		        return "Global radiation load on exterior surface (gross) of wall #1 [W/m2] (with respect to wall surface area)";
		    }
		},
		OUTSIDE_WALL_2 {
		    public String toString() {
		        return "Outside wall #2 surface temperature [degC]	";
		    }
		},
		INSIDE_WALL_2 {
		    public String toString() {
		        return "Inside wall #2 surface temperature [degC]	";
		    }
		},
		TRANSMISSION_LOAD_FROM_SURFACE_OF_WALL_2 {
		    public String toString() {
		        return "Transmission load from surface of wall #2 [W/m2]";
		    }
		},
		TRANSMISSION_LOADS_THROUGH_WINDOW_2 {
		    public String toString() {
		        return "Transmission loads through window of wall #2 [W/m2]	";
		    }
		},
		RADIATION_LOADS_THROUGH_WINDOW_2 {
		    public String toString() {
		        return "Radiation loads through window of wall #2 [W/m2]	";
		    }
		},
		IMPOSED_RADIATION_LOAD_2 {
		    public String toString() {
		        return "Imposed radiation load on exterior surface of wall #2 [W/m2] (with respect to wall surface area)";
		    }
		},
		GLOBAL_RADIATION_LOAD_2 {
		    public String toString() {
		        return "Global radiation load on exterior surface (gross) of wall #2 [W/m2] (with respect to wall surface area)";
		    }
		},
		OUTSIDE_WALL_3 {
		    public String toString() {
		        return "Outside wall #3 surface temperature [degC]	";
		    }
		},
		INSIDE_WALL_3 {
		    public String toString() {
		        return "Inside wall #3 surface temperature [degC]	";
		    }
		},
		TRANSMISSION_LOAD_FROM_SURFACE_OF_WALL_3 {
		    public String toString() {
		        return "Transmission load from surface of wall #3 [W/m2]";
		    }
		},
		TRANSMISSION_LOADS_THROUGH_WINDOW_3 {
		    public String toString() {
		        return "Transmission loads through window of wall #3 [W/m2]	";
		    }
		},
		RADIATION_LOADS_THROUGH_WINDOW_3 {
		    public String toString() {
		        return "Radiation loads through window of wall #3 [W/m2]	";
		    }
		},
		IMPOSED_RADIATION_LOAD_3 {
		    public String toString() {
		        return "Imposed radiation load on exterior surface of wall #3 [W/m2] (with respect to wall surface area)";
		    }
		},
		GLOBAL_RADIATION_LOAD_3 {
		    public String toString() {
		        return "Global radiation load on exterior surface (gross) of wall #3 [W/m2] (with respect to wall surface area)";
		    }
		}
	}

}
