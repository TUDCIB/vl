/**
 */
package de.tudresden.bau.cib.simmatrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Target Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTargetType()
 * @model extendedMetaData="name='targetType'"
 * @generated
 */
public enum TargetType implements Enumerator {
	/**
	 * The '<em><b>Zone</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZONE_VALUE
	 * @generated
	 * @ordered
	 */
	ZONE(0, "Zone", "Zone"),

	/**
	 * The '<em><b>Space</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPACE_VALUE
	 * @generated
	 * @ordered
	 */
	SPACE(1, "Space", "Space"),

	/**
	 * The '<em><b>Storey</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STOREY_VALUE
	 * @generated
	 * @ordered
	 */
	STOREY(2, "Storey", "Storey"),

	/**
	 * The '<em><b>Building</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUILDING_VALUE
	 * @generated
	 * @ordered
	 */
	BUILDING(3, "Building", "Building");

	/**
	 * The '<em><b>Zone</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Zone</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZONE
	 * @model name="Zone"
	 * @generated
	 * @ordered
	 */
	public static final int ZONE_VALUE = 0;

	/**
	 * The '<em><b>Space</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Space</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SPACE
	 * @model name="Space"
	 * @generated
	 * @ordered
	 */
	public static final int SPACE_VALUE = 1;

	/**
	 * The '<em><b>Storey</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Storey</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOREY
	 * @model name="Storey"
	 * @generated
	 * @ordered
	 */
	public static final int STOREY_VALUE = 2;

	/**
	 * The '<em><b>Building</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Building</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BUILDING
	 * @model name="Building"
	 * @generated
	 * @ordered
	 */
	public static final int BUILDING_VALUE = 3;

	/**
	 * An array of all the '<em><b>Target Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TargetType[] VALUES_ARRAY =
		new TargetType[] {
			ZONE,
			SPACE,
			STOREY,
			BUILDING,
		};

	/**
	 * A public read-only list of all the '<em><b>Target Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TargetType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Target Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TargetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TargetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Target Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TargetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TargetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Target Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TargetType get(int value) {
		switch (value) {
			case ZONE_VALUE: return ZONE;
			case SPACE_VALUE: return SPACE;
			case STOREY_VALUE: return STOREY;
			case BUILDING_VALUE: return BUILDING;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TargetType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //TargetType
