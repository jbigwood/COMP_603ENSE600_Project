/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
 *
 * @author wkv8299
 */
public final class infostore {
    
    private infostore(){
    }
    
    public static Path data(){
        return Paths.get("data");
    }
    
    
    public static Path accinfo(){
        return data().resolve("users");
    }
    

    public static Path mlistlocate(){
        return data().resolve("words.csv");
    }
    
    
    public static void createFolders() throws IOException{
        Files.createDirectories(data());
        Files.createDirectories(accinfo());
    } 
    
    
    public static void pwout(Path file, String content) throws IOException{
        
        
        try(FileOutputStream fout = new FileOutputStream(file.toFile());
            OutputStreamWriter out = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(out)){
            pw.print(content);
        }
        
      
    }
    
  
    public static List<String> readeverything(Path file) throws IOException{
        
        List<String> lines = new ArrayList<>();
        try(BufferedReader buffread = Files.newBufferedReader(file, StandardCharsets.UTF_8)){
            String info;
            while((info = buffread.readLine()) != null){
                lines.add(info);
            }
        }
        return lines;
    }
   
}

