
"""
purpose of this class: This class handels material data, Material Category in only supporting enum class for pre- defined categories
institution: IBK, TUD
"""

# libs
from enum import Enum
import re



#see documentation material file Delphin on Qucosa: http://nbn-resolving.de/urn:nbn:de:bsz:14-qucosa-126274
class MaterialCategory(Enum):

    #following enums have a unique name (eg. COATING) and corresponding value (eg.1)
    COATING = 1             #Coating
    PLASTER = 2             #Plaster and Mortar
    BRICK = 3               #Building Brick
    NATURAL_STONES = 4      #Natural Stones
    CONCRETE = 5            #Concrete Containing Materials
    INSULATION = 6          #Insulation Materials
    BUILDING_BOARDS = 7     #Building Boards
    TIMBER = 8              #Timber
    NATURAL_MATERIALS = 10  #Natural Materials
    SOIL = 11               #Soil
    CLADDING = 12           #Cladding Panels and Ceramic Tiles
    FOILS = 13              #Foils and Waterproofing Products
    MISC = 14               #Miscellaneous    
 
    

class Material:

    #constructor has project- initializing function
    def __init__(self):
        
        #private attributes and variables: only for class- internal use, syntax: self.__XXX
        #none

        self.__name = ""
        self.__category = MaterialCategory.MISC
        self.__source = ""

        self.__matId = 0      #if material id was set, value must not be 0
       
        self.__rho = 0.0        #rel. thermal property, unit: kg/m3
        self.__ce = 0.0         #rel. thermal property, unit: J/kgK
        self.__opor = 0.0       #unit: m3/m3
        self.__oeff = 0.0       #unit: m3/m3
        self.__mew = 0.0        #unit: -
        self.__aw = 0.0         #unit: kg/m2s05
        self.__kleff = 0.0      #unit: s
        self.__la = 0.0         #rel. thermal property, unit: W/mK
        self.__ocap = 0.0       #unit: m3/m3
        self.__o80 = 0.0        #unit: m3/m3

        self.__mu = 0.0
        self.__w80 = 0.0
        self.__wSat = 0.0
        self.__HPCM = 0.0
        self.__TPCLow = 0.0
        
        #protected attributes: don't modify externally, syntax: self._XXX
        #none   
        
        #public attributes: syntax: self.XXX
        #none


    #purpose of this function:  Function initializes Material object with defaut data (Concrete)
    #submitted variables:       none
    #return values:             none
    def assign_defaultData(self):

        self.__name = "Concrete_C20_25"
        self.__category = MaterialCategory.CONCRETE
        self.__source = "TU Dresden"


        #basic values
        self.__rho = 2.3202000000e+03
        self.__ce = 8.5000000000e+02
        self.__la = 2.1000000000e+00

        #delphin values
        self.__opor = 1.4300000000e-01
        self.__oeff = 1.4299000000e-01
        self.__mew = 1.1000000000e+02
        self.__aw = 2.0000000000e-02
        self.__kleff = 4.4010000000e-11
        self.__ocap = 1.3600000000e-01
        self.__o80 = 5.8205895345e-02

        #therakles values
        self.__mu = 8.9
        self.__w80 = 0.024
        self.__wSat = 0.0276
        self.__HPCM = 0.0
        self.__TPCLow = 23.0


    #purpose of this function:  Function assigns thermal material data
    #submitted variables:       string of name, category name (predefined), rho(density), ce(specific heat capacity), la(thermal conductivity)
    #return values:             none
    def assign_thermalData(self, name, category, rho, ce, la):

        self.__name = name
        self.__category = category
        self.__rho = rho
        self.__ce = ce
        self.__la = la

        return True

    
    #purpose of these functions: Access to single variables of this data set
    #submitted variables:       none
    #return values:             value of specific quantity
    def return_name(self):
        return self.__name

    def return_category(self):
        return self.__category.name

    def return_rho(self):
        return self.__rho

    def return_ce(self):
        return self.__ce

    def return_la(self):
        return self.__la

    def return_listOfTheraklesValues(self):

        values = []
        values.append(self.__category.value)    #index: 0
        values.append(self.__name)              #index: 1
        values.append(self.__la)                #index: 2
        values.append(self.__ce)                #index: 3
        values.append(self.__rho)               #index: 4
        values.append(self.__mu)                #index: 5
        values.append(self.__w80)               #index: 6
        values.append(self.__wSat)              #index: 7
        values.append(self.__HPCM)              #index: 8
        values.append(self.__TPCLow)            #index: 9

        return values


    #purpose of these functions:Handle and assign material ids externally 
    #submitted variables:       material id
    #return values:             none
    def set_matId(self, matId):
        self.__matId = int( matId )
        return True
    def return_matId(self):
        #return value of 0 means no id was yet set
        return self.__matId  

    def set_matName(self, matName):
        self.__name = matName
        return True
    
    #purpose of this function:  Function reads material data set from delphin data set (*.m6)
    #submitted variables:       string including file path and name of the desired material 
    #return values:             none, refreshes material properties
    def read_DelphinDataSet(self, stringFilePathAndName_m6 ):
        
        #parse text file line by line (no xml structure)
        try:
            fileObject = open( stringFilePathAndName_m6, encoding="utf8" )
        except Exception:
            print("Error while parsing material file %s." % stringFilePathAndName_m6)
            return False
        else:
            lineCounter = 0
            for line in fileObject.readlines():
                
                #get value string and remove white spaces 
                if( line.find("=") > 0 ): 
                    firstPos = line.find("=") + 1
                    lastPos = line.rfind(" ") #original: firstPos + 22
                
                    value = line[firstPos:lastPos]
                    value = value.strip()

                    #limit space number if value is a float
                    try:
                        valueFloat = float(value)
                    except Exception:
                        #extract only first included number if other characters are included
                        allIncludedNumbers = re.findall('\d+', value)
                        if( len(allIncludedNumbers) > 0):
                            value = "{:.2f}".format( float( allIncludedNumbers[0]) )
                        else:
                            #flag value, if no value can be extracted
                            value = "{:.2f}".format( 0.0 )
                    else:
                        value = "{:.2f}".format( float(valueFloat))
                    
                    #search for values, comm: white spaces included to ensure single word search, otherwise mismatch
                    if(line.find(" RHO ") > 0):
                        self.__rho = value
                    elif(line.find(" CE ")  > 0):
                        self.__ce = value
                    elif(line.find(" OPOR ")  > 0):
                        self.__opor = value
                    elif(line.find(" OEFF ") > 0):
                        self.__oeff = value
                    elif(line.find(" MEW ") > 0):
                        self.__mew = value
                    elif(line.find(" AW ") > 0):
                        self.__aw = value
                    elif(line.find(" KLEFF ") > 0):
                        self.__kleff = value
                    elif(line.find(" LAMBDA ") > 0):
                        self.__la = value
                    elif(line.find(" OCAP ") > 0):
                        self.__ocap = value
                    elif(line.find(" O80 ") > 0):
                        self.__o80 = value

                #raise counter, just info 
                lineCounter = lineCounter + 1

        return True        


