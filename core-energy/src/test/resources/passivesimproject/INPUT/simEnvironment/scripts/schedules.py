"""
purpose of this class: This class handles schedule types for usage profiles
institution: IBK, TU Dresden
included functions are:

"""

#---libraries----#
from enum import Enum


##-------------------------Enum Classes-----------------------------##

class ScheduleType(Enum):

    CONSTANT = 1
    DAILY_SEASONAL = 2
    DAILY_WEEK = 3
    ANNUAL = 4
    
class DataType(Enum):

    TEMPERATURE = 1         #temperature in degC, degC
    RELATIVEHUMIDITY = 2    #relative humidity in percent, %
    VENTILATIONRATES = 3    #ventilation rates in m3 per hour, m3/h
    AIRCHANGERATES = 4      #air change rates in units per hour, h^(-1)
    NUMBEROFOCCUPANTS = 5   #number of occupants,
    HEATFLUXDENSITY = 6     #heat flux density (area=usageArea) of equipment, W/m2
    SHADING = 7             #fraction of unshaded window area, [0-1]
    



##-------------------------SCHEDULE CLASS----------------------------##

#class creates and handles different schedule types
class Schedule(object):

    #constructor
    def __init__(self, ScheduleType, DataType):

        #keep schedule characteristics
        self.__ScheduleType = ScheduleType
        self.__DataType = DataType
        
        #init values for type constant
        self.__value = 0.0

        #init values for type daily_seasonal
        self.__dayProfileSummer = []                    #includes 24 values for typical summer day,float
        self.__dayProfileWinter = []                    #includes 24 values for typical winter day,float
        self.__startDaySummer = "1.6."                  #date as string (German format without year)
        self.__startDayWinter = "1.10."                 #date as string (German format without year)

        #init values for type daily_week
        self.__dayProfileWeek = []                      #includes 24 values for typical week day,float
        self.__dayProfileWeekEnd = []                   #includes 24 values for typical week end day,float
        self.__daysWeekend = ["Sat","Sun"]              #includes max. 7 values of day types: [Mon, Tue, Wed, Thu, Fri, Sat, Sun], string

        #init values for type annual
        self.__annualProfile = []                       #includes 8760 values for hourly profile of one year, float


    #return and data access functions
    #purpose of this function:  Returns saved day profile if schedule type is week/weekend 
    #submitted variables:       str_sep --> string with seperator character
    #return values:             str --> string with 24 float values, seperated by str_sep, empty list in case of errors
    def return_WeekDayProfileString(self, str_sep):
        str_values = ""
        print("str_sep: "+str_sep)
        if( self.__ScheduleType == ScheduleType.DAILY_WEEK ):
            if( len(self.__dayProfileWeek) == 24 ):
                for entry in self.__dayProfileWeek:
                    output = "{:.2f}{:s}".format(float(entry),str_sep)
                    str_values += output
            else:
                print("ScheduleObject: requested schedule wasn't initialized correctly.\nSubmitted values are (for working day): %s" % self.__dayProfileWeek )                  
        else:
            print("ScheduleObject: Saved schedule type and requested profile no not match.")
            
        #return string without last character (seperator)
        return str_values[:-1]


    #same as previous function but for week end profile
    def return_WeekEndProfileString(self, str_sep):
        str_values = ""
        print("str_sep: "+str_sep)
        if( self.__ScheduleType == ScheduleType.DAILY_WEEK ):
            if( len(self.__dayProfileWeekEnd) == 24 ):
                for entry in self.__dayProfileWeekEnd:
                    output = "{:.2f}{:s}".format(float(entry),str_sep)
                    str_values += output
            else:
                print("ScheduleObject: requested schedule wasn't initialized correctly.\nSubmitted values are (for week end day): %s" % self.__dayProfileWeekEnd )                
        else:
            print("ScheduleObject: Saved schedule type and requested profile no not match.")

        #return string without last character (seperator)
        return str_values[:-1]


    #same as previous function but for week end profile
    def return_WeekEndDaysString(self, str_sep):
        str_values = ""
        print("str_sep: "+str_sep)
        if( self.__ScheduleType == ScheduleType.DAILY_WEEK ):
            if( len(self.__daysWeekend) <= 7 ) and ( len(self.__daysWeekend) > 0 ):
                for entry in self.__daysWeekend:
                    output = "{:s}{:s}".format(entry,str_sep)
                    str_values += output
            else:
                print("ScheduleObject: requested schedule wasn't initialized correctly.\nSubmitted values are (for week end day types): %s" % self.__daysWeekend )               
        else:
            print("ScheduleObject: Saved schedule type and requested profile no not match.")

        #return string without last character (seperator)
        return str_values[:-1]

    #purpose of this function:  Function submits constant schedule value
    #submitted variables:       constant value of data type "DataType"
    #return values:             none
    def submit_constantSchedule(self, constantValue ):

        if( self.__ScheduleType == ScheduleType.CONSTANT):
            self.__value = float( constantValue )
        else:
            print("Initialized schedule type is not constant.")
            return False
        
        return True


    #purpose of this function:  Function submits daily profile values (summer, winter)
    #submitted variables:       two lists of type "DataType" with 24 values for summer and winter, start summer, start winter
    #return values:             none
    def submit_seasonalDaySchedule(self, dayProfileSummer, dayProfileWinter, startSummer, startWinter ):

        if( self.__ScheduleType == ScheduleType.DAILY_SEASONAL):
            len_1 = len(dayProfileSummer)
            len_2 = len(dayProfileWinter)
            if( len_1 == len_2 ) and (len_1 == 24):
                self.__dayProfileSummer = dayProfileSummer
                self.__dayProfileWinter = dayProfileWinter
                if( self.__checkDate(startSummer) == True) and (self.__checkDate(startWinter) == True):
                    self.__startDaySummer = startSummer
                    self.__startDayWinter = startWinter
                    return True
                else:
                    print("Incorrect data format for start and/ or end date of summer/winter season. Ensure format Day.Month.")
                    return False
            else:
                print("Length of submitted day profiles is inconsistent or not equal to 24.")
                return False
        else:
            print("Initialized schedule type is not seasonal (winter/summer) day profile.")
            return False




    #purpose of this function:  Function submits daily profile values (week, weekend)
    #submitted variables:       two lists of type "DataType" with 24 values for week days and week end days, list with day types
    #return values:             none
    def submit_weekDaySchedule(self, dayProfileWeek, dayProfileWeekEnd, dayListWeekEnd ):

        if( self.__ScheduleType == ScheduleType.DAILY_WEEK):
            try:
                len_1 = len( dayProfileWeek )
                len_2 = len( dayProfileWeekEnd )
                len_3 = len( dayListWeekEnd )
            except Exception:
                print("One or several submitted elements are not of type list.")
                return False
            else:
                if(( len_1 == len_2 ) and (len_1 == 24) and (len_3 <= 7)):
                    self.__dayProfileWeek = dayProfileWeek
                    self.__dayProfileWeekEnd = dayProfileWeekEnd
                    if( self.__checkDays( dayListWeekEnd ) == False):
                        print(dayListWeekEnd)
                        print("ScheduleObject: Day types are not in the correct format, allowed types are: Mon, Tue, Wed, Thu, Fri, Sat, Sun.")
                        return False
                    else:
                        return True
                else:
                    print("Length of submitted day profiles is inconsistent or not equal to 24.")
                    return False
        else:
            print("Initialized schedule type is not weekly (week day, week end day) profile.")
            return False



    #purpose of this function:  Function submits annual profile
    #submitted variables:       list of type "DataType" with 8760 values for each hour of the year
    #return values:             none
    def submit_annualSchedule(self, annualProfile):

        if( self.__ScheduleType == ScheduleType.ANNUAL):
            try:
                len_1 = len(annualProfile)
            except Exception:
                print("Submitted element for annual schedule is not a list.")
                return False
            else:
                if(len_1 == 8760):
                    self.__annualProfile = annualProfile
                else:
                    print("Length of submitted list is not equal to 8760.")
                    return False
        else:
            print("Initialized schedule type is not annual profile.")
            return False




##------------SUPPORTING FUNCTIONS------------------------------------------------------------------------------------------##     

    #supporting function, checks if date is correct   
    def __checkDate(self, date):
        #check if we have two points
        if(date.count(".") != 2):
            return False

        #check if we have two numbers with correct size
        numDates = date.split(".")
        if(len(numDates) <= 3):
            if(int(numDates[0]) > 31):
                return False
            if(int(numDates[1]) > 12):
                return False
        else:
            return False
        
    #supporting function, checks if days are correct   
    def __checkDays( self, listOfDays ):

        #compare submitted day with predefined days
        defaultDayList = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
        for day in listOfDays:
            if day not in defaultDayList:
                print("Unknown day type %s." % day)
                return False
            
        return True
