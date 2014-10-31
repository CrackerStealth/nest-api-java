package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for the HVAC Mode property including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum HvacModeState {
	HEAT ("heat"),
	COOL ("cool"),
	HEAT_COOL ("heat-cool"),
	OFF ("off");
	
	private final String name;       

    private HvacModeState (String s) {
        name = s;
    }

    public boolean equalsName (String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString () {
       return name;
    }
}
