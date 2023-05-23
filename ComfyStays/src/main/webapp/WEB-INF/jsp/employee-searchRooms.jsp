<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Search for Rooms</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F5F5F5;
            margin: 0;
        }
        
        p {
            color: #333;
        }
        
        span {
            font-weight: bold;
            color: #008CBA;
        }

        .tile-container {
            margin-left: 5%;
            margin-right: 5%;
            align-items: center;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(350px, 3fr));
            grid-gap: 30px;
        }

        .tile {
            background-color: #7cdce5;
            border-radius: 5px;
            padding: 50px;
            height: 100px;
            margin-left: 5%;
            margin-right: 5%;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }

        .tile h2 {
            margin-top: 0;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            color: rgb(120, 26, 71);
            font-weight: lighter;
        }

        .tile p {
            margin-bottom: 0;
        }

        .tile img {
            width: 100%;
            height: auto;
            border-radius: 5px;
            margin-top: 10px;
        }

        .addRoomButton{
            width: 100%;
            background-color: blue;
            color: white;
            border-radius: 10px;
            margin-top: 2%;
            padding-top: 5px;
            padding-bottom:5px ;
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
        
        header {
			background-color: rgb(126, 36, 110);
			color: #FFF;
			padding: 15px;
			text-align: center;
		}
    </style>
</head>
<body>
    <header>
        <h1>ComfyStays</h1>
        <a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
    </header>
    <p>Showing rooms available from <span name="checkInDate" value="${checkInDate}">${checkInDate}</span> to 
        <span name="checkOutDate" value="${checkOutDate}">${checkOutDate}</span></p>
    <div class="tile-container">
        <c:forEach var="item" items="${rooms}">
            <div class="tile">
                <form action="${pageContext.request.contextPath}/employee/addNewBooking/Rooms/book.htm" method="post">
                    <h2>${item.getHotel().getHotelName()} - ${item.getRoomType()}</h2>
                    <p>Price -  $${item.getRoomPrice()}/night.</p>
                    <input type="hidden" name="roomId" value="${item.getRoomId()}">
                    <input type="hidden" name="checkInDate" value="${checkInDate}">
                    <input type="hidden" name="checkOutDate" value="${checkOutDate}">
                    <input type="hidden" name="noOfDays" value="${noOfDays}">
                    <input type="hidden" name="userEmpId" value="${userEmpId}">
                    <button type="submit" class="addRoomButton">Book</button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>