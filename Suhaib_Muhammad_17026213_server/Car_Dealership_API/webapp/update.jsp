<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suhaib Car Dealership Server</title>
</head>
<body>
	<h2>Updating Vehicle Data</h2>
	<form method="POST" action="./update">
		<label>ID</label><br>
			<input type="text" name="vehicle_id"><br>
		<label>Make</label><br>
			<input type="text" name="make"><br>
		<label>Model</label><br>
			<input type="text" name="model"><br>
		<label>Year</label><br>
			<input type="text" name="year"><br>
		<label>Price</label><br>
			<input type="text" name="price"><br>
		<label>License</label><br>
			<input type="text" name="license_number"><br>
		<label>Colour</label><br>
			<input type="text" name="colour"><br>
		<label>Doors</label><br>
			<input type="text" name="number_doors"><br>
		<label>Transmission</label><br>
			<input type="text" name="transmission"><br>
		<label>Mileage</label><br>
			<input type="text" name="mileage"><br>
		<label>Fuel</label><br>
			<input type="text" name="fuel_type"><br>
		<label>Engine</label><br>
			<input type="text" name="engine_size"><br>
		<label>Body</label><br>
			<input type="text" name="body_style"><br>
		<label>Condition</label><br>
			<input type="text" name="condition"><br>
		<label>Notes</label><br>
			<input type="text" name="notes"><br>
		<label>-----------</label><br>
			<input type="submit"><br>
	</form>
</body>
</html>