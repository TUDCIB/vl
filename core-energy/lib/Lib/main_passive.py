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

NandradFileName = "ISES_project.xml"
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



##--------------------------------PERFORM AND ANALYZE PASSIVE NANDRAD SIMULATION-----------------------------##

###create new file with passive settings, modify output and solver settings for this file
NandradProject.set_zonesToPassiveState()
NandradProject.set_output_toHourlyPassiveResults()
NandradProject.set_settings_toMinSolverSet()


###define and create new project file output
NandradPassiveFileName = "NandradProject_Passive.xml"
NandradPassiveFilePath = os.path.join( ThisWorkPath, NandradPassiveFileName)
NandradProject.write_nandradXmlFile( NandradPassiveFilePath )

###define and create new results output
NandradExeFilename = "NandradSolver.exe"
NandradPath = MainWorkPath + "bin" + SystemSep + "Nandrad" + SystemSep
NandradExeFilePath =  NandradPath + NandradExeFilename

###start simulation and analyze results
if( NandradProject.start_NandradSimulation( NandradPassiveFilePath,NandradExeFilePath ) == True):
    print("Nandrad simulation for file %s terminated. Output will be analyzed." % NandradPassiveFilePath)
    if (NandradProject.start_zoneAnalysisPassiveResults( NandradPassiveFilePath, True )== True):
        print("Passive analysis finished. Check summary files in subfolder %s." % (NandradPassiveFilePath.replace("xml", (os.sep + "results" + os.sep))))
else:
    print("Nandrad simulation for file %s failed. Check executable (version and location)." % NandradPassiveFilePath)
