package ca.chrisgraham.nest.api.structure;

import java.util.Date;

import org.json.JSONObject;

import ca.chrisgraham.nest.api.NestApi;
import ca.chrisgraham.nest.api.NestApiDeviceInterface;
import ca.chrisgraham.nest.api.NestApiUtility;

import ca.chrisgraham.nest.api.exception.NestApiException;
import ca.chrisgraham.nest.api.exception.NestApiParseException;

import static ca.chrisgraham.nest.api.NestApiKeyConstants.*;

/**
 * Custom class for holding all properties about a Nest Structures ETA
 * section within the Nest Structure in the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class StructureEta extends NestApiDeviceInterface<StructureEta> {
	private String tripId = null;
	private Date estimatedArrivalWindowBegin = null;
	private Date estimatedArrivalWindowEnd = null;
	
	public StructureEta (NestApi apiAccess) throws NestApiException {
		super(apiAccess);
	}
	
	public String getType () {
		return NEST_API_STRUCT_ETA_KEY;
	}

	public String getId () {
		return this.tripId;
	}
	
	public Object getParameterValueByName (String parameterName) throws NestApiParseException {
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

	public String getJson () {
		JSONObject json = new JSONObject();

		json.put(NEST_API_STRUCT_TRIP_ID_KEY, this.tripId);
		json.put(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_BEGIN_KEY, this.estimatedArrivalWindowBegin);
		json.put(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_END_KEY, this.estimatedArrivalWindowEnd);
		
		return json.toString();
	}
	
	public void parseJson (String jsonString) throws NestApiParseException {
		JSONObject json = new JSONObject(jsonString);
		
		this.tripId = json.getString(NEST_API_STRUCT_TRIP_ID_KEY);
		this.estimatedArrivalWindowBegin = NestApiUtility.parseJsonDate(json.getString(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_BEGIN_KEY));
		this.estimatedArrivalWindowEnd = NestApiUtility.parseJsonDate(json.getString(NEST_API_STRUCT_ESTIMATED_ARRIVAL_WINDOW_END_KEY));
	}

	@Override
    public int compareTo (StructureEta o) {
        return this.getTripId().compareTo(o.getTripId());
    }
	
	/**
	 * @return the tripId
	 */
	public String getTripId () {
		return tripId;
	}
	
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId (String tripId) {
		this.tripId = tripId;
	}
	
	/**
	 * @return the estimatedArrivalWindowBegin
	 */
	public Date getEstimatedArrivalWindowBegin () {
		return estimatedArrivalWindowBegin;
	}
	
	/**
	 * @param estimatedArrivalWindowBegin the estimatedArrivalWindowBegin to set
	 */
	public void setEstimatedArrivalWindowBegin (Date estimatedArrivalWindowBegin) {
		this.estimatedArrivalWindowBegin = estimatedArrivalWindowBegin;
	}
	
	/**
	 * @return the estimatedArrivalWindowEnd
	 */
	public Date getEstimatedArrivalWindowEnd () {
		return estimatedArrivalWindowEnd;
	}
	
	/**
	 * @param estimatedArrivalWindowEnd the estimatedArrivalWindowEnd to set
	 */
	public void setEstimatedArrivalWindowEnd (Date estimatedArrivalWindowEnd) {
		this.estimatedArrivalWindowEnd = estimatedArrivalWindowEnd;
	}
}
