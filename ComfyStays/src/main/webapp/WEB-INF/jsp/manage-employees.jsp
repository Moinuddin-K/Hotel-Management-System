<%@ taglib prefix="c" uri="jakarta.tags.core" %>
	<!DOCTYPE html>
	<html>

	<head>
		<title>Manage Hotels</title>
		<style>
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

			p {
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

			.addEmpbutton {
				background-color: rgb(11, 23, 133);
				color: #FFF;
				border-radius: 5px;
				padding: 10px 20px;
				margin-left: 20px;
				text-decoration: none;
			}

			.headMessage {
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
		<h1 class="headMessage">Manage Employees</h1>

		<a href="signup.htm?type=employee" class="addEmpbutton">Add Employee</a>

		<p>${message}</p>
		<table>
			<thead>
				<tr>
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email ID</th>
					<th>Hotel Id</th>
					<th>Hotel Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eee}" var="item">
					<form method="post" action="${request.getServletPath}/ComfyStays/admin/manage-employees/assign">
						<tr>
							<td>${item.getUserId()}</td>
							<td>${item.getFirstName()}</td>
							<td>${item.getLastName()}</td>
							<td>${item.getEmailId()}</td>
							<td>${item.getAssignedHotel().getHotelId()}</td>
							<td>${item.getAssignedHotel().getHotelName()}</td>
							<td>
								<button type="submit" name="assign" id="assign" value="${item.getUserId()}">Assign
									Hotel</button>
							</td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>

	</body>

	</html>