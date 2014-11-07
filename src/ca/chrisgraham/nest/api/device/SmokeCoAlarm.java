package ca.chrisgraham.nest.api.device;

import java.util.Date;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.NestApi;
import ca.chrisgraham.nest.api.NestApiDeviceInterface;
import ca.chrisgraham.nest.api.NestApiUtility;

import ca.chrisgraham.nest.api.type.AlarmState;
import ca.chrisgraham.nest.api.type.BatteryHealthState;
import ca.chrisgraham.nest.api.type.UiColorState;


import ca.chrisgraham.nest.api.exception.NestApiException;
import ca.chrisgraham.nest.api.exception.NestApiParseException;
import ca.chrisgraham.nest.api.exception.NestApiValidationException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

/**
 * Custom class for holding all properties about a Nest Protect from 
 * the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class SmokeCoAlarm extends NestApiDeviceInterface<SmokeCoAlarm> {
	//private final static String NEST_API_SMOKE_CO_ALARM_ITEM_UPDATE_PATH = "/devices/smoke_co_alarms/%s/%s/";
	
	private String deviceId = null;
	private String locale = null;
	private String softwareVersion = null;
	private String name = null;
	private String nameLong = null;
	private Date lastConnection = null;
	private Boolean isOnline = false;
	private BatteryHealthState batteryHealth = null;
	private AlarmState coAlarmState = null;
	private AlarmState smokeAlarmState = null;
	private UiColorState uiColorState = null;
	
	public SmokeCoAlarm (NestApi apiAccess) throws NestApiException {
		super(apiAccess);
	}
	
	@Override
	public String getType () {
		return NEST_API_SMOKE_CO_ALARMS_KEY;
	}
	
	@Override
	public String getId () {
		return this.deviceId;
	}
	
	@Override
	public Object getParameterValueByName (String parameterName) throws NestApiException {
		if ( parameterName.equals(NEST_API_SMOKE_CO_DEVICE_ID_KEY) ) {
			return this.deviceId;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_LOCALE_KEY) ) {
			return this.locale;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_SOFTWARE_VERSION_KEY) ) {
			return this.softwareVersion;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_NAME_KEY) ) {
			return this.name;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_NAME_LONG_KEY) ) {
			return this.nameLong;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_LAST_CONNECTION_KEY) ) {
			return this.lastConnection;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_IS_ONLINE_KEY) ) {
			return this.isOnline;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_BATTERY_HEALTH_KEY) ) {
			return this.batteryHealth;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_CO_ALARM_STATE_KEY) ) {
			return this.coAlarmState;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_SMOKE_ALARM_STATE_KEY) ) {
			return this.smokeAlarmState;
		} else if ( parameterName.equals(NEST_API_SMOKE_CO_UI_COLOR_STATE_KEY) ) {
			return this.uiColorState;
		} else {
			throw new NestApiParseException("The parameter name '" + parameterName + "' does not exist in the Nest Protect device.");
		}	
	}

	@Override
	public void parseJson (String jsonString) throws NestApiException {
		JSONObject json = new JSONObject(jsonString);

		this.deviceId = json.getString(NEST_API_SMOKE_CO_DEVICE_ID_KEY);
		this.locale = json.getString(NEST_API_SMOKE_CO_LOCALE_KEY);
		this.softwareVersion = json.getString(NEST_API_SMOKE_CO_SOFTWARE_VERSION_KEY);
		this.name = json.getString(NEST_API_SMOKE_CO_NAME_KEY);
		this.nameLong = json.getString(NEST_API_SMOKE_CO_NAME_LONG_KEY);
		this.lastConnection = NestApiUtility.parseJsonDate(json.getString(NEST_API_SMOKE_CO_LAST_CONNECTION_KEY));
		this.isOnline = json.getBoolean(NEST_API_SMOKE_CO_IS_ONLINE_KEY);
		
		String batteryHealth = json.getString(NEST_API_SMOKE_CO_BATTERY_HEALTH_KEY);
		if ( batteryHealth.equals(NEST_API_SMOKE_CO_BATT_HEALTH_OK_KEY) ) {
			this.batteryHealth = BatteryHealthState.OK;
		} else if ( batteryHealth.equals(NEST_API_SMOKE_CO_BATT_HEALTH_REPLACE_KEY) ) {
			this.batteryHealth = BatteryHealthState.REPLACE;
		} else {
			throw new NestApiParseException("Unknown battery health state: " + batteryHealth);
		}
		
		String coAlarmState = json.getString(NEST_API_SMOKE_CO_CO_ALARM_STATE_KEY);
		if ( coAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_OK_KEY) ) {
			this.coAlarmState = AlarmState.OK;
		} else if ( coAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_WARNING_KEY) ) {
			this.coAlarmState = AlarmState.WARNING;
		} else if ( coAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_EMERGENCY_KEY) ) {
			this.coAlarmState = AlarmState.EMERGENCY;
		} else {
			throw new NestApiParseException("Unknown CO alarm state: " + coAlarmState);
		}
		
		String smokeAlarmState = json.getString(NEST_API_SMOKE_CO_SMOKE_ALARM_STATE_KEY);
		if ( smokeAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_OK_KEY) ) {
			this.smokeAlarmState = AlarmState.OK;
		} else if ( smokeAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_WARNING_KEY) ) {
			this.smokeAlarmState = AlarmState.WARNING;
		} else if ( smokeAlarmState.equals(NEST_API_SMOKE_CO_ALARM_STATE_EMERGENCY_KEY) ) {
			this.smokeAlarmState = AlarmState.EMERGENCY;
		} else {
			throw new NestApiParseException("Unknown smoke alarm state: " + smokeAlarmState);
		}

		String uiColor = json.getString(NEST_API_SMOKE_CO_UI_COLOR_STATE_KEY);
		if ( uiColor.equals(NEST_API_SMOKE_CO_ALARM_UI_COLOR_GREY_KEY) ) {
			this.uiColorState = UiColorState.GREY;
		} else if ( uiColor.equals(NEST_API_SMOKE_CO_ALARM_UI_COLOR_GREEN_KEY) ) {
			this.uiColorState = UiColorState.GREEN;
		} else if ( uiColor.equals(NEST_API_SMOKE_CO_ALARM_UI_COLOR_YELLOW_KEY) ) {
			this.uiColorState = UiColorState.YELLOW;
		} else if ( uiColor.equals(NEST_API_SMOKE_CO_ALARM_UI_COLOR_RED_KEY) ) {
			this.uiColorState = UiColorState.RED;
		} else {
			throw new NestApiParseException("Unknown UI color: " + uiColor);
		}		
	}

	@Override
    public int compareTo (SmokeCoAlarm o) {
        return this.getDeviceId().compareTo(o.getDeviceId());
    }
	
	/**
	 * @return the deviceId
	 */
	public String getDeviceId () {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId (String deviceId) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the locale
	 */
	public String getLocale () {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale (String locale)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_LOCALE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the softwareVersion
	 */
	public String getSoftwareVersion () {
		return softwareVersion;
	}

	/**
	 * @param softwareVersion the softwareVersion to set
	 */
	public void setSoftwareVersion (String softwareVersion)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_SOFTWARE_VERSION_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setName (String name)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_NAME_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the nameLong
	 */
	public String getNameLong () {
		return nameLong;
	}

	/**
	 * @param nameLong the nameLong to set
	 */
	public void setNameLong (String nameLong)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_NAME_LONG_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the lastConnection
	 */
	public Date getLastConnection () {
		return lastConnection;
	}

	/**
	 * @param lastConnection the lastConnection to set
	 */
	public void setLastConnection (Date lastConnection)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_LAST_CONNECTION_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the isOnline
	 */
	public Boolean getIsOnline () {
		return isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setIsOnline (Boolean isOnline)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_IS_ONLINE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the batteryHealth
	 */
	public BatteryHealthState getBatteryHealth () {
		return batteryHealth;
	}

	/**
	 * @param batteryHealth the batteryHealth to set
	 */
	public void setBatteryHealth (BatteryHealthState batteryHealth)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_BATTERY_HEALTH_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the coAlarmState
	 */
	public AlarmState getCoAlarmState () {
		return coAlarmState;
	}

	/**
	 * @param coAlarmState the coAlarmState to set
	 */
	public void setCoAlarmState (AlarmState coAlarmState)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_CO_ALARM_STATE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the smokeAlarmState
	 */
	public AlarmState getSmokeAlarmState () {
		return smokeAlarmState;
	}

	/**
	 * @param smokeAlarmState the smokeAlarmState to set
	 */
	public void setSmokeAlarmState (AlarmState smokeAlarmState)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_SMOKE_ALARM_STATE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}

	/**
	 * @return the uiColorState
	 */
	public UiColorState getUiColorState () {
		return uiColorState;
	}

	/**
	 * @param uiColorState the uiColorState to set
	 */
	public void setUiColorState (UiColorState uiColorState)  throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_SMOKE_CO_UI_COLOR_STATE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
}
