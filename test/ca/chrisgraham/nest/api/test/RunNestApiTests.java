package ca.chrisgraham.nest.api.test;

import ca.chrisgraham.nest.api.*;
import ca.chrisgraham.nest.api.device.SmokeCoAlarm;
import ca.chrisgraham.nest.api.device.Thermostat;
import ca.chrisgraham.nest.api.structure.Structure;

public class RunNestApiTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String token = "";
		
		NestApi nestApi = new NestApi(token);
		
		try {
			nestApi.refresh();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		
		System.out.println("====");
		System.out.println("Thermostats:");
		
		for ( String id : nestApi.getThermostatIdList() ) {
			System.out.println("----");
			
			Thermostat therm = nestApi.getThermostatById(id);
			
			System.out.println(therm.getId());
			System.out.println(therm.getDeviceId());
			System.out.println(therm.getLocale());
			System.out.println(therm.getSoftwareVersion());
			System.out.println(therm.getName());
			System.out.println(therm.getNameLong());
			System.out.println(therm.getLastConnection());
			System.out.println(therm.getIsOnline());
			System.out.println(therm.getCanCool());
			System.out.println(therm.getCanHeat());
			System.out.println(therm.getIsUsingEmergencyHeat());
			System.out.println(therm.getHasFan());
			System.out.println(therm.getFanTimerActive());
			System.out.println(therm.getFanTimerTimeout());
			System.out.println(therm.getHasLeaf());
			System.out.println(therm.getHvacMode());
			System.out.println(therm.getTemperatureScale());
			System.out.println(therm.getTargetTemperatureF());
			System.out.println(therm.getTargetTemperatureC());
			System.out.println(therm.getTargetTemperatureHighF());
			System.out.println(therm.getTargetTemperatureHighC());
			System.out.println(therm.getTargetTemperatureLowF());
			System.out.println(therm.getTargetTemperatureLowC());
			System.out.println(therm.getAwayTemperatureHighF());
			System.out.println(therm.getAwayTemperatureHighC());
			System.out.println(therm.getAwayTemperatureLowF());
			System.out.println(therm.getAwayTemperatureLowC());
			System.out.println(therm.getAmbientTemperatureF());
			System.out.println(therm.getAmbientTemperatureC());
			System.out.println(therm.getHumidity());
		}

		System.out.println("====");
		System.out.println("Smoke CO Alarms:");
		
		for ( String id : nestApi.getSmokeCoAlarmIdList() ) {
			System.out.println("----");
			
			SmokeCoAlarm alarm = nestApi.getSmokeCoAlarmById(id);
			
			System.out.println(alarm.getId());
			System.out.println(alarm.getDeviceId());
			System.out.println(alarm.getLocale());
			System.out.println(alarm.getSoftwareVersion());
			System.out.println(alarm.getName());
			System.out.println(alarm.getNameLong());
			System.out.println(alarm.getLastConnection());
			System.out.println(alarm.getIsOnline());
			System.out.println(alarm.getBatteryHealth());
			System.out.println(alarm.getCoAlarmState());
			System.out.println(alarm.getSmokeAlarmState());
			System.out.println(alarm.getUiColorState());
			System.out.println(alarm.getIsManualTestActive());
			System.out.println(alarm.lastManualTestTime);
		}
		
		System.out.println("====");
		System.out.println("Structures:");
		
		for ( String id : nestApi.getStructureIdList() ) {
			System.out.println("----");
			
			Structure struct = nestApi.getStructureById(id);
			
			System.out.println(struct.getId());
			System.out.println(struct.getStructureId());
			System.out.println(struct.getAway());
			System.out.println(struct.getName());
			System.out.println(struct.getCountryCode());
			System.out.println(struct.getPeakPeriodStartTime());
			System.out.println(struct.getPeakPeriodEndTime());
			System.out.println(struct.getTimeZone());
			System.out.println(struct.getPostalCode());
		}
		
		System.out.println("====");
		
		try {
			Thermostat therm = nestApi.getThermostatById((nestApi.getThermostatIdList()[0]));
			therm.setTargetTemperatureC(19.0);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}
