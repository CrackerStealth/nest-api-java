package ca.chrisgraham.nest.api.device;

import java.util.Date;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.NestApi;
import ca.chrisgraham.nest.api.NestApiDeviceInterface;
import ca.chrisgraham.nest.api.NestApiUtility;

import ca.chrisgraham.nest.api.type.HvacModeState;
import ca.chrisgraham.nest.api.type.TempState;

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
public class Thermostat extends NestApiDeviceInterface<Thermostat> {
	private final static String NEST_API_THERMOSTAT_ITEM_UPDATE_PATH = "/devices/thermostats/%s/%s/";
	
	private String deviceId = null;
	private String locale = null;
	private String softwareVersion = null;
	private String name = null;
	private String nameLong = null;
	private Date lastConnection = null;
	private Boolean isOnline = false;
	private Boolean canCool = false;
	private Boolean canHeat = false;
	private Boolean isUsingEmergencyHeat = false;
	private Boolean hasFan = false;
	private Boolean fanTimerActive = false;
	private Date fanTimerTimeout = null;
	private Boolean hasLeaf = false;
	private TempState temperatureScale = null;
	private Integer targetTemperatureF = 0;
	private Double targetTemperatureC = 0.0;
	private Integer targetTemperatureHighF = 0;
	private Double targetTemperatureHighC = 0.0;
	private Integer targetTemperatureLowF = 0;
	private Double targetTemperatureLowC = 0.0;
	private Integer awayTemperatureHighF = 0;
	private Double awayTemperatureHighC = 0.0;
	private Integer awayTemperatureLowF = 0;
	private Double awayTemperatureLowC = 0.0;
	private HvacModeState hvacMode = null;
	private Integer ambientTemperatureF = 0;
	private Double ambientTemperatureC = 0.0;
	private Integer humidity = 0;
	
	public Thermostat (NestApi apiAccess) throws NestApiException {
		super(apiAccess);
	}
	
	@Override
	public String getType () {
		return NEST_API_THERMOSTATS_KEY;
	}

	
	@Override
	public String getId () {
		return this.deviceId;
	}
	
