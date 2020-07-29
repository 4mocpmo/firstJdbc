package com.service;

import com.model.Order;
import com.service.config.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private DB_Connection dbConnection = DB_Connection.getInstance();

    public List findAll() {
        String ALL_ORDER = "select * from javacup._order";
        List<Order> orders = new ArrayList<>();
        try (Statement statement = dbConnection.getConnection().createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(ALL_ORDER);) {
                while (resultSet.next()) {
                    int order_num = resultSet.getInt(1);
                    int id = resultSet.getInt(2);
                    Order order = new Order(order_num, id);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public boolean save(Order order){
        String SAVE = "insert into javacup._order values(? , ?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(SAVE);
            statement.setInt(1 ,order.getOrder_num());
            statement.setInt(2,order.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean delete(int order_num){
        String DELETE = "delete from javacup._order where order_num = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(DELETE);
            statement.setInt(1,order_num);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public int update(int order_num ,int Norder_num){
                String UPDATE  = "update javacup._order set order_num = ? where order_num = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(UPDATE);
            statement.setInt(1,Norder_num);
            statement.setInt(2,order_num);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
