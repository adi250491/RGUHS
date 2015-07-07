<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Genarated College Report</title>
</head>
<body onload="window.print()">

	<div>
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>Sr.No.</th>
				<th>Institute Name</th>
				<th>Contact Number</th>
				<th>College Email</th>
				<th>Contact Person</th>
				<th>Mobile Number</th>
				<th>Institute Address</th>
				<th>District</th>

			</tr>
			<%
				int i = 1;
			%>
			<s:iterator value="affInstList">
				<tr>
					<td style="margin-left: 20px; font-size: small;"><%=i%></td>
					<td style="margin-left: 10px; font-size: small;"><s:property
							value="instName" /></td>

					<td style="margin-left: 10px; font-size: small;"><s:property
							value="contactNumber" /></td>

					<td style="margin-left: 10px; font-size: small;"><s:property
							value="email" /></td>
					<td style="margin-left: 10px; font-size: small;"><s:property
							value="contactPerson" /></td>
					<td style="margin-left: 10px; font-size: small;"><s:property
							value="mobileNum" /></td>
					<td style="margin-left: 10px; font-size: small;"><s:property
							value="instAddress" /></td>
					<td style="margin-left: 10px; font-size: small;"><s:property
							value="place" /></td>

				</tr>
				<%
					i++;
				%>

			</s:iterator>

		</table>
	</div>
</body>
</html>