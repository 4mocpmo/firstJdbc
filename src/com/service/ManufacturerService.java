package com.service;

import com.model.Manufacturer;
import com.service.config.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerService {
    private DB_Connection dbConnection = DB_Connection.getInstance();

    public List findAll(){
        String ALL_MANUFACTURER = "select * from javacup.manufacturer";
        List<Manufacturer>  manufacturers = new ArrayList<Manufacturer>();
        try (Statement statement = dbConnection.getConnection().createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(ALL_MANUFACTURER);) {
                while(resultSet.next()){
                    String name = resultSet.getString(1);
                    String city = resultSet.getString(2);
                    String street = resultSet.getString(3);

                    Manufacturer manufacturer = new Manufacturer(name, city,street);
                    manufacturers.add(manufacturer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }
    public boolean save(Manufacturer manufacturer){
        String SAVE = "insert into javacup.manufacturer(name , city , street) values(? , ? , ?)";
        PreparedStatement statement = null;
        try {
            statement = this.dbConnection.getConnection().prepareStatement(SAVE);
            statement.setString(1,manufacturer.getName());
            statement.setString(2,manufacturer.getCity());
            statement.setString(3,manufacturer.getStreet());
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean delete(String manufacturerName){
        String DELETE = "delete from javacup.manufacturer where name = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(DELETE);
            statement.setString(1,manufacturerName);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public int update (String manufacturer_name , String city , String street){
        String UPDATE = "update javacup.manufacturer set  city = ? where name = ? ; update javacup.manufacturer set street = ? where name = ?";
        try(PreparedStatement statement = dbConnection.getConnection().prepareStatement(UPDATE)){
            statement.setString(1 , city );
            statement.setString(2,manufacturer_name);
            statement.setString(3,street);
            statement.setString(4,manufacturer_name);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public boolean search(String name){
        List<Manufacturer> list = new ArrayList<Manufacturer>();
        list.addAll(findAll());
        for (Manufacturer i :list) {
            if (name.equals(i.getName()))
                return true;
        }
        return false;
    }

}
