<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

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

	<!-- sign in modal begins -->

	<!-- Button trigger modal -->

	<div class="container">
		<!-- <hr class="prettyline">-->
		<br>
		<center>
			<div class="col-lg-4" id="loginButton">
			<!-- <h3>Please login by clicking on the below button</h3>-->
			<br>
			<button class="btn btn-primary btn-lg" href="#signup"
				data-toggle="modal" style="margin-top: 500px;" data-target=".bs-modal-sm">Sign In</button>
			
			</div>
		</center>
		<br>
		<!-- <hr class="prettyline">-->
	</div>


	<!-- Modal -->
	<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<br>
				<div class="bs-example bs-example-tabs">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active"><a href="#signin" data-toggle="tab">Sign
								In</a></li>
						<li class=""><a href="#signup" data-toggle="tab"
							onclick="getCommandClass()">Register</a></li>
						<li class=""><a href="#why" data-toggle="tab">Help!</a></li>
					</ul>
				</div>
				<div class="modal-body">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in" id="why">
							<p>Forgot password/Locked out?</p>
							<p>Follow the below link and raise a request to us.We will
								get back to you shortly</p>
							<br>
							<form action="raiseRequest.htm" method="get">
								<input type="submit" class="btn btn-info" value="Make Request" />
							</form>

						</div>
						<div class="tab-pane fade active in" id="signin">

							<form:form class="form-horizontal" action="signIn.htm"
								commandName="user" method="post">
								<fieldset>
									<!-- Sign In Form -->
									<!-- User Type -->

									<!-- Please select your user type.<br>
									
									
									<!-- Text input-->
									<div class="control-group">
										<font color="red"><form:errors path="*" /></font><br> <label
											class="control-label" for="userid">User ID:</label>
										<div class="controls">
											<form:input path="userName" id="userid" 
												type="text" class="form-control input-medium"
												placeholder="User ID" required="true" value="${cookie.userNameCookie.value }" />

										</div>
									</div>

									<!-- Password input-->
									<div class="control-group">
										<label class="control-label" for="passwordinput">Password:</label>
										<div class="controls">
											<form:input path="password" required="true" id="passwordinput"
												 class="form-control input-medium"
												type="password" value="${cookie.passwordCookie.value }" placeholder="********" />
										</div>

									</div>

									<input type="checkbox" name="rememberMe" id="rememberMe"
										value="rememberMe"> Remember me

									<!-- Multiple Checkboxes (inline) -->







									<!-- Button -->
									<div class="control-group">
										<label class="control-label" for="signin"></label>
										<div class="controls">
											<button id="signin" name="signin" class="btn btn-success">Sign
												In</button>
										</div>
									</div>
								</fieldset>
							</form:form>
						</div>

						<div class="tab-pane fade" id="signup">
							<form:form action="signUp.htm" commandName="user" method="post"
								class="form-horizontal">
								<fieldset>
									<!-- Sign Up Form -->
									<!-- Text input-->
									<div class="control-group">
										<label class="control-label" for="Email">Email:</label>
										<div class="controls">
											<form:input path="emailId" id="emaiId" name="emailId"
												class="form-control input-large" type="text"
												placeholder="abc@xyz.com" required="" />
										</div>
									</div>

									<!-- Text input-->
									<div class="control-group">
										<label class="control-label" for="userName">User ID:</label>
										<div class="controls">
											<form:input path="userName" id="userName" name="userName1"
												class="form-control input-large" type="text"
												placeholder="userId" required="" />
										</div>
									</div>

									<!-- Text input-->
									<div class="control-group">
										<label class="control-label" for="accountNumber">Account
											Number:</label>
										<div class="controls">
											<input id="accountNumber" name="accountNumber"
												class="form-control input-large" type="text"
												placeholder="1234567890" required />
										</div>
									</div>

									<!-- Password input-->
									<div class="control-group">
										<label class="control-label" for="password">Password:</label>
										<div class="controls">
											<form:input path="password" id="password" name="password"
												class="form-control input-large" type="password"
												placeholder="********" required="" />
											<em>1-8 Characters</em>
										</div>
									</div>

									<!-- Text input-->
									<div class="control-group">
										<label class="control-label" for="reenterpassword">Re-Enter
											Password:</label>
										<div class="controls">
											<input id="reenterpassword" class="form-control input-large"
												name="reenterpassword" type="password"
												placeholder="********" required="" data-toggle="tooltip"
												data-placement="right" title="Please enter same as above">
										</div>
									</div>



									<!-- Button -->
									<div class="control-group">
										<label class="control-label" for="confirmsignup"></label>
										<div class="controls">
											<button id="confirmsignup" name="confirmsignup"
												class="btn btn-success">Sign Up</button>
										</div>
									</div>
								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</center>
				</div>
			</div>
		</div>
	</div>



	<!-- sign in modal ends -->


	<!-- <P>The time on the server is ${serverTime}.</P> -->
	<script>
		var xmlHttp;
		xmlHttp = GetXmlHttpObject();

		function GetXmlHttpObject() {
			var xmlHttp = null;
			try {
				// Firefox, Opera 8.0+, Safari
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return xmlHttp;
		}

		function getCommandClass() {
			if (xmlHttp == null) {
				alert("Your browser does not support AJAX");
				return;
			}

		}
	</script>
</body>

</html>
