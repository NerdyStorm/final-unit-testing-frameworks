package com.techfios.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import org.openqa.selenium.remote.service.DriverService;

public class Database {
    

    String user = "root";
    String password = "root";

    String url = "jdbc:mysql://127.0.0.1:3306/mdtestdatabase"; //jdbc:mysql//127.0.0.1/september2021 --> jdbc:mysql://127.0.0.1:3306/september2021

    String query = "select * from users limit 1;";

    ResultSet result; 


    public ResultSet retrieveData(){

    

    try {

        Class.forName("com.mysql.cj.jdbc.Driver"); //com.jdbc.cj.mysql --> com.mysql.jdbc.cj.Driver

        Connection c = DriverManager.getConnection(url, user, password);

        Statement s = c.createStatement();

        ResultSet r = s.executeQuery(query);

        r.next();

        return r;



    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        return result;
    }

    

}
