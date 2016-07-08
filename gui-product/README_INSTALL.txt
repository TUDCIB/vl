INSTALLATION

Just unzip the archive file to any direction you want (but keep the path to the root in mind 

because you need it for the configuration)

Install Java (JRE 8)
Install Java3D for the 3D viewer


Install python on the PC (recommended version 2.7.9)

https://www.python.org/downloads/

Install if needed the Microsoft Visual C++ Compiler for Python 2.7 (https://www.microsoft.com/en-us/download/confirmation.aspx?id=44266)

Install following Python libs (with pip):
- MatPlotlib (1.5.0) 
- SciPy (if there are problems by using pip -> download a wheel from http://www.lfd.uci.edu/~gohlke/pythonlibs/)
- NumPy (it is also possible that numpy-1.10.4+mkl-cp27 must be installed)
- Enum34

A stable internet connection is needed for calling the decision making analysis.

CONFIGURATION
The 'data' directory is very important - Change in the directory 'data/config' the 

vl.properties file and set
 
PATH_BASE = <absolute path to 'data' directory> 

e.g. you unzipped the archive to D:/VirtualEnergyLab then set
PATH_BASE = D:/VirtualEnergyLab/data

In the 'files' directory are two IFC (2x3) example files.

START
You can start the application by calling the vellauncher.exe.


NOTE:
It is possible that you have to create the directory "C:\Users\YourLogin\AppData\Local\IBK\Therakles" 
manually before running the sensitive analysis.