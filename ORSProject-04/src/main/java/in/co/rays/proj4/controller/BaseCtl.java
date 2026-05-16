package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Base controller class abstracting common servlet operations.
 * It provides boilerplate implementations for validation, preloading,
 * and DTO population that other controllers can extend and override.
 * 
 * @author Harshit
 */
public abstract class BaseCtl extends HttpServlet {

	// Common Buttons
	public static final String OP_SAVE = "Save";
	public static final String OP_UPDATE = "Update";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_RESET = "Reset";
	public static final String OP_LOG_OUT = "Logout";

	// Common Keys
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by the user.
	 * * @param request the HTTP servlet request
	 * @return true if validation passes, false otherwise
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads required data into the request before forwarding to the view.
	 * * @param request the HTTP servlet request
	 */
	protected void preload(HttpServletRequest request) {

	}

	/**
	 * Populates the generic bean from request parameters.
	 * * @param httpServletRequest the HTTP servlet request
	 * @return the populated BaseBean object
	 */
	protected BaseBean populateBean(HttpServletRequest httpServletRequest) {
		return null;
	}

	/**
	 * Populates the common auditing fields (CreatedBy, ModifiedBy, CreatedDatetime, ModifiedDatetime) 
	 * into the DTO from the request and session data.
	 * * @param dto     the BaseBean data transfer object
	 * @param request the HTTP servlet request
	 * @return the populated BaseBean object with tracking details
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Intercepts the HTTP request to perform common operations such as preloading 
	 * data and validating the request based on the triggered operation before 
	 * delegating to doGet or doPost.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println("OP ===> " + op);

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)
				&& !OP_VIEW.equalsIgnoreCase(op) && !OP_DELETE.equalsIgnoreCase(op)) {
			if (validate(request) == false) {
				BaseBean bean = populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}

		super.service(request, response);
	}

	/**
	 * Returns the view page (JSP) associated with the specific controller.
	 * * @return a string representing the view path
	 */
	protected abstract String getView();

}