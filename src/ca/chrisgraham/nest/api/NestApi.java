package ca.chrisgraham.nest.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.device.SmokeCoAlarm;
import ca.chrisgraham.nest.api.device.Thermostat;

import ca.chrisgraham.nest.api.structure.Structure;

import ca.chrisgraham.nest.api.exception.NestApiCommunicationException;
import ca.chrisgraham.nest.api.exception.NestApiException;
import ca.chrisgraham.nest.api.exception.NestApiParseException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

/**
 * Using an oAuth2 token received from the Nest developer API, query the Nest API
 * for JSON response, then parse the response into a simple Java class structure.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class NestApi {
	public final static String NEST_API_URL = "https://developer-api.nest.com";
	public final static String NEST_API_AUTH_PARAMETER_NAME = "auth";
	
	//private final static String NEST_API_AUTHORIZATION_URL = "https://home.nest.com/login/oauth2?client_id=<CLIENT_ID>&state=<STATE_ID>";
	private final static String NEST_ACCESS_TOKEN_URL = "https://api.home.nest.com/oauth2/access_token";
	private final static String NEST_API_CLIENT_ID_PARAMETER_NAME = "client_id";
	private final static String NEST_API_CLIENT_SECRET_PARAMETER_NAME = "client_secret";
	//private final static String NEST_API_STATE_PARAMETER_NAME = "state";
	private final static String NEST_API_GRANT_TYPE_PARAMETER_NAME = "auth";
	private final static String NEST_API_CODE_PARAMETER_NAME = "code";
	
	private String token = null;
	private String clientId = null;
	private String clientSecret = null;
	private int timeout = 5000;
	
	private List<Thermostat> thermostats = Collections.synchronizedList(new ArrayList<Thermostat>());
	private List<SmokeCoAlarm> smokeCoAlarms = Collections.synchronizedList(new ArrayList<SmokeCoAlarm>());
	
	private List<Structure> structures = Collections.synchronizedList(new ArrayList<Structure>());
	
	public NestApi (String token) {
		setToken(token);
	}
	
	public NestApi (int timeout) {
		setTimeout(timeout);
	}
	
	public NestApi (String token, int timeout) {
		setToken(token);
		setTimeout(timeout);
	}
	
	public NestApi (String clientId, String clientSecret) {
		setClientId(clientId);
		setClientSecret(clientSecret);
	}
	
	public NestApi (String clientId, String clientSecret, int timeout) {
		setClientId(clientId);
		setClientSecret(clientSecret);
		setTimeout(timeout);
	}

	public NestApi (String token, String clientId, String clientSecret) {
		setToken(token);
		setClientId(clientId);
		setClientSecret(clientSecret);
	}
	
	public NestApi (String token, String clientId, String clientSecret, int timeout) {
		setToken(token);
		setClientId(clientId);
		setClientSecret(clientSecret);
		setTimeout(timeout);
	}
	
	public String getToken () {
		return this.token;
	}
	
	public void setToken (String token) {
		if ( NestApiUtility.isNotBlank(token) ) {
			this.token = token;
		}
	}
	
	public int getTimeout () {
		return this.timeout;
	}
	
	public void setTimeout (int timeoutIn) {
		if ( timeoutIn > 0 ) {
			this.timeout = timeoutIn;
		}
	}
	
	public String getClientId () {
		return this.clientId;
	}
	
	public void setClientId (String clientId) {
		if ( NestApiUtility.isNotBlank(clientId) ) {
			this.clientId = clientId;
		}
	}
	
	public void setClientSecret (String clientSecret) {
		if ( NestApiUtility.isNotBlank(clientSecret) ) {
			this.clientSecret = clientSecret;
		}
	}
	
	public String requestTokenFromNest (int pincode) throws NestApiException {
		if ( NestApiUtility.isBlank(clientId) ) {
			throw new NestApiException("Client Id was not provided to request token from Nest API.");
		}
		
		if ( NestApiUtility.isBlank(clientSecret) ) {
			throw new NestApiException("Client secret was not provided to request token from Nest API.");
		}
		
		StringBuilder postParam = new StringBuilder();
		
		try {
			NestApiUtility.addEncodedParameter(postParam, NEST_API_CLIENT_ID_PARAMETER_NAME, clientId);
			NestApiUtility.addEncodedParameter(postParam, NEST_API_CODE_PARAMETER_NAME, String.valueOf(pincode));
			NestApiUtility.addEncodedParameter(postParam, NEST_API_CLIENT_SECRET_PARAMETER_NAME, clientSecret);
			NestApiUtility.addEncodedParameter(postParam, NEST_API_GRANT_TYPE_PARAMETER_NAME, "authorization_code");
		} catch (UnsupportedEncodingException ex) {
			throw new NestApiException ("Could not encode URL parameters to communicate with the Nest API: " + ex.getMessage());
		}
		
		String url = NEST_ACCESS_TOKEN_URL + "?" + postParam;
		
		String response = null;
		
		try {
			response = NestApiUtility.handleHTTPSPost(url, null, timeout);
		} catch (IOException ex) {
			throw new NestApiCommunicationException("Could not communicate with the Nest API: " + ex.getMessage());
		} catch (InterruptedException ex) {
			throw new NestApiCommunicationException("The Nest API took too long to respond: " + ex.getMessage());
		}
		
		return response;
	}
	
	public void refresh () throws NestApiException {
		String rawResponse = null;
		
		try {
			rawResponse = getCurrentNestApiState();
		} catch (UnsupportedEncodingException ex) {
			throw new NestApiException("Could not encode URL parameters to communicate with the Nest API: " + ex.getMessage());
		} catch (IOException ex) {
			throw new NestApiCommunicationException("Could not communicate with the Nest API: " + ex.getMessage());
		} catch (InterruptedException ex) {
			throw new NestApiCommunicationException("The Nest API took too long to respond: " + ex.getMessage());
		}
		
		JSONObject jsonResponse = new JSONObject(rawResponse);
		
		try {
			parseThermostats(jsonResponse);
		} catch (Exception ex) {
			throw new NestApiParseException("Could not parse Thermostats from JSON response body: " + ex.getMessage());
		}
		
		try {	
			parseSmokeCoAlarms(jsonResponse);
		} catch (Exception ex) {
			throw new NestApiParseException("Could not parse Smoke/CO Alarms from JSON response body: " + ex.getMessage());
		}
		
		try {
			parseStructures(jsonResponse);
		} catch (Exception ex) {
			throw new NestApiParseException("Could not parse Structures from JSON response body: " + ex.getMessage());
		}		
	}
	
	public String[] getThermostatIdList () {
		String[] thermostatIdList = new String[thermostats.size()];
		
		for ( int i = 0; i < thermostats.size(); i++ ) {
			thermostatIdList[i] = thermostats.get(i).getDeviceId();
		}
		
		return thermostatIdList;
	}
	
	public Thermostat getThermostatById (String idIn) {
		for (Thermostat item : thermostats) {
			if (item.getDeviceId().equals(idIn)) {
				return item;
			}
		}
		
		return null;
	}
	
	public Thermostat getThermostatByName (String nameIn) {
		for (Thermostat item : thermostats) {
			if (item.getName().equals(nameIn)) {
				return item;
			}
		}
		
		return null;		
	}
	
	public Thermostat getThermostatByNameLong (String nameLongIn) {
		for (Thermostat item : thermostats) {
			if (item.getNameLong().equals(nameLongIn)) {
				return item;
			}
		}
		
		return null;	
	}

	public String[] getSmokeCoAlarmIdList () {
		String[] SmokeCoAlarmIdList = new String[smokeCoAlarms.size()];
		
		for ( int i = 0; i < smokeCoAlarms.size(); i++ ) {
			SmokeCoAlarmIdList[i] = smokeCoAlarms.get(i).getDeviceId();
		}
		
		return SmokeCoAlarmIdList;
	}
	
	public SmokeCoAlarm getSmokeCoAlarmById (String idIn) {
		for (SmokeCoAlarm item : smokeCoAlarms) {
			if (item.getDeviceId().equals(idIn)) {
				return item;
			}
		}
		
		return null;
	}
	
	public SmokeCoAlarm getSmokeCoAlarmByName (String nameIn) {
		for (SmokeCoAlarm item : smokeCoAlarms) {
			if (item.getName().equals(nameIn)) {
				return item;
			}
		}
		
		return null;
	}
	
	public SmokeCoAlarm getSmokeCoAlarmByNameLong (String nameLongIn) {
		for (SmokeCoAlarm item : smokeCoAlarms) {
			if (item.getNameLong().equals(nameLongIn)) {
				return item;
			}
		}
		
		return null;
	}

	public String[] getStructureIdList () {
		String[] structuresIdList = new String[structures.size()];
		
		for ( int i = 0; i < structures.size(); i++ ) {
			structuresIdList[i] = structures.get(i).getStructureId();
		}
		
		return structuresIdList;
	}
	
	public Structure getStructureById (String idIn) {
		for (Structure item : structures) {
			if (item.getStructureId().equals(idIn)) {
				return item;
			}
		}
		
		return null;
	}
	
	public Structure getStructureByName (String nameIn) {
		for (Structure item : structures) {
			if (item.getName().equals(nameIn)) {
				return item;
			}
		}
		
		return null;
	}
	
	private String getCurrentNestApiState () throws IOException, InterruptedException, UnsupportedEncodingException {
		String response = null;
		
		StringBuilder requestParam = new StringBuilder();
		NestApiUtility.addEncodedParameter(requestParam, NEST_API_AUTH_PARAMETER_NAME, token);
		
		String url = NEST_API_URL + "?" + requestParam.toString();
		
		response = NestApiUtility.handleHTTPSGet(url, timeout);
		
		return response;
	}
	
	private void parseThermostats (JSONObject json) throws Exception {		
		JSONObject devices = json.optJSONObject(NEST_API_DEVICES_KEY);
		thermostats = Collections.synchronizedList(new ArrayList<Thermostat>());
		
		if (devices == null) {
			return;
		}
		
		JSONObject therm = devices.optJSONObject(NEST_API_THERMOSTATS_KEY);
		
		if (therm == null) {
			return;
		}
		
		for (String name : JSONObject.getNames(therm)) {
			JSONObject item = therm.getJSONObject(name);
			
			Thermostat newTherm = new Thermostat(this);
			newTherm.parseJson(item.toString());
			
			thermostats.add(newTherm);
		}
	}

	private void parseSmokeCoAlarms (JSONObject json) throws Exception {
		JSONObject devices = json.optJSONObject(NEST_API_DEVICES_KEY);
		smokeCoAlarms = Collections.synchronizedList(new ArrayList<SmokeCoAlarm>());
		
		if (devices == null) {
			return;
		}
		
		JSONObject smokeco = devices.optJSONObject(NEST_API_SMOKE_CO_ALARMS_KEY);
		
		if (smokeco == null) {
			return;
		}

		for ( String name : JSONObject.getNames(smokeco) ) {
			JSONObject item = smokeco.getJSONObject(name);
			
			SmokeCoAlarm newAlarm = new SmokeCoAlarm(this);
			newAlarm.parseJson(item.toString());
			
			smokeCoAlarms.add(newAlarm);
		}
	}

	private void parseStructures (JSONObject json) throws Exception {
		JSONObject struct = json.optJSONObject(NEST_API_STRCUTURES_KEY);
		structures = Collections.synchronizedList(new ArrayList<Structure>());
				
		if (struct == null) {
			return;
		}

		for ( String name : JSONObject.getNames(struct) ) {
			JSONObject item = struct.getJSONObject(name);
			
			Structure newStruct = new Structure(this);
			newStruct.parseJson(item.toString());
			
			structures.add(newStruct);
		}		
	}
}
