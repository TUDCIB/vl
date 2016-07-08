
"""
purpose of this class: This class handels all zone dependent data (area, volume, schedules, constructions,...)
institution: IBK, TUD
"""

# standard libs
from enum import Enum

# related libs
from material import Material
from construction import Construction
from schedules import ScheduleType, DataType, Schedule



##------Supporting Class for pre-defined types--------------------##

class SpaceType(Enum):
    
    #following types are pre-defined for Nandrad occ. types
    PASSIVE = 1
    ACTIVE = 2
    

##------Main class--------------------##

class ZoneObject:

    #constructor has project- initializing function
    def __init__(self, name):
        
        #private attributes and variables: only for class- internal use, syntax: self.__XXX
        #none
        self.__materialsChecked = False
        
        #protected attributes: don't modify externally, syntax: self._XXX
        #none
        
        #public attributes: syntax: self.XXX
        #ToDo: include these variables as private members
        self.name = name                        #zone name (string)
        self.id = "0"                           #nandrad project file id
        self.ifc = ""                           #ifc key
        self.area = "0.0"                       #zone area in m2
        self.volume = "0.0"                     #zone net air volume in m3
        self.height = "0.0"                     #average zone hight in m
        self.constructions = []                 #list of zone constructions 
        self.windows = []                       #list of zone window objects
        self.materials = []                     #list of included materials (objects)
        self.spaceTypeName =SpaceType.PASSIVE   #string with pre-defined space type (Passive or Active)
        #self.usageType = UsageType()           #includes data set of occupancy, heating, cooling etc. schedules

        self.__heat = False                     #False if no heating model defined for this zone
        self.__heat_maxP = 0.0                  #max heating power for this zone
        self.__heat_mode = "P"                  #control mode of heating system: proportional= P
        self.__heat_Schedule = Schedule(ScheduleType.DAILY_WEEK, DataType.TEMPERATURE)

        self.__cool = False                     #False if no cooling model defined for this zone
        self.__cool_maxP = 0.0                  #max cooling power for this zone
        self.__cool_mode = "P"                  #control mode of cooling system: proportional= P
        self.__cool_Schedule = Schedule(ScheduleType.DAILY_WEEK, DataType.TEMPERATURE)

        self.__vent = False                     #natural ventilation model
        self.__vent_Schedule = Schedule(ScheduleType.DAILY_WEEK, DataType.AIRCHANGERATES)

        self.__equip = False                    #equipment heat emission model
        self.__equip_Schedule = Schedule(ScheduleType.DAILY_WEEK, DataType.HEATFLUXDENSITY)

        self.__weatherDataSetName = ""

        
    #purpose of this function:  Function initializes Zone object with defaut data
    #submitted variables:       none
    #return values:             none
    def assign_defaultData(self):

        #objects
        newConstruction = Construction("defaultConstruction")
        newConstruction.assign_defaultData()
        self.constructions.append( newConstruction )

        #newWindow = Window
        #newWindow.assign_defaultData()
        #self.windows.append( newWindow )



    #purpose of this function:  Adds construction to member list
    #submitted variables:       construction object 
    #return values:             none
    def add_Construction( self, Construction ):
        #check construction properties!!
        self.constructions.append( Construction )
            
    #purpose of this function:  Protected access to member lists (ToDo: transform to private level)
    #submitted variables:       none
    #return values:             list of Objects
    def return_ConsList(self):
        return self.constructions
    
    #call this function after material list update 
    def return_MatList(self):
        return self.materials

    def return_WinList(self):
        return self.windows

    #return schedule values as list, depending on submitted schedule value
    def return_HeatScheduleObject(self):    
        return self.__heat_Schedule
    def return_CoolScheduleObject(self):
        return self.__cool_Schedule
    def return_VentScheduleObject(self):
        return self.__vent_Schedule
    def return_EquipScheduleObject(self):
        return self.__equip_Schedule


    #other values: return_heatMode(), heatMaxP()
    #submitted variable:    version--> string: "T" for therakles format, "N" for Nandrad format
    def return_heatMode(self, version):
        if(version == "T"):
            mode = self.__heat_mode
            if(mode == "P"):
                return "SIMPLE"
            elif(mode == "PID"):
                return "SIMPLE_PID"
            else:
                return ""
        elif(version == "N"):
            return str(self.__heat_mode)
    
    def return_heatMaxP(self):
        return float(self.__heat_maxP)


    #other values: return_coolMode(), coolMaxP()
    #submitted variable:    version--> string: "T" for therakles format, "N" for Nandrad format
    def return_coolMode(self, version):
        if(version == "T"):
            mode = self.__cool_mode
            if(mode == "P"):
                return "SIMPLE"
            else:
                return ""
        elif(version == "N"):
            return str(self.__heat_mode)

    
    def return_heatMaxP(self):
        return float(self.__heat_maxP)      

    def return_coolMaxP(self):
        return float(self.__cool_maxP)

        
    #purpose of this function:  Create ideal heating model for this zone
    #submitted variables:       maxPower = maximum power of the heating model in W
    #                           controlType = "P" or "PID"
    #                           scheduleType = predefined Schedule object --> TODO: implement!
    #return values:             none
    def add_idealHeatingModel(self, maxPower, mode, controlType, ScheduleObject):

        if( mode != "None" ):

            #heating model was defined, settings are checked and added
            self.__heat = True
            
            #ensure max power of 10 000 Watt (for large zones)
            if(maxPower > 10000.0):
                self.__heat_maxP = maxPower
            else:
                #print("Ideal heating power is raised to 10000 Watt.")
                self.__heat_maxP = 10000.0
                
            #check submitted heating model key
            if(controlType == "P") or (controlType == "PID"):
                self.__heat_mode = controlType
            else:
                print("Undefined control type for heating model.")
                return False

            #take schedule
            self.__heat_Schedule = ScheduleObject

            return True
            


    #purpose of this function:  Create ideal cooling model for this zone
    #submitted variables:       maxPower = maximum power of the cooling model in W
    #                           controlType = "P" or "PID"
    #                           scheduleType = predefined Schedule object --> TODO: implement!
    #return values:             True if cooling model was created, False if not
    def add_idealCoolingModel(self, maxPower, mode, controlType, ScheduleObject):

        if( mode != "None" ):
            
            #cooling model was defined, settings are checked and added
            self.__cool = True
            
            #ensure max power of 10 000 Watt (for large zones)
            if(maxPower > 10000.0):
                self.__cool_maxP = maxPower
            else:
                #print("Ideal heating power is raised to 10000 Watt.")
                self.__cool_maxP = 10000.0
                
            #check submitted heating model key
            if(controlType == "P") or (controlType == "PID"):
                self.__cool_mode = controlType
            else:
                print("Undefined control type for cooling model.")
                return False

            #take schedule
            self.__cool_Schedule = ScheduleObject

            return True



    def add_ventModel(self, ScheduleObject):           
        self.__vent = True
        self.__vent_Schedule = ScheduleObject
        return True

    def add_equipLoadsModel(self, ScheduleObject):
        self.__equip = True
        self.__equip_Schedule = ScheduleObject
        return True

          
    #purpose of this function:  Updates material list based on construction object member list
    #submitted variables:       none
    #return values:             true if successful, false if not
    def update_IncludedObjectsList(self):

        #loop over all constructions to get all materials
        if (len(self.constructions) > 0):
            
            #construction objects in list
            for consObject in self.constructions:
                
                #loop over layers to get materials
                for layer in consObject.return_listOfLayers():

                    #skipt check and append all materials
                    self.materials.append(layer.mat)
