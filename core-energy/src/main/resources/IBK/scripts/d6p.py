'''
Created on 06.11.2014

@author: Rene Hoch
'''
import os
import subprocess
import time

from xml.dom.minidom import Document
from sets import Set

class delphinConstruction(object):

    def __init__(self, layers, str_destinationOfFile):
        
        self.__constructionLayers = layers
        self.__constructionFile = Document()
        #path definitions
        self.__scriptsPath = os.path.dirname(os.path.realpath(__file__))
        self.__projectPath = os.path.dirname(os.path.normpath(self.__scriptsPath))
        self.__projectFolder = os.path.join(self.__projectPath, "project")
        
        self.__resConPathAndName = os.path.join(self.__projectPath, "bin", "ResCon", "ResCon.jar")
        
        self.__createConstruction(str_destinationOfFile)
        
    def __createConstruction(self, str_destinationOfFile):
        
        #self.__constructionFile.toxml(encoding="UTF-8")
        self.__constructionFile.toprettyxml("\t", "\n\r", "UTF-8")
        construction = self.__constructionFile.createElement("DelphinProject")
        construction.setAttribute("xmlns", "http://www.bauklimatik-dresden.de")
        construction.setAttribute("xmlns:IBK", "http://www.bauklimatik-dresden.de/IBK")
        construction.setAttribute("fileVersion", "5.6.7")
        construction.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema")
        construction.setAttribute("xsi:schemaLocation", "http://www.bauklimatik-dresden.de DelphinProject.xsd")
        self.__constructionFile.appendChild(construction)
        
        projectInfo = self.__constructionFile.createElement("ProjectInfo")
        construction.appendChild(projectInfo)
        
        displayName = self.__constructionFile.createElement("DisplayName")
        projectInfo.appendChild(displayName)
        
        comment = self.__constructionFile.createElement("Comment")
        commentValue = self.__constructionFile.createTextNode("This template was made by ResCon")
        comment.appendChild(commentValue)
        
        projectInfo.appendChild(comment)
        
        materials = self.__constructionFile.createElement("Materials")
        construction.appendChild(materials)
        
        discretization = self.__constructionFile.createElement("Discretization")
        discretization.setAttribute("geometryType", "2D")
        construction.appendChild(discretization)
        
        xsteps = self.__constructionFile.createElement("XSteps")
        xsteps.setAttribute("unit", "m")
        discretization.appendChild(xsteps)
        
        assignments = self.__constructionFile.createElement("Assignments")
        construction.appendChild(assignments)
        
        materialList = Set()
        materialSourceDisplaynameDictionary = {}
        materialDisplaynameIdDictionary = {}
        
        for layer in self.__constructionLayers:
                
            if (layer.return_source() not in materialList):
                
                materialList.add(layer.return_source)
                #create material file
                str_destinationOfMaterialFile = str_destinationOfFile.replace("DB_Constructions","DB_Materials")
                
                self.__call_ResCon(layer.return_source(), str_destinationOfMaterialFile)
                
                materialFileName = self.__openCombinationsFile(layer.return_source())
                str_fileName = "${Material Database}/"+materialFileName
                materialFileNameArray = materialFileName.split("_")
                str_displayName = materialFileNameArray[0]
                str_Id = materialFileNameArray[len(materialFileNameArray)-1]
                str_Id = str_Id.replace(".m6", "")
                
                materialSourceDisplaynameDictionary[layer.return_source] = str_displayName
                materialDisplaynameIdDictionary[str_displayName] = str_Id
                
                materialReferenceNode = self.__createMaterial(str_displayName, str_fileName)
                materials.appendChild(materialReferenceNode)
        
        xstepsValues = ""
        counter = 0
        
        for layer in self.__constructionLayers:
            
            if (counter == 0 ):
                
                xstepsValues += layer.return_thickness()
                counter = 1
            else:
                
                xstepsValues += " "+layer.return_thickness()
        
        xStepsValueNode = self.__constructionFile.createTextNode(xstepsValues)
        xsteps.appendChild(xStepsValueNode)
        
        layerOrder = 0
        
        for layer in self.__constructionLayers:
            
            str_displayName = materialSourceDisplaynameDictionary[layer.return_source]
            str_id = materialDisplaynameIdDictionary[str_displayName]
            
            assignment = self.__createAssignment(str_displayName, str_id, layerOrder)
            
            assignments.appendChild(assignment)
            
            layerOrder += 1 
    
    def __createMaterial(self, str_displayName, str_fileName):
        
        materialReference = self.__constructionFile.createElement("MaterialReference")
        materialReference.setAttribute("displayName", str_displayName)
        materialReferenceContent = self.__constructionFile.createTextNode(str_fileName)
        materialReference.appendChild(materialReferenceContent) 
        
        return materialReference
        
    def __createAssignment(self, str_displayName, str_id, str_order):
        
        assignment = self.__constructionFile.createElement("Assignment")
        assignment.setAttribute("location", "Element")
        assignment.setAttribute("type", "Material")
        
        reference = self.__constructionFile.createElement("Reference")
        reference.setAttribute("displayName", str_displayName)
        referenceID = self.__constructionFile.createTextNode(str_id)
        reference.appendChild(referenceID)
        assignment.appendChild(reference)
        
        rangeNode = self.__constructionFile.createElement("IBK:Range")
        rangeValue = self.__constructionFile.createTextNode(str(str_order)+" 0 "+str(str_order)+" 0")
        rangeNode.appendChild(rangeValue)
        assignment.appendChild(rangeNode)
        
        return assignment

    # purpose of this function:  This function calls resource requesting java application ResCon
    # submitted variables:       str_resource --> Predefined type definition of implemented resource types
    #                                           defined are: 
    # return values:             True if ressource was created
    #                           False if not
    def __call_ResCon(self, str_sourceURI, str_destinationOfRoot):

        SF_keyWord = "ServiceFramework_Material"
        N_keyWord = "Nandrad_Material"
        targetDir = str_destinationOfRoot
        
        argList = [ "java", "-jar", self.__resConPathAndName, SF_keyWord, str_sourceURI, N_keyWord , targetDir ]
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
        
    def __openCombinationsFile(self, str_source):
        
        materialName = ""
        
        try:
            combinations = os.path.join(os.getcwd(), "combinations.csv")
            fileObject = open(combinations, "r" )
            
        except Exception as e:
        
            print("Error while parsing material file %s." % combinations)
            print e
            return False
            
        else:
            
            for line in fileObject.readlines():
            
                pair = line.split(",")
                source = pair[0]
                destination = pair[1]
                
                #get value string and remove white spaces 
                if( str_source == source ): 
                    
                    pathOfNewMaterial = destination.split(os.sep)
                    
                    if (len(pathOfNewMaterial) > 1):
                        
                        materialName = pathOfNewMaterial[len(pathOfNewMaterial)-1]
                        materialName = materialName.replace("\n","")
                    
                    else:
                        
                        pathOfNewMaterial = destination.split("\\")
                            
                        if (len(pathOfNewMaterial > 1)):
                            
                            materialName = pathOfNewMaterial[len(pathOfNewMaterial)-1]
                            materialName = materialName.replace("\n","")
                        
                        else:
                            
                            pathOfNewMaterial = destination.split("/")
                            materialName = pathOfNewMaterial[len(pathOfNewMaterial)-1]
                            materialName = materialName.replace("\n","")
        
        return materialName
    
    def return_construction(self):
        
        return self.__constructionFile
    
    def add_displayName(self, str_displayName):
        
        displayName = self.__constructionFile.getElementsByTagName("DisplayName")[0]
        displayNameValue = self.__constructionFile.createTextNode(str_displayName)
        displayName.appendChild(displayNameValue)

class delphinConstructionLayer(object):
    
    def __init__(self, str_thickness, str_reference):
        
        self.__thickness = str_thickness
        self.__source = str_reference
        
    def return_source(self):
        
        return self.__source
    
    def return_thickness(self):
        
        return self.__thickness