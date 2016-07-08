
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
from decimal import Decimal

from d6p import delphinConstruction, delphinConstructionLayer

# predefined python libs
import xml.dom.minidom as DOM
import subprocess
import time
import os
import copy
import shutil

from sets import Set
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

    def __init__(self, str_combId):

        self.__combinationId = str_combId  # individual string for identification of each version
        self.__varianteAssignmentContainer = {}  # dictionary with assignment id and variable version reference id for each modified variable
        self.__variantContainer = Set()
        
    def add_assignmentReference(self, str_variantReference, str_assignmentReference):
        print str_variantReference
        print str_assignmentReference
        self.__varianteAssignmentContainer[str_variantReference] = str_assignmentReference
        
    def add_variant(self, str_variantReference):
        
        self.__variantContainer.add(str_variantReference)
    
    def return_ID(self):
        
        return self.__combinationId

    def return_VariableAssignmentDict(self):
        
        return self.__varianteAssignmentContainer
    
    def return_variants(self):
        
        return self.__variantContainer

# This class is a data container to hold the target identifiers
class TargetList_Container(object):
    
    def __init__(self, str_targetListId):
        
        self.__identifier = str_targetListId
        self.__targets = Set()
        self.__targetType = {}
        
    def add_Target(self, str_type, str_idOfTarget):
        
        self.__targets.add(str)
        self.__targetType[str_idOfTarget] = str_type
        
    def return_TargetListId(self):
        
        return self.__identifier

