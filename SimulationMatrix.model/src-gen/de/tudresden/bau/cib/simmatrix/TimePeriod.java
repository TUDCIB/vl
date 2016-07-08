/**
 */
package de.tudresden.bau.cib.simmatrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Period</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTimePeriod()
 * @model extendedMetaData="name='timePeriod'"
 * @generated
 */
public enum TimePeriod implements Enumerator {
	/**
	 * The '<em><b>Daily</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAILY_VALUE
	 * @generated
	 * @ordered
	 */
	DAILY(0, "daily", "daily"),

	/**
	 * The '<em><b>Weekly</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEEKLY_VALUE
	 * @generated
	 * @ordered
	 */
	WEEKLY(1, "weekly", "weekly"),

	/**
	 * The '<em><b>Monthly</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTHLY_VALUE
	 * @generated
	 * @ordered
	 */
	MONTHLY(2, "monthly", "monthly"),

	/**
	 * The '<em><b>Annual</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANNUAL_VALUE
	 * @generated
	 * @ordered
	 */
	ANNUAL(3, "annual", "annual");

	/**
	 * The '<em><b>Daily</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Daily</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAILY
	 * @model name="daily"
	 * @generated
	 * @ordered
	 */
	public static final int DAILY_VALUE = 0;

	/**
	 * The '<em><b>Weekly</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Weekly</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEEKLY
	 * @model name="weekly"
	 * @generated
	 * @ordered
	 */
	public static final int WEEKLY_VALUE = 1;

	/**
	 * The '<em><b>Monthly</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Monthly</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTHLY
	 * @model name="monthly"
	 * @generated
	 * @ordered
	 */
	public static final int MONTHLY_VALUE = 2;

	/**
	 * The '<em><b>Annual</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Annual</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ANNUAL
	 * @model name="annual"
	 * @generated
	 * @ordered
	 */
	public static final int ANNUAL_VALUE = 3;

	/**
	 * An array of all the '<em><b>Time Period</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TimePeriod[] VALUES_ARRAY =
		new TimePeriod[] {
			DAILY,
			WEEKLY,
			MONTHLY,
			ANNUAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Time Period</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TimePeriod> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Time Period</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimePeriod get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimePeriod result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Period</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimePeriod getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimePeriod result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Period</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TimePeriod get(int value) {
		switch (value) {
			case DAILY_VALUE: return DAILY;
			case WEEKLY_VALUE: return WEEKLY;
			case MONTHLY_VALUE: return MONTHLY;
			case ANNUAL_VALUE: return ANNUAL;
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
	private TimePeriod(int value, String name, String literal) {
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
	
} //TimePeriod
