package ca.chrisgraham.nest.api.exception;

/**
 * Custom exception to throw for the Nest API library structure.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class NestApiParseException extends NestApiException {
	/**
	 * Static serial id for this custom exception.
	 */
	private static final long serialVersionUID = 7629990329912071629L;

	public NestApiParseException (String message) {
		super(message);
	}
}
