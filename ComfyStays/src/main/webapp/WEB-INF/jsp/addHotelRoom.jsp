<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>

    <head>
      <title>Add Room</title>
      <style>
        body {
          margin: 0;
        }

        form label {
          display: block;
          margin-top: 20px;
        }

        form input[type="text"] {
          padding: 10px;
          border: 2px solid #ddd;
          border-radius: 4px;
          width: 30vw;
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

        .formSubmit{
          margin-left: 10px;
        }
      </style>
    </head>

    <body>
      <header>
        <h1>ComfyStays</h1>
        <a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
      </header>
      <h1 class="headMessage">Add Room to hotel - ${hotelName}</h1>

      <form action="addRoom/submit" modelAttribute="Room" method="post" class="formSubmit">
        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType">

        <label for="numberOfGuests">Number of Guests:</label>
        <input type="text" id="numberOfGuests" name="numberOfGuests">

        <label for="roomPrice">Price:</label>
        <input type="text" id="roomPrice" name="roomPrice">

        <input type="hidden" name="hotelId" id="hotelId" value="${hotelId}">

        <input type="submit" value="Submit">
      </form>
    </body>

    </html>

    <!-- <!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Room</title>
</head>
<body>
    <h1>Add Room to hotel - ${hotelName}</h1>
    <form action="addRoom/submit" modelAttribute="Room" method="post">

        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType"><br>

        <label for="numberOfGuests">Number of Guests:</label>
        <input type="text" id="numberOfGuests" name="numberOfGuests"><br>
        
        <label for="roomPrice">Price:</label>
        <input type="text" id="roomPrice" name="roomPrice"><br>
        
        <input type="hidden" name="hotelId" id="hotelId" value="${hotelId}">

        <input type="submit" value="Submit">
      </form>
</body>
</html> -->