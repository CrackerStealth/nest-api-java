package ca.chrisgraham.nest.api.type;

/**
 * Enumerable type for an Tenant State property including custom
 * string convertible names.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public enum TenantState {
    HOME ("home"),
    AWAY ("away"),
    AUTO_AWAY ("auto-away");
    
	private final String name;       

    private TenantState (String s) {
        name = s;
    }

    public boolean equalsName (String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString (){
       return name;
    }
}
