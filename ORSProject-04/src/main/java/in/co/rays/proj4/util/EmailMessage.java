package in.co.rays.proj4.util;

/**
 * Data Transfer Object containment model grouping transaction parameters for Email deliveries.
 * * @author Harshit
 */
public class EmailMessage {

	/** Recipient addresses */
	private String to;
	/** Email subject title */
	private String subject;
	/** Principal text body structure payload */
	private String message;
	/** Message content configuration protocol (defaults to {@link #TEXT_MSG}) */
	private int messageType = TEXT_MSG;

	/** Flag integer specifying HTML context format */
	public static final int HTML_MSG = 1;
	/** Flag integer specifying raw text format */
	public static final int TEXT_MSG = 2;

	/**
	 * Default zero-argument constructor.
	 */
	public EmailMessage() {
	}

	/**
	 * Parameterized constructor initialization.
	 * * @param to recipient email
	 * @param subject message title
	 * @param message message main payload string
	 */
	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * Sets recipient field.
	 * * @param to destination mail address
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Gets recipient field.
	 * * @return destination mail address
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets mail subject.
	 * * @param subject mail subject line
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets mail subject.
	 * * @return context subject text string
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the email message body.
	 * * @param message message content
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the email message body.
	 * * @return message body string
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets messaging content format mode (HTML vs TEXT).
	 * * @param messageType identification type code
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/**
	 * Gets messaging content format type code.
	 * * @return integer type identifier
	 */
	public int getMessageType() {
		return messageType;
	}
}