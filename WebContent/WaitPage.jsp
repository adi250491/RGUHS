<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Wait</title>
</head>
<body onload="SetTimeout()">
<center><h2>Please Wait</h2></center>
<script >
function SetTimeout()
{
	 window.setTimeout(function(){
	        window.location = '<%=request.getAttribute("redirectLink").toString()%>';
	},01);
}
</script>
</body>
</html>