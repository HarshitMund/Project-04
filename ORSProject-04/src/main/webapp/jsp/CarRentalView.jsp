<%@page import="in.co.rays.proj4.controller.CarRentalCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Rental View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="CarRentalCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CarRentalBean"
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
				Car Rental Details
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
					<th align="left">Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Customer Name"
						value="<%=DataUtility.getStringData(bean.getName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Car Model<span style="color: red">*</span></th>
					<td><input type="text" name="carModel"
						placeholder="Enter Car Model"
						value="<%=DataUtility.getStringData(bean.getCarModel())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("carModel", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Rent Per Day<span style="color: red">*</span></th>
					<td><input type="text" name="rentpayDay"
						placeholder="Enter Rent Per Day"
						value="<%=bean.getRentpayDay() > 0 ? bean.getRentpayDay() : ""%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("rentpayDay", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Fuel Type<span style="color: red">*</span></th>
					<td><input type="text" name="fuelType"
						placeholder="Enter Fuel Type"
						value="<%=DataUtility.getStringData(bean.getFuelType())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("fuelType", request)%>
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
						name="operation" value="<%=CarRentalCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CarRentalCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CarRentalCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=CarRentalCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>