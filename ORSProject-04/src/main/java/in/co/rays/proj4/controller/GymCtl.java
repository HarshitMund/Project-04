package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.GymBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.GymModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "GymCtl", urlPatterns = { "/ctl/GymCtl" })
public class GymCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("memberName"))) {
			request.setAttribute("memberName", PropertyReader.getValue("error.require", "Member Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("memberName"))) {
			request.setAttribute("memberName", "Name is invalid");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("trainerName"))) {
			request.setAttribute("trainerName", PropertyReader.getValue("error.require", "Trainer Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("trainerName"))) {
			request.setAttribute("trainerName", "Name is invalid");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("fee"))) {
			request.setAttribute("fee", PropertyReader.getValue("error.require", "Fee"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "Joining Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", "Joining Date is invalid");
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		GymBean bean = new GymBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setMemberName(DataUtility.getString(request.getParameter("memberName")));
		bean.setTrainerName(DataUtility.getString(request.getParameter("trainerName")));
		bean.setFee(Double.parseDouble(request.getParameter("fee")));
		bean.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		GymModel model = new GymModel();

		if (id > 0) {
			try {
				GymBean bean = model.findById(id);
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

		GymModel model = new GymModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			GymBean bean = (GymBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Gym Membership added successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Member Name already Exist", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			GymBean bean = (GymBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Gym Membership added successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Member Name already Exist", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect("/ORSProject-04/GymListCtl", request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect("/ORSProject-04/GymCtl", request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.GYM_VIEW;
	}

}
