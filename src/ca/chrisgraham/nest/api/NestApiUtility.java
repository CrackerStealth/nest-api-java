package ca.chrisgraham.nest.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import ca.chrisgraham.nest.api.exception.NestApiParseException;

/**
 * Utility functions for working with the Nest API.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public final class NestApiUtility {
	private final static String UTF_8_ENCODING = "UTF-8";
	
	private final static String HTTP_GET_REQUEST_TYPE = "GET";
	private final static String HTTP_POST_REQUEST_TYPE = "POST";
	private final static String HTTP_PUT_REQUEST_TYPE = "PUT";
	//private final static String HTTP_USER_AGENT = "Mozilla/5.0";
	//private final static String HTTP_ACCEPTED_LANG = "en-US,en;q=0.5"
	
	private final static String NEST_API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	private final static int NEST_API_ISO8601_DATE_FIX_POS = 6;
	private final static String NEST_API_ISO8601_DATE_GMT_STATIC = "Z";
	private final static String NEST_API_DATE_GMT_STATIC_FORMAT = "GMT-00:00";
	
	public static Date parseJsonDate(String date) throws NestApiParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(NEST_API_DATE_FORMAT);
		
		if ( date == null ) return null;
		if ( date.equals("") ) return null;
		  
		if ( date.endsWith( NEST_API_ISO8601_DATE_GMT_STATIC ) ) {
			date = date.substring( 0, date.length() - 1) + NEST_API_DATE_GMT_STATIC_FORMAT;
		} else {
			date = date.substring( 0, date.length() - NEST_API_ISO8601_DATE_FIX_POS ) + "GMT" + date.substring( date.length() - NEST_API_ISO8601_DATE_FIX_POS, date.length() );
		}		
		
		try {
			return dateFormat.parse(date);
		} catch (Exception ex) {
			throw new NestApiParseException("Could not parse ISO 8601 date and time provided: " + ex.getMessage() );
		}
	}
	
	public static boolean isBlank (String value) {
		if ( value == null ) return true;
		if ( value.equals("") ) return true;
		if ( value.replace(" ", "").equals("") ) return true;
		
		return false;
	}
	
	public static boolean isNotBlank (String value) {
		return (!isBlank(value));
	}

	public static String handleHTTPSPut (String url, String body, int timeout) throws IOException, InterruptedException {
		return handleHTTPS(url, HTTP_PUT_REQUEST_TYPE, body, timeout);
	}
	
	public static String handleHTTPSPost (String url, String body, int timeout) throws IOException, InterruptedException {
		return handleHTTPS(url, HTTP_POST_REQUEST_TYPE, body, timeout);
	}
	
	public static String handleHTTPSGet (String url, int timeout) throws IOException, InterruptedException {		
		return handleHTTPS(url, HTTP_GET_REQUEST_TYPE, null, timeout);
	}
	
	public static void addEncodedParameter(StringBuilder sb, String name, String value) throws UnsupportedEncodingException {
		if ( isBlank(value) ) {
			return;
		}
		
		if (sb.length() > 0) {
			sb.append("&");
		}
		
		sb.append(URLEncoder.encode(name, UTF_8_ENCODING));
		sb.append("=");
		sb.append(URLEncoder.encode(value, UTF_8_ENCODING));
	}
	
	public static String readAllContent (Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
    
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}

		return sb.toString();
	}
	
	private static String handleHTTPS (String url, String requestType, String body, int timeout) throws IOException {
		URL httpRequestUrl = new URL(url);
		HttpsURLConnection httpsConnection = (HttpsURLConnection) httpRequestUrl.openConnection();
		
		httpsConnection.setRequestMethod(requestType);
		
		String response = null;

		httpsConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
		httpsConnection.setRequestProperty("Accept", "application/json");
		//httpsConnection.setRequestProperty("User-Agent", HTTP_USER_AGENT);
		//httpsConnection.setRequestProperty("Accept-Language", HTTP_ACCEPTED_LANG);
		
		if ( isNotBlank(body) ) {
			httpsConnection.setDoOutput(true);
			
			OutputStreamWriter writer = new OutputStreamWriter(httpsConnection.getOutputStream());
			writer.append(body);
			writer.flush();
			
			writer.close();
		}
		
		InputStream stream = null;
		
		try {
			stream = httpsConnection.getInputStream();
		} catch (Exception exp) {
			stream = httpsConnection.getErrorStream();
		}
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(stream, Charset.forName(UTF_8_ENCODING)));
		response = NestApiUtility.readAllContent(rd);
		
		rd.close();
		stream.close();
		httpsConnection.disconnect();

		return response;		
	}
}
