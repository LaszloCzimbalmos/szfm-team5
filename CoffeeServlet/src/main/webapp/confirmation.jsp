<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table Booking Confirmation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles/confirmStyle.css">
</head>
<body>
<h1>Table Booking Confirmation</h1>

<p>Dear ${name},</p>
<p>Your booking was successful! You have been assigned Table Number: <strong>${tableNumber}</strong>.</p>
<p>We look forward to seeing you at the café.</p>
</body>
</html>