package ca.chrisgraham.nest.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.exception.NestApiCommunicationException;
import ca.chrisgraham.nest.api.exception.NestApiException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

public abstract class NestApiDeviceInterface<T extends NestApiDeviceInterface<T>> implements Comparable<T> {
	private NestApi api = null;
	
	public NestApiDeviceInterface (NestApi apiAccess) throws NestApiException {
		this.api = apiAccess;
		
		if ( this.api == null) {
			throw new NestApiException("NestApi class not provided.");
		}
	}
	
	abstract public String getType ();
	
	abstract public String getId ();
	
	abstract public Object getParameterValueByName (String parameterName) throws NestApiException;
	
	abstract public void parseJson (String jsonString) throws NestApiException;
	
	protected void sendNestApiUpdates (String path, String body) throws NestApiException {
		try {
			String response = null;
			JSONObject jsonResponse = null;
			
			StringBuilder requestParam = new StringBuilder();
			NestApiUtility.addEncodedParameter(requestParam, api.NEST_API_AUTH_PARAMETER_NAME, api.getToken());
			
			String url = api.NEST_API_URL + path + "?" + requestParam.toString();
			
			response = NestApiUtility.handleHTTPSPut(url, body, api.getTimeout());
			jsonResponse = new JSONObject(response);
			
			if ( jsonResponse.has(NEST_API_ERROR_RESPONSE_KEY) ) {
				String message = jsonResponse.getString(NEST_API_ERROR_RESPONSE_KEY);
				throw new NestApiCommunicationException (message);
			}
		} catch (UnsupportedEncodingException ex) {
			throw new NestApiException("Could not encode URL parameters to communicate with the Nest API: " + ex.getMessage());
		} catch (IOException ex) {
			throw new NestApiCommunicationException("Could not communicate with the Nest API: " + ex.getMessage());
		} catch (InterruptedException ex) {
			throw new NestApiCommunicationException("The Nest API took too long to respond: " + ex.getMessage());
		}
	}
}
