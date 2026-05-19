package in.co.rays.proj4.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Controller class to handle new User Registrations.
 * 
 * @author Harshit
 */
@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "Sign Up";

	private static final Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**
	 * Handles HTTP GET requests to display the user registration form. * @param
	 * request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserRegistrationCtl doGet() called, forwarding to view");

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Validates the input data to ensure required fields for a new user
	 * registration are correct. * @param request the HTTP servlet request
	 * 
	 * @return true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl validate() called");

		boolean flag = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First Name is required.");
			flag = false;
			log.warn("Validation failed: firstName is null");
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First Name is in invalid format");
			flag = false;
			log.warn("Validation failed: firstName invalid");
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required.");
			flag = false;
			log.warn("Validation failed: lastName is null");
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is in invalid format");
			flag = false;
			log.warn("Validation failed: lastName invalid");
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required.");
			flag = false;
			log.warn("Validation failed: login is null");
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is in invalid format");
			flag = false;
			log.warn("Validation failed: login not a valid email");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			flag = false;
			log.warn("Validation failed: password is null");
		} else if (!DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("password", "password length must be 8 - 12");
			flag = false;
			log.warn("Validation failed: password length invalid");
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Must contain uppercase, lowercase, digit & special character");
			flag = false;
			log.warn("Validation failed: password strength invalid");
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			flag = false;
			log.warn("Validation failed: confirmPassword is null");
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			flag = false;
			log.warn("Validation failed: gender is null");
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			flag = false;
			log.warn("Validation failed: dob is null");
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", "Invalid date of birth");
			flag = false;
			log.warn("Validation failed: dob invalid date");
		}

		if (!request.getParameter("password").equalsIgnoreCase(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Password and confirm password must be same!");
			flag = false;
			log.warn("Validation failed: password and confirmPassword mismatch");
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			flag = false;
			log.warn("Validation failed: mobileNo is null");
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			flag = false;
			log.warn("Validation failed: mobileNo length invalid");
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			flag = false;
			log.warn("Validation failed: mobileNo invalid");
		}

		return flag;
	}

	/**
	 * Populates the UserBean from the incoming request parameters during
	 * registration. Default role for newly registered users is set to STUDENT.
	 * * @param request the HTTP servlet request
	 * 
	 * @return the populated BaseBean object representing a new user
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserRegistrationCtl populateBean() called");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setRoleId(RoleBean.STUDENT);

		log.info("Populated UserBean for registration: " + bean.getLogin());
		return bean;
	}

	/**
	 * Handles HTTP POST requests for saving new user registration data or resetting
	 * the form. * @param req the HTTP servlet request
	 * 
	 * @param resp the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.debug("UserRegistrationCtl doPost() called");

		String op = DataUtility.getString(req.getParameter("operation"));

		UserModel model = new UserModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			log.info("Sign Up operation triggered");
			UserBean bean = (UserBean) populateBean(req);

			try {
				long pk = model.registerUser(bean);
				ServletUtility.setBean(bean, req);
				// request.setAttribute("success", "Registeration successfully");
				ServletUtility.setSuccessMessage("Registration successful!", req);
				log.info("User registered successfully: " + bean.getLogin());

			} catch (DuplicateRecordException e) {
				log.warn("Duplicate login during registration: " + bean.getLogin());
				ServletUtility.setBean(bean, req);
				// request.setAttribute("error", Login id already exists");
				ServletUtility.setErrorMessage("Login id already exists", req);

			} catch (ApplicationException e) {
				log.error("ApplicationException in doPost() during registration", e);
				e.printStackTrace();

			}
			ServletUtility.forward(getView(), req, resp);

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			log.info("Reset operation triggered, redirecting to registration page");
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, req, resp);
			return;
		}

	}

	/**
	 * Returns the specific view corresponding to the user registration page.
	 * * @return a string representing the view path
	 */
	@Override
	protected String getView() {
		log.debug("Returning UserRegistration view page");
		return ORSView.USER_REGISTRATION_VIEW;
	}

}