package com.service;

import com.model.Customer;
import com.service.config.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private DB_Connection dbConnection = DB_Connection.getInstance();

    public List findAll() {
        String ALL_MANUFACTURER = "select * from javacup.customer";
        List<Customer> customers = new ArrayList<Customer>();
        try (Statement statement = dbConnection.getConnection().createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(ALL_MANUFACTURER);) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    Customer customer = new Customer(id, name);
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean save(Customer customer) {
        String SAVE = "insert into javacup.customer values(? , ?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(SAVE);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int customerId) {
        String DELETE = "delete from javacup.customer where id = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(DELETE);
            statement.setInt(1, customerId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int update(int customer_id, String name, int newCustomerId) {
        String UPDATE = "update javacup.customer set name = ? where id = ? ;" +
                "update java.customer set id = ? where id =?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(UPDATE);
            statement.setString(1,name);
            statement.setInt(2,customer_id);
            statement.setInt(3,newCustomerId);
            statement.setInt(4,customer_id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public boolean search(int id){
        List<Customer> list = new ArrayList<Customer>();
        list.addAll(findAll());
        for (Customer i :list) {
            if (id == i.getId())
                return true;
        }
        return false;
    }
}