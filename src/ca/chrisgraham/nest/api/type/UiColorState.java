package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for an UI Color property including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum UiColorState {
    GREY ("grey"),
    GREEN ("green"),
    YELLOW ("yellow"),
    RED ("red");
    
	private final String name;       

    private UiColorState (String s) {
        name = s;
    }

    public boolean equalsName (String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString () {
       return name;
    }
}
