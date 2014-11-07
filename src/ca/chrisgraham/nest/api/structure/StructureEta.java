package ca.chrisgraham.nest.api.structure;

import java.util.Date;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.NestApiDeviceInterface;
import ca.chrisgraham.nest.api.NestApiUtility;

import ca.chrisgraham.nest.api.exception.NestApiParseException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

/**
 * Custom class for holding all properties about a Nest Structures ETA
 * section within the Nest Structure in the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class StructureEta {
	private String tripId = null;
	private Date estimatedArrivalWindowBegin = null;
	private Date estimatedArrivalWindowEnd = null;

	private boolean tripIdChanged = false;
	private boolean estimatedArrivalWindowBeginChanged = false;
	private boolean estimatedArrivalWindowEndChanged = false;
	
	public StructureEta (String jsonString) throws NestApiParseException {
		if ( NestApiUtility.isNotBlank(jsonString) ) { 
			parseJson (jsonString);
		}
	}
	
	public String getType() {
		return NEST_API_STRUCT_ETA_KEY;
	}

	public String getId () {
		return this.tripId;
	}
	
	public Object getParameterValueByName(String parameterName) throws NestApiParseException {
		if ( parameterName.equals(NEST_API_STRUCT_TRIP_ID_KEY) ) {
			return this.tripId;
		} else if ( parameterName.equals(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_BEGIN_KEY) ) {
			return this.estimatedArrivalWindowBegin;
		} else if ( parameterName.equals(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_END_KEY) ) {
			return this.estimatedArrivalWindowEnd;
		} else {
			throw new NestApiParseException("The parameter name '" + parameterName + "' does not exist in the Nest Structure Eta object.");
		}
	}

	public void parseJson(String jsonString) throws NestApiParseException {
		JSONObject json = new JSONObject(jsonString);
		
		this.tripId = json.getString(NEST_API_STRUCT_TRIP_ID_KEY);
		this.estimatedArrivalWindowBegin = NestApiUtility.parseJsonDate(json.getString(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_BEGIN_KEY));
		this.estimatedArrivalWindowEnd = NestApiUtility.parseJsonDate(json.getString(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_END_KEY));
	}

	public String formatChangedJson() {
		JSONObject json = new JSONObject();
		
		if ( tripIdChanged ) {
			json.put(NEST_API_STRUCT_TRIP_ID_KEY, this.tripId);
		}
		
		if ( estimatedArrivalWindowBeginChanged ) {
			json.put(NEST_API_STRUCT_TRIP_ID_KEY, this.estimatedArrivalWindowBegin);
		}
		
		if ( estimatedArrivalWindowEndChanged ) {
			json.put(NEST_API_STRUCT_TRIP_ID_KEY, this.estimatedArrivalWindowEnd);
		}
		
		return json.toString();
	}

	public boolean isChanged() {
		return ( tripIdChanged || estimatedArrivalWindowBeginChanged || estimatedArrivalWindowEndChanged );
	}
	
	public int compareTo(NestApiDeviceInterface o) {
		return this.getId().compareTo(o.getId());
	}
	
	/**
	 * @return the tripId
	 */
	public String getTripId() {
		return tripId;
	}
	
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(String tripId) {
		this.tripIdChanged = true;
		this.tripId = tripId;
	}
	
	/**
	 * @return the estimatedArrivalWindowBegin
	 */
	public Date getEstimatedArrivalWindowBegin() {
		return estimatedArrivalWindowBegin;
	}
	
	/**
	 * @param estimatedArrivalWindowBegin the estimatedArrivalWindowBegin to set
	 */
	public void setEstimatedArrivalWindowBegin(Date estimatedArrivalWindowBegin) {
		this.estimatedArrivalWindowBeginChanged = true;
		this.estimatedArrivalWindowBegin = estimatedArrivalWindowBegin;
	}
	
	/**
	 * @return the estimatedArrivalWindowEnd
	 */
	public Date getEstimatedArrivalWindowEnd() {
		return estimatedArrivalWindowEnd;
	}
	
	/**
	 * @param estimatedArrivalWindowEnd the estimatedArrivalWindowEnd to set
	 */
	public void setEstimatedArrivalWindowEnd(Date estimatedArrivalWindowEnd) {
		this.estimatedArrivalWindowEndChanged = true;
		this.estimatedArrivalWindowEnd = estimatedArrivalWindowEnd;
	}
}
