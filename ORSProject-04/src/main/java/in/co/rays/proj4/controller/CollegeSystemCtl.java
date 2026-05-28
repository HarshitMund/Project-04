package in.co.rays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeSystemBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeSystemModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CollegeSystemCtl", urlPatterns = { "/ctl/CollegeSystemCtl" })
public class CollegeSystemCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("studentName"))) {
			request.setAttribute("studentName", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		} else if(!DataValidator.isName(request.getParameter("studentName")))

		if (DataValidator.isNull(request.getParameter("branch"))) {
			request.setAttribute("branch", PropertyReader.getValue("error.require", "Branch"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("semester"))) {
			request.setAttribute("semester", "Semester value must be an integer");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cgpa"))) {
			request.setAttribute("cgpa", PropertyReader.getValue("error.require", "CGPA"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CollegeSystemBean bean = new CollegeSystemBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setStudentName(DataUtility.getString(request.getParameter("studentName")));
		bean.setBranch(DataUtility.getString(request.getParameter("branch")));

		String semStr = request.getParameter("semester");
		if (DataValidator.isNotNull(semStr)) {
			bean.setSemester(DataUtility.getInt(semStr));
		}

		String cgpaStr = request.getParameter("cgpa");
		if (DataValidator.isNotNull(cgpaStr)) {
			bean.setCgpa(Double.parseDouble(cgpaStr));
		}

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));
		CollegeSystemModel model = new CollegeSystemModel();

		if (id > 0) {
			try {
				CollegeSystemBean bean = model.findById(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		CollegeSystemModel model = new CollegeSystemModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			CollegeSystemBean bean = (CollegeSystemBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Record added successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Student Name " + bean.getStudentName() + " already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			CollegeSystemBean bean = (CollegeSystemBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Record updated successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Student Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_SYSTEM_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_SYSTEM_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_SYSTEM_VIEW;
	}
}