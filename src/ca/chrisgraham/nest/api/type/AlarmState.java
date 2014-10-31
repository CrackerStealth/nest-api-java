package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for an Alarm State property including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum AlarmState {
	OK ("ok"),
	WARNING ("warning"),
	EMERGENCY ("emergency");
	
	private final String name;       

	private AlarmState (String s) {
		name = s;
	}

	public boolean equalsName (String otherName){
		return (otherName == null)? false:name.equals(otherName);
	}

	public String toString () {
		return name;
	}
}
