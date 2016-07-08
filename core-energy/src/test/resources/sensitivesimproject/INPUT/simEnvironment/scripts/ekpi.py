"""
purpose of this class: This class handles pre-defined energy-related key performance indicators
institution: IBK, TU Dresden
included functions are:

"""

##-------------------------BASE CLASS-----------------------------##

class eKPI(object):

    #constructor
    def __init__(self, name, unit, key ):

        #private member variables
        self.__name = name          #complete eKPI name
        self.__unit = unit          #unit 
        self.__key = key            #key number
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
    

    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

##-------------------------SPECIFIED CLASSES----------------------------##



##------------------------------------------------ Annual heat flux characteristics --------------------------------------##
    
#net annual ventilation heat losses
#defined as eKPI 1.0.1 
class eKPI_101(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualVentFluxInt, float_factor, string_unit):

        #init values
        self.__name = "Net annual natural ventilation heat loss"        #complete eKPI name
        self.__unit = string_unit                                       #unit 
        self.__key = "1.0.1"                                            #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
        
        if( string_inputDataType == "FluxInt_Therakles" ):

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            fac_output = -1.0
            self.__factor = fac_fluxIntTher * float_factor * fac_output             
            self.__result = float_annualVentFluxInt * self.__factor 
            
            #submit essential variables to mother class
            #eKPI.__init__(self, "Net annual natural ventilation heat loss", string_unit, "1.0.1")

                     
    def return_result(self):
        return self.__result
    def return_factor(self):
        return self.__factor

    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_key(self):
        return self.__key
    
#net annual internal heat gains
#defined as eKPI 1.0.2 
class eKPI_102(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualEquipGainsInt, float_factor, string_unit):

        #init values
        self.__name = "Net annual internal equipment heat gains"        #complete eKPI name
        self.__unit = string_unit                                       #unit 
        self.__key = "1.0.2"                                            #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value

        #submit essential variables to mother class
        #eKPI.__init__(self, "Net annual internal equipment heat gains", string_unit, "1.0.2")

        if( string_inputDataType == "FluxInt_Therakles" ):

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            self.__factor = fac_fluxIntTher * float_factor             
            self.__result = float_annualEquipGainsInt * self.__factor
            
                      
    def return_result(self):
        return self.__result
    def return_factor(self):
        return self.__factor

    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_key(self):
        return self.__key



#net annual transmission heat losses
#defined as eKPI 1.0.3 
class eKPI_103(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualTransmLossesInt, float_factor, string_unit):

        #init values
        self.__name = "Net annual transmission heat losses"         #complete eKPI name
        self.__unit = string_unit                                   #unit 
        self.__key = "1.0.3"                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value        

        #submit essential variables to mother class
        #eKPI.__init__(self, "Net annual transmission heat losses", string_unit, "1.0.3")

        #implement other string keys for other types of input values
        if( string_inputDataType == "FluxInt_Therakles" ):

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            fac_output = -1.0
            self.__factor = fac_fluxIntTher * float_factor * fac_output            
            self.__result = float_annualTransmLossesInt * self.__factor
                      
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key


#net annual solar heat gains
#defined as eKPI 1.0.4 
class eKPI_104(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualSolGainsInt, float_factor, string_unit):
        
        #init values
        self.__name = "Net annual solar heat gains"                 #complete eKPI name
        self.__unit = string_unit                                   #unit 
        self.__key = "1.0.4"                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
        
        #submit essential variables to mother class
        #eKPI.__init__(self, "Net annual solar heat gains", string_unit, "1.0.4")
        
        #implement other string keys for other types of input values
        if( string_inputDataType == "FluxInt_Therakles" ):
            
            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            self.__factor = fac_fluxIntTher * float_factor             
            self.__result = float_annualSolGainsInt * self.__factor
                      
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key


##------------------------------------------------ Net heating and cooling energy consumption --------------------------------------##

