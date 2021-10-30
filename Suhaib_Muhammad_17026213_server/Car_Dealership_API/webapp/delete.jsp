<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suhaib Car Dealership Server</title>
</head>
<body>
	<h2>Deleting Vehicle Data</h2>
	<form method="POST" action="./delete" >
		<p>Insert Vehicle ID number to delete vehicle</p>
		<label>Vehicle ID</label><br>
		<input type="text" name="id"><br>
		<input type="submit" value="DELETE"/><br>
	</form>
</body>
</html>