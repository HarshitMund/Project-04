package in.co.rays.proj4.exception;

/**
 * DatabaseException is thrown when a failure or error occurs during 
 * low-level database processing operations, such as query failures or connectivity losses.
 * @author Harshit
 */
public class DatabaseException extends Exception {

	/**
	 * Constructs a {@code DatabaseException} with the specified detail message.
	 * * @param msg the detail message describing the database system error
	 */
	public DatabaseException(String msg) {
		super(msg);
	}
}