package in.co.rays.proj4.util;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import in.co.rays.proj4.exception.ApplicationException;

/**
 * Utility handling mail transfers through an external SMTP network layout.
 * @author Harshit
 */
public class EmailUtility {

	/** ResourceBundle tracking network configuration references */
	static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");

	/** SMTP Server domain property */
	private static final String SMTP_HOST_NAME = rb.getString("smtp.server");
	/** Target routing server port connection flag */
	private static final String SMTP_PORT = rb.getString("smtp.port");
	/** System transactional delivery sender address */
	private static final String emailFromAddress = rb.getString("email.login");
	/** System authentication account token password */
	private static final String emailPassword = rb.getString("email.pwd");

	/** Mail server protocol parameters payload object */
	private static Properties props = new Properties();

	static {
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	}

	/**
	 * Dispatches mail packages based on values derived inside an {@link EmailMessage} container.
	 * * @param emailMessageDTO email contents parameters package to deliver
	 * @throws ApplicationException thrown if server transmissions drop or encounter faults
	 */
	public static void sendMail(EmailMessage emailMessageDTO) throws ApplicationException {
		try {
			// Setup mail session
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFromAddress, emailPassword);
				}
			});

			// Create and setup the email message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailFromAddress));
			msg.setRecipients(Message.RecipientType.TO, getInternetAddresses(emailMessageDTO.getTo()));
			msg.setSubject(emailMessageDTO.getSubject());

			// Set content type based on message type
			String contentType = emailMessageDTO.getMessageType() == EmailMessage.HTML_MSG ? "text/html" : "text/plain";
			msg.setContent(emailMessageDTO.getMessage(), contentType);

			// Send the email
			Transport.send(msg);

		} catch (Exception ex) {
			throw new ApplicationException("Email Error: " + ex.getMessage());
		}
	}

	/**
	 * Compiles comma-separated textual mail listings into a structural sequence of InternetAddress instances.
	 * * @param emails raw CSV string of emails
	 * @return formatted InternetAddress tracking arrays
	 * @throws Exception thrown if address formatting patterns mismatch standard formatting
	 */
	private static InternetAddress[] getInternetAddresses(String emails) throws Exception {
		if (emails == null || emails.isEmpty()) {
			return new InternetAddress[0];
		}
		String[] emailArray = emails.split(",");
		InternetAddress[] addresses = new InternetAddress[emailArray.length];
		for (int i = 0; i < emailArray.length; i++) {
			addresses[i] = new InternetAddress(emailArray[i].trim());
		}
		return addresses;
	}
}