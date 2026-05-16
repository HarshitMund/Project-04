<%@page import="in.co.rays.proj4.controller.RoleCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.ROLE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.RoleBean"
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
				Role
			</h1>
			<h3 align="center" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h3>
			<h3 align="center" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h3>
		</div>

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime" value="<%=bean.getCreatedDatetime()%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=bean.getModifiedDatetime()%>">

		<table align="center">
			<tr>
				<th>Name: <span style="color: red">*</span></th>
				<td><input type="text" name="name" placeholder="Enter Name"
					value="<%=DataUtility.getStringData(bean.getName())%>"></td>
				<td><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
			</tr>
			<tr>
				<th>Description: <span style="color: red">*</span></th>
				<td><textarea rows="3" name="description"
						style="width: 170px; resize: none;"
						placeholder="Enter the Description"><%=DataUtility.getStringData(bean.getDescription()).trim()%></textarea></td>
				<td><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
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
					name="operation" value="<%=RoleCtl.OP_UPDATE%>"> <input
					type="submit" name="operation" value="<%=RoleCtl.OP_CANCEL%>">
				</td>
				<%
				} else {
				%>
				<td align="left" colspan="2"><input type="submit"
					name="operation" value="<%=RoleCtl.OP_SAVE%>"> <input
					type="submit" name="operation" value="<%=RoleCtl.OP_RESET%>">
				</td>
				<%
				}
				%>
			</tr>
		</table>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>