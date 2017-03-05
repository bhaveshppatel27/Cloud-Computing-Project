<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Statement</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/employee-style.css">

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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" id="brand" href="#"> Northeastern Bank <!--  <img alt="Brand" src="${pageContext.request.contextPath}/resources/images/northeastern-university-logo.jpg">-->
			</a>

			<p class="navbar-text navbar-right">
				Signed in as <a href="#" class="navbar-link">${sessionScope.customer.firstName}
					${sessionScope.customer.lastName}</a>
			</p>
			<br> <br>
		</div>
	</div>
	</nav>

	<!--  Header Begins  -->
	<div class="container">
		<div class="row">
			<div class="col-lg-10">Hello,
				${sessionScope.customer.firstName}</div>
			<div class="col-lg-1">
				<form method="post" action="customerHome.htm">
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
	<br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse2"> Update Password</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">
							<form onsubmit="return false" method="post">
								<div class="row" id="resetStatusMessage"></div>

								<div class="row">
									<div class="col-lg-12">
										<span class="label label-danger">Please note that you
											must log of after the password reset for the new password to
											be effective.</span>
									</div>
								</div>
								<br>
								<div class="row">

									<div class="col-lg-7">
										<div class="row">

											<div class="col-lg-4">Password</div>
											<div class="col-lg-4">
												<input required="" type="password" id="password"
													name="password" />
											</div>
										</div>

										<div class="row">
											<div class="col-lg-4">Re-enter Password</div>
											<div class="col-lg-4">
												<input type="password" name="reenterPassword"
													id="reenterPassword" />
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12">
												<input type="submit" onclick="resetPassword()"
													class="btn btn-success" value="Submit" />
											</div>
										</div>

									</div>


								</div>
								<br>
								<div class="row">
									<div class="col-lg-4">
										

											<div class="well">
												<h4>Password Policy</h4>
												Your password must contain:
												<ul class="list-group">
													<li class="list-group-item">Atleast one lower case
														letter</li>
													<li class="list-group-item">Atleast one upper case
														letter</li>
													<li class="list-group-item">Atleast one special
														character from @#$%</li>
													<li class="list-group-item">Atleast one number</li>
													<li class="list-group-item">Minimun length is 6 and
														Maximum is 20</li>
												</ul>
											</div>
										
									</div>
								</div>



							</form>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script>
		var xmlhttp;
		xmlhttp = xmlHttpObject();

		function xmlHttpObject() {
			var xmlHttp = null;
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlHttp = new XMLHttpRequest();
			} else { // code for IE6, IE5
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			return xmlHttp;
		}
		function resetPassword() {
			console.log("In Function");
			if (xmlhttp == null) {
				alert("Your browser does not support AJAX");
				return;
			}

			var password = $("#password").val();
			var reenterPassword = $("#reenterPassword").val();
			console.log("reenterPassword" + reenterPassword + " password"
					+ password);
			query = "reenterPassword=" + reenterPassword + "&password="
					+ password;

			console.log(query);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					var json = JSON.parse(xmlhttp.responseText)
					console.log(json.status);

					if (json.status) {
						$("resetStatusMessage").fadeIn(10);
						var insertText = "<p class='bg-success'>"
								+ json.returnMessage + "</p>";
						document.getElementById("resetStatusMessage").innerHTML = insertText;
						$("#resetStatusMessage").fadeOut(6000);
					} else {
						$("#resetStatusMessage").fadeIn(10);
						var insertText = "<p class='bg-danger'>"
								+ json.returnMessage + "</p>";
						document.getElementById("resetStatusMessage").innerHTML = insertText;
						$("#resetStatusMessage").fadeOut(4000);
					}

				}
			}
			xmlhttp.open("POST", "resetPassword.htm", true);
			xmlhttp.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			<!--xmlhttp.send(query);
			-->
			xmlhttp.send(query);
		}
	</script>
</body>
</html>