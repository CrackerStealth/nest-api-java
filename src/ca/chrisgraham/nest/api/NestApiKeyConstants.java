package ca.chrisgraham.nest.api;

/**
 * Constant values that represent the name of the JSON keys used in the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public final class NestApiKeyConstants {
	private NestApiKeyConstants () {
		// no operation
	}
	
	public final static String NEST_API_DEVICES_KEY = "devices";
	public final static String NEST_API_THERMOSTATS_KEY = "thermostats";
	public final static String NEST_API_SMOKE_CO_ALARMS_KEY = "smoke_co_alarms";
	public final static String NEST_API_STRCUTURES_KEY = "structures";
	
	public final static String NEST_API_ERROR_RESPONSE_KEY = "error";
	
	public final static String NEST_API_THERMOSTAT_DEVICE_ID_KEY = "device_id";
	public final static String NEST_API_THERMOSTAT_LOCALE_KEY = "locale";
	public final static String NEST_API_THERMOSTAT_SOFTWARE_VERSION_KEY = "software_version";
	public final static String NEST_API_THERMOSTAT_STRUCTURE_ID_KEY = "structure_id";
	public final static String NEST_API_THERMOSTAT_NAME_KEY = "name";
	public final static String NEST_API_THERMOSTAT_NAME_LONG_KEY = "name_long";
	public final static String NEST_API_THERMOSTAT_LAST_CONNECTION_KEY = "last_connection";
	public final static String NEST_API_THERMOSTAT_IS_ONLINE_KEY = "is_online";
	public final static String NEST_API_THERMOSTAT_CAN_COOL_KEY = "can_cool";
	public final static String NEST_API_THERMOSTAT_CAN_HEAT_KEY = "can_heat";
	public final static String NEST_API_THERMOSTAT_IS_USING_EMERGENCY_HEAT_KEY = "is_using_emergency_heat";
	public final static String NEST_API_THERMOSTAT_HAS_FAN_KEY = "has_fan";
	public final static String NEST_API_THERMOSTAT_FAN_TIMER_ACTIVE_KEY = "fan_timer_active";
	public final static String NEST_API_THERMOSTAT_FAN_TIMER_TIMEOUT_KEY = "fan_timer_timeout";
	public final static String NEST_API_THERMOSTAT_HAS_LEAF_KEY = "has_leaf";
	public final static String NEST_API_THERMOSTAT_TEMPERATURE_SCALE_KEY = "temperature_scale";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_F_KEY = "target_temperature_f";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_C_KEY = "target_temperature_c";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_F_KEY = "target_temperature_high_f";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_HIGH_C_KEY = "target_temperature_high_c";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_F_KEY = "target_temperature_low_f";
	public final static String NEST_API_THERMOSTAT_TARGET_TEMPERATURE_LOW_C_KEY = "target_temperature_low_c";
	public final static String NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_F_KEY = "away_temperature_high_f";
	public final static String NEST_API_THERMOSTAT_AWAY_TEMPERATURE_HIGH_C_KEY = "away_temperature_high_c";
	public final static String NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_F_KEY = "away_temperature_low_f";
	public final static String NEST_API_THERMOSTAT_AWAY_TEMPERATURE_LOW_C_KEY = "away_temperature_low_c";
	public final static String NEST_API_THERMOSTAT_HVAC_MODE_KEY = "hvac_mode";
	public final static String NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_F_KEY = "ambient_temperature_f";
	public final static String NEST_API_THERMOSTAT_AMBIENT_TEMPERATURE_C_KEY = "ambient_temperature_c";

	public final static String NEST_API_SMOKE_CO_DEVICE_ID_KEY = "device_id";
	public final static String NEST_API_SMOKE_CO_LOCALE_KEY = "locale";
	public final static String NEST_API_SMOKE_CO_SOFTWARE_VERSION_KEY = "software_version";
	public final static String NEST_API_SMOKE_CO_STRUCTURE_ID_KEY = "structure_id";
	public final static String NEST_API_SMOKE_CO_NAME_KEY = "name";
	public final static String NEST_API_SMOKE_CO_NAME_LONG_KEY = "name_long";
	public final static String NEST_API_SMOKE_CO_LAST_CONNECTION_KEY = "last_connection";
	public final static String NEST_API_SMOKE_CO_IS_ONLINE_KEY = "is_online";
	public final static String NEST_API_SMOKE_CO_BATTERY_HEALTH_KEY = "battery_health";
	public final static String NEST_API_SMOKE_CO_CO_ALARM_STATE_KEY = "co_alarm_state";
	public final static String NEST_API_SMOKE_CO_SMOKE_ALARM_STATE_KEY = "smoke_alarm_state";
	public final static String NEST_API_SMOKE_CO_UI_COLOR_STATE_KEY = "ui_color_state";
	
	public final static String NEST_API_STRUCT_STRUCTURE_ID_KEY = "structure_id";
	public final static String NEST_API_STRUCT_THERMOSTATS_KEY = "thermostats";
	public final static String NEST_API_STRUCT_SMOKE_CO_ALARMS_KEY = "smoke_co_alarms";
	public final static String NEST_API_STRUCT_AWAY_KEY = "away";
	public final static String NEST_API_STRUCT_NAME_KEY = "name";
	public final static String NEST_API_STRUCT_COUNTRY_CODE_KEY = "country_code";
	public final static String NEST_API_STRUCT_PEAK_PERIOD_START_TIME_KEY = "peak_period_start_time";
	public final static String NEST_API_STRUCT_PEAK_PERIOD_END_TIME_KEY = "peak_period_end_time";
	public final static String NEST_API_STRUCT_TIME_ZONE_KEY = "time_zone";
	public final static String NEST_API_STRUCT_ETA_KEY = "eta";
	public final static String NEST_API_STRUCT_TRIP_ID_KEY = "trip_id";
	public final static String NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_BEGIN_KEY = "estimated_arrival_window_begin";
	public final static String NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_END_KEY = "estimated_arrival_window_end";
	
	public final static String NEST_API_THERMOSTAT_HVAC_MODE_HEAT_KEY = "heat";
	public final static String NEST_API_THERMOSTAT_HVAC_MODE_COOL_KEY = "cool";
	public final static String NEST_API_THERMOSTAT_HVAC_MODE_HEAT_COOL_KEY = "heat-cool";
	public final static String NEST_API_THERMOSTAT_HVAC_MODE_OFF_KEY = "off";

	public final static String NEST_API_THERMOSTAT_TEMP_UNITS_C_KEY = "C";
	public final static String NEST_API_THERMOSTAT_TEMP_UNITS_F_KEY = "F";
	
	public final static String NEST_API_SMOKE_CO_BATT_HEALTH_OK_KEY = "ok";
	public final static String NEST_API_SMOKE_CO_BATT_HEALTH_REPLACE_KEY = "replace";
	
	public final static String NEST_API_SMOKE_CO_ALARM_STATE_OK_KEY = "ok";
	public final static String NEST_API_SMOKE_CO_ALARM_STATE_WARNING_KEY = "warning";
	public final static String NEST_API_SMOKE_CO_ALARM_STATE_EMERGENCY_KEY = "emergency";
	
	public final static String NEST_API_SMOKE_CO_ALARM_UI_COLOR_GREY_KEY = "grey";
	public final static String NEST_API_SMOKE_CO_ALARM_UI_COLOR_GREEN_KEY = "green";
	public final static String NEST_API_SMOKE_CO_ALARM_UI_COLOR_YELLOW_KEY = "yellow";
	public final static String NEST_API_SMOKE_CO_ALARM_UI_COLOR_RED_KEY = "red";

	public final static String NEST_API_STRUCT_TENANT_HOME_KEY = "home";
	public final static String NEST_API_STRUCT_TENANT_AWAY_KEY = "away";
	public final static String NEST_API_STRUCT_TENANT_AUTO_AWAY_KEY = "auto-away";
}
