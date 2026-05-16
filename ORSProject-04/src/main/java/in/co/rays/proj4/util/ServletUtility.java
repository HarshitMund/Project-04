package in.co.rays.proj4.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.controller.BaseCtl;
import in.co.rays.proj4.controller.ORSView;

/**
 * Controller-level helper utility class handling context storage operations, forward actions, and redirection pathways.
 * @author Harshit
 */
public class ServletUtility {

	/**
	 * Server-side internal routing action using RequestDispatcher forwards.
	 * * @param page destination page path
	 * @param request HTTP request container tracking states attributes
	 * @param response HTTP server delivery channels controller pipeline
	 * @throws IOException pipeline tracking input output exception drops
	 * @throws ServletException servlet standard engine structural error states indicators
	 */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * Performs standard HTTP client-side page redirection sequence routines.
	 * * @param page destination target route URL path string location
	 * @param request active network processing query context parameter package
	 * @param response client processing destination delivery output context framework tracker
	 * @throws IOException basic IO connectivity pipeline execution drop notifications
	 * @throws ServletException processing configuration failures signals
	 */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * Extracts named transactional interface error text strings stored under precise tracking keywords inside request structures.
	 * * @param property literal naming flag string pointer key
	 * @param request processing query pipeline controller tracker container
	 * @return matched text error value contents, or blank string value if empty
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {

		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Pulls targeted data string messages stored inside runtime transaction contexts attributes safely.
	 * * @param property data lookup key literal string identification label flag
	 * @param request active client interaction processing transactional request model tracker
	 * @return configuration message content text, or blank string parameter if empty
	 */
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Attaches generic exception tracking statements to the current request transaction layer variables.
	 * * @param msg details text message content
	 * @param request tracking parameters context pipeline
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Pulls globally assigned operation tracking failure text lines saved within requests contexts maps structures.
	 * * @param request tracking processing model variables container
	 * @return error description message parameters string data
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Registers operation success informational statement payloads onto active request states instances.
	 * * @param msg affirmation text notice content detail string data
	 * @param request target delivery request transactional container parameter
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Pulls context operation success message string parameters from current context structures tracking attributes variables maps.
	 * * @param request target transaction request pipeline tracker package instance
	 * @return message text string
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Attaches operational system Bean instances to the request framework scopes attributes properties maps.
	 * * @param bean dynamic entity context model class instance extending BaseBean wrapper controls
	 * @param request server network interaction tracking model layer parameter package
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	/**
	 * Pulls core base entity framework Bean information out from tracking attribute scopes parameters storage.
	 * * @param request transaction context pipeline processing parameter tracker object
	 * @return active operational entity BaseBean class instance object context pointer
	 */
	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	/**
	 * Retrieves requested query string submission components safely converting null elements into secure blank text strings representation parameters.
	 * * @param property request parameters string parameter identifier label keyword key name
	 * @param request user input collection structure layer active tracking class package object
	 * @return user values, or blank string parameter if empty
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Saves database pagination collection list instances onto active request transaction contexts parameters tracking attributes scopes.
	 * * @param list search datasets execution entities output collections list instance data package
	 * @param request transaction contextual communication properties handler object
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * Pulls operational pagination results tables list collection sets out from active request attributes fields.
	 * * @param request user communication handling context object container tracking state attributes
	 * @return structural entities result matching collection sets records data list instance
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * Stores pagination tracker indexing pointers values into active request states scopes data.
	 * * @param pageNo data viewing index navigation page number sequence coordinate position integer value
	 * @param request processing query parameter container tracking framework attributes variables
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * Pulls pagination position indexing indicators from active request framework configuration entries attributes maps tracking records.
	 * * @param request data pipeline controller tracking active operations parameters package
	 * @return target processing index page count identification sequence numerical code position integer
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * Stores pagination view limits volume control configurations variables records indicators values.
	 * * @param pageSize data output grid visualization boundaries limit dimension scale parameter integer count
	 * @param request interaction active handling request transaction variable storage object tracking container
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * Pulls active pagination layout items processing quantity size count numbers trackers from configuration parameter records attributes.
	 * * @param request active data pipeline communication parameters package object tracker
	 * @return rendering list rows threshold limit parameter tracking count configuration integer numerical value
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

	/**
	 * Implements default error page redirection routing loops passing explicit exception parameters details properties.
	 * * @param e system error context reference instance tracked tracking target details payload code loop
	 * @param request processing contextual parameter database structure handler object container parameters
	 * @param response client application context delivery visualization framework controller channel
	 * @throws IOException tracking output processing channel exceptions drop notifications alerts
	 * @throws ServletException processing initialization workflow setup operations failures alerts signals
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}
}