<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<%
    PrintWriter writer = response.getWriter();
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    String time = date.format(format);
    writer.write("<p><font color=#fff >"  +  time + "</font></p>");
%>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta charset="UTF-8">
    <title>LIST</title>
</head>
<body align="right">
    <a href="NewManufacturer.html">&#9679 ADD NEW MANUFACTURER</a><br/><br/><br/>
    <a href="NewPart.html">&#9679 Add NEW PART PRODUCED BY SPECIFIC MANUFACTURER</a><br/><br/><br/>
    <a href="NewCustomer.html"> &#9679 ADD NEW CUSTOMER</a><br/><br/><br/>
    <a href="NewOrder1.html">&#9679 ADD NEW ORDER</a><br/><br/><br/>
    <a href="DeleteManufacturer.html">&#9679 DELETE MANUFACTURER</a><br/><br/><br/>
    <a href="DeleteCustomer.html">&#9679 DELETE CUSTOMER</a><br/><br/><br/>
</body>
</html>
