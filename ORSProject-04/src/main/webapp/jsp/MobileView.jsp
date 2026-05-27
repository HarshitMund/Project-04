<%@page import="in.co.rays.proj4.controller.MobileCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mobile View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="MobileCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MobileBean"
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
				Mobile Details
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
					<th align="left">Brand Name<span style="color: red">*</span></th>
					<td><input type="text" name="beandName"
						placeholder="Enter Brand Name"
						value="<%=DataUtility.getStringData(bean.getBeandName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("beandName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Mobile Name<span style="color: red">*</span></th>
					<td><input type="text" name="mobileName"
						placeholder="Enter Mobile Name"
						value="<%=DataUtility.getStringData(bean.getMobileName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">RAM/Raw<span style="color: red">*</span></th>
					<td><input type="text" name="raw"
						placeholder="Enter RAM/Raw Size"
						value="<%=bean.getRaw() > 0 ? bean.getRaw() : ""%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("raw", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Price<span style="color: red">*</span></th>
					<td><input type="text" name="price" placeholder="Enter Price"
						value="<%=bean.getPrice() > 0 ? bean.getPrice() : ""%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("price", request)%>
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
						name="operation" value="<%=MobileCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=MobileCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=MobileCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=MobileCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>