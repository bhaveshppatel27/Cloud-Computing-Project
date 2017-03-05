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
			<div class="col-lg-6">
			<a class="btn btn-warning" href="${pageContext.request.contextPath}/getPdfDocument.htm" target="_blank" role="button">Generate PDF</a>
				

			</div>
		</div>
		<form onsubmit="return false">
			<input type="hidden" value="${sessionScope.statementOffset}"
				name="statementOffset" id="statementOffset" />
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-6">
							<input type="submit" class="btn btn-warning" id="generateButton"
								value="Show More" onclick="generateNextStatement()" /> <br>
						</div>


					</div>

				</div>
				<div class="col-lg-12">

					<table class="table" id="transactionTable">
						<tr>
							<th>Transaction Id</th>
							<th>From</th>
							<th>To</th>
							<th>Amount</th>
							<th>Description</th>
							<th>Date(YYYY-MM-DD Time)</th>
							<th>Transaction Type</th>
						</tr>
						<c:forEach items="${requestScope.statement}" var="transaction">
							<tr>
								<td><span class="badge">${transaction.transactionId}</span></td>
								<td>${transaction.fromAccount}</td>
								<td>${transaction.toAccount}</td>
								<td>${transaction.amount}</td>
								<td>${transaction.shortDescription}</td>
								<td>${transaction.transactionDate}</td>
								<c:choose>
									<c:when
										test="${requestScope.customerAccountNumber==transaction.fromAccount}">
										<td><span class="label label-warning">Debit</span></td>
									</c:when>
									<c:otherwise>
										<td><span class="label label-success">Credit</span></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</form>
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

		function generateNextStatement() {

			console.log("In Function");
			if (xmlhttp == null) {
				alert("Your browser does not support AJAX");
				return;
			}

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					console.log("In JSON");

					var json = JSON.parse(xmlhttp.responseText);
					console.log(json);
					/*console.log(json.status.trim());*/

				}
			}
			xmlhttp.open("POST", "generateNextStatement.htm", true);
			xmlhttp.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			<!--xmlhttp.send(query);
			-->
			xmlhttp.send();
		}
	</script>
</body>
</html>