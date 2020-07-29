package com.servlet;

import com.model.Customer;
import com.model.Manufacturer;
import com.service.ManufacturerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DeleteManufacturerServlet" ,urlPatterns = {"/deleteManufacturer"})
public class DeleteManufacturerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacturerService manufacturerService = new ManufacturerService();
        String name = request.getParameter("name");
        ArrayList<Manufacturer> list = new ArrayList<>();
        list.addAll(manufacturerService.findAll());
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        boolean flag = false;
        for (Manufacturer i :list) {
            if (i.getName().equals(name)){
                if (manufacturerService.delete(name)) {
                    list.remove(i);
                    out.write("<h1><font size = 12 color = GREEN >This manufacturer Successfully delete  </font></h1>");
                    out.write("<h1><font size = 12 >Name : " + i.getName() + " </font></h1>");
                    out.write("<h1><font size = 12 >City : " + i.getCity() + " </font></h1>");
                    out.write("<h1><font size = 12 >Street : " + i.getStreet() + " </font></h1>");
                    out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");
                    flag = true;
                    break;
                }
            }
        }
        if (name.length() != 0) {
            if (!flag) {
                out.write("<h1><font size = 12 color = RED >This NAME does not exists  </font></h1>");
                out.write("<a href=\"DeleteManufacturer.html\">BACK</a><br/><br/>");
                int j = 1;
                out.write("<p><font size = 12 color = BLUE> manufacturer list  : </font></p>");
                for (Manufacturer i : list) {
                    out.write("<p>" + j + "&#128073" + i + "</p>");
                    j++;
                }
            }
        }
        else {
            out.write("<h1><font size = 12 color = RED >NAME should not be empty  </font></h1>");
            out.write("<a href=\"DeleteManufacturer.html\">BACK</a><br/><br/>");
        }

        out.write("</body>");
        out.write("</html>");
    }
}
