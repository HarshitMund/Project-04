package in.co.rays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.EventModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "EventCtl", urlPatterns = { "/ctl/EventCtl" })
public class EventCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("eventName"))) {
			request.setAttribute("eventName", PropertyReader.getValue("error.require", "Event Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("organizerName"))) {
			request.setAttribute("organizerName", PropertyReader.getValue("error.require", "Organizer Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("venue"))) {
			request.setAttribute("venue", PropertyReader.getValue("error.require", "Venue"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("budget"))) {
			request.setAttribute("budget", PropertyReader.getValue("error.require", "Budget"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		EventBean bean = new EventBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setEventName(DataUtility.getString(request.getParameter("eventName")));
		bean.setOrganizerName(DataUtility.getString(request.getParameter("organizerName")));
		bean.setVenue(DataUtility.getString(request.getParameter("venue")));

		String budgetStr = request.getParameter("budget");
		if (DataValidator.isNotNull(budgetStr)) {
			bean.setBudget(Double.parseDouble(budgetStr));
		}

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));
		EventModel model = new EventModel();

		if (id > 0) {
			try {
				EventBean bean = model.findById(id);
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
		EventModel model = new EventModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			EventBean bean = (EventBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Event added successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Event Name" + bean.getEventName() + "already Exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			EventBean bean = (EventBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Event updated successfully", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Event Name already Exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.EVENT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.EVENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.EVENT_VIEW;
	}
}