/**
 * @author Chad Rehm
 * @date 2/25/21
 * @description This class represents an literal for a cnf formula.
 */
package cs475_sat_rehm;

public class Literal {
	private String name;
	private boolean isNegated;

	/**
	 * Getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns whether the literal ins negated or not
	 * @return
	 */
	public boolean isIsNegated() {
		return isNegated;
	}

	/**
	 * Setter for isNegated
	 * @param isNegated
	 */
	public void setIsNegated(boolean isNegated) {
		this.isNegated = isNegated;
	}
	
	@Override
	public String toString() {
		return String.format("%s%s", name, isNegated ? " is negated" : "");
	}
}
