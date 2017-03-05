<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
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
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>

	<!-- Navbar header common in all Customer pages.Can be replaced as Navbar tile using Apache Tiles-->
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" id="brand" href="#"> Northeastern Bank <!--  <img alt="Brand" src="${pageContext.request.contextPath}/resources/images/northeastern-university-logo.jpg">-->
			</a>

			<p class="navbar-text navbar-right">
				Signed in as <a href="#" class="navbar-link">${sessionScope.loggedInUser.firstName}
					${sessionScope.loggedInUser.lastName}</a>
			</p>
			<br> <br>
		</div>
	</div>
	</nav>

	<!-- <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>-->
	<div class="container">
		<div class="row">

			<!-- Header after navbar.This too can be added as a tile-->

			<div class="col-lg-11">Hello,
				${sessionScope.loggedInUser.firstName}</div>
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

			<div class="panel panel-success">
				<div class="panel-heading">Welcome
					${sessionScope.loggedInUser.firstName}</div>
				<div class="panel-body">
					Panel Content

					<div id="tabs">
						<ul>
							<li><a href="#home">Home</a></li>
							<li><a href="#createCustomer">Create Customer</a></li>
							<li><a href="#updateCustomer">Update Customer</a></li>
							<li><a href="#banking">Banking</a></li>
							<li><a href="#settings">Settings</a></li>
						</ul>
						<div id="home">This is your home screen.</div>
						<div id="createCustomer">
							<c:if test="${requestScope.message!=null}">
							Last Added new Customer:<br>
							${requestScope.message} - ${requestScope.customer.firstName} ${requestScope.customer.lastName}
						</c:if>

							<!-- <spring:hasBindErrors name="customer">
								<h2>Errors</h2>
								<div class="well">
								<form:errors path="*"/>
									<ul>
										<c:forEach var="error" items="${errors.allErrors}">
											<li><font color="blue">${error.defaultMessage}</font></li>
										</c:forEach>
									</ul>
								</div>
							</spring:hasBindErrors> -->
							<form:form action="createCustomer.htm" commandName="customer"
								method="post">
								<table class="table">
									<tr>
										<td>First Name:</td>
										<td><form:input type="text" path="firstName"
												name="firstName" value="" required="true" /></td>
										<td><font color="red"><form:errors
													path="firstName" /></font></td>

									</tr>

									<tr>
										<td>Last Name:</td>
										<td><form:input type="text" path="lastName"
												name="lastName" value="" required="true" /></td>
										<td><font color="red"><form:errors path="lastName" /></font></td>
									</tr>

									<tr>
										<td>User Name:</td>
										<td><form:input type="text" path="userName"
												name="userName" value="" required="true" /></td>
										<td><font color="red"><form:errors path="userName" /></font></td>
									</tr>

									<tr>
										<td>Password Name:</td>
										<td><form:input type="text" path="password"
												name="password" value="" required="true" /></td>
										<td><font color="red"><form:errors path="password" /></font></td>
									</tr>

									<tr>
										<td>Date of Birth:</td>
										<td><form:input type="date" path="dateOfBirth"
												name="dateOfBirth" value="" required="true" /></td>
										<td><font color="red"><form:errors
													path="dateOfBirth" /></font></td>
									</tr>


									<tr>
										<td>SSN:</td>
										<td><form:input type="text" name="sSN" value=""
												path="sSN" required="true" /></td>
										<td><font color="red"><form:errors path="sSN" /></font></td>
									</tr>


									<tr>
										<td>Phone Number:</td>
										<td><form:input type="text" name="phoneNumber" value=""
												path="phoneNumber" required="true" /></td>
										<td><font color="red"><form:errors
													path="phoneNumber" /></font></td>
									</tr>
									<tr>

										<td>Gender:</td>
										<td><br> <form:radiobutton name="gender" value="M"
												path="gender" required="true" />M<br> <form:radiobutton
												name="gender" path="gender" value="F" />F</td>
										<td><font color="red"><form:errors path="gender" /></font></td>
									</tr>

									<tr>
										<td>Email:</td>
										<td><form:input type="text" name="emailId" value=""
												path="emailId" required="true" /></td>
										<td><font color="red"><form:errors path="emailId" /></font></td>
									</tr>

									<tr>
										<td>Bank Account Details:</td>
										<td></td>
									</tr>

									<!-- <tr>
										<td>Account Balance:</td>
										<td><input type="text" name="accountBalance" value=""
											required="true" /></td>
										<td></td>
									</tr> -->

									
									<tr>
										<td>Account Type:</td>
										<td><select name="accountType" size="2" required="true">

												<option value="Savings">Savings</option>
												<option value="Checking">Checking</option>
										</select></td>
										<td></td>
									</tr>

									<!-- 
									<tr>
										<td>Associated Debit Card:</td>
										<td></td>
									</tr>
									<tr>
										<td>Card Number:</td>
										<td><input type="text" name="cardNumber" value=""
											required="true" /></td>
									</tr>
									<tr>
										<td>CVV:</td>
										<td><input type="text" name="cvv" value="" required="true" /></td>
									</tr>
									<tr>
										<td>Pin Code:</td>
										<td><input type="text" name="pinCode" value="true"
											required="" /></td>
									</tr>
									
									
									 -->
									<!--<tr>
										<td>Valid From:</td><td><input type="date" name="validFrom" value="" required="" /></td>
									</tr>  -->

									<tr>
										<td>Card Name:</td>
										<td>
											<select name="cardName" size="2" required="true">
												<option value="Platinum Card">Platinum Card</option>
												<option value="Titanium Card">Titanium Card</option>
												<option value="Gold Card">Gold Card</option>
												<option value="Travel Advantage Card">Travel Advantage Card</option>
												
										</select>
											
											</td>
										<td></td>
									</tr>
									<!-- <tr>
										<td>Daily Max Withdrawl Limit:</td>
										<td><input type="text" name="dailyMaxWithDrawlLimit"
											value="800" required="" /></td>
									</tr>
									<tr>
										<td>Min Amt Per Withdrawl:</td>
										<td><input type="text" name="minimumAmountPerWithDrawl"
											value="20" required="" /></td>
									</tr> -->

								</table>
								<input type="submit" class="btn btn-success"
									value="Create Customer" />
							</form:form>

						</div>

						<div id="updateCustomer">
							<form onsubmit="return false">
								<input type="text" size="30" name="searchCustomer"
									onkeyup="customerList(this.value)" />
								<div id="livesearch"></div>
							</form>



						</div>
						<div id="banking">
							<table class="table">
								<tr>
									<td>Request ID</td>
									<td>
									<td>Image Name</td>
									<td>
									<td>Customer Name</td>
									<td>Image</td>

								</tr>
								<c:forEach items="${requestScope.requestList}"
									var="chequeRequest">
									<tr>
										<td>${chequeRequest.id}</td>
										<td>
										<td>${chequeRequest.imageName}</td>
										<td>
										<td>${chequeRequest.customer.firstName}
											${chequeRequest.customer.lastName}</td>
										<td><img
											src="<c:url value="/images/${chequeRequest.imageName}"/>"
											style="width: 60px; height: 80px;"></td>

									</tr>


								</c:forEach>




							</table>


						</div>
						<div id="settings">
							<p>Settings</p>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script>
		var xmlhttp;
		<!--xmlhttp = xmlHttpObject();

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
		-->

		function customerList(str) {
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
							resultString = resultString + "<li>"
									+ json.usersList[i].userName + "</li>";
						}
						console.log("resultString--" + resultString);
						resultString = resultString + "</ul>";
						document.getElementById("livesearch").innerHTML = resultString;
						document.getElementById("livesearch").style.border = "1px solid #A5ACB2";

					}
				}
			}
			xmlhttp.open("GET", "searchUser.htm?searchKey=" + str, true);
			<!--xmlhttp.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			-->
			<!--xmlhttp.send(query);
			-->
			xmlhttp.send();
		}
	</script>

</body>
</html>