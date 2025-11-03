/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author Rebornsunrise
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class users {
    
    private users(){}
    
    public static boolean newuser(String username, String password) throws SQLException{
        Connection conn = null;
        PreparedStatement usercheck = null;
        PreparedStatement adduser = null;
        ResultSet rs = null;
        boolean finished = false;
        
        try{
            conn = Database.get();
            
            usercheck = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            usercheck.setString(1, username);
            rs = usercheck.executeQuery();
            
            if(!rs.next()){
                adduser = conn.prepareStatement("INSERT INTO USERS (USERNAME, PASSWORD, LEVEL) VALUES (?,?,0)");
                adduser.setString(1, username);
                adduser.setString(2, password);
                adduser.executeUpdate();
                finished = true;
            }
            else{
                System.out.println("Account already exists, please try again");
            }
            
            rs.close();
            usercheck.close();
            if(adduser != null){
                adduser.close();
            }
            conn.close();
            
        }catch(SQLException e){
            System.out.println("Issue during account creation: " + e.getMessage());
        }
        return finished;
    }
    
    public static boolean verifyuser(String username, String password) throws SQLException{
        Connection conn = null;
        PreparedStatement check = null;
        ResultSet rs = null;
        boolean finished = false;
        
        try{
            conn = Database.get();
            check = conn.prepareStatement("SELECT PASSWORD FROM USERS WHERE USERNAME = ?");
            check.setString(1, username);
            rs = check.executeQuery();
            
            if(rs.next()){
                if(password.equals(rs.getString("PASSWORD"))){
                    System.out.println("Logging in");
                    finished = true;
                }
                else{
                    System.out.println("incorrect password");
                }
            }
            else{
                System.out.println("No account found");
            }
            
            rs.close();
            check.close();
            conn.close();
        }catch(SQLException e){
            System.out.println("Database login error " + e.getMessage());
        }
        return finished;
    }
    
    
    public static int lvl(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement lvl = null;
        ResultSet rs = null;
        int level = 0;
        
        try{
            conn = Database.get();
            lvl = conn.prepareStatement("SELECT LEVEL FROM USERS WHERE USERNAME = ?");
            lvl.setString(1,username);
            rs = lvl.executeQuery();
        
        
        if(rs.next()){
            level = rs.getInt("LEVEL");
        }
        
        rs.close();
        lvl.close();
        conn.close();
        }catch(SQLException e){
            System.out.println("Error with user level: " + e.getMessage());
        }
        return level;
    }
    
    
    public static void changelvl(String username, int level) throws SQLException{
        Connection conn = null;
        PreparedStatement lvl = null;
        
        try{
            
            conn = Database.get();
            
            lvl = conn.prepareStatement("UPDATE USERS SET LEVEL = ? WHERE USERNAME = ?");
            lvl.setInt(1,level);
            lvl.setString(2,username);
            lvl.executeUpdate();
            
            lvl.close();
            conn.close();
        }catch(SQLException e){
            System.out.println("Issue with changing level: "+ e.getMessage());
            
        }
    }
    
    
}