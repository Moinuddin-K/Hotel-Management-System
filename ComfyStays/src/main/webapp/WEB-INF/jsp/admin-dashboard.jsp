<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@page contentType="text/html" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html>

		<head>
			<title>Admin Dashboard</title>
			<style>
				body {
					font-family: Arial, sans-serif;
					background-color: #F5F5F5;
					margin: 0;
					padding: 0;
				}

				header {
					background-color: rgb(126, 36, 110);
					color: #FFF;
					padding: 20px;
					text-align: center;
				}

				.container {
					display: flex;
					flex-wrap: wrap;
					justify-content: center;
					margin-top: 50px;
				}

				.card {
					background-color: #FFF;
					box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
					max-width: 300px;
					margin: 10px;
					text-align: center;
					font-size: 20px;
					padding: 20px;
				}

				.card:hover {
					box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
				}

				.card h2 {
					font-size: 24px;
					margin-bottom: 20px;
				}

				.card p {
					font-size: 18px;
					margin-top: 20px;
					margin-bottom: 20px;
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
			</style>
		</head>

		<body>
			<header>
				<h1>ComfyStays</h1>
				<p>Admin Dashboard</p>
				<a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
			</header>

			<div class="container">
				<div class="card">
					<h2>Hotels</h2>
					<p>Manage hotels</p>
					<a href="${pageContext.request.contextPath}/admin/manage-hotels" class="button">Manage Hotels</a>
				</div>

				<div class="card">
					<h2>Employees</h2>
					<p>Manage employees</p>
					<a href="${pageContext.request.contextPath}/admin/manage-employees" class="button">Manage
						Employees</a>
				</div>

				<div class="card">
					<h2>Bookings</h2>
					<p>Manage bookings</p>
					<a href="${pageContext.request.contextPath}/admin/manage-bookings" class="button">Manage
						Bookings</a>
				</div>
			</div>


		</body>

		</html>