<%@page import="in.co.rays.proj4.controller.CollegeSystemCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>College System View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="CollegeSystemCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeSystemBean"
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
				Student Record
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
					<th align="left">Student Name<span style="color: red">*</span></th>
					<td><input type="text" name="studentName"
						placeholder="Enter Student Name"
						value="<%=DataUtility.getStringData(bean.getStudentName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("studentName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Branch<span style="color: red">*</span></th>
					<td><input type="text" name="branch"
						placeholder="Enter Branch"
						value="<%=DataUtility.getStringData(bean.getBranch())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("branch", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Semester<span style="color: red">*</span></th>
					<td><input type="text" name="semester"
						placeholder="Enter Semester"
						value="<%=bean.getSemester() > 0 ? bean.getSemester() : ""%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("semester", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">CGPA<span style="color: red">*</span></th>
					<td><input type="text" name="cgpa" placeholder="Enter CGPA"
						value="<%=bean.getCgpa() > 0 ? bean.getCgpa() : ""%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("cgpa", request)%>
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
						name="operation" value="<%=CollegeSystemCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=CollegeSystemCtl.OP_CANCEL%>"> <%
 } else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CollegeSystemCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=CollegeSystemCtl.OP_RESET%>"> <%
 }
 %>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>