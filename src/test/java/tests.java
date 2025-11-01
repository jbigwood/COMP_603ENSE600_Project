/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rebornsunrise
 */

import java.sql.*;
import org.junit.Test;

public class tests {
    
    //database creation and exists
    @Test
    public void tableswork() throws Exception{
        try(Connection c = db.Database.get()){
            DatabaseMetaData md = c.getMetaData();
            
            try(ResultSet users = md.getTables(null, null,"USERS", null)){
                org.junit.Assert.assertTrue("USERS table missing", users.next());
            }
            try(ResultSet words = md.getTables(null,null,"WORDS",null)){
                org.junit.Assert.assertTrue("WORDS table missing", words.next());
            }
            
        }
    }
    
}
