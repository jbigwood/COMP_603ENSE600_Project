/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

//utilities
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.StandardCopyOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;



/**
 *
 * @author wkv8299
 */
public final class Users {
    
    private Users(){}
    
    
    public static boolean userExists(Path userFile){
        return Files.exists(userFile);
    }
    
   
    public static void New(Path userfile, String username, String password) throws IOException{
        int startlevel = 0;
        String content = "Username: " + username + "\n" + "Password: " + password + "\n" + "Level: " + startlevel + "\n";
        infostore.pwout(userfile, content);
    }
    
    
    public static boolean verify(Path userFile, String username, String password) throws IOException {
        
        String user = null;
        String pass = null;
        List<String> lines = infostore.readeverything(userFile);
        
        for(String line: lines){
            if(line.startsWith("Username: ")){
                user = line.substring("Username: ".length()).trim();
            }
            else if(line.startsWith("Password: ")){
                pass = line.substring("Password: ".length()).trim();
            }
            
            
        }
        if (user == null || pass == null){
            return false;
        }
        return username.equals(user) && password.equals(pass);
        
    } 
    
    
    public static int acclevel(Path userFile) throws IOException{
        
       List<String> lines = infostore.readeverything(userFile);
       
       for(String line : lines){
           if(line.startsWith("Level: ")){
               String L = line.substring("Level: ".length()).trim();
               
               try{
                   return Integer.parseInt(L);
               }catch(NumberFormatException e){
                   return 0;
               }
           }
       }
        return 0;
    }
    
    
    public static void ammendLevel(Path userFile, int level) throws IOException{
           
           Path dir = userFile.getParent();
           Path tmp = Files.createTempFile(dir, "user_", ".tmp");
           
           String lineUN = null;
           String linePW = null;
         
           
           if(Files.exists(userFile)){
               
               try(BufferedReader in = Files.newBufferedReader(userFile, StandardCharsets.UTF_8)){
                   String line;
                   
                   while((line = in.readLine()) != null){
                       if(line.startsWith("Username: ")){
                          lineUN = line;
                       }
                       else if(line.startsWith("Password: ")){
                           linePW = line;
                       }
                       
                   }
               }
               
               
               try(BufferedWriter out = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)){
                   
                   if(lineUN != null){
                       out.write(lineUN);
                       out.newLine();
                   }
                   if(linePW != null){
                       out.write(linePW);
                       out.newLine();
                   }
                   out.write("Level: " + level);
                   out.newLine();
               }
               
               
               try{
                   Files.move(tmp, userFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
               } catch(AtomicMoveNotSupportedException a){
                   System.out.println("Cant replace with temp file: " + a.getMessage());
               }
               
           }
           
           
  
           
       }
    
        
       
    
    
}