	@Override
	public Object getParameterValueByName (String parameterName) throws NestApiException {
		if ( parameterName.equals(NEST_API_THERMOSTAT_DEVICE_ID_KEY) ) {
			return this.deviceId;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_LOCALE_KEY) ) {
			return this.locale;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_SOFTWARE_VERSION_KEY) ) {
			return this.softwareVersion;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_NAME_KEY) ) {
			return this.name;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_NAME_LONG_KEY) ) {
			return this.nameLong;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_LAST_CONNECTION_KEY) ) {
			return this.lastConnection;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_IS_ONLINE_KEY) ) {
			return this.isOnline;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_CAN_COOL_KEY) ) {
			return this.canCool;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_CAN_HEAT_KEY) ) {
			return this.canHeat;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_IS_USING_EMERGENCY_HEAT_KEY) ) {
			return this.isUsingEmergencyHeat;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_HAS_FAN_KEY) ) {
			return this.hasFan;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_FAN_TIMER_ACTIVE_KEY) ) {
			return this.fanTimerActive;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_FAN_TIMER_TIMEOUT_KEY) ) {
			return this.fanTimerTimeout;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_HAS_LEAF_KEY) ) {
			return this.hasLeaf;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY) ) {
			return this.temperatureScale;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_F_KEY) ) {
			return this.targetTemperatureF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_C_KEY) ) {
			return this.targetTemperatureC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_F_KEY) ) {
			return this.targetTemperatureHighF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_C_KEY) ) {
			return this.targetTemperatureHighC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_F_KEY) ) {
			return this.targetTemperatureLowF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_C_KEY) ) {
			return this.targetTemperatureLowC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_F_KEY) ) {
			return this.awayTemperatureHighF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_C_KEY) ) {
			return this.awayTemperatureHighC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_F_KEY) ) {
			return this.awayTemperatureLowF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_C_KEY) ) {
			return this.awayTemperatureLowC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_HVAC_MODE_KEY) ) {
			return this.hvacMode;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_F_KEY) ) {
			return this.ambientTemperatureF;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_C_KEY) ) {
			return this.ambientTemperatureC;
		} else if ( parameterName.equals(NEST_API_THERMOSTAT_HUMIDITY_KEY) ) {
			return this.humidity;
		} else {
			throw new NestApiParseException("The parameter name '" + parameterName + "' does not exist in the Nest Thermostat device.");
		}		
	}
	
	@Override
	public void parseJson (String jsonString) throws NestApiException {
		JSONObject json = new JSONObject(jsonString);

		this.deviceId = json.getString(NEST_API_THERMOSTAT_DEVICE_ID_KEY);
		this.locale = json.getString(NEST_API_THERMOSTAT_LOCALE_KEY);
		this.softwareVersion = json.getString(NEST_API_THERMOSTAT_SOFTWARE_VERSION_KEY);
		this.name = json.getString(NEST_API_THERMOSTAT_NAME_KEY);
		this.nameLong = json.getString(NEST_API_THERMOSTAT_NAME_LONG_KEY);
		this.lastConnection = NestApiUtility.parseJsonDate(json.getString(NEST_API_THERMOSTAT_LAST_CONNECTION_KEY));
		this.isOnline = json.getBoolean(NEST_API_THERMOSTAT_IS_ONLINE_KEY);
		this.canCool = json.getBoolean(NEST_API_THERMOSTAT_CAN_COOL_KEY);
		this.canHeat = json.getBoolean(NEST_API_THERMOSTAT_CAN_HEAT_KEY);
		this.isUsingEmergencyHeat =  json.getBoolean(NEST_API_THERMOSTAT_IS_USING_EMERGENCY_HEAT_KEY);
		this.hasFan = json.getBoolean(NEST_API_THERMOSTAT_HAS_FAN_KEY);
		this.fanTimerActive = json.getBoolean(NEST_API_THERMOSTAT_FAN_TIMER_ACTIVE_KEY);
		this.fanTimerTimeout = NestApiUtility.parseJsonDate(json.optString(NEST_API_THERMOSTAT_FAN_TIMER_TIMEOUT_KEY));
		this.hasLeaf = json.getBoolean(NEST_API_THERMOSTAT_HAS_LEAF_KEY);
		this.targetTemperatureF = json.getInt(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_F_KEY);
		this.targetTemperatureC = json.getDouble(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_C_KEY);
		this.targetTemperatureHighF = json.getInt(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_F_KEY);
		this.targetTemperatureHighC = json.getDouble(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_C_KEY);
		this.targetTemperatureLowF = json.getInt(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_F_KEY);
		this.targetTemperatureLowC = json.getDouble(NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_C_KEY);
		this.awayTemperatureHighF = json.getInt(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_F_KEY);
		this.awayTemperatureHighC = json.getDouble(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_C_KEY);
		this.awayTemperatureLowF = json.getInt(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_F_KEY);
		this.awayTemperatureLowC = json.getDouble(NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_C_KEY);
		this.ambientTemperatureF = json.getInt(NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_F_KEY);
		this.ambientTemperatureC = json.getDouble(NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_C_KEY);
		this.humidity = json.optInt(NEST_API_THERMOSTAT_HUMIDITY_KEY);
		
		String tempScale = json.getString(NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY);
		if ( tempScale.equals(NEST_API_THERMOSTAT_TEMP_UNITS_C_KEY) ) {
			this.temperatureScale = TempState.C;
		} else if ( tempScale.equals(NEST_API_THERMOSTAT_TEMP_UNITS_F_KEY) ) {
			this.temperatureScale = TempState.F;
		} else {
			throw new NestApiParseException("Unknown Temperature Scale: " + tempScale);
		}
		
		String hvacMode = json.getString(NEST_API_THERMOSTAT_HVAC_MODE_KEY);
		if ( hvacMode.equals(NEST_API_THERMOSTAT_HVAC_MODE_HEAT_KEY) ) {
			this.hvacMode = HvacModeState.HEAT;
		} else if ( hvacMode.equals(NEST_API_THERMOSTAT_HVAC_MODE_COOL_KEY) ) {
			this.hvacMode = HvacModeState.COOL;
		} else if ( hvacMode.equals(NEST_API_THERMOSTAT_HVAC_MODE_HEAT_COOL_KEY) ) {
			this.hvacMode = HvacModeState.HEAT_COOL;
		} else if ( hvacMode.equals(NEST_API_THERMOSTAT_HVAC_MODE_OFF_KEY) ) {
			this.hvacMode = HvacModeState.OFF;
		} else {
			throw new NestApiParseException("Unknown HVAC Mode: " + hvacMode);
		}	
	}

	@Override
    public int compareTo (Thermostat o) {
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
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setLocale (String locale) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_LOCALE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setSoftwareVersion (String softwareVersion) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_SOFTWARE_VERSION_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setName (String name) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_NAME_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setNameLong (String nameLong) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_NAME_LONG_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setLastConnection (Date lastConnection) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_LAST_CONNECTION_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
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
	public void setIsOnline (Boolean isOnline) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_IS_ONLINE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the canCool
	 */
	public Boolean getCanCool () {
		return canCool;
	}
	
	/**
	 * @param canCool the canCool to set
	 */
	public void setCanCool (Boolean canCool) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_CAN_COOL_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the canHeat
	 */
	public Boolean getCanHeat () {
		return canHeat;
	}
	
	/**
	 * @param canHeat the canHeat to set
	 */
	public void setCanHeat (Boolean canHeat) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_CAN_HEAT_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the isUsingEmergencyHeat
	 */
	public Boolean getIsUsingEmergencyHeat () {
		return isUsingEmergencyHeat;
	}
	
	/**
	 * @param isUsingEmergencyHeat the isUsingEmergencyHeat to set
	 */
	public void setIsUsingEmergencyHeat (Boolean isUsingEmergencyHeat) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_IS_USING_EMERGENCY_HEAT_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the hasFan
	 */
	public Boolean getHasFan () {
		return hasFan;
	}
	
	/**
	 * @param hasFan the hasFan to set
	 */
	public void setHasFan (Boolean hasFan) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_HAS_FAN_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the fanTimerActive
	 */
	public Boolean getFanTimerActive () {
		return fanTimerActive;
	}
	
	/**
	 * @param fanTimerActive the fanTimerActive to set
	 */
	public void setFanTimerActive (Boolean fanTimerActive) throws NestApiException {
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_FAN_TIMER_ACTIVE_KEY);
		sendNestApiUpdates(url, fanTimerActive.toString());
		
		this.fanTimerActive = fanTimerActive;
	}
	
	/**
	 * @return the fanTimerTimeout
	 */
	public Date getFanTimerTimeout () {
		return fanTimerTimeout;
	}
	
	/**
	 * @param fanTimerTimeout the fanTimerTimeout to set
	 */
	public void setFanTimerTimeout (Date fanTimerTimeout) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_FAN_TIMER_TIMEOUT_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the hasLeaf
	 */
	public Boolean getHasLeaf () {
		return hasLeaf;
	}
	
	/**
	 * @param hasLeaf the hasLeaf to set
	 */
	public void setHasLeaf (Boolean hasLeaf) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_HAS_LEAF_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the temperatureScale
	 */
	public TempState getTemperatureScale () {
		return temperatureScale;
	}
	
	/**
	 * @param temperatureScale the temperatureScale to set
	 */
	public void setTemperatureScale (TempState temperatureScale) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the targetTemperatureF
	 */
	public Integer getTargetTemperatureF () {
		return targetTemperatureF;
	}
	
	/**
	 * @param targetTemperatureF the targetTemperatureF to set
	 */
	public void setTargetTemperatureF (Integer targetTemperatureF) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.C) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_F_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.C + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_F_KEY);
		sendNestApiUpdates(url, Integer.toString(targetTemperatureF));
		
		this.targetTemperatureF = targetTemperatureF;
	}
	
	/**
	 * @return the targetTemperatureC
	 */
	public Double getTargetTemperatureC () {
		return targetTemperatureC;
	}
	
	/**
	 * @param targetTemperatureC the targetTemperatureC to set
	 */
	public void setTargetTemperatureC (Double targetTemperatureC) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.F) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_C_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.F + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_C_KEY);
		sendNestApiUpdates(url, Double.toString(targetTemperatureC));
		
		this.targetTemperatureC = targetTemperatureC;
	}
	
	/**
	 * @return the targetTemperatureHighF
	 */
	public Integer getTargetTemperatureHighF () {
		return targetTemperatureHighF;
	}
	
	/**
	 * @param targetTemperatureHighF the targetTemperatureHighF to set
	 */
	public void setTargetTemperatureHighF (Integer targetTemperatureHighF) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.C) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_F_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.C + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_F_KEY);
		sendNestApiUpdates(url, Integer.toString(targetTemperatureHighF));
		
		this.targetTemperatureHighF = targetTemperatureHighF;
	}
	
	/**
	 * @return the targetTemperatureHighC
	 */
	public Double getTargetTemperatureHighC () {
		return targetTemperatureHighC;
	}
	
	/**
	 * @param targetTemperatureHighC the targetTemperatureHighC to set
	 */
	public void setTargetTemperatureHighC (Double targetTemperatureHighC) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.F) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_C_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.F + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_C_KEY);
		sendNestApiUpdates(url, Double.toString(targetTemperatureHighC));
		
		this.targetTemperatureHighC = targetTemperatureHighC;
	}
	
	/**
	 * @return the targetTemperatureLowF
	 */
	public Integer getTargetTemperatureLowF () {
		return targetTemperatureLowF;
	}
	
	/**
	 * @param targetTemperatureLowF the targetTemperatureLowF to set
	 */
	public void setTargetTemperatureLowF (Integer targetTemperatureLowF) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.C) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_F_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.C + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_F_KEY);
		sendNestApiUpdates(url, Integer.toString(targetTemperatureLowF));
		
		this.targetTemperatureLowF = targetTemperatureLowF;
	}
	
	/**
	 * @return the targetTemperatureLowC
	 */
	public Double getTargetTemperatureLowC () {
		return targetTemperatureLowC;
	}
	
	/**
	 * @param targetTemperatureLowC the targetTemperatureLowC to set
	 */
	public void setTargetTemperatureLowC (Double targetTemperatureLowC) throws NestApiException {
		if ( this.temperatureScale.equals(TempState.F) ) {
			throw new NestApiValidationException("The parameter '" + NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_C_KEY + "' cannot be set when '" + NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY + "' is set to '" + TempState.F + "'.");
		}
		
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_C_KEY);
		sendNestApiUpdates(url, Double.toString(targetTemperatureLowC));
		
		this.targetTemperatureLowC = targetTemperatureLowC;
	}
	
	/**
	 * @return the awayTemperatureHighF
	 */
	public Integer getAwayTemperatureHighF () {
		return awayTemperatureHighF;
	}
	
	/**
	 * @param awayTemperatureHighF the awayTemperatureHighF to set
	 */
	public void setAwayTemperatureHighF (Integer awayTemperatureHighF) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the awayTemperatureHighC
	 */
	public Double getAwayTemperatureHighC () {
		return awayTemperatureHighC;
	}
	
	/**
	 * @param awayTemperatureHighC the awayTemperatureHighC to set
	 */
	public void setAwayTemperatureHighC (Double awayTemperatureHighC) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the awayTemperatureLowF
	 */
	public Integer getAwayTemperatureLowF () {
		return awayTemperatureLowF;
	}
	
	/**
	 * @param awayTemperatureLowF the awayTemperatureLowF to set
	 */
	public void setAwayTemperatureLowF (Integer awayTemperatureLowF) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the awayTemperatureLowC
	 */
	public Double getAwayTemperatureLowC () {
		return awayTemperatureLowC;
	}
	
	/**
	 * @param awayTemperatureLowC the awayTemperatureLowC to set
	 */
	public void setAwayTemperatureLowC (Double awayTemperatureLowC) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_DEVICE_ID_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the hvacMode
	 */
	public HvacModeState getHvacMode () {
		return hvacMode;
	}
	
	/**
	 * @param hvacMode the hvacMode to set
	 */
	public void setHvacMode (HvacModeState hvacMode) throws NestApiException {
		String url = String.format(NEST_API_THERMOSTAT_ITEM_UPDATE_PATH, this.getDeviceId(), NEST_API_THERMOSTAT_HVAC_MODE_KEY);
		sendNestApiUpdates(url, hvacMode.toString());
		
		this.hvacMode = hvacMode;
	}
	
	/**
	 * @return the ambientTemperatureF
	 */
	public Integer getAmbientTemperatureF () {
		return ambientTemperatureF;
	}
	
	/**
	 * @param ambientTemperatureF the ambientTemperatureF to set
	 */
	public void setAmbientTemperatureF (Integer ambientTemperatureF) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_F_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the ambientTemperatureC
	 */
	public Double getAmbientTemperatureC () {
		return ambientTemperatureC;
	}
	
	/**
	 * @param ambientTemperatureC the ambientTemperatureC to set
	 */
	public void setAmbientTemperatureC (Double ambientTemperatureC) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_C_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
	
	/**
	 * @return the humidity
	 */
	public Integer getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(Integer humidity) throws NestApiException {
		throw new NestApiValidationException("The '" + NEST_API_THERMOSTAT_HUMIDITY_KEY + "' parameter is not updateable in the Nest API for a Nest Thermostat.");
	}
}
