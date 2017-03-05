<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Cards</title>

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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

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

<!--  Header Begins  -->
<div class="container">
<div class="row">
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



</div>
</div>
<!--  Header closes  -->
<br>

<div class="container">
<div class="row" id="serverResponse" ></div>
<div class="row">
<form:form commandName="creditCard" action="processCardRegistration.htm" method="post"><!-- onsubmit="return false" -->
<h3>Credit Card Registration</h3>

<div class="col-lg-12">

<table class="table">

<tr>
<td>
Register with Account
</td><td>
 <select class="selectpicker" data-style="btn-primary" id="toAccount" name="toAccount">
		  <optgroup label="Saving">
		  <c:forEach items="${sessionScope.customer.bankAccounts}" var="account" >
		  <c:if test="${account.accountType=='Savings'}">
		   <option value="${account.accountNumber}">${account.accountNumber}</option>
		   </c:if>
			</c:forEach>
		    
		  </optgroup>
		  <optgroup label="Current">
		  <c:forEach items="${sessionScope.customer.bankAccounts}" var="account" >
		  <c:if test="${account.accountType=='Current'}">
		   <option value="${account.accountNumber}">${account.accountNumber}</option>
		   </c:if>
			</c:forEach>
		  </optgroup>
	 </select>

</td></tr>
<tr><td>Credit Card Number:</td><td><form:input path="cardNumber" type="text" name="cardNumber" id="cardNumber"  maxlength="16" size="18" required="true"/><br>
<font color="red"><form:errors path="cardNumber"/></font>
</td></tr>

<tr>
<td>Credit Card Name:</td><td>
<select class="selectpicker" data-style="btn-primary" id="cardName" name="cardName">
		  <option value="Gold Card">Gold Card</option>
		  <option value="Platinum Card">Platinum Card</option>
		  <option value="Titanium Card">Titanium Card</option>
		  <option value="Amex Card">Amex Card</option>
	</select>


</td>

</tr>
<tr>
<td>CVV</td><td><form:input  path="cvv" type="text" id="cvv" name="cvv" required="true"  maxlength="3" size="5"/><br>
<font color="red"><form:errors path="cvv"/></font>
</td>
</tr>

<tr>
<td>Pin Code</td><td><form:input path="pinCode" type="text" id="pinCode" name="pinCode" required="true" maxlength="5" size="7"/><br>
<font color="red"><form:errors path="pinCode"/></font></td>
</tr>

<tr>
<td>Valid From</td><td><form:input path="validFrom" placeholder="YYYY-MM-DD" type="date" id="validFrom" name="validFrom" required="true" /><br>
<font color="red"><form:errors path="validFrom"/></font>
</td>
</tr>

</table>



</div>



<div class="col-lg-12"><input type="submit"  class="btn btn-success" value="Add" /></div><!--  onclick="addCard()"-->
</form:form>
</div>

</div>

<script>
<!--var xmlhttp;
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
function addCard() {
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
	 
	 
	 var toAccount =$("#toAccount").val();
	 var cardNumber=$("#cardNumber").val();
	 var cardName = $("#cardName").val();
	 var cvv=$("#cvv").val();
	 var pinCode=$("#pinCode").val();
	 var validFrom=$("#validFrom").val();
	 
	 console.log("toAccount "+toAccount+" cardNumber "+cardNumber+" cardName "+cardName);
	  query="toAccount="+toAccount+"&cardNumber="+cardNumber+"&cardName="+cardName+"&cvv="+cvv+"&pinCode="+pinCode+"&validFrom="+validFrom;
	  
	  console.log(query);
	  xmlhttp.onreadystatechange=function() {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
	    	
	    	
	    	
	    	var json = JSON.parse(xmlhttp.responseText)
	    	console.log(json.status);
	    	/*console.log(json.status.trim());*/
	    	if(json.status){
	    		var insertText="<p class='bg-success'>"+json.returnMessage +"</p>";
		    	document.getElementById("serverResponse").innerHTML=insertText;
	    	}else{
	    		var insertText="<p class='bg-danger'>"+json.returnMessage +"</p>";
		    	document.getElementById("serverResponse").innerHTML=insertText;
	    	}
	    	
	    }
	  }
	  xmlhttp.open("POST","processCardRegistration.htm",true);
	  xmlhttp.setRequestHeader("content-type", "application/x-www-form-urlencoded");
      <!--xmlhttp.send(query);
	  xmlhttp.send(query);
	}-->

</script>
</body>
</html>