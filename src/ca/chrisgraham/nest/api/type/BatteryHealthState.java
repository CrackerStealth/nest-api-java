package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for an Battery Health State property including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum BatteryHealthState {
	OK ("ok"),
	REPLACE ("replace");
    
	private final String name;       

    private BatteryHealthState (String s) {
        name = s;
    }

    public boolean equalsName (String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString () {
       return name;
    }
}
