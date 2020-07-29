package com.servlet;

import com.model.Manufacturer;
import com.model.Part;
import com.service.ManufacturerService;
import com.service.PartService;
import org.postgresql.util.PSQLException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NewPartServlet" , urlPatterns = {"/newPart"})
public class NewPartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PartService partService = new PartService();
        PrintWriter out = response.getWriter();
        String manufacturerName = request.getParameter("manufacturerName");
//        String partNum = request.getParameter("partNum");

        List<Part> partList = new ArrayList<>();
        partList.addAll(partService.findAll());
        int partNum = partList.size() + 1;
        String description = request.getParameter("description");
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (manufacturerName.length() == 0 ){
            out.write("<h><font size = 12 color = RED> The NAME should not be empty </font></h>");
            out.write("<a href=\"NewPart.html\">BACK</a><br/><br/>");

        }
        else {
            try {
                ManufacturerService manufacturerService = new ManufacturerService();
                if (manufacturerService.search(manufacturerName)){
                    if (partService.save(new Part(partNum, description, manufacturerName))) {
                        out.write("<h1>This Part Save Successfully</h1>");
                        out.write("<p1>   MANUFACTURER NAME : " + manufacturerName.toUpperCase() + "</p1>");
                        out.write("<p>    NUMBER OF PART : " + partNum + "</p>");
                        out.write("<p2>   DESCRIPTION : " + description + "  </p2><p/>");
                        out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");

                    }
                    else {
                        out.write("<p><font size = 16 color = RED>this part num already exists</font></p>");
                        ArrayList<Part> list = new ArrayList<>();
                        list.addAll(partService.findAll());
                        int  j = 1 ;
                        out.write("<a href=\"NewPart.html\">BACK</a><br/><br/>");
                        out.write("<p><font size = 12 color = BLUE> part list  : </font></p>");
                        for (Part i: list) {
                            out.write("<p>"+ j + "&#128073" + i + "</p>");
                            j++;
                        }
                    }
                }
                else {
                    out.write("<p><font size = 16 color = RED>this manufacturer name does not exists" + "</font></p>");
                    ArrayList<Manufacturer> list = new ArrayList<>();
                    list.addAll(manufacturerService.findAll());
                    out.write("<a href=\"NewPart.html\">BACK</a><br/><br/>");
                    out.write("<p><font size = 12 color = BLUE> manufacturer list  : </font></p>");
                    int j = 1;
                    for (Manufacturer i :list) {
                        out.write("<p>"+ j + "&#128073" + i + "</p>");
                        j++;
                    }
                }

            } catch (Exception e) {
                out.write("<p>NUMBER OF PART should be type of number</p>");
                out.write("<a href=\"NewPart.html\">BACK</a><br/><br/>");

            }
        }
        out.write("</body>");
        out.write("</html>");
    }
}
