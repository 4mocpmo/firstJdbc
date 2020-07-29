package com.servlet;

import com.model.Manufacturer;
import com.service.ManufacturerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "com.servlet.NewManufacturerServlet" , urlPatterns = {"/newManufacturer"})
public class NewManufacturerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacturerService manufacturerService = new ManufacturerService();
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (name.length() == 0 ){
            out.write("<h><font size = 12 color = RED> The NAME should not be empty </font></h>");
            out.write("<a href=\"NewManufacturer.html\">BACK</a><br/><br/>");
        }
        else if (manufacturerService.save(new Manufacturer(name,city,street))){
            out.write("<h1>This Manufacturer Save Successfully</h1>");
            out.write("<p1>   NAME : " + name.toUpperCase() + "</p1>");
            out.write("<p>    CITY : " + city.toUpperCase() + "</p>");
            out.write("<p>  STREET : " + street.toUpperCase() +  "</p>");
            out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");
        }
        else {
            out.write("<h1><font size = 12 color = RED >This NAME already exists  </font></h1>");
            out.write("<a href=\"NewManufacturer.html\">BACK</a><br/><br/>");
        }
        out.write("</body>");
        out.write("</html>");
    }
}
