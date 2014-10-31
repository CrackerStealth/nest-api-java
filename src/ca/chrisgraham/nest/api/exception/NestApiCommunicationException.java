package ca.chrisgraham.nest.api.exception;

/**
 * Custom exception to throw for the Nest API library structure.
 * 
 * @author Chris Graham
 * @since 0.0.1
 */
public class NestApiCommunicationException extends NestApiException {
	/**
	 * Static serial id for this custom exception.
	 */
	private static final long serialVersionUID = 6085932695686268576L;

	public NestApiCommunicationException (String message) {
		super(message);
	}
}
