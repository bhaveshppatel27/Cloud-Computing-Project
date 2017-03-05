<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Added</title>
</head>
<body>

${requestScope.customer.firstName} ${requestScope.customer.lastName} added.

Return <form action="signIn.htm" method="post"><input type="submit" value="Add another Customer"/></form>

</body>
</html>