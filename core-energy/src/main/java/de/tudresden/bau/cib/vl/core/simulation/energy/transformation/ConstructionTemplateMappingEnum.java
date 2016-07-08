package de.tudresden.bau.cib.vl.core.simulation.energy.transformation;


/**
 * @author Ken
 *	@deprecated Moved to eeBIM bundle
 */
@Deprecated
public enum ConstructionTemplateMappingEnum {

	IW_concrete ("IW_concrete", "walltypeConcrete"),
	IW_GYPSUM_BOARD ("IW_gypsum board", "walltypeGypsumBoard"),
	OW_steel_cover ("OW_steel cover", "walltypeSteelCover"),
	OW_steel ("OW_steel", "walltypeSteel"),
	OW_sunprotection_case ("OW_sunprotection case", "walltypeSunprotectionCase"),
	OW_reinforced_concrete ("OW_reinforced concrete", "walltypeReinforcedConcrete"),
	OW_TICS ("OW_TICS", "walltypeTICS"),
	
	W_narrow_corridor_window_and_door_gf ("W_narrow corridor_gf", "Narrow corridor window and door ground floor"),
	W_narrow_corridor_window_and_door_uf ("W_narrow corridor_uf", "Narrow corridor window and door upper floors"),
	W_narrow_window_ground_floor ("W_narrow window_gf", "Narrow window ground floor"),
	W_standard_window_upper_floors ("W_standard window_uf", "Standard windows upper floors"),
	W_standard_window_gf ("W_standard window_gf", "Window without glass panel ground floor"),
	W_standard_window_with_glass_paneel_ground_floor ("W_standard_paneel_gf", "Standard windows with glass panel ground floor"),
	W_window_without_glass_panel_ground_floor ("W_standard window_gf", "Window without glass panel ground floor"),
	W_mullion_and_transom_upper_floors ("W_mullion and transom_uf", "Mullion and transom upper floors"),
	W_escape_door_upper_floors ("W_escape door_uf", "Window Escape door upper floors"),
	W_escape_door_upper_floors_large ("W_escape door_uf_large", "Window Escape door upper floors_large"),
	W_broad_corridor_window_and_door_upper_floors  ("W_broad corridor_uf", "Broad corridor window and door upper floors"),
	W_broad_corridor_window_and_door_ground_floors  ("W_broad corridor_gf", "Broad corridor window and door ground floor"),
	
	BP_concrete ("BP_concrete", "Concrete_baseplate_42cm"),
	C_concrete ("C_concrete", "concrete ceiling"),
	F_Bonded_Screed_33_base ("F_Bonded Screed_3.3_base", "Bonded_screed_3_3_technology_laboratory_34cm"),
	F_Carpet_23 ("F_Carpet_2.3", "Carpet_2_3_conference_room_36cm"),
	F_Industrial_Screed_35 ("F_Industrial Screed_3.5", "Industrial_screed_3_5_chemical_bacteriological_laboratory_35cm"),
	F_Industrial_Screed_33_uf ("F_Industrial Screed_3.3_uf", "Industrial_screed_35cm"),  // <--
	F_Industrial_Screed_41 ("F_Industrial Screed_4.1", "Industrial_screed_4_1_stockroom_110cm"),
	F_Industrial_Screed_42 ("F_Industrial Screed_4.2", "Industrial_screed_4_2_library_archive_rooms_46cm"),
	F_Industrial_Screed_73 ("F_Industrial Screed_7.3", "Industrial_screed_7_3_storage_room_36cm"),
	F_Linoleum_21 ("F_Linoleum_2.1", "Linoleum_2_1_office_room_35cm"),
	F_Linoleum_28 ("F_Linoleum_2.8", "Linoleum_2_8_room_for_office_supply_36cm"),
	F_Linoleum_29 ("F_Linoleum_2.9", "Linoleum_2_9_miscellaneous_office_36cm"),
	F_Linoleum_21_gf ("F_Linoleum_2.1_gf", "Linoleum_36cm_01"),
	F_Linoleum_54 ("F_Linoleum_5.4", "Linoleum_5_4_Library_room_36cm"),
	F_Linoleum_84 ("F_Linoleum_8.4", "Linoleum_8_4_electrical_power_supply_36cm"),
	F_Linoleum_91 ("F_Linoleum_9.1", "Linoleum_9_1_floor_hall_37cm"),
	F_Linoleum_76_b ("F_Linoleum_7.6_b", "Linoleum_on_doublefloor_36cm"),
	F_Linoleum_76 ("F_Linoleum_7.6", "Linoleum_on_doublefloor_7_6_room_for_technical_supply_101cm"),
	F_Linoleum_85 ("F_Linoleum_8.5", "Linoleum_on_doublefloor_8_5_telecommunication_37cm"),
	F_Natural_Rubber_56 ("F_Natural Rubber_5.6", "Natural_rubber_5_6_assembly_room_36cm"),
	F_Natural_Stone_27 ("F_Natural Stone_2.7", "Natural_stone_2_7_controllership_room_38cm"),
	F_Natural_Stone_92 ("F_Natural Stone_9.2", "Natural_stone_24cm"),
	F_Natural_Stone_99_base ("F_Natural Stone_9.9_base", "Natural_stone_28cm"),
	F_Natural_Stone_92_podest ("F_Natural Stone_9.2_podest", "Natural_stone_32cm"),
	F_Natural_Stone_86 ("F_Natural Stone_8.6", "Natural_stone_8_6_lift_and_conveying_machinery_24cm"),
//	F_Natural_Stone_92 ("F_Natural Stone_9.2", "Natural_stone_9_2_stairs_35cm"),
	F_Natural_Stone_99 ("F_Natural Stone_9.9", "Natural_stone_9_9_miscellaneous_public_thoroughfare_37cm"),
	F_Tiles_38 ("F_Tiles_3.8", "Tiles_3_8_kitchen_36cm"),
	F_Tiles_71_gf ("F_Tiles_7.1_gf", "Tiles_36cm_01"),
	F_Tiles_71_disabled ("F_Tiles_7.1_disabled", "Tiles_37cm"),
	F_Tiles_71 ("F_Tiles_7.1", "Tiles_7_1_sanitary_installations_35cm"),
	
	C_suspended_gypsum_ceiling ("C_suspended gypsum ceiling", "ceilingtype20"),
	C_suspended_alu_ceiling ("C_suspended alu ceiling", "ceilingtype30"),
	C_suspended_cooling_ceiling ("C_suspended cooling ceiling", "ceilingtype40"),
	
	R_extensive_planting_roof ("R_extensive planting roof", "rooftype 1")
	;
	
	private String name;
	private String ibkTemplate;
	public static final String SEARCH_PATTERN = "/Test/";
	
	private ConstructionTemplateMappingEnum(String name, String ibkTemplate) {
		this.name = name;
		this.ibkTemplate = ibkTemplate;
	}

	public static String getIbkTemplate(String ifcName) {
		ifcName = ifcName.toLowerCase();
		ConstructionTemplateMappingEnum[] mappings = values();
		for(ConstructionTemplateMappingEnum mapping : mappings) {
			String mName = mapping.name.toLowerCase();
			if(ifcName.matches(".*"+mName+".*")){ // contains the name
				return mapping.ibkTemplate;
			}
		}
		
		return null;
	}
}
