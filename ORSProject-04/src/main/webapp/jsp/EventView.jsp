<%@page import="in.co.rays.proj4.controller.EventCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="EventCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.EventBean"
			scope="request"></jsp:useBean>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
				if (bean != null && bean.getId() > 0) {
				%>Update<%
				} else {
				%>Add<%
				}
				%>
				Event Details
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
				<h3 align="center">
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Event Name<span style="color: red">*</span></th>
					<td><input type="text" name="eventName"
						placeholder="Enter Event Name"
						value="<%=DataUtility.getStringData(bean.getEventName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("eventName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Organizer Name<span style="color: red">*</span></th>
					<td><input type="text" name="organizerName"
						placeholder="Enter Organizer Name"
						value="<%=DataUtility.getStringData(bean.getOrganizerName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("organizerName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Venue<span style="color: red">*</span></th>
					<td><input type="text" name="venue" placeholder="Enter Venue"
						value="<%=DataUtility.getStringData(bean.getVenue())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("venue", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Budget<span style="color: red">*</span></th>
					<td><input type="text" name="budget"
						placeholder="Enter Budget"
						value="<%=bean.getBudget() > 0 ? bean.getBudget() : ""%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("budget", request)%>
					</font></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th></th>
					<%
					if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=EventCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=EventCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=EventCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=EventCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>