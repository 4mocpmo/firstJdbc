package com.service;

import com.model.Part;
import com.service.config.DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartService {
    private DB_Connection dbConnection = DB_Connection.getInstance();

    public List findAll(){
        String SAVE = "select *from javacup.Part";
        ArrayList<Part> parts = new ArrayList<>();
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SAVE);
            while(resultSet.next()){
                int part_num = resultSet.getInt(1);
                String description = resultSet.getString(2);
                String name = resultSet.getString(3);
                Part part = new Part(part_num , description , name);
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parts;
    }
    public boolean save(Part part){
        String SAVE = "insert into javacup.part values(? , ? , ?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(SAVE);
            statement.setInt(1 ,    part.getPart_num());
            statement.setString(2 , part.getDescription());
            statement.setString(3 , part.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean delete(int partNum){
        String  DELETE = "delete from javacup.Part where part_num = ? ";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(DELETE);
            statement.setInt(1 ,partNum );
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public int update (int pastPartnum , int newPrtnum , String Ndescription ){
        String UPDATE = "update javacup.Part set description = ? where part_num = ? ;" +
                        "update javacup.Part set part_num = ? where part_num = ?";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(UPDATE);
            statement.setString(1,Ndescription);
            statement.setInt(2,pastPartnum);
            statement.setInt(3,newPrtnum);
            statement.setInt(4,pastPartnum);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }


}
