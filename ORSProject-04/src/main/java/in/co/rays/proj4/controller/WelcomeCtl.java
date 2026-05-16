package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.util.ServletUtility;

/**
 * Controller class to handle forwarding to the Welcome page after successful login.
 * * @author Harshit
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	/**
	 * Handles HTTP GET requests to display the welcome view.
	 * * @param req  the HTTP servlet request
	 * @param resp the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtility.forward(getView(), req, resp);
	}

	/**
	 * Returns the specific view corresponding to the welcome page.
	 * * @return a string representing the view path
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}