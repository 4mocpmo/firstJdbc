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

@WebServlet(name = "DeleteCustomerServlet" , urlPatterns = {"/deleteCustomer"})
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        ArrayList<Customer> list = new ArrayList<>();
        list.addAll(customerService.findAll());
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (id.length() == 0){
            out.write("<h1><font size = 12 color = RED >ID should not be empty  </font></h1>");
            out.write("<a href=\"DeleteCustomer.html\">BACK</a><br/><br/>");
        }
        else {
            boolean flag = false;
            try {
                for (Customer i : list) {
                    if (i.getId() == Integer.parseInt(id)) {
                        customerService.delete(Integer.parseInt(id));
                        out.write("<h1><font size = 12 color = GREEN >This Customer Successfully delete  </font></h1>");
                        list.remove(i);
                        out.write("<h1><font size = 12 >Name : " + i.getName() + " </font></h1>");
                        out.write("<h1><font size = 12 >ID : " + i.getId() + " </font></h1>");
                        flag = true;
                        out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");
                        break;
                    }
                }
                if (!flag){
                    out.write("<h1><font size = 12 color = RED >This ID does not exists  </font></h1>");
                    int j = 1;
                    out.write("<p><font size = 12 color = BLUE> customer list  : </font></p>");
                    out.write("<a href=\"DeleteCustomer.html\">BACK</a><br/><br/>");

                    for (Customer i : list) {
                        out.write("<p>" + j + "&#128073" + i + "</p>");
                        j++;
                    }
                }
            }catch (Exception e){
                out.write("<h><font size = 12 color = RED>ID should be type of number </font></h>");
                out.write("<a href=\"DeleteCustomer.html\">BACK</a><br/><br/>");
            }
        }
        out.write("</body>");
        out.write("</html>");

    }

}
