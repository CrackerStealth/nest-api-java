package ca.chrisgraham.nest.api.structure;

import java.util.Date;
import java.util.TimeZone;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.NestApi;
import ca.chrisgraham.nest.api.NestApiDeviceInterface;
import ca.chrisgraham.nest.api.NestApiUtility;

import ca.chrisgraham.nest.api.type.TenantState;

import ca.chrisgraham.nest.api.exception.NestApiException;
import ca.chrisgraham.nest.api.exception.NestApiParseException;
import ca.chrisgraham.nest.api.exception.NestApiValidationException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

/**
 * Custom class for holding all properties about a Nest Structure from 
 * the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class Structure extends NestApiDeviceInterface<Structure> {
	private final static String NEST_API_STRUCTURE_ITEM_UPDATE_PATH = " /structures/%s/%s/";
	
	private String structureId = null;
	private TenantState away = null;
	private String name = null;
	private String countryCode = null;
	private Date peakPeriodStartTime = null;
	private Date peakPeriodEndTime = null;
	private TimeZone timeZone = null;
	private StructureEta structureEta = null;
	private String postalCode;
	
	public Structure (NestApi apiAccess) throws NestApiException {
		super(apiAccess);
	}
	
	@Override
	public String getType () {
		return NEST_API_STRCUTURES_KEY;
	}

	@Override
	public String getId () {
		return this.structureId;
	}
	
	@Override
	public Object getParameterValueByName (String parameterName) throws NestApiParseException {
		if ( parameterName.equals(NEST_API_STRUCT_STRUCTURE_ID_KEY) ) {
			return this.structureId;
		} else if ( parameterName.equals(NEST_API_STRUCT_AWAY_KEY) ) {
			return this.away;
		} else if ( parameterName.equals(NEST_API_STRUCT_NAME_KEY) ) {
			return this.name;
		} else if ( parameterName.equals(NEST_API_STRUCT_COUNTRY_CODE_KEY) ) {
			return this.countryCode;
		} else if ( parameterName.equals(NEST_API_STRUCT_PEAK_PERIOD_START_TIME_KEY) ) {
			return this.peakPeriodStartTime;
		} else if ( parameterName.equals(NEST_API_STRUCT_PEAK_PERIOD_END_TIME_KEY) ) {
			return this.peakPeriodEndTime;
		} else if ( parameterName.equals(NEST_API_STRUCT_TIME_ZONE_KEY) ) {
			return this.timeZone;
		} else if ( parameterName.equals(NEST_API_STRUCT_POSTAL_CODE_KEY) ) {
			return this.postalCode;
		} else {
			throw new NestApiParseException("The parameter name '" + parameterName + "' does not exist in the Nest Structure object.");
		}
	}

	@Override
	public void parseJson (String jsonString) throws NestApiParseException {
		JSONObject json = new JSONObject(jsonString);
		
		this.structureId = json.getString(NEST_API_STRUCT_STRUCTURE_ID_KEY);
		this.name = json.getString(NEST_API_STRUCT_NAME_KEY);
		this.countryCode = json.getString(NEST_API_STRUCT_COUNTRY_CODE_KEY);
		this.peakPeriodStartTime = NestApiUtility.parseJsonDate(json.optString(NEST_API_STRUCT_PEAK_PERIOD_START_TIME_KEY));
		this.peakPeriodEndTime = NestApiUtility.parseJsonDate(json.optString(NEST_API_STRUCT_PEAK_PERIOD_END_TIME_KEY));
		this.timeZone = TimeZone.getTimeZone(json.optString(NEST_API_STRUCT_TIME_ZONE_KEY));
		this.postalCode = json.optString(NEST_API_STRUCT_POSTAL_CODE_KEY);
		
		String away = json.getString(NEST_API_STRUCT_AWAY_KEY);
		if ( away.equals(NEST_API_STRUCT_TENANT_HOME_KEY) ) {
			this.away = TenantState.HOME;
		} else if ( away.equals(NEST_API_STRUCT_TENANT_AWAY_KEY) ) {
			this.away = TenantState.AWAY;
		} else if ( away.equals(NEST_API_STRUCT_TENANT_AUTO_AWAY_KEY) ) {
			this.away = TenantState.AUTO_AWAY;
		} else {
			throw new NestApiParseException("Unknown Away State: " + away);
		}
	}
	
	@Override
    public int compareTo (Structure o) {
        return this.getStructureId().compareTo(o.getStructureId());
    }
	
	/**
	 * @return the structureId
	 */
	public String getStructureId () {
		return structureId;
	}
	
	/**
	 * @param structureId the structureId to set
	 */
	public void setStructureId (String structureId) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_STRUCTURE_ID_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the away
	 */
	public TenantState getAway () {
		return away;
	}
	
	/**
	 * @param away the away to set
	 */
	public void setAway (TenantState away) throws NestApiException {
		String url = String.format(NEST_API_STRUCTURE_ITEM_UPDATE_PATH, NEST_API_STRUCT_AWAY_KEY, this.getStructureId());
		sendNestApiUpdates(url, away.toString());
		
		this.away = away;
	}
	
	/**
	 * @return the name
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName (String name) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_NAME_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the countryCode
	 */
	public String getCountryCode () {
		return countryCode;
	}
	
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode (String countryCode) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_COUNTRY_CODE_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the peakPeriodStartTime
	 */
	public Date getPeakPeriodStartTime () {
		return peakPeriodStartTime;
	}
	
	/**
	 * @param peakPeriodStartTime the peakPeriodStartTime to set
	 */
	public void setPeakPeriodStartTime (Date peakPeriodStartTime) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_PEAK_PERIOD_START_TIME_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the peakPeriodEndTime
	 */
	public Date getPeakPeriodEndTime () {
		return peakPeriodEndTime;
	}
	
	/**
	 * @param peakPeriodEndTime the peakPeriodEndTime to set
	 */
	public void setPeakPeriodEndTime (Date peakPeriodEndTime) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_PEAK_PERIOD_END_TIME_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone () {
		return timeZone;
	}
	
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone (TimeZone timeZone) throws NestApiValidationException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_TIME_ZONE_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
	
	/**
	 * @return the structureEta
	 */
	public StructureEta getStructureEta () {
		return structureEta;
	}
	
	/**
	 * @param structureEta the structureEta to set
	 */
	public void setStructureEta (StructureEta structureEta) throws NestApiException {
		String url = String.format(NEST_API_STRUCTURE_ITEM_UPDATE_PATH, NEST_API_STRUCT_ETA_KEY, this.getStructureId());
		sendNestApiUpdates(url, structureEta.getJson());
	}
	
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_STRUCT_POSTAL_CODE_KEY + "' parameter is not updateable in the Nest API for a Structure object.");
	}
}
