package ca.chrisgraham.nest.api.exception;

/**
 * Custom exception to throw for the Nest API library structure.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class NestApiValidationException extends NestApiException {
	/**
	 * Static serial id for this custom exception.
	 */
	private static final long serialVersionUID = 7717443162786480038L;

	public NestApiValidationException (String message) {
		super(message);
	}
}