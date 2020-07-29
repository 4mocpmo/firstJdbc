package com.servlet;

import com.model.Customer;
import com.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "NewOrder1Servlet", urlPatterns = {"/newOrder1"})
public class NewOrder1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        CustomerService customerService = new CustomerService();
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (_id.length() == 0){
            out.write("<h><font size = 12 color = black>  ID should not be empty </font></h><br/>");
            out.write("<a href=\"NewOrder1.html\"><font size = 16 color  = RED >BACK</font></a><br/><br/>");
        }
        else {
            try{
                int id = Integer.parseInt(_id);
                if (customerService.search(id)){
                    out.write("<p><font size = 12 color = green> found id " + "&#10004" + "</font></p>");
                    NewOrderServlet newOrderServlet = new NewOrderServlet();
                    newOrderServlet.setId(id);
                    out.write("<a href=\"NewOrder.jsp\"><font size = 16>complete order  </font></a><br/><br/>");

                }
                else {
                    out.write("<p><font size = 12 color = RED> this id of customer does not exists</font></p>");
                    ArrayList<Customer> list = new ArrayList<>();
                    out.write("<a href=\"NewCustomer.html\"><font size = 17 >Register new customer</font></a><br/><br/>");
                    list.addAll(customerService.findAll());
                    int j = 1;
                    out.write("<p><font size = 12 color = BLUE> customer list  : </font></p>");
                    for (Customer i : list) {
                        out.write("<p><font size = 16>" + j + "&#128073" + i + "</font></p>");
                        j++;
                    }
                    out.write("<a href=\"NewOrder1.html\"><font size = 16 >BACK</font></a><br/><br/>");
                }
            }catch (Exception e){
                out.write("<h><font size = 12 color = RED>ID should be type of number </font></h>");
                out.write("<a href=\"NewOrder1.html\">BACK</a><br/><br/>");
            }
        }
        out.write("</body>");
        out.write("</html>");
    }

}