##
##                    #loop over all materials in member list to check properties
##                    if len(self.materials) > 0:
##                        for material in self.materials:
##                            if( (layer.mat.return_name() != material.return_name()) or
##                                (layer.mat.return_rho() != material.return_rho()) or
##                                (layer.mat.return_ce() != material.return_ce()) or
##                                (layer.mat.return_la() != material.return_la())):
##
##                                #one or more properties are not equal, material must be added to database
##                                self.materials.append(layer.mat)
##                    else:
##                        self.materials.append(layer.mat)
                        
            return True
        else:
            return False



    #purpose of this function:  Updates construction list and summarizes constructions with equal properties and different area
    #submitted variables:       none
    #return values:             true if successful, false if not           
    def merge_Constructions(self):

        ## TODO: ensure functionality for different input formats (string is compared!!)

        bothEqual = False               #flag value for layer comparison
        sumEO_area = 0.0                #keeps entire area of two embedded objects of different constructions to me merged
        sumCons_area = 0.0              #keeps entire area of two constructions to me merged
        caseEO = 0                      #includes case for embedded objects: 0 = not compared yet, 1 = only embO in first construction, 2 = only in second construction

        #loop over all constructions and compare values, alpha and beta values are not included
        numberOfConstr = len(self.constructions)
        numberDelCons_0 = 0             #reduction counter for first level loop
        numberDelCons_1 = 0             #reduction counter for second level loop
        itList = range(numberOfConstr)
        
        for c_0 in itList:

            #set back reduction index for inner loop
            numberDelCons_1 = 0

            #get corrected outer loop index
            c_0 = c_0 - numberDelCons_0
            
            #check counter after modification
            if((c_0 < 0) or (c_0 >= numberOfConstr)): break
            
            #compare with remaining elements (list update)
            for c_1 in itList:
                c_1 = c_1 - numberDelCons_1
                
                #check counter after modification
                if((c_1 < 0) or (c_1 >= numberOfConstr)): break
            
                #exclude comparison between same list elements (same index)
                if( c_0 != c_1 ):

                    #print("Comparision loop. Counter outer loop is %d. Counter inner loop is %d." % (c_0, c_1) )
                    
                    #get elements and name for comparison, construction name is not compared
                    consO_0 = self.constructions[ c_0 ]
                    consO_1 = self.constructions[ c_1 ] 
                    id_0 = consO_0.return_teraklesID
                    id_1 = consO_1.return_teraklesID

                    #get properties of the construction as string with two decimal positions
                    or_0 = "{:.2f}".format( float(consO_0.return_orient()))
                    or_1 = "{:.2f}".format( float(consO_1.return_orient()))
                    in_0 = "{:.2f}".format( float(consO_0.return_inclin()))
                    in_1 = "{:.2f}".format( float(consO_1.return_inclin()))
                    rI_0 = "{:.2f}".format( float(consO_0.return_rSurfIn()))
                    rI_1 = "{:.2f}".format( float(consO_1.return_rSurfIn()))
                    rO_0 = "{:.2f}".format( float(consO_0.return_rSurfOut()))
                    rO_1 = "{:.2f}".format( float(consO_1.return_rSurfOut()))

                    #compare inclination and orientation, inner and outer surface resistance
                    if(( or_0 == or_1 ) and ( in_0 == in_1) and (rI_0 == rI_1) and (rO_0 == rO_1) ):
                        #print("Orientation, inclination and surface resistances of construction %s and %s are the same." % (id_0, id_1))

                        #get and compare embedded objects properties (only one embedded object per area is allowed and thus compared)
                        embOEqual = False
                        if( consO_0.return_numberOfEmbObjects() > 0):
                            #embedded object in first construction
                            if( consO_1.return_numberOfEmbObjects() > 0):
                                #embedded object in second construction
                                #get objects and compare their properties
                                embO_0 = consO_0.return_embObject( 0 )
                                embO_1 = consO_1.return_embObject( 0 )
                                uVal_0 = "{:.2f}".format( float(embO_0.uValue))
                                uVal_1 = "{:.2f}".format( float(embO_1.uValue))
                                shgc_0 = "{:.2f}".format( float(embO_0.shgc))
                                shgc_1 = "{:.2f}".format( float(embO_1.shgc))
                                fGla_0 = "{:.2f}".format( float(embO_0.fGlass))
                                fGla_1 = "{:.2f}".format( float(embO_1.fGlass))
                                if( (uVal_0 == uVal_1) and (shgc_0 == shgc_1) and (fGla_0 == fGla_1)):
                                    #get entire area of both embedded objects
                                    embOEqual = True
                                    sumEO_area = embO_0.area + embO_1.area
                            else:
                                #only embedded object in first construction, not in second
                                embOEqual = True
                                sumEO_area = consO_0.return_embObject( 0 ).area
                                caseEO = 1
                        #no embedded object in first construction, check for second construction  
                        elif (consO_1.return_numberOfEmbObjects() > 0):
                            sumEO_area = consO_1.return_embObject( 0 ).area
                            caseEO = 2
                            embOEqual = True
                        #no embedded object in both constuctions
                        else:
                            caseEO = 1
                            embOEqual = True
                                
                        #go on with layer comparison if embedded objects can be merged
                        if( embOEqual == True):
                            #print("Embedded objects in constructions with index %d and %d can be merged." % (c_0, c_1))
                            #get number of layers
                            noLay_0 = consO_0.return_numberOfLayers()
                            noLay_1 = consO_1.return_numberOfLayers()
                            if (noLay_0 == noLay_1):
                                bothEqual = True
                                layList_0 = consO_0.return_listOfLayers()
                                layList_1 = consO_1.return_listOfLayers()
                                #number of layers is the same, loop over each layer to compare thickness and material name
                                for counter in range(noLay_0):
                                    matName_0 = layList_0[counter].mat.return_name()
                                    d_0 = "{:.2f}".format( float(layList_0[counter].d) )
                                    matName_1 = layList_1[counter].mat.return_name()
                                    d_1 = "{:.2f}".format( float(layList_1[counter].d) )
                                    if( (matName_0 != matName_1) or (d_0 != d_1)):
                                        #print("Mismatch in layer properties in constructions %d, %d, layer number %d." % (c_0, c_1, counter))
                                        bothEqual = False
                                        break
                            #else:
                                #print("Mismatch in number of layers in constructions %d, %d." % (c_0, c_1))
                    #else:
                        #print("Mismatch in orientation, inclination or surface resistances of construction %d, %d" % (c_0, c_1))
                                
                    #ceck if same construction was found in this loop routine, remove construction from list and merge area
                    if( bothEqual == True ):
                        #print("Matching construction properties and layer properties for index %d and %d found." % (c_0, c_1))
                        #get sum of area of both constructions as string
                        sumCons_area = "{:.2f}".format( (float(consO_0.return_area()) + float(consO_1.return_area()) ) )
                        
                        #renew area of embedded object due to case flag and new area
                        if( caseEO == 2):
                            #print("Matching case 2, first element with index %d is deleted, second is kept." % c_0 )
                            #only second construction includes embedded object, thus we take second and delete first object
                            self.constructions[c_1].assign_area( sumCons_area, self.constructions[c_1].return_typeValue() )
                            del self.constructions[ c_0 ]
                            numberDelCons_0 += 1
                            
                        elif( caseEO == 1):
                            #print("Matching case 1, second element with index %d is deleted, first is kept." % c_1 )
                            #only first or none construction includes embedded object, thus we take first and delete second object
                            self.constructions[c_0].assign_area( sumCons_area, self.constructions[c_0].return_typeValue() )
                            del self.constructions[ c_1 ]
                            if( c_0 > c_1):         #reduce second counter if deleted list element affects both counters
                                numberDelCons_0 += 1
                            numberDelCons_1 += 1
            
                        else:
                            #print("Matching case 0, second element with index %d is deleted, first is kept." % c_1 )
                            #both constructions include embedded objects, first is kept and second deleted, both areas are adapted
                            self.constructions[c_0].assign_area( sumCons_area, self.constructions[c_0].return_typeValue() )
                            self.constructions[c_0].return_embObject(0).area = sumEO_area
                            del self.constructions[ c_1 ]
                            if( c_0 > c_1):         #reduce second counter if deleted list element affects both counters
                                numberDelCons_0 += 1
                            numberDelCons_1 += 1
                            
                                
                        #reduce number of constructions, set flag values to starting value
                        sumEO_area = 0.0
                        sumCons_area = 0.0
                        bothEqual = False
                        numberOfConstr = len(self.constructions)

                        #break inner loop if first element (counter c_0) doesn't exist any more
                        if(caseEO == 2):        
                            caseEO = 0
                            break
                        caseEO = 0
                        
                    #constructions not equal, counters unmodified
                        
                
        #function return value
        return True










































        
        
            


            


