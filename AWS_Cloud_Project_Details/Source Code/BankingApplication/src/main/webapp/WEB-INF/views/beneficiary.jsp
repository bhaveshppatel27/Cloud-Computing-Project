<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Beneficiaries</title>

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
      <a class="navbar-brand" id="brand" href="#">
       Northeastern Bank
       <!--  <img alt="Brand" src="${pageContext.request.contextPath}/resources/images/northeastern-university-logo.jpg">-->
      </a>
     
     <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">${requestScope.customer.firstName} ${requestScope.customer.lastName}</a></p><br><br>
    </div>
  </div>
</nav>


<div class="container">
<div class="row">

<Header after navbar.This too can be added as a tile-->
<div class="col-lg-10">


Hello, ${sessionScope.customer.firstName}
</div>
<div class="col-lg-1">
<form method="post" action="customerHome.htm"><input type="submit" class="btn btn-info"value="Home"> </form>
</div>
<div class="col-lg-1">
<form:form action="logout.htm">
						<input type="submit" class="btn btn-success" name="logout" value="Logout" />
					</form:form>
</div>

<!--  Header closes  -->

</div>
<div class="row">

<div class="panel-group" id="accordion">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
        Add New Beneficiary</a>
      </h4>
    </div>
    <div id="collapse1" class="panel-collapse collapse in">
    <div class="panel-body">
      
     <form  onsubmit="return false" method="post">
      <div class="row" id="addedSuccessfully" ></div>
      <div class="row" id="failedToAdd" ></div>
      <div class="row">
      <div class="col-lg-2">Account Number</div><div class="col-lg-4"><input required="" type="text" id="accountNumber" name="accountNumber"/></div>
      </div>
      
      <div class="row">
      <div class="col-lg-2">Email Address</div><div class="col-lg-4"><input type="text" id="emailId" name="emaiId"/></div>
      </div>
      <div class="row">
      <div class="col-lg-12"><input type="submit" onclick="addBeneficiary()" class="btn btn-success" value="Add" /></div>
      </div>
     
     </form>
     
      
      </div>
    </div>
  </div>
  <div class="panel panel-success">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
        View Beneficiary</a>
      </h4>
    </div>
    <div id="collapse2" class="panel-collapse collapse">
      <div class="panel-body">
		<table class="table">
		<th>Beneficiary Name</th><th>Account Number</th><th>Delete</th>
		<c:forEach items="${sessionScope.customer.beneficiaryList}" var="beneficiary" >
		<tr><td>${beneficiary.belongToCustomer.firstName} ${beneficiary.belongToCustomer.lastName} </td><td>${beneficiary.accountNumber}</td><td><input type="submit" class="btn btn-danger" value="Delete"/></td></tr>
		</c:forEach>
		</table>

	</div>
    </div>
  </div>
  <!-- <div class="panel panel-warning">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
        Delete Beneficiary</a>
      </h4>
    </div>
    <div id="collapse3" class="panel-collapse collapse">
      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
      commodo consequat.</div>
    </div>
  </div> -->
</div>


</div>
</div>

<script>
var xmlhttp;
xmlhttp=xmlHttpObject();

function xmlHttpObject(){
	var xmlHttp = null;
	if (window.XMLHttpRequest) {
	    // code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp=new XMLHttpRequest();
	  } else {  // code for IE6, IE5
	    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
    return xmlHttp;
}
function addBeneficiary() {
	console.log("In Function");
	if (xmlhttp == null) {
        alert("Your browser does not support AJAX");
        return;
    }
	
	 /*if (str.length==0) { 
	    document.getElementById("livesearch").innerHTML="";
	    document.getElementById("livesearch").style.border="0px";
	    return;
	  }*/
	 
	 var accountNumber=document.getElementById("accountNumber").value;
	 var emailId=document.getElementById("emailId").value;
	 console.log("Account Number"+accountNumber+" EmailId"+emailId);
	  query="accountNumber="+accountNumber+"&emailId="+emailId;
	  
	  console.log(query);
	  xmlhttp.onreadystatechange=function() {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
	    	
	    	
	    	
	    	var json = JSON.parse(xmlhttp.responseText)
	    	console.log(json.status);
	    	/*console.log(json.status.trim());*/
	    	if(json.status){
	    		$("addedSuccessfully").fadeIn(10);
	    		var insertText="<p class='bg-success'>"+json.returnMessage +"</p>";
		    	document.getElementById("addedSuccessfully").innerHTML=insertText;
		    	$("#addedSuccessfully").fadeOut(6000);
	    	}else{
	    		$("#failedToAdd").fadeIn(10);
	    		var insertText="<p class='bg-danger'>"+json.returnMessage +"</p>";
		    	document.getElementById("failedToAdd").innerHTML=insertText;
		    	$( "#failedToAdd" ).fadeOut(4000);
	    	}
	    	
	    }
	  }
	  xmlhttp.open("POST","addBeneficiary.htm",true);
	  xmlhttp.setRequestHeader("content-type", "application/x-www-form-urlencoded");
      <!--xmlhttp.send(query);-->
	  xmlhttp.send(query);
	}


</script>
</body>
</html>