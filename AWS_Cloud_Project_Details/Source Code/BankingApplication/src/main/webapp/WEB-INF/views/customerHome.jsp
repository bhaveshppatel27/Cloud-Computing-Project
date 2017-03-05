<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${requestScope.customer.firstName}!</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/customer-style.css">
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

	<!-- Navbar header common in all Customer pages.Can be replaced as Navbar tile using Apache Tiles-->
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

	<!-- <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>-->
	<div class="container">
		<div class="row">

			<Header after navbar.This too can be added as a tile-->
			<div class="col-lg-11">Hello,
				${sessionScope.customer.firstName}</div>
			<div class="col-lg-1">
				<form:form action="logout.htm">
					<input type="submit" class="btn btn-success" name="logout"
						value="Logout" />
				</form:form>
			</div>

			<!--  Header closes  -->
			<div class="col-lg-12">
				A glance at your Accounts:
				<table class="table table-hover">
					<tr>
						<th>Account Type</th>
						<th>Account Number</th>
						<th>Account Balance As of Today</th>
					</tr>
					<c:forEach items="${sessionScope.customer.bankAccounts}"
						var="account">
						<tr>
							<td><span class="label label-warning">${account.accountType}</span></td>
							<td>${account.accountNumber}</td>
							<td>$ ${account.accountBalance}</td>
					</c:forEach>
				</table>
			</div>
		</div>

		<!-- New row for panels for user actions -->
		<div class="row">

			<div class="col-lg-6">

				<div class="panel-group">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
								<a data-toggle="collapse" href="#collapse4">Banking</a>
							</h4>
						</div>
						<div id="collapse4" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">Make Transactions:</div>
								</div>
								<div class="row">
									<div class="col-lg-6">

										<form action="makeTransfer.htm" method="post">
											<input type="submit" class="btn btn-success"
												value="Make Fund  Transfer"><br> </input>
										</form>
									</div>

									<div class="col-lg-6">
										<form action="beneficiary.htm" method="post">
											<input type="submit" class="btn btn-success"
												value="Manage Benefeciary"> </input>
										</form>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</div>
			<div class="col-lg-6">

				<div class="panel-group">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
								<a data-toggle="collapse" href="#collapse2">Statement</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="col-lg-12">
									<!-- <form action="viewStatement.htm" method="post">
										<input type="submit" class="btn btn-success"
											value="View Statement"> View Statement</input>
									</form> -->

									<div class="row">
										For Account:
										<div class="col-lg12">
											<form action="generateStatement.htm" method="post">
												<select class="selectpicker" data-style="btn-warning"
													id="forAccount" name="forAccount">
													<optgroup label="Saving">
														<c:forEach items="${sessionScope.customer.bankAccounts}"
															var="account">
															<c:if test="${account.accountType=='Savings'}">
																<option value="${account.accountNumber}">${account.accountNumber}</option>
															</c:if>
														</c:forEach>

													</optgroup>
													<optgroup label="Current">
														<c:forEach items="${sessionScope.customer.bankAccounts}"
															var="account">
															<c:if test="${account.accountType=='Current'}">
																<option value="${account.accountNumber}">${account.accountNumber}</option>
															</c:if>
														</c:forEach>
													</optgroup>
												</select> 
												<input type="hidden" value="${sessionScope.statementOffset}" id="statementOffset" name="statementOffset"/>
												<input type="submit" class="btn btn-warning" />
											</form>
										</div>
									</div>
								</div>


							</div>

						</div>
					</div>
				</div>

			</div>

		</div>
		<div class="row">

			<div class="col-lg-6">

				<div class="panel-group">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>

								<a data-toggle="collapse" href="#collapse3"> Cards</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row">

									<div class="col-lg-12">Manage your cards:</div>
								</div>
								<div class="row">
									<div class="col-lg-6">
										<form action="viewCards.htm" method="post">
											<input type="submit" class="btn btn-primary"
												value="View Registered Cards"><br> </input>
										</form>
									</div>

									<div class="col-lg-6">
										<form action="registerCard.htm" method="post">
											<input type="submit" class="btn btn-primary"
												value="Register a Credit Card"> </input>
										</form>
									</div>
								</div>


							</div>

						</div>
					</div>
				</div>

			</div>
			<div class="col-lg-6">

				<div class="panel-group">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								<a data-toggle="collapse" href="#collapse1">Profile</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">Manage profile details:</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<form action="manageProfile.htm" method="post">
											<input type="submit" class="btn btn-danger"
												value="Manage Profile"> </input>
										</form>
									</div>
								</div>




							</div>

						</div>
					</div>
				</div>


			</div>

		</div>

		<div class="row">
			<div class="col-lg-6">
				${requestScope.requestDeliveryStatus}<br>

				<form:form method="post" action="sendRequest.htm">
					<select class="selectpicker" data-style="btn-warning"
						id="requestType" name="requestType">
						<option value="Banking Issues">Banking Issues</option>
						<option value="Cards">Card Issues</option>
						<option value="New Cheque Book">Cheque Book</option>
						<option value="Access Issue">Access Issues</Access>
						<option value="other">Other</option>
					</select>

					
				</form:form>


			</div>
			<br>
			<div class="col-lg-6">
			<form method="post" action="chequeDeposit.htm"  method="post">
			
			<input type="submit" class="btn btn-info" value="Send Image for Cheque deposit "/>
			</form>
			</div>
		</div>
	</div>

</body>
</html>