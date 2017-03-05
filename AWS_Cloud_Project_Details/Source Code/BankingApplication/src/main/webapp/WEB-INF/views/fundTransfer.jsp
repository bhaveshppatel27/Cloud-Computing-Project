<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banking</title>
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

	<div class="container">
		<div class="row">

			<div class="col-lg-12">
				<div class="panel-group">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a>Fund Transfer</a>
							</h4>
						</div>
						<form:form action="fundTransfer.htm" commandName="transaction"
							method="POST">
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-4">
										From<br> <select class="selectpicker"
											data-style="btn-primary" name="fromAccount">
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

									</div>
									<div class="col-lg-4">
										To<br> <select class="selectpicker"
											data-style="btn-success" name="toAccount">

											<c:forEach items="${sessionScope.customer.beneficiaryList}"
												var="beneficiary">

												<option value="${beneficiary.accountNumber}">${beneficiary.accountNumber}
													- ${beneficiary.belongToCustomer.firstName}
													${beneficiary.belongToCustomer.lastName}</option>

											</c:forEach>



										</select>
									</div>
									<div class="col-lg-4">
										Amount<br>
										<form:input path="amount" type="text" name="amount"
											required="" />
										<br> <font color="red"><form:errors path="*" /></font> <font
											color="red"> ${requestScope.transferError} </font>

									</div>
								</div>

								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="description">Short Description:</label>
											<textarea class="form-control" rows="5" id="description"
												name="description"></textarea>
										</div>

									</div>

								</div>
								<br>
								<div class="row">
									<div class="col-lg-2">

										<c:choose>
											<c:when
												test="${sessionScope.customer.beneficiaryList.size()>0}">
												<input type="submit" value="Submit" class="btn btn-primary" />
											</c:when>
											<c:when
												test="${sessionScope.customer.beneficiaryList.size()==0}">No beneficiary available to transfer</c:when>
										</c:choose>


									</div>

								</div>



							</div>
						</form:form>

						<div class="panel-footer">Panel Footer</div>

					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>