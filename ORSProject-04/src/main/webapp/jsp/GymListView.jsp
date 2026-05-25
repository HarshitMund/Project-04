<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.GymListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.GymBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gym Membership List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>

	<%@include file="ModuleView.jsp"%>
	<div align="center">
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.GymBean"
			scope="request"></jsp:useBean>
		<h1 align="center" style="margin-bottom: -15; color: navy;">Gym
			Membership List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.GYM_LIST_CTL%>" method="POST">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<GymBean> gymList = (List<GymBean>) request.getAttribute("gymList");

			List<GymBean> list = (List<GymBean>) ServletUtility.getList(request);
			Iterator<GymBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Member Name :</b></label> <input
						type="text" name="memberName" placeholder="Enter Member Name"
						value="<%=ServletUtility.getParameter("memberName", request)%>">&emsp;
						<label><b>Trainer Name :</b></label> <input type="text"
						name="trainerName" placeholder="Enter Trainer Name"
						value="<%=ServletUtility.getParameter("trainerName", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=GymListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=GymListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Member Name</th>
					<th width="25%">Trainer Name</th>
					<th width="15%">Fee</th>
					<th width="10%">Joining Date</th>
					<th width="5%">Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					bean = it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getMemberName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getTrainerName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getFee()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getJoiningDate()%></td>
					<td style="text-align: center;"><a
						href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=GymListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=GymListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=GymListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=GymListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
			}
			%>
		</form>
</body>
</html>