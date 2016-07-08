#! /usr/bin/env python

#libs
import time
import os

#user defined classes
from nandrad_project import Nandrad_xml
from therakles_project import Therakles_project
from material import Material
from construction import Construction
from simulationmatrix import SimulationMatrix


##--------------------------------GET RELATIVE FILE PATHS----------------------------##
"""
>>>>>  assumed existing folder structure:

MainFolder\ 
    bin\
        Therakles\
            resources\...           --> includes therakles resources (climate (*.ccd and *.xml), db_constructions,db_... (libs in *.xml format))
            TheraklesSolver.exe     --> executable Therakles.exe (Version Jun 2014)
        Nandrad\
            NandradSolver.exe       --> executable Nandrad.exe (Version Nov 2013)
        ResCon\
            ..
        Bim2Sim\
            
    project\
        NandradProject.xml          --> nandrad project file
        
        DB_Templates\...            --> Schedule, Output and SpaceType definitions which are referenced by Project file (created by Bim2Sim)
        DB_Materials\...            --> Material files (*.m6) which are referenced by DB_Construction files (created by Bim2Sim)
        DB_Constructions\...        --> Construction files in Delphin format (*.d6p) which are referenced by Project file (created by Bim2Sim)
        DB_Climate\...              --> Climate files (*.xml and several *.ccd files) which are referenced by Project file (created by Bim2Sim)

                                    Notice: Resources in this folder are referenced by the project file and created by Bim2Sim application.
                                    These files are renamed copies of the MainFolder/resource - files.
        
        SimMa\
            *.simmatrix             --> simualtion matrix file(s), name equals ifc-key of project zones or "project.simmatrix" for entire building
            
    resources\
        DB_Templates\...            --> Schedule, Output and SpaceType definitions which are referenced by project file and simmatrix 
        DB_Materials\...            --> Material files (*.m6) which are referenced by DB_Construction files 
        DB_Constructions\...        --> Construction files in Delphin format (*.d6p) which are referenced by project file and simmatrix 
        DB_Climate\...              --> Climate files (*.xml and several *.ccd files) which are referenced by project file and simmatrix 

                                    Notice: Resources in this folder are essential for the link between nandrad Project File and SimMatrix files and
                                    allow the identification of the varied elements origin via xml-attribute "source"        

    scripts\
        
        main.py                     --> this script file
        ...                         --> all linked script files (e.g. nandrad_project, therakles_project,...)


         

>>>>>  Folder and sub folders created by nandrad, therakles etc. and by script:

MainFolder\
    project\
        NandradProject_Passive.xml  --> nandrad passive simulation and therakles file (created by script)
        NandradProject_Passive\     
            results\
                log\
                    ...             --> nandrad screen log report (created by nandrad)
                results\
                    *.d6o           --> nandrad results files for requested output variables (heat flux, temperature,...)(created by nandrad) 
                    *.png           --> summary graph files (created by script in two different resolutions for each quantity)
                    *.txt           --> summary text files, incl. "ZonesOfInterest_Summary.txt" (created by script)
                                        
                var\
                    ...             --> definition of output variables
        TheraklesProjects\
            *.rmxml                 --> therakles project files of single zones from nandrad project file, named as Zone_[ifc-Key].rmxml (created by script)
            \                       --> folders of results files from therakles simulation (created by therakles)
        SimMa\
            *_summary.txt           --> summary file with eKPIs and same name as simmatrix
                
"""

#get Nandrad file project file path and file
ThisWorkPath = os.path.abspath(".")
ThisWorkPath = ThisWorkPath.replace("scripts", "project")

NandradFileName = "NandradProject.xml"
NandradFilePath = os.path.join( ThisWorkPath, NandradFileName)

#get project main path (Main Folder, see structure above)
SystemSep = os.sep
FolderList = ThisWorkPath.split( SystemSep )
FolderList = FolderList[0:(len(FolderList)-1)]
MainWorkPath = ""
for i in FolderList:
    MainWorkPath = MainWorkPath + i + SystemSep


##--------------------------------TEST FUNCTIONS _ REMOVE LATER -----------------------------##
 

#get processing time (start)
t_start = time.clock()


##--------------------------------READ AND INIT NANDRAD FILE-----------------------------##

#create class instance of nandrad xml project
NandradProject = Nandrad_xml()


#init nandrad project and submit file path, init zones (get ifc and nandrad key lists for all zones)
NandradProject.init_file(NandradFilePath)
##--------------------------------PARSE SIMULATON MATRIX FILES-----------------------------------------------------------------##

#get files from folder simma and submit ifc keys (name of sim matrix file) and complete path in dictionary
#TODO: discuss file name with CIB: sp. characters in ifc key might cause errors: include ifc key in file header?
SimMatrixDirectory = ThisWorkPath + SystemSep + "SimMa" + SystemSep
SimMatrixFileNames = {}

for name in os.listdir(SimMatrixDirectory):
    if name.endswith(".simmatrix"):
		SimMatrixFileNames[name] = (SimMatrixDirectory + name)
        ###ZoneIFCKey = name[0:-10]
        ###SimMatrixFileNames[ZoneIFCKey] = (SimMatrixDirectory + name)
        
#get ResCon path
ResConJarFilename = "ResCon.jar"
ResConPath = MainWorkPath + "bin" + SystemSep + "ResCon" + SystemSep
ResConJarFilePath = ResConPath + ResConJarFilename

##--------------------------------CREATE SINGLES ZONE DATASETS AND SIMULATE ZONES (THERAKLES)-----------------------------##

#Therakles executable
TheraklesExeFilename = "TheraklesSolver.exe"
TheraklesPath = MainWorkPath + "bin" + SystemSep + "Therakles" + SystemSep
TheraklesExeFilePath =  TheraklesPath + TheraklesExeFilename

#request zone ifc keys for zones of interest (assumption: each simulation matrix file is named by ifc key of the zone)
###zonesOfInterest = SimMatrixFileNames.keys()
#additional level for variation of desired zone object due to simulation matrix specifications
simMaFileName = SimMatrixFileNames[ zoneId ]
CurrentSimMatrix = SimulationMatrix(simMaFileName)
CurrentSimMatrix.define_resConPathAndName(ResConJarFilePath)
zonesOfInterest = CurrentSimMatrix.return_zonesOfInterest

analyzedZones = []          #keep names of analyzed zones to avoid double calculation of one zone

#supporting variables for summary output file
eKPI_fileString = []        #output file string which includes: ifc- key of the zone in first header line & eKPI- key words and units in second header line, written in outer loop
                            #and for each version of the current zone the explicit values of ekpis: version key, eKPI value, eKPI value, ...
eKPIHeaderLine = ""         #includes all specifications for the written eKPIs (name, unit)
eKPIHeaderBool = False      #False as long as header information wasn't written

#create new folder for single Zone (Therakles) output
TheraklesResDirectory = ThisWorkPath + SystemSep + "TheraklesProjects" 
if not os.path.exists(TheraklesResDirectory):
    os.makedirs(TheraklesResDirectory)

if ( len(zonesOfInterest) > 0):

    #loop over zone ids, get zone object, simulate it and analyze output for each
    for zoneId in zonesOfInterest:
        
        #check if this zone has already been simulated
        if zoneId not in analyzedZones:
    
            #create zone object which keeps all zone and construction properties
            currentZoneObject = NandradProject.returnZoneObject( str(zoneId), "IFC" )   

            for key in CurrentSimMatrix.return_versionKeys():
                            
                #request modification of zone object due to version key
                modifiedZoneObject = CurrentSimMatrix.return_modifiedZoneObject( currentZoneObject, key )
                
                #create Therakles Project based on Nandrad zone properties for each id
                newFileName = "Zone_" + modifiedZoneObject.ifc + "Version_" + key + ".rmxml"
                TheraklesInputFileName = TheraklesResDirectory + SystemSep + newFileName
                
                currentTheraklesProject = Therakles_project()
                currentTheraklesProject.initializeProject()
                currentTheraklesProject.submitZoneSettings( modifiedZoneObject )
                currentTheraklesProject.write_ProjectFile( TheraklesInputFileName )           

                if( currentTheraklesProject.start_Simulation( TheraklesInputFileName , TheraklesExeFilePath ) == True ):
                #if( True == True ):
                    print("Main: Therakles simulation for zone with ifc-key %s was finished." % (modifiedZoneObject.ifc) )

                    #compute eKPIs from results
                    netZoneArea = currentTheraklesProject.return_netZoneArea()
                    eKPI_list = currentTheraklesProject.start_ResultsAnalysis( TheraklesInputFileName, netZoneArea )
                    eKPI_lineString = ""
                    eKPI_lineString += key
                    for eKPI in eKPI_list:
                        eKPI_lineString += ",%.2f" % (eKPI.return_result())
                    eKPI_fileString.append(eKPI_lineString + "\n")
                    

                    #get header information if not available yet
                    if( eKPIHeaderBool == False):
                        eKPIHeaderLine += "CombinationID, "
                        for eKPI in eKPI_list:
                            eKPIHeaderLine += "KPI_%s_%s_(%s)," % (eKPI.return_key(), eKPI.return_name(), eKPI.return_unit())
                        eKPIHeaderLine = eKPIHeaderLine[0:-1] + "\n"
                        eKPIHeaderBool = True
                                
                else:
                    print("Therakles simulation for zone with ifc-key %s failed." % (currentZoneObject.ifc) )

        #keep zone id for comparison
        analyzedZones.append(zoneId)


        #print summary file based on simulation matrix specification
        summaryKPIFileName = SimMatrixFileNames[ zoneId ].replace(".simmatrix","_summary.txt")
        summaryKPIFileObject = open(summaryKPIFileName, "w")
        summaryKPIFileObject.write(eKPIHeaderLine)
        for line in eKPI_fileString:
            summaryKPIFileObject.write(line)
        summaryKPIFileObject.close()


#get entire processing time for this part
t_end = time.clock()
t_ex = (t_end - t_start)
print("Entire execution time was %.2f seconds (%.2f minutes, %.2f hours)." % (t_ex, (t_ex/60.0), (t_ex/(60.0*60.0)) ))