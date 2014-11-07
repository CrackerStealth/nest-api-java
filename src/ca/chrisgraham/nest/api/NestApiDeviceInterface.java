package ca.chrisgraham.nest.api;

import ca.chrisgraham.nest.api.exception.NestApiException;

public interface NestApiDeviceInterface extends Comparable<NestApiDeviceInterface> {	
	public String getType ();
	
	public String getId ();
	
	public Object getParameterValueByName(String parameterName) throws NestApiException;
	
	public void parseJson (String jsonString) throws NestApiException;
	public NestApiChangeItem[] getChanges ();
	public int countChanges ();
	
	abstract boolean isChanged();
}
