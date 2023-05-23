<%@ taglib prefix="c" uri="jakarta.tags.core" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Employee Dashboard</title>
		<style>
			body {
				margin: 0;
			}

			.error {
				color: red;
			}

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

			header {
				background-color: rgb(126, 36, 110);
				color: #FFF;
				padding: 20px;
				text-align: center;
			}

			.message {
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

			.button:hover {
				background-color: #e0d6d6;
				color: #f2f2f2;
			}

			.logout {
				position: absolute;
				top: 30px;
				right: 20px;
				background-color: #f4f2f2;
				color: #201d26d5;
				padding: 10px 20px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
				font-size: 16px;
				cursor: pointer;
				border-radius: 5px;
			}

			.logout:hover {
				background-color: #50f0f0;
			}

			.hotelName {
				font-size: larger;
				font-weight: bolder;
			}

			.pageHead {
				margin-left: 2%;
				font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
				font-weight: lighter;
				color: rgb(42, 96, 102);
			}

			.addUserButton{
				background-color: #0b2a9b;
				color: #d7ffffd5;
				padding: 10px 20px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
				font-size: 16px;
				cursor: pointer;
				border-radius: 5px;
				margin-left: 10px;
			}

			.addUserButton:hover {
				background-color: #7f6972;
			}

			.newBookingButton{
				background-color: #0b2a9b;
				color: #d7ffffd5;
				font-size: 16px;
				cursor: pointer;
				border-radius: 5px;
				padding: 10px 20px;
				margin-left: 50px;
			}

			.block{
				display: flex;
				flex-direction: row;
			}
		</style>
	</head>

	<body>
		<header>
			<h1>ComfyStays</h1>

			<c:if test="${not empty empHotel}">
				<span id="hotelName">${empHotel.getHotelName()}</span>
			</c:if>
			<c:if test="${empty empHotel}">
				<p>Not assigned any hotel yet. Contact Admin to get assigned</p>
			</c:if>
			<a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
		</header>
		<h1 class="pageHead">Welcome ${employeename},</h1>
		<!-- <h2>Employee Information</h2>
		<p>Name: ${employeename}</p>
		<p>Email: ${employeeemail}</p> -->
		<div class="block">
			<a href="signup.htm?type=user" class="addUserButton">Add User</a>

			<form action="${request.getServletPath}/ComfyStays/employee/addNewBooking" method="post">
				<button class="newBookingButton">Make new Booking</button>
				<input type="hidden" name="empHotelBook" id="empHotelBook" value="${empHotel.getHotelId()}">
			</form>
		</div>
		

		<h2>Bookings</h2>
		<p class="message">${deletedMessage}</p>
		<c:if test="${not empty bookingsEmp}">
			<table>
				<thead>
					<tr>
						<th>Booking ID</th>
						<th>Hotel Name</th>
						<th>Room Type</th>
						<th>Check-in Date</th>
						<th>Check-out Date</th>
						<th>Price Per night (in $)</th>
                        <th>Total Price (in $)</th>
						<th>Cancel</th>
					</tr>
				</thead>
				<tbody class="bookingTable">
					<c:forEach items="${bookingsEmp}" var="booking">
						<form method="post" action="${request.getServletPath}/ComfyStays/employee/cancel"
							modelAttribute="hotel">
							<tr>
								<td>${booking.getBookingId()}</td>
								<td>${booking.getHotel().getHotelName()}</td>
								<td>${booking.getRoom().getRoomType()}</td>
								<td>${booking.getCheckInDate()}</td>
								<td>${booking.getCheckOutDate()}</td>
								<td>${booking.getRoom().getRoomPrice()}</td>
                                <td>${booking.getPrice()}</td>
								<input type="hidden" name="bookingId" id="bookingId" value="${booking.getBookingId()}">
								<td>
									<button type="submit" name="action" id="cancelBooking" value="cancelBooking">Cancel
										Booking</button>
								</td>
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty bookingsEmp}">
			<p>Hotel has no bookings yet.</p>
		</c:if>
	</body>

	</html>