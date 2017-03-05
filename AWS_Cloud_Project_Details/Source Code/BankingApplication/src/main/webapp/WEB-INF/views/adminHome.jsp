<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page import="com.neu.edu.wtp.utility.SessionCounter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/admin-style.css">

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
				Signed in as <a href="#" class="navbar-link">${requestScope.customer.firstName}
					${requestScope.customer.lastName}</a>
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
				<!--<form method="post" action="customerHome.htm">
					<input type="submit" class="btn btn-info" value="Home">
				</form>-->
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
		<h1>Active users : ${sessionScope.session-count}</h1>
		</div>
			<div class="col-lg-12">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab1info" data-toggle="tab">Create
									Employee</a></li>
							<li><a href="#tab2info" data-toggle="tab">Update
									Employee Account</a></li>
							<li><a href="#tab3info" data-toggle="tab">Delete
									Employee</a></li>
							<li class="dropdown"><a href="#" data-toggle="dropdown">Dropdown
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#tab4info" data-toggle="tab">Info 4</a></li>
									<li><a href="#tab5info" data-toggle="tab">Info 5</a></li>
								</ul></li>
						</ul>
					</div>
					<div class="panel-body">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="tab1info">
								<div class="row">
									<div class="col-lg-12">
										<form:form action="addEmployee.htm" commandName="employee"
											method="post">
											<br>
											<h3>${requestScope.addErrorMessage}</h3>
											</br>
											<table class="table">
												<tr>
													<td>First Name:</td>
													<td><form:input required="true" path="firstName"
															size="30" /></td>
													<td><font color="red"><form:errors
																path="firstName"></form:errors></font></td>

												</tr>

												<tr>
													<td>Last Name:</td>
													<td><form:input required="true" path="lastName"
															size="30" /></td>
													<td><font color="red"><form:errors
																path="lastName" /></font></td>

												</tr>


												<tr>
													<td>User Name:</td>
													<td><form:input required="true" path="userName"
															size="30" /></td>
													<td><font color="red"><form:errors
																path="userName" /></font></td>

												</tr>

												<tr>
													<td>Password:</td>
													<td><form:password required="true" path="password"
															size="30" /></td>
													<td><font color="red"><form:errors
																path="password" /></font></td>

												</tr>

												<tr>
													<td>Email Id:</td>
													<td><form:input required="true" path="emailId"
															size="30" /></td>
													<td><font color="red"><form:errors
																path="emailId" /></font></td>

												</tr>


												<tr>
													<td>Date of Birth:</td>
													<td><form:input type="date" required=""
															path="dateOfBirth" size="30" /></td>
													<td><font color="red"><form:errors
																path="dateOfBirth" /></font></td>
												</tr>
												<tr>
													<td>Gender</td>
													<td><form:radiobutton required="" path="gender"
															value="M" size="30" />Male<br> <form:radiobutton
															path="gender" value="F" size="30" />Female</td>
													<td><font color="red"><form:errors
																path="gender" /></font></td>
												</tr>
												<tr>
													<td colspan="2"><input type="submit"
														value="Create User" /></td>
													<td></td>
												</tr>
											</table>

										</form:form>


									</div>



								</div>


							</div>

							<div class="tab-pane fade" id="tab2info">

								<h3>${requestScope.errorMessage }</h3>
								<br>
								<form method="post" action="updateEmployee.htm">
									<div class="row">
										<div class="col-lg-3">
											Search : <input type="text" name="searchEmployee"
												id="searchEmployee" onkeyup="showResult(this.value)" />
										</div>
										<div class="col-lg-3">
											<input type="submit" class="btn btn-success"
												name="employeeId" value="Submit" />
										</div>
									</div>
									<div id="livesearch"></div>

								</form>



							</div>

							<div class="tab-pane fade" id="tab3info">Info 3</div>
							<div class="tab-pane fade" id="tab4info">Info 4</div>
							<div class="tab-pane fade" id="tab5info">Info 5</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	var source = new EventSource("");
	source.onmessage = function(event) {
	    document.getElementById("result").innerHTML += event.data + "<br>";
	};
	
	
		function showResult(str) {
			if (str.length == 0) {
				document.getElementById("livesearch").innerHTML = "";
				document.getElementById("livesearch").style.border = "0px";
				return;
			}
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else { // code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			query = "searchKey=" + str;

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					var resultString = "<ul>"
					var json = JSON.parse(xmlhttp.responseText);
					console.log("JSON--" + json);
					console.log("Length--" + json.usersList.length);
					if (json.usersList.length == 0) {
						resultString = resultString
								+ "<li>No Records found</li>";
						console.log("resultString--" + resultString);
						resultString = resultString + "</ul>";
						document.getElementById("livesearch").innerHTML = resultString;
						document.getElementById("livesearch").style.border = "1px solid #A5ACB2";
					}

					else {
						for (var i = 0; i < json.usersList.length; i++) {
							resultString = resultString + "<li id=\"user" + i
									+ "\" onclick=\"selectThisUser('user" + i
									+ "')\"><a>" + json.usersList[i]
									+ "</a></li>";
						}
						console.log("resultString--" + resultString);
						resultString = resultString + "</ul>";
						document.getElementById("livesearch").innerHTML = resultString;
						document.getElementById("livesearch").style.border = "1px solid #A5ACB2";

					}
				}
			}
			xmlhttp.open("GET", "searchManager.htm?searchKey=" + str, true);
			xmlhttp.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");

			<!--xmlhttp.send(query);
			-->
			xmlhttp.send();
		}

		function selectThisUser(str) {

			var userName = $("#" + str).children("a").text();
			$("#searchEmployee").val(userName);
		}
	</script>
</body>
</html>