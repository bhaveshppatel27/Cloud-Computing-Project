<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${requestScope.customer.firstName}!</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
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


</head>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" id="brand" href="#"> Northeastern Bank
					Testing Server </a> <br> <br>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="row">

			<div class="col-sm-4 col-md-4 col-lg-4">

				<form action="fixedUsersTesting" method="GET">
				<input type ="hidden" value ="3" name = "users">
					<input type="submit" class="btn btn-success" value="Login 3 Users" />
				</form>

				<form action="fixedUsersTesting" method="GET">
				<input type ="hidden" value ="10" name = "users">
					<input type="submit" class="btn btn-success" value="Login 10 Users" />
				</form>

				<form action="fixedUsersTesting" method="GET">
				<input type ="hidden" value ="17" name = "users">
					<input type="submit" class="btn btn-success" value="Login 17 Users" />
				</form>

				<form action="fixedUsersTesting" method="GET">
				<input type ="hidden" value ="24" name = "users">
					<input type="submit" class="btn btn-success" value="Login 24 Users" />
				</form>

			</div>

			<div class="col-sm-4 col-md-4 col-lg-4">
				<form action="usersTesting" method="GET">
					<div class="form-group">
						<label for=users>Input Number Of users to login:</label> <input
							type="number" class="form-control" id="users" name="users">
					</div>
					<input type="submit" class="btn btn-success" />
				</form>
			</div>

		</div>

	</div>

</body>
</html>
