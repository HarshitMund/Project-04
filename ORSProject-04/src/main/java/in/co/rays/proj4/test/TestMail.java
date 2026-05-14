package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestMail {

	public static void main(String[] args) throws ApplicationException {

//		testUserRegistrationMail();
//		testForgetPasswordMail();
		testChangePasswordMail();

	}

	private static void testUserRegistrationMail() throws ApplicationException {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", "mndharshit@gmail.com");
		map.put("password", "Pass@123");

		String msg = EmailBuilder.getUserRegistrationMessage(map);

		System.out.println(msg);

		EmailMessage message = new EmailMessage();

		message.setTo("mndharshit@gmail.com");
		message.setSubject("User Reqistration Success");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(message);

		System.out.println("Message sent successfully");

	}

	private static void testForgetPasswordMail() throws ApplicationException {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("firstName", "Harshit");
		map.put("lastName", "Mund");
		map.put("login", "mndharshit@gmail.com");
		map.put("password", "Pass@123");

		String msg = EmailBuilder.getForgetPasswordMessage(map);

		System.out.println(msg);

		EmailMessage message = new EmailMessage();

		message.setTo("mndharshit@gmail.com");
		message.setSubject("Forget Password");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(message);

		System.out.println("Message sent successfully");

	}

	private static void testChangePasswordMail() throws ApplicationException {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("firstName", "Harshit");
		map.put("lastName", "Mund");
		map.put("login", "mndharshit@gmail.com");
		map.put("password", "Pass@123");

		String msg = EmailBuilder.getChangePasswordMessage(map);

		System.out.println(msg);

		EmailMessage message = new EmailMessage();

		message.setTo("mndharshit@gmail.com");
		message.setSubject("Change Password");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(message);

		System.out.println("Message sent successfully");

	}
}