#net annual energy consumption of the heating system 
#defined as eKPI 1.1.1 
class eKPI_111(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualHeatFluxInt, float_factor, string_unit):

        #init values
        self.__name = "Net annual energy consumption of the heating system"         #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.1.1"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value

        #submit essential variables to mother class
        #eKPI.__init__(self, "Net annual energy consumption of the heating system", string_unit, "1.1.1")

        #implement other string keys for other types of input values
        if( string_inputDataType == "FluxInt_Therakles" ):
            
            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            self.__factor = fac_fluxIntTher * float_factor             
            self.__result = float_annualHeatFluxInt * self.__factor
                                   
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key



#net annual energy consumption of the cooling system 
#defined as eKPI 1.1.2 
class eKPI_112(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualCoolFluxInt, float_factor, string_unit):

        #init values
        self.__name = "Net annual energy consumption of the cooling system"         #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.1.2"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value

        #submit essential variables to mother class
        #eKPI.__init__(self, "Net annual energy consumption of the cooling system", string_unit, "1.1.2")

        #implement other string keys for other types of input values
        if( string_inputDataType == "FluxInt_Therakles" ):

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            fac_output = -1.0
            self.__factor = fac_fluxIntTher * float_factor* fac_output            
            self.__result = float_annualCoolFluxInt * self.__factor
                                   
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

##------------------------------------------------ Final heating and cooling energy consumption --------------------------------------##


      
#final annual energy consumption of the heating system 
#defined as eKPI 1.2.1 
class eKPI_121(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualHeatFluxInt, float_factor, string_unit):

        #init values
        self.__name = "Final annual energy consumption of the heating system"       #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.2.1"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value

        #float_factor must be area because hot water consumption is area related

        if( string_inputDataType == "FluxInt_Therakles" ):
            
            #submit essential variables to mother class
            #eKPI.__init__(self, "Final annual energy consumption of the heating system", string_unit, "1.2.1")

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            self.__factor = fac_fluxIntTher * float_factor

            domHotWater = 12.5          #domestic hot water consumption: in kWh/m2a
            f_heating = 1.6             #assumed factor which accounts for generation, storage and distribution of heat in building
            
            self.__result = ((float_annualHeatFluxInt * self.__factor ) + domHotWater )* f_heating
                  
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

#final annual energy consumption of the cooling system 
#defined as eKPI 1.2.2
class eKPI_122(eKPI):

    #constructor
    def __init__(self, string_inputDataType, float_annualCoolFluxInt, float_factor, string_unit):

        #init values
        self.__name = "Final annual energy consumption of the cooling system"       #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.2.2"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
        
        #float_factor must be area because hot water consumption is area related

        if( string_inputDataType == "FluxInt_Therakles" ):
            
            #submit essential variables to mother class
            #eKPI.__init__(self, "Final annual energy consumption of the cooling system", string_unit, "1.2.2")

            #assumes input unit Joule * 1e6 and output unit kWh --> standard conversion factor = 1e6/(3600.0*1000.0)
            fac_fluxIntTher = 1e6/(3600.0*1000.0)
            fac_output = -1.0
            self.__factor = fac_fluxIntTher * float_factor * fac_output

            f_cooling = 1.9             #assumed factor which accounts for generation, storage and distribution of cooled air in building
            
            self.__result = (float_annualCoolFluxInt) * self.__factor * f_cooling
                  
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

        
##------------------------------------------------Primary energy consumption --------------------------------------##

#primary energy consumption
#defined as eKPI 1.3.1
class eKPI_131(eKPI):

    #constructor
    def __init__(self, float_finalEnElectricity, float_finalEnHeating , string_unit):

        #init values
        self.__name = "Primary energy consumption for heating and cooling"          #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.3.1"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value

        
        #submit essential variables to mother class
        #eKPI.__init__(self, "Primary energy consumption for heating and cooling", string_unit, "1.3.1")

        f_elPower = 3.0             #primary energy factor for electric power
        f_heat = 1.2                #primary energy factor for conventional heating energy source 
        
        self.__result = ((float_finalEnElectricity * f_elPower) + ( float_finalEnHeating * f_heat)) 
                  
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key


##------------------------------------------------ Comfort Rating --------------------------------------##

