<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Room Booked successfully</title>
    <style>
		body{
			margin: 0;
		}
        .button {
			background-color: #333;
			border: none;
			color: #FFF;
			padding: 10px 20px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin-top: 20px;
			cursor: pointer;
			border-radius: 5px;
		}

		.button:hover {
			background-color: #555;
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

		.bookingId{
			color: rgb(21, 207, 21);
		}

		.bookingHotel{
			color: rgb(34, 154, 66);
		}

		header{
			background-color: rgb(126, 36, 110);
			padding: 2%;
		}

		.head{
			padding-left: 3%;
			color: aliceblue;
			font-size: larger;
			font-weight: lighter;
		}

		.success{
			font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
			font-weight: lighter;
			color: rgb(12, 87, 108);
		}

		.homeLink{
			color: aliceblue;
			padding-left: 5px;
			text-decoration: none;
		}
    </style>
</head>
<body>
    <header>
		<span class="homeLink"><a class="homeLink" href="${request.getServletPath}/ComfyStays/employee/employee-dashboard.htm">Home</a></span>
		<span class="head">ComfyStays</span>
		<a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
	</header>
	<p class="success">Congratulations!!</p>
	<p>Booking confirmed with booking id <span class="bookingId">${bookingId}</span> at <span class="bookingHotel">${bookingHotelName}</span>.</p>
    
</body>
</html>