package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for an temperature units including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum TempState {
	C ("C"),
	F ("F");
	
	private final String name;       

	private TempState (String s) {
		name = s;
	}

	public boolean equalsName (String otherName){
		return (otherName == null)? false:name.equals(otherName);
	}

	public String toString () {
		return name;
	}
}
