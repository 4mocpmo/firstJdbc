package com;

import com.model.Customer;
import com.service.CustomerService;
import com.service.config.DB_Connection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DB_Connection dbConnection = DB_Connection.getInstance();
        Connection connection = dbConnection.getConnection();
        CustomerService customerService = new CustomerService();
    }
}
