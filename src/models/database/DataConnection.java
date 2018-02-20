package models.database;

import models.structures.PriceUpdate;
import org.postgresql.util.PSQLException;

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

            PreparedStatement createStatement = conn.prepareStatement("CREATE SEQUENCE user_id_seq;" +
                    " CREATE TABLE IF NOT EXISTS priceupdate(id int NOT NULL DEFAULT nextval('user_id_seq') , updatetime DATE , exchange char(10) NOT NULL ," +
                    "sourcecurrency char(15) not NULL ," +
                    "destinationcurrency char(15) not NULL," +
                    "forwardrate real not NULL," +
                    "backwardrate real not NULL ," +
                    "PRIMARY KEY(id));" +
                    "ALTER SEQUENCE user_id_seq OWNED BY priceupdate.id;");
            createStatement.executeUpdate();

            System.out.println("Table added");
        }catch (Exception ex){
            System.out.println("priceupdate table already existing");
        }
        return conn;
    }

    public void insertIntoTable(String sqlcommand) {

        try {
            PreparedStatement createStatement = connection.prepareStatement(sqlcommand);
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
}
