package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Controller class to handle User Login, Sign Up, and Logout operations.
 * @author Harshit
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl {

	public static final String OP_SIGN_IN = "Sign In";
	public static final String OP_SIGN_UP = "Sign Up";
	public static final String OP_LOG_OUT = "Logout";

	/**
	 * Validates the input data to ensure login and password are provided and correctly formatted.
	 * * @param request the HTTP servlet request
	 * @return true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean flag = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SIGN_UP.equalsIgnoreCase(op) || OP_LOG_OUT.equalsIgnoreCase(op)) {
			return flag;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "Login is required.");
			flag = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "Login is invalid");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			flag = false;
		}
		return flag;

	}

	/**
	 * Populates the UserBean from the incoming request parameters.
	 * * @param request the HTTP servlet request
	 * @return the populated BaseBean object representing a user
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getStringData(request.getParameter("login")));
		bean.setPassword(DataUtility.getStringData(request.getParameter("password")));

		return bean;

	}

	/**
	 * Handles HTTP GET requests, primarily used to perform logout functionality.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equalsIgnoreCase(op)) {
			HttpSession session = request.getSession();
			session.invalidate();
			ServletUtility.setSuccessMessage("Logout Successfully!", request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests for authenticating the user and processing sign in or sign up.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();
		RoleModel roleModel = new RoleModel();
		HttpSession session = request.getSession();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					session.setAttribute("user", bean);
					RoleBean roleBean = roleModel.findByPk(bean.getRoleId());
					if (roleBean != null) {
						session.setAttribute("role", roleBean.getName());
					}

					ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
					return;

				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid Login or Password", request);
					ServletUtility.forward(getView(), request, response);
					return;
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the specific view corresponding to the login page.
	 * * @return a string representing the view path
	 */
	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}