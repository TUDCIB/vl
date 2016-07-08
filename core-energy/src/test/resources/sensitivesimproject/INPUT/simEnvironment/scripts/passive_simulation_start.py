#! /usr/bin/env python
import os
#user defined classes
from nandrad_project import Nandrad_xml

# get Nandrad file project file path and file
#NandradFilePath = "../project/ISES_project.xml"
scriptDirectory = os.path.dirname(os.path.realpath(__file__))
print scriptDirectory

projectDirectory = os.path.dirname(scriptDirectory)
print projectDirectory

NandradFilePath = os.path.join(projectDirectory, "project", "ISES_project.xml")
NandradPassiveFilePath = os.path.join(projectDirectory, "project", "ISES_project_passive.xml")
# create class instance of nandrad xml project
NandradProject = Nandrad_xml()

# init nandrad project and submit file path, init zones (get ifc and nandrad key lists for all zones)
NandradProject.init_file(NandradFilePath)

##--------------------------------PERFORM AND ANALYZE PASSIVE NANDRAD SIMULATION-----------------------------##

# create new file with passive settings, modify output and solver settings for this file
NandradProject.set_zonesToPassiveState()
NandradProject.set_output_toHourlyPassiveResults()
NandradProject.set_settings_toMinSolverSet()

# define and create new project file output
NandradProject.write_nandradXmlFile(NandradPassiveFilePath)

