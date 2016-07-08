/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TWeather Data Series Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getAirTemperature <em>Air Temperature</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDiffuse <em>Short Wave Diffuse</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDirect <em>Short Wave Direct</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant()
 * @model extendedMetaData="name='T_WeatherDataSeriesVariant' kind='elementOnly'"
 * @generated
 */
public interface TWeatherDataSeriesVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Air Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Air Temperature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Air Temperature</em>' attribute.
	 * @see #setAirTemperature(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant_AirTemperature()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='element' name='AirTemperature' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAirTemperature();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getAirTemperature <em>Air Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Air Temperature</em>' attribute.
	 * @see #getAirTemperature()
	 * @generated
	 */
	void setAirTemperature(String value);

	/**
	 * Returns the value of the '<em><b>Short Wave Diffuse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Wave Diffuse</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Wave Diffuse</em>' attribute.
	 * @see #setShortWaveDiffuse(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant_ShortWaveDiffuse()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='element' name='ShortWaveDiffuse' namespace='##targetNamespace'"
	 * @generated
	 */
	String getShortWaveDiffuse();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDiffuse <em>Short Wave Diffuse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Wave Diffuse</em>' attribute.
	 * @see #getShortWaveDiffuse()
	 * @generated
	 */
	void setShortWaveDiffuse(String value);

	/**
	 * Returns the value of the '<em><b>Short Wave Direct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Wave Direct</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Wave Direct</em>' attribute.
	 * @see #setShortWaveDirect(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant_ShortWaveDirect()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='element' name='ShortWaveDirect' namespace='##targetNamespace'"
	 * @generated
	 */
	String getShortWaveDirect();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDirect <em>Short Wave Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Wave Direct</em>' attribute.
	 * @see #getShortWaveDirect()
	 * @generated
	 */
	void setShortWaveDirect(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"CCD"</code>.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.WeatherTypes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(WeatherTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWeatherDataSeriesVariant_Type()
	 * @model default="CCD" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	WeatherTypes getType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(WeatherTypes value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(WeatherTypes)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(WeatherTypes)
	 * @generated
	 */
	boolean isSetType();

} // TWeatherDataSeriesVariant
