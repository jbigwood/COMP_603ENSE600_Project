/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author wkv8299
 */

import java.sql.*;

public class Database {
    
    private static final String URL = "jdbc:derby:data/Appdatabase;create=true";
    
    private Database(){}
    
    public static Connection get() throws SQLException{
        Connection connection = DriverManager.getConnection(URL);
        Tables(connection);
        return connection;
        
    }
    
    private static boolean aretables(Connection connection, String Tname) throws SQLException{
        DatabaseMetaData md =connection.getMetaData();
        try(ResultSet rs = md.getTables(null,null,Tname.toUpperCase(),null)){
            return rs.next();
        }
        
    }
    
    private static void Tables(Connection connection) throws SQLException{
        if(!aretables(connection, "USERS")){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate("CREATE TABLE USERS (USERNAME VARCHAR(64) PRIMARY KEY, PASSWORD VARCHAR(255), LEVEL INT)");
            }
        }
        
        if(!aretables(connection, "WORDS")){
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate("CREATE TABLE WORDS (POSNUM VARCHAR(32) PRIMARY KEY, SPANISH VARCHAR(128), ENGLISH VARCHAR(128), WORDREF VARCHAR(128), TYPE VARCHAR(32), LVL INT)");
            }
        }
    }
    
    
    
}
