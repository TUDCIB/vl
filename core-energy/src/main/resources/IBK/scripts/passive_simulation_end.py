#! /usr/bin/env python
import os
#user defined classes
from nandrad_project import Nandrad_xml

scriptDirectory = os.path.dirname(os.path.realpath(__file__))
print scriptDirectory

projectDirectory = os.path.dirname(scriptDirectory)
print projectDirectory

NandradPassiveFilePath = os.path.join(projectDirectory, "project", "ISES_project_passive.xml")
# create class instance of nandrad xml project
NandradProject = Nandrad_xml()

# init nandrad project and submit file path, init zones (get ifc and nandrad key lists for all zones)
NandradProject.init_file(NandradPassiveFilePath)

# process the results
NandradProject.start_zoneAnalysisPassiveResults( NandradPassiveFilePath, True )
