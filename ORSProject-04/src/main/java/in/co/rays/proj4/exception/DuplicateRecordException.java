package in.co.rays.proj4.exception;

/**
 * DuplicateRecordException is thrown when an operation attempts to insert 
 * or register data that already exists within the application's unique records database.
 * * @author Harshit
 */
public class DuplicateRecordException extends Exception {

	/**
	 * Constructs a {@code DuplicateRecordException} with the specified detail message.
	 * * @param msg the detail message describing the data duplication conflict
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}