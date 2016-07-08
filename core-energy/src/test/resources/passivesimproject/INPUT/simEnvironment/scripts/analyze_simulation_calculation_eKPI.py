
from ekpi import eKPI_101
from ekpi import eKPI_102
from ekpi import eKPI_103
from ekpi import eKPI_104
from ekpi import eKPI_111
from ekpi import eKPI_112
from ekpi import eKPI_121
from ekpi import eKPI_122
from ekpi import eKPI_131
from ekpi import eKPI_141
from ekpi import eKPI_142

import xml.dom.minidom as DOM

import os

#list of eKPI- objects

pathOfScripts = os.getcwd()
pathOfProject = os.path.dirname(pathOfScripts)
therakles_results = os.path.join(pathOfProject, "project", "TheraklesProjects")
folders = os.listdir(therakles_results)

for folder in folders:
    #define file names
    if (folder.__contains__(".rmxml") == False) and (folder.__contains__(".txt") == False):
        
        eKPI_list = []
        cfd_list = []
        
        constructionMapping = {}
        #open the fitting Therakles project file and get area of the zone.
        therakles_xml_tree = DOM.EmptyNodeList
        currentTherakles = folder+".rmxml"
        pathOfCurrentTherakles = os.path.join(therakles_results, currentTherakles)
        therakles_project_file = open(pathOfCurrentTherakles);
        therakles_xml_tree = DOM.parse(therakles_project_file)
        zoneAreaTag = therakles_xml_tree.getElementsByTagName("Area")[0]
        zoneArea = zoneAreaTag.firstChild.nodeValue
        
        listOfFolderparts = folder.split("_")
        versionId = listOfFolderparts[len(listOfFolderparts)-1]
        zoneId = folder.replace("Zone_", "")
        zoneId = zoneId.replace("_Version_","")
        zoneId = zoneId.replace(versionId, "")
        
        constructions = therakles_xml_tree.getElementsByTagName("ConstructionType")
        numberOfConstructions = 1
        
        for construction in constructions:
            
            attributeName = construction.getAttribute("name")
            ifcGUID = attributeName.split("|")[0]
            ifcGUID = ifcGUID.replace("de:Construction_","")
            ifcGUID = ifcGUID.replace("en:Construction_","")
            
            constructionMapping[numberOfConstructions] = ifcGUID
            
            numberOfConstructions +=1
        
        resultsFolder = os.path.join(therakles_results, folder, "results")
        
        fluxIntFileName = os.path.join(resultsFolder, "flux_integrals.tsv")
        fluxesFileName = os.path.join(resultsFolder, "fluxes.tsv")
        statesFileName = os.path.join(resultsFolder, "states.tsv")
        roomTemperatureFileName = os.path.join(resultsFolder, "RoomTemperature.ccd")
        roomHumidityFileName = os.path.join(resultsFolder, "RoomRelativeHumidity.ccd")
        wallStatesFileName = os.path.join(resultsFolder, "wall_states.tsv")
        wallFluxesFileName = os.path.join(resultsFolder, "wall_fluxes.tsv")
        wallFluxes_integralsFileName = os.path.join(resultsFolder, "wall_flux_integrals.tsv")
        
        try:
            fluxesFileObject = open( fluxesFileName )
            statesFileObject = open( statesFileName )
            fluxIntFileObject = open( fluxIntFileName )
            roomTempFileObject = open ( roomTemperatureFileName )
            roomHumFileObject = open ( roomHumidityFileName )
            wallStatesFileObject = open ( wallStatesFileName )
            wallFluxesFileObject = open ( wallFluxesFileName )
            wallFluxes_integralsFileObject = open ( wallFluxes_integralsFileName )
        
        except Exception:
            
            print("Error")
        
        else:
            #get header and last line analysis of fluxes_integrals (cumulated values)
            fluxInt_Lines = fluxIntFileObject.readlines()
            #init index values for required output
            index_natVent = 1
            index_heatLoads = 3
            index_cooLoads = 4
            index_equipLoads = 6
            index_traLoads = 9
            index_solLoads = 13
            noRequiredValues = 6
            #check if we have correct file structure: search for index values in first line
            header_fluxInt = fluxInt_Lines[0]
            counter_noRV = 0
            counter_el = 0
                    
            for keyWord in header_fluxInt.split("\t"):
                
                if( "Natural ventilation heat loss/gain" in keyWord):
                    counter_noRV += 1
                    index_natVent = counter_el
                    
                elif("Heating loads" in keyWord):
                    counter_noRV += 1
                    index_heatLoads = counter_el
                    
                elif("Active cooling loads" in keyWord):
                    counter_noRV += 1
                    index_cooLoads = counter_el
                    
                elif("Equipment loads" in keyWord):
                    counter_noRV += 1
                    index_equipLoads = counter_el
                    
                elif("Total transmission loads through inside of all walls" in keyWord):
                    counter_noRV += 1
                    index_traLoads = counter_el
                    
                elif("Radiation loads through all windows" in keyWord):
                    counter_noRV += 1
                    index_solLoads = counter_el
                    
                counter_el += 1
        
            #compare found and searched key word number
            if (counter_noRV != noRequiredValues):
                print("One or several key words were not found in %s file. Please check file structure." % fluxIntFileName )
        
            #init and get cumulated values from last line
            lastLineValues = fluxInt_Lines[ len(fluxInt_Lines)-2 ].split("\t")
            val_natVent = float( lastLineValues[index_natVent])
            val_heatLoads = float( lastLineValues[index_heatLoads])
            val_cooLoads = float( lastLineValues[index_cooLoads])
            val_equipLoads = float( lastLineValues[index_equipLoads])
            val_traLoads = float( lastLineValues[index_traLoads])
            val_solLoads = float( lastLineValues[index_solLoads])
        
            #net energy
            equipE_KPI = eKPI_102( "FluxInt_Therakles", val_equipLoads, (1.0 / float(zoneArea)) , "kWh/m2a") #keep equipment load object for primary energy calc.
                    
            eKPI_list.append( eKPI_101( "FluxInt_Therakles", val_natVent, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( equipE_KPI )
            eKPI_list.append( eKPI_103( "FluxInt_Therakles", val_traLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_104( "FluxInt_Therakles", val_solLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_111( "FluxInt_Therakles", val_heatLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
            eKPI_list.append( eKPI_112( "FluxInt_Therakles", val_cooLoads, (1.0 / float(zoneArea)) , "kWh/m2a"))
        
            #final energy
            heatFinE_KPI = eKPI_121( "FluxInt_Therakles", val_heatLoads, (1.0 / float(zoneArea)) , "kWh/m2a")
            coolFinE_KPI = eKPI_122( "FluxInt_Therakles", val_cooLoads, (1.0 / float(zoneArea)) , "kWh/m2a")
                  
            eKPI_list.append( heatFinE_KPI )
            eKPI_list.append( coolFinE_KPI )
                    
        
            #primary energy
            heatFinE_value = heatFinE_KPI.return_result()
            coolFinE_value = coolFinE_KPI.return_result()
            equipE_value = equipE_KPI.return_result()
                        
            finalHeatEnergy = heatFinE_value
            finalElectEn = coolFinE_value + equipE_value
                    
            eKPI_list.append( eKPI_131( finalElectEn, finalHeatEnergy, "kWh/m2a"))
        
            #comfort values
            linesList_File = statesFileObject.readlines()
            eKPI_list.append( eKPI_141( "StatesFile_Therakles" , linesList_File , 1.0 , "degC"))
            eKPI_list.append( eKPI_142( "StatesFile_Therakles" , linesList_File ))                             
        
            #CFD eKPI-Files
            #adds the construction IFC GUIDs to the first row of the max_Temp-file
            #CombinationID, construction_1 (GUID), dateOfMaxTemp, dateOfMinTemp, construction_2 (GUID)....... 
            #close file objects
            fluxIntFileObject.close()
            statesFileObject.close()
                    
           
            eKPI_lineString = ""
            eKPI_lineString += versionId
            eKPI_fileString = []
            eKPIHeaderLine = ""
           
            for eKPI in eKPI_list:
                
                eKPI_lineString += ",%.2f" % (eKPI.return_result())
            
            eKPI_fileString.append(eKPI_lineString + "\n")
                        
    
            #get header information if not available yet
            
            eKPIHeaderLine += "CombinationID, "
                
            for eKPI in eKPI_list:
                
                eKPIHeaderLine += "KPI_%s_%s_(%s)," % (eKPI.return_key(), eKPI.return_name(), eKPI.return_unit())
            
            eKPIHeaderLine = eKPIHeaderLine[0:-1] + "\n"                       
            #keep zone id for comparison

            #print summary file based on simulation matrix specification
            summaryKPIFileName = os.path.join(therakles_results, zoneId+"_summary.txt")
            
            
            if os.path.exists(summaryKPIFileName) == False:
                
                summaryKPIFileObject = open(summaryKPIFileName, "w")
                summaryKPIFileObject.write(eKPIHeaderLine)
            
                for line in eKPI_fileString:
                    summaryKPIFileObject.write(line)
                summaryKPIFileObject.close()
            else:
                summaryKPIFileObject = open(summaryKPIFileName, "a")
                
                for line in eKPI_fileString:
                    summaryKPIFileObject.write(line)
                summaryKPIFileObject.close()
                
            #output for CFD simulations
            cfdKPIFileNameHeating = os.path.join(therakles_results, zoneId+"_Heating_cfd.txt")
            
            #get data row for heating loads
            fluxLines = fluxesFileObject.readlines()
            
            index_heatLoads = 0
            index_coolLoads = 0
            #check if we have correct file structure: search for index values in first line
            header_flux = fluxLines[0]
            counter_noRV = 0
            counter_el = 0
                    
            for keyWord in header_flux.split("\t"):
                
                if("Heating loads" in keyWord):
                    counter_noRV += 1
                    index_heatLoads = counter_el
                    
                elif("Active cooling loads" in keyWord):
                    counter_noRV += 1
                    index_coolLoads = counter_el
                    
                counter_el += 1
            
            maxHeatingLoad = 0.0
            maxHeatingLoadRow = 0
            
            maxCoolingLoad = 0.0
            maxCoolingLoadRow = 0
            
            cfdHeaderLine = ""
            
            counterHour = 1
            
            for line in fluxLines[1:len(fluxLines)-1]:
                
                currentLineArray = line.split("\t")
                
                if (maxHeatingLoad < currentLineArray[index_heatLoads]):
                    
                    maxHeatingLoad = currentLineArray[index_heatLoads]
                    maxHeatingLoadRow = counterHour
                    
                if (maxCoolingLoad < currentLineArray[index_coolLoads]):
                    
                    maxCoolingLoad = currentLineArray[index_coolLoads]
                    maxCoolingLoadRow = counterHour
                    
                counterHour += 1
            
            #gets lines of highest heating and cooling
            headerFluxes = fluxLines[0]
            headerFluxes = headerFluxes.replace("Time [d]\t", "CombinationID\tTime [d]\t")
            
            lineHighestHeatingList = fluxLines[maxHeatingLoadRow].split("\t")
            lineHighestHeating = versionId+"\t"
            
            counter = 1
            
            for value in lineHighestHeatingList:
                
                if ((counter == 1) or (counter == len(lineHighestHeatingList)-1)):
                    lineHighestHeating += value
                    
                else:
                    lineHighestHeating += "\t"+value
                
                counter += 1
            #lineHighestCooling = fluxLines[maxCoolingLoadRow]
            
            wallstates = wallStatesFileObject.readlines()
            #get header and line maxHeatingLoadRow
            headerWallstates = wallstates[0]
            headerWallstatesList = headerWallstates.split("\t")
            headerWallstates = ""
            
            for value in headerWallstatesList[1:(len(headerWallstatesList)-1)]:
                
                headerWallstates += "\t"+value
            
            for key in constructionMapping:
                
                headerWallstates = headerWallstates.replace("#"+str(key) ,"#"+constructionMapping[key])
            
            headerFluxes += headerWallstates
            headerFluxes = headerFluxes.replace("\n", "")
            headerFluxes = headerFluxes.replace("\r", "")
            
            wallstatesList = wallstates[maxHeatingLoadRow]
            wallstatesList = wallstatesList.split("\t")
            
            wallstatesRow = ""
            
            counter = 1
            
            for value in wallstatesList[1:(len(wallstatesList)-1)]:
                
                if ((counter == 1) or (counter == len(wallstatesList)-1)):
                    wallstatesRow += value
                else:
                    wallstatesRow += "\t"+value
                
                counter += 1
                        
            lineHighestHeating += wallstatesRow
            lineHighestHeating = lineHighestHeating.replace("\n","")
            lineHighestHeating = lineHighestHeating.replace("\r","")
            #lineHighestHeating = lineHighestHeating.replace("\t\t", "\t")
            #lineHighestCooling += wallstates[maxCoolingLoadRow]
            #get line maxCoolingLoadRow
            wallStatesFileObject.close()
            
            cfd_fileString = []
            cfd_fileString.append(lineHighestHeating)
            
            if os.path.exists(cfdKPIFileNameHeating) == False:
                
                cfdKPIFileObject = open(cfdKPIFileNameHeating, "w")
                cfdKPIFileObject.write(headerFluxes)
                cfdKPIFileObject.write("\n")
            
                for line in cfd_fileString:
                    
                    cfdKPIFileObject.write(line)
                    cfdKPIFileObject.write("\n")
            else:
                
                cfdKPIFileObject = open(cfdKPIFileNameHeating, "a")
                
                for line in cfd_fileString:
                    
                    cfdKPIFileObject.write(line)
                    cfdKPIFileObject.write("\n")
                    
            summaryKPIFileObject.close()