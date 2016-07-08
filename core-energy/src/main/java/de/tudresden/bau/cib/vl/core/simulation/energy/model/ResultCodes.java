package de.tudresden.bau.cib.vl.core.simulation.energy.model;

public enum ResultCodes {

//	Weather data
	Outdoor_air_temperature(13010, "Outdoor air temperature", "Outdoor air temperature [°C]", "°C"),
	Outdoor_air_relative_humidity(13020, "Outdoor air relative humidity", "Outdoor air relative humidity [%]", "%"),	
	Long_wave_sky_radiation(13030, "Long wave sky radiation", "Long wave sky radiation [W]", "W"),
	Wind_direction(13040, "Wind direction", "Wind direction [°]", "°"),
	Wind_velocity(13050, "Wind velocity", "Wind velocity [m/s]", "m/s"),
	Outdoor_air_pressure(13060, "Outdoor air pressure", "Outdoor air pressure [hPa]", "hPa"),
	Wind_driven_rain(13070, "Wind driven rain", "Wind driven rain [kg/(m2s)", "kg/(m2s)"),
	Short_wave_direct_radiation(13080, "Short wave direct radiation", "Short wave direct radiation [W]", "W"),	
	Short_wave_diffuse_radiation(13090, "Short wave diffuse radiation", "Short wave diffuse radiation [W]", "W"),	
	Azimuth(13100, "Azimuth", "Azimuth [°]", "°"),	
	Elevation(13110, "Elevation", "Elevation [°]", "°"),	
	Short_wave_global_radiation(13120, "Short wave global radiation", "Short wave global radiation [W]", "W"),
	Cloud_coverage(13130, "Cloud coverage", "Cloud average [-]", "-"),
	Dew_Point_Temperature(13140, "Dew Point Temperature", "Dew Point Temperature [°C]", "°C"),
	Precipitation(13150, "Precipitation", "Precipitation [mm/h]","mm/h"),
	Solar_declination (13160, "Solar declination (0 - north)", "Solar declination (0 - north) [°]","°"),
	
//	Other
	Other_Ground_temperature(14010, "Ground temperature (undisturbed area)", "Ground temperature (undisturbed area) [°C]", "°C"),
	
//	Set Points
	Set_Point_Room_Temperature_Heating (15010, "Set point room temperature heating", "Set point room temperature heating [°C]", "°C"),
	Set_Point_Room_Temperature_Cooling (15020, "Set point room temperature cooling", "Set point room temperature cooling [°C]", "°C"),
	Set_Point_Air_Flow_Rate(15030, "Air Flow Rate (mechanical ventilation)", "Air Flow Rate (mechanical ventilation) [m3/h]", "m3/h"),
	Set_Point_Air_Humidity(15040, "Air Humidity", "Air Humidity [%]", "%"),
	Set_Point_CO2_Concentration(15050, "CO2 Concentration", "CO2 Concentration [ppm CO2]", "ppm CO2"),
	
//	External heat/gain loss
	External_heat_gainloss_solar_radiation (21010, "External heat gain/loss - solar radiation", "External heat gain/loss - solar radiation [kWh]", "kWh"),
	External_heat_gainloss_adjacent_spaces (21020, "External heat gain/loss - adjacent spaces", "External heat gain/loss - adjacent spaces [kWh]", "kWh"),
	External_heat_gainloss_facade (21030, "External heat gain/loss - facade", "External heat gain/loss - facade [kWh]", "kWh"),
	Sum_of_heat_transmission_gainloss_through_constructions (21040, "Sum of heat transmission gain/loss through constructions", "Sum of heat transmission gain/loss through constructions [W]", "W"),
	Sum_of_heat_transmission_gainloss_through_windows (21050, "Sum of heat transmission gain/loss through windows", "Sum of heat transmission gain/loss through windows [W]", "W"),
	Sum_of_short_wave_heat_radiation_gainloss_through_windows (21060, "Sum of short wave heat radiation gain/loss through windows" ,"Sum of short wave heat radiation gain/loss through windows [W]", "W"),
	Heat_transmission_through_windows_doors_openings (21070, "Heat transmission through windows, doors and openings", "Heat transmission through windows, doors and openings [W]", "W"),
	Short_wave_heat_radiation_through_windows_doors_openings (21080, "Short wave heat radiation through windows, doors and openings", "Short wave heat radiation through windows, doors and openings [W]", "W"),
	Heat_conduction_flux_through_interfaces (21090, "Heat conduction flux through interfaces", "Heat conduction flux through interfaces [W]", "W"),
	Long_wave_heat_radiation_flux_through_interfaces (21100, "Long wave heat radiation flux through interfaces", "Long wave heat radiation flux through interfaces [W]", "W"),
	Short_wave_heat_radiation_throug_interfaces (21110, "Short wave heat radiation through interfaces", "Short wave heat radiation through interfaces [W]", "W"),
//	Useful Energy Demand
	Heating(22010, "Heating", "Heating [kWh]", "kWh"),
	Cooling(22020, "Cooling", "Cooling [kWh]", "kWh"),
	Heating_Convective_Gains (22050, "Heating - Convective Gains", "Heating - Convective Gains [W]", "W"),
	Heating_Radiant_Gains (22060, "Heating - Radiant Gains", "Heating - Radiant Gains [W]", "W"),
//	Thermal Comfort
	Operative_Room_Temperature (23010, "Operative Room Temperature", "Operative Room Temperature [degC]", "degC"),	
	Operative_Room_Temperature_Min (23011, "Operative Room Temperature", "Operative Room Temperature [degC]", "degC"),
	Operative_Room_Temperature_Max (23012, "Operative Room Temperature", "Operative Room Temperature [degC]", "degC"),
	Room_Air_Temperature(23040, "Room Air Temperature", "Room Air Temperature [°C]", "°C"),
	
