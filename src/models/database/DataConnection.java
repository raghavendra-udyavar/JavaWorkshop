package models.database;

import models.structures.PriceUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataConnection {

    Connection connection;

    public DataConnection(){
        connection = initConnection();
    }

    public Connection initConnection(){

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/exchangeratecalculator";
        String username = "postgres";
        String password = "dbpassword";

        try {
            Class.forName(driver);
        }catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");

            System.out.println("Table added");
        }catch (Exception ex){
            System.out.println( ex);
        }
        return conn;
    }

    public void insertIntoTable(String sqlcommand) throws SQLException {
        PreparedStatement createStatement = connection.prepareStatement(sqlcommand);
        createStatement.executeUpdate();
    }
}
