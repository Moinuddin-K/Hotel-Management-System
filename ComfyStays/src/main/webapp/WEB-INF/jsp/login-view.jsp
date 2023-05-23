<!-- <!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
</head>
<body>
	<h1>Login Page</h1>
	<form method="post" action="login">
		<label>Username:</label>
		<input type="text" name="username"><br>
		<label>Password:</label>
		<input type="password" name="password"><br>
		<input type="submit" value="Login">
	</form>
</body>
</html> -->

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f3f3f3;
            /* background-image: url("https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG90ZWx8ZW58MHx8MHx8&w=1000&q=80"); */
            opacity:  1;
            width: 100%;
            /* background-image: "; */
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        img {
            max-width: 100%;
            height: auto;
            margin-bottom: 30px;
        }

        form {
            width: 100%;
            max-width: 500px;
            padding: 40px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        h1 {
            margin: 0 0 30px;
            font-size: 36px;
            color: #333;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-size: 18px;
            color: #333;
        }

        select {
            /* display: block; */
            display: flexbox;
            margin-bottom: 10px;
            font-size: 15px;
            color: #060c78;
            background: rgba(209, 223, 231, 0.3);
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
            
            /* box-shadow: #ff6200; */
        }

        input[type="text"], input[type="password"] {
            display: block;
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            font-size: 16px;
            color: #333;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
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

    </style>
	<!-- <link rel="stylesheet" href="../styles/login_view_styles.css"> -->
</head>
<body>
	<div class="container">
		<!-- <img src="https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG90ZWx8ZW58MHx8MHx8&w=1000&q=80" alt="Travel Image"> -->
		<form method="post" action="login.htm" modelAttribute="user">
			<h1>Login</h1>
            <p>${error}</p>
            <!-- <label>Login as a:</label>
            <select>
                <option>User</option>
                <option>Employee</option>
                <option>Admin</option>
            </select> -->
			<label for="email">Email</label>
			<input type="text" name="emailId" id="emailId" required>
			<label for="password">Password</label>
			<input type="password" name="password" id="password" required>
			<input type="submit" value="Login">
            <span>Not a User? <a href="${pageContext.request.contextPath}/signup.htm">Signup</a></span>
		</form>
        
	</div>
</body>
</html>