# This class is a data container to hold the assignment identifiers
class AssignmentGroup_Container(object):
    
    def __init__(self, str_assignmentGroupId, str_type):
        
        self.__identifier = str_assignmentGroupId
        print str_assignmentGroupId
        self.__type = str_type
        self.__assignments = Set()
        
    def add_Element(self, str_identifier):
        
        self.__assignments.add(str_identifier)
    
    def return_type(self):
        
        return self.__type
    
    def return_assignments(self):
        
        return self.__assignments
    
    def return_identifier(self):
        
        return self.__identifier
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
        self.__targetLists = []
        self.__assignmentLists = []
        
        self.__currentConstruction = delphinConstruction
        self.__currentSourceOfVariant = ""
        # target directories ---> call define_resourceDirectories(...) to specify this
        self.__scriptsDir = os.path.dirname(os.path.realpath(__file__))
        self.__targetDir = os.path.dirname(self.__scriptsDir)
        self.__binaryDir = os.path.join(self.__targetDir, "bin")
        self.__nandradDir = os.path.join(self.__binaryDir, "Nandrad")
        self.__resconDir = os.path.join(self.__binaryDir, "ResCon")
        self.__projectDir = os.path.join(self.__targetDir, "project")
        self.__buildingDir = os.path.join(self.__projectDir, "NandradProjects")
        self.__projectFile = os.path.join(self.__projectDir, "ISES_project.xml")
        
        self.__cliDir_T = os.path.join(self.__projectDir, "DB_Climate")
        self.__conDir_T = os.path.join(self.__projectDir, "DB_Constructions")       
        self.__matDir_T = os.path.join(self.__projectDir, "DB_Materials")
        self.__temDir_T = os.path.join(self.__projectDir, "DB_Templates")

        self.__resConAv = False
        self.__resConPathAndName = ""

        # call initial function which fills variables above
        self.__xmlFileInitialized = self.__parseFile(filePathAndName)
        self.__create_modifiedNandradFile()
        
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
            
            # Collects TargetList elements and create an TargetList_Container
            for targetList in self.__file_xml_tree.getElementsByTagName("TargetList"):
                
                currentTargetList = TargetList_Container(targetList.getAttribute("id"))
                
                for target in targetList.getElementsByTagName("Target"):
                    
                    currentTargetList.add_Target(target.getAttribute("type"), target.firstChild.nodeValue)
                # adds the current target list container to the array
                self.__targetLists.append(currentTargetList)
            
            # Collects AssignmentGroup elements 
            for elementGroup in self.__file_xml_tree.getElementsByTagName("ElementGroup"):
                
                currentAssignmentGroup = AssignmentGroup_Container(elementGroup.getAttribute("id"), "ElementGroup")
                #This step could be optimized in a later state because and the beginning it is not known if the assignment group will be used
                for element in elementGroup.getElementsByTagName("Element"):
                    
                    currentAssignmentGroup.add_Element(element.firstChild.nodeValue)
                
                self.__assignmentLists.append(currentAssignmentGroup)
            
            # Combinations
            for combination in self.__file_xml_tree.getElementsByTagName("Combination"):
                
                combinationID = combination.getAttribute("id")
                combinationObject = SimMa_Combination(combinationID)
                
                for variant in combination.getElementsByTagName("Variant"):
                    
                    varRef = variant.getAttribute("VREF")
                    combinationObject.add_variant(varRef)
                    
                    if (variant.hasAttribute("AREF")):
                        
                        assRef = variant.getAttribute("AREF")
                        combinationObject.add_assignmentReference(varRef, assRef)
                        
                self.__combObjectList.append(combinationObject)
                self.__combKeyList.append(combinationID)

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
    def __create_modifiedNandradFile(self):

        # check initialization
        if (self.__xmlFileInitialized == False):
            
            print("SimulationMatrix:ReturnModifiedZoneObject: simulation matrix xml file must be initialized correctly before calling this function.")
        # get list of variable reference ids
        
        for combination in self.__combObjectList:
            
            combinationID = combination.return_ID()
            
            currentRootDir = os.path.join(self.__buildingDir, "Building_version_"+combinationID)
            if (not os.path.exists(currentRootDir)):
                
                os.mkdir(currentRootDir)
            
            currentBinaryDir = os.path.join(currentRootDir, "bin")
            if (not os.path.exists(currentBinaryDir)):
                
                os.mkdir(currentBinaryDir)
            #copying Nandrad Solvers
            nandradDir = os.path.join(currentBinaryDir, "Nandrad")
            if (not os.path.exists(nandradDir)):
                
                shutil.copytree(self.__nandradDir, nandradDir)
            
            resconDir = os.path.join(currentBinaryDir, "ResCon")
            if (not os.path.exists(resconDir)):
                
                shutil.copytree(self.__resconDir, resconDir)
            
            currentProjectDir = os.path.join(currentRootDir, "project")
            if (not os.path.exists(currentProjectDir)):
                
                os.mkdir(currentProjectDir)
            
            currentClimateDb = os.path.join(currentProjectDir, "DB_Climate")
            if (not os.path.exists(currentClimateDb)):
                
                shutil.copytree(self.__cliDir_T, currentClimateDb)
            
            currentMaterialDb = os.path.join(currentProjectDir, "DB_Materials")
            if (not os.path.exists(currentMaterialDb)):
                
                shutil.copytree(self.__matDir_T, currentMaterialDb)
            
            currentConstructionDb = os.path.join(currentProjectDir, "DB_Constructions")
            if (not os.path.exists(currentConstructionDb)):
                
                shutil.copytree(self.__conDir_T, currentConstructionDb)
            
            currentTemplatesDb = os.path.join(currentProjectDir, "DB_Templates")
            if (not os.path.exists(currentTemplatesDb)):
                
                shutil.copytree(self.__temDir_T, currentTemplatesDb)
            
            currentProjectFile = os.path.join(currentProjectDir, "ISES_project.xml")
            if (not os.path.exists(currentProjectFile)):
                
                shutil.copy2(self.__projectFile, currentProjectFile)
                
            modifiedNandradFile = DOM.parse(currentProjectFile)
            
            for variant in combination.return_variants():
                
                del self.__currentConstruction
                del self.__currentSourceOfVariant
                
                if (self.__isConstructionVariant(variant, currentRootDir)):
                    
                    #gets parent construction type and source
                    #constructionTypeSource = constructionType.getAttribute("source")
                    #checks if an assignment group exists
                    currentConstructionTypeReferences = modifiedNandradFile.getElementsByTagName("ConstructionTypeReference")
                    currentNumberOfConstructionReferences = len(currentConstructionTypeReferences)
                    # if the current variant id is in the assignment dictionary
                    # means there is at least one assigned element for the variant
                    # so adaption of construction element has to be separated from other elements  
                    if (variant in combination.return_VariableAssignmentDict()):
                        #Every <AssignmentGroup> has an attribute id, which holds an unique identifier in the Simulation Matrix
                        currentAssignmentGroupId = combination.return_VariableAssignmentDict()[variant]
                        #finds assignment group in assignmentList containers, which holds every assignment groups of the current Simulation MAtrix
                        for assignmentGroup in self.__assignmentLists:
                            
                            print currentAssignmentGroupId
                            print assignmentGroup.return_identifier()
                            
                            if (assignmentGroup.return_identifier() == currentAssignmentGroupId):
                                #get all elements of that assignments container
                                assignmentElements = assignmentGroup.return_assignments()
                                constructionTypeId = currentNumberOfConstructionReferences+1
                                #creates new d6p file with construction_variant_id.d6p
                                constructionTypes = modifiedNandradFile.getElementsByTagName("ConstructionTypes")[0]
                                
                                constructionTypeReference = modifiedNandradFile.createElement("ConstructionTypeReference")
                                constructionTypeReference.setAttribute("displayName", "constructionVariant_"+variant)
                                
                                constructionTypeReferenceValue = modifiedNandradFile.createTextNode("${Construction Database}/constructionVariant_"+variant+"_"+str(constructionTypeId)+".d6p")
                                
                                constructionTypeReference.appendChild(constructionTypeReferenceValue)
                                
                                constructionTypes.appendChild(constructionTypeReference)
                                
                                self.__currentConstruction.add_displayName("constructionVariant_"+variant)
                                self.__write_File(os.path.join(currentConstructionDb, "constructionVariant_"+variant+"_"+str(constructionTypeId)+".d6p"), self.__currentConstruction.return_construction())
                                
                                #changes every construction instance which is in assignment group
                                for constructionInstance in modifiedNandradFile.getElementsByTagName("ConstructionInstance"):
                                    
                                    if (constructionInstance.getAttribute("fromElement") in assignmentElements):
                                        
                                        constructionTypeIdNode = constructionInstance.getElementsByTagName("ConstructionTypeID")[0]
                                        constructionTypeIdNode.firstChild.nodeValue = constructionTypeId
                    else:
                        # finds the matching construction type reference and overwrites it with the path of the construction variant
                        for constructionInstance in modifiedNandradFile.getElementsByTagName("ConstructionInstance"):
                            #parsing of constructionInstance elements until the source of a <ConstructionInstance> matches the self.__currentSourceOfVariant
                            if (constructionInstance.getAttribute("source") == self.__currentSourceOfVariant):
                                #matching of source and source-variant
                                constructionTypeIdNode = constructionInstance.getElementsByTagName("ConstructionTypeID")[0]
                                constructionTypeId = int(constructionTypeIdNode.firstChild.nodeValue)
                                currentConstructionTypeReferences[constructionTypeId-1].firstChild.nodeValue = "${Construction Database}/constructionVariant_"+variant+"_"+str(constructionTypeId)+".d6p"
                                self.__currentConstruction.add_displayName("constructionVariant_"+variant)
                                self.__write_File(os.path.join(currentConstructionDb, "constructionVariant_"+variant+"_"+str(constructionTypeId)+".d6p"), self.__currentConstruction.return_construction())
                                
                                print constructionTypeId
                                break 
                        print ("Overwrite the existing template")
                        
                elif (self.__isMaterialVariant(variant)):
                    
                    print("Adaption of material")
                    
                elif (self.__isClimateVariante(variant)):
                    # A variation of climate data set is always an adaption on building level. It means the link of climate data set must not be changed. 
                    # Only the climate folder itself has to be adapted. 
                    # Either the entire folder can be replaced or the files which will be adapted will be replaced.
                    
                    print("Adaption of climate")
                    
                elif (self.__isWindowVariante(variant)):
                    
                    print("Adaption of windows")
                    
        self.__write_File(os.path.join(currentProjectDir, "ISES_project_"+combinationID+".xml"), modifiedNandradFile)

    # purpose of this function:  This function calls resource requesting java application ResCon
    # submitted variables:       str_resource --> Predefined type definition of implemented resource types
    #                                           defined are: 
    # return values:             True if ressource was created
    #                           False if not
    def __call_ResCon(self, str_resourceType, str_sourceURIRoot):

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
        elif(str_resourceType == "Construction"):
            SF_keyWord = "ServiceFramework_Construction_Material"
            N_keyWord = "Nandrad_Construction_Material"
        elif(str_resourceType == "Material"):
            SF_keyWord = "ServiceFramework_Material"
            N_keyWord = "Nandrad_Material"
        elif(str_resourceType == "Schedule"):
            SF_keyWord = "ServiceFramework_Construction_Material"
            N_keyWord = "Nandrad_Construction_Material"
        else:
            print("SimulationMatrix: CallResCon: Undefined ressource type submitted.")
            return False
        # create list of arguments for ResCon application
        argList = [ "java", "-jar", self.__resConPathAndName, SF_keyWord, str_sourceURIRoot, N_keyWord , "null" ]
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

    def returnTargetLists(self):
        
        return self.__targetLists
    
    def __isConstructionVariant(self, str_variant, str_path):
        print("Path of construction: "+str(str_path))
        isConstruction = False
        
        for constructionType in self.__file_xml_tree.getElementsByTagName("ConstructionType"):
            
            currentSourceOfConstruction = constructionType.getAttribute("source")
            
            for constructionTypeVariant in constructionType.getElementsByTagName("ConstructionTypeVariant"):
                
                constructionTypeVariantId = constructionTypeVariant.getAttribute("id")
                
                if (str_variant == constructionTypeVariantId):
                    
                    layers = []
                    
                    for layer in constructionTypeVariant.getElementsByTagName("Layer"):
                        
                        str_unit = layer.getAttribute("unit")
                        
                        if (str_unit == "m"):
                            
                            str_thickness = layer.getAttribute("thickness")
                            
                        elif (str_unit == "cm"):
                            
                            currentThickness = Decimal(layer.getAttribute("thickness")/100)
                            str_thickness = currentThickness
                            
                        elif (str_unit == "mm"):
                            
                            currentThickness = Decimal(layer.getAttribute("thickness")/1000)
                            str_thickness = currentThickness
                        else:
                            print("Unit is not supported by this conversion process.")
                        
                        str_reference = layer.firstChild.nodeValue
                        
                        currentLayer = delphinConstructionLayer(str_thickness, str_reference)
                        
                        layers.append(currentLayer)
                        
                    print(str_path)
                    self.__currentConstruction = delphinConstruction(layers, str_path)
                    self.__currentSourceOfVariant = currentSourceOfConstruction
                    isConstruction = True
                    
                    return isConstruction
                    
        return isConstruction
    
    def __isMaterialVariant(self, str_variant):
        
        isMaterial = False
        
        for materialType in self.__file_xml_tree.getElementsByTagName("MaterialType"):
            
            for materialTypeVariant in materialType.getElementsByTagName("MaterialTypeVariant"):
                
                materialTypeVariantId = materialTypeVariant.getAttribute("id")
                
                if (str_variant == materialTypeVariantId):
                    
                    isMaterial = True
                    materialTypeSource = materialType.getAttribute("source")
                    #create m6 material file
        return isMaterial
    
    def __isClimateVariante(self, str_variant):
        
        isClimate = False
        
        for climateType in self.__file_xml_tree.getElementsByTagName("WeatherDataSetVariant"):
            
            climateTypeId = climateType.getAttribute("id")
            
            if ((str_variant == climateTypeId) and (climateType.getAttribute("type").__contains__("CCD"))):
                
                isClimate = True
                
                if (climateType.getAttribute("type").__contains__("CCD")):
                    
                    currentWeatherDataSetSource = climateType.firstChild.nodeValue
                    self.__call_ResCon("Climate", currentWeatherDataSetSource)
                    
                else:
                    
                    print("Current data set format for climate information is not supported, yet.")
                
        return isClimate
    
    def __isWindowVariante(self, str_variant):
        
        isWindow = False
        
        for windowType in self.__file_xml_tree.getElementsByTagName("WindowType"):
            
            for windowTypeVariant in windowType.getElementsByTagName("WindowTypeVariant"):
                
                windowTypeVariantId = windowTypeVariant.getAttribute("id")
                
                if (str_variant == windowTypeVariantId):
                    
                    isWindow = True
                    
                    #get parameter/s which has/have to be adapted
                    for windowParameter in windowTypeVariant.getChildNodes():
                        
                        if (windowParameter.nodeName == "GlassFraction"):
                            
                            print ("adapt glass fraction")
                        elif(windowParameter.nodeName == "FrameFraction"):
                            
                            print ("adapt glass fraction")
                        elif(windowParameter.nodeName == "ThermalTransmittance"):
                            
                            print ("adapt thermal transmittance")
                        elif(windowParameter.nodeName == "SolarHeatGainCoefficient"):
                            
                            print ("adapt solar heat coefficient")
                        elif(windowParameter.nodeName == "ShadingFactor"):
                            
                            print ("adapt shading")
        
        return isWindow


    def __write_File(self, file, modifiedFile):
        
        #create output file object
        outputFileObject = open( file , "w")
        #write modified xml output
        try:
            modifiedFile.writexml( outputFileObject )
            outputFileObject.close()
        except Exception as e:
            print("Could not save Nandrad project file: %s." % (file))
            print e
            return False
        else:
            return True