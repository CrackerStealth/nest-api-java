package ca.chrisgraham.nest.api.exception;

/**
 * Custom exception to throw for the Nest API library structure.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class NestApiException extends Exception {
	/**
	 * Static serial id for this custom exception.
	 */
	private static final long serialVersionUID = -3439468514404998193L;

	public NestApiException (String message) {
		super(message);
	}
}
