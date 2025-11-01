/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author Rebornsunrise
 */

import java.sql.*;

public class dbusers {
    
    private dbusers(){}
    
    public static boolean createuser(String username, String password) throws SQLException{
        
        try(Connection connection = Database.get();
                PreparedStatement statement = connection.prepareStatement("SELECT USERNAME FROM USERS WHERE USERNAME=?")){
            statement.setString(1, username);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return false;
                }
            }
        }
        try(Connection connection = Database.get();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (USERNAME, PASSWORD, LEVEL) VALUES (?,?,0)")){
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
            }
        }
        
    public static boolean verifyuser(String username, String password) throws SQLException {
        
        try(Connection connection = Database.get();
            PreparedStatement statement = connection.prepareStatement("SELECT PASSWORD FROM USERS WHERE USERNAME=?")){
            statement.setString(1, username);
            try(ResultSet rs = statement.executeQuery()){
                return rs.next() && password.equals(rs.getString(1));
            }
        }
        
    }
    
    public static int retreivelvl(String username) throws SQLException{
        
        try(Connection connection = Database.get();
        PreparedStatement statement = connection.prepareStatement("SELECT LEVEL FROM USERS WHERE USERNAME=?")){
            statement.setString(1, username);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    
    public static void newlvl(String username, int level) throws SQLException{
        
        try(Connection connection = Database.get();
        PreparedStatement statement = connection.prepareStatement("UPDATE USERS SET LEVEL=? WHERE USERNAME=?")){
            
            statement.setInt(1, level);
            statement.setString(2, username);
            statement.executeUpdate();
        }
    }
    }
        
        
        
        
        

