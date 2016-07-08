
"""
purpose of this class (sim ma): This class reads submitted simulation matrix xml file, holds all data and manages data access
institution: IBK, TUD
included functions: ...
"""
                    
# user defined classes
from material import Material
from zone_object import ZoneObject
from zone_object import SpaceType
from construction import ConstructionType
from construction import Construction, Layer, EmbeddedObject
from schedules import ScheduleType, DataType, Schedule

# predefined python libs
import xml.dom.minidom as DOM
import subprocess
import time
import os
import copy
import shutil
from enum import Enum
#from scripts.main_analyze import zonesOfInterest

# #---------------------SUPPORTING VARIANT TYPE AND DATA CLASS-------------------------------------------##

"""
purpose of this class: supports data type handling (enum)
"""
class SimMa_VariableTypes(Enum):

    WEATHERDATASET = 1            
    CONSTRUCTION = 2 
    WINDOW = 3
    MATERIAL = 4
    NONE = 5

"""
purpose of this class: supports simulation matrix combination handling 
"""
class SimMa_Combination(object):

    def __init__(self, str_combId, strDict_AssVar):

        self.__combId = str_combId  # individual string for identification of each version
        self.__combAssVar = strDict_AssVar  # dictionary with assignment id and variable version reference id for each modified variable

    def return_ID(self):
        return self.__combId

    def return_VariableAssignmentDict(self):
        return self.__combAssVar

# #---------------------MAIN CLASS SIMULATION MATRIX------------------------------------------##

class SimulationMatrix(object):
    # constructor has project- initializing function
    # input values:      filePathAndName --> simmatrix file path
    #
    def __init__(self, filePathAndName):
        # private attributes and variables
        self.__filePathAndName = filePathAndName
        self.__id = ""
        self.__type = ""
        self.__file_xml_tree = DOM.EmptyNodeList()  # includes complete xml tree structure (after initialization)

        # varied objects ---> not hold in this class, properties are requested seperately / for different implementation with spec. object type
        self.__combObjectList = []  # list with combination objects: SimMa_Comb
        self.__combKeyList = []  # list with strings of combination ids
        self.__zonesOfInterest = []
        # list with ifc keys of zones of interest

        # target directories ---> call define_resourceDirectories(...) to specify this
        self.__targetDir = os.path.abspath(".").replace("scripts", "project")
        self.__cliDir_T = os.path.join(self.__targetDir, "DB_Climate")
        self.__conDir_T = os.path.join(self.__targetDir, "DB_Constructions")       
        self.__matDir_T = os.path.join(self.__targetDir, "DB_Materials")
        self.__temDir_T = os.path.join(self.__targetDir, "DB_Templates")
        self.__binDir = os.path.abspath(".").replace("scripts", "bin")
        self.__resConAv = False
        self.__resConPathAndName = ""

        # call initial function which fills variables above
        self.__xmlFileInitialized = self.__parseFile(filePathAndName)

#---------------------SUPPORTING FUNCTIONS FOR SIMMA-------------------------------------------##

        
    # purpose of this function:  Function initializes simulation matrix file and interprets submitted elements
    # submitted variables:       input file name of simulation matrix xml
    # return values:             True if interpretation was successful, False if not
    def __parseFile(self, filePathAndName):
        # check submitted xml file structure
        try:
            self.__file_xml_tree = DOM.parse(filePathAndName)
        except Exception:
            
            print("SimulationMatrix:ParseFile: Error while interpreting xml file structure via DOM. Check syntax.")
            return False
        else:
            # general matrix properties (id, type)
            self.__id = self.__file_xml_tree.getElementsByTagName("SimulationMatrix")[0].getAttribute("id")
            self.__type = self.__file_xml_tree.getElementsByTagName("SimulationMatrix")[0].getAttribute("type")

            # combinations
            for combNode in self.__file_xml_tree.getElementsByTagName("Combination"):
                
                combID = combNode.getAttribute("id")
                combDict = {}
                
                for varNode in combNode.getElementsByTagName("Variant"):
                    varRef = varNode.getAttribute("VREF")
                    assRef = varNode.getAttribute("AREF")
                    combDict[varRef] = assRef

                # submit properties to comb object 
                combObject = SimMa_Combination(combID, combDict)

                self.__combObjectList.append(combObject)
                self.__combKeyList.append(combID)
                    # read ifc keys
            
            for ifcNode in self.__file_xml_tree.getElementsByTagName("Target"):
            
                if((ifcNode.getAttribute("type") == "Space") or (ifcNode.getAttribute("type") == "Zone")):
                
                    if(not self.__zonesOfInterest.__contains__(ifcNode.firstChild.nodeValue)):
                    
                        self.__zonesOfInterest.append(ifcNode.firstChild.nodeValue)

        return True

