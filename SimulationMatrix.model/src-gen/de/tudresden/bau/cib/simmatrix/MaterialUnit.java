/**
 */
package de.tudresden.bau.cib.simmatrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Material Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getMaterialUnit()
 * @model extendedMetaData="name='materialUnit'"
 * @generated
 */
public enum MaterialUnit implements Enumerator {
	/**
	 * The '<em><b>J</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #J_VALUE
	 * @generated
	 * @ordered
	 */
	J(0, "J", "J"),

	/**
	 * The '<em><b>JKg K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JKG_K_VALUE
	 * @generated
	 * @ordered
	 */
	JKG_K(1, "JKgK", "J/kgK"),

	/**
	 * The '<em><b>WM2K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WM2K_VALUE
	 * @generated
	 * @ordered
	 */
	WM2K(2, "WM2K", "W/m2K"),

	/**
	 * The '<em><b>WMK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WMK_VALUE
	 * @generated
	 * @ordered
	 */
	WMK(3, "WMK", "W/mK"),

	/**
	 * The '<em><b>Kg M3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KG_M3_VALUE
	 * @generated
	 * @ordered
	 */
	KG_M3(4, "kgM3", "kg/m3"),

	/**
	 * The '<em><b></b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #__VALUE
	 * @generated
	 * @ordered
	 */
	_(5, "_", "---"),

	/**
	 * The '<em><b>M3M3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #M3_M3_VALUE
	 * @generated
	 * @ordered
	 */
	M3_M3(6, "m3M3", "m3/m3"),

	/**
	 * The '<em><b>Kg M2s05</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KG_M2S05_VALUE
	 * @generated
	 * @ordered
	 */
	KG_M2S05(7, "kgM2s05", "kg/m2s05"),

	/**
	 * The '<em><b>1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_1_VALUE
	 * @generated
	 * @ordered
	 */
	_1(8, "_1", "%"),

	/**
	 * The '<em><b>Frac</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRAC_VALUE
	 * @generated
	 * @ordered
	 */
	FRAC(9, "Frac", "Frac");

	/**
	 * The '<em><b>J</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>J</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #J
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int J_VALUE = 0;

	/**
	 * The '<em><b>JKg K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JKg K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JKG_K
	 * @model name="JKgK" literal="J/kgK"
	 * @generated
	 * @ordered
	 */
	public static final int JKG_K_VALUE = 1;

	/**
	 * The '<em><b>WM2K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WM2K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WM2K
	 * @model literal="W/m2K"
	 * @generated
	 * @ordered
	 */
	public static final int WM2K_VALUE = 2;

	/**
	 * The '<em><b>WMK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WMK</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WMK
	 * @model literal="W/mK"
	 * @generated
	 * @ordered
	 */
	public static final int WMK_VALUE = 3;

	/**
	 * The '<em><b>Kg M3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Kg M3</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KG_M3
	 * @model name="kgM3" literal="kg/m3"
	 * @generated
	 * @ordered
	 */
	public static final int KG_M3_VALUE = 4;

	/**
	 * The '<em><b></b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b></b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_
	 * @model literal="---"
	 * @generated
	 * @ordered
	 */
	public static final int __VALUE = 5;

	/**
	 * The '<em><b>M3M3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>M3M3</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #M3_M3
	 * @model name="m3M3" literal="m3/m3"
	 * @generated
	 * @ordered
	 */
	public static final int M3_M3_VALUE = 6;

	/**
	 * The '<em><b>Kg M2s05</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Kg M2s05</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KG_M2S05
	 * @model name="kgM2s05" literal="kg/m2s05"
	 * @generated
	 * @ordered
	 */
	public static final int KG_M2S05_VALUE = 7;

	/**
	 * The '<em><b>1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_1
	 * @model literal="%"
	 * @generated
	 * @ordered
	 */
	public static final int _1_VALUE = 8;

	/**
	 * The '<em><b>Frac</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Frac</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FRAC
	 * @model name="Frac"
	 * @generated
	 * @ordered
	 */
	public static final int FRAC_VALUE = 9;

	/**
	 * An array of all the '<em><b>Material Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MaterialUnit[] VALUES_ARRAY =
		new MaterialUnit[] {
			J,
			JKG_K,
			WM2K,
			WMK,
			KG_M3,
			_,
			M3_M3,
			KG_M2S05,
			_1,
			FRAC,
		};

	/**
	 * A public read-only list of all the '<em><b>Material Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MaterialUnit> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Material Unit</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaterialUnit get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MaterialUnit result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Material Unit</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaterialUnit getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MaterialUnit result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}
	
	/**
	 * Returns the '<em><b>Material Unit</b></em>' literal with the specified literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaterialUnit getByLiteral(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MaterialUnit result = VALUES_ARRAY[i];
			if (result.getLiteral().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Material Unit</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MaterialUnit get(int value) {
		switch (value) {
			case J_VALUE: return J;
			case JKG_K_VALUE: return JKG_K;
			case WM2K_VALUE: return WM2K;
			case WMK_VALUE: return WMK;
			case KG_M3_VALUE: return KG_M3;
			case __VALUE: return _;
			case M3_M3_VALUE: return M3_M3;
			case KG_M2S05_VALUE: return KG_M2S05;
			case _1_VALUE: return _1;
			case FRAC_VALUE: return FRAC;
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
	private MaterialUnit(int value, String name, String literal) {
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
	
} //MaterialUnit
