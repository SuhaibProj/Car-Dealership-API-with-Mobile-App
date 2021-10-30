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
		}
		.nav-bar a {
  			float: left;
  			color: #f2f2f2;
  			text-align: center;
  			padding: 10px;
  			text-decoration: none;
  			font-size: 17px;
		}
		.nav-bar a:hover {
  			background-color: #ddd;
  			color: black;
		}
		.nav-bar a.active {
			float: right;
  			background-color: #4CAF50;
  			color: white;
		}
		.nav-bar a.active:hover {	background-color: #45a049;	}
		h3 {
			color: red
		}	
	</style>
</head>
<body bgcolor = #ddd>
	<div class="nav-bar">
  		<a class="active" type="submit" href="./home">Logout</a>
  		<a href="./sales">Sales Table</a>
  		<a href="./create">Create</a>
  		<a href="./update">Update</a>
  		<a href="./delete">Delete</a>
	</div>
	<h3>Admin Mode: </h3>
	<h1>Suhaib's Car DealerShip Server</h1>
	<h2 align = center>Viewing all available vehicles!</h2><br>
	<table border = 2 cellpadding=10 align = center>
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