# #-----------------------------MODIFICATION FUNCTIONS--------------------------------------------------------------------------##

    # purpose of this function:  Function returns all defined versions / combinations included in simulation matrix
    # submitted variables:       none
    # return values:             list of strings with version/ combination keys
    def return_versionKeys(self):

        if(len(self.__combKeyList) == 0):
            print("SimulationMatrix: ReturnVersionKey: no combinations defined in simulation matrix. Please check submitted simulation matrix file firstly.")

        return self.__combKeyList

    # purpose of this function:  Function modifies zone object due to initialized sim matrix specification (by version key) and submitted default settings (in zone object)
    # submitted variables:       currentZoneObject --> default zone object with default settings which should be modified
    #                           key--> version/ combination key which is specified in simmatrix (request list of keys after initialization firstly)
    # return values:             modified zone object
    def return_modifiedZoneObject(self, currentZoneObject, key):

        # check initialization
        if (self.__xmlFileInitialized == False):
            
            print("SimulationMatrix:ReturnModifiedZoneObject: simulation matrix xml file must be initialized correctly before calling this function.")

        # copy submitted zone object
        newZoneObject = copy.deepcopy(currentZoneObject)

        # get list of variable reference ids
        varRefList = []
        
        for entry in self.__combObjectList:
            
            if(key == entry.return_ID()):
                
                varRefList = list(entry.return_VariableAssignmentDict().keys())
                
        # check database directories and get name list of available entries
        
        # loop over variable id list and go through xml structure to find the referenced element
        for varRef in varRefList:
            # go through xml structure to find referenced id in different types of variable variants
            # check if this is a construction modification reference
            for conTypeNode in self.__file_xml_tree.getElementsByTagName("ConstructionType"):
                # get only last part of source identification
                conSource = ""
                conSource = conTypeNode.getAttribute("source")
                print("Current source of construction: "+conSource)
                
                # go through const variants to compare reference id with defined ids
                # inner loop through one <ConstructionType>
                for conTypeVarNode in conTypeNode.getElementsByTagName("ConstructionTypeVariant"):
                    
                    conTypeVarId = conTypeVarNode.getAttribute("id")
                    
                    if(varRef == conTypeVarId):
                        # go through all constructions of this file to get the matching source attribute(s)
                        print("Positive matching of construction type variant from simulation matrix.")
                        
                        for conObject in newZoneObject.return_ConsList():
                            
                            conSourceRef = ""
                            conSourceRef = conObject.return_source()
                            print("source from zone object:"+conSourceRef)
                            
                            # check if both ids are equal and modify constructions due to xml specifications
                            if (conSourceRef == conSource):
                                
                                print("matching of conSourceRef and conSource:"+conSourceRef)
                                # list which keeps all new layers
                                newLayers_List = []
                                # create new layer structure for this construction
                                for layNode in conTypeVarNode.getElementsByTagName("Layer"):
                                    # get layer thickness
                                    d = float(layNode.getAttribute("thickness"))
                                    
                                    matFileName = layNode.firstChild.nodeValue
                                    # check if this material is included in target directory (Materials)
                                    if(matFileName not in os.listdir(self.__matDir_T)):
                                        
                                        self.__call_ResCon("Material", matFileName)
                                        # open combinations.csv and get the new path of the material file
                                        # it is the second argument in a data row
                                        # first argument is the source which can be used to compare
                                        try:
                                            combinations = os.path.join(os.getcwd(), "combinations.csv")
                                            fileObject = open(combinations, "r" )
                                        
                                        except Exception as e:
                                            
                                            print("Error while parsing material file %s." % combinations)
                                            
                                            return False
                                        
                                        else:
                                            
                                            for line in fileObject.readlines():
                                                
                                                pair = line.split(",")
                                                source = pair[0]
                                                destination = pair[1]
                                                
                                                #get value string and remove white spaces 
                                                if( matFileName == source ): 
                                                    
                                                    print("Material file name matches: "+source)
                                                    pathOfNewMaterial = destination.split(os.sep)
                                                    matFileName = pathOfNewMaterial[len(pathOfNewMaterial)-1]
                                                    matFileName = matFileName.replace("\n","")
                                                    print("mat file name: "+matFileName)
                                                #end if'
                                            #end for
                                    else :
                                        print("Material was found in material database.")
                                    
                                    # parse material properties if material is available
                                    matFilePath = os.path.join(self.__matDir_T,matFileName)
                                    matObject = Material()
                                    
                                    if(matObject.read_DelphinDataSet(matFilePath) == False):
                                        matObject.assign_defaultData()
                                        print("SimulationMatrix: ReturnModifiedZoneObject: Material %s can't be parsed. Default data is assumed." % matFileName)
                                    # create new layer from this and keep it
                                    newLayer = Layer(matObject, d)
                                    newLayers_List.append(newLayer)
                                    print(len(newLayers_List))
                                    print("Current number of layers of one construction element")
                                
                                # remove all layers from current construction and submit new layer structure
                                conObject.remove_allLayers()
                                
                                for layerObject in newLayers_List:
                                    
                                    conObject.add_layer(layerObject.mat , layerObject.d, 0)
                                #end for
                    else:
                        
                        print("no construction influences for current construction in Simulationmatrix")
                #end for
            # check if this is a construction modification reference
            for winTypeNode in self.__file_xml_tree.getElementsByTagName("WindowType"):
                
                # get only last part of source identification
                winSource = ""
                winSource_long = winTypeNode.getAttribute("source")
                if(os.sep in winSource_long): winSource = winSource_long.split(os.sep)[-1]
                elif("/" in winSource_long): winSource = winSource_long.split("/")[-1]
                elif("\\" in winSource_long): winSource = winSource_long.split("\\")[-1]
                else: winSource = winSource_long
                
                # go through const variants to compare reference id with defined ids
                for winTypeVarNode in winTypeNode.getElementsByTagName("WindowTypeVariant"):
                    
                    winTypeVarId = winTypeVarNode.getAttribute("id")
                    
                    if(varRef == winTypeVarId):
                        # go through all windows of this file to get the matching source attribute(s)
                        for conObject in newZoneObject.return_ConsList():
                            
                            winSourceRef = ""
                            
                            if (conObject.return_numberOfEmbObjects() > 0):
                                # get id 
                                winSourceRef_long = conObject.return_embObject(0).source
                                # get only file name from id if possible
                                if(os.sep in winSourceRef_long): winSourceRef = winSourceRef_long.split(os.sep)[-1]
                                elif("/" in winSourceRef_long): winSourceRef = winSourceRef_long.split("/")[-1]
                                elif("\\" in winSourceRef_long): winSourceRef = winSourceRef_long.split("\\")[-1]
                                else: winSourceRef = winSourceRef_long

                                # check if both ids are equal and modify window due to xml specifications
                                if (winSourceRef == winSource):
                                    
                                    # get copy of existing embedded object and modify all specified values
                                    newEmbeddedObject = conObject.return_embObject(0)
                                    
                                    if(len(winTypeVarNode.getElementsByTagName("GlassFraction")) > 0):
                                        # get Value and submit it to construction - embedded object properties
                                        newEmbeddedObject.fGlass = (1.0 - float(winTypeVarNode.getElementsByTagName("GlassFraction")[0].firstChild.nodeValue))
                                    if(len(winTypeVarNode.getElementsByTagName("FrameFraction")) > 0):
                                        # get Value and submit it to construction - embedded object properties
                                        newEmbeddedObject.fGlass = (1.0 - float(winTypeVarNode.getElementsByTagName("FrameFraction")[0].firstChild.nodeValue))
                                    if(len(winTypeVarNode.getElementsByTagName("ThermalTransmittance")) > 0):
                                        # get Value and submit it to construction - embedded object properties
                                        newEmbeddedObject.uValue = float(winTypeVarNode.getElementsByTagName("ThermalTransmittance")[0].firstChild.nodeValue)
                                    if(len(winTypeVarNode.getElementsByTagName("SolarHeatGainCoefficient")) > 0):
                                        # get Value and submit it to construction - embedded object properties
                                        newEmbeddedObject.shgc = float(winTypeVarNode.getElementsByTagName("SolarHeatGainCoefficient")[0].firstChild.nodeValue)

                                    # submit embedded object to current construction
                                    conObject.replace_embObject(newEmbeddedObject)
                            # else:
            
            for weatherDataSetType in self.__file_xml_tree.getElementsByTagName("WeatherDataSetVariant"):
                
                currentDataSetId = weatherDataSetType.getAttribute("id")
                
                if (varRef == currentDataSetId):
                    
                    if (weatherDataSetType.getAttribute("type").__contains__("CCD")):
                        
                        currentWeatherDataSetSource = weatherDataSetType.firstChild.nodeValue
                        currentClimateSource = os.path.join(self.__cliDir_T, "ISES_climate")
                        currentClimateSourceTemporary = os.path.join(self.__cliDir_T, "ISES_climate_temporary")
                        currentClimateVariante = os.path.join(self.__binDir,"Therakles", "data", "DB_climate", "ISES_climate_"+key)
                        currentClimateVarianteWindows = os.path.join(self.__binDir,"Therakles", "resources", "IBK", "DB_Climate", "ISES_climate_"+key)
                        #rename the current ISES_climate folder because the call of ResCon will replace the current folder.
                        os.rename(currentClimateSource, currentClimateSourceTemporary)
                        self.__call_ResCon("Climate", currentWeatherDataSetSource)
                        shutil.copytree(currentClimateSource, currentClimateVariante)
                        shutil.move(currentClimateSource, currentClimateVarianteWindows)
                        newZoneObject.add_ClimateLocation("ISES_climate_"+key)
                        
                        os.rename(currentClimateSourceTemporary, currentClimateSource)
                    else:
                        
                        print("Current data set format for climate information is not supported, yet.")
                        
                                # print("SimulationMatrix: ReturnModifiedZoneObject: Requested construction object does not include a window object which can be modified.")
        return newZoneObject

    # purpose of this function:  This function modifies default directories for all database objects which are defined as sources in simmatrix file
    # submitted variables:       str_sourceDirectory --> directory which contains all sources which are included in simulation file & Nandrad project file spec.
    #                           str_targetDirectory --> directory in which all new files should be stored
    # return values:             True
    def define_resourceDirectories(self, str_sourceDirectory, str_targetDirectory):

        self.__targetDir = str_targetDirectory
        self.__cliDir_T = str_targetDirectory + os.sep + "DB_Climate"
        self.__conDir_T = str_targetDirectory + os.sep + "DB_Constructions"            
        self.__matDir_T = str_targetDirectory + os.sep + "DB_Materials"
        self.__temDir_T = str_targetDirectory + os.sep + "DB_Schedules"

        return True

    # purpose of this function:  This function sets location of resource converting java application ResCon
    # submitted variables:       str_resConDirectory --> directory and complete file name (e.g. "D:\Projekte\ISES\ExampleProjectScripts\bin\ResCon\ResCon.jar")
    # return values:             True
    def define_resConPathAndName(self, str_resConPathAndName):

        if(str_resConPathAndName != ""):
            self.__resConAv = True
            self.__resConPathAndName = str_resConPathAndName
        else:
            print("SimMatrix: DefineRessourceConverterPathAndName: Empty path submitted.")
            self.__resConAv = False
        return True


    # purpose of this function:  This function calls resource requesting java application ResCon
    # submitted variables:       str_resource --> Predefined type definition of implemented resource types
    #                                           defined are: 
    # return values:             True if ressource was created
    #                           False if not
    def __call_ResCon(self, str_resourceType, str_sourceURI):

        # check if rescon path is available
        #if (self.resConAv == False):
        #   print("SimulationMatrix: CallResCon: Submit file path for ResCon.jar firstly.")
        #  return False

        # check type of requested resource to get right target directory 
        targetDir = ""
        SF_keyWord = ""
        N_keyWord = ""

        if (str_resourceType == "Climate"):
            SF_keyWord = "ServiceFramework_Climate"
            N_keyWord = "Nandrad_Climate_Set"
            targetDir = os.path.dirname(os.getcwd())+os.sep+"project"+os.sep+self.__cliDir_T
        elif(str_resourceType == "Construction"):
            SF_keyWord = "ServiceFramework_Construction_Material"
            N_keyWord = "Nandrad_Construction_Material"
            targetDir = os.path.dirname(os.getcwd())+os.sep+"project"+os.sep+self.__conDir_T 
        elif(str_resourceType == "Material"):
            SF_keyWord = "ServiceFramework_Material"
            N_keyWord = "Nandrad_Material"
            targetDir = os.path.dirname(os.getcwd())+os.sep+"project"+os.sep+self.__matDir_T
        elif(str_resourceType == "Schedule"):
            SF_keyWord = "ServiceFramework_Construction_Material"
            N_keyWord = "Nandrad_Construction_Material"
            targetDir = os.path.dirname(os.getcwd())+os.sep+"project"+os.sep+self.__temDir_T
        else:
            print("SimulationMatrix: CallResCon: Undefined ressource type submitted.")
            return False
        
        # content of batch file
        # java -jar ResCon.jar ServiceFramework_Construction_Material http://130.208.198.50:8080/dfservice/dm/open/74/GETID/2 Nandrad_Construction_Material null
        
        print(self.__resConPathAndName)
        # create list of arguments for ResCon application
        argList = [ "java", "-jar", self.__resConPathAndName, SF_keyWord, str_sourceURI, N_keyWord , "null" ]
        # check: definition target directory?
        # call ResCon execution process
        try: 
            executionProcess = subprocess.Popen(argList)

            # wait until process is terminated
            timeOut = 1.0 
            while executionProcess.poll() == None:
                time.sleep(timeOut) 
            return True              

        except Exception:
            print("SimulationMatrix: CallResCon: Undefined error while executing ResCon. Check list of arguments: ")
            print(argList)
            return False

    def returnZonesOfInterrest(self):
        return self.__zonesOfInterest