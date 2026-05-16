package in.co.rays.proj4.exception;

/**
 * ApplicationException is thrown when a general higher-level application 
 * business logic error or runtime obstruction occurs.
 * @author Harshit
 */
public class ApplicationException extends Exception {

	/**
	 * Constructs an {@code ApplicationException} with the specified detail message.
	 * * @param msg the detail message describing the specific error
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
}