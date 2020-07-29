<%@ page import="com.service.PartService" %>
<%@ page import="com.model.Part" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: MOSTAFA
  Date: 3/31/2020
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD NEW ORDER</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form method="post" action="newOrder">
    <table><tr>
        <td>
            <lable for="partNum">NUMBER OF PART </lable>
        </td>
        <td>
            <lable><input id="partNum" type="text" name="partNum"></lable>
        </td>
    </tr> <tr>
        <td>
            <lable for="quantity"> QUANTITY </lable>
        </td>
        <td>
            <lable><input id="quantity" type="text" name="quantity"></lable>
        </td>
    </tr><tr>
        <td>
            <a href="NewOrder1.html">BACK</a><br/><br/>
        </td>
        <td>
            <input type="submit" value="save data"/>
        </td>
    </tr>
    </table>
</form>

<%
    PartService partService = new PartService();
    List<Part> list = new ArrayList<>();
    list.addAll(partService.findAll());
    PrintWriter writer = response.getWriter();
    int j = 1;
    writer.write("<p><font size = 17 color=green> part list : </font></p>");
    for (Part i :list) {
        writer.write("<p><font color=black>" + j + "&#128073" + i + "</font></p>");
        j++;
    }
%>
</body>
</html>
