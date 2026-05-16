<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
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
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<div align="center">
			<h1>User Registration</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
				<h3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
			</div>

			<table>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
					</td>
				</tr>

				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName"
						placeHolder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
					</td>
				</tr>

				<tr>
					<th>Login:</th>
					<td><input type="text" name="login" placeHolder="Enter login"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</td>
				</tr>

				<tr>
					<th>Password:</th>
					<td><input type="password" name="password"
						placeHolder="Enter password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
					</td>
				</tr>

				<tr>
					<th>Confirm Password:</th>
					<td><input type="password" name="confirmPassword"
						placeHolder="Enter confirm password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
					</td>
				</tr>

				<tr>
					<th align="left">Date of Birth<span style="width: 98%"
						style="color: red">*</span></th>
					<td><input type="date" name="dob" style="width: 165px"
						placeholder="Select Date of Birth"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>

				<tr>
					<th>Gender:<span style="color: red">*</span></th>
					<td>
						<%
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("Male", "Male");
						map.put("Female", "Female");

						String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>
				<tr>
					<th>Mobile No:</th>
					<td><input type="text" name="mobileNo"
						placeHolder="Enter mobile number"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
					</td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
						type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_RESET%>"></td>
				</tr>

			</table>
		</div>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>