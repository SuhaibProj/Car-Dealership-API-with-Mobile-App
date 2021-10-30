<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>>
	<meta charset="UTF-8">
	<title>Suhaib's Car Dealership Server</title>
	<link rel="stylesheet" href="css/site.css">
	<style>
		body {	margin: 0;	}
		.nav-bar {
  			overflow: hidden;
  			background-color: #571B7E;
  			colour: yellow
		}
		input[type=text] {
			float:right;
			margin-right: 5px;
  			width: 10%;
  			padding: 8px;
  			display: inline-block;
  			box-sizing: border-box;
		}
		input[type=password] {
			float:right;
			margin-right: 10px;
  			width: 10%;
  			padding: 8px;
  			display: inline-block;
  			box-sizing: border-box;
		}
		input[type=submit] {
			float:right;
			width: 10%;
  			background-color: #4CAF50;
 			color: white;
 			padding: 8px;
 			cursor: pointer;
		}
		input[type=submit]:hover {	background-color: #45a049;	}
	</style>
</head>
<body bgcolor = #B7CEEC>
	<div class="nav-bar">
		<form action="admin" method="POST">
  			--<input type="submit" value="Login"/>
  			--<input type="password" name="password" placeholder="Password">
  			<input type="text" name="username" placeholder="UserName">
  		</form>
	</div>
	<h1>Suhaib's Car DealerShip Server</h1>
	<h2 align = center>Viewing all available vehicles!</h2>
	<br>
	<table border = 2, cellpadding=10, align = center>
		<tr>
			<th>Vehicle ID</th>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Price</th>
			<th>License Plate</th>
			<th>Colour</th>
			<th>Doors</th>
			<th>Transmission</th>
			<th>Mileage</th>
			<th>Fuel type</th>
			<th>Engine Size</th>
			<th>Body Style</th>
			<th>Condition</th>
			<th>Notes</th>
		</tr>
		<v:forEach items="${allVehicles}" var="v">
		<tr>
			<td>${v.getVehicle_id()}</td>
			<td>${v.getMake()}</td>
			<td>${v.getModel()}</td>
			<td>${v.getYear()}</td>
			<td>${v.getPrice()}</td>
			<td>${v.getLicense()}</td>
			<td>${v.getColour()}</td>
			<td>${v.getDoors()}</td>
			<td>${v.getTransmission()}</td>
			<td>${v.getMileage()}</td>
			<td>${v.getFuel()}</td>
			<td>${v.getEngine()}</td>
			<td>${v.getBody()}</td>
			<td>${v.getCondition()}</td>
			<td>${v.getNotes()}</td>
		</tr>
		</v:forEach>
	</table>
</body>
</html>