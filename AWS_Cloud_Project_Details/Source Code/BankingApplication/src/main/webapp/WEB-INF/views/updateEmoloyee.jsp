<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" id="brand" href="#"> Northeastern Bank <!--  <img alt="Brand" src="${pageContext.request.contextPath}/resources/images/northeastern-university-logo.jpg">-->
			</a>

			<p class="navbar-text navbar-right">
				Signed in as <a href="#" class="navbar-link">${requestScope.username}
				</a>
			</p>
			<br> <br>
		</div>
	</div>
	</nav>

	<!--  Header Begins  -->
	<div class="container">
		<div class="row">
			<div class="col-lg-10">Hello, ${sessionScope.username}</div>
			<div class="col-lg-1">
				<form method="post" action="adminHome.htm">
					<input type="submit" class="btn btn-info" value="Home">
				</form>
			</div>
			<div class="col-lg-1">
				<form:form action="logout.htm">
					<input type="submit" class="btn btn-success" name="logout"
						value="Logout" />
				</form:form>
			</div>



		</div>
	</div>
	<!--  Header closes  -->

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="saveChanges.htm" commandName="employee"
					method="post">

					<table class="table">
						<tr>
							<td>First Name:</td>
							<td><form:input required="true" path="firstName" size="30" /></td>
							<td><font color="red"><form:errors path="firstName"></form:errors></font></td>

						</tr>

						<tr>
							<td>Last Name:</td>
							<td><form:input required="true" path="lastName" size="30" /></td>
							<td><font color="red"><form:errors path="lastName" /></font></td>

						</tr>


						<tr>
							<td>User Name:</td>
							<td><form:input required="true" disabled="true" path="userName" size="30" /></td>
							<td><font color="red"><form:errors path="userName" /></font></td>

						</tr>

						<tr>
							<td>Password:</td>
							<td><form:password required="true" path="password" size="30" /></td>
							<td><font color="red"><form:errors path="password" /></font></td>

						</tr>

						<tr>
							<td>Email Id:</td>
							<td><form:input required="true" path="emailId" size="30" /></td>
							<td><font color="red"><form:errors path="emailId" /></font></td>

						</tr>


						<tr>
							<td>Date of Birth:</td>
							<td><form:input type="date" required="" path="dateOfBirth"
									size="30" /></td>
							<td><font color="red"><form:errors path="dateOfBirth" /></font></td>
						</tr>
						<tr>
							<td>Gender</td>
							<td><form:radiobutton required="" path="gender" size="30" />Male<br>
								<form:radiobutton path="gender" size="30" />Female</td>
							<td><font color="red"><form:errors path="gender" /></font></td>
						</tr>
						<tr>
							<td colspan="2"><input class="btn btn-success" type="submit" value="Save" /></td>
							<td></td>
						</tr>
					</table>

				</form:form>

			</div>
		</div>
	</div>

</body>
</html>