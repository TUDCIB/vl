
"""
purpose of this class: This class handels construction related data, layer & construction type have only supporting function
institution: IBK, TUD
"""

#libs
from enum import Enum
import xml.dom.minidom as DOM
import os

#self defined libs
from material import Material


#this class is used to handle construction types
class ConstructionType(Enum):

    #following enums have a unique name (eg. EXTERNAL) and corresponding value (eg.1)
    EXTERNAL = 1            #External construction, adjacent to outdoor air
    INTERNAL_AD = 2         #Internal construction, adjacent to similar zone (half storage capacity considered)
    INTERNAL_T = 3          #Internal construction, adjacent to zone with constant temperature
    
    
#this class is used to handle each single layer
class Layer:

    #constructor
    def __init__(self, material_object, layer_thickness_m ):

        #layer is defined with layer thickness in m  and material object
        self.mat = material_object          #material object
        self.d = layer_thickness_m          #material thickness 
        

#this class is used to handle embedded objects (yet only windows)
class EmbeddedObject:

    #constructor
    def __init__(self):

        self.uValue = 1.0           #heat transfer coefficient, unit W/m2K
        self.shgc = 0.6             #solar heat gain coefficient, unit [-] frac
        self.fGlass = 0.7           #glass area fraction, unit [-] frac
        self.area = 2.0             #area in m2
        self.source = ""            #additional attribute which is used for simma iterpretation
        self.ifcKey = ""            #ifc key 

    def submitProperties(self, uValue, shgc, fGlass, area):
        
        self.uValue = uValue
        self.shgc = shgc
        self.fGlass = fGlass
        self.area = area

    def submitIdentifiers(self, str_ifc, str_source):
        if( str_ifc == "" or str_source == ""):
            print("Constructions:EmbeddedObject:AssignIdentifiers: Ifc key or source key are empty strings and might not be included in nandrad project file.")
        self.source = str_source
        self.ifcKey = str_ifc



#this class is used to handle construction types, windows / doors are threated as a part of these objects
class Construction:

    #constructor 
    def __init__(self, constructionName ):
        
        #private attributes and variables: only for class- internal use, syntax: self.__XXX

        self.__name = constructionName
        self.__orientation = 180.0              #construction area azimuth, 0 = north, east = 90, south = 180,...  , unit deg
        self.__inclination = 90.0               #construction area inclination, horizontal = 0, vertical = 90, downwards = 180, unit deg
        self.__area = 10.0                      #gross area of the wall, including embedded objects (windows)
        self.__type = ConstructionType.EXTERNAL #construction type -->ToDo: create Enum 

        self.__listOfEmbObjects = []            #includes window constructions or door constructions

        self.__aInside = 0.6                    #absorption coefficient at inner surface for short wave radiation, unit frac
        self.__innerHeatTransferC = 0.13        #heat transfer resistance at inner surface, unit m2K/W
        self.__betaInside = 2e-07               #moisture transfer coefficient at inner surface, unit 
        self.__listOfLayers = []                #list of layers, index 0 equals innermost layer
        self.__outerHeatTransferC = 0.04        #heat transfer resistance at external surface, unit m2K/W
        self.__betaOutside = 3e-08              #moisture transfer coefficient at external surface, unit 
        self.__aOutside = 0.6                   #absorption coefficient at external surface for short wave radiation, unit frac

        self.__constrTheraklesId = "0"
        self.__constrNandradId = "0"            #supporting value to keep nandrad assignment to construction database
        
        self.__ifcKey = ""                      #included as attribute "fromElement"
        self.__source = ""                      #additional attribute which is used for simma iterpretation
        
        #protected attributes: don't modify externally, syntax: self._XXX
        #none
        
        #public attributes: syntax: self.XXX
        #none


        
    #purpose of this function:  Function initializes Construction object with defaut data (one concrete layer of 0.2 m thickness)
    #submitted variables:       none
    #return values:             none
    def assign_defaultData(self):
        
        #assign default data 
        newMaterial = Material()
        newMaterial.assign_defaultData()
        newThickness = 0.2

        #create and append layer to member list
        newLayer = Layer( newMaterial, newThickness )
        self.__listOfLayers = []
        self.__listOfLayers.append( newLayer )
        
        #assign default embedded objects: no window in default settings
        #newWindow = EmbeddedObject()
        #self.__listOfEmbObjects = []
        #self.__listOfEmbObjects.append( newWindow )



    #purpose of this function:  Function assigns surface heat resistances and absorption coefficients
    #submitted variables:       Surface heat resistance inside (index 0 is innermost layer) and outside in m2K/W
    #return values:             none
    def assign_surfaceProp(self, R_inside, R_outside):

        #assign values
        self.__innerHeatTransferC = R_inside
        self.__outerHeatTransferC = R_outside


    #purpose of this function:  Function assigns inclination and orientation angle
    #submitted variables:       orientation and inclination angles in deg
    #return values:             none
    def assign_angles(self, inclination, orientation):

        #assign values
        self.__inclination = inclination
        self.__orientation = orientation

    #purpose of this function:  Function assigns area (gross area without windows, holes,...)
    #submitted variables:       area in m2
    #return values:             none
    def assign_area(self, area, consType):

        #assign values
        self.__area = area
        self.__type = consType

    def assign_Identifiers(self, str_ifcKey, str_source):

        if( str_ifcKey == "" or str_source == ""):
            print("Constructions:AssignIdentifiers: Ifc key or source key are empty strings and might not be included in nandrad project file.")
            
        self.__ifcKey = str_ifcKey
        self.__source = str_source

                
    #purpose of this function:  Function removes layer from given position
    #submitted variables:       position (start counting from innermost plane)
    #return values:             none
    def remove_layer(self, positionIndex ):

        #insert layer at desired position
        pos = int(positionIndex)
        
        if ( pos < len(self.__listOfLayers) ):
            self.__listOfLayers.pop(pos)
        else:
            print("Desired layer index (position) doesn't exist yet.")
          

    def remove_allLayers(self):
        self.__listOfLayers = []

        
    #purpose of this function:  Function adds layer at desired position, if position not existing at outermost plane
    #submitted variables:       material object, thickness, position (start counting from innermost plane)
    #return values:             none
    def add_layer(self, material, thickness, positionIndex ):

        #check thickness of material layer
        if (float(thickness) > 0.5):
            print("Construction:AddLayer: Layer thickness is more than 50 cm. Check settings. Layer thickness is restricted to 0.5 m.")
            thickness = "0.5"

        #create layer with submitted data
        newLayer = Layer( material, thickness )

        #insert layer at desired position
        pos = int(positionIndex)
        if ( pos < len(self.__listOfLayers)  ):
            self.__listOfLayers.insert(pos, newLayer)
        else:
            #print("Desired position doesn't exist yet, layer is added at outermost plane.")
            self.__listOfLayers.append( newLayer )



    #purpose of this function:  Function adds embedded object (window) to this construction ----> REMOVE LATER; SUBSTR. BY FOLLOWING FUNCTION
    #submitted variables:       embObject properties
    #return values:             none
    def add_embeddedObject(self, uValue, shgc, fGlass, area ):

        #create object and submit properties
        newEmbeddedObject = EmbeddedObject()
        newEmbeddedObject.submitProperties( uValue, shgc, fGlass, area )

        #only one object allowed yet: clear list and append new object
        self.__listOfEmbObjects = []
        self.__listOfEmbObjects.append( newEmbeddedObject )


    #purpose of this function:  Function adds embedded object (window) to this construction
    #submitted variables:       embObject properties
    #return values:             none
    def add_embeddedObjectWithIDs(self, uValue, shgc, fGlass, area, ifc, source ):

        #create object and submit properties
        newEmbeddedObject = EmbeddedObject()
        newEmbeddedObject.submitProperties( uValue, shgc, fGlass, area )
        newEmbeddedObject.submitIdentifiers( ifc, source)

        #only one object allowed yet: clear list and append new object
        self.__listOfEmbObjects = []
        self.__listOfEmbObjects.append( newEmbeddedObject )
            


        
    #purpose of this function:  Keeps nandrad id to find fitting construction object to xml entry later
    #submitted variables:       id as string value
    #return values:             none
    def assign_NandradConstructionID(self, nandrad_id ):
        self.__constrNandradId = nandrad_id


    
    #purpose of this function:  Check construction settings
    #submitted variables:       none
    #return values:             u-value in W/m2K, gives 0 if no layer is defined
    def return_uValue(self):

        #calculation values
        rSurf = self.__innerHeatTransferC + self.__outerHeatTransferC
        rLayers = 0.0
        uValue = 0.0
        
        #compute U-value
        if( len(self.__listOfLayers) > 0 ):
            for layer in self.__listOfLayers:
                rLayer = (layer.d / layer.mat.return_la())
                rLayers = rLayers + rLayer
            #use final resistance
            uValue = 1.0 / rLayers

        #return result, u-value = 0 if no layer is defined
        return uValue
    

    #purpose of this function:  Check number of defined layers
    #submitted variables:       none
    #return values:             integer (layer number)
    def return_numberOfLayers(self):
        return len( self.__listOfLayers )
    
    def return_listOfLayers(self):
        return self.__listOfLayers

    
    def return_numberOfEmbObjects(self):
        return len( self.__listOfEmbObjects )
    
    def replace_embObject(self, newEmbObject):
        #TODO: request index of embedded object for replacing 
        self.__listOfEmbObjects  = []
        self.__listOfEmbObjects.append(newEmbObject)
        return True
    
    def return_embObject(self, id):
        if ( len(self.__listOfEmbObjects) > id ):
            return self.__listOfEmbObjects[id]
        else:
            return False
    def return_embObject_ifc(self, id):
        if ( len(self.__listOfEmbObjects) > id ):
            return self.__listOfEmbObjects[id].ifcKey
        else:
            return ""       
    def return_embObject_source(self, id):
        if ( len(self.__listOfEmbObjects) > id ):
            return self.__listOfEmbObjects[id].source
        else:
            return ""       

    #purpose of these functions: Protected value access
    def return_name(self):
        return self.__name
    def return_orient(self):
        return self.__orientation
    def return_inclin(self):
        return self.__inclination
    def return_area(self):
        return self.__area
    def return_aOutside(self):
        return self.__aOutside
    def return_rSurfOut(self):
        return self.__outerHeatTransferC
    def return_rSurfIn(self):
        return self.__innerHeatTransferC
    def return_type(self):
        if( self.__type.value == 1):
            return "Outside"
        else:
            return "Inside"
    def return_typeValue(self):
        return self.__type           
    def return_betaInside(self):
        return self.__betaInside
    def return_betaOutside(self):
        return self.__betaOutside
    def return_teraklesID(self):
        return str(self.__constrTheraklesId)
    def return_ifcKey(self):
        return self.__ifcKey
    def return_source(self):
        return self.__source
    



    #purpose of this function:  Function reads construction data set from delphin data set (*d6p)
    #submitted variables:       string including file path and name (path\*.*d6p) of the desired construction
    #                           string including file path of material files (path\ [....m6])
    #return values:             false if invalid files and dataset couldn't be read
    #                           true if file parsing succeeded
    
    def read_DelphinDataSet(self, stringFilePathName_d6p, stringFilePath_m6 ):
        
        #try to read file and replace invalid prefix in delphin project file
        try:
            fileString = open(stringFilePathName_d6p, "r").read()
        except Exception:
            print("Invalid file or file access not possible: %s" % stringFilePathName_d6p)
        else:
            buggyString = "xsi:schemaLocation" #undefined prefix causes error in xml parsing function
            newString = "schemaLocation"
            #find false string
            if (buggyString in fileString):
                newFileString = fileString.replace( buggyString, newString )
                newFile = open(stringFilePathName_d6p, "w")
                newFile.write(newFileString)
                newFile.close()

        #read xml project file
        try:
            xml_fileTreeObject = DOM.parse( stringFilePathName_d6p )
        except Exception:
            print("XML file reading of file %s failed. Invalid xml file structure." % stringFilePathName_d6p)
            return False
        else:
            #init properties temporary property/ element lists
            materialList = []           #container for material objects specified in this file
            layerThickList = []         #contains string values with layer thicknesses in m

            #construction file reading successful, now try to parse included material files
            for matTag in  xml_fileTreeObject.getElementsByTagName("MaterialReference"):
                
                #get tag properties
                matName = matTag.getAttribute("displayName")
                matFileString = matTag.firstChild.nodeValue

                #get file location for material file
                placeHolder = "${Material Database}/"
                folder = stringFilePath_m6 + os.sep
                matFileLocation = matFileString.replace( placeHolder , folder )
                #print("Identified and submitted file string is %s." % matFileLocation )
                
                #read data set and create material object with specific name
                newMaterial = Material()
                if (newMaterial.read_DelphinDataSet( matFileLocation ) == True):
                    #print("Material %s identified." % matName)
                    newMaterial.set_matName( matName )
                    materialList.append( newMaterial )
                else:
                    print("One or several materials in delivered construction file %s are invalid." % stringFilePathName_d6p)
                    return False
                
            #now get layer thicknesses
            allSteps = xml_fileTreeObject.getElementsByTagName("XSteps")[0].firstChild.nodeValue
            for step in allSteps.split():
                layerThickList.append(step)
                #print("Layer thickness %s identified." % step)

            #read layer order and create layers for this construction
            layerCounter = 0
            for assTag in xml_fileTreeObject.getElementsByTagName("Assignment"):
                if ( assTag.getAttribute("type") == "Material" ):
                    tempMatName = assTag.getElementsByTagName("Reference")[0].getAttribute("displayName")
                    #print("Referenced material name %s identified." % tempMatName)
                    for matObject in materialList:
                        #print("checked name for material: %s" % matObject.return_name() )
                        if (matObject.return_name() == tempMatName):
                            #matching material, create layer
                            self.add_layer( matObject, layerThickList[ layerCounter ], layerCounter )
                            #print("Matching material %s identified. Layer created." % matObject.return_name() )
                            layerCounter = layerCounter + 1
                else:
                    print("Delphin project file parsing of file %s failed. Invalid layer definiton." % stringFilePathName_d6p)
                    return False               
            
            return True
   

