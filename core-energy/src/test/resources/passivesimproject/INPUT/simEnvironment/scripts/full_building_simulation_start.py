#libs
import time
import os
import copy

#user defined classes
from simulationmatrix_full_building import SimulationMatrix


#get Nandrad file project file path and file
ScriptsPath = os.path.dirname(os.path.realpath(__file__))
ProjectPath = os.path.dirname(os.path.normpath(ScriptsPath))
ProjectFolder = os.path.join(ProjectPath, "project")

##--------------------------------TEST FUNCTIONS _ REMOVE LATER -----------------------------##

#get processing time (start)
t_start = time.clock()
##--------------------------------PARSE SIMULATON MATRIX FILES-----------------------------------------------------------------##

#get files from folder simma and submit ifc keys (name of sim matrix file) and complete path in dictionary
SimMatrixDirectory = os.path.join(ProjectFolder, "SimMa")

#--------------------------------CREATE SINGLES ZONE DATASETS AND SIMULATE ZONES (THERAKLES)-----------------------------##

#request zone ifc keys for zones of interest (assumption: each simulation matrix file is named by ifc key of the zone)
#additional level for variation of desired zone object due to simulation matrix specifications
#simMaFileName = SimMatrixFileNames["ISES_project.simmatrix"]
simMaFileName = "ISES_project.simmatrix"
pathToSimMatrix = os.path.join(SimMatrixDirectory, simMaFileName)
print pathToSimMatrix
fullBuildingSimulations = SimulationMatrix(pathToSimMatrix)
#get entire processing time for this part
t_end = time.clock()
t_ex = (t_end - t_start)
print("Entire execution time was %.2f seconds (%.2f minutes, %.2f hours)." % (t_ex, (t_ex/60.0), (t_ex/(60.0*60.0)) ))
