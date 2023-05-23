<!-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   -->

<!-- <%@page contentType="text/html" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
  <head>
	<title>ComfyStays - Hotel Booking Management</title>
	<meta charset="UTF-8">
    <style>
      /* Style for the page */
      body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f2f2f2;
      }
      
      /* Style for the header */
      .top {
        background-color: #060809;
        color: #eaeaea;
        padding: 10px;
        text-align: center;
        display: flex;
        justify-content: space-between;
        align-items: center;
		height:10vh;
      }
      
      /* Style for the buttons */
      .button-container {
        display: flex;
        align-items: center;
      }
      
      .button {
        display: inline-block;
        padding: 12px 24px;
        background-color: #007bff;
        color: #fff;
        font-size: 16px;
        text-decoration: none;
        border-radius: 4px;
        margin: 20px;
      }
      
      .button:hover {
        background-color: #0056b3;
      }
      
      /* Style for the image */
      .image-container {
        position: relative;
        height: 90vh;
        background-image: url("https://www.w3schools.com/w3images/hotel.jpg");
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
      }
      
      .image-overlay {
        position: absolute;
        top: 0;
        left: 0;
        height: 100%;
        width: 100%;
        background-color: rgba(0, 0, 0, 0.4);
        display: flex;
        align-items: center;
        justify-content: center;
      }
      
      .image-overlay h1 {
        color: #ffffff;
        font-size: 48px;
        text-align: center;
        margin: 0;
      }
    </style>
  </head>
  <body>
    <div class="top">
      <h1>ComfyStays</h1>
      
      <div class="button-container">
        <a href="login.htm" class="button">Log In</a>
        <a href="signup.htm?type=user" class="button">Sign Up</a>
      </div>
    </div>
    
    <main>
      <div class="image-container">
        <div class="image-overlay">
          <h1>Welcome to ComfyStays</h1>
        </div>
      </div>
	  <!-- <canvas id="myCanvas" width="200" height="100" style="border:1px solid #000000;">
		</canvas> -->
      
      <!-- <p>Our website offers an easy and convenient way to book and manage your hotel reservations. With a user-friendly interface and a wide selection of hotels, you can find the perfect accommodations for your next trip.</p> -->
    </main>
  </body>
</html>


<!-- <!DOCTYPE html>
<head>
	<title>ESD Demo</title>
</head>
<body>
	<p>Hotel Management System with jsp</p>
	<a href="login.htm">Login</a>
	<a href="signup.htm?type=user">Signup</a>
</body> -->
<!-- <!DOCTYPE html>
<html>
  <head>
    <title>Hotel Booking Management</title>
    <style>
      /* Style for the page */
      body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f2f2f2;
      }
      
      /* Style for the header */
      header {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
      }
      
      /* Style for the buttons */
      .button {
        display: inline-block;
        padding: 12px 24px;
        background-color: #007bff;
        color: #fff;
        font-size: 16px;
        text-decoration: none;
        border-radius: 4px;
        margin: 20px;
      }
      .button:hover {
        background-color: #0056b3;
      }
    </style>
  </head>
  <body>
    <header>
      <h1>Hotel Booking Management</h1>
    </header>
    
    <main>
      <h2>Welcome!</h2>
      <p>Our website offers an easy and convenient way to book and manage your hotel reservations. With a user-friendly interface and a wide selection of hotels, you can find the perfect accommodations for your next trip.</p>
      
      <div>
        <a href="login.htm" class="button">Log In</a>
        <a href="signup.htm?type=user" class="button">Sign Up</a>
      </div>
    </main>
  </body>
</html> -->