<%@page import="in.co.rays.proj4.controller.GymCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gym View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="GymCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.GymBean"
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
				Gym Membership
			</h1>

			<div style="height: 15px; margin-botton: 12px">
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
					<th align="left">Member Name<span style="color: red">*</span></th>
					<td><input type="text" name="memberName"
						placeholder="Enter Member Name"
						value="<%=DataUtility.getStringData(bean.getMemberName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("memberName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Trainer Name<span style="color: red">*</span></th>
					<td><input type="text" name="trainerName"
						placeholder="Enter Trainer Name"
						value="<%=DataUtility.getStringData(bean.getTrainerName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("trainerName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Member Fee<span style="color: red">*</span></th>
					<td><input type="text" name="fee"
						placeholder="Enter Member Fee"
						value="<%=DataUtility.getStringData(bean.getFee())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("fee", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Joining Date<span style="color: red">*</span></th>
					<td><input type="date" id="udate" name="joiningDate"
						style="width: 165px;" placeholder="Select Joining Date"
						value="<%=DataUtility.getDateString(bean.getJoiningDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("joiningDate", request)%>
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
						name="operation" value="<%=GymCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=GymCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=GymCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=GymCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>