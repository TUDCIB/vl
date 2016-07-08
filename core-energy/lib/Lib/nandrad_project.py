"""
purpose of this class: This class handles Nandrad xml project files for pre- simulation and as default settings source
institution:IBK, TUD
included protected/public variables: _nandrad_xml_initialized ;
included functions:   constructor ; init_file ; init_zones ; set_zonesToPassiveState;
                      set_settings_toMinSolverSet ; set_output_toHourlyPassiveResults;
                      start_NandradSimulation ; start_zoneAnalysisPassiveResults
                      returnAllZoneIDs(self), returnAllZoneIFCKeys(self),
                      returnZonesOfInterestIDs(self), returnZonesOfInterestIFCKeys(self)
                      returnZoneObject(self, zoneId)

"""


# libs
import xml.dom.minidom as DOM
import subprocess
import matplotlib.pyplot as PLOT
import time, sys
import numpy as NP
import os
from scipy.stats import scoreatpercentile
from scipy.stats import gaussian_kde

#self defined objects/classes
from zone_object import ZoneObject
from zone_object import SpaceType
from construction import ConstructionType
from construction import Construction, Layer, EmbeddedObject
from schedules import ScheduleType, DataType, Schedule



class Nandrad_xml:

    #constructor has only variable- initializing function
    def __init__(self):

        #attribute definition
        
        #private attributes and variables
        self.__nandrad_xml = "none"                     #complete file name and path of nandrad project file
        self.__nandrad_xml_tree = DOM.EmptyNodeList()   #contains complete nandrad xml tree structure if initialization was successful
        self.__zoneIndizes = []                         #includes all indizes of all nandrad project zones as a string 
        self.__zoneIFCKeys = []                         #includes all ifc codes of all nandrad project zones as string 

        self.__zoneIdDict = {}                          #dictionary with nandrad id and ifc keys for each zone
        
        self.__zonesInitialized = False                 #True if project file was checked for assigned ifc and zone indizes
        self.__zonesOfInterestInitialized = False       #True if project file was checked for assigned ifc and zone indizes and passive analysis wall called
        self.__zonesOfInterestIndizes = []              #includes all nandrad indizes of all zones of interest as string
        self.__zonesOfInterestKeys = []                 #includes all ifc- keys of all zones of interest as string

        self.__summaryTempFileLines = []                #contains strings with relevant output for all temperature variables
        self.__summaryHeatFluxFileLines = []            #contains strings with relevant output for all heat flux variables
        self.__summaryFileLines_Z = []                  #contains string comment for zones of interest

        self.__projectDirectory = ""                    #identified during initialization
        self.__projectFileName = ""                     #identified during initialization

        self.__pathsDefined = False                     #True if all (following four) file paths have been identified during zone initialization
        self.__pathClimate = ""                         #complete absolute folder of climate subfolders containing ccd files
        self.__pathMaterials = ""                       #complete absolute folder of material files containing m6- files
        self.__pathConstructions = ""                   #complete absolute folder of construction files containing d6p files
        self.__pathTemplates = ""                       #complete absolute folder of remaining files (schedules, spacetypes, outputs)

        
        #protected attributes: don't modify externally
        self._nandrad_xml_initialized = False           #True if initialization was successful, False if not
        



        
    #purpose of this function:  Function checks Nandrad project xml file.
    #submitted variables:       nandrad_xml > complete file path and name of nandrad project file
    #return values:             false > xml is invalid (pure xml structure check, no nandrad- specific check)
    #                           true > xml structure is valid
    def init_file(self, nandrad_xml): 
        
        #check submitted xml file structure
        try:
            self.__nandrad_xml_tree = DOM.parse( nandrad_xml )
        except Exception:
            self._nandrad_xml_initialized = False
            return False
        else:
            self.__nandrad_xml = nandrad_xml
            self._nandrad_xml_initialized = True

            #get file location
            SystemSep = os.sep
            FolderList = nandrad_xml.split( SystemSep )             #get list of folders in given directory, last element is file name
            self.__projectFileName = FolderList[len(FolderList)-1]  #index of last element equals lenght of list - 1
            FolderList = FolderList[0:(len(FolderList)-1)]          #remove last element from list
            self.__projectDirectory = nandrad_xml.replace( self.__projectFileName , "")
            #print("Extracted project directory is %s." % self.__projectDirectory )
            #print("Extracted project name is %s." % self.__projectFileName )

            #call zone initialization
            self.__init_Zones()

            #finished
            return True


        
    #purpose of this function:  Function checks Nandrad project xml file for zone definitions and saves their ifc- indizes and project ids.
    #                           Call this function after initialization of the xml file structure (init_file)
    #submitted variables:       none
    #return values:             false > no zones defined or no ifc- definition in zone name included 
    #                           true > zones found and indizes saved
    def __init_Zones(self):

        if(self._nandrad_xml_initialized == False):
            print("Initialize Nandrad xml file firstly.")
            return False
        
        ##----------------------PARSE PATH DEFINITIONS AND GENERAL INFO-------------------------##

        #counter for number of identified paths
        noIdentLoc = 0

        #extract all relevant project database directories
        for phTag in self.__nandrad_xml_tree.getElementsByTagName("Placeholder"):

            #read values to be compared
            nameAtt = phTag.getAttribute("name")
            tagValue = phTag.firstChild.nodeValue

            stringPH = "${Project Directory}/"
            if (stringPH in tagValue):
                tagValue = tagValue.replace(stringPH , self.__projectDirectory)
            #else: direct definition of file path is assumed
            
            if( nameAtt == "Climate Database" ):
                self.__pathClimate = tagValue
                noIdentLoc += 1
            elif(nameAtt == "Construction Database"):
                self.__pathConstructions = tagValue
                noIdentLoc += 1
            elif(nameAtt == "Material Database"):
                self.__pathMaterials = tagValue
                noIdentLoc += 1
            elif(nameAtt == "Templates"):
                self.__pathTemplates = tagValue
                noIdentLoc += 1

        if(noIdentLoc != 4):
            print("Couldn't identify all path definitions. Only %d were identified." % noIdentLoc )
            self.__pathsDefined = False
        else:
            self.__pathsDefined = True


        ##----------------------IDENTIFY ZONE DEFINITIONS-------------------------##

        #loop over xml elements to get zone tag display name and id
        zoneTagList = self.__nandrad_xml_tree.getElementsByTagName("Zone")
        for zoneTag in zoneTagList:

            #get attributes display name and id
            zoneID = zoneTag.getAttribute("id")
            zoneIFC = zoneTag.getAttribute("displayName")
            firstPos = ""
            lastPos = ""
            
            if( "Ifc=" in zoneIFC) and ("}" in zoneIFC):
                firstPos = zoneIFC.find("=") + 1
                lastPos = zoneIFC.find("}")

            extractedIFC = zoneIFC[firstPos:lastPos]

            self.__zoneIFCKeys.append( extractedIFC  )
            self.__zoneIndizes.append( zoneID )                     
            
            self.__zoneIdDict[extractedIFC] = zoneID

        #check sucess
        if( len(self.__zoneIndizes) > 0) and (len(self.__zoneIFCKeys) > 0):
            self.__zonesInitialized = True
            return True
        else:
            return False

        


    #purpose of this function:  Function modifies xml tree and sets all zones from active to passive state
    #submitted variables:       none
    #return values:             false > modification wasn't successful
    #                           true > modification done
    def set_zonesToPassiveState(self):

        #loop over tags with name "zone" and modify them
        for zoneTag in self.__nandrad_xml_tree.getElementsByTagName("Zone"):

            #find attribute "type" and set value to "Active"
            zoneTag.attributes["type"].value == "Active"
          
        #loop over SpaceType tags and set it to "PassiveZone"
        for zoneTag in self.__nandrad_xml_tree.getElementsByTagName("SpaceTypeName"):

            #set Space Type to passive which is defined as space type property below
            zoneTag.firstChild.data = "PassiveZone"
            

        #-----Create new space type properties tags for passive zone-----#

        #first level
        #tagSpaceTypeProperties = self.__nandrad_xml_tree.createElement("SpaceTypeProperties")

        #second level
        #level not included in currend solver version
        tagSpaceTypes = self.__nandrad_xml_tree.createElement("SpaceTypes")

        #third level
        tagSpaceType = self.__nandrad_xml_tree.createElement("SpaceType")
        tagSpaceType.attributes["name"] = "PassiveZone"

        #fourth level
        tagIBKString_1 = self.__nandrad_xml_tree.createElement("IBK:String")
        tagIBKString_1.attributes["name"] = "HeatingControlMode"
        tagIBKString_2 = self.__nandrad_xml_tree.createElement("IBK:String")
        tagIBKString_2.attributes["name"] = "CoolingControlMode"
        
        #description tags are no more included in current solver version
        #tagShortDes = self.__nandrad_xml_tree.createElement("ShortDescription")
        #tagShortDes.attributes["name"] = "lang=""en"""
        #tagLongDes = self.__nandrad_xml_tree.createElement("LongDescription")
        #tagLongDes.attributes["name"] = "lang=""en"""

        #fifth level
        textIBKString_1 = self.__nandrad_xml_tree.createTextNode("None")
        textIBKString_2 = self.__nandrad_xml_tree.createTextNode("None")
        
        #description tags are no more included in current solver version
        #textShortDes = self.__nandrad_xml_tree.createTextNode("PassiveZone")
        #textLongDes = self.__nandrad_xml_tree.createTextNode("This tag is used for free-running zones.")

        
        #----- Create new node structure -----#

        #fourth level
        tagIBKString_1.appendChild( textIBKString_1 )
        tagIBKString_2.appendChild( textIBKString_2 )
        #tagShortDes.appendChild( textShortDes )
        #tagLongDes.appendChild( textLongDes )

        #third level
        tagSpaceType.appendChild( tagIBKString_1 )
        tagSpaceType.appendChild( tagIBKString_2 )
        #tagSpaceType.appendChild( tagShortDes )
        #tagSpaceType.appendChild( tagLongDes )
                
        #second level
        #this level is not included in current solver version
        #tagSpaceTypes.appendChild( tagSpaceType )

        #first level
        #tagSpaceTypeProperties.appendChild( tagSpaceType )
        tagSpaceTypes.appendChild( tagSpaceType ) # see following assignments of tag SpaceTypeProperties

        
        #-----replace old tag with new tag -----#
        
        #check if we have weather direct or indirect definition
        nandradProjectNode = self.__nandrad_xml_tree.getElementsByTagName("NandradProject")[0]
        spaTypeRefList = self.__nandrad_xml_tree.getElementsByTagName("SpaceTypesReference")
        spaTypeList = self.__nandrad_xml_tree.getElementsByTagName("SpaceTypeProperties")

        #reference of space types
        if spaTypeRefList.length == 1:
            nandradProjectNode.appendChild(  tagSpaceTypes )
            nandradProjectNode.removeChild( spaTypeRefList[0] )
        #direct definition of space types
        elif spaTypeList.length == 1:
            nandradProjectNode.appendChild(  tagSpaceTypes )
            nandradProjectNode.removeChild( spaTypeList[0] )         
        else:
            print("New space type properties definition failed.")
            return False

        return True

  
    #purpose of this function:  Function sets Solver parameters to minimum required exactness
    #submitted variables:       none
    #return values:             false > modification wasn't successful
    #                           true > modification done                
    def set_settings_toMinSolverSet(self):

        #search for solver parameter node
        nandradSolverSettingsNode = self.__nandrad_xml_tree.getElementsByTagName("SolverParameter")[0]

        #loop over child nodes, check attribute type and assign minimum value
        counterChanges = 0
        for node in nandradSolverSettingsNode.getElementsByTagName("IBK:Parameter"):
            currentNameAttValue = node.getAttribute("name")
            if ( currentNameAttValue == "MinTimeStep" ):
                node.firstChild.nodeValue = "1e-05"
                counterChanges += 1
            elif ( currentNameAttValue == "RelTol" ):
                node.firstChild.nodeValue = "1e-03"
                counterChanges += 1
            elif ( currentNameAttValue == "AbsTol" ):
                node.firstChild.nodeValue = "1e-04"
                counterChanges += 1
            elif ( currentNameAttValue == "DiscDetailLevel" ):
                node.firstChild.nodeValue = "0"
                counterChanges += 1
                
        if (counterChanges == 4):
            #print("All tags found and modified.")
            return True
        else:
            return False


 
    #purpose of this function:  Function replaces output reference tag with simple output definition of hourly operative Temperature
    #submitted variables:       none
    #return values:             false > modification wasn't successful
    #                           true > modification done                
    def set_output_toHourlyPassiveResults(self):

        #-----create output tags and assign hierarchy level-----#
        
        #parent level output (tag)
        tagOutputs = self.__nandrad_xml_tree.createElement("Outputs")
        
        #first level output (tags simplified as value entry) 
        tagIBKUnit = self.__nandrad_xml_tree.createElement("IBK:Unit")
        tagIBKUnit.attributes["name"] = "TimeUnit"
        textIBKUnit = self.__nandrad_xml_tree.createTextNode("d")
        
        tagIBKFlag = self.__nandrad_xml_tree.createElement("IBK:Flag")
        tagIBKFlag.attributes["name"] = "BinaryFormat"
        textIBKFlag = self.__nandrad_xml_tree.createTextNode("false")

        #first level output (tag)
        tagOutputGrids = self.__nandrad_xml_tree.createElement("OutputGrids")

        #second level output (tag and attribute)
        tagOutputGrid = self.__nandrad_xml_tree.createElement("OutputGrid") 
        tagOutputGrid.attributes["name"] = "Hourly"

        #third level output (tag) 
        tagInterval = self.__nandrad_xml_tree.createElement("Interval")

        #fourth level output  (tag simplified as value entry)
        tagIBKParam = self.__nandrad_xml_tree.createElement("IBK:Parameter")
        tagIBKParam.attributes["name"] = "StepSize"
        tagIBKParam.attributes["unit"] = "h"
        textIBKParam = self.__nandrad_xml_tree.createTextNode("1")

        #first level output (tag)
        tagOutputDefinitions = self.__nandrad_xml_tree.createElement("OutputDefinitions")

        #second level output (tag)
        tagOutputDefinition = self.__nandrad_xml_tree.createElement("OutputDefinition")

        #third level output (tag)
        tagOutputGridName = self.__nandrad_xml_tree.createElement("OutputGridName")

        #fourth level output (value)
        textOutputGridName = self.__nandrad_xml_tree.createTextNode("Hourly")

        #third level output (tag)
        tagObjectListName = self.__nandrad_xml_tree.createElement("ObjectListName")

        #fourth level output (value)
        textObjectListName = self.__nandrad_xml_tree.createTextNode("Active zones")

        #third level output (tag)
        tagQuantity = self.__nandrad_xml_tree.createElement("Quantity")
        
        #fourth level output (value)
        textQuantity = self.__nandrad_xml_tree.createTextNode("OperativeTemperature")

 
        #-----create hierarchy for these tags-----#
        
        #fourth and lower level below outputs
        tagIBKParam.appendChild( textIBKParam )
        tagInterval.appendChild( tagIBKParam )
        
        tagOutputGridName.appendChild( textOutputGridName )
        tagObjectListName.appendChild( textObjectListName )
        tagQuantity.appendChild( textQuantity )

        #third level below outputs
        tagOutputGrid.appendChild( tagInterval )
        tagOutputDefinition.appendChild( tagOutputGridName )
        tagOutputDefinition.appendChild( tagObjectListName )
        tagOutputDefinition.appendChild( tagQuantity )

        #second level below outputs
        tagIBKUnit.appendChild( textIBKUnit )
        tagIBKFlag.appendChild( textIBKFlag )
        tagOutputGrids.appendChild( tagOutputGrid )
        tagOutputDefinitions.appendChild( tagOutputDefinition )

        #first level below outputs
        tagOutputs.appendChild( tagIBKUnit )
        tagOutputs.appendChild( tagIBKFlag )
        tagOutputs.appendChild( tagOutputGrids )
        tagOutputs.appendChild( tagOutputDefinitions )

        #----clone output definition node to request conduction heat flux and short wave heat flux output -----#

        #copy OpTemp tag 
        tagOutputDefinitionCond = tagOutputDefinition.cloneNode( deep = True )
        tagOutputDefinitionSWRad = tagOutputDefinition.cloneNode( deep = True )

        #create new text elements
        textOutputQuantityCond = self.__nandrad_xml_tree.createTextNode("ConstructionHeatConductionGains")
        textOutputQuantitySWRad = self.__nandrad_xml_tree.createTextNode("WindowSWRadGains")

        #replace old text entries in copied tags with new entries
        tagToModify = tagOutputDefinitionCond.getElementsByTagName("Quantity")[0]
        tagToModify.replaceChild( textOutputQuantityCond, tagToModify.firstChild )
        tagToModify = tagOutputDefinitionSWRad.getElementsByTagName("Quantity")[0]
        tagToModify.replaceChild( textOutputQuantitySWRad, tagToModify.firstChild )

        #append new elements
        tagOutputDefinitions.appendChild( tagOutputDefinitionCond )
        tagOutputDefinitions.appendChild( tagOutputDefinitionSWRad )
          

        #-----search for outputs reference tag or outputs definition tag-----#
        newTag = oldTag = tagOutputs
        
        outRefList = self.__nandrad_xml_tree.getElementsByTagName("OutputsReference")
        outList = self.__nandrad_xml_tree.getElementsByTagName("Outputs")
        nandradProjectNode = self.__nandrad_xml_tree.getElementsByTagName("NandradProject")[0]
        
        if outRefList.length == 1:
            
            #replace tag reference with output specification
            try:
                nandradProjectNode.appendChild( newTag )
                nandradProjectNode.removeChild( outRefList[0] )
            except Exception:
                print("Reference element was found but not replaced.")
                return False
            else:
                return True

        elif outList.length == 1:
            
            #replace output tag with output specification
            try:
                nandradProjectNode.appendChild( newTag )
                nandradProjectNode.removeChild( outList[0] )
            except Exception:
                print("Definition element was found but replaced.")
                return False
            else:
                return True
            
        else:
            #no output was defined in submitted file, maybe invalid file
            return False


 



    #purpose of this function:  Function creates new nandrad file, call after modification (passive state, output)
    #submitted variables:       nandrad_xml_new > complete file path and name of the new file
    #return values:             false > file writing wasn't successful
    #                           true > file was created     
    def write_nandradXmlFile(self, nandrad_xml_new):
        
        #create output file object
        outputFileObject = open( nandrad_xml_new, "w")
        #write modified xml output
        try:
            outputFileObject.write(self.__nandrad_xml_tree.toprettyxml() )
            outputFileObject.close()
        except Exception:
            print("Modified xml was not saved in output file.")
            return False
        else:
            return True


    #purpose of this function:  Function starts nandrad simulation and returns True when it is finished 
    #submitted variables:       nandrad_xml > complete file path and name of the project file
    #                           nandrad_exe > complete file path and name of nandrad.exe
    #return values:             false > simulation failed
    #                           true > simulation started     
    def start_NandradSimulation(self, nandrad_xml , nandrad_exe ):
        try:
            #start execution of Nandrad and catch output tuple PIPE (stdout, stderr)
            executionProcess = subprocess.Popen( [nandrad_exe, nandrad_xml], shell=False ) #check argument "-x"

            #check status of simulation process: wait as long as Nandrad is running
            print("Nandrad simulation started with solver: %s \nSimulation in progress...." % (nandrad_exe) )

            #check if execution is terminated, can be .wait() or .poll()
            while executionProcess.wait() == None:
                #sys.stdout.flush()
                time.sleep( 300.0 ) #sleeping time in seconds: wait in five minutes steps

            #executionProcess.communicate()
                    

            #finished
            return True              
                
        except Exception:
                                  
            #open log file to check simulation results
            pointIndex = nandrad_xml.rfind(".")
            logFilePathWithoutEnding = nandrad_xml[0:pointIndex]
            logFilePath = logFilePathWithoutEnding + "\\log\\screenlog.log"
            textEditor = "notepad++.exe"
            logFileOpening = subprocess.Popen([textEditor, logFilePath], shell=False )

            #return status 
            return False
            
           



    #purpose of this function:  Function analyzes resulting operative temperatature and heat flux output (active zones) in nandrad project folder 
    #submitted variables:       nandrad_xmlFileString > complete file path and name of the project file, ensure original location of project file
    #                           getGraph > boolean value, true= create png graphs of the outputs, false = don't create graphs
    #return values:             false > no analysis possible and thus no summary file created, value list has default values
    #                           true > analyis done, file created, value list filled
    def start_zoneAnalysisPassiveResults( self, nandrad_xmlFileString, getGraph):  

        #check if zone analysis is done, otherwise call zone analysis again
        if (self.__zonesInitialized == False):
            self.init_file(nandrad_xmlFileString)
            self.init_Zones()
            print("Initialization of Nandrad file wasn't done up to now and is called automatically now.")

        #define settings for graph plot
        pic_dx = 20.0
        pic_dy = 10.0
        pic_res = 200
                    
        
        #get results folder location and name
        #folder name of output equals project name, subfolders are always in the same structure
        pointIndex = nandrad_xmlFileString.rfind(".")
        nameWithoutEnding = nandrad_xmlFileString[0:pointIndex]

        #init summary lists for output file with comments
        self.__summaryTempFileLines.append("Summary output for Nandrad passive Simulation of project %s.\n" % (nandrad_xmlFileString))
        self.__summaryHeatFluxFileLines.append("Summary output for Nandrad passive Simulation of project %s.\n" % (nandrad_xmlFileString))
        self.__summaryFileLines_Z.append("Zones of interest summary for project file: %s\nNote: identical results are not considered, init id = 1 \nZones of Interest are: \n" % (nandrad_xmlFileString) )
        
        resultsFileTypes = []
        resultsFileTypes.append("OperativeTemperature")
        resultsFileTypes.append("WindowSWRadGains")
        resultsFileTypes.append("ConstructionHeatConductionGains")

       

        for resultsFileType in resultsFileTypes:

            #create Lists for data analysis
            zoneIndexList = []                      #list of all zone ids in results file
            maxTimeStepOutput = 8760                #init value for hourly output of one year
            maxZoneNumberOutput = len(self.__zoneIndizes)
            valueList = NP.empty((maxTimeStepOutput, maxZoneNumberOutput))
            valueList.fill( -80.0 )                  #fill numpy array with default values


            #create current file name and open file object
            resultsFilePathAndName = nameWithoutEnding + os.sep + "results" + os.sep + "Zones_" + resultsFileType + "_objectList(Active_zones).d6o"        
            print("Parsing results file: %s" % (resultsFilePathAndName) )
            try:
                resultsFile = open( resultsFilePathAndName, "r")
            except Exception:
                print("One or more results files are not existing or in another folder than project\\results\\..")
                return False

            #create values which keep data type and unit, min and max Values
            resultsUnit = "no unit"
            resultsType = "no type"
            yMin = 0
            yMax = 0
            
         
            #read and extract data from output file, set counters and data flags
            startData = False
            timeStepCounter = 0         
            for line in resultsFile:

                #search for flag line and for definition lines in header lines
                if (startData == False) :
                    #get type of current output file
                    if line.find("QUANTITY_KW") != -1:

                        #skip first characters, quantity definition
                        endPosition = len(line)
                        newline = line[16:endPosition]                   
                        resultsType = newline.strip()
                        
                    #get unit of current output file
                    if line.find("VALUE_UNIT") != -1:

                        #skip first characters, quantity definition
                        endPosition = len(line)
                        newline = line[16:endPosition]                   
                        resultsUnit = newline.strip()
                        
                    #get zone index values 
                    if line.find("INDICES") != -1:

                        startData = True
                       
                        #skip first characters, read index definition
                        endPosition = len(line)
                        newline = line[16:endPosition]
                        stringIndizes = newline.split() #whitespaces between indices: 1 2 3 ...
                        indexCounter = 0
                        for element in stringIndizes:
                            zoneIndexList.append( int(float(element)) )
                            indexCounter += 1
         
                #skip first line after index definition (empty line)
                elif ((startData == True) and (timeStepCounter == 0)):
                    timeStepCounter += 1
                
                #start data parsing if indizes were defined and empty line was skipped
                elif ((startData == True) and (timeStepCounter > 0) and (timeStepCounter < (maxTimeStepOutput+1))):
                  
                    for zoneIndexCounter in range( len(zoneIndexList) ):
                        
                        #get string extract with current value (position definition)
                        start = 13 + zoneIndexCounter * 16  #first value starts at position 13
                        end = 27 + zoneIndexCounter * 16    #value output lenght is 16 positions
                        currentTimeStepValueString = line[start:end]

                        #convert value string and save in output list
                        currentTimeStepValue = float( currentTimeStepValueString )
                        if (currentTimeStepValue > yMax):
                            yMax = currentTimeStepValue
                        elif(currentTimeStepValue < yMin):
                            yMin = currentTimeStepValue

                        #submit values to list 
                        valueList[(timeStepCounter-1), zoneIndexCounter] =  currentTimeStepValue 
                        
                    #raise time step counter after line (one time step) parsing
                    timeStepCounter += 1

            #close
            resultsFile.close()
     
            # Detailed output for Temperature Values
            if (resultsUnit == "C"):

                #produce summary results and save it in member variables
                self.__summaryTempFileLines.append("Statistic data series summary of NANDRAD results file %s \n" % (resultsFilePathAndName))
                self.__summaryTempFileLines.append("Statistic data series summary for output quantity: %s \n" % (resultsType))

                #Create Box Plot Graph if requested
                if (getGraph == True):
                    #plot data
                    Figure = PLOT.figure(None, figsize=(pic_dx, pic_dy), dpi=pic_res)
                    BoxPlot = PLOT.boxplot(valueList, notch=0, sym='+', vert=1, whis=1.5)
                    PLOT.setp( BoxPlot['boxes'], color='black' )
                    PLOT.setp( BoxPlot['whiskers'], color='black' )
                    #PLOT.setp( BoxPlot['fliers'], color='red', marker='+' )

                    #modify layout of boxplot graph
                    minIndex = min(zoneIndexList)
                    maxIndex = max(zoneIndexList)
                    PLOT.xticks( range((minIndex),(maxIndex),5))
                    PLOT.xlabel('Zone Indices')
                    PLOT.ylabel('%s [%s]' % (resultsType, resultsUnit) )
                    PLOT.ylim(( (yMin - 5), (yMax + 5.0) ))
                    PLOT.xlim((minIndex-0.5,maxIndex+0.5))
                    PLOT.grid(True)
                    titleString = "BoxPlot graph of NANDRAD Passive Simulation Results"
                    PLOT.title( titleString )

                #create output file strings for temperature values
                self.__summaryTempFileLines.append("\nZone Index, IFCKey, Median[%s], Min[%s], Max[%s], FirstQuartile[%s], ThirdQuartile[%s], WhiskerMin[%s], WhiskerMax[%s] \n" % (resultsUnit,resultsUnit,resultsUnit,resultsUnit,resultsUnit,resultsUnit,resultsUnit) )

                #variables and ids for most critical zones
                HighestQuartileRange = 0.0
                HighestWhiskerRange = 0.0
                LowestQuartileRange = 20.0
                LowestWhiskerRange = 2.0
                LowestTemperature = 20.0
                HighestTemperature = -20.0
                #TODO:  change format, keep only index
                #zone indizes
                HQR_id = 1
                HWR_id = 1
                LQR_id = 1
                LWR_id = 1
                LT_id = 1
                HT_id = 1
                #zone ifc- indizes
                HQR_ifc = ""
                HWR_ifc = ""
                LQR_ifc = ""
                LWR_ifc = ""
                LT_ifc = ""
                HT_ifc = ""
                
                
                #zone index loop
                for zoneCounter in range(0,len(zoneIndexList)):
                    
                    #create one-dimensional array for each zone
                    singleZoneDataSeries = valueList[0:maxTimeStepOutput,zoneCounter]

                    #get direct values from numpy
                    medianValue = NP.median(singleZoneDataSeries)
                    minValue = NP.min(singleZoneDataSeries)
                    maxValue = NP.max(singleZoneDataSeries)

                    #get first and third quartile (scipy, see import line on top)
                    firstQuartile = scoreatpercentile(singleZoneDataSeries, 25)
                    thirdQuartile = scoreatpercentile(singleZoneDataSeries, 75)

                    #get corrected min/max values (whisker)
                    QuartileRange = thirdQuartile- firstQuartile
                    whiskerDistance= 1.5 * QuartileRange
                    minValueC = medianValue - whiskerDistance
                    maxValueC = medianValue + whiskerDistance

                    #get output for current zone index
                    self.__summaryTempFileLines.append("%s, %s, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f \n" %(self.__zoneIndizes[zoneCounter], self.__zoneIFCKeys[zoneCounter], medianValue,minValue,maxValue,firstQuartile,thirdQuartile,minValueC, maxValueC))

                    #get most critical zones
                    if (QuartileRange > HighestQuartileRange):
                        HighestQuartileRange = QuartileRange
                        if (self.__zonesInitialized == True): 
                            HQR_id = self.__zoneIndizes[zoneCounter]
                            HQR_ifc = self.__zoneIFCKeys[zoneCounter]
                    if ((2.0 * whiskerDistance) >  HighestWhiskerRange):
                        HighestWhiskerRange = (2.0 * whiskerDistance)
                        if (self.__zonesInitialized == True): 
                            HWR_id = self.__zoneIndizes[zoneCounter]
                            HWR_ifc = self.__zoneIFCKeys[zoneCounter]
                    if (QuartileRange < LowestQuartileRange):
                        LowestQuartileRange = QuartileRange
                        if (self.__zonesInitialized == True): 
                            LQR_id = self.__zoneIndizes[zoneCounter]
                            LQR_ifc = self.__zoneIFCKeys[zoneCounter]
                    if ((2.0 * whiskerDistance) <  LowestWhiskerRange):
                        LowestWhiskerRange = (2.0 * whiskerDistance)
                        if (self.__zonesInitialized == True): 
                            LWR_id = self.__zoneIndizes[zoneCounter]
                            LWR_ifc = self.__zoneIFCKeys[zoneCounter]
                    if (minValueC < LowestTemperature):
                        LowestTemperature = minValueC
                        if (self.__zonesInitialized == True): 
                            LT_id = self.__zoneIndizes[zoneCounter]
                            LT_ifc = self.__zoneIFCKeys[zoneCounter]
                    if (maxValueC > HighestTemperature):
                        HighestTemperature = maxValueC
                        if (self.__zonesInitialized == True): 
                            HT_id = self.__zoneIndizes[zoneCounter]
                            HT_ifc = self.__zoneIFCKeys[zoneCounter]

                #save zones of interst Nandrad ids in member list
                self.__zonesOfInterestIndizes.append( HQR_id )
                self.__zonesOfInterestIndizes.append( HWR_id )
                self.__zonesOfInterestIndizes.append( LQR_id )
                self.__zonesOfInterestIndizes.append( LWR_id )
                self.__zonesOfInterestIndizes.append( LT_id )
                self.__zonesOfInterestIndizes.append( HT_id )
                
                #save zones of interst ifc ids in member list
                self.__zonesOfInterestKeys.append( HQR_ifc )
                self.__zonesOfInterestKeys.append( HWR_ifc )
                self.__zonesOfInterestKeys.append( LQR_ifc )
                self.__zonesOfInterestKeys.append( LWR_ifc )
                self.__zonesOfInterestKeys.append( LT_ifc )
                self.__zonesOfInterestKeys.append( HT_ifc ) 

                #write output line for most critical zones
                self.__summaryFileLines_Z.append("%s, %s, highest quartile range, %.2f [%s]\n"  % (HQR_id, HQR_ifc, HighestQuartileRange, resultsUnit) )
                self.__summaryFileLines_Z.append("%s, %s, highest whisker range, %.2f [%s]\n"  % (HWR_id, HWR_ifc, HighestWhiskerRange, resultsUnit) )
                self.__summaryFileLines_Z.append("%s, %s, lowest quartile range, %.2f [%s]\n"  % (LQR_id, LQR_ifc, LowestQuartileRange, resultsUnit) )
                self.__summaryFileLines_Z.append("%s, %s, lowest whisker range, %.2f [%s]\n"  % (LWR_id, LWR_ifc, LowestWhiskerRange, resultsUnit) )
                self.__summaryFileLines_Z.append("%s, %s, lowest operative temperature, %.2f [%s]\n"  % (LT_id, LT_ifc, LowestTemperature, resultsUnit) )
                self.__summaryFileLines_Z.append("%s, %s, highest operative temperature, %.2f [%s]\n"  % (HT_id, HT_ifc, HighestTemperature, resultsUnit))


                #Comment Box Plot Graph 
                if (getGraph == True):
                    
                    #diplay zones of interest: Highest Quartile Range
                    xPosArr = float(HQR_id)
                    yPosArr = HighestTemperature - 2.0
                    xPosText = float(HQR_id) + 2.0
                    yPosText = HighestTemperature 
                    PLOT.annotate(("Zone: " + HQR_ifc), xy=(xPosArr, yPosArr), xytext=(xPosText, yPosText),
                                  arrowprops=dict(arrowstyle="->",connectionstyle="angle,angleA=0,angleB=90,rad=10"),)

                    #diplay zones of interest: Highest Temperature
                    xPosArr = float(HT_id)
                    yPosArr = HighestTemperature -2.0
                    xPosText = float(HT_id) + 2.0
                    yPosText = HighestTemperature 
                    PLOT.annotate(("Zone: " + HT_ifc), xy=(xPosArr, yPosArr), xytext=(xPosText, yPosText),
                                  arrowprops=dict(arrowstyle="->",connectionstyle="angle,angleA=0,angleB=90,rad=10"),)

                    #diplay zones of interest: Lowest Temperature
                    xPosArr = float(LT_id)
                    yPosArr = HighestTemperature - 2.0
                    xPosText = float(LT_id) + 2.0
                    yPosText = HighestTemperature 
                    PLOT.annotate(("Zone: " + LT_ifc), xy=(xPosArr, yPosArr), xytext=(xPosText, yPosText),
                                  arrowprops=dict(arrowstyle="->",connectionstyle="angle,angleA=0,angleB=90,rad=10"),)
                    
                #write output    
                outputFileString = nameWithoutEnding + "\\results\\" + resultsFileType + "_summary.txt"
                summaryFile = open( outputFileString, "w")
                for line in self.__summaryTempFileLines:
                    summaryFile.write( line )
                summaryFile.close()
                del self.__summaryTempFileLines[:]
                
            if (resultsUnit == "W"):

                #produce summary results and save it in member variables
                self.__summaryHeatFluxFileLines.append("Statistic data series summary of NANDRAD results file %s \n" % (resultsFilePathAndName))
                self.__summaryHeatFluxFileLines.append("Statistic data series summary for output quantity: %s \n" % (resultsType))

                #create output file strings for heat flux values
                self.__summaryHeatFluxFileLines.append("\nZone Index, IFCKey, SumOfAnnualLosses[%s], SumOfAnnualGains[%s]\n" % (resultsUnit,resultsUnit))

                #Create bar chart for heat flux if requested
                if (getGraph == True):
                    Figure = PLOT.figure(None, figsize=(pic_dx, pic_dy), dpi=pic_res)
                    #Figure = PLOT.figure(None, figsize=(20, 10), dpi=200)
                    SubFigure = Figure.add_subplot(111)
                
                #create data for heat flux/ radiation gains: sum of gains and losses for each zone
                sumOfAnnualGains = []
                sumOfAnnualLosses= []
                
                #get most critical zone data
                HighestAnnualGains = 0.0
                HighestAnnualLosses = 0.0
                AG_id = 1
                AG_ifc = ""
                AL_id = 1
                AL_ifc = ""
                
                for zoneCounter in range(0,len(zoneIndexList)):
                    posValueList = [n for n in valueList[:,zoneCounter] if n > 0]
                    negValueList = [n for n in valueList[:,zoneCounter] if n < 0]
                    CurrentGains = sum(posValueList)
                    CurrentLosses = sum(negValueList)
                    sumOfAnnualGains.append( CurrentGains) 
                    sumOfAnnualLosses.append( CurrentLosses )
                    
                    #get output string for summary text file
                    self.__summaryHeatFluxFileLines.append("%s, %s, %.2f, %.2f \n" %(self.__zoneIndizes[zoneCounter], self.__zoneIFCKeys[zoneCounter], CurrentLosses, CurrentGains))
                    if(CurrentGains > HighestAnnualGains):
                        HighestAnnualGains = CurrentGains
                        if (self.__zonesInitialized == True):
                            AG_id = self.__zoneIndizes[zoneCounter]
                            AG_ifc = self.__zoneIFCKeys[zoneCounter]
                    if(CurrentLosses < HighestAnnualLosses):
                        HighestAnnualLosses = CurrentLosses
                        if (self.__zonesInitialized == True):
                            AL_id = self.__zoneIndizes[zoneCounter]
                            AL_ifc = self.__zoneIFCKeys[zoneCounter]

                #save zones of interst Nandrad ids in member list
                self.__zonesOfInterestIndizes.append( AG_id )
                self.__zonesOfInterestIndizes.append( AL_id )
                
                #save zones of interst ifc ids in member list
                self.__zonesOfInterestKeys.append( AG_id )
                self.__zonesOfInterestKeys.append( AL_id )                

                #get critical zones output summary
                self.__summaryFileLines_Z.append("%s, %s, highest cumulated heat flux (gains of %s), %.2f [%s]\n"  % (AG_id, AG_ifc, resultsType, HighestAnnualGains, resultsUnit) )
                if (resultsFileType != "WindowSWRadGains"):
                    self.__summaryFileLines_Z.append("%s, %s, highest cumulated heat flux (losses of %s), %.2f [%s]\n"  % (AL_id, AL_ifc, resultsType, HighestAnnualLosses, resultsUnit) )   

                
                #Create graph output of results if requested
                if (getGraph == True):
                    
                    d_bars = 0.35
                    maxLosses = min(sumOfAnnualLosses)
                    maxGains = max(sumOfAnnualGains)
                    yAxisTol = max( abs(maxLosses), abs(maxGains)) * 0.15 #five percent tolerance for axis
                    
                    
                    BarsGains = SubFigure.bar( zoneIndexList, sumOfAnnualGains, d_bars, color='green' )
                    BarsLosses = SubFigure.bar( zoneIndexList, sumOfAnnualLosses, d_bars, color='red' ) 

                    # axes and labels
                    SubFigure.set_xlim((minIndex-0.5,maxIndex+0.5))
                    SubFigure.set_ylim( (( maxLosses - yAxisTol ), ( maxGains + yAxisTol )) )
                    SubFigure.set_ylabel('%s [%s]' % (resultsType, resultsUnit) )
                    SubFigure.set_xlabel('Zone Indices')
                    SubFigure.set_title('Annual sum of heat fluxes')

                    PLOT.grid(True)
                    
                    #diplay zones of interest
                    xPosArr = float(AG_id)
                    yPosArr = HighestAnnualGains
                    xPosText = float(AG_id) + 2.0
                    yPosText = HighestAnnualGains + (0.1 * HighestAnnualGains)
                    PLOT.annotate(("Zone: " + AG_ifc), xy=(xPosArr, yPosArr), xytext=(xPosText, yPosText),
                                  arrowprops=dict(arrowstyle="->",connectionstyle="angle,angleA=0,angleB=90,rad=10"),)
                    
                    xPosArr = float(AL_id)
                    yPosArr = HighestAnnualLosses
                    xPosText = float(AL_id) + 2.0
                    yPosText = HighestAnnualLosses + (0.15 * HighestAnnualLosses)
                    PLOT.annotate(("Zone: " + AL_ifc), xy=(xPosArr, yPosArr), xytext=(xPosText, yPosText),
                                  arrowprops=dict(arrowstyle="->",connectionstyle="angle,angleA=0,angleB=90,rad=10"),)

                    ## add a legend
                    SubFigure.legend( (BarsGains[0], BarsLosses[0]), ('Gains', 'Losses') )

                #write output
                outputFileString = nameWithoutEnding + os.sep + "results" + os.sep + resultsFileType + "_Summary.txt"
                summaryFile = open( outputFileString, "w")
                for line in self.__summaryHeatFluxFileLines:
                    summaryFile.write( line )
                summaryFile.close()
                del self.__summaryHeatFluxFileLines[:]

            
            #Create chart/graph output for evaluated data if requested
            if (getGraph == True):
                
                #output of higher an lower resolution graph
                outputFileStringGraph = resultsFilePathAndName.replace("_objectList(Active_zones).d6o", "_200dpi.png")
                PLOT.savefig( outputFileStringGraph, bbox_inches='tight' )
                PLOT.savefig( outputFileStringGraph.replace("_200dpi.png", "_50dpi.png"), bbox_inches='tight', dpi=50)
    
                
        #Create text output summary for evaluated data
        outputFileStringSummary = nameWithoutEnding + os.sep + "results" + os.sep + "ZonesOfInterest_Summary.txt"
        summaryFile = open( outputFileStringSummary, "w")
        for line in self.__summaryFileLines_Z:
            summaryFile.write( line )
        summaryFile.close() 

        #succeeded
        self.__zonesOfInterestInitialized = True
        return True



    #purpose of this function:  Function returns list of nandrad project zone ids resp. ifc keys
    #                           call after initialization,
    #submitted variables:       none
    #return values:             list of strings of ids     
    def returnAllZoneIDs(self):
        #check if zones were initialized
        if (self.__zonesInitialized == False):
            print("Zones not initialized yet.")
        return self.__zoneIndizes
    
    def returnAllZoneIFCKeys(self):
        #check if zones were initialized
        if (self.__zonesInitialized == False):
            print("Zones not initialized yet.")
        return self.__zoneIFCKeys


    #purpose of this function:  Function returns list of nandrad project zone ids for those zones which are of interest
    #                           call after initialization, modification, run and passive analysis
    #submitted variables:       none
    #return values:             list of strings of ids     
    def returnZonesOfInterestIDs(self):
        #check if project analysis was done
        if (self.__zonesOfInterestInitialized == False):
            print("Start passive analysis routine firstly to create list for zones of interest.")    
        return self.__zonesOfInterestIndizes



    #purpose of this function:  Function returns list of ifc zone ids for those zones which are of interest
    #                           call after initialization, modification, run and passive analysis
    #submitted variables:       none
    #return values:             list of strings of ids     
    def returnZonesOfInterestIFCKeys(self):
        #check if project analysis was done
        if (self.__zonesOfInterestInitialized == False):
            print("Start passive analysis routine firstly to create list for zones of interest.")  
        return self.__zonesOfInterestKeys

        
    #purpose of this function:  Function returns zone object for a specific nandrad id
    #                           call after initialization (file and zones)
    #submitted variables:       int_zoneId --> Nandrad zone id  or ifc key
    #                           str_type --> string which defines type of submitted key: "ID"= Nandrad- ID, "IFC" = Ifc-key of the zone
    #return values:             Zone object
    def returnZoneObject(self, str_zoneId, str_type):

        #check which type we have
        if( str_type == "ID"):
            zoneId = str_zoneId
        elif( str_type == "IFC"):
            if( str_zoneId not in list(self.__zoneIdDict.keys()) ):
                print("NandradProject: ReturnZoneObject: undefined IFC key submitted. Suchlike zone doesn't exist in this project file.")
            zoneId = str( self.__zoneIdDict[ str_zoneId ] )
        else:
            print("NandradProject: ReturnZoneObject: undefined id type submitted. Allowed are: IFC and ID.")

            
        #create new zone project and assign default values
        newZoneName = "Zone " + zoneId
        newZoneObject = ZoneObject( newZoneName )

        #init searched zone properties
        area = 0.0
        height = 0.0
        zoneDisplayName = ""
        spaceTypeName = ""
        spaceType = SpaceType.ACTIVE
        
        
        #check if xml was interpreted yet
        if ( self._nandrad_xml_initialized == False):
            print("Submit valid file path and init project and zones firstly.")

            #assign default values for yet not implemented settings
            newZoneObject.assign_defaultData()
            
        else:


            ##--------------------------ZONE PROPERTIES-----------------------------##

            #initialization was done, search for desired zone in xml tree
            for zoneNode in self.__nandrad_xml_tree.getElementsByTagName("Zone"):
                
                #check if we have the desired zone
                nandradZoneId = zoneNode.getAttribute("id")
                zoneDisplayName = zoneNode.getAttribute("displayName")
                zoneType = zoneNode.getAttribute("type")


                if ( int(nandradZoneId) == int(zoneId) ):
                    #print("Matching zone was found.")                    
                    #get zone data via IBK Parameter
                    for subNode in zoneNode.getElementsByTagName("IBK:Parameter"):
                        if ( subNode.getAttribute("name") == "Height" ):
                            height = subNode.firstChild.nodeValue
                        elif ( subNode.getAttribute("name") == "Area" ):
                            area = subNode.firstChild.nodeValue

                    #get space type name
                    spaceTypeName = zoneNode.getElementsByTagName("SpaceTypeName")[0].firstChild.nodeValue
                    #print("Space type name for this zone is %s" % spaceTypeName )


                    #transfer space type
                    if (zoneType == "Active"):
                        spaceType = SpaceType.ACTIVE
                    else:
                        spaceType = SpaceType.PASSIVE


                    break
                

            #extract ifcString and name for zone object
            firstPos = zoneDisplayName.find("{") + 8
            lastPos = zoneDisplayName.find("}") - 1
            ifcString = zoneDisplayName[firstPos:lastPos]
            zoneName = zoneDisplayName[0:(firstPos-9)]
          
            #submit extracted values to zone object
            newZoneObject.area = area
            newZoneObject.height = height
            newZoneObject.volume = str( float(area) * float(height))
            newZoneObject.ifc = ifcString
            newZoneObject.id = zoneId
            newZoneObject.name = zoneName
            newZoneObject.spaceType = spaceType
            newZoneObject.spaceTypeName = spaceTypeName

                
            ##----------------------FIND ZONE CONSTRUCTIONS (SURFACES)-------------------------##        


            #read construction definition 
            for constrNode in self.__nandrad_xml_tree.getElementsByTagName("ConstructionInstance"):

                #get interfaces of this construction node
                for idNode in constrNode.getElementsByTagName("ZoneID"):

                    #check if one of the two interfaces is adjacent to the current zone
                    refZoneId = idNode.firstChild.nodeValue
                    if( int(refZoneId) == int(zoneId)):
                        #print("Matching surface found.")

                        #create new construction object and get values
                        newConstruction = Construction("Construction")

                        #init values
                        c_ori = "0"
                        c_inc = "0"
                        c_area = "0"
                        c_id = "0"
                        c_Rsi = "0"
                        c_Rse = "0"

                        c_ifc = ""
                        c_source = ""
                        
                        ##----------------------Construction attributes-------------------------##

                        c_ifc = constrNode.getAttribute("fromElement")
                        c_source = constrNode.getAttribute("source")

                        
                        ##----------------------General Surface Properties-------------------------##

                        #search for xml entries
                        for param in constrNode.getElementsByTagName("IBK:Parameter"):
                            if (param.getAttribute("name") == "Orientation"):
                                c_ori = param.firstChild.nodeValue
                            elif (param.getAttribute("name") == "Inclination"):
                                c_inc = param.firstChild.nodeValue
                            elif (param.getAttribute("name") == "Area"):
                                c_area = param.firstChild.nodeValue

                        #get interface values, always two interfaces defined
                        firstIntTag = constrNode.getElementsByTagName("Interface")[0]
                        secIntTag = constrNode.getElementsByTagName("Interface")[1]

                        if (firstIntTag.getAttribute("location") == "A" ) and ( firstIntTag.getElementsByTagName("ZoneID")[0].firstChild.nodeValue == "0"):
                            #in this case, interface is adjacent to outside
                            c_type = ConstructionType.EXTERNAL
                        else:
                            #in this case, internal interface
                            c_type = ConstructionType.INTERNAL_AD

                        #get surface properties
                        tag_Rse = firstIntTag.getElementsByTagName("IBK:Parameter")[0]
                        if( tag_Rse.getAttribute("name") == "HeatTransferCoefficient"):
                            c_Rse = str(1.0 /float(tag_Rse.firstChild.nodeValue))
                        else:
                            c_Rse = "0.04"
                        
                        tag_Rsi = secIntTag.getElementsByTagName("IBK:Parameter")[0]
                        if( tag_Rsi.getAttribute("name") == "HeatTransferCoefficient"):
                            c_Rsi = str(1.0 /float(tag_Rsi.firstChild.nodeValue))
                        else:
                            c_Rsi = "0.13"

                        ##----------------------Referenced Construction Objects (d6p) and material objects (m6)-------------------------##
                                
       
                        #get database id
                        c_id = constrNode.getElementsByTagName("ConstructionTypeID")[0].firstChild.nodeValue

                        #get correlating folder path for this id
                        for consTypeRefTag in self.__nandrad_xml_tree.getElementsByTagName("ConstructionTypeReference"):
                            nameWithID = consTypeRefTag.getAttribute("displayName")
                            extrID = nameWithID.split("_").pop()                    #underscore splits name from id, get last element (id)
                            if( extrID == c_id ):
                                #print("Fitting construction in constr. type references found.")
                                newPathWithSep = self.__pathConstructions + os.sep
                                pathNameConstruction = consTypeRefTag.firstChild.nodeValue.replace("${Construction Database}/", newPathWithSep)
             
                        #assign values to construction object
                        newConstruction.assign_angles( c_inc, c_ori )
                        newConstruction.assign_area ( c_area, c_type )
                        newConstruction.assign_NandradConstructionID( c_id )
                        newConstruction.assign_surfaceProp( c_Rsi, c_Rse)
                        newConstruction.assign_Identifiers( c_ifc, c_source)

                        #read construction file and parse information from it
                        if (newConstruction.read_DelphinDataSet( pathNameConstruction, self.__pathMaterials ) == True):
                            flag=1
                            #print("Delphin dataset for construction %s was read." % c_id )
                        #else:
                            #print("No Delphin dataset for construction %s could be read." % c_id )
                        


                        ##--------------------EmbeddedObjects(Windows)-------------------##

                        #get interfaces of this construction node
                        for winNode in constrNode.getElementsByTagName("EmbeddedObject"):
                        #for winNode in constrNode.getElementsByTagName("InvalidForTesting"):

                            if( len(winNode.getElementsByTagName("Window")) > 0):

                                #print("Embedded object of type window was found.")
                                
                                #init values
                                area = 0.0
                                uValue = 0.0
                                shgc = 0.0
                                fGlass = 0.0
                                fSha = 0.0

                                eO_ifc = ""
                                eO_source = ""

                                #get identifiers
                                eO_ifc = winNode.getAttribute("fromElement")
                                eO_source = winNode.getAttribute("template")

                                #read properties, all defined in IBK:Parameter tags
                                for parNode in winNode.getElementsByTagName("IBK:Parameter"):

                                    #area
                                    if( parNode.getAttribute("name") == "Area" ):
                                        area = float( parNode.firstChild.nodeValue )                                        
                                    #uvalue
                                    if( parNode.getAttribute("name") == "ThermalTransmittance" ):
                                        uValue = float( parNode.firstChild.nodeValue )                                           
                                    #solar heat gain coefficient
                                    if( parNode.getAttribute("name") == "SolarHeatGainCoefficient" ):
                                        shgc = float( parNode.firstChild.nodeValue )
                                    #glass fraction
                                    if( parNode.getAttribute("name") == "GlassFraction" ):
                                        fGlass = float( parNode.firstChild.nodeValue )   
                                    #shading factor
                                    if( parNode.getAttribute("name") == "ShadingFactor" ):
                                        fSha = float( parNode.firstChild.nodeValue )   

                                #create embedded object for this construction
                                #newConstruction.add_embeddedObject( uValue, shgc, fGlass, area ) #--> old version: remove later
                                newConstruction.add_embeddedObjectWithIDs( uValue, shgc, fGlass, area, eO_ifc ,eO_source)
                                
                            else:
                                print("Unknown embedded object type (no window) is ignored.")
                            
                            
                        #add construction to zone object
                        newZoneObject.add_Construction( newConstruction )
                        #print("Identified surface/construction properties are: %s,%s,%s,%s,%s,%s,%s " % (c_inc, c_ori, c_area, c_type, c_id, c_Rsi, c_Rse) )
                      
                
                
            ##--------------------------SPACE TYPES / SCHEDULES-----------------------------##

            #zone space type name was identified in zone block and saved as "spaceTypeName"
            #properties we need for complete heating/cooling model definition
            #heat_schedule = ScheduleType.NONE
            #cool_schedule = ScheduleType.NONE
            heat_pmax = 0.0
            cool_pmax = 0.0
            heat_mode = "None"
            cool_mode = "None"
            heat_conType = "P"
            cool_conType = "P"
                        
            
            #check file for heating or cooling model
            for modelNode in self.__nandrad_xml_tree.getElementsByTagName("Model"):

                #see if we have a defined ideal heating model
                if (modelNode.getAttribute("model") == "HeatingModelIdeal"):
                    #see if this is defined for all zones
                    if (modelNode.getElementsByTagName("ObjectListName")[0].firstChild.nodeValue != "Active zones"):
                        #print("Ideal heating model was defined for all zones in the building")
                        #else:
                        print("Individual assignment object lists are not interpreted yet.")
                    #ideal heating model, get max power
                    for paramNode in modelNode.getElementsByTagName("IBK:Parameter"):
                        if (paramNode.getAttribute("name") == "MaximumHeatingPower"):
                            heat_pmax = float(paramNode.firstChild.nodeValue)

                #check for cooling model
                elif(modelNode.getAttribute("model") == "CoolingModelIdeal"):
                    #see if this is defined for all zones
                    if (modelNode.getElementsByTagName("ObjectListName")[0].firstChild.nodeValue != "Active zones"):
                        #print("Ideal cooling model was defined for all zones in the building")
                        #else:
                        print("Individual assignment object lists are not interpreted yet.")
                    #ideal heating model, get max power
                    for paramNode in modelNode.getElementsByTagName("IBK:Parameter"):
                        if (paramNode.getAttribute("name") == "MaximumCoolingPower"):
                            cool_pmax = float(paramNode.firstChild.nodeValue)
                else:
                    print("Unknown model type for heating/ cooling defined.")


            ##----------SPACE TYPE DEFINITIONS (EXTERNAL OR INTERNAL)------------------------------------------------------##

            #check file for space type definition for the heating model
            #see if space types are directly included in project file
            if( len( self.__nandrad_xml_tree.getElementsByTagName("SpaceTypeProperties")) > 0):

                #direct definition is given, check for current zone space type
                for spaceTypeNode in self.__nandrad_xml_tree.getElementsByTagName("SpaceType"):
                    print("Direct space type definition was found in project file.")

                    if(spaceTypeNode.getAttribute("name") == spaceTypeName):

                        #definition for desired zone was found, see if we have heating/cooling model
                        for node in spaceTypeNode.getElementsByTagName("IBK:String"):

                            if(node.getAttribute("name") == "HeatingControlMode"):
                                heat_mode = node.firstChild.nodeValue
                            elif(node.getAttribute("name") == "CoolingControlMode"):
                                cool_mode = node.firstChild.nodeValue

            #no direct definition, check for referenced definition
            elif( len(self.__nandrad_xml_tree.getElementsByTagName("SpaceTypesReference")) > 0):

                #reference is given and must be checked: location is given in template directory 
                referencePath = self.__nandrad_xml_tree.getElementsByTagName("SpaceTypesReference")[0].firstChild.nodeValue
                #print("Reference for space type definition was found in project file with reference path %s." % referencePath )

                placeHolder = "${Templates}/"
                if( self.__pathsDefined == True ) and ( placeHolder in referencePath):
                    absRefPath = self.__pathTemplates + os.sep
                    referencePath = referencePath.replace( placeHolder, absRefPath)
                    #print("Reference was found for space types. File location is %s" % referencePath )

                    #now open file and try to get relevant space type definition from xml (parse xml)
                    try:                        
                        spaceType_xml = DOM.parse( referencePath )
                    except Exception:
                        print("Error while parsing space type definition file %s. Check file format of referenced xml." % referencePath)
                    else:
                        for spaceTypeNode in spaceType_xml.getElementsByTagName("SpaceType"):

                            if(spaceTypeNode.getAttribute("name") == spaceTypeName):

                                #definition for desired zone was found, see if we have heating/cooling model
                                for node in spaceTypeNode.getElementsByTagName("IBK:String"):

                                    if(node.getAttribute("name") == "HeatingControlMode"):
                                        heat_mode = node.firstChild.nodeValue
                                    elif(node.getAttribute("name") == "CoolingControlMode"):
                                        cool_mode = node.firstChild.nodeValue
                else:
                    print("Couldn't interprete given Template path %s." % referencePath)

            else:
                print("Couldn't find direct or indirect schedule definition in project file.") 


            ##----------SCHEDULE DEFINTIONS (EXTERNAL OR INTERNAL)------------------------------------------------------##


            #check file for direct schedule definitions:in project file
            schedulesTagList = self.__nandrad_xml_tree.getElementsByTagName("Schedules")
            if( len( schedulesTagList ) > 0):

               #read default setting for weekend days
                if( len(schedulesTagList.getElementsByTagName("WeekEndDays")) > 0):
                    weekEndDays = schedulesTagList.getElementsByTagName("WeekEndDays")[0].firstChild.nodeValue
                    weekEndDaysList = weekEndDays.split(",")
                else:
                    weekEndDaysList = ["Sat","Sun"]
                
                #holidays = schedulesTagList[0].getElementsByTagName("Holidays")[0].firstChild.NodeValue    #holidays are ignored yet
                #holidaysList = holidays.split(",")                                                         #holidays are ignored yet

                #direct definition is given, check for current zone space type
                for spaceTypeGroupNode in schedulesTagList[0].getElementsByTagName("SpaceTypeGroup"):

                    #print("Direct schedule definition was found in project file.")
                    if(spaceTypeGroupNode.getAttribute("spaceTypeName") == spaceTypeName):
                        
                        #definition for desired zone was found, get values
                        print("Matching space type was found for %s." % spaceTypeName)

                        #read schedules and submit schedules to zone object settings
                        ScheduleObjectHeatingSetPoints = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "HeatingSetPointTemperature", DataType.TEMPERATURE, weekEndDaysList)
                        newZoneObject.add_idealHeatingModel(heat_pmax, heat_mode, heat_conType, ScheduleObjectHeatingSetPoints)

                        ScheduleObjectCoolingSetPoints = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "CoolingSetPointTemperature", DataType.TEMPERATURE, weekEndDaysList)
                        newZoneObject.add_idealCoolingModel(cool_pmax, cool_mode, cool_conType, ScheduleObjectCoolingSetPoints)

                        ScheduleObjectAirChangeRates = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "AirChangeRate", DataType.AIRCHANGERATES, weekEndDaysList)
                        newZoneObject.add_ventModel(ScheduleObjectAirChangeRates)

                        ScheduleObjectEquipLoads = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "EquipmentLoads", DataType.HEATFLUXDENSITY, weekEndDaysList)
                        newZoneObject.add_equipLoadsModel(ScheduleObjectEquipLoads)


            #no direct definition, check for referenced definition
            elif( len(self.__nandrad_xml_tree.getElementsByTagName("SchedulesReference")) > 0):

                #reference is given and must be checked: location is given in template directory 
                referencePath = self.__nandrad_xml_tree.getElementsByTagName("SchedulesReference")[0].firstChild.nodeValue
                #print("Reference for schedule definition was found in project file with reference path %s." % referencePath )

                placeHolder = "${Templates}/"
                if( self.__pathsDefined == True ) and ( placeHolder in referencePath):
                    absRefPath = self.__pathTemplates + os.sep
                    referencePath = referencePath.replace( placeHolder, absRefPath)
                    #print("Reference was found for schedules. File location is %s" % referencePath )

                    #now open file and try to get relevant space type definition from xml (parse xml)
                    try:                        
                        schedules_xml = DOM.parse( referencePath )
                    except Exception:
                        print("Error while parsing schedule definition file %s. Check file format of referenced xml." % referencePath)
                    else:
                        #read default setting for weekend days
                        if( len(schedules_xml.getElementsByTagName("WeekEndDays")) > 0):
                            weekEndDays = schedules_xml.getElementsByTagName("WeekEndDays")[0].firstChild.nodeValue
                            weekEndDaysList = weekEndDays.split(",")
                        else:
                            weekEndDaysList = ["Sat","Sun"]

                        #check space type groups for searched name
                        for spaceTypeGroupNode in schedules_xml.getElementsByTagName("SpaceTypeGroup"):

                            if(spaceTypeGroupNode.getAttribute("spaceTypeName") == spaceTypeName):

                                #read schedules and submit schedules to zone object settings
                                ScheduleObjectHeatingSetPoints = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "HeatingSetPointTemperature", DataType.TEMPERATURE, weekEndDaysList)
                                newZoneObject.add_idealHeatingModel(heat_pmax, heat_mode, heat_conType, ScheduleObjectHeatingSetPoints)
                                del ScheduleObjectHeatingSetPoints

                                ScheduleObjectCoolingSetPoints = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "CoolingSetPointTemperature", DataType.TEMPERATURE, weekEndDaysList)
                                newZoneObject.add_idealCoolingModel(cool_pmax, cool_mode, cool_conType, ScheduleObjectCoolingSetPoints)
                                del ScheduleObjectCoolingSetPoints

                                ScheduleObjectAirChangeRates = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "AirChangeRate", DataType.AIRCHANGERATES, weekEndDaysList)
                                newZoneObject.add_ventModel(ScheduleObjectAirChangeRates)
                                del ScheduleObjectAirChangeRates

                                ScheduleObjectEquipLoads = self.__read_NandradWeekSchedule( spaceTypeGroupNode, "EquipmentLoads", DataType.HEATFLUXDENSITY, weekEndDaysList)
                                newZoneObject.add_equipLoadsModel(ScheduleObjectEquipLoads)
                                del ScheduleObjectEquipLoads
                     
                else:
                    print("Couldn't interprete given Template path %s." % referencePath)
            else:
                print("Couldn't find direct or indirect schedule definition in project file.") 
                   

        #return complete zone object
        return newZoneObject
                    


##---------------------------SUPPORTING FUNCTIONS---------------------------------------------------##
    
    def __read_NandradWeekSchedule(self, xml_spaceTypeGroupNode, keyWord, dataType, dayListWeekEnd ):

        #create new schedule object with submitted properties (Daily week is assumed because this is standard in Nandrad)
        NewSchedule = Schedule( ScheduleType.DAILY_WEEK, dataType )

        #init value lists we are searching for
        valueScheduleWeek = []          #list with 24 elements for hourly values of the day
        valueScheduleWeekEnd = []       #list with 24 elements for hourly values of the day

        #go through schedule objects which define different day types: AllDays, WeekEndDays and Holidays
        for scheduleTag in xml_spaceTypeGroupNode.getElementsByTagName("Schedule"):

            #init value schedule in general
            valueSchedule = []

            #go through interval tags and fill value schedule
            for intervalTag in scheduleTag.getElementsByTagName("Interval"):

                #init values which are given in interval tag
                timeValue = 24
                value = -50.0

                #values are held in ibk- param tags
                for paramTag in intervalTag.getElementsByTagName("IBK:Parameter"):
                    if(paramTag.getAttribute("name") == "End"):
                        timeValue = int(paramTag.firstChild.nodeValue)
                    elif(paramTag.getAttribute("name") == keyWord):
                        value = float(paramTag.firstChild.nodeValue)

                #save values in list
                start = len(valueSchedule)
                end = min(timeValue, 24)
                for timeStep in range( start, end ):
                    if(len(valueSchedule) < 24):
                        if(int(value) != -50):
                            #don't append value if no entry was found
                            valueSchedule.append(value)
                    else:
                        print("Weekly schedule has already 24 values. Check interval definition for %." % xml_spaceTypeGroupNode.getAttribute("spaceTypeName"))
                        break

                
            #see if we had week day or week end day schedule, holidays are ignored
            if(scheduleTag.getAttribute("type") == "AllDays"):
                valueScheduleWeek = valueSchedule
            elif(scheduleTag.getAttribute("type") == "WeekEnd"):
                valueScheduleWeekEnd = valueSchedule
                

        #submit values and return schedule
        if (len(valueScheduleWeek) == 24) and (len(valueScheduleWeekEnd) == 24):
            NewSchedule.submit_weekDaySchedule(valueScheduleWeek, valueScheduleWeekEnd, dayListWeekEnd )
        elif (len(valueScheduleWeek) == 24):
            #print("NandradProject:ReadWeekSchedule: Error occured while reading weekend schedule, same profile is assumed as for week.")
            NewSchedule.submit_weekDaySchedule(valueScheduleWeek, valueScheduleWeek, dayListWeekEnd)
        else:
            #print("NandradProject:ReadWeekSchedule: NO weekly profile identified for %s. Default data is assumed." % keyWord)
            print("NandradProject: ReadWeeklySchedule: Schedule couldn't be initialized. Requested value %s might be missing value in data set. Standard profile is assumed" % (keyWord))
            
            #define default values as dictionary
            defaultValues_SetPoint = {
                "HeatingLoad": 100.0,
                "HeatingSetPointTemperature": 20.0,
                "CoolingSetPointTemperature": 26.0,
                "AirChangeRate": 0.7,
                "LoadsPerPerson": 80.0,
                "EquipmentLoads": 6.0,
                "Other": 0.0
                }
            defaultValues_SetBack = {
                "HeatingLoad": 50.0,
                "HeatingSetPointTemperature": 16.0,
                "CoolingSetPointTemperature": 28.0,
                "AirChangeRate": 0.3,
                "LoadsPerPerson": 0.0,
                "EquipmentLoads": 3.0,
                "Other": 0.0
                }
            
            #core utilization time period of the day
            start_hour = 8
            end_hour = 16

            #corrected key word
            keyWord_new = "Other"
            
            #check if keyWord is known
            if( keyWord not in defaultValues_SetPoint):
                print("NandradObject:WeeklySchedule: Key word %s is not included in quantity list." %keyWord)
                #keyWord_new = "Other"
            else:
                keyWord_new = keyWord

            #key word wasn't found, value schedule is set back, profile is initialized with standard settings
            valueScheduleWeek = []
            valueScheduleWeekEnd = []
            
            #fill schedule list with default values
            for i in range(24):
                #time destinction in week profile
                if (i < start_hour) or (i > end_hour):
                    valueScheduleWeek.append( defaultValues_SetBack[ keyWord_new ] )
                else:
                    valueScheduleWeek.append( defaultValues_SetPoint[ keyWord_new ] )
                #constant profile for week end
                valueScheduleWeekEnd.append( defaultValues_SetBack[ keyWord_new ] )
                
                
            #submit list to schedule object
            NewSchedule.submit_weekDaySchedule(valueScheduleWeek, valueScheduleWeekEnd, dayListWeekEnd)    
            


        return NewSchedule
            
        



































