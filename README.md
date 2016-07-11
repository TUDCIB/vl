# vl
Virtual Laboratory

- OSGi for creating SOA with bundles
- Spring 3.1.1 framework to manage services as beans
- Gemini blueprint for building the bridge from OSGi to Spring
- Hibernate & HSQLDB as database system
- Apache Jena for ontology management
- Jersey for building up REST clients
- RCP for GUI application
- Java3D for BIM viewer
- SLF4J for logging
- Actviti for BPMN management
- [BIMfit](https://openeebim.bau.tu-dresden.de/bimfit.html) for working with IFC 2x3 & 4 files 

## core
The central bundle with configuration of the database and declaration of service interfaces

## core-bim
The IFC service using [BIMfit](https://openeebim.bau.tu-dresden.de/bimfit.html)

## core-configuration
Configuration bundle to manage local paths using Apache Commons Configuration

## core-eeBim
eeBIM resources like constructions, materials, occupancy and climate data. Integration of ISES templating framework.

## core-energy
Thermal energy simulation services using NANDRAD and Therakles from Technische Universität Dresden and Python as scripting language.
Space Boundary Conversion Service using BSPro from Granlund, Finland.

## core-logging
Logging bundle fro runtime issues.

## core-ontology
Ontology integration for managing OWL and RDF data using Apache Jena. It can be used for querying RDF graphs with SPARQL and reasoning with Jena rules.

## core-parent
Parent maven bundle for all bundles starting with "core..." plus sensitivity_input, libraries and SimulationMatrix.model.

## core-target
Target platform for Eclipse.

## de.tudresden.bau.cib.ui.widgets
GUI widgets like Google Map or IFC viewer.

## gui-app
Core component of the desktop application implemented with RCP.

## gui-bim
BIM GUI component including IFC viewer implemented with RCP.

## gui-gis
GIS GUI component including Google Maps implemented with RCP.

## gui-product
GUI product definition for maven.

## gui-product-feature
GUI product definition feature for maven.

## gui-sim-energy
Energy simulation GUI component implemented with RCP.

## libraries
All Java libraries needed additionally to the target platform.

## sensitivity-input
Stochastic methods for varying simulation input data.

## SimulationMatrix.model
Simulation matrix scheme and implementation using EMF.

## vl-gui-rcp-parent
Parent maven bundle for all GUI bundles starting with "gui...".

