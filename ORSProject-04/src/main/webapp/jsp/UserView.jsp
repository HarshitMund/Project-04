<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.RoleCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="<%=ORSView.USER_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
		List<UserBean> roleList = (List<UserBean>) request.getAttribute("roleList");
		%>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
				if (bean != null && bean.getId() > 0) {
				%>Update<%
				} else {
				%>Add<%
				}
				%>
				User
			</h1>
			<h3 style="color: red"><%=ServletUtility.getErrorMessage(request)%></h3>
			<h3 style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h3>
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
				<th align="left">First name:<span style="color: red">*</span></th>
				<td><input type="text" name="firstName"
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"
					placeholder="Enter First Name"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Last name:<span style="color: red">*</span></th>
				<td><input type="text" name="lastName"
					value="<%=DataUtility.getStringData(bean.getLastName())%>"
					placeholder="Enter last Name"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Login:<span style="color: red">*</span></th>
				<td><input type="text" name="login"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"
					placeholder="Enter Email Id"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Password:<span style="color: red">*</span></th>
				<td><input type="password" name="password"
					value="<%=DataUtility.getStringData(bean.getPassword())%>"
					placeholder="Enter Password"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Confirm Password:<span style="color: red">*</span></th>
				<td><input type="password" name="confirmPassword"
					value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"
					placeholder="Enter Confirm Password"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Gender:<span style="color: red">*</span></th>
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
				<th align="left">Date of Birth<span style="width: 98%"
					style="color: red">*</span></th>
				<td><input style="width: 165px" type="date" name="dob"
					placeholder="Select Date of Birth"
					value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Role:<span style="color: red">*</span></th>
				<td><%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), roleList)%></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font></td>
			</tr>
			<tr>
				<th align="left">Mobile No:<span style="color: red">*</span></th>
				<td><input type="text" name="mobileNo" maxlength="10"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
					placeholder="Enter mobile Number"></td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr>
				<th></th>
				<%
				if (bean != null && bean.getId() > 0) {
				%>
				<td align="left" colspan="2"><input type="submit"
					name="operation" value="<%=UserCtl.OP_UPDATE%>"> <input
					type="submit" name="operation" value="<%=UserCtl.OP_CANCEL%>">
					<%
					} else {
					%></td>
				<td align="left" colspan="2"><input type="submit"
					name="operation" value="<%=UserCtl.OP_SAVE%>"> <input
					type="submit" name="operation" value="<%=UserCtl.OP_RESET%>">
					<%
					}
					%></td>
			</tr>
		</table>

		<%@ include file="Footer.jsp"%>

	</form>

</body>
</html>