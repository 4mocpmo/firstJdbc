package com.service;

import com.model.Part_Order;
import com.service.config.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Part_OrderService {
    DB_Connection dbConnection = DB_Connection.getInstance();
    public List finsAll(){
        String FINDALL = " select *from javacup.part_order";
        List<Part_Order> part_orders = new ArrayList<>();
        try {
            ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery(FINDALL);
            while (resultSet.next()){
                int quantity = resultSet.getInt(1);
                int part_uum = resultSet.getInt(2);
                int order_num = resultSet.getInt(3);
                Part_Order part_order = new Part_Order(quantity,part_uum,order_num);
                part_orders.add(part_order);
            }

        } catch (SQLException e) {
            return part_orders;
        }
        return part_orders;
    }
    public boolean save(Part_Order part_order) {
        String SAVE = "insert into javacup.part_order values(?,?,?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(SAVE);
            statement.setInt(1, part_order.getQuantity());
            statement.setInt(2, part_order.getPart_num());
            statement.setInt(3, part_order.getOrder_num());
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public int update(int Pquantity ,int  Nquantity , int part_num , int  order_num){
        String UPDATE = "update javacup.part_order set quantity = ? where quantity = ? and part_num = ? and order_num = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(UPDATE);
            statement.setInt(1,Nquantity);
            statement.setInt(2,Pquantity);
            statement.setInt(3,part_num);
            statement.setInt(4,order_num);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
