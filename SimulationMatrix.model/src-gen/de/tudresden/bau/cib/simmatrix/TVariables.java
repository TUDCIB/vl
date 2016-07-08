/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TVariables</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getWeatherVariables <em>Weather Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getOrientationVariables <em>Orientation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getElevationVariables <em>Elevation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getUsageTypeVariables <em>Usage Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getConstructionTypeVariables <em>Construction Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getWindowTypeVariables <em>Window Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getDoorTypeVariables <em>Door Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariables#getMaterialTypeVariables <em>Material Type Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables()
 * @model extendedMetaData="name='T_Variables' kind='elementOnly'"
 * @generated
 */
public interface TVariables extends EObject {
	/**
	 * Returns the value of the '<em><b>Weather Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weather Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weather Variables</em>' containment reference.
	 * @see #setWeatherVariables(TWeather)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_WeatherVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WeatherVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TWeather getWeatherVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getWeatherVariables <em>Weather Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Variables</em>' containment reference.
	 * @see #getWeatherVariables()
	 * @generated
	 */
	void setWeatherVariables(TWeather value);

	/**
	 * Returns the value of the '<em><b>Orientation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation Variables</em>' containment reference.
	 * @see #setOrientationVariables(TOrientation)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_OrientationVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='OrientationVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TOrientation getOrientationVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getOrientationVariables <em>Orientation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation Variables</em>' containment reference.
	 * @see #getOrientationVariables()
	 * @generated
	 */
	void setOrientationVariables(TOrientation value);

	/**
	 * Returns the value of the '<em><b>Elevation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elevation Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation Variables</em>' containment reference.
	 * @see #setElevationVariables(TElevation)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_ElevationVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ElevationVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TElevation getElevationVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getElevationVariables <em>Elevation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation Variables</em>' containment reference.
	 * @see #getElevationVariables()
	 * @generated
	 */
	void setElevationVariables(TElevation value);

	/**
	 * Returns the value of the '<em><b>Usage Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage Type Variables</em>' containment reference.
	 * @see #setUsageTypeVariables(TUsage)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_UsageTypeVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='UsageTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TUsage getUsageTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getUsageTypeVariables <em>Usage Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage Type Variables</em>' containment reference.
	 * @see #getUsageTypeVariables()
	 * @generated
	 */
	void setUsageTypeVariables(TUsage value);

	/**
	 * Returns the value of the '<em><b>Construction Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type Variables</em>' containment reference.
	 * @see #setConstructionTypeVariables(TConstructionTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_ConstructionTypeVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ConstructionTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TConstructionTypes getConstructionTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getConstructionTypeVariables <em>Construction Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Construction Type Variables</em>' containment reference.
	 * @see #getConstructionTypeVariables()
	 * @generated
	 */
	void setConstructionTypeVariables(TConstructionTypes value);

	/**
	 * Returns the value of the '<em><b>Window Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Window Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Window Type Variables</em>' containment reference.
	 * @see #setWindowTypeVariables(TWindowTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_WindowTypeVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WindowTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TWindowTypes getWindowTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getWindowTypeVariables <em>Window Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Window Type Variables</em>' containment reference.
	 * @see #getWindowTypeVariables()
	 * @generated
	 */
	void setWindowTypeVariables(TWindowTypes value);

	/**
	 * Returns the value of the '<em><b>Door Type Variables</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Door Type Variables</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Door Type Variables</em>' attribute.
	 * @see #setDoorTypeVariables(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_DoorTypeVariables()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='DoorTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDoorTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getDoorTypeVariables <em>Door Type Variables</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Door Type Variables</em>' attribute.
	 * @see #getDoorTypeVariables()
	 * @generated
	 */
	void setDoorTypeVariables(String value);

	/**
	 * Returns the value of the '<em><b>Material Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type Variables</em>' containment reference.
	 * @see #setMaterialTypeVariables(TMaterialTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariables_MaterialTypeVariables()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MaterialTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TMaterialTypes getMaterialTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariables#getMaterialTypeVariables <em>Material Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material Type Variables</em>' containment reference.
	 * @see #getMaterialTypeVariables()
	 * @generated
	 */
	void setMaterialTypeVariables(TMaterialTypes value);

} // TVariables
