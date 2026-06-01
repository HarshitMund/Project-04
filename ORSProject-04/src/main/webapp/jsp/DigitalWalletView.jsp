<%@page import="in.co.rays.proj4.controller.DigitalWalletCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Digital Wallet View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.jpg" sizes="16x16" />
</head>
<body>
	<form action="DigitalWalletCtl" method="POST">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.DigitalWalletBean"
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
				Digital Wallet
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
					<th align="left">User Name<span style="color: red">*</span></th>
					<td><input type="text" name="userName"
						placeholder="Enter User Name"
						value="<%=DataUtility.getStringData(bean.getUserName())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("userName", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Balance<span style="color: red">*</span></th>
					<td><input type="text" name="balance"
						placeholder="Enter Wallet Balance"
						value="<%=bean.getBalance() > 0 ? bean.getBalance() : ""%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("balance", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Mobile Number<span style="color: red">*</span></th>
					<td><input type="text" name="mobileNumber"
						placeholder="Enter Mobile Number"
						value="<%=DataUtility.getStringData(bean.getMobileNumber())%>">
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNumber", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Wallet Status<span style="color: red">*</span></th>
					<td><input type="text" name="walletStatus" placeholder="Enter Wallet Status"
						value="<%=DataUtility.getStringData(bean.getWalletStatus())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("walletStatus", request)%>
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
						name="operation" value="<%=DigitalWalletCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=DigitalWalletCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=DigitalWalletCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=DigitalWalletCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>