#comfort rating: average operative temperature
#defined as eKPI 1.4.1
class eKPI_141(eKPI):

    #constructor
    def __init__(self, string_inputDataType , linesList_File , float_factor , string_unit):

        #init values
        self.__name = "Annual average of operative temperature"          #complete eKPI name
        self.__unit = string_unit                                                   #unit 
        self.__key = "1.4.1"                                                        #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
        
        #submit essential variables to mother class
        #eKPI.__init__(self, "Annual average of operative temperature", string_unit, "1.4.1")

        #get column index for operative temperature
        if( string_inputDataType == "StatesFile_Therakles" ):

            #check for correct column
            index_OpTemp = 4
            col_counter = 0
            for keyWord in linesList_File[0].split("\t"):
                if ("Operative temperature" in keyWord):
                    index_OpTemp = col_counter
                col_counter += 1

            #analyze values
            sumOpTemp = 0.0
            noValues = 0.0
            for line in linesList_File[1:]:
                sumOpTemp += float( line.split("\t")[index_OpTemp] )
                noValues += 1.0
                
            avgOpTemp = sumOpTemp / noValues
            self.__result = (avgOpTemp * float_factor)      
       
        else:
            print("EKPI:eKPI_141: unknown input data characteristic %s." % string_inputDataType )
        
             
                  
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

#comfort rating: number of discomfort hours due to EN 15251
#defined as eKPI 1.4.2
class eKPI_142(eKPI):

    #constructor
    def __init__(self, string_inputDataType , linesList_File):

        #init values
        self.__name = "Number of annual discomfort hours via EN 15251"          #complete eKPI name
        self.__unit = ""                                                        #unit 
        self.__key = "1.4.2"                                                    #key number
        
        self.__factor = 1.0         #conversion factor (e.g. for modified units)
        self.__result = 0.0         #init value
        
        #submit essential variables to mother class
        #eKPI.__init__(self, "Number of annual discomfort hours due to EN 15251", "-", "1.4.2")

        #get column index for operative temperature
        if( string_inputDataType == "StatesFile_Therakles" ):

            #check for correct column
            index_OpTemp = 11
            index_RunMeanOutTemp = 2
            col_counter = 0
            for keyWord in linesList_File[0].split("\t"):
                if ("Operative temperature" in keyWord):
                    index_OpTemp = col_counter
                elif("Gliding (2-day) average" in keyWord):
                    index_RunMeanOutTemp = col_counter                    
                col_counter += 1

            #analyze values
            noDiscHours = 0
            for line in linesList_File[1:]:

                #get values
                runMeanOutTemp = float( line.split("\t")[index_RunMeanOutTemp] )
                opTemp = float( line.split("\t")[index_OpTemp] )

                #compute and compare with ideal temp range
                idealOpTemp = 18.8 + 0.33 * runMeanOutTemp
                tolerance = 4.0
                minIdealTemp = max((idealOpTemp - tolerance), 22.0)
                maxIdealTemp = min((idealOpTemp + tolerance), 31.0)
                if( opTemp < minIdealTemp) or (opTemp > maxIdealTemp):
                    noDiscHours += 1

            self.__result = noDiscHours
       
        else:
            print("EKPI:eKPI_141: unknown input data characteristic %s." % string_inputDataType )
        
             
                  
    def return_result(self):
        return self.__result
    def return_unit(self):
        return self.__unit
    def return_name(self):
        return self.__name
    def return_factor(self):
        return self.__factor
    def return_key(self):
        return self.__key

##------------------------------------------------Economic values--------------------------------------##

##------------------------------------------------Peak heating and cooling loads--------------------------------------##
class eKPI_maxTemp(eKPI):
    
    def __init__(self, string_inputDataType, linesList_File):
        
        self.__constructionName = ""
        self.__constructionTempMax = 0.0
        self.__constructionTempMin = 0.0
        
class eKPI_maxHeatingLoads(eKPI):
    
    def __init__(self, string_inputDataType, linesList_File):
        
        self.__currentRow = 0
        self.__maxHeating = 0
        
class eKPI_maxCoolingLoads(eKPI):
    
    def __init__(self, string_inputDataType, linesList_File):
        
        self.__
