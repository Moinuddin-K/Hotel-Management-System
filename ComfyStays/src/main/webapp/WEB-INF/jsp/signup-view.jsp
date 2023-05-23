<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <title>Sign up</title>
      <style>
        /* Add some basic styling to the page */
        body {
          font-family: Arial, sans-serif;
          margin: 0;
          padding: 0;
        }

        h1 {
          margin: 0 0 30px;
          font-size: 36px;
          color: #333;
          text-align: center;
        }

        form {
          max-width: 700px;
          margin: 20px auto;
          padding: 20px;
          border: 1px solid #ccc;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
          display: block;
          margin-bottom: 10px;
          font-size: 18px;
          color: #333;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
          display: block;
          width: 95%;
          padding: 10px;
          margin-bottom: 20px;
          border: none;
          border-radius: 5px;
          box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
          font-size: 16px;
          color: #333;
        }

        input[type="submit"] {
          background-color: #ff6200;
          color: #fff;
          padding: 15px 30px;
          border: none;
          border-radius: 5px;
          font-size: 18px;
          font-weight: bold;
          cursor: pointer;
          transition: background-color 0.2s ease;
        }

        input[type="submit"]:hover {
          background-color: #e55900;
        }

        /* span {} */
      </style>
    </head>

    <body>
      <form modelAttribute="user" action="signup.htm?type=${type}" method="post">
        <h1>Signup</h1>
        <p style="color: red;">${error}</p>
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>
        <label for="email">Email:</label>
        <input type="email" id="emailId" name="emailId" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <label for="confirmPassword">Confirm Password:</label>
        <span id='message'></span>
        <input type="password" id="confirmPassword" onkeyup="check()" name="confirmPassword" required >
        
        <input type="submit" id="submitButton" style="background-color: gray;" disabled value="Sign Up">
        <span>Already have an account?<a href="${pageContext.request.contextPath}/login.htm">Login</a></span>
      </form>
    </body>

    <script>
      function check() {
        const pass = document.getElementById("password").value;
        const confPass = document.getElementById("confirmPassword").value;
        if(pass != confPass){
          document.getElementById("message").innerHTML="Password doesn't match";
          document.getElementById("message").style.color='red';
        }else{
          document.getElementById("message").innerHTML="Password matches";
          document.getElementById("message").style.color='green';
          document.getElementById("submitButton").disabled=false;
          document.getElementById("submitButton").style.backgroundColor='#e55900';
        }
      }
    </script>
    </html>