<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
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
		.nav-bar a.active:hover {	background-color: #45a049;	}	
	</style>
</head>
<body bgcolor = #B7CEEC>
	<div class="nav-bar">
  		<a href="./home">Home</a>
	</div>
	<h2 align = center>Viewing all Sales Data!</h2>
	<br>
	<table border = 2 cellpadding=10 align = center>
		<tr>
			<th>Vehicle ID</th>
			<th>Sold Date</th>
			<th>Sold Price</th>
			<th>Status Information</th>
		</tr>
		<c:forEach items="${allSales}" var="c">
		<tr>
			<td>${c.getVehicle_id()}</td>
			<td>${c.getSoldDate()}</td>
			<td>${c.getSoldPrice()}</td>
			<td>${c.getStatus()}</td>
		</tr>	
		</c:forEach>
	</table>
</body>
</html>