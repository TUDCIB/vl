
"""
purpose of this class: This class creates empty therakles project and handles its modification, data filling and simulation
based on submittet mat database (material), cons database (construction), win database (window), zone, climate, occupancy
institution: IBK, TUD
included functions: initializeProject(self), submitZoneSettings(self, zoneObject),
                    write_ProjectFile(self, therakles_project), start_Simulation(self, therakles_project , therakles_exe )
                    (plus internal supporting functions)
"""
                    
#user defined classes
from zone_object import ZoneObject
from ekpi import eKPI_101, eKPI_102, eKPI_103, eKPI_104, eKPI_111, eKPI_112, eKPI_121, eKPI_122, eKPI_131, eKPI_141, eKPI_142


# predefined python libs
import xml.dom.minidom as DOM
import subprocess
import time, sys
import os



class Therakles_project:

    #constructor has project- initializing function
    def __init__(self):
        
        #private attributes and variables: only for class- internal use, syntax: self.__XXX
        self.__therakles_xml_tree = DOM.EmptyNodeList()     #contains complete therakles xml tree structure if initialization was successful
        self.__theraklesProject_initialized = False         #True if initialization was successful, False if not                

        self.__matObjects = []                              #filled with material objects, not used, zone object is used instead of single elements
        self.__matIDs = []                                  #ids of material objects in database
        self.__matIDStart = 200                             #starting id for embedded materials

        self.__consObjects = []                              #filled with construction objects, zone object is used instead of single elements
        self.__consIDs = []                                  #ids ofconstruction objects in database
        self.__consIDStart = 200                             #starting id for embedded constructions

        self.__winObjects = []                              #filled with window objects, zone object is used instead of single elements
        self.__winIDs = []                                  #ids of window objects in database
        self.__winIDStart = 200                             #starting id for embedded window


        #shading not considered yet
        self.__shaIDStart = 200

        #key values available after initialization with zone object
        self.__zoneObjectSubmitted = False
        self.__height = 0.0
        self.__area = 0.0
        

        #protected attributes: don't modify externally, syntax: self._XXX
        #public attributes: syntax: self.XXX




        
    #purpose of this function:  Function initializes Therakles project file with default data
    #submitted variables:       none
    #return values:             none
    def initializeProject(self):

        #-----Create new xml document frame ---------------------------------------------------------------------------#
        imp = DOM.getDOMImplementation()

        #create document type, assign properties
        strRoomModelProject = "RoomModelProject"
        docType = imp.createDocumentType("RoomModelProject", "", "")
        rootTag = imp.createDocument("IBK",strRoomModelProject ,docType)
        rootTag.documentElement.setAttribute("Version","1.6")      
        
        #create first level tags
        tagRoom = rootTag.createElement("Room")
        tagEmbDB = rootTag.createElement("EmbeddedDataBase")

        #create second level tags inside "room"
        tagGeo = rootTag.createElement("Geometry")
        tagClim = rootTag.createElement("ClimateLocation")
        tagHeat = rootTag.createElement("HeatingModel")
        tagCool = rootTag.createElement("CoolingModel")
        tagVent = rootTag.createElement("NaturalVentilationModel")
        tagULoa = rootTag.createElement("UserLoads")
        tagSha = rootTag.createElement("ShadingControlModel")
        tagSim = rootTag.createElement("SimulationParameter")

        #create second level tags inside "embeddedDataBase"
        tagMat = rootTag.createElement("Materials")
        tagCT =  rootTag.createElement("ConstructionTypes")
        tagWT =  rootTag.createElement("WindowTypes")
        tagShT = rootTag.createElement("ShadingTypes")

        #-----geometry tags and attributes

        tagGeo_h = rootTag.createElement("Height")
        tagGeo_A = rootTag.createElement("Area")
        tagGeo_C = rootTag.createElement("Constructions")
        
        tagGeo_C_RC = rootTag.createElement("RoomConstruction")

        tagGeo_C_RC_or = rootTag.createElement("orientation")
        tagGeo_C_RC_in = rootTag.createElement("inclination")
        tagGeo_C_RC_Rui = rootTag.createElement("R_ue")
        tagGeo_C_RC_Rue = rootTag.createElement("R_ui")
        tagGeo_C_RC_betaI = rootTag.createElement("beta_i")
        tagGeo_C_RC_betaE = rootTag.createElement("beta_e")
        tagGeo_C_RC_a = rootTag.createElement("ad")
        tagGeo_C_RC_T = rootTag.createElement("zoneT")
        tagGeo_C_RC_RH = rootTag.createElement("zoneRH")
        tagGeo_C_RC_AW = rootTag.createElement("AW") #construction area
        tagGeo_C_RC_AF = rootTag.createElement("AF") #window area within construction
        tagGeo_C_RC_AWid = rootTag.createElement("constructionId")
        tagGeo_C_RC_AFid = rootTag.createElement("windowId")
        tagGeo_C_RC_Shid = rootTag.createElement("shadingTypeId")
        tagGeo_C_RC_t = rootTag.createElement("type")

        #-----climate tags and attributes
        #no child tags
        #tagClim.appendChild( rootTag.createTextNode("TRY2010_04_Jahr_00000K0_00081m") )
        tagClim.appendChild( rootTag.createTextNode("munich") )

        #-----heating model tags and attributes

        tagHeat.setAttribute("modelType","NONE")
        tagHeat.setAttribute("name", "")
        tagHeat_MP = rootTag.createElement("MaxPower")
        if (self.__createScheduleTag(rootTag, "C","ScheduleSetPoints") != False ):
            tagHeat_Sch = self.__createScheduleTag( rootTag, "C","ScheduleSetPoints")
            tagHeat.appendChild(tagHeat_MP)
            tagHeat.appendChild(tagHeat_Sch)

        #-----cooling model tags and attributes
            
        tagCool.setAttribute("modelType","NONE")
        tagCool.setAttribute("name","")
        tagCool_mP = rootTag.createElement("MaxPower")
        tagCool_SuT = rootTag.createElement("SupplyTemperature")
        tagCool_MaxSFR = rootTag.createElement("MaxSupplyFlowRate")
        tagCool_HR = rootTag.createElement("HeatRecoveryEfficiency")
        tagCool_SP = rootTag.createElement("HeatRecoverySetpoint")

        #set point schedules are both created and connected for cooling model
        tagCool_Sch = rootTag.createElement("ScheduleSetPoints")
        tagCool_Sch.setAttribute("type", "CONSTANT")
        tagCool_Sch_CV =  rootTag.createElement("ConstantValue")
        tagCool_SchH = rootTag.createElement("ScheduleHumiditySetPoints")
        tagCool_SchH.setAttribute("type", "CONSTANT")
        tagCool_SchH_CV =  rootTag.createElement("ConstantValue")      
        tagCool_SchV = rootTag.createElement("ScheduleVentilationRates")
        tagCool_SchV.setAttribute("type", "CONSTANT")
        tagCool_SchV_CV =  rootTag.createElement("ConstantValue")       

        #-----ventilation model tags and attributes
        
        tagVent.setAttribute("name","")
        tagVent_SchN = rootTag.createElement("ScheduleAirChangeRates")
        tagVent_SchN.setAttribute("type", "CONSTANT")
        tagVent_SchN_CV =  rootTag.createElement("ConstantValue")

        #-----user loads tags and attributes
        
        tagULoa.setAttribute("name","")
        tagULoa_PL = rootTag.createElement("PersonLoad")
        tagULoa_SchO = rootTag.createElement("ScheduleOccupancy")
        tagULoa_SchO.setAttribute("type", "CONSTANT")
        tagULoa_SchO_CV =  rootTag.createElement("ConstantValue")
        tagULoa_SchE = rootTag.createElement("ScheduleEquipmentLoad")
        tagULoa_SchE.setAttribute("type", "CONSTANT")
        tagULoa_SchE_CV =  rootTag.createElement("ConstantValue")

        #-----shading model tags and attributes
        
        tagSha.setAttribute("modelType","NONE")
        tagSha.setAttribute("name","")
        tagSha_RHor = rootTag.createElement("MaxRadiationIntensityHorizontal")
        tagSha_RHor.appendChild( rootTag.createTextNode("250.0") )
        tagSha_RW = rootTag.createElement("MaxRadiationIntensityWest")
        tagSha_RW.appendChild( rootTag.createTextNode("250.0") )
        tagSha_RS = rootTag.createElement("MaxRadiationIntensitySouth")
        tagSha_RS.appendChild( rootTag.createTextNode("250.0") )
        tagSha_RN = rootTag.createElement("MaxRadiationIntensityNorth")
        tagSha_RN.appendChild( rootTag.createTextNode("250.0") )
        tagSha_RE = rootTag.createElement("MaxRadiationIntensityEast")
        tagSha_RE.appendChild( rootTag.createTextNode("250.0") )
        tagSha_SchS = rootTag.createElement("ScheduleShadingDegree")
        tagSha_SchS.setAttribute("type", "CONSTANT")
        tagSha_SchS_CV =  rootTag.createElement("ConstantValue")
        tagSha_SchS_CV.appendChild( rootTag.createTextNode("1.0") )
        
        #-----simulation settings tags and attributes

        tagSim_Tin = rootTag.createElement("TInitial")
        tagSim_Tin.appendChild( rootTag.createTextNode("20.0") )
        tagSim_al = rootTag.createElement("Albedo")
        tagSim_al.appendChild( rootTag.createTextNode("0.2") )
        tagSim_ghc = rootTag.createElement("GammaHC")
        tagSim_ghc.appendChild( rootTag.createTextNode("0.5") )
        tagSim_gr = rootTag.createElement("GammaRad")
        tagSim_gr.appendChild( rootTag.createTextNode("0.5") )
        tagSim_c = rootTag.createElement("AdditionalMassHeatCapacity")
        tagSim_c.appendChild( rootTag.createTextNode("1000.0") )
        tagSim_m =rootTag.createElement("AdditionalMass")
        tagSim_m.appendChild( rootTag.createTextNode("0.0") )
        
      

        #----- Connect created nodes -------------------------------------------------------------#

        #----- Geometry-------------------#
        
        tagGeo_C_RC.appendChild(tagGeo_C_RC_or)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_in)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_Rui)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_Rue)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_betaI)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_betaE)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_a)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_T)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_RH)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_AW)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_AF)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_AWid)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_AFid)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_Shid)
        tagGeo_C_RC.appendChild(tagGeo_C_RC_t)
        
        
        tagGeo_C.appendChild(tagGeo_C_RC)
        
        tagGeo.appendChild(tagGeo_h)
        tagGeo.appendChild(tagGeo_A)
        tagGeo.appendChild(tagGeo_C)
        
        #----- Heating Model -------------------#

        #empty
        
        #----- Cooling Model -------------------#
        
        tagCool_Sch.appendChild( tagCool_Sch_CV )
        tagCool_SchH.appendChild( tagCool_SchH_CV )
        tagCool_SchV.appendChild( tagCool_SchV_CV )
        
        tagCool.appendChild(tagCool_mP)
        tagCool.appendChild(tagCool_SuT)
        tagCool.appendChild(tagCool_MaxSFR)
        tagCool.appendChild(tagCool_HR)
        tagCool.appendChild(tagCool_SP)
        tagCool.appendChild(tagCool_Sch)
        tagCool.appendChild(tagCool_SchH)
        tagCool.appendChild(tagCool_SchV)

        #----- Ventilation Model -------------------#

        tagVent_SchN.appendChild(tagVent_SchN_CV)
        tagVent.appendChild(tagVent_SchN)

        #----- User Loads Model -------------------#
        
        tagULoa_SchO.appendChild(tagULoa_SchO_CV)
        tagULoa_SchE.appendChild(tagULoa_SchE_CV)
        tagULoa.appendChild(tagULoa_PL)
        tagULoa.appendChild(tagULoa_SchO)
        tagULoa.appendChild(tagULoa_SchE)

        #----- Shading Model -------------------#

        tagSha_SchS.appendChild(tagSha_SchS_CV)
        tagSha.appendChild(tagSha_RHor)
        tagSha.appendChild(tagSha_RW)
        tagSha.appendChild(tagSha_RS)
        tagSha.appendChild(tagSha_RN)
        tagSha.appendChild(tagSha_RE)
        tagSha.appendChild(tagSha_SchS)
                
        #----- Simulation Settings -------------------#
        
        tagSim.appendChild(tagSim_Tin)
        tagSim.appendChild(tagSim_al)
        tagSim.appendChild(tagSim_ghc)
        tagSim.appendChild(tagSim_gr)
        tagSim.appendChild(tagSim_c)
        tagSim.appendChild(tagSim_m)

        #----- Final Structure -------------------#

        tagRoom.appendChild(tagGeo)
        tagRoom.appendChild(tagClim)
        tagRoom.appendChild(tagHeat)
        tagRoom.appendChild(tagCool)
        tagRoom.appendChild(tagVent)
        tagRoom.appendChild(tagULoa)
        tagRoom.appendChild(tagSha)
        tagRoom.appendChild(tagSim)

        tagEmbDB.appendChild(tagMat)
        tagEmbDB.appendChild(tagCT)
        tagEmbDB.appendChild(tagWT)
        tagEmbDB.appendChild(tagShT)
        

        rootTag.documentElement.appendChild( tagRoom )
        rootTag.documentElement.appendChild( tagEmbDB )
        

        #----- Publish created tree -----#
        self.__therakles_xml_tree = rootTag 
        return True



    #purpose of this function:  Function assigns zone data based on submitted zone object  
    #submitted variables:       zone object
    #return values:                 
    def submitZoneSettings(self, zoneObject):
        
        #zone height                   
        heightValue = self.__therakles_xml_tree.createTextNode(str(zoneObject.height))
        self.__therakles_xml_tree.getElementsByTagName("Height")[0].appendChild( heightValue )
        self.__height = float(zoneObject.height)

        #zone area
        areaValue = self.__therakles_xml_tree.createTextNode(str(zoneObject.area))
        self.__therakles_xml_tree.getElementsByTagName("Area")[0].appendChild( areaValue )
        self.__area = float(zoneObject.area)

        #get root node
        motherTag = self.__therakles_xml_tree.getElementsByTagName("Constructions")[0]

        #call update functions for all constructions and materials in this project 
        zoneObject.update_IncludedObjectsList()

        ##-------START CHECK FUNCTION----------------------------------------##
        
        consObjectList = zoneObject.return_ConsList()
        #print("Number of construction objects before reduction is %d." % len(consObjectList))
        allCons_area = 0.0
        for cons in consObjectList:
            allCons_area += float(cons.return_area())
        #print("Area of all constructions before reduction is: %.2f" % allCons_area )
        
        zoneObject.merge_Constructions()
        
        newConsObjectList = zoneObject.return_ConsList()
        #print("Number of construction objects after reduction is %d." % len(newConsObjectList))
        newCons_area = 0.0
        for cons in newConsObjectList:
            newCons_area += float(cons.return_area())
        #print("Area of all constructions after reduction is: %.2f" % allCons_area )
        
        ##-------END CHECK FUNCTION----------------------------------------##
        
        #counter equals number of tags
        counter = 0        
        for element in zoneObject.constructions:

            #remove empty tag which was created in initialization
            if (counter == 0):
                oldTag = self.__therakles_xml_tree.getElementsByTagName("RoomConstruction")[0]
                motherTag.removeChild(oldTag)


            #create new tag frame for each construction
            tagGeo_C_RC = self.__therakles_xml_tree.createElement("RoomConstruction")

            tagGeo_C_RC_or = self.__therakles_xml_tree.createElement("orientation")
            tagGeo_C_RC_in = self.__therakles_xml_tree.createElement("inclination")
            tagGeo_C_RC_Rui = self.__therakles_xml_tree.createElement("R_ue")
            tagGeo_C_RC_Rue = self.__therakles_xml_tree.createElement("R_ui")
            tagGeo_C_RC_betaI = self.__therakles_xml_tree.createElement("beta_i")
            tagGeo_C_RC_betaE = self.__therakles_xml_tree.createElement("beta_e")
            tagGeo_C_RC_a = self.__therakles_xml_tree.createElement("ad")
            tagGeo_C_RC_T = self.__therakles_xml_tree.createElement("zoneT")
            tagGeo_C_RC_RH = self.__therakles_xml_tree.createElement("zoneRH")
            tagGeo_C_RC_AW = self.__therakles_xml_tree.createElement("AW") 
            tagGeo_C_RC_AF = self.__therakles_xml_tree.createElement("AF") 
            tagGeo_C_RC_AWid = self.__therakles_xml_tree.createElement("constructionId")
            tagGeo_C_RC_AFid = self.__therakles_xml_tree.createElement("windowId")
            tagGeo_C_RC_Shid = self.__therakles_xml_tree.createElement("shadingTypeId")
            tagGeo_C_RC_t = self.__therakles_xml_tree.createElement("type")
            
            #orientation
            valueTagOrient = self.__therakles_xml_tree.createTextNode( str(element.return_orient()) )
            tagGeo_C_RC_or.appendChild( valueTagOrient )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_or )

            #inclination
            valueTagInclin = self.__therakles_xml_tree.createTextNode( str(element.return_inclin()) )
            tagGeo_C_RC_in.appendChild( valueTagInclin )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_in )
            
            #R_ui
            valueTagRui = self.__therakles_xml_tree.createTextNode( str(element.return_rSurfIn()) )
            tagGeo_C_RC_Rui.appendChild( valueTagRui )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_Rui )
            
            #R_ue
            valueTagRue = self.__therakles_xml_tree.createTextNode( str(element.return_rSurfOut()) )
            tagGeo_C_RC_Rue.appendChild( valueTagRue )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_Rue )
            
            #Beta_i
            valueTagBetaIn = self.__therakles_xml_tree.createTextNode( str(element.return_betaInside()) )
            tagGeo_C_RC_betaI.appendChild( valueTagBetaIn )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_betaI)

            #Beta_e
            valueTagBetaOut = self.__therakles_xml_tree.createTextNode( str(element.return_betaOutside()) )
            tagGeo_C_RC_betaE.appendChild( valueTagBetaOut )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_betaE )

            #a
            valueTagA = self.__therakles_xml_tree.createTextNode( str(element.return_aOutside()) )
            tagGeo_C_RC_a.appendChild( valueTagA )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_a )
            
            #Zone starting temperature and relative humidity are taken as default values
            rTempValue = self.__therakles_xml_tree.createTextNode("20.0")
            tagGeo_C_RC_T.appendChild( rTempValue )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_T )
            relHumValue = self.__therakles_xml_tree.createTextNode("60.0")
            tagGeo_C_RC_RH.appendChild( relHumValue )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_RH )
            
            #area
            valueTagArea = self.__therakles_xml_tree.createTextNode( str(element.return_area()) )
            tagGeo_C_RC_AW.appendChild( valueTagArea )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_AW )
            
            #window area
            if (element.return_embObject(0) != False ):
                winArea = element.return_embObject(0).area
            else:
                winArea = 0.0
            valueTagWinArea = self.__therakles_xml_tree.createTextNode( str( winArea ) )
            tagGeo_C_RC_AF.appendChild( valueTagWinArea )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_AF )
            
            #construction id
            #raise construction counter and keep value in member list
            consCounterValue = self.__consIDStart + ( len(self.__consIDs) + 1 ) 
            self.__consIDs.append( consCounterValue )
            valueTagConsID = self.__therakles_xml_tree.createTextNode( str( consCounterValue ) )
            tagGeo_C_RC_AWid.appendChild( valueTagConsID )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_AWid )
            
            #window id
            if ( element.return_embObject(0) != False):
                winCounterValue = self.__winIDStart + ( len(self.__winIDs) + 1)
                self.__winIDs.append( winCounterValue )
                valueTagWinID = self.__therakles_xml_tree.createTextNode( str( winCounterValue ) )
            else:
                #no window included in construction, any id can be written
                noWindowId = 0
                valueTagWinID = self.__therakles_xml_tree.createTextNode( str( noWindowId ) )
            tagGeo_C_RC_AFid.appendChild( valueTagWinID )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_AFid )

            #shading type id
            noShadingId = 0
            valueTagShaID = self.__therakles_xml_tree.createTextNode( str( noShadingId ) )
            tagGeo_C_RC_Shid.appendChild( valueTagShaID )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_Shid )

            #type
            valueTagType = self.__therakles_xml_tree.createTextNode( str( element.return_type() ) )
            tagGeo_C_RC_t.appendChild( valueTagType )
            tagGeo_C_RC.appendChild( tagGeo_C_RC_t )

            #raise counter
            counter = counter + 1

            #append new construction
            motherTag.appendChild( tagGeo_C_RC )
            
            
        ##--------------- Utilization Profiles: Heating Model from weekly schedule --------------------------------------------##

        #create new heating model tags
        newHeatModelNode = self.__therakles_xml_tree.createElement("HeatingModel")
        newHeatPowerNode = self.__therakles_xml_tree.createElement("MaxPower")
        newHeatScheduleNode = self.__createIntScheduleTag( "DW", "ScheduleSetPoints")

        #get values
        WeekDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_HeatScheduleObject().return_WeekDayProfileString(" "))
        WeekEndDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_HeatScheduleObject().return_WeekEndProfileString(" "))
        WeekEndDaysTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_HeatScheduleObject().return_WeekEndDaysString(" "))
        maxPTextNode = self.__therakles_xml_tree.createTextNode(str(zoneObject.return_heatMaxP()))
        
        #assign attributes
        newHeatModelNode.setAttribute("name", "SimpleHeating")
        newHeatModelNode.setAttribute("modelType", zoneObject.return_heatMode("T"))
      
        #adapt schedule values
        newHeatScheduleNode.getElementsByTagName("WeekDaySchedule")[0].appendChild( WeekDayScheduleTextNode )
        newHeatScheduleNode.getElementsByTagName("WeekEndSchedule")[0].appendChild( WeekEndDayScheduleTextNode )
        newHeatScheduleNode.getElementsByTagName("WeekEndDays")[0].appendChild( WeekEndDaysTextNode )

        #create new structure
        newHeatPowerNode.appendChild( maxPTextNode )
        newHeatModelNode.appendChild( newHeatPowerNode )
        newHeatModelNode.appendChild( newHeatScheduleNode )

        
        #check if model should be addded or replaced
        if( len(self.__therakles_xml_tree.getElementsByTagName("HeatingModel")) > 0):
            oldHeatModelNode = self.__therakles_xml_tree.getElementsByTagName("HeatingModel")[0]
            parentNode = oldHeatModelNode.parentNode
            parentNode.replaceChild( newHeatModelNode, oldHeatModelNode )
        else:
            print("TheraklesObject: No heating model initialized in therakles project file structure.")
            
           
 ##--------------- Utilization Profiles: Cooling Model from weekly schedule --------------------------------------------##
        
        #create new heating model tags
        newCoolModelNode = self.__therakles_xml_tree.createElement("CoolingModel")
        newCoolPowerNode = self.__therakles_xml_tree.createElement("MaxPower")
        newCoolSupplyTemNode = self.__therakles_xml_tree.createElement("SupplyTemperature")
        newCoolSupplyFlowRateNode = self.__therakles_xml_tree.createElement("MaxSupplyFlowRate")
        newCoolHeatRecEffNode = self.__therakles_xml_tree.createElement("HeatRecoveryEfficiency")
        newCoolHeatRecSetNode = self.__therakles_xml_tree.createElement("HeatRecoverySetpoint")
        newCoolScheduleNode = self.__createIntScheduleTag( "DW", "ScheduleSetPoints")
        newCoolHumScheduleNode = self.__createIntScheduleTag( "C", "ScheduleHumiditySetPoints")
        newCoolVentScheduleNode = self.__createIntScheduleTag( "C", "ScheduleVentilationRates")

        #get values
        Cool_WeekDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_CoolScheduleObject().return_WeekDayProfileString(" "))
        Cool_WeekEndDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_CoolScheduleObject().return_WeekEndProfileString(" "))
        Cool_WeekEndDaysTextNode = self.__therakles_xml_tree.createTextNode(zoneObject.return_CoolScheduleObject().return_WeekEndDaysString(" "))
        Cool_maxPTextNode = self.__therakles_xml_tree.createTextNode(str(zoneObject.return_coolMaxP()))
        
        #assign attributes
        newCoolModelNode.setAttribute("name", "SimpleProportionalCoolingModel")
        newCoolModelNode.setAttribute("modelType", zoneObject.return_coolMode("T"))
      
        #adapt schedule values
        newCoolHumScheduleNode.getElementsByTagName("ConstantValue")[0].appendChild( self.__therakles_xml_tree.createTextNode("0.0") )
        newCoolVentScheduleNode.getElementsByTagName("ConstantValue")[0].appendChild( self.__therakles_xml_tree.createTextNode("0.0") )
        newCoolScheduleNode.getElementsByTagName("WeekDaySchedule")[0].appendChild( Cool_WeekDayScheduleTextNode )
        newCoolScheduleNode.getElementsByTagName("WeekEndSchedule")[0].appendChild( Cool_WeekEndDayScheduleTextNode )
        newCoolScheduleNode.getElementsByTagName("WeekEndDays")[0].appendChild( Cool_WeekEndDaysTextNode )

        #other values
        newCoolSupplyTemNode.appendChild( self.__therakles_xml_tree.createTextNode("0.0") )
        newCoolSupplyFlowRateNode.appendChild( self.__therakles_xml_tree.createTextNode("0.0") )
        newCoolHeatRecEffNode.appendChild( self.__therakles_xml_tree.createTextNode("0.0") )
        newCoolHeatRecSetNode.appendChild( self.__therakles_xml_tree.createTextNode("0.0") )

        #create new structure
        newCoolPowerNode.appendChild( Cool_maxPTextNode )
        newCoolModelNode.appendChild( newCoolPowerNode )
        newCoolModelNode.appendChild(newCoolSupplyTemNode)
        newCoolModelNode.appendChild(newCoolSupplyFlowRateNode )
        newCoolModelNode.appendChild(newCoolHeatRecEffNode)
        newCoolModelNode.appendChild(newCoolHeatRecSetNode)
        newCoolModelNode.appendChild( newCoolScheduleNode )
        newCoolModelNode.appendChild(newCoolHumScheduleNode)
        newCoolModelNode.appendChild(newCoolVentScheduleNode)

        
        #check if model should be addded or replaced
        if( len(self.__therakles_xml_tree.getElementsByTagName("CoolingModel")) > 0):
            oldCoolModelNode = self.__therakles_xml_tree.getElementsByTagName("CoolingModel")[0]
            parentNode = oldCoolModelNode.parentNode
            parentNode.replaceChild( newCoolModelNode, oldCoolModelNode )
        else:
            print("TheraklesObject: No cooling model initialized in therakles project file structure.")


 ##--------------- Utilization Profiles: Ventilation Model from weekly schedule --------------------------------------------##


        #create new ventilation rate tag
        newVentModelNode = self.__therakles_xml_tree.createElement("NaturalVentilationModel")
        newVentScheduleNode = self.__createIntScheduleTag( "DW", "ScheduleAirChangeRates")

        #get values
        VentSchedule = zoneObject.return_VentScheduleObject()
        Vent_WeekDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(VentSchedule.return_WeekDayProfileString(" "))
        Vent_WeekEndDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(VentSchedule.return_WeekEndProfileString(" "))
        Vent_WeekEndDaysTextNode = self.__therakles_xml_tree.createTextNode(VentSchedule.return_WeekEndDaysString(" "))
        
        #assign attributes
        newVentModelNode.setAttribute("name", "NatVentilationAirChangeRates")
      
        #adapt schedule values
        newVentScheduleNode.getElementsByTagName("WeekDaySchedule")[0].appendChild( Vent_WeekDayScheduleTextNode )
        newVentScheduleNode.getElementsByTagName("WeekEndSchedule")[0].appendChild( Vent_WeekEndDayScheduleTextNode )
        newVentScheduleNode.getElementsByTagName("WeekEndDays")[0].appendChild( Vent_WeekEndDaysTextNode )

        #create new structure
        newVentModelNode.appendChild( newVentScheduleNode )

        
        #check if model should be addded or replaced
        if( len(self.__therakles_xml_tree.getElementsByTagName("NaturalVentilationModel")) > 0):
            oldVentModelNode = self.__therakles_xml_tree.getElementsByTagName("NaturalVentilationModel")[0]
            parentNode = oldVentModelNode.parentNode
            parentNode.replaceChild( newVentModelNode, oldVentModelNode )
        else:
            print("TheraklesObject: No ventilation model initialized in therakles project file structure.")    


  ##--------------- Utilization Profiles: Equipment Loads Model from weekly schedule --------------------------------------------##

        
        #create new equipment loads tag
        newLoadsModelNode = self.__therakles_xml_tree.createElement("UserLoads")
        #assign attribute
        newLoadsModelNode.setAttribute("name", "UsageAreaRelatedEquipLoads")
        
        newPersLoadsNode = self.__therakles_xml_tree.createElement("PersonLoad")        
        newLoadsOccScheduleNode = self.__createIntScheduleTag( "C", "ScheduleOccupancy")
        newLoadsScheduleNode = self.__createIntScheduleTag( "DW", "ScheduleEquipmentLoad")

        
        #get values
        EquipLoadsSchedule = zoneObject.return_EquipScheduleObject()
        Equip_WeekDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(EquipLoadsSchedule.return_WeekDayProfileString(" "))
        Equip_WeekEndDayScheduleTextNode = self.__therakles_xml_tree.createTextNode(EquipLoadsSchedule.return_WeekEndProfileString(" "))
        Equip_WeekEndDaysTextNode = self.__therakles_xml_tree.createTextNode(EquipLoadsSchedule.return_WeekEndDaysString(" "))

        #other values
        EquipPersonLoadTextNode = self.__therakles_xml_tree.createTextNode("80.0")
        EquipOccScheduleTextNode = self.__therakles_xml_tree.createTextNode("0.0")
      
        #adapt schedule values
        newPersLoadsNode.appendChild(EquipPersonLoadTextNode)
        newLoadsOccScheduleNode.getElementsByTagName("ConstantValue")[0].appendChild( EquipOccScheduleTextNode )
        newLoadsScheduleNode.getElementsByTagName("WeekDaySchedule")[0].appendChild( Equip_WeekDayScheduleTextNode )
        newLoadsScheduleNode.getElementsByTagName("WeekEndSchedule")[0].appendChild( Equip_WeekEndDayScheduleTextNode )
        newLoadsScheduleNode.getElementsByTagName("WeekEndDays")[0].appendChild( Equip_WeekEndDaysTextNode )


        #create new structure
        newLoadsModelNode.appendChild( newPersLoadsNode )
        newLoadsModelNode.appendChild( newLoadsOccScheduleNode )
        newLoadsModelNode.appendChild( newLoadsScheduleNode )

        
        #check if model should be addded or replaced
        if( len(self.__therakles_xml_tree.getElementsByTagName("UserLoads")) > 0):
            oldLoadsModelNode = self.__therakles_xml_tree.getElementsByTagName("UserLoads")[0]
            parentNode = oldLoadsModelNode.parentNode
            parentNode.replaceChild( newLoadsModelNode, oldLoadsModelNode )
        else:
            print("TheraklesObject: No ventilation model initialized in therakles project file structure.")



            
        ##---------------Embedded data base--------------------------------------------## 
        
        if( len(zoneObject.return_MatList()) > 0):
            
            for material in zoneObject.return_MatList():
                
                #create counter 
                matCounterValue = self.__matIDStart + ( len(self.__matIDs) + 1 ) 
                self.__matIDs.append( matCounterValue )
                
                #set id  of current layer material in material object
                material.set_matId(matCounterValue)
        
        #call function which creates embedded data base objects based on written object id lists
        self.__createEmbDB(zoneObject)

        #init info flag
        self.__zoneObjectSubmitted = True


        #function end
        return True




    

    #purpose of this function:  Function creates new Therakles project file, call after initialization and modification
    #submitted variables:       therakles_project > complete file path and name of the new file
    #return values:             false > file writing wasn't successful
    #                           true > file was created     
    def write_ProjectFile(self, therakles_project):
        
        #create output file object
        outputFileObject = open( therakles_project , "w")
        #write modified xml output
        try:
            #output = self.__therakles_xml_tree.toprettyxml() #submitted arguments: (indent="\t",newl="\n",encoding="utf-8")--> causes error
            outputFileContString = self.__therakles_xml_tree.toprettyxml().replace("?xml version=\"1.0\" ?","?xml version=\"1.0\" encoding=\"utf-8\"?")
            outputFileObject.write( outputFileContString )
            outputFileObject.close()
        except Exception:
            print("Could not save Therakles project file: %s." % (therakles_project))
            return False
        else:
            return True



    #purpose of this function:  Function starts Therakles simulation and returns True when it is finished 
    #submitted variables:       therakles_project > complete file path and name of the project file
    #                           therakles_exe > complete file path and name of therakles.exe
    #return values:             false > simulation failed
    #                           true > simulation started     
    def start_Simulation(self, therakles_project , therakles_exe ):
        try:
            #start execution 
            executionProcess = subprocess.Popen( [therakles_exe, therakles_project])

            #check status of simulation process: wait as long as Therakles is running
            #print("Therakles is simulating with solver: %s ...." % (therakles_exe) )

            #check if execution is terminated, can be .wait() or .poll()
            timeOut = 5.0 
            while executionProcess.poll() == None:
                time.sleep( timeOut ) 

            #finished
            return True              

        except Exception:
            return False


        
    #purpose of this function:  Evaluates Therakles output files, creates list with eKPI values
    #submitted variables:       none
    #                           list of eKPI objects
    def start_ResultsAnalysis( self, TheraklesInputFileNamePath, zoneArea ):

        #list of eKPI- objects
        eKPI_list = []
        
        #define file names
        TheraklesInputFilePath = TheraklesInputFileNamePath.replace(".rmxml", "")
        resultsFolder = TheraklesInputFilePath + os.sep + "results" + os.sep
        fluxIntFileName = resultsFolder + "flux_integrals.tsv"
        fluxesFileName = resultsFolder + "fluxes.tsv"
        statesFileName = resultsFolder + "states.tsv"
        
        try:
            #fluxesFileObject = open( fluxesFileName )
            statesFileObject = open( statesFileName )
            fluxIntFileObject = open( fluxIntFileName )
        except Exception:
            print("TheraklesProject: Therakles output files for project %s can't be opened. Please check file location." % (TheraklesInputFileNamePath))
            return False
        else:            
            #get header and last line analysis of fluxes_integrals (cumulated values)
            fluxInt_Lines = fluxIntFileObject.readlines()

            #init index values for required output
            index_natVent = 1
            index_heatLoads = 3
            index_cooLoads = 4
            index_equipLoads = 6
            index_traLoads = 9
            index_solLoads = 13
            noRequiredValues = 6

            #check if we have correct file structure: search for index values in first line
            header_fluxInt = fluxInt_Lines[0]
            counter_noRV = 0
            counter_el = 0
            for keyWord in header_fluxInt.split("\t"):
                if( "Natural ventilation heat loss/gain" in keyWord):
                    counter_noRV += 1
                    index_natVent = counter_el
                elif("Heating loads" in keyWord):
                    counter_noRV += 1
                    index_heatLoads = counter_el
                elif("Active cooling loads" in keyWord):
                    counter_noRV += 1
                    index_cooLoads = counter_el
                elif("Equipment loads" in keyWord):
                    counter_noRV += 1
                    index_equipLoads = counter_el
                elif("Total transmission loads through inside of all walls" in keyWord):
                    counter_noRV += 1
                    index_traLoads = counter_el
                elif("Radiation loads through all windows" in keyWord):
                    counter_noRV += 1
                    index_solLoads = counter_el
                counter_el += 1

            #compare found and searched key word number
            if (counter_noRV != noRequiredValues):
                print("One or several key words were not found in %s file. Please check file structure." % fluxIntFileName )

            #init and get cumulated values from last line
            lastLineValues = fluxInt_Lines[ len(fluxInt_Lines)-2 ].split("\t")
            val_natVent = float( lastLineValues[index_natVent])
            val_heatLoads = float( lastLineValues[index_heatLoads])
            val_cooLoads = float( lastLineValues[index_cooLoads])
            val_equipLoads = float( lastLineValues[index_equipLoads])
            val_traLoads = float( lastLineValues[index_traLoads])
            val_solLoads = float( lastLineValues[index_solLoads])

            #net energy
            equipE_KPI = eKPI_102( "FluxInt_Therakles", val_equipLoads, (1.0 / float(zoneArea)) , "kWh/m2a") #keep equipment load object for primary energy calc.
            
            eKPI_list.append( eKPI_101( "FluxInt_Therakles", val_natVent, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( equipE_KPI )
            eKPI_list.append( eKPI_103( "FluxInt_Therakles", val_traLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_104( "FluxInt_Therakles", val_solLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_111( "FluxInt_Therakles", val_heatLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_112( "FluxInt_Therakles", val_cooLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))


            #final energy
            heatFinE_KPI = eKPI_121( "FluxInt_Therakles", val_heatLoads, (1.0 / float(zoneArea)) , "kWh/m2a")
            coolFinE_KPI = eKPI_122( "FluxInt_Therakles", val_cooLoads, (1.0 / float(zoneArea)) , "kWh/m2a")
            
            eKPI_list.append( heatFinE_KPI )
            eKPI_list.append( coolFinE_KPI )
            

            #primary energy
            heatFinE_value = heatFinE_KPI.return_result()
            coolFinE_value = coolFinE_KPI.return_result()
            equipE_value = equipE_KPI.return_result()

            finalHeatEnergy = heatFinE_value
            finalElectEn = coolFinE_value + equipE_value
            
            eKPI_list.append( eKPI_131( finalElectEn, finalHeatEnergy, "kWh/m2a"))

            #comfort values
            linesList_File = statesFileObject.readlines()
            eKPI_list.append( eKPI_141( "StatesFile_Therakles" , linesList_File , 1.0 , "degC"))
            eKPI_list.append( eKPI_142( "StatesFile_Therakles" , linesList_File ))                             


            #close file objects
            fluxIntFileObject.close()
            statesFileObject.close()
                              

        return eKPI_list

        
    #-------------------------------Access functions----------------------------------------------------------#
    def return_netZoneArea(self):
        if( self.__zoneObjectSubmitted == False):
            print("Area value is not available, init zone object firstly.")
        return float(self.__area)
    
    def return_zoneVolume(self):
        if( self.__zoneObjectSubmitted == False):
            print("Area value is not available, init zone object firstly.")
        return (float(self.__height)*float(self.__area))        
        


    
    #-------------------------------Supporting functions----------------------------------------------------------#       
    #purpose of this function:  Creates schedule type tag entry for given document and type
    #submitted variables:       Document--> xml tree struction document
    #                           str_type--> string which defines schedule type, allowed are: "C" (CONSTANT), "DW" (DAILY_WEEK), TODO: continue
    #                           str_name--> string which holds specific schedule name, d.g. "ScheduleSetPoints", "ScheduleShadingDegree", etc.
    #return values:             xml tag to be added in the tree
    def __createScheduleTag(self, Document, str_type, str_name):
        try:
            newTag = Document.createElement(str_name)
        except Exception:
            return False
        
        if(str_type == "C"):
            #create tags
            newTag.setAttribute("type","CONSTANT")
            newChildTag = Document.createElement("ConstantValue")
            #connect tags
            newTag.appendChild(newChildTag)
        elif(str_type == "DW"):
            #create tags
            newTag.setAttribute("type","DAILY_WEEK")
            newChildTag_1 = Document.createElement("WeekDaySchedule")
            newChildTag_2 = Document.createElement("WeekEndSchedule")
            newChildTag_3 = Document.createElement("WeekEndDays")
            #connect tags
            newTag.appendChild(newChildTag_1)
            newTag.appendChild(newChildTag_2)
            newTag.appendChild(newChildTag_3)

        #elif... other tag types

        return newTag



    #same as befor but assumes member self.__therakles_xml_tree as document object
    def __createIntScheduleTag(self, str_type, str_name):
        try:
            newTag = self.__therakles_xml_tree.createElement(str_name)
        except Exception:
            return False
        
        if(str_type == "C"):
            #create tags
            newTag.setAttribute("type","CONSTANT")
            newChildTag = self.__therakles_xml_tree.createElement("ConstantValue")
            #connect tags
            newTag.appendChild(newChildTag)
        elif(str_type == "DW"):
            #create tags
            newTag.setAttribute("type","DAILY_WEEK")
            newChildTag_1 = self.__therakles_xml_tree.createElement("WeekDaySchedule")
            newChildTag_2 = self.__therakles_xml_tree.createElement("WeekEndSchedule")
            newChildTag_3 = self.__therakles_xml_tree.createElement("WeekEndDays")
            #connect tags
            newTag.appendChild(newChildTag_1)
            newTag.appendChild(newChildTag_2)
            newTag.appendChild(newChildTag_3)

        #elif... other tag types

        return newTag



    #purpose of this function:  Creates material(embedded database) objects based on member lists embDB_
    #submitted variables:       zoneObject which includes material, construction and window data base
    #return values:             True if modification was successful, false if not
    def __createEmbDB(self, zoneObject ):
        try:
            #get root tag to add objects
            rootTagEmbDB = self.__therakles_xml_tree.getElementsByTagName("EmbeddedDataBase")[0]


            #-------------------------CREATE MAT DB-----------------------------#

            #get materials root tag 
            materialEmbDB = rootTagEmbDB.getElementsByTagName("Materials")[0]

            #counter used for id loops
            counter = 0
            #loop over all material objects in zone Object
            if( len(zoneObject.return_MatList()) > 0 ):
                for materialObject in zoneObject.return_MatList():
                    newMaterialTag = self.__therakles_xml_tree.createElement("Material")
                    newMaterialTag.setAttribute("id", str( self.__matIDs[counter] ) )
                    counter = counter + 1
                    #get current data from material object
                    values =  materialObject.return_listOfTheraklesValues()
                    newMaterialTag.setAttribute("category", str( values[0] ) )
                    newMaterialTag.setAttribute("name", ("de:" +  str( values[1]) + "|en:" + str( values[1] ) ) )
                    newMaterialTag.setAttribute("lambda", str( values[2] ) )
                    newMaterialTag.setAttribute("rho", str( values[3] ) )
                    newMaterialTag.setAttribute("cT", str( values[4] ) )
                    newMaterialTag.setAttribute("mu", str( values[5] ) )
                    newMaterialTag.setAttribute("w80", str( values[6] ) )
                    newMaterialTag.setAttribute("wsat", str( values[7] ) )
                    newMaterialTag.setAttribute("HPCM", str( values[8] ) )
                    newMaterialTag.setAttribute("TPCMLow", str( values[9] ) )                                           
                    materialEmbDB.appendChild( newMaterialTag )     
            else:
                print("No material database objects included in submitted zone object.")


            #-------------------------CREATE CONS DB-----------------------------#

            #get construction type root tag 
            constEmbDB = rootTagEmbDB.getElementsByTagName("ConstructionTypes")[0]
            
            counter = 0
            #loop over all material objects in zone Object
            if( len(zoneObject.return_ConsList()) > 0 ):
                for consObject in zoneObject.return_ConsList():

                    #create tag frame and id 
                    newConsTag = self.__therakles_xml_tree.createElement("ConstructionType")
                    newLayersTag = self.__therakles_xml_tree.createElement("Layers")
                    newConsTag.setAttribute("id", str( self.__consIDs[counter] ) )
                    counter = counter + 1
                    
                    #get current data from construction object
                    newConsTag.setAttribute("name", ("de:" +  consObject.return_name() + "|en:" + consObject.return_name()) )

                    #loop over layers, create and fill layer tags
                    for layer in consObject.return_listOfLayers():
                        newLayerTag = self.__therakles_xml_tree.createElement("Layer")
                        newLayerTag.setAttribute("materialId", str( layer.mat.return_matId()))
                        newLayerTag.setAttribute("d", str( layer.d ))
                        #append created tag to layers tag
                        newLayersTag.appendChild( newLayerTag )

                    #submit new tag structure
                    newConsTag.appendChild( newLayersTag )
                    constEmbDB.appendChild( newConsTag )
                    
            else:
                print("No construction database objects included in submitted zone object.")

            

            
            #-------------------------CREATE WINDOW DB-----------------------------#
        
            #get construction type root tag 
            winEmbDB = rootTagEmbDB.getElementsByTagName("WindowTypes")[0]

            counter = 0
            #loop over all construction objects in zone object
            for consObject in zoneObject.return_ConsList():

                #check if this construction includes an embedded object
                if( consObject.return_embObject(0) != False):

                    #get object
                    windowObject = consObject.return_embObject(0)
                    
                    #create tags
                    newWindowTag = self.__therakles_xml_tree.createElement("WindowType")
                    linSplTag = self.__therakles_xml_tree.createElement("LinearSpline")
                    xTag = self.__therakles_xml_tree.createElement("X")
                    yTag = self.__therakles_xml_tree.createElement("Y")
                    xValue = self.__therakles_xml_tree.createTextNode("0 10 20 30 40 50 60 70 80 90")
                    yValue = self.__therakles_xml_tree.createTextNode("100 100 100 100 98 94 86 69 37 0")

                    #assign attributes
                    if( counter < len(self.__winIDs) ):
                       idValString = str( self.__winIDs[counter])
                       counter += 1
                    else:
                       idValString = self.__winIDStart
 
                    newWindowTag.setAttribute("U", str(windowObject.uValue) )
                    newWindowTag.setAttribute("g", str(windowObject.shgc) )
                    newWindowTag.setAttribute("f", str(windowObject.fGlass) )
                    newWindowTag.setAttribute("name", ("de:" + "Fenstertyp" + "|en:" + "Window type") )
                    newWindowTag.setAttribute("id", idValString )
                    linSplTag.setAttribute("name", "RelativeSHGC")
                    xTag.setAttribute("unit", "Deg")
                    yTag.setAttribute("unit", "%")

                    #connect tags
                    xTag.appendChild(xValue)
                    yTag.appendChild(yValue)
                    linSplTag.appendChild(xTag)
                    linSplTag.appendChild(yTag)
                    newWindowTag.appendChild( linSplTag )

                    #submit tag 
                    winEmbDB.appendChild( newWindowTag )


            return True
        
        except Exception:
            print("Error occured while creating embedded object tags in therakles xml.")
            return False

  
            


            