	Positive_Deviations_Set_Point_Room_Air_Temperature (23054, "Positive Deviations Set Point - Room Air Temperature", "Positive Deviations Set Point - Room Air Temperature [Kh]", "Kh"),
	Negative_Deviations_Set_Point_Room_Air_Temperature (23064, "Negative Deviations Set Point - Room Air Temperature", "Negative Deviations Set Point - Room Air Temperature [Kh]", "Kh"),
	
	Mean_radiative_temperature_of_the_space_envelope (23070, "Mean radiative temperature of the space envelope", "Mean radiative temperature of the space envelope [°C]", "°C"),
	Surface_temperature_of_interfaces (23080, "Surface temperature of interfaces", "Surface temperature of interfaces [°C]", "°C"),
	
//	ENERGY KEY PERFORMANCE INDICATORS
//	eKPI1.1a
	Heating_Net_Energy 				(31010, "Heating Net Energy", "Heating Net Energy [kWh/t]", "kWh/t"),
	Heating_Final_Energy 			(31014, "Heating Final Energy", "Heating Final Energy [kWh/t]", "kWh/t"),
//	eKPI1.2
	Cooling_Net_Energy 				(31030, "Cooling Net Energy", "Cooling Net Energy [kWh/t]", "kWh/t"),
	Cooling_Final_Energy 			(31034, "Cooling Final Energy", "Cooling Final Energy [kWh/t]", "kWh/t"),
//	eKPI1.1a & eKPI1.2
	Heating_And_Cooling_Net_Energy	(31040, "Heating and Cooling Net Energy", "Heating and Cooling Net Energy [kWh/t]", "kWh/t"),
	Heating_And_Cooling_Final_Energy(31044, "Heating and Cooling Final Energy", "Heating and Cooling Final Energy [kWh/t]", "kWh/t"),
//	eKPI2.1
	Greenhouse_Gas_Emissions		(33014, "Greenhouse gases (CO2 emissions)", "Greenhouse gases (CO2 emissions) [kg]", "kg"),

//	eKPI3.1a -- Combines Positive_Deviations_Set_Point_Room_Air_Temperature and Negative_Deviations_Set_Point_Room_Air_Temperature
	Temperature_Over_Under_Runs		(31074, "Temperature overruns and underruns", "Temperature overruns and underruns [°C]", "°C"),
//	eKPI3.1b
	Thermal_Comfort					(31070, "Thermal Comfort", "Thermal Comfort [°C]", "°C"),
//	eKPI3.1c
	Thermal_Comfort_Global_Values	(31071, "Thermal Comfort Global Values", "Thermal Comfort Global Values [°C]", "°C"),
	
//	eKPI6.1
	Investment_Costs				(42014, "Investment Costs", "Investment Costs [€]", "€"),
//	eKPI6.2
	Energy_Related_Operational_Costs (42024, "Energy related operational costs", "Energy related operational costs [€]", "€"),
//	eKPI6.3
	Non_Energy_Related_Operational_Costs (42034, "Non-energy related operational costs", "Non-energy related operational costs [€]", "€"),
	Lifecycle_Costs					(42040, "Lifecycle Costs", "Lifecycle Costs [€]", "€"),
	
//	Monitoring
	Duct_Air_Temperature			(51010, "Supply air after heat recovery", "Supply air after heat recovery [°C]", "°C"),
	Duct_Air_Temperature_Exhaust_Air(51015, "Exhaust air", "Exhaust air [°C]", "°C"),
	Duct_Air_Pressure				(51020, "Duct Air Pressure", "Duct Air Pressure [-]", "-"),
	Core_Temperature				(51030, "Core Temperature", "Core Temperature [°C]", "°C"),
	Water_Temperature				(51040, "Water Temperature", "Water Temperature [°C]", "°C"),
	Volumetric_Flow_Rate			(51050, "Volumetric Flow Rate", "Volumetric Flow Rate [-]", "-"),
	Water_Volume_Meter				(51060, "Water Volume Meter", "Water Volume Meter [-]", "-"),
	
	// Other
	Heat_recovery_valve_position(53020,"Heat recovery valve position","Heat recovery valve position [%]","%"),
	Supply_air_fan_rotation_speed(53030,"Supply air fan rotation speed","Supply air fan rotation speed [%]","%");

	
	private final int code;
	private final String quantity;
	private final String label;
	private final String valueUnit;
	
	
	private ResultCodes(int code, String quantity, String label, String valueUnit) {
		this.code = code;
		this.quantity = quantity;
		this.label = label;
		this.valueUnit = valueUnit;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getValueUnit() {
		return valueUnit;
	}
	
	public static ResultCodes valueOf(int code) {
		ResultCodes[] codes = ResultCodes.values();
		for(ResultCodes rc : codes) {
			if(rc.getCode() == code) {
				return rc;
			}
		}
		return null;
	}
}
