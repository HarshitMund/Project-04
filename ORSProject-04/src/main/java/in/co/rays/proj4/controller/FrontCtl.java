package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import in.co.rays.proj4.util.ServletUtility;

/**
 * Front Controller filter to handle session management and authentication checks
 * before allowing access to secure application resources.
 * @author Harshit
 */
@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontCtl implements Filter {

	/**
	 * Initializes the filter configuration.
	 * * @param filterConfig the filter configuration object
	 * @throws ServletException if an error occurs during initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * Intercepts requests to check for a valid user session. Redirects to login 
	 * if the session has expired or the user is not authenticated.
	 * * @param request  the servlet request
	 * @param response the servlet response
	 * @param chain    the filter chain
	 * @throws IOException      if an I/O error occurs
	 * @throws ServletException if a servlet-specific error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			ServletUtility.setErrorMessage("Your session has been expired... Please Login Again", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, resp);
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * Called by the web container to indicate to a filter that it is being taken out of service.
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}