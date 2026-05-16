package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Controller class responsible for handling operations related to fetching a
 * specific Marksheet by its Roll Number.
 * 
 * @author Harshit
 */
@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/ctl/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {

	/**
	 * Validates the input data submitted from the user interface request. * @param
	 * request the HTTP request object containing user inputs
	 * 
	 * @return true if data validation succeeds, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a {@link MarksheetBean} object from the parameters received in the
	 * request. * @param request the HTTP request object containing parameter inputs
	 * 
	 * @return the populated BaseBean wrapper object containing operational data
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		return bean;
	}

	/**
	 * Handles HTTP GET requests to forward the request to the corresponding viewing
	 * component. * @param request the HTTP request context object
	 * 
	 * @param response the HTTP response context object
	 * @throws ServletException if a servlet-specific error occurs during processing
	 * @throws IOException      if an input or output exception is encountered
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests to perform the lookup logic for a marksheet record
	 * using business models. * @param request the HTTP request context object
	 * 
	 * @param response the HTTP response context object
	 * @throws ServletException if a servlet-specific error occurs during processing
	 * @throws IOException      if an input or output exception is encountered
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		MarksheetModel model = new MarksheetModel();

		MarksheetBean bean = (MarksheetBean) populateBean(request);

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				bean = model.findByRollNo(bean.getRollNo());
				if (bean != null) {
					ServletUtility.setBean(bean, request);
				} else {
					ServletUtility.setErrorMessage("RollNo Does Not exists", request);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the destination view page path mapped for this controller. * @return
	 * the explicit view page path string constant
	 */
	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}
}