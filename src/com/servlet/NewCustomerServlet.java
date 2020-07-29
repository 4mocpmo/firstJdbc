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
import java.util.List;

@WebServlet(name = "com.servlet.NewCustomerServlet" , urlPatterns = {"/newCustomer"})
public class NewCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        String _name = request.getParameter("name");
//        String _id = request.getParameter("id");
        List<Customer> customerList = new ArrayList<>();
        customerList.addAll(customerService.findAll());
        int _id = customerList.size() + 1;
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (_name.length() == 0 ){
                out.write("<h><font size = 16 color = red >Name should not be empty</font></h><br/>");
            out.write("<a href=\"NewCustomer.html\">BACK</a><br/><br/>");
        }
        else {
            try {
                if (customerService.save(new Customer(_id, _name))) {
                    out.write("<h1>This Customer Save Successfully</h1>");
                    out.write("<p1>   NAME : " + _name.toUpperCase() + "</p1>");
                    out.write("<p>   ID : " + _id + "</p>");
                    out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");
                } else {
                    out.write("<h1><font size = 12 color = RED >This ID already exists  </font></h1>");
                    out.write("<a href=\"NewCustomer.html\">BACK</a><br/><br/>");
                }
            } catch (Exception e) {
                out.write("<h><font size = 12 color  = RED>ID should be number type </font></h>");
                out.write("<a href=\"NewCustomer.html\">BACK</a><br/><br/>");
            }
        }
        out.write("</body>");
        out.write("</html>");
    }
}