<!DOCTYPE html>
<html>

<head>
    <title>Update Hotel</title>
    <style>
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

        .headMessage {
            margin-left: 2%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-weight: lighter;
            color: rgb(42, 96, 102);
        }

        form label {
          display: block;
          margin-top: 20px;
        }

        form input[type="text"] {
          padding: 10px;
          border: 2px solid #ddd;
          border-radius: 4px;
          width: 15vw;
        }

        form input[type="submit"] {
          background-color: #008080;
          color: #fff;
          border: none;
          padding: 10px 20px;
          border-radius: 4px;
          margin-top: 20px;
          cursor: pointer;
        }

        form {
			/* display: flex; */
			/* flex-direction: column; */
			align-items: center;
			padding-left: 2%;
			margin-left: 20%;
			padding-bottom: 3%;
			width: 30vw;
			border: 1px solid #ccc;
			border-radius: 10px;
		}
    </style>
</head>

<body>
    <header>
        <h1>ComfyStays</h1>
        <a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
    </header>
    <h1 class="headMessage">Update Hotel - ${hotel.getHotelName()}</h1>
    <form method="post" action="${pageContext.request.contextPath}/admin/manage-hotels/updateHotel"
        modelAttribute="updateHotel">
        <label for="hotel-name">Hotel Name:</label><br>
        <input type="text" id="hotelName" name="hotelName" value="${hotel.getHotelName()}"><br>

        <label for="address">Hotel Address:</label><br>
        <input type="text" id="address" name="address" value="${hotel.getAddress()}"><br>

        <label for="city">City:</label><br>
        <input type="text" id="city" name="city" value="${hotel.getCity()}"><br>

        <label for="state">State:</label><br>
        <input type="text" id="state" name="state" value="${hotel.getState()}"><br>

        <label for="zipCode">Zip Code:</label><br>
        <input type="text" id="hotel-zip" name="zipCode" value="${hotel.getZipCode()}"><br>

        <label for="contactNo">Contact Number:</label><br>
        <input type="text" id="contactNo" name="contactNo" value="${hotel.getContactNo()}"><br>

        <input type="hidden" name="hotelId" id="hotelId" value="${hotel.getHotelId()}">

        <input type="submit" value="Update">
    </form>
</body>

</html>