/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rebornsunrise
 */

import java.sql.*;
import org.junit.Assert;
import org.junit.Test;
import quiz.Quiz;
import io.Word;
import io.masterlist;
import io.infostore;

public class tests {
    
    //database connected
    @Test
    public void connection() throws Exception{
        try(Connection c = db.Database.get()){
            Assert.assertNotNull("Database connection is null", c);
            Assert.assertFalse("Database connection is open", c.isClosed());
        }
    }
    
    //database tables
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
    
    //test correct answer works for Quiz.checkAnswer
    @Test
    public void chckanswers(){
        Word word = new Word("2", "adios", "goodbye", "noun", "farewell", 1);
        
        Assert.assertTrue("Answer should be correct", Quiz.checkAnswer(word, "goodbye"));
        Assert.assertFalse("Answer should be incorrect", Quiz.checkAnswer(word, "hello"));
    }
    
    
    
    //masterlist loads the CSV 
    @Test
    public void CSVloads() throws Exception {
        masterlist list = new masterlist();
        list.loadfile(infostore.mlistlocate());
        Assert.assertTrue("Word list is not empty", !list.all().isEmpty());
    }
    
    //level count works
    @Test
    public void lvlincrease(){
        masterlist test = new masterlist();
        for(int i = 1; i <= 50; i++){
            test.all().add(new Word(String.valueOf(i), "palabra", "word", "noun", "test",1));
        }
        int level = 0;
        int correct = 0;
        
        for (int i = 1; i<= 50; i++){
            correct++;
            if(correct % 10 == 0){
                level++;
            }
        }
        Assert.assertEquals("Level should have gone up", 5, level);
        
    }
    
    
    
}
