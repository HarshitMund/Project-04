<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Module</title>
</head>
<body>
	<!-- Logo -->
	<img src="<%=request.getContextPath()%>/img/customLogo.jpg"
		align="right" width="100" height="40" border="0">
	<%
	UserBean user = (UserBean) session.getAttribute("user");
	boolean loggedIn = user != null;
	%>
	<%
	if (loggedIn) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
	<%} %>
	
	<a href="<%=ORSView.WELCOME_CTL%>"><b>Go Back</b></a>
	<b>|</b>
	<a href="<%=ORSView.GYM_CTL%>">Add Gym Membership</a>
	<b>|</b>
	<a href="<%=ORSView.GYM_LIST_CTL%>">Gym Membership List</a>
	<b>|</b>
	
	<a href="<%=ORSView.MOBILE_CTL%>">Add Mobile</a>
	<b>|</b>
	<a href="<%=ORSView.MOBILE_LIST_CTL%>">Mobile List</a>
	<b>|</b>

	<a href="<%=ORSView.COLLEGE_SYSTEM_CTL%>">Add College System</a>
	<b>|</b>
	<a href="<%=ORSView.COLLEGE_SYSTEM_LIST_CTL%>">College System List</a>
	<b>|</b>

	<a href="<%=ORSView.EVENT_CTL%>">Add Event</a>
	<b>|</b>
	<a href="<%=ORSView.EVENT_LIST_CTL%>">Event List</a>
	<b>|</b>
	
	<hr>

</body>
</html>