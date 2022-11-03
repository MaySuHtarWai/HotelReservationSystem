<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Reservation System</title>
    <link rel="stylesheet" href="css/bootstrapmin.css">
    <link rel="stylesheet" href="css/reservation.css">
    <script src="js/bootstrapmin.js"></script>
</head>

<body>
    <%
        String roomId = request.getAttribute("roomId").toString();
        String checkInDate = request.getAttribute("checkInDate").toString();
        String checkOutDate = request.getAttribute("checkOutDate").toString();
        Double price = Double.parseDouble(request.getAttribute("price").toString());
        
        // calculate number of day
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(checkInDate);
        Date d2 = sdf.parse(checkOutDate);
        int numberOfDays = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        
        double totalPrice = numberOfDays * price;        
    %>
    
    <div class="header lead">
        <h1>Final Reservation</h1>
        <p>Please, fill the form for complete the reservation process.</p>
    </div>

    <div class="container">
        <div class="alert alert-info">
            <strong>Room No: </strong>${room_id}
        </div>
        <div class="alert alert-info">
            <Strong>From: </Strong><span id='checkInDate'>${checkInDate}</span><br>
            <Strong>To: </Strong><span id='checkOutDate'>${checkOutDate}</span><br>
            <Strong>Number of days: </Strong><span id='numberOfDays'>${numberOfDays}</span><br>
            <strong>Price for one night: </strong><span id='price'>${price} Kyats</span><br>
            <strong>Total price: </strong> <span id='totalPrice'>${totalPrice} Kyats</span>
        </div>
        <form action="reservation" method="POST">
            <input type="hidden" name="roomId" value="${roomId}">
            <input type="hidden" name="checkInDate" value="${checkInDate}">
            <input type="hidden" name="checkOutDate" value="${checkOutDate}">
            <div class="form-group">
                <label for="name">name</label>
                <input type="text" class="form-control" name="name" id="name" placeholder="Type full name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Type your email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="phone" class="form-control" name="phone" id="phone" placeholder="Type phone number">
            </div>
            <div>
                <button type="submit" class="btn btn-success">Submit</button>
                <a href="<%= request.getContextPath() %>"><button class="btn btn-secondary" style="display: block; float: right; margin-bottom: 15px">Back to Home</button></a>
            </div>
        </form>
    </div>
            

</body>
</html>
