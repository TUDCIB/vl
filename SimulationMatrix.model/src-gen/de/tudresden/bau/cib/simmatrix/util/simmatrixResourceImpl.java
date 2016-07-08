/**
 */
package de.tudresden.bau.cib.simmatrix.util;

import de.tudresden.bau.cib.simmatrix.DistanceUnit;
import de.tudresden.bau.cib.simmatrix.DocumentRoot;
import de.tudresden.bau.cib.simmatrix.FileFormat;
import de.tudresden.bau.cib.simmatrix.Loads;
import de.tudresden.bau.cib.simmatrix.MaterialUnit;
import de.tudresden.bau.cib.simmatrix.OrientationSide;
import de.tudresden.bau.cib.simmatrix.OrientationUnit;
import de.tudresden.bau.cib.simmatrix.SetPoint;
import de.tudresden.bau.cib.simmatrix.Shading;
import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TBIMGroup;
import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TConstantTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElementGroup;
import de.tudresden.bau.cib.simmatrix.TElements;
import de.tudresden.bau.cib.simmatrix.TElevation;
import de.tudresden.bau.cib.simmatrix.TElevationVariant;
import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TLayer;
import de.tudresden.bau.cib.simmatrix.TMaterialType;
import de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant;
import de.tudresden.bau.cib.simmatrix.TMaterialTypes;
import de.tudresden.bau.cib.simmatrix.TOccupancyType;
import de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant;
import de.tudresden.bau.cib.simmatrix.TOrientation;
import de.tudresden.bau.cib.simmatrix.TOrientationVariant;
import de.tudresden.bau.cib.simmatrix.TScheduleType;
import de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSetPersonLoads;
import de.tudresden.bau.cib.simmatrix.TSetPoint;
import de.tudresden.bau.cib.simmatrix.TSetShading;
import de.tudresden.bau.cib.simmatrix.TSetTemperature;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TSpace;
import de.tudresden.bau.cib.simmatrix.TSpaceGroup;
import de.tudresden.bau.cib.simmatrix.TSpaces;
import de.tudresden.bau.cib.simmatrix.TTarget;
import de.tudresden.bau.cib.simmatrix.TTargetList;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TUsage;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.TargetType;
import de.tudresden.bau.cib.simmatrix.Temperature;
import de.tudresden.bau.cib.simmatrix.TimePeriod;
import de.tudresden.bau.cib.simmatrix.WeatherTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixFactory;

import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;

import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.util.simmatrixResourceFactoryImpl
 * @generated
 */
public class simmatrixResourceImpl extends XMLResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public simmatrixResourceImpl(URI uri) {
		super(uri);
	}

	/**
	 * A load option that turns of the use of the generate data converters.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String OPTION_USE_DATA_CONVERTER = "USE_DATA_CONVERTER";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		if (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER))) {
		  getContents().add
			 (load
				 (new InputSource(inputStream), 
				  (Map<String, Boolean>)options.get(XMLResource.OPTION_PARSER_FEATURES), 
				  (Map<String, ?>)options.get(XMLResource.OPTION_PARSER_PROPERTIES), 
				  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());
		}
		else {
			super.doLoad(inputStream, options);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void doLoad(InputSource inputSource, Map<?, ?> options) throws IOException {
		if (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER))) {
		  getContents().add
			 (load
				 (inputSource,
				  (Map<String, Boolean>)options.get(XMLResource.OPTION_PARSER_FEATURES), 
				  (Map<String, ?>)options.get(XMLResource.OPTION_PARSER_PROPERTIES), 
				  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());
		}
		else {
			super.doLoad(inputSource, options);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final XMLParserPool parserPool = new XMLParserPoolImpl();

	/**
	 * Loads an instance from the input.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param inputSource the input from which to load.
	 * @param features a map of the parser features and their values.
	 * @param properties a map of a parser properties and their values.
	 * @param useLexicalHandler whether a lexical handler should be used during loading.
	 * @return the root object; for the case of a document root, the child of that document root is return.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @generated
	 */
	public static EObject load(InputSource inputSource, Map<String, Boolean> features, Map<String, ?> properties, boolean useLexicalHandler) throws IOException {
		Map<String, Boolean> requiredFeatures = new HashMap<String, Boolean>();
		requiredFeatures.put("http://xml.org/sax/features/namespaces", Boolean.TRUE); 
		if (features != null) {
			requiredFeatures.putAll(features);
		}
		
		if (properties == null) {
			properties = Collections.emptyMap();
		}
		
		SAXParser saxParser = null;
		try {
			saxParser = parserPool.get(requiredFeatures, properties, useLexicalHandler);
			final FrameFactory.DocumentRootStackFrame documentRoot = FrameFactory.INSTANCE.pushDocumentRoot(null, null);
			XMLTypeResourceImpl.Handler handler = new XMLTypeResourceImpl.Handler(documentRoot);
			saxParser.parse(inputSource, handler);
			return FrameFactory.INSTANCE.popDocumentRoot(documentRoot).eContents().get(0);
		}
		catch (Exception exception) {
			throw new IOWrappedException(exception);
		}
		finally {
			parserPool.release(saxParser, requiredFeatures, properties, useLexicalHandler);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public final static class FrameFactory {
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final FrameFactory INSTANCE = new FrameFactory();
	
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected DocumentRootStackFrame documentRoot;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TAssignmentGroupsStackFrame tAssignmentGroups;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TBIMGroupStackFrame tbimGroup;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TCombinationStackFrame tCombination;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TCombinationsStackFrame tCombinations;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TConstantTypeVariantStackFrame tConstantTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TConstructionTypeStackFrame tConstructionType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TConstructionTypesStackFrame tConstructionTypes;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TConstructionTypeVariantStackFrame tConstructionTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TElementGroupStackFrame tElementGroup;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TElementsStackFrame tElements;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TElevationStackFrame tElevation;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TElevationVariantStackFrame tElevationVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TFloatWithUnitsStackFrame tFloatWithUnits;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TLayerStackFrame tLayer;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TMaterialTypeStackFrame tMaterialType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TMaterialTypesStackFrame tMaterialTypes;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TMaterialTypeVariantStackFrame tMaterialTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TOccupancyTypeStackFrame tOccupancyType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TOccupancyTypeVariantStackFrame tOccupancyTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TOrientationStackFrame tOrientation;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TOrientationVariantStackFrame tOrientationVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TScheduleTypeStackFrame tScheduleType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TScheduleTypeVariantStackFrame tScheduleTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSetPersonLoadsStackFrame tSetPersonLoads;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSetPointStackFrame tSetPoint;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSetShadingStackFrame tSetShading;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSetTemperatureStackFrame tSetTemperature;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSimulationMatrixStackFrame tSimulationMatrix;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSpaceStackFrame tSpace;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSpaceGroupStackFrame tSpaceGroup;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TSpacesStackFrame tSpaces;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TTargetStackFrame tTarget;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TTargetListStackFrame tTargetList;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TTargetsStackFrame tTargets;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TUsageStackFrame tUsage;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TVariablesStackFrame tVariables;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TVariantStackFrame tVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWeatherStackFrame tWeather;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWeatherDataSeriesVariantStackFrame tWeatherDataSeriesVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWeatherDataSetVariantStackFrame tWeatherDataSetVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWindowTypeStackFrame tWindowType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWindowTypesStackFrame tWindowTypes;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected TWindowTypeVariantStackFrame tWindowTypeVariant;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame distanceUnit;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame fileFormat;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame loads;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame materialUnit;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame orientationSide;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame orientationUnit;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame setPoint;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame shading;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame targetType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame temperature;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame timePeriod;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame weatherTypes;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame distanceUnitObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame fileFormatObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame loadsObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame materialUnitObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame orientationSideObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame orientationUnitObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame setPointObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame shadingObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame simatType;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame targetTypeObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame temperatureObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame timePeriodObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame tStringList;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected XMLTypeResourceImpl.DataFrame weatherTypesObject;

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public DocumentRootStackFrame pushDocumentRoot(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 DocumentRootStackFrame resultDocumentRoot = documentRoot == null ? new DocumentRootStackFrame() : documentRoot;
			 documentRoot = null;
			 resultDocumentRoot.pushOnto(previous);
			 resultDocumentRoot.handleAttributes(attributes);
			 return resultDocumentRoot;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public DocumentRoot popDocumentRoot(DocumentRootStackFrame documentRoot) {
			DocumentRoot resultDocumentRootValue = documentRoot.popDocumentRoot();
			this.documentRoot = documentRoot;
			return resultDocumentRootValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class DocumentRootStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected DocumentRoot theDocumentRoot;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TAssignmentGroupsStackFrame assignmentGroups;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame bIMREF;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TCombinationStackFrame combination;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TCombinationsStackFrame combinations;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstantTypeVariantStackFrame constantTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypeStackFrame constructionType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypesStackFrame constructionTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypeVariantStackFrame constructionTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame doorTypeVariables;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame element;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElementGroupStackFrame elementGroup;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElementsStackFrame elements;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElevationStackFrame elevationVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElevationVariantStackFrame elevationVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TLayerStackFrame layer;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypeStackFrame materialType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypesStackFrame materialTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypeVariantStackFrame materialTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOccupancyTypeStackFrame occupancyType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOccupancyTypeVariantStackFrame occupancyTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOrientationStackFrame orientationVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOrientationVariantStackFrame orientationVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TScheduleTypeStackFrame scheduleType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TScheduleTypeVariantStackFrame scheduleTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSimulationMatrixStackFrame simulationMatrix;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame space;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSpaceGroupStackFrame spaceGroup;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSpacesStackFrame spaces;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetStackFrame target;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetListStackFrame targetList;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetsStackFrame targets;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TUsageStackFrame usageTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TVariablesStackFrame variables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TVariantStackFrame variant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherDataSeriesVariantStackFrame weatherDataSeriesVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherDataSetVariantStackFrame weatherDataSetVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherStackFrame weatherVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypeStackFrame windowType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypesStackFrame windowTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypeVariantStackFrame windowTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("AssignmentGroups".equals(localName) && "".equals(namespace)) {
					return assignmentGroups = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTAssignmentGroups(this, attributes);
				}
				else if ("BIMREF".equals(localName) && "".equals(namespace)) {
					return bIMREF = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else if ("Combination".equals(localName) && "".equals(namespace)) {
					return combination = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTCombination(this, attributes);
				}
				else if ("Combinations".equals(localName) && "".equals(namespace)) {
					return combinations = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTCombinations(this, attributes);
				}
				else if ("ConstantTypeVariant".equals(localName) && "".equals(namespace)) {
					return constantTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstantTypeVariant(this, attributes);
				}
				else if ("ConstructionType".equals(localName) && "".equals(namespace)) {
					return constructionType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionType(this, attributes);
				}
				else if ("ConstructionTypeVariables".equals(localName) && "".equals(namespace)) {
					return constructionTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionTypes(this, attributes);
				}
				else if ("ConstructionTypeVariant".equals(localName) && "".equals(namespace)) {
					return constructionTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionTypeVariant(this, attributes);
				}
				else if ("DoorTypeVariables".equals(localName) && "".equals(namespace)) {
					return doorTypeVariables = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else if ("Element".equals(localName) && "".equals(namespace)) {
					return element = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else if ("ElementGroup".equals(localName) && "".equals(namespace)) {
					return elementGroup = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElementGroup(this, attributes);
				}
				else if ("Elements".equals(localName) && "".equals(namespace)) {
					return elements = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElements(this, attributes);
				}
				else if ("ElevationVariables".equals(localName) && "".equals(namespace)) {
					return elevationVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElevation(this, attributes);
				}
				else if ("ElevationVariant".equals(localName) && "".equals(namespace)) {
					return elevationVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElevationVariant(this, attributes);
				}
				else if ("Layer".equals(localName) && "".equals(namespace)) {
					return layer = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTLayer(this, attributes);
				}
				else if ("MaterialType".equals(localName) && "".equals(namespace)) {
					return materialType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialType(this, attributes);
				}
				else if ("MaterialTypeVariables".equals(localName) && "".equals(namespace)) {
					return materialTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialTypes(this, attributes);
				}
				else if ("MaterialTypeVariant".equals(localName) && "".equals(namespace)) {
					return materialTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialTypeVariant(this, attributes);
				}
				else if ("OccupancyType".equals(localName) && "".equals(namespace)) {
					return occupancyType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOccupancyType(this, attributes);
				}
				else if ("OccupancyTypeVariant".equals(localName) && "".equals(namespace)) {
					return occupancyTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOccupancyTypeVariant(this, attributes);
				}
				else if ("OrientationVariables".equals(localName) && "".equals(namespace)) {
					return orientationVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOrientation(this, attributes);
				}
				else if ("OrientationVariant".equals(localName) && "".equals(namespace)) {
					return orientationVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOrientationVariant(this, attributes);
				}
				else if ("ScheduleType".equals(localName) && "".equals(namespace)) {
					return scheduleType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTScheduleType(this, attributes);
				}
				else if ("ScheduleTypeVariant".equals(localName) && "".equals(namespace)) {
					return scheduleTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTScheduleTypeVariant(this, attributes);
				}
				else if ("SimulationMatrix".equals(localName) && "".equals(namespace)) {
					return simulationMatrix = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSimulationMatrix(this, attributes);
				}
				else if ("Space".equals(localName) && "".equals(namespace)) {
					return space = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else if ("SpaceGroup".equals(localName) && "".equals(namespace)) {
					return spaceGroup = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSpaceGroup(this, attributes);
				}
				else if ("Spaces".equals(localName) && "".equals(namespace)) {
					return spaces = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSpaces(this, attributes);
				}
				else if ("Target".equals(localName) && "".equals(namespace)) {
					return target = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTarget(this, attributes);
				}
				else if ("TargetList".equals(localName) && "".equals(namespace)) {
					return targetList = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTargetList(this, attributes);
				}
				else if ("Targets".equals(localName) && "".equals(namespace)) {
					return targets = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTargets(this, attributes);
				}
				else if ("UsageTypeVariables".equals(localName) && "".equals(namespace)) {
					return usageTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTUsage(this, attributes);
				}
				else if ("Variables".equals(localName) && "".equals(namespace)) {
					return variables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTVariables(this, attributes);
				}
				else if ("Variant".equals(localName) && "".equals(namespace)) {
					return variant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTVariant(this, attributes);
				}
				else if ("WeatherDataSeriesVariant".equals(localName) && "".equals(namespace)) {
					return weatherDataSeriesVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeatherDataSeriesVariant(this, attributes);
				}
				else if ("WeatherDataSetVariant".equals(localName) && "".equals(namespace)) {
					return weatherDataSetVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeatherDataSetVariant(this, attributes);
				}
				else if ("WeatherVariables".equals(localName) && "".equals(namespace)) {
					return weatherVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeather(this, attributes);
				}
				else if ("WindowType".equals(localName) && "".equals(namespace)) {
					return windowType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowType(this, attributes);
				}
				else if ("WindowTypeVariables".equals(localName) && "".equals(namespace)) {
					return windowTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowTypes(this, attributes);
				}
				else if ("WindowTypeVariant".equals(localName) && "".equals(namespace)) {
					return windowTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == assignmentGroups) {
					theDocumentRoot.setAssignmentGroups(simmatrixResourceImpl.FrameFactory.INSTANCE.popTAssignmentGroups(assignmentGroups));
					assignmentGroups = null;
				}
				else if (child == bIMREF) {
					theDocumentRoot.setBIMREF(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(bIMREF));
					bIMREF = null;
				}
				else if (child == combination) {
					theDocumentRoot.setCombination(simmatrixResourceImpl.FrameFactory.INSTANCE.popTCombination(combination));
					combination = null;
				}
				else if (child == combinations) {
					theDocumentRoot.setCombinations(simmatrixResourceImpl.FrameFactory.INSTANCE.popTCombinations(combinations));
					combinations = null;
				}
				else if (child == constantTypeVariant) {
					theDocumentRoot.setConstantTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstantTypeVariant(constantTypeVariant));
					constantTypeVariant = null;
				}
				else if (child == constructionType) {
					theDocumentRoot.setConstructionType(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionType(constructionType));
					constructionType = null;
				}
				else if (child == constructionTypeVariables) {
					theDocumentRoot.setConstructionTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionTypes(constructionTypeVariables));
					constructionTypeVariables = null;
				}
				else if (child == constructionTypeVariant) {
					theDocumentRoot.setConstructionTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionTypeVariant(constructionTypeVariant));
					constructionTypeVariant = null;
				}
				else if (child == doorTypeVariables) {
					theDocumentRoot.setDoorTypeVariables(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(doorTypeVariables));
					doorTypeVariables = null;
				}
				else if (child == element) {
					theDocumentRoot.setElement(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(element));
					element = null;
				}
				else if (child == elementGroup) {
					theDocumentRoot.setElementGroup(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElementGroup(elementGroup));
					elementGroup = null;
				}
				else if (child == elements) {
					theDocumentRoot.setElements(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElements(elements));
					elements = null;
				}
				else if (child == elevationVariables) {
					theDocumentRoot.setElevationVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElevation(elevationVariables));
					elevationVariables = null;
				}
				else if (child == elevationVariant) {
					theDocumentRoot.setElevationVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElevationVariant(elevationVariant));
					elevationVariant = null;
				}
				else if (child == layer) {
					theDocumentRoot.setLayer(simmatrixResourceImpl.FrameFactory.INSTANCE.popTLayer(layer));
					layer = null;
				}
				else if (child == materialType) {
					theDocumentRoot.setMaterialType(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialType(materialType));
					materialType = null;
				}
				else if (child == materialTypeVariables) {
					theDocumentRoot.setMaterialTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialTypes(materialTypeVariables));
					materialTypeVariables = null;
				}
				else if (child == materialTypeVariant) {
					theDocumentRoot.setMaterialTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialTypeVariant(materialTypeVariant));
					materialTypeVariant = null;
				}
				else if (child == occupancyType) {
					theDocumentRoot.setOccupancyType(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOccupancyType(occupancyType));
					occupancyType = null;
				}
				else if (child == occupancyTypeVariant) {
					theDocumentRoot.setOccupancyTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOccupancyTypeVariant(occupancyTypeVariant));
					occupancyTypeVariant = null;
				}
				else if (child == orientationVariables) {
					theDocumentRoot.setOrientationVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOrientation(orientationVariables));
					orientationVariables = null;
				}
				else if (child == orientationVariant) {
					theDocumentRoot.setOrientationVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOrientationVariant(orientationVariant));
					orientationVariant = null;
				}
				else if (child == scheduleType) {
					theDocumentRoot.setScheduleType(simmatrixResourceImpl.FrameFactory.INSTANCE.popTScheduleType(scheduleType));
					scheduleType = null;
				}
				else if (child == scheduleTypeVariant) {
					theDocumentRoot.setScheduleTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTScheduleTypeVariant(scheduleTypeVariant));
					scheduleTypeVariant = null;
				}
				else if (child == simulationMatrix) {
					theDocumentRoot.setSimulationMatrix(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSimulationMatrix(simulationMatrix));
					simulationMatrix = null;
				}
				else if (child == space) {
					theDocumentRoot.setSpace(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(space));
					space = null;
				}
				else if (child == spaceGroup) {
					theDocumentRoot.setSpaceGroup(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSpaceGroup(spaceGroup));
					spaceGroup = null;
				}
				else if (child == spaces) {
					theDocumentRoot.setSpaces(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSpaces(spaces));
					spaces = null;
				}
				else if (child == target) {
					theDocumentRoot.setTarget(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTarget(target));
					target = null;
				}
				else if (child == targetList) {
					theDocumentRoot.setTargetList(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTargetList(targetList));
					targetList = null;
				}
				else if (child == targets) {
					theDocumentRoot.setTargets(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTargets(targets));
					targets = null;
				}
				else if (child == usageTypeVariables) {
					theDocumentRoot.setUsageTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTUsage(usageTypeVariables));
					usageTypeVariables = null;
				}
				else if (child == variables) {
					theDocumentRoot.setVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTVariables(variables));
					variables = null;
				}
				else if (child == variant) {
					theDocumentRoot.setVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTVariant(variant));
					variant = null;
				}
				else if (child == weatherDataSeriesVariant) {
					theDocumentRoot.setWeatherDataSeriesVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeatherDataSeriesVariant(weatherDataSeriesVariant));
					weatherDataSeriesVariant = null;
				}
				else if (child == weatherDataSetVariant) {
					theDocumentRoot.setWeatherDataSetVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeatherDataSetVariant(weatherDataSetVariant));
					weatherDataSetVariant = null;
				}
				else if (child == weatherVariables) {
					theDocumentRoot.setWeatherVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeather(weatherVariables));
					weatherVariables = null;
				}
				else if (child == windowType) {
					theDocumentRoot.setWindowType(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowType(windowType));
					windowType = null;
				}
				else if (child == windowTypeVariables) {
					theDocumentRoot.setWindowTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowTypes(windowTypeVariables));
					windowTypeVariables = null;
				}
				else if (child == windowTypeVariant) {
					theDocumentRoot.setWindowTypeVariant(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowTypeVariant(windowTypeVariant));
					windowTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theDocumentRoot = simmatrixFactory.eINSTANCE.createDocumentRoot();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected DocumentRoot popDocumentRoot() {
				pop();
				DocumentRoot resultDocumentRootValue = theDocumentRoot;
				theDocumentRoot = null;
				return resultDocumentRootValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TAssignmentGroupsStackFrame pushTAssignmentGroups(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TAssignmentGroupsStackFrame resultTAssignmentGroups = tAssignmentGroups == null ? new TAssignmentGroupsStackFrame() : tAssignmentGroups;
			 tAssignmentGroups = null;
			 resultTAssignmentGroups.pushOnto(previous);
			 resultTAssignmentGroups.handleAttributes(attributes);
			 return resultTAssignmentGroups;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TAssignmentGroups popTAssignmentGroups(TAssignmentGroupsStackFrame tAssignmentGroups) {
			TAssignmentGroups resultTAssignmentGroupsValue = tAssignmentGroups.popTAssignmentGroups();
			this.tAssignmentGroups = tAssignmentGroups;
			return resultTAssignmentGroupsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TAssignmentGroupsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TAssignmentGroups theTAssignmentGroups;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSpacesStackFrame spaces;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElementsStackFrame elements;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Spaces".equals(localName) && "".equals(namespace)) {
					return spaces = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSpaces(this, attributes);
				}
				else if ("Elements".equals(localName) && "".equals(namespace)) {
					return elements = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElements(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == spaces) {
					theTAssignmentGroups.setSpaces(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSpaces(spaces));
					spaces = null;
				}
				else if (child == elements) {
					theTAssignmentGroups.setElements(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElements(elements));
					elements = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTAssignmentGroups = simmatrixFactory.eINSTANCE.createTAssignmentGroups();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TAssignmentGroups popTAssignmentGroups() {
				pop();
				TAssignmentGroups resultTAssignmentGroupsValue = theTAssignmentGroups;
				theTAssignmentGroups = null;
				return resultTAssignmentGroupsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TBIMGroupStackFrame pushTBIMGroup(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TBIMGroupStackFrame resultTBIMGroup = tbimGroup == null ? new TBIMGroupStackFrame() : tbimGroup;
			 tbimGroup = null;
			 resultTBIMGroup.pushOnto(previous);
			 resultTBIMGroup.handleAttributes(attributes);
			 return resultTBIMGroup;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TBIMGroup popTBIMGroup(TBIMGroupStackFrame tbimGroup) {
			TBIMGroup resultTBIMGroupValue = tbimGroup.popTBIMGroup();
			this.tbimGroup = tbimGroup;
			return resultTBIMGroupValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TBIMGroupStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TBIMGroup theTBIMGroup;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame bIMREF;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTBIMGroup.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("BIMREF".equals(localName) && "".equals(namespace)) {
					return bIMREF = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == bIMREF) {
					theTBIMGroup.getBIMREF().add(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(bIMREF));
					bIMREF = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTBIMGroup = simmatrixFactory.eINSTANCE.createTBIMGroup();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TBIMGroup popTBIMGroup() {
				pop();
				TBIMGroup resultTBIMGroupValue = theTBIMGroup;
				theTBIMGroup = null;
				return resultTBIMGroupValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TCombinationStackFrame pushTCombination(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TCombinationStackFrame resultTCombination = tCombination == null ? new TCombinationStackFrame() : tCombination;
			 tCombination = null;
			 resultTCombination.pushOnto(previous);
			 resultTCombination.handleAttributes(attributes);
			 return resultTCombination;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TCombination popTCombination(TCombinationStackFrame tCombination) {
			TCombination resultTCombinationValue = tCombination.popTCombination();
			this.tCombination = tCombination;
			return resultTCombinationValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TCombinationStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TCombination theTCombination;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TVariantStackFrame variant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTCombination.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Variant".equals(localName) && "".equals(namespace)) {
					return variant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == variant) {
					theTCombination.getVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTVariant(variant));
					variant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTCombination = simmatrixFactory.eINSTANCE.createTCombination();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TCombination popTCombination() {
				pop();
				TCombination resultTCombinationValue = theTCombination;
				theTCombination = null;
				return resultTCombinationValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TCombinationsStackFrame pushTCombinations(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TCombinationsStackFrame resultTCombinations = tCombinations == null ? new TCombinationsStackFrame() : tCombinations;
			 tCombinations = null;
			 resultTCombinations.pushOnto(previous);
			 resultTCombinations.handleAttributes(attributes);
			 return resultTCombinations;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TCombinations popTCombinations(TCombinationsStackFrame tCombinations) {
			TCombinations resultTCombinationsValue = tCombinations.popTCombinations();
			this.tCombinations = tCombinations;
			return resultTCombinationsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TCombinationsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TCombinations theTCombinations;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TCombinationStackFrame combination;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Combination".equals(localName) && "".equals(namespace)) {
					return combination = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTCombination(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == combination) {
					theTCombinations.getCombination().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTCombination(combination));
					combination = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTCombinations = simmatrixFactory.eINSTANCE.createTCombinations();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TCombinations popTCombinations() {
				pop();
				TCombinations resultTCombinationsValue = theTCombinations;
				theTCombinations = null;
				return resultTCombinationsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstantTypeVariantStackFrame pushTConstantTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TConstantTypeVariantStackFrame resultTConstantTypeVariant = tConstantTypeVariant == null ? new TConstantTypeVariantStackFrame() : tConstantTypeVariant;
			 tConstantTypeVariant = null;
			 resultTConstantTypeVariant.pushOnto(previous);
			 resultTConstantTypeVariant.handleAttributes(attributes);
			 return resultTConstantTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstantTypeVariant popTConstantTypeVariant(TConstantTypeVariantStackFrame tConstantTypeVariant) {
			TConstantTypeVariant resultTConstantTypeVariantValue = tConstantTypeVariant.popTConstantTypeVariant();
			this.tConstantTypeVariant = tConstantTypeVariant;
			return resultTConstantTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TConstantTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstantTypeVariant theTConstantTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPointStackFrame heatingSetPoint;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPointStackFrame coolingSetPoint;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPointStackFrame personLoad;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPointStackFrame equipmentLoad;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPointStackFrame shading;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTConstantTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("HeatingSetPoint".equals(localName) && "".equals(namespace)) {
					return heatingSetPoint = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPoint(this, attributes);
				}
				else if ("CoolingSetPoint".equals(localName) && "".equals(namespace)) {
					return coolingSetPoint = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPoint(this, attributes);
				}
				else if ("PersonLoad".equals(localName) && "".equals(namespace)) {
					return personLoad = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPoint(this, attributes);
				}
				else if ("EquipmentLoad".equals(localName) && "".equals(namespace)) {
					return equipmentLoad = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPoint(this, attributes);
				}
				else if ("Shading".equals(localName) && "".equals(namespace)) {
					return shading = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPoint(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == heatingSetPoint) {
					theTConstantTypeVariant.setHeatingSetPoint(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPoint(heatingSetPoint));
					heatingSetPoint = null;
				}
				else if (child == coolingSetPoint) {
					theTConstantTypeVariant.setCoolingSetPoint(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPoint(coolingSetPoint));
					coolingSetPoint = null;
				}
				else if (child == personLoad) {
					theTConstantTypeVariant.setPersonLoad(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPoint(personLoad));
					personLoad = null;
				}
				else if (child == equipmentLoad) {
					theTConstantTypeVariant.setEquipmentLoad(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPoint(equipmentLoad));
					equipmentLoad = null;
				}
				else if (child == shading) {
					theTConstantTypeVariant.setShading(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPoint(shading));
					shading = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTConstantTypeVariant = simmatrixFactory.eINSTANCE.createTConstantTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstantTypeVariant popTConstantTypeVariant() {
				pop();
				TConstantTypeVariant resultTConstantTypeVariantValue = theTConstantTypeVariant;
				theTConstantTypeVariant = null;
				return resultTConstantTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionTypeStackFrame pushTConstructionType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TConstructionTypeStackFrame resultTConstructionType = tConstructionType == null ? new TConstructionTypeStackFrame() : tConstructionType;
			 tConstructionType = null;
			 resultTConstructionType.pushOnto(previous);
			 resultTConstructionType.handleAttributes(attributes);
			 return resultTConstructionType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionType popTConstructionType(TConstructionTypeStackFrame tConstructionType) {
			TConstructionType resultTConstructionTypeValue = tConstructionType.popTConstructionType();
			this.tConstructionType = tConstructionType;
			return resultTConstructionTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TConstructionTypeStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionType theTConstructionType;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypeVariantStackFrame constructionTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame orientation;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame source;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "orientation");
				if (theValue != null) {
					theTConstructionType.setOrientation(simmatrixFactory.eINSTANCE.createOrientationSide(theValue));
				}
				theValue = attributes.getValue("", "source");
				if (theValue != null) {
					theTConstructionType.setSource(XMLTypeFactory.eINSTANCE.createAnyURI(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("ConstructionTypeVariant".equals(localName) && "".equals(namespace)) {
					return constructionTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == constructionTypeVariant) {
					theTConstructionType.getConstructionTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionTypeVariant(constructionTypeVariant));
					constructionTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTConstructionType = simmatrixFactory.eINSTANCE.createTConstructionType();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionType popTConstructionType() {
				pop();
				TConstructionType resultTConstructionTypeValue = theTConstructionType;
				theTConstructionType = null;
				return resultTConstructionTypeValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionTypesStackFrame pushTConstructionTypes(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TConstructionTypesStackFrame resultTConstructionTypes = tConstructionTypes == null ? new TConstructionTypesStackFrame() : tConstructionTypes;
			 tConstructionTypes = null;
			 resultTConstructionTypes.pushOnto(previous);
			 resultTConstructionTypes.handleAttributes(attributes);
			 return resultTConstructionTypes;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionTypes popTConstructionTypes(TConstructionTypesStackFrame tConstructionTypes) {
			TConstructionTypes resultTConstructionTypesValue = tConstructionTypes.popTConstructionTypes();
			this.tConstructionTypes = tConstructionTypes;
			return resultTConstructionTypesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TConstructionTypesStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionTypes theTConstructionTypes;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypeStackFrame constructionType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("ConstructionType".equals(localName) && "".equals(namespace)) {
					return constructionType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionType(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == constructionType) {
					theTConstructionTypes.getConstructionType().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionType(constructionType));
					constructionType = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTConstructionTypes = simmatrixFactory.eINSTANCE.createTConstructionTypes();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionTypes popTConstructionTypes() {
				pop();
				TConstructionTypes resultTConstructionTypesValue = theTConstructionTypes;
				theTConstructionTypes = null;
				return resultTConstructionTypesValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionTypeVariantStackFrame pushTConstructionTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TConstructionTypeVariantStackFrame resultTConstructionTypeVariant = tConstructionTypeVariant == null ? new TConstructionTypeVariantStackFrame() : tConstructionTypeVariant;
			 tConstructionTypeVariant = null;
			 resultTConstructionTypeVariant.pushOnto(previous);
			 resultTConstructionTypeVariant.handleAttributes(attributes);
			 return resultTConstructionTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TConstructionTypeVariant popTConstructionTypeVariant(TConstructionTypeVariantStackFrame tConstructionTypeVariant) {
			TConstructionTypeVariant resultTConstructionTypeVariantValue = tConstructionTypeVariant.popTConstructionTypeVariant();
			this.tConstructionTypeVariant = tConstructionTypeVariant;
			return resultTConstructionTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TConstructionTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionTypeVariant theTConstructionTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TLayerStackFrame layer;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTConstructionTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Layer".equals(localName) && "".equals(namespace)) {
					return layer = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTLayer(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == layer) {
					theTConstructionTypeVariant.getLayer().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTLayer(layer));
					layer = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTConstructionTypeVariant = simmatrixFactory.eINSTANCE.createTConstructionTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TConstructionTypeVariant popTConstructionTypeVariant() {
				pop();
				TConstructionTypeVariant resultTConstructionTypeVariantValue = theTConstructionTypeVariant;
				theTConstructionTypeVariant = null;
				return resultTConstructionTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElementGroupStackFrame pushTElementGroup(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TElementGroupStackFrame resultTElementGroup = tElementGroup == null ? new TElementGroupStackFrame() : tElementGroup;
			 tElementGroup = null;
			 resultTElementGroup.pushOnto(previous);
			 resultTElementGroup.handleAttributes(attributes);
			 return resultTElementGroup;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElementGroup popTElementGroup(TElementGroupStackFrame tElementGroup) {
			TElementGroup resultTElementGroupValue = tElementGroup.popTElementGroup();
			this.tElementGroup = tElementGroup;
			return resultTElementGroupValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TElementGroupStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElementGroup theTElementGroup;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame element;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTElementGroup.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Element".equals(localName) && "".equals(namespace)) {
					return element = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == element) {
					theTElementGroup.getElement().add(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(element));
					element = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTElementGroup = simmatrixFactory.eINSTANCE.createTElementGroup();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElementGroup popTElementGroup() {
				pop();
				TElementGroup resultTElementGroupValue = theTElementGroup;
				theTElementGroup = null;
				return resultTElementGroupValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElementsStackFrame pushTElements(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TElementsStackFrame resultTElements = tElements == null ? new TElementsStackFrame() : tElements;
			 tElements = null;
			 resultTElements.pushOnto(previous);
			 resultTElements.handleAttributes(attributes);
			 return resultTElements;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElements popTElements(TElementsStackFrame tElements) {
			TElements resultTElementsValue = tElements.popTElements();
			this.tElements = tElements;
			return resultTElementsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TElementsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElements theTElements;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElementGroupStackFrame elementGroup;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("ElementGroup".equals(localName) && "".equals(namespace)) {
					return elementGroup = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElementGroup(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == elementGroup) {
					theTElements.getElementGroup().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElementGroup(elementGroup));
					elementGroup = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTElements = simmatrixFactory.eINSTANCE.createTElements();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElements popTElements() {
				pop();
				TElements resultTElementsValue = theTElements;
				theTElements = null;
				return resultTElementsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElevationStackFrame pushTElevation(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TElevationStackFrame resultTElevation = tElevation == null ? new TElevationStackFrame() : tElevation;
			 tElevation = null;
			 resultTElevation.pushOnto(previous);
			 resultTElevation.handleAttributes(attributes);
			 return resultTElevation;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElevation popTElevation(TElevationStackFrame tElevation) {
			TElevation resultTElevationValue = tElevation.popTElevation();
			this.tElevation = tElevation;
			return resultTElevationValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TElevationStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElevation theTElevation;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElevationVariantStackFrame elevationVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("ElevationVariant".equals(localName) && "".equals(namespace)) {
					return elevationVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElevationVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == elevationVariant) {
					theTElevation.getElevationVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElevationVariant(elevationVariant));
					elevationVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTElevation = simmatrixFactory.eINSTANCE.createTElevation();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElevation popTElevation() {
				pop();
				TElevation resultTElevationValue = theTElevation;
				theTElevation = null;
				return resultTElevationValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElevationVariantStackFrame pushTElevationVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TElevationVariantStackFrame resultTElevationVariant = tElevationVariant == null ? new TElevationVariantStackFrame() : tElevationVariant;
			 tElevationVariant = null;
			 resultTElevationVariant.pushOnto(previous);
			 resultTElevationVariant.handleAttributes(attributes);
			 return resultTElevationVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TElevationVariant popTElevationVariant(TElevationVariantStackFrame tElevationVariant) {
			TElevationVariant resultTElevationVariantValue = tElevationVariant.popTElevationVariant();
			this.tElevationVariant = tElevationVariant;
			return resultTElevationVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TElevationVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElevationVariant theTElevationVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTElevationVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTElevationVariant.setUnit(simmatrixFactory.eINSTANCE.createDistanceUnit(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTElevationVariant = simmatrixFactory.eINSTANCE.createTElevationVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TElevationVariant popTElevationVariant() {
				pop();
				TElevationVariant resultTElevationVariantValue = theTElevationVariant;
				theTElevationVariant = null;
				return resultTElevationVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TFloatWithUnitsStackFrame pushTFloatWithUnits(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TFloatWithUnitsStackFrame resultTFloatWithUnits = tFloatWithUnits == null ? new TFloatWithUnitsStackFrame() : tFloatWithUnits;
			 tFloatWithUnits = null;
			 resultTFloatWithUnits.pushOnto(previous);
			 resultTFloatWithUnits.handleAttributes(attributes);
			 return resultTFloatWithUnits;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TFloatWithUnits popTFloatWithUnits(TFloatWithUnitsStackFrame tFloatWithUnits) {
			TFloatWithUnits resultTFloatWithUnitsValue = tFloatWithUnits.popTFloatWithUnits();
			this.tFloatWithUnits = tFloatWithUnits;
			return resultTFloatWithUnitsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TFloatWithUnitsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TFloatWithUnits theTFloatWithUnits;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTFloatWithUnits.setUnit(simmatrixFactory.eINSTANCE.createMaterialUnit(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTFloatWithUnits = simmatrixFactory.eINSTANCE.createTFloatWithUnits();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TFloatWithUnits popTFloatWithUnits() {
				pop();
				TFloatWithUnits resultTFloatWithUnitsValue = theTFloatWithUnits;
				theTFloatWithUnits = null;
				return resultTFloatWithUnitsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TLayerStackFrame pushTLayer(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TLayerStackFrame resultTLayer = tLayer == null ? new TLayerStackFrame() : tLayer;
			 tLayer = null;
			 resultTLayer.pushOnto(previous);
			 resultTLayer.handleAttributes(attributes);
			 return resultTLayer;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TLayer popTLayer(TLayerStackFrame tLayer) {
			TLayer resultTLayerValue = tLayer.popTLayer();
			this.tLayer = tLayer;
			return resultTLayerValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TLayerStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TLayer theTLayer;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame thickness;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "thickness");
				if (theValue != null) {
					theTLayer.setThickness(XMLTypeFactory.eINSTANCE.createDouble(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTLayer.setUnit(simmatrixFactory.eINSTANCE.createDistanceUnit(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTLayer = simmatrixFactory.eINSTANCE.createTLayer();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TLayer popTLayer() {
				pop();
				TLayer resultTLayerValue = theTLayer;
				theTLayer = null;
				return resultTLayerValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialTypeStackFrame pushTMaterialType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TMaterialTypeStackFrame resultTMaterialType = tMaterialType == null ? new TMaterialTypeStackFrame() : tMaterialType;
			 tMaterialType = null;
			 resultTMaterialType.pushOnto(previous);
			 resultTMaterialType.handleAttributes(attributes);
			 return resultTMaterialType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialType popTMaterialType(TMaterialTypeStackFrame tMaterialType) {
			TMaterialType resultTMaterialTypeValue = tMaterialType.popTMaterialType();
			this.tMaterialType = tMaterialType;
			return resultTMaterialTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TMaterialTypeStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialType theTMaterialType;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypeVariantStackFrame materialTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame source;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "source");
				if (theValue != null) {
					theTMaterialType.setSource(XMLTypeFactory.eINSTANCE.createAnyURI(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("MaterialTypeVariant".equals(localName) && "".equals(namespace)) {
					return materialTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == materialTypeVariant) {
					theTMaterialType.getMaterialTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialTypeVariant(materialTypeVariant));
					materialTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTMaterialType = simmatrixFactory.eINSTANCE.createTMaterialType();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialType popTMaterialType() {
				pop();
				TMaterialType resultTMaterialTypeValue = theTMaterialType;
				theTMaterialType = null;
				return resultTMaterialTypeValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialTypesStackFrame pushTMaterialTypes(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TMaterialTypesStackFrame resultTMaterialTypes = tMaterialTypes == null ? new TMaterialTypesStackFrame() : tMaterialTypes;
			 tMaterialTypes = null;
			 resultTMaterialTypes.pushOnto(previous);
			 resultTMaterialTypes.handleAttributes(attributes);
			 return resultTMaterialTypes;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialTypes popTMaterialTypes(TMaterialTypesStackFrame tMaterialTypes) {
			TMaterialTypes resultTMaterialTypesValue = tMaterialTypes.popTMaterialTypes();
			this.tMaterialTypes = tMaterialTypes;
			return resultTMaterialTypesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TMaterialTypesStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialTypes theTMaterialTypes;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypeStackFrame materialType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("MaterialType".equals(localName) && "".equals(namespace)) {
					return materialType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialType(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == materialType) {
					theTMaterialTypes.getMaterialType().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialType(materialType));
					materialType = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTMaterialTypes = simmatrixFactory.eINSTANCE.createTMaterialTypes();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialTypes popTMaterialTypes() {
				pop();
				TMaterialTypes resultTMaterialTypesValue = theTMaterialTypes;
				theTMaterialTypes = null;
				return resultTMaterialTypesValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialTypeVariantStackFrame pushTMaterialTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TMaterialTypeVariantStackFrame resultTMaterialTypeVariant = tMaterialTypeVariant == null ? new TMaterialTypeVariantStackFrame() : tMaterialTypeVariant;
			 tMaterialTypeVariant = null;
			 resultTMaterialTypeVariant.pushOnto(previous);
			 resultTMaterialTypeVariant.handleAttributes(attributes);
			 return resultTMaterialTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TMaterialTypeVariant popTMaterialTypeVariant(TMaterialTypeVariantStackFrame tMaterialTypeVariant) {
			TMaterialTypeVariant resultTMaterialTypeVariantValue = tMaterialTypeVariant.popTMaterialTypeVariant();
			this.tMaterialTypeVariant = tMaterialTypeVariant;
			return resultTMaterialTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TMaterialTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialTypeVariant theTMaterialTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame density;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame specificHeatCapacity;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame conductivity;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame waterVaporDiffusionResistanceFactor;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame waterAbsorptionCapacity;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame openPorosity;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame effectiveSaturation;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame capillarySaturationContent;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame hygroscopicSorption;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame thermalConductivity;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame liquidWaterConductivityEffectiveSaturation;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTMaterialTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Density".equals(localName) && "".equals(namespace)) {
					return density = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("SpecificHeatCapacity".equals(localName) && "".equals(namespace)) {
					return specificHeatCapacity = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("Conductivity".equals(localName) && "".equals(namespace)) {
					return conductivity = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("WaterVaporDiffusionResistanceFactor".equals(localName) && "".equals(namespace)) {
					return waterVaporDiffusionResistanceFactor = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("WaterAbsorptionCapacity".equals(localName) && "".equals(namespace)) {
					return waterAbsorptionCapacity = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("OpenPorosity".equals(localName) && "".equals(namespace)) {
					return openPorosity = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("EffectiveSaturation".equals(localName) && "".equals(namespace)) {
					return effectiveSaturation = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("CapillarySaturationContent".equals(localName) && "".equals(namespace)) {
					return capillarySaturationContent = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("HygroscopicSorption".equals(localName) && "".equals(namespace)) {
					return hygroscopicSorption = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("ThermalConductivity".equals(localName) && "".equals(namespace)) {
					return thermalConductivity = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("LiquidWaterConductivityEffectiveSaturation".equals(localName) && "".equals(namespace)) {
					return liquidWaterConductivityEffectiveSaturation = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == density) {
					theTMaterialTypeVariant.setDensity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(density));
					density = null;
				}
				else if (child == specificHeatCapacity) {
					theTMaterialTypeVariant.setSpecificHeatCapacity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(specificHeatCapacity));
					specificHeatCapacity = null;
				}
				else if (child == conductivity) {
					theTMaterialTypeVariant.setConductivity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(conductivity));
					conductivity = null;
				}
				else if (child == waterVaporDiffusionResistanceFactor) {
					theTMaterialTypeVariant.setWaterVaporDiffusionResistanceFactor(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(waterVaporDiffusionResistanceFactor));
					waterVaporDiffusionResistanceFactor = null;
				}
				else if (child == waterAbsorptionCapacity) {
					theTMaterialTypeVariant.setWaterAbsorptionCapacity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(waterAbsorptionCapacity));
					waterAbsorptionCapacity = null;
				}
				else if (child == openPorosity) {
					theTMaterialTypeVariant.setOpenPorosity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(openPorosity));
					openPorosity = null;
				}
				else if (child == effectiveSaturation) {
					theTMaterialTypeVariant.setEffectiveSaturation(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(effectiveSaturation));
					effectiveSaturation = null;
				}
				else if (child == capillarySaturationContent) {
					theTMaterialTypeVariant.setCapillarySaturationContent(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(capillarySaturationContent));
					capillarySaturationContent = null;
				}
				else if (child == hygroscopicSorption) {
					theTMaterialTypeVariant.setHygroscopicSorption(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(hygroscopicSorption));
					hygroscopicSorption = null;
				}
				else if (child == thermalConductivity) {
					theTMaterialTypeVariant.setThermalConductivity(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(thermalConductivity));
					thermalConductivity = null;
				}
				else if (child == liquidWaterConductivityEffectiveSaturation) {
					theTMaterialTypeVariant.setLiquidWaterConductivityEffectiveSaturation(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(liquidWaterConductivityEffectiveSaturation));
					liquidWaterConductivityEffectiveSaturation = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTMaterialTypeVariant = simmatrixFactory.eINSTANCE.createTMaterialTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TMaterialTypeVariant popTMaterialTypeVariant() {
				pop();
				TMaterialTypeVariant resultTMaterialTypeVariantValue = theTMaterialTypeVariant;
				theTMaterialTypeVariant = null;
				return resultTMaterialTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOccupancyTypeStackFrame pushTOccupancyType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TOccupancyTypeStackFrame resultTOccupancyType = tOccupancyType == null ? new TOccupancyTypeStackFrame() : tOccupancyType;
			 tOccupancyType = null;
			 resultTOccupancyType.pushOnto(previous);
			 resultTOccupancyType.handleAttributes(attributes);
			 return resultTOccupancyType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOccupancyType popTOccupancyType(TOccupancyTypeStackFrame tOccupancyType) {
			TOccupancyType resultTOccupancyTypeValue = tOccupancyType.popTOccupancyType();
			this.tOccupancyType = tOccupancyType;
			return resultTOccupancyTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TOccupancyTypeStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOccupancyType theTOccupancyType;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOccupancyTypeVariantStackFrame occupancyTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame source;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "source");
				if (theValue != null) {
					theTOccupancyType.setSource(XMLTypeFactory.eINSTANCE.createAnyURI(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("OccupancyTypeVariant".equals(localName) && "".equals(namespace)) {
					return occupancyTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOccupancyTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == occupancyTypeVariant) {
					theTOccupancyType.getOccupancyTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOccupancyTypeVariant(occupancyTypeVariant));
					occupancyTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTOccupancyType = simmatrixFactory.eINSTANCE.createTOccupancyType();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOccupancyType popTOccupancyType() {
				pop();
				TOccupancyType resultTOccupancyTypeValue = theTOccupancyType;
				theTOccupancyType = null;
				return resultTOccupancyTypeValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOccupancyTypeVariantStackFrame pushTOccupancyTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TOccupancyTypeVariantStackFrame resultTOccupancyTypeVariant = tOccupancyTypeVariant == null ? new TOccupancyTypeVariantStackFrame() : tOccupancyTypeVariant;
			 tOccupancyTypeVariant = null;
			 resultTOccupancyTypeVariant.pushOnto(previous);
			 resultTOccupancyTypeVariant.handleAttributes(attributes);
			 return resultTOccupancyTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOccupancyTypeVariant popTOccupancyTypeVariant(TOccupancyTypeVariantStackFrame tOccupancyTypeVariant) {
			TOccupancyTypeVariant resultTOccupancyTypeVariantValue = tOccupancyTypeVariant.popTOccupancyTypeVariant();
			this.tOccupancyTypeVariant = tOccupancyTypeVariant;
			return resultTOccupancyTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TOccupancyTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOccupancyTypeVariant theTOccupancyTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame period;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTOccupancyTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
				theValue = attributes.getValue("", "period");
				if (theValue != null) {
					theTOccupancyTypeVariant.setPeriod(simmatrixFactory.eINSTANCE.createTimePeriod(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTOccupancyTypeVariant.setType(simmatrixFactory.eINSTANCE.createFileFormat(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTOccupancyTypeVariant = simmatrixFactory.eINSTANCE.createTOccupancyTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOccupancyTypeVariant popTOccupancyTypeVariant() {
				pop();
				TOccupancyTypeVariant resultTOccupancyTypeVariantValue = theTOccupancyTypeVariant;
				theTOccupancyTypeVariant = null;
				return resultTOccupancyTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOrientationStackFrame pushTOrientation(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TOrientationStackFrame resultTOrientation = tOrientation == null ? new TOrientationStackFrame() : tOrientation;
			 tOrientation = null;
			 resultTOrientation.pushOnto(previous);
			 resultTOrientation.handleAttributes(attributes);
			 return resultTOrientation;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOrientation popTOrientation(TOrientationStackFrame tOrientation) {
			TOrientation resultTOrientationValue = tOrientation.popTOrientation();
			this.tOrientation = tOrientation;
			return resultTOrientationValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TOrientationStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOrientation theTOrientation;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOrientationVariantStackFrame orientationVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("OrientationVariant".equals(localName) && "".equals(namespace)) {
					return orientationVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOrientationVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == orientationVariant) {
					theTOrientation.getOrientationVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOrientationVariant(orientationVariant));
					orientationVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTOrientation = simmatrixFactory.eINSTANCE.createTOrientation();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOrientation popTOrientation() {
				pop();
				TOrientation resultTOrientationValue = theTOrientation;
				theTOrientation = null;
				return resultTOrientationValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOrientationVariantStackFrame pushTOrientationVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TOrientationVariantStackFrame resultTOrientationVariant = tOrientationVariant == null ? new TOrientationVariantStackFrame() : tOrientationVariant;
			 tOrientationVariant = null;
			 resultTOrientationVariant.pushOnto(previous);
			 resultTOrientationVariant.handleAttributes(attributes);
			 return resultTOrientationVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TOrientationVariant popTOrientationVariant(TOrientationVariantStackFrame tOrientationVariant) {
			TOrientationVariant resultTOrientationVariantValue = tOrientationVariant.popTOrientationVariant();
			this.tOrientationVariant = tOrientationVariant;
			return resultTOrientationVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TOrientationVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOrientationVariant theTOrientationVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTOrientationVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTOrientationVariant.setUnit(simmatrixFactory.eINSTANCE.createOrientationUnit(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTOrientationVariant = simmatrixFactory.eINSTANCE.createTOrientationVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TOrientationVariant popTOrientationVariant() {
				pop();
				TOrientationVariant resultTOrientationVariantValue = theTOrientationVariant;
				theTOrientationVariant = null;
				return resultTOrientationVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TScheduleTypeStackFrame pushTScheduleType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TScheduleTypeStackFrame resultTScheduleType = tScheduleType == null ? new TScheduleTypeStackFrame() : tScheduleType;
			 tScheduleType = null;
			 resultTScheduleType.pushOnto(previous);
			 resultTScheduleType.handleAttributes(attributes);
			 return resultTScheduleType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TScheduleType popTScheduleType(TScheduleTypeStackFrame tScheduleType) {
			TScheduleType resultTScheduleTypeValue = tScheduleType.popTScheduleType();
			this.tScheduleType = tScheduleType;
			return resultTScheduleTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TScheduleTypeStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TScheduleType theTScheduleType;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TScheduleTypeVariantStackFrame scheduleTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstantTypeVariantStackFrame constantTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame source;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "source");
				if (theValue != null) {
					theTScheduleType.setSource(XMLTypeFactory.eINSTANCE.createAnyURI(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("ScheduleTypeVariant".equals(localName) && "".equals(namespace)) {
					return scheduleTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTScheduleTypeVariant(this, attributes);
				}
				else if ("ConstantTypeVariant".equals(localName) && "".equals(namespace)) {
					return constantTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstantTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == scheduleTypeVariant) {
					theTScheduleType.getScheduleTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTScheduleTypeVariant(scheduleTypeVariant));
					scheduleTypeVariant = null;
				}
				else if (child == constantTypeVariant) {
					theTScheduleType.getConstantTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstantTypeVariant(constantTypeVariant));
					constantTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTScheduleType = simmatrixFactory.eINSTANCE.createTScheduleType();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TScheduleType popTScheduleType() {
				pop();
				TScheduleType resultTScheduleTypeValue = theTScheduleType;
				theTScheduleType = null;
				return resultTScheduleTypeValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TScheduleTypeVariantStackFrame pushTScheduleTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TScheduleTypeVariantStackFrame resultTScheduleTypeVariant = tScheduleTypeVariant == null ? new TScheduleTypeVariantStackFrame() : tScheduleTypeVariant;
			 tScheduleTypeVariant = null;
			 resultTScheduleTypeVariant.pushOnto(previous);
			 resultTScheduleTypeVariant.handleAttributes(attributes);
			 return resultTScheduleTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TScheduleTypeVariant popTScheduleTypeVariant(TScheduleTypeVariantStackFrame tScheduleTypeVariant) {
			TScheduleTypeVariant resultTScheduleTypeVariantValue = tScheduleTypeVariant.popTScheduleTypeVariant();
			this.tScheduleTypeVariant = tScheduleTypeVariant;
			return resultTScheduleTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TScheduleTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TScheduleTypeVariant theTScheduleTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetTemperatureStackFrame heatingSetPoint;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetTemperatureStackFrame coolingSetPoint;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPersonLoadsStackFrame personLoad;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetPersonLoadsStackFrame equipmentLoad;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSetShadingStackFrame shading;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTScheduleTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("HeatingSetPoint".equals(localName) && "".equals(namespace)) {
					return heatingSetPoint = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetTemperature(this, attributes);
				}
				else if ("CoolingSetPoint".equals(localName) && "".equals(namespace)) {
					return coolingSetPoint = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetTemperature(this, attributes);
				}
				else if ("PersonLoad".equals(localName) && "".equals(namespace)) {
					return personLoad = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPersonLoads(this, attributes);
				}
				else if ("EquipmentLoad".equals(localName) && "".equals(namespace)) {
					return equipmentLoad = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetPersonLoads(this, attributes);
				}
				else if ("Shading".equals(localName) && "".equals(namespace)) {
					return shading = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSetShading(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == heatingSetPoint) {
					theTScheduleTypeVariant.setHeatingSetPoint(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetTemperature(heatingSetPoint));
					heatingSetPoint = null;
				}
				else if (child == coolingSetPoint) {
					theTScheduleTypeVariant.setCoolingSetPoint(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetTemperature(coolingSetPoint));
					coolingSetPoint = null;
				}
				else if (child == personLoad) {
					theTScheduleTypeVariant.setPersonLoad(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPersonLoads(personLoad));
					personLoad = null;
				}
				else if (child == equipmentLoad) {
					theTScheduleTypeVariant.setEquipmentLoad(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetPersonLoads(equipmentLoad));
					equipmentLoad = null;
				}
				else if (child == shading) {
					theTScheduleTypeVariant.setShading(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSetShading(shading));
					shading = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTScheduleTypeVariant = simmatrixFactory.eINSTANCE.createTScheduleTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TScheduleTypeVariant popTScheduleTypeVariant() {
				pop();
				TScheduleTypeVariant resultTScheduleTypeVariantValue = theTScheduleTypeVariant;
				theTScheduleTypeVariant = null;
				return resultTScheduleTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetPersonLoadsStackFrame pushTSetPersonLoads(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSetPersonLoadsStackFrame resultTSetPersonLoads = tSetPersonLoads == null ? new TSetPersonLoadsStackFrame() : tSetPersonLoads;
			 tSetPersonLoads = null;
			 resultTSetPersonLoads.pushOnto(previous);
			 resultTSetPersonLoads.handleAttributes(attributes);
			 return resultTSetPersonLoads;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetPersonLoads popTSetPersonLoads(TSetPersonLoadsStackFrame tSetPersonLoads) {
			TSetPersonLoads resultTSetPersonLoadsValue = tSetPersonLoads.popTSetPersonLoads();
			this.tSetPersonLoads = tSetPersonLoads;
			return resultTSetPersonLoadsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSetPersonLoadsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetPersonLoads theTSetPersonLoads;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame period;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "period");
				if (theValue != null) {
					theTSetPersonLoads.setPeriod(simmatrixFactory.eINSTANCE.createTimePeriod(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTSetPersonLoads.setType(simmatrixFactory.eINSTANCE.createFileFormat(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTSetPersonLoads.setUnit(simmatrixFactory.eINSTANCE.createLoads(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSetPersonLoads = simmatrixFactory.eINSTANCE.createTSetPersonLoads();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetPersonLoads popTSetPersonLoads() {
				pop();
				TSetPersonLoads resultTSetPersonLoadsValue = theTSetPersonLoads;
				theTSetPersonLoads = null;
				return resultTSetPersonLoadsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetPointStackFrame pushTSetPoint(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSetPointStackFrame resultTSetPoint = tSetPoint == null ? new TSetPointStackFrame() : tSetPoint;
			 tSetPoint = null;
			 resultTSetPoint.pushOnto(previous);
			 resultTSetPoint.handleAttributes(attributes);
			 return resultTSetPoint;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetPoint popTSetPoint(TSetPointStackFrame tSetPoint) {
			TSetPoint resultTSetPointValue = tSetPoint.popTSetPoint();
			this.tSetPoint = tSetPoint;
			return resultTSetPointValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSetPointStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetPoint theTSetPoint;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTSetPoint.setUnit(simmatrixFactory.eINSTANCE.createSetPoint(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSetPoint = simmatrixFactory.eINSTANCE.createTSetPoint();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetPoint popTSetPoint() {
				pop();
				TSetPoint resultTSetPointValue = theTSetPoint;
				theTSetPoint = null;
				return resultTSetPointValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetShadingStackFrame pushTSetShading(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSetShadingStackFrame resultTSetShading = tSetShading == null ? new TSetShadingStackFrame() : tSetShading;
			 tSetShading = null;
			 resultTSetShading.pushOnto(previous);
			 resultTSetShading.handleAttributes(attributes);
			 return resultTSetShading;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetShading popTSetShading(TSetShadingStackFrame tSetShading) {
			TSetShading resultTSetShadingValue = tSetShading.popTSetShading();
			this.tSetShading = tSetShading;
			return resultTSetShadingValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSetShadingStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetShading theTSetShading;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame period;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "period");
				if (theValue != null) {
					theTSetShading.setPeriod(simmatrixFactory.eINSTANCE.createTimePeriod(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTSetShading.setType(simmatrixFactory.eINSTANCE.createFileFormat(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTSetShading.setUnit(simmatrixFactory.eINSTANCE.createShading(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSetShading = simmatrixFactory.eINSTANCE.createTSetShading();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetShading popTSetShading() {
				pop();
				TSetShading resultTSetShadingValue = theTSetShading;
				theTSetShading = null;
				return resultTSetShadingValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetTemperatureStackFrame pushTSetTemperature(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSetTemperatureStackFrame resultTSetTemperature = tSetTemperature == null ? new TSetTemperatureStackFrame() : tSetTemperature;
			 tSetTemperature = null;
			 resultTSetTemperature.pushOnto(previous);
			 resultTSetTemperature.handleAttributes(attributes);
			 return resultTSetTemperature;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSetTemperature popTSetTemperature(TSetTemperatureStackFrame tSetTemperature) {
			TSetTemperature resultTSetTemperatureValue = tSetTemperature.popTSetTemperature();
			this.tSetTemperature = tSetTemperature;
			return resultTSetTemperatureValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSetTemperatureStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetTemperature theTSetTemperature;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame period;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame unit;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "period");
				if (theValue != null) {
					theTSetTemperature.setPeriod(simmatrixFactory.eINSTANCE.createTimePeriod(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTSetTemperature.setType(simmatrixFactory.eINSTANCE.createFileFormat(theValue));
				}
				theValue = attributes.getValue("", "unit");
				if (theValue != null) {
					theTSetTemperature.setUnit(simmatrixFactory.eINSTANCE.createTemperature(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSetTemperature = simmatrixFactory.eINSTANCE.createTSetTemperature();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSetTemperature popTSetTemperature() {
				pop();
				TSetTemperature resultTSetTemperatureValue = theTSetTemperature;
				theTSetTemperature = null;
				return resultTSetTemperatureValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSimulationMatrixStackFrame pushTSimulationMatrix(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSimulationMatrixStackFrame resultTSimulationMatrix = tSimulationMatrix == null ? new TSimulationMatrixStackFrame() : tSimulationMatrix;
			 tSimulationMatrix = null;
			 resultTSimulationMatrix.pushOnto(previous);
			 resultTSimulationMatrix.handleAttributes(attributes);
			 return resultTSimulationMatrix;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSimulationMatrix popTSimulationMatrix(TSimulationMatrixStackFrame tSimulationMatrix) {
			TSimulationMatrix resultTSimulationMatrixValue = tSimulationMatrix.popTSimulationMatrix();
			this.tSimulationMatrix = tSimulationMatrix;
			return resultTSimulationMatrixValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSimulationMatrixStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSimulationMatrix theTSimulationMatrix;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetsStackFrame targets;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TVariablesStackFrame variables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TAssignmentGroupsStackFrame assignmentGroups;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TCombinationsStackFrame combinations;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTSimulationMatrix.setId(XMLTypeFactory.eINSTANCE.createString(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTSimulationMatrix.setType(simmatrixFactory.eINSTANCE.createSimatType(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Targets".equals(localName) && "".equals(namespace)) {
					return targets = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTargets(this, attributes);
				}
				else if ("Variables".equals(localName) && "".equals(namespace)) {
					return variables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTVariables(this, attributes);
				}
				else if ("AssignmentGroups".equals(localName) && "".equals(namespace)) {
					return assignmentGroups = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTAssignmentGroups(this, attributes);
				}
				else if ("Combinations".equals(localName) && "".equals(namespace)) {
					return combinations = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTCombinations(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == targets) {
					theTSimulationMatrix.setTargets(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTargets(targets));
					targets = null;
				}
				else if (child == variables) {
					theTSimulationMatrix.setVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTVariables(variables));
					variables = null;
				}
				else if (child == assignmentGroups) {
					theTSimulationMatrix.setAssignmentGroups(simmatrixResourceImpl.FrameFactory.INSTANCE.popTAssignmentGroups(assignmentGroups));
					assignmentGroups = null;
				}
				else if (child == combinations) {
					theTSimulationMatrix.setCombinations(simmatrixResourceImpl.FrameFactory.INSTANCE.popTCombinations(combinations));
					combinations = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSimulationMatrix = simmatrixFactory.eINSTANCE.createTSimulationMatrix();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSimulationMatrix popTSimulationMatrix() {
				pop();
				TSimulationMatrix resultTSimulationMatrixValue = theTSimulationMatrix;
				theTSimulationMatrix = null;
				return resultTSimulationMatrixValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpaceStackFrame pushTSpace(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSpaceStackFrame resultTSpace = tSpace == null ? new TSpaceStackFrame() : tSpace;
			 tSpace = null;
			 resultTSpace.pushOnto(previous);
			 resultTSpace.handleAttributes(attributes);
			 return resultTSpace;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpace popTSpace(TSpaceStackFrame tSpace) {
			TSpace resultTSpaceValue = tSpace.popTSpace();
			this.tSpace = tSpace;
			return resultTSpaceValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSpaceStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpace theTSpace;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTSpace.setId(XMLTypeFactory.eINSTANCE.createString(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSpace = simmatrixFactory.eINSTANCE.createTSpace();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpace popTSpace() {
				pop();
				TSpace resultTSpaceValue = theTSpace;
				theTSpace = null;
				return resultTSpaceValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpaceGroupStackFrame pushTSpaceGroup(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSpaceGroupStackFrame resultTSpaceGroup = tSpaceGroup == null ? new TSpaceGroupStackFrame() : tSpaceGroup;
			 tSpaceGroup = null;
			 resultTSpaceGroup.pushOnto(previous);
			 resultTSpaceGroup.handleAttributes(attributes);
			 return resultTSpaceGroup;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpaceGroup popTSpaceGroup(TSpaceGroupStackFrame tSpaceGroup) {
			TSpaceGroup resultTSpaceGroupValue = tSpaceGroup.popTSpaceGroup();
			this.tSpaceGroup = tSpaceGroup;
			return resultTSpaceGroupValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSpaceGroupStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpaceGroup theTSpaceGroup;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame space;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTSpaceGroup.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Space".equals(localName) && "".equals(namespace)) {
					return space = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == space) {
					theTSpaceGroup.getSpace().add(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(space));
					space = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSpaceGroup = simmatrixFactory.eINSTANCE.createTSpaceGroup();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpaceGroup popTSpaceGroup() {
				pop();
				TSpaceGroup resultTSpaceGroupValue = theTSpaceGroup;
				theTSpaceGroup = null;
				return resultTSpaceGroupValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpacesStackFrame pushTSpaces(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TSpacesStackFrame resultTSpaces = tSpaces == null ? new TSpacesStackFrame() : tSpaces;
			 tSpaces = null;
			 resultTSpaces.pushOnto(previous);
			 resultTSpaces.handleAttributes(attributes);
			 return resultTSpaces;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TSpaces popTSpaces(TSpacesStackFrame tSpaces) {
			TSpaces resultTSpacesValue = tSpaces.popTSpaces();
			this.tSpaces = tSpaces;
			return resultTSpacesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TSpacesStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpaces theTSpaces;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TSpaceGroupStackFrame spaceGroup;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTSpaces.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("SpaceGroup".equals(localName) && "".equals(namespace)) {
					return spaceGroup = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTSpaceGroup(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == spaceGroup) {
					theTSpaces.getSpaceGroup().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTSpaceGroup(spaceGroup));
					spaceGroup = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTSpaces = simmatrixFactory.eINSTANCE.createTSpaces();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TSpaces popTSpaces() {
				pop();
				TSpaces resultTSpacesValue = theTSpaces;
				theTSpaces = null;
				return resultTSpacesValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTargetStackFrame pushTTarget(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TTargetStackFrame resultTTarget = tTarget == null ? new TTargetStackFrame() : tTarget;
			 tTarget = null;
			 resultTTarget.pushOnto(previous);
			 resultTTarget.handleAttributes(attributes);
			 return resultTTarget;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTarget popTTarget(TTargetStackFrame tTarget) {
			TTarget resultTTargetValue = tTarget.popTTarget();
			this.tTarget = tTarget;
			return resultTTargetValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TTargetStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTarget theTTarget;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTTarget.setType(simmatrixFactory.eINSTANCE.createTargetType(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTTarget = simmatrixFactory.eINSTANCE.createTTarget();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTarget popTTarget() {
				pop();
				TTarget resultTTargetValue = theTTarget;
				theTTarget = null;
				return resultTTargetValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTargetListStackFrame pushTTargetList(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TTargetListStackFrame resultTTargetList = tTargetList == null ? new TTargetListStackFrame() : tTargetList;
			 tTargetList = null;
			 resultTTargetList.pushOnto(previous);
			 resultTTargetList.handleAttributes(attributes);
			 return resultTTargetList;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTargetList popTTargetList(TTargetListStackFrame tTargetList) {
			TTargetList resultTTargetListValue = tTargetList.popTTargetList();
			this.tTargetList = tTargetList;
			return resultTTargetListValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TTargetListStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTargetList theTTargetList;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetStackFrame target;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTTargetList.setId(XMLTypeFactory.eINSTANCE.createString(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("Target".equals(localName) && "".equals(namespace)) {
					return target = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTarget(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == target) {
					theTTargetList.getTarget().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTarget(target));
					target = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTTargetList = simmatrixFactory.eINSTANCE.createTTargetList();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTargetList popTTargetList() {
				pop();
				TTargetList resultTTargetListValue = theTTargetList;
				theTTargetList = null;
				return resultTTargetListValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTargetsStackFrame pushTTargets(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TTargetsStackFrame resultTTargets = tTargets == null ? new TTargetsStackFrame() : tTargets;
			 tTargets = null;
			 resultTTargets.pushOnto(previous);
			 resultTTargets.handleAttributes(attributes);
			 return resultTTargets;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TTargets popTTargets(TTargetsStackFrame tTargets) {
			TTargets resultTTargetsValue = tTargets.popTTargets();
			this.tTargets = tTargets;
			return resultTTargetsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TTargetsStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTargets theTTargets;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TTargetListStackFrame targetList;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("TargetList".equals(localName) && "".equals(namespace)) {
					return targetList = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTTargetList(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == targetList) {
					theTTargets.getTargetList().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTTargetList(targetList));
					targetList = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTTargets = simmatrixFactory.eINSTANCE.createTTargets();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TTargets popTTargets() {
				pop();
				TTargets resultTTargetsValue = theTTargets;
				theTTargets = null;
				return resultTTargetsValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TUsageStackFrame pushTUsage(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TUsageStackFrame resultTUsage = tUsage == null ? new TUsageStackFrame() : tUsage;
			 tUsage = null;
			 resultTUsage.pushOnto(previous);
			 resultTUsage.handleAttributes(attributes);
			 return resultTUsage;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TUsage popTUsage(TUsageStackFrame tUsage) {
			TUsage resultTUsageValue = tUsage.popTUsage();
			this.tUsage = tUsage;
			return resultTUsageValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TUsageStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TUsage theTUsage;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOccupancyTypeStackFrame occupancyType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TScheduleTypeStackFrame scheduleType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("OccupancyType".equals(localName) && "".equals(namespace)) {
					return occupancyType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOccupancyType(this, attributes);
				}
				else if ("ScheduleType".equals(localName) && "".equals(namespace)) {
					return scheduleType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTScheduleType(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == occupancyType) {
					theTUsage.getOccupancyType().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOccupancyType(occupancyType));
					occupancyType = null;
				}
				else if (child == scheduleType) {
					theTUsage.getScheduleType().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTScheduleType(scheduleType));
					scheduleType = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTUsage = simmatrixFactory.eINSTANCE.createTUsage();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TUsage popTUsage() {
				pop();
				TUsage resultTUsageValue = theTUsage;
				theTUsage = null;
				return resultTUsageValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TVariablesStackFrame pushTVariables(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TVariablesStackFrame resultTVariables = tVariables == null ? new TVariablesStackFrame() : tVariables;
			 tVariables = null;
			 resultTVariables.pushOnto(previous);
			 resultTVariables.handleAttributes(attributes);
			 return resultTVariables;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TVariables popTVariables(TVariablesStackFrame tVariables) {
			TVariables resultTVariablesValue = tVariables.popTVariables();
			this.tVariables = tVariables;
			return resultTVariablesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TVariablesStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TVariables theTVariables;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherStackFrame weatherVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TOrientationStackFrame orientationVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TElevationStackFrame elevationVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TUsageStackFrame usageTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TConstructionTypesStackFrame constructionTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypesStackFrame windowTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame doorTypeVariables;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TMaterialTypesStackFrame materialTypeVariables;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("WeatherVariables".equals(localName) && "".equals(namespace)) {
					return weatherVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeather(this, attributes);
				}
				else if ("OrientationVariables".equals(localName) && "".equals(namespace)) {
					return orientationVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTOrientation(this, attributes);
				}
				else if ("ElevationVariables".equals(localName) && "".equals(namespace)) {
					return elevationVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTElevation(this, attributes);
				}
				else if ("UsageTypeVariables".equals(localName) && "".equals(namespace)) {
					return usageTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTUsage(this, attributes);
				}
				else if ("ConstructionTypeVariables".equals(localName) && "".equals(namespace)) {
					return constructionTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTConstructionTypes(this, attributes);
				}
				else if ("WindowTypeVariables".equals(localName) && "".equals(namespace)) {
					return windowTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowTypes(this, attributes);
				}
				else if ("DoorTypeVariables".equals(localName) && "".equals(namespace)) {
					return doorTypeVariables = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushString(this, attributes);
				}
				else if ("MaterialTypeVariables".equals(localName) && "".equals(namespace)) {
					return materialTypeVariables = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTMaterialTypes(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == weatherVariables) {
					theTVariables.setWeatherVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeather(weatherVariables));
					weatherVariables = null;
				}
				else if (child == orientationVariables) {
					theTVariables.setOrientationVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTOrientation(orientationVariables));
					orientationVariables = null;
				}
				else if (child == elevationVariables) {
					theTVariables.setElevationVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTElevation(elevationVariables));
					elevationVariables = null;
				}
				else if (child == usageTypeVariables) {
					theTVariables.setUsageTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTUsage(usageTypeVariables));
					usageTypeVariables = null;
				}
				else if (child == constructionTypeVariables) {
					theTVariables.setConstructionTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTConstructionTypes(constructionTypeVariables));
					constructionTypeVariables = null;
				}
				else if (child == windowTypeVariables) {
					theTVariables.setWindowTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowTypes(windowTypeVariables));
					windowTypeVariables = null;
				}
				else if (child == doorTypeVariables) {
					theTVariables.setDoorTypeVariables(XMLTypeResourceImpl.FrameFactory.INSTANCE.popString(doorTypeVariables));
					doorTypeVariables = null;
				}
				else if (child == materialTypeVariables) {
					theTVariables.setMaterialTypeVariables(simmatrixResourceImpl.FrameFactory.INSTANCE.popTMaterialTypes(materialTypeVariables));
					materialTypeVariables = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTVariables = simmatrixFactory.eINSTANCE.createTVariables();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TVariables popTVariables() {
				pop();
				TVariables resultTVariablesValue = theTVariables;
				theTVariables = null;
				return resultTVariablesValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TVariantStackFrame pushTVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TVariantStackFrame resultTVariant = tVariant == null ? new TVariantStackFrame() : tVariant;
			 tVariant = null;
			 resultTVariant.pushOnto(previous);
			 resultTVariant.handleAttributes(attributes);
			 return resultTVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TVariant popTVariant(TVariantStackFrame tVariant) {
			TVariant resultTVariantValue = tVariant.popTVariant();
			this.tVariant = tVariant;
			return resultTVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TVariant theTVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame aREF;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame vREF;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "AREF");
				if (theValue != null) {
					theTVariant.setAREF(XMLTypeFactory.eINSTANCE.createIDREF(theValue));
				}
				theValue = attributes.getValue("", "VREF");
				if (theValue != null) {
					theTVariant.setVREF(XMLTypeFactory.eINSTANCE.createIDREF(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTVariant = simmatrixFactory.eINSTANCE.createTVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TVariant popTVariant() {
				pop();
				TVariant resultTVariantValue = theTVariant;
				theTVariant = null;
				return resultTVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeatherStackFrame pushTWeather(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWeatherStackFrame resultTWeather = tWeather == null ? new TWeatherStackFrame() : tWeather;
			 tWeather = null;
			 resultTWeather.pushOnto(previous);
			 resultTWeather.handleAttributes(attributes);
			 return resultTWeather;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeather popTWeather(TWeatherStackFrame tWeather) {
			TWeather resultTWeatherValue = tWeather.popTWeather();
			this.tWeather = tWeather;
			return resultTWeatherValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWeatherStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeather theTWeather;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherDataSetVariantStackFrame weatherDataSetVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWeatherDataSeriesVariantStackFrame weatherDataSeriesVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("WeatherDataSetVariant".equals(localName) && "".equals(namespace)) {
					return weatherDataSetVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeatherDataSetVariant(this, attributes);
				}
				else if ("WeatherDataSeriesVariant".equals(localName) && "".equals(namespace)) {
					return weatherDataSeriesVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWeatherDataSeriesVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == weatherDataSetVariant) {
					theTWeather.getWeatherDataSetVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeatherDataSetVariant(weatherDataSetVariant));
					weatherDataSetVariant = null;
				}
				else if (child == weatherDataSeriesVariant) {
					theTWeather.getWeatherDataSeriesVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWeatherDataSeriesVariant(weatherDataSeriesVariant));
					weatherDataSeriesVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWeather = simmatrixFactory.eINSTANCE.createTWeather();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeather popTWeather() {
				pop();
				TWeather resultTWeatherValue = theTWeather;
				theTWeather = null;
				return resultTWeatherValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeatherDataSeriesVariantStackFrame pushTWeatherDataSeriesVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWeatherDataSeriesVariantStackFrame resultTWeatherDataSeriesVariant = tWeatherDataSeriesVariant == null ? new TWeatherDataSeriesVariantStackFrame() : tWeatherDataSeriesVariant;
			 tWeatherDataSeriesVariant = null;
			 resultTWeatherDataSeriesVariant.pushOnto(previous);
			 resultTWeatherDataSeriesVariant.handleAttributes(attributes);
			 return resultTWeatherDataSeriesVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeatherDataSeriesVariant popTWeatherDataSeriesVariant(TWeatherDataSeriesVariantStackFrame tWeatherDataSeriesVariant) {
			TWeatherDataSeriesVariant resultTWeatherDataSeriesVariantValue = tWeatherDataSeriesVariant.popTWeatherDataSeriesVariant();
			this.tWeatherDataSeriesVariant = tWeatherDataSeriesVariant;
			return resultTWeatherDataSeriesVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWeatherDataSeriesVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeatherDataSeriesVariant theTWeatherDataSeriesVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame airTemperature;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame shortWaveDiffuse;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame shortWaveDirect;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTWeatherDataSeriesVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTWeatherDataSeriesVariant.setType(simmatrixFactory.eINSTANCE.createWeatherTypes(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("AirTemperature".equals(localName) && "".equals(namespace)) {
					return airTemperature = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushAnyURI(this, attributes);
				}
				else if ("ShortWaveDiffuse".equals(localName) && "".equals(namespace)) {
					return shortWaveDiffuse = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushAnyURI(this, attributes);
				}
				else if ("ShortWaveDirect".equals(localName) && "".equals(namespace)) {
					return shortWaveDirect = XMLTypeResourceImpl.FrameFactory.INSTANCE.pushAnyURI(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == airTemperature) {
					theTWeatherDataSeriesVariant.setAirTemperature(XMLTypeResourceImpl.FrameFactory.INSTANCE.popAnyURI(airTemperature));
					airTemperature = null;
				}
				else if (child == shortWaveDiffuse) {
					theTWeatherDataSeriesVariant.setShortWaveDiffuse(XMLTypeResourceImpl.FrameFactory.INSTANCE.popAnyURI(shortWaveDiffuse));
					shortWaveDiffuse = null;
				}
				else if (child == shortWaveDirect) {
					theTWeatherDataSeriesVariant.setShortWaveDirect(XMLTypeResourceImpl.FrameFactory.INSTANCE.popAnyURI(shortWaveDirect));
					shortWaveDirect = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWeatherDataSeriesVariant = simmatrixFactory.eINSTANCE.createTWeatherDataSeriesVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeatherDataSeriesVariant popTWeatherDataSeriesVariant() {
				pop();
				TWeatherDataSeriesVariant resultTWeatherDataSeriesVariantValue = theTWeatherDataSeriesVariant;
				theTWeatherDataSeriesVariant = null;
				return resultTWeatherDataSeriesVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeatherDataSetVariantStackFrame pushTWeatherDataSetVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWeatherDataSetVariantStackFrame resultTWeatherDataSetVariant = tWeatherDataSetVariant == null ? new TWeatherDataSetVariantStackFrame() : tWeatherDataSetVariant;
			 tWeatherDataSetVariant = null;
			 resultTWeatherDataSetVariant.pushOnto(previous);
			 resultTWeatherDataSetVariant.handleAttributes(attributes);
			 return resultTWeatherDataSetVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWeatherDataSetVariant popTWeatherDataSetVariant(TWeatherDataSetVariantStackFrame tWeatherDataSetVariant) {
			TWeatherDataSetVariant resultTWeatherDataSetVariantValue = tWeatherDataSetVariant.popTWeatherDataSetVariant();
			this.tWeatherDataSetVariant = tWeatherDataSetVariant;
			return resultTWeatherDataSetVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWeatherDataSetVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeatherDataSetVariant theTWeatherDataSetVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame type;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTWeatherDataSetVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
				theValue = attributes.getValue("", "type");
				if (theValue != null) {
					theTWeatherDataSetVariant.setType(simmatrixFactory.eINSTANCE.createWeatherTypes(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				return super.startElement(namespace, localName, qName, attributes);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				super.endElement(child);
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWeatherDataSetVariant = simmatrixFactory.eINSTANCE.createTWeatherDataSetVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWeatherDataSetVariant popTWeatherDataSetVariant() {
				pop();
				TWeatherDataSetVariant resultTWeatherDataSetVariantValue = theTWeatherDataSetVariant;
				theTWeatherDataSetVariant = null;
				return resultTWeatherDataSetVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowTypeStackFrame pushTWindowType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWindowTypeStackFrame resultTWindowType = tWindowType == null ? new TWindowTypeStackFrame() : tWindowType;
			 tWindowType = null;
			 resultTWindowType.pushOnto(previous);
			 resultTWindowType.handleAttributes(attributes);
			 return resultTWindowType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowType popTWindowType(TWindowTypeStackFrame tWindowType) {
			TWindowType resultTWindowTypeValue = tWindowType.popTWindowType();
			this.tWindowType = tWindowType;
			return resultTWindowTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWindowTypeStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowType theTWindowType;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypeVariantStackFrame windowTypeVariant;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame name;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "name");
				if (theValue != null) {
					theTWindowType.setName(XMLTypeFactory.eINSTANCE.createAnyURI(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("WindowTypeVariant".equals(localName) && "".equals(namespace)) {
					return windowTypeVariant = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowTypeVariant(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == windowTypeVariant) {
					theTWindowType.getWindowTypeVariant().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowTypeVariant(windowTypeVariant));
					windowTypeVariant = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWindowType = simmatrixFactory.eINSTANCE.createTWindowType();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowType popTWindowType() {
				pop();
				TWindowType resultTWindowTypeValue = theTWindowType;
				theTWindowType = null;
				return resultTWindowTypeValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowTypesStackFrame pushTWindowTypes(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWindowTypesStackFrame resultTWindowTypes = tWindowTypes == null ? new TWindowTypesStackFrame() : tWindowTypes;
			 tWindowTypes = null;
			 resultTWindowTypes.pushOnto(previous);
			 resultTWindowTypes.handleAttributes(attributes);
			 return resultTWindowTypes;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowTypes popTWindowTypes(TWindowTypesStackFrame tWindowTypes) {
			TWindowTypes resultTWindowTypesValue = tWindowTypes.popTWindowTypes();
			this.tWindowTypes = tWindowTypes;
			return resultTWindowTypesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWindowTypesStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowTypes theTWindowTypes;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TWindowTypeStackFrame windowType;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				// There are attributes to handle.
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("WindowType".equals(localName) && "".equals(namespace)) {
					return windowType = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTWindowType(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == windowType) {
					theTWindowTypes.getWindowType().add(simmatrixResourceImpl.FrameFactory.INSTANCE.popTWindowType(windowType));
					windowType = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWindowTypes = simmatrixFactory.eINSTANCE.createTWindowTypes();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowTypes popTWindowTypes() {
				pop();
				TWindowTypes resultTWindowTypesValue = theTWindowTypes;
				theTWindowTypes = null;
				return resultTWindowTypesValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowTypeVariantStackFrame pushTWindowTypeVariant(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 TWindowTypeVariantStackFrame resultTWindowTypeVariant = tWindowTypeVariant == null ? new TWindowTypeVariantStackFrame() : tWindowTypeVariant;
			 tWindowTypeVariant = null;
			 resultTWindowTypeVariant.pushOnto(previous);
			 resultTWindowTypeVariant.handleAttributes(attributes);
			 return resultTWindowTypeVariant;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TWindowTypeVariant popTWindowTypeVariant(TWindowTypeVariantStackFrame tWindowTypeVariant) {
			TWindowTypeVariant resultTWindowTypeVariantValue = tWindowTypeVariant.popTWindowTypeVariant();
			this.tWindowTypeVariant = tWindowTypeVariant;
			return resultTWindowTypeVariantValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static class TWindowTypeVariantStackFrame extends XMLTypeResourceImpl.StackFrame {
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowTypeVariant theTWindowTypeVariant;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame glassFraction;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame frameFraction;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame thermalTransmittance;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame solarHeatGainCoefficient;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected simmatrixResourceImpl.FrameFactory.TFloatWithUnitsStackFrame shadingFactor;

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected XMLTypeResourceImpl.DataFrame id;
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void handleAttributes(Attributes attributes) {
				String theValue = attributes.getValue("", "id");
				if (theValue != null) {
					theTWindowTypeVariant.setId(XMLTypeFactory.eINSTANCE.createID(theValue));
				}
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
				if ("GlassFraction".equals(localName) && "".equals(namespace)) {
					return glassFraction = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("FrameFraction".equals(localName) && "".equals(namespace)) {
					return frameFraction = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("ThermalTransmittance".equals(localName) && "".equals(namespace)) {
					return thermalTransmittance = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("SolarHeatGainCoefficient".equals(localName) && "".equals(namespace)) {
					return solarHeatGainCoefficient = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else if ("ShadingFactor".equals(localName) && "".equals(namespace)) {
					return shadingFactor = simmatrixResourceImpl.FrameFactory.INSTANCE.pushTFloatWithUnits(this, attributes);
				}
				else {
					return super.startElement(namespace, localName, qName, attributes);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
				if (child == glassFraction) {
					theTWindowTypeVariant.setGlassFraction(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(glassFraction));
					glassFraction = null;
				}
				else if (child == frameFraction) {
					theTWindowTypeVariant.setFrameFraction(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(frameFraction));
					frameFraction = null;
				}
				else if (child == thermalTransmittance) {
					theTWindowTypeVariant.setThermalTransmittance(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(thermalTransmittance));
					thermalTransmittance = null;
				}
				else if (child == solarHeatGainCoefficient) {
					theTWindowTypeVariant.setSolarHeatGainCoefficient(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(solarHeatGainCoefficient));
					solarHeatGainCoefficient = null;
				}
				else if (child == shadingFactor) {
					theTWindowTypeVariant.setShadingFactor(simmatrixResourceImpl.FrameFactory.INSTANCE.popTFloatWithUnits(shadingFactor));
					shadingFactor = null;
				}
				else {
					super.endElement(child);
				}
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public void create() {
				theTWindowTypeVariant = simmatrixFactory.eINSTANCE.createTWindowTypeVariant();
			}
		
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected TWindowTypeVariant popTWindowTypeVariant() {
				pop();
				TWindowTypeVariant resultTWindowTypeVariantValue = theTWindowTypeVariant;
				theTWindowTypeVariant = null;
				return resultTWindowTypeVariantValue;
			}
		
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushDistanceUnit(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultDistanceUnit = distanceUnit == null ? new XMLTypeResourceImpl.DataFrame() : distanceUnit;
			 distanceUnit = null;
			 resultDistanceUnit.pushOnto(previous);
			 resultDistanceUnit.handleAttributes(attributes);
			 return resultDistanceUnit;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public DistanceUnit popDistanceUnit(XMLTypeResourceImpl.DataFrame distanceUnit) {
			DistanceUnit resultDistanceUnitValue = simmatrixFactory.eINSTANCE.createDistanceUnit(distanceUnit.popValue());
			this.distanceUnit = distanceUnit;
			return resultDistanceUnitValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushFileFormat(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultFileFormat = fileFormat == null ? new XMLTypeResourceImpl.DataFrame() : fileFormat;
			 fileFormat = null;
			 resultFileFormat.pushOnto(previous);
			 resultFileFormat.handleAttributes(attributes);
			 return resultFileFormat;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public FileFormat popFileFormat(XMLTypeResourceImpl.DataFrame fileFormat) {
			FileFormat resultFileFormatValue = simmatrixFactory.eINSTANCE.createFileFormat(fileFormat.popValue());
			this.fileFormat = fileFormat;
			return resultFileFormatValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushLoads(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultLoads = loads == null ? new XMLTypeResourceImpl.DataFrame() : loads;
			 loads = null;
			 resultLoads.pushOnto(previous);
			 resultLoads.handleAttributes(attributes);
			 return resultLoads;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Loads popLoads(XMLTypeResourceImpl.DataFrame loads) {
			Loads resultLoadsValue = simmatrixFactory.eINSTANCE.createLoads(loads.popValue());
			this.loads = loads;
			return resultLoadsValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushMaterialUnit(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultMaterialUnit = materialUnit == null ? new XMLTypeResourceImpl.DataFrame() : materialUnit;
			 materialUnit = null;
			 resultMaterialUnit.pushOnto(previous);
			 resultMaterialUnit.handleAttributes(attributes);
			 return resultMaterialUnit;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public MaterialUnit popMaterialUnit(XMLTypeResourceImpl.DataFrame materialUnit) {
			MaterialUnit resultMaterialUnitValue = simmatrixFactory.eINSTANCE.createMaterialUnit(materialUnit.popValue());
			this.materialUnit = materialUnit;
			return resultMaterialUnitValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushOrientationSide(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultOrientationSide = orientationSide == null ? new XMLTypeResourceImpl.DataFrame() : orientationSide;
			 orientationSide = null;
			 resultOrientationSide.pushOnto(previous);
			 resultOrientationSide.handleAttributes(attributes);
			 return resultOrientationSide;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public OrientationSide popOrientationSide(XMLTypeResourceImpl.DataFrame orientationSide) {
			OrientationSide resultOrientationSideValue = simmatrixFactory.eINSTANCE.createOrientationSide(orientationSide.popValue());
			this.orientationSide = orientationSide;
			return resultOrientationSideValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushOrientationUnit(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultOrientationUnit = orientationUnit == null ? new XMLTypeResourceImpl.DataFrame() : orientationUnit;
			 orientationUnit = null;
			 resultOrientationUnit.pushOnto(previous);
			 resultOrientationUnit.handleAttributes(attributes);
			 return resultOrientationUnit;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public OrientationUnit popOrientationUnit(XMLTypeResourceImpl.DataFrame orientationUnit) {
			OrientationUnit resultOrientationUnitValue = simmatrixFactory.eINSTANCE.createOrientationUnit(orientationUnit.popValue());
			this.orientationUnit = orientationUnit;
			return resultOrientationUnitValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushSetPoint(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultSetPoint = setPoint == null ? new XMLTypeResourceImpl.DataFrame() : setPoint;
			 setPoint = null;
			 resultSetPoint.pushOnto(previous);
			 resultSetPoint.handleAttributes(attributes);
			 return resultSetPoint;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public SetPoint popSetPoint(XMLTypeResourceImpl.DataFrame setPoint) {
			SetPoint resultSetPointValue = simmatrixFactory.eINSTANCE.createSetPoint(setPoint.popValue());
			this.setPoint = setPoint;
			return resultSetPointValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushShading(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultShading = shading == null ? new XMLTypeResourceImpl.DataFrame() : shading;
			 shading = null;
			 resultShading.pushOnto(previous);
			 resultShading.handleAttributes(attributes);
			 return resultShading;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Shading popShading(XMLTypeResourceImpl.DataFrame shading) {
			Shading resultShadingValue = simmatrixFactory.eINSTANCE.createShading(shading.popValue());
			this.shading = shading;
			return resultShadingValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTargetType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTargetType = targetType == null ? new XMLTypeResourceImpl.DataFrame() : targetType;
			 targetType = null;
			 resultTargetType.pushOnto(previous);
			 resultTargetType.handleAttributes(attributes);
			 return resultTargetType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TargetType popTargetType(XMLTypeResourceImpl.DataFrame targetType) {
			TargetType resultTargetTypeValue = simmatrixFactory.eINSTANCE.createTargetType(targetType.popValue());
			this.targetType = targetType;
			return resultTargetTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTemperature(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTemperature = temperature == null ? new XMLTypeResourceImpl.DataFrame() : temperature;
			 temperature = null;
			 resultTemperature.pushOnto(previous);
			 resultTemperature.handleAttributes(attributes);
			 return resultTemperature;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Temperature popTemperature(XMLTypeResourceImpl.DataFrame temperature) {
			Temperature resultTemperatureValue = simmatrixFactory.eINSTANCE.createTemperature(temperature.popValue());
			this.temperature = temperature;
			return resultTemperatureValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTimePeriod(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTimePeriod = timePeriod == null ? new XMLTypeResourceImpl.DataFrame() : timePeriod;
			 timePeriod = null;
			 resultTimePeriod.pushOnto(previous);
			 resultTimePeriod.handleAttributes(attributes);
			 return resultTimePeriod;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TimePeriod popTimePeriod(XMLTypeResourceImpl.DataFrame timePeriod) {
			TimePeriod resultTimePeriodValue = simmatrixFactory.eINSTANCE.createTimePeriod(timePeriod.popValue());
			this.timePeriod = timePeriod;
			return resultTimePeriodValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushWeatherTypes(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultWeatherTypes = weatherTypes == null ? new XMLTypeResourceImpl.DataFrame() : weatherTypes;
			 weatherTypes = null;
			 resultWeatherTypes.pushOnto(previous);
			 resultWeatherTypes.handleAttributes(attributes);
			 return resultWeatherTypes;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public WeatherTypes popWeatherTypes(XMLTypeResourceImpl.DataFrame weatherTypes) {
			WeatherTypes resultWeatherTypesValue = simmatrixFactory.eINSTANCE.createWeatherTypes(weatherTypes.popValue());
			this.weatherTypes = weatherTypes;
			return resultWeatherTypesValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushDistanceUnitObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultDistanceUnitObject = distanceUnitObject == null ? new XMLTypeResourceImpl.DataFrame() : distanceUnitObject;
			 distanceUnitObject = null;
			 resultDistanceUnitObject.pushOnto(previous);
			 resultDistanceUnitObject.handleAttributes(attributes);
			 return resultDistanceUnitObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public DistanceUnit popDistanceUnitObject(XMLTypeResourceImpl.DataFrame distanceUnitObject) {
			DistanceUnit resultDistanceUnitObjectValue = simmatrixFactory.eINSTANCE.createDistanceUnitObject(distanceUnitObject.popValue());
			this.distanceUnitObject = distanceUnitObject;
			return resultDistanceUnitObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushFileFormatObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultFileFormatObject = fileFormatObject == null ? new XMLTypeResourceImpl.DataFrame() : fileFormatObject;
			 fileFormatObject = null;
			 resultFileFormatObject.pushOnto(previous);
			 resultFileFormatObject.handleAttributes(attributes);
			 return resultFileFormatObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public FileFormat popFileFormatObject(XMLTypeResourceImpl.DataFrame fileFormatObject) {
			FileFormat resultFileFormatObjectValue = simmatrixFactory.eINSTANCE.createFileFormatObject(fileFormatObject.popValue());
			this.fileFormatObject = fileFormatObject;
			return resultFileFormatObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushLoadsObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultLoadsObject = loadsObject == null ? new XMLTypeResourceImpl.DataFrame() : loadsObject;
			 loadsObject = null;
			 resultLoadsObject.pushOnto(previous);
			 resultLoadsObject.handleAttributes(attributes);
			 return resultLoadsObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Loads popLoadsObject(XMLTypeResourceImpl.DataFrame loadsObject) {
			Loads resultLoadsObjectValue = simmatrixFactory.eINSTANCE.createLoadsObject(loadsObject.popValue());
			this.loadsObject = loadsObject;
			return resultLoadsObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushMaterialUnitObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultMaterialUnitObject = materialUnitObject == null ? new XMLTypeResourceImpl.DataFrame() : materialUnitObject;
			 materialUnitObject = null;
			 resultMaterialUnitObject.pushOnto(previous);
			 resultMaterialUnitObject.handleAttributes(attributes);
			 return resultMaterialUnitObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public MaterialUnit popMaterialUnitObject(XMLTypeResourceImpl.DataFrame materialUnitObject) {
			MaterialUnit resultMaterialUnitObjectValue = simmatrixFactory.eINSTANCE.createMaterialUnitObject(materialUnitObject.popValue());
			this.materialUnitObject = materialUnitObject;
			return resultMaterialUnitObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushOrientationSideObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultOrientationSideObject = orientationSideObject == null ? new XMLTypeResourceImpl.DataFrame() : orientationSideObject;
			 orientationSideObject = null;
			 resultOrientationSideObject.pushOnto(previous);
			 resultOrientationSideObject.handleAttributes(attributes);
			 return resultOrientationSideObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public OrientationSide popOrientationSideObject(XMLTypeResourceImpl.DataFrame orientationSideObject) {
			OrientationSide resultOrientationSideObjectValue = simmatrixFactory.eINSTANCE.createOrientationSideObject(orientationSideObject.popValue());
			this.orientationSideObject = orientationSideObject;
			return resultOrientationSideObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushOrientationUnitObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultOrientationUnitObject = orientationUnitObject == null ? new XMLTypeResourceImpl.DataFrame() : orientationUnitObject;
			 orientationUnitObject = null;
			 resultOrientationUnitObject.pushOnto(previous);
			 resultOrientationUnitObject.handleAttributes(attributes);
			 return resultOrientationUnitObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public OrientationUnit popOrientationUnitObject(XMLTypeResourceImpl.DataFrame orientationUnitObject) {
			OrientationUnit resultOrientationUnitObjectValue = simmatrixFactory.eINSTANCE.createOrientationUnitObject(orientationUnitObject.popValue());
			this.orientationUnitObject = orientationUnitObject;
			return resultOrientationUnitObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushSetPointObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultSetPointObject = setPointObject == null ? new XMLTypeResourceImpl.DataFrame() : setPointObject;
			 setPointObject = null;
			 resultSetPointObject.pushOnto(previous);
			 resultSetPointObject.handleAttributes(attributes);
			 return resultSetPointObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public SetPoint popSetPointObject(XMLTypeResourceImpl.DataFrame setPointObject) {
			SetPoint resultSetPointObjectValue = simmatrixFactory.eINSTANCE.createSetPointObject(setPointObject.popValue());
			this.setPointObject = setPointObject;
			return resultSetPointObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushShadingObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultShadingObject = shadingObject == null ? new XMLTypeResourceImpl.DataFrame() : shadingObject;
			 shadingObject = null;
			 resultShadingObject.pushOnto(previous);
			 resultShadingObject.handleAttributes(attributes);
			 return resultShadingObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Shading popShadingObject(XMLTypeResourceImpl.DataFrame shadingObject) {
			Shading resultShadingObjectValue = simmatrixFactory.eINSTANCE.createShadingObject(shadingObject.popValue());
			this.shadingObject = shadingObject;
			return resultShadingObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushSimatType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultSimatType = simatType == null ? new XMLTypeResourceImpl.DataFrame() : simatType;
			 simatType = null;
			 resultSimatType.pushOnto(previous);
			 resultSimatType.handleAttributes(attributes);
			 return resultSimatType;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public String popSimatType(XMLTypeResourceImpl.DataFrame simatType) {
			String resultSimatTypeValue = simmatrixFactory.eINSTANCE.createSimatType(simatType.popValue());
			this.simatType = simatType;
			return resultSimatTypeValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTargetTypeObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTargetTypeObject = targetTypeObject == null ? new XMLTypeResourceImpl.DataFrame() : targetTypeObject;
			 targetTypeObject = null;
			 resultTargetTypeObject.pushOnto(previous);
			 resultTargetTypeObject.handleAttributes(attributes);
			 return resultTargetTypeObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TargetType popTargetTypeObject(XMLTypeResourceImpl.DataFrame targetTypeObject) {
			TargetType resultTargetTypeObjectValue = simmatrixFactory.eINSTANCE.createTargetTypeObject(targetTypeObject.popValue());
			this.targetTypeObject = targetTypeObject;
			return resultTargetTypeObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTemperatureObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTemperatureObject = temperatureObject == null ? new XMLTypeResourceImpl.DataFrame() : temperatureObject;
			 temperatureObject = null;
			 resultTemperatureObject.pushOnto(previous);
			 resultTemperatureObject.handleAttributes(attributes);
			 return resultTemperatureObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Temperature popTemperatureObject(XMLTypeResourceImpl.DataFrame temperatureObject) {
			Temperature resultTemperatureObjectValue = simmatrixFactory.eINSTANCE.createTemperatureObject(temperatureObject.popValue());
			this.temperatureObject = temperatureObject;
			return resultTemperatureObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTimePeriodObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTimePeriodObject = timePeriodObject == null ? new XMLTypeResourceImpl.DataFrame() : timePeriodObject;
			 timePeriodObject = null;
			 resultTimePeriodObject.pushOnto(previous);
			 resultTimePeriodObject.handleAttributes(attributes);
			 return resultTimePeriodObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public TimePeriod popTimePeriodObject(XMLTypeResourceImpl.DataFrame timePeriodObject) {
			TimePeriod resultTimePeriodObjectValue = simmatrixFactory.eINSTANCE.createTimePeriodObject(timePeriodObject.popValue());
			this.timePeriodObject = timePeriodObject;
			return resultTimePeriodObjectValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushTStringList(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultTStringList = tStringList == null ? new XMLTypeResourceImpl.DataFrame() : tStringList;
			 tStringList = null;
			 resultTStringList.pushOnto(previous);
			 resultTStringList.handleAttributes(attributes);
			 return resultTStringList;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public List<String> popTStringList(XMLTypeResourceImpl.DataFrame tStringList) {
			List<String> resultTStringListValue = simmatrixFactory.eINSTANCE.createTStringList(tStringList.popValue());
			this.tStringList = tStringList;
			return resultTStringListValue;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public XMLTypeResourceImpl.DataFrame pushWeatherTypesObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
			 XMLTypeResourceImpl.DataFrame resultWeatherTypesObject = weatherTypesObject == null ? new XMLTypeResourceImpl.DataFrame() : weatherTypesObject;
			 weatherTypesObject = null;
			 resultWeatherTypesObject.pushOnto(previous);
			 resultWeatherTypesObject.handleAttributes(attributes);
			 return resultWeatherTypesObject;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public WeatherTypes popWeatherTypesObject(XMLTypeResourceImpl.DataFrame weatherTypesObject) {
			WeatherTypes resultWeatherTypesObjectValue = simmatrixFactory.eINSTANCE.createWeatherTypesObject(weatherTypesObject.popValue());
			this.weatherTypesObject = weatherTypesObject;
			return resultWeatherTypesObjectValue;
		}

	}

} //simmatrixResourceImpl
