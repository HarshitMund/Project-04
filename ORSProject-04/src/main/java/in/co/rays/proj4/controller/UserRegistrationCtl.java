package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

public class UserRegistrationCtl extends BaseCtl {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean flag = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required.");
			flag = false;
		} else if (DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is in invalid format");
			flag = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required.");
			flag = false;
		} else if (DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is in invalid format");
			flag = false;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required.");
			flag = false;
		} else if (DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is in invalid format");
			flag = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			flag = false;
		} else if (DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("password", "password length must be 8 - 12");
			flag = false;
		} else if (DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Must contain uppercase, lowercase, digit & special character");
			flag = false;
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			flag = false;
		}

		return super.validate(request);
	}

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}
