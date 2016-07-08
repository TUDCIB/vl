#libs
import time
import os
import copy
import shutil

#user defined classes
from nandrad_project import Nandrad_xml
from therakles_project import Therakles_project
from simulationmatrix import SimulationMatrix


#get Nandrad file project file path and file
__ScriptsPath = os.path.dirname(os.path.realpath(__file__))
__ProjectPath = os.path.dirname(os.path.normpath(__ScriptsPath))
__ProjectFolder = os.path.join(__ProjectPath, "project")
__BinPath = os.path.join(__ProjectPath, "bin")

NandradFileName = "ISES_project.xml"
NandradFilePath = os.path.join(__ProjectFolder, NandradFileName)
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
SimMatrixDirectory = os.path.join(__ProjectFolder, "SimMa")

#get ResCon path
ResConJarFilename = "ResCon.jar"
ResConPath = os.path.join(__ProjectPath, "bin", "ResCon")
ResConJarFilePath = os.path.join(ResConPath, ResConJarFilename)

##--------------------------------CREATE SINGLES ZONE DATASETS AND SIMULATE ZONES (THERAKLES)-----------------------------##

#request zone ifc keys for zones of interest (assumption: each simulation matrix file is named by ifc key of the zone)
#additional level for variation of desired zone object due to simulation matrix specifications
#simMaFileName = SimMatrixFileNames["ISES_project.simmatrix"]
simMaFileName = "ISES_project.simmatrix"
CurrentSimMatrix = SimulationMatrix(os.path.join(SimMatrixDirectory, simMaFileName))
CurrentSimMatrix.define_resConPathAndName(ResConJarFilePath)
zonesOfInterest = []
zonesOfInterest = CurrentSimMatrix.returnZonesOfInterrest()

#keep names of analyzed zones to avoid double calculation of one zone
analyzedZones = []

#create new folder for single Zone (Therakles) output
TheraklesResDirectory = os.path.join(__ProjectFolder, "TheraklesProjects") 

if not os.path.exists(TheraklesResDirectory):
    os.makedirs(TheraklesResDirectory)

#copy the ISES_climate folder from DB_Climate into climate folders of the two different Therakles solver versions
#bin->Therakles->resources->IBK->DB_Climate
#bin->Therakles->data->DB_climate

__dbClimate = os.path.join(__ProjectFolder, "DB_Climate")
__isesClimate = os.path.join(__dbClimate, "ISES_climate")
__currentClimateVariante = os.path.join(__BinPath,"Therakles", "data", "DB_climate", "ISES_climate")
__currentClimateVarianteWindows = os.path.join(__BinPath,"Therakles", "resources", "IBK", "DB_Climate", "ISES_climate")
try:
    shutil.copytree(__isesClimate, __currentClimateVariante)
except:
    print("ISES_climate for Linux version could not be copied")

try:
    shutil.copytree(__isesClimate, __currentClimateVarianteWindows)
except:
    print("ISES_climate for Windows version could not be copied")

print("start of initialization of zones:")

if ( len(zonesOfInterest) > 0):
    #loop over zone ids, get zone object, simulate it and analyze output for each
    for zoneId in zonesOfInterest:
        #check if this zone has already been simulated
        if zoneId not in analyzedZones:
            
            #create zone object which keeps all zone and construction properties
            currentZoneObject = NandradProject.returnZoneObject( str(zoneId), "IFC")
            
            for key in CurrentSimMatrix.return_versionKeys():
                
                currentZoneObjectCopy = copy.deepcopy(currentZoneObject)
                #request modification of zone object due to version key
                modifiedZoneObject = CurrentSimMatrix.return_modifiedZoneObject(currentZoneObjectCopy, key)
                #create Therakles Project based on Nandrad zone properties for each id
                newFileName = "Zone_" + modifiedZoneObject.ifc + "_Version_" + key + ".rmxml"
                TheraklesInputFileName = os.path.join(TheraklesResDirectory, newFileName)
                print TheraklesInputFileName
                
                currentTheraklesProject = Therakles_project()
                currentTheraklesProject.initializeProject()
                currentTheraklesProject.submitZoneSettings( modifiedZoneObject )
                currentTheraklesProject.write_ProjectFile( TheraklesInputFileName )
                
                del currentZoneObjectCopy
            #keep zone id for comparison
            analyzedZones.append(zoneId)
            
#get entire processing time for this part
t_end = time.clock()
t_ex = (t_end - t_start)
print("Entire execution time was %.2f seconds (%.2f minutes, %.2f hours)." % (t_ex, (t_ex/60.0), (t_ex/(60.0*60.0)) ))