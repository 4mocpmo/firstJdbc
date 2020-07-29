package com.servlet;


import com.model.Order;
import com.model.Part;
import com.model.Part_Order;
import com.service.CustomerService;
import com.service.OrderService;
import com.service.PartService;
import com.service.Part_OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "NewOrderServlet" , urlPatterns = {"/newOrder"})
public class NewOrderServlet extends HttpServlet {
    private static int id;

    public int getId() {
        return id;
    }

    public void setId(int idd) {
        id = idd;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        String partNum = request.getParameter("partNum");
        String quantity = request.getParameter("quantity");
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">  </head>");
        out.write("<body>");
        if (partNum.length() == 0 || quantity.length() == 0){
            out.write("<h><font size = 12 color = black>partNum and quantity should not be empty </font></h><br/>");
            out.write("<a href=\"NewOrder.jsp\"><font size = 16 color  = RED >BACK</font></a><br/><br/>");
        } else{
            try{
                int id = getId();
                ArrayList<Order> list = new ArrayList<>();
                list.addAll(orderService.findAll());
                int orderNum = list.size() + 1 ;
                int pNum = Integer.parseInt(partNum);
                int quantit = Integer.parseInt(quantity);
                Part_OrderService partOrderService = new Part_OrderService();
                ArrayList<Part> list1 = new ArrayList<>();
                PartService partService = new PartService();
                list1.addAll(partService.findAll());
                boolean flag1 = false;
                if (list1.size() != 0) {
                    for (Part i : list1) {
                        if (i.getPart_num() == pNum) {
                            flag1 = true;
                            break;
                        }
                    }
                }
                    if (flag1){
                        if (orderService.save(new Order(orderNum, id))){
                            if (partOrderService.save(new Part_Order(quantit, pNum, orderNum))) {
                                out.write("<h1>This Order Save Successfully</h1>");
                                out.write("<h2>    NUMBER OF ORDER : " + orderNum + "</h2>");
                                out.write("<h2>   ID OF CUSTOMER: " + id + "  </h2>");
                                out.write("<a href =\"index.jsp\"><font size = 14 > home </font></a><br/><br/>");
                            }
                        }
                    }else {
                        int j = 1;
                        out.write("<p><font size = 12 color = BLUE> part list  : </font></p>");
                        for (Part i :list1) {
                            out.write("<p><font size = 16>"+ j + "&#128073" + i + "</font></p>");
                            j++;
                        }
                        out.write("<a href=\"index.jsp\">BACK</a><br/><br/>");
                    }
            }catch (Exception e){
                out.write("<h><font size = 12 color = RED>partNum should be type of number </font></h>");
                out.write("<a href=\"index.jsp\">BACK</a><br/><br/>");
            }
        }
        out.write("</body>");
        out.write("</html>");
    }

}
