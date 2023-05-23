<!-- <%@ taglib prefix="c" uri="jakarta.tags.core" %> -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <style>
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

        body {
            margin: 0;
        }

        header {
            background-color: rgb(126, 36, 110);
            color: #FFF;
            padding: 20px;
            text-align: center;
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


        form {
            /* display: flex; */
            /* flex-direction: column; */
            align-items: center;
            padding-left: 10%;
            margin-left: 30%;
            padding-bottom: 3%;
            width: 30vw;
            border: 1px solid #ccc;
            border-radius: 10px;
        }

        label {
            font-size: 1.2rem;
            margin-left: 2%;
        }

        input[type="date"] {
            /* padding: 5px; */
            font-size: 1.1rem;
            border: none;
            border-radius: 5px;
            box-shadow: 0 0 5px #ccc;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            padding: 5px 10px;
            background-color: #0077FF;
            color: #fff;
            font-size: 1.1rem;
            border: none;
            margin-left: 20%;
            border-radius: 5px;
            cursor: pointer;
        }

        h3 {
            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            /* font-style: italic; */
            color: rgb(24, 29, 17);
        }

        .pageHead {
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
    <br>
    <br>
    <form onsubmit="return validateDates()" action="${pageContext.request.contextPath}/employee/addNewBooking/Rooms" id="form"
        method="post">
        <span id="error" class="error"></span><br>
        <h3>Book a room</h3>
        <label for="user">User:</label>
		<select id="user" name="userId">
			<c:forEach items="${listUsers}" var="item">
				<option value="${item.getUserId()}">${item.getFirstName()} ${item.getLastName()}</option>
			</c:forEach>
		</select>
        <br>
        <label>Check-in date:</label>
        <input type="date" name="checkin" id="checkin" required><br>
        <label>Check-out date:</label>
        <input type="date" name="checkout" id="checkout" required><br>
        <input type="hidden" name="empHotelBook" id="empHotelBook" value="${hotelId}">
        <input type="submit" value="Search">
    </form>
</body>
<script>
    function validateDates() {
        console.log(document.getElementById("checkin").value);
        console.log(document.getElementById("checkout").value);
        var checkInDate = new Date(document.getElementById("checkin").value);
        var checkOutDate = new Date(document.getElementById("checkout").value);
        console.log(checkInDate);
        console.log(checkOutDate);
        if (checkInDate >= checkOutDate) {
            document.getElementById("error").innerHTML = "Check-in date must be less than check-out date";
            return false;
        }
        return true;
    }
</script>


</html>