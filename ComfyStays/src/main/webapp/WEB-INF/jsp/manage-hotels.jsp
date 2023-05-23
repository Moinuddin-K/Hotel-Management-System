<!-- <%@ taglib prefix="c" uri="jakarta.tags.core" %> -->
<!DOCTYPE html>
<html>

<head>
	<title>Manage Hotels</title>
	<style>
		table {
			border-collapse: collapse;
			width: 100%;
		}

		th,
		td {
			text-align: left;
			padding: 8px;
		}

		th {
			background-color: #4CAF50;
			color: white;
		}

		tr:nth-child(even) {
			background-color: #f2f2f2;
		}

		/* Add border to the table */
		table,
		th,
		td {
			border: 1px solid black;
		}

		p {
			animation: hide 5s forwards;
		}

		@keyframes hide {
			0% {
				opacity: 1;
			}

			100% {
				opacity: 0;
			}
		}

		body {
			margin: 0;
		}

		.logout {
			position: absolute;
			top: 20px;
			right: 20px;
			background-color: #333;
			color: #FFF;
			padding: 10px 20px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			cursor: pointer;
			border-radius: 5px;
		}

		.logout:hover {
			background-color: #555;
		}

		header {
			background-color: rgb(126, 36, 110);
			color: #FFF;
			padding: 15px;
			text-align: center;
		}

		.add-hotel-form {
			border: 1px solid #333;
			border-radius: 5px;
			background-color: lightblue;
			width: 30%;
			padding-left: 40px;
			margin-left: 25px;
			margin-top: 10px;
			padding-top: 5px;
			padding-bottom: 5px;
		}

		.add-hotel-form input {
			margin-top: 5px;
		}

		.addHotelButton {
			background-color: rgb(11, 23, 133);
			color: #FFF;
			border-radius: 5px;
			padding: 10px 20px;
			margin-left: 20px;
		}

		#addButton {
			background-color: rgb(11, 23, 133);
			color: #FFF;
			border-radius: 5px;
			padding: 10px 20px;
			margin-left: 20px;
		}

		.headMessage {
			margin-left: 2%;
			font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
			font-weight: lighter;
			color: rgb(42, 96, 102);
		}
	</style>
</head>

<body>
	<header>
		<h1>ComfyStays</h1>
		<a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
	</header>
	<h1 class="headMessage">Manage Hotels</h1>
	<input type="hidden" name="message" id="message" value="${message}">
	<div>
		<button id="addButton" onclick="showAddHotelForm()">Add Hotel</button>
	</div>

	<div id="add-hotel-form" class="add-hotel-form" style="display:none">
		<form method="post" action="${pageContext.request.contextPath}/admin/manage-hotels/addHotel"
			modelAttribute="addHotel">
			<label for="hotel-name">Hotel Name:</label><br>
			<input type="text" id="hotelName" name="hotelName" required><br>

			<label for="address">Hotel Address:</label><br>
			<input type="text" id="address" name="address" required><br>

			<label for="city">City:</label><br>
			<input type="text" id="city" name="city" required><br>

			<label for="state">State:</label><br>
			<input type="text" id="state" name="state" required><br>

			<label for="zipCode">Zip Code:</label><br>
			<input type="text" id="hotel-zip" name="zipCode" required><br>

			<label for="contactNo">Contact Number:</label><br>
			<input type="text" id="contactNo" name="contactNo" required><br>

			<input type="submit" value="Add" class="addHotelButton">
		</form>
	</div>

	<p>${message}</p>
	<table>
		<thead>
			<tr>
				<th>Hotel Name</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>Zip Code</th>
				<th>Contact Number</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${hotelList}" var="item">
				<form method="post" action="${request.getServletPath}/ComfyStays/admin/manage-hotels/task"
					modelAttribute="hotel">
					<tr>
						<td>${item.getHotelName()}</td>
						<td>${item.getAddress()}</td>
						<td>${item.getCity()}</td>
						<td>${item.getState()}</td>
						<td>${item.getZipCode()}</td>
						<td>${item.getContactNo()}</td>
						<!-- <td style="display: none;" id="hotelId">${item.getHotelId()}</td> -->
						<input type="hidden" name="hotelId" id="hotelId" value="${item.getHotelId()}">
						<td>
							<button type="submit" name="action" id="addRooms" value="addRooms">Add Rooms</button>
							<button type="submit" name="action" id="modifyRooms" value="modifyRooms">Modify</button>
							<!-- <button type ="submit" name ="action" id = "deleteRooms" value="deleteRooms">Delete</button> -->
						</td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>

	<script>
		function showAddHotelForm() {
			document.getElementById("add-hotel-form").style.display = "block";
			document.getElementById("modify-hotel-form").style.display = "none";
			document.getElementById("delete-hotel-form").style.display = "none";
			document.getElementById("addButton").disabled = true;
		}

	</script>
</body>

</html>