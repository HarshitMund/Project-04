package in.co.rays.proj4.exception;

/**
 * RecordNotFoundException is thrown when a requested data record 
 * cannot be located or does not exist within the persistence layer.
 * * @author Harshit
 */
public class RecordNotFoundException extends Exception {

	/**
	 * Constructs a {@code RecordNotFoundException} with the specified detail message.
	 * * @param msg the detail message specifying the missing record context
	 */
	public RecordNotFoundException(String msg) {
		super(msg);
	}
}