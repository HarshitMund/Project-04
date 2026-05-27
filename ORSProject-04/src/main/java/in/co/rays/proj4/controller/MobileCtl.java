package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.MobileBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.MobileModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "MobileCtl", urlPatterns = { "/ctl/MobileCtl" })
public class MobileCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("beandName"))) {
			request.setAttribute("beandName", PropertyReader.getValue("error.require", "Brand Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileName"))) {
			request.setAttribute("mobileName", PropertyReader.getValue("error.require", "Mobile Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("raw"))) {
			request.setAttribute("raw", PropertyReader.getValue("error.require", "RAM/Raw"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("raw"))) {
			request.setAttribute("raw", "RAM value must be an integer");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		MobileBean bean = new MobileBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBeandName(DataUtility.getString(request.getParameter("beandName")));
		bean.setMobileName(DataUtility.getString(request.getParameter("mobileName")));

		String rawStr = request.getParameter("raw");
		if (DataValidator.isNotNull(rawStr)) {
			bean.setRaw(DataUtility.getInt(rawStr));
		}

		String priceStr = request.getParameter("price");
		if (DataValidator.isNotNull(priceStr)) {
			bean.setPrice(Double.parseDouble(priceStr));
		}

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		MobileModel model = new MobileModel();

		if (id > 0) {
			try {
				MobileBean bean = model.findById(id);
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

		MobileModel model = new MobileModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			MobileBean bean = (MobileBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Mobile details added successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Mobile Name already Exist", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			MobileBean bean = (MobileBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Mobile details updated successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Mobile Name already Exist", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MOBILE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MOBILE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.MOBILE_VIEW;
	}

}