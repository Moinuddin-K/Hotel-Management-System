<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Assign Hotel to Employee</title>
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

            .selectList {
                border: 1px solid #333;
                border-radius: 5px;
                background-color: lightblue;
                width: 30%;
                padding-left: 40px;
                margin-left: 25px;
                margin-top: 10px;
                padding-top: 5px;
                padding-bottom: 5px;
            }

            .assignSubmit{
                background-color: rgb(11, 23, 133);
				color: #FFF;
				border-radius: 5px;
				padding: 10px 20px;
				margin-left: 20px;
				text-decoration: none;
            }
        </style>
    </head>

    <body>
        <header>
            <h1>ComfyStays</h1>
            <a href="${request.getServletPath}/ComfyStays/logout" class="logout">Logout</a>
        </header>
        <h1 class="headMessage">Assign Hotel to Employee</h1>
        <form method="post" class="selectList"
            action="${request.getServletPath}/ComfyStays/admin/manage-employees/assign/submit">
            <c:forEach items="${listH}" var="item" varStatus="status">
                <input type="radio" id="hotelIdForm" name="hotelIdForm" value="${item.getHotelId()}" ${status.first ? 'checked' : ''}>
                <label for="hotelIdForm">${item.getHotelName()}</label><br>
            </c:forEach>
            <input type="hidden" name="userEmpId" value="${userEmpId}">
            <button type="submit" name="assignSubmit" id="assignSubmit" class="assignSubmit">Assign Hotel</button>
        </form>

    </body>

    </html>