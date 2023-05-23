<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Manage Bookings</title>
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
        <h1 class="headMessage">Manage Bookings</h1>
        <input type="hidden" name="message" id="message" value="${message}">

        <h2 class="headMessage">Bookings</h2>
        <p class="message">${deletedMessage}</p>
        <c:if test="${not empty bookingsAdm}">
            <table>
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Hotel Name</th>
                        <th>Room Type</th>
                        <th>Check-in Date</th>
                        <th>Check-out Date</th>
                        <th>Price Per night (in $)</th>
                        <th>Total Price (in $)</th>
                        <th>Cancel</th>
                    </tr>
                </thead>
                <tbody class="bookingTable">
                    <c:forEach items="${bookingsAdm}" var="booking">
                        <form method="post" action="${request.getServletPath}/ComfyStays/admin/cancel"
                            modelAttribute="hotel">
                            <tr>
                                <td>${booking.getBookingId()}</td>
                                <td>${booking.getHotel().getHotelName()}</td>
                                <td>${booking.getRoom().getRoomType()}</td>
                                <td>${booking.getCheckInDate()}</td>
                                <td>${booking.getCheckOutDate()}</td>
                                <td>${booking.getRoom().getRoomPrice()}</td>
                                <td>${booking.getPrice()}</td>
                                <!-- <td>${booking.getAvailability()}</td> -->
                                <input type="hidden" name="bookingId" id="bookingId" value="${booking.getBookingId()}">
                                <td>
                                    <button type="submit" name="action" id="cancelBooking" value="cancelBooking">Cancel
                                        Booking</button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty bookingsAdm}">
            <p>There are no bookings yet.</p>
        </c:if>

    </body>

    </html>