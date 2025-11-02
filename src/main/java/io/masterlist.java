/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;



import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;


/**
 *
 * @author wkv8299
 */
public class masterlist {
    
     private final List<Word> words;
        
    
    private final Map<String, Word> posNum; 
    
    
    public masterlist(){
        this.words = new ArrayList<>();
        this.posNum = new HashMap<>();
    }
    
    
    public List<Word> all() {
        
        return new ArrayList<>(words);
    }
    
    public Word getId(String posnum){
        return posNum.get(posnum);
    }
    
    
    public void loadfile(Path csv) throws IOException {
        
        
        List<String> lines = infostore.readeverything(csv);
        if (lines.isEmpty()){
            System.out.println("csv is empty"); 
            return;
        }
        words.clear(); 
        posNum.clear();
        
        for(int i = 1; i < lines.size(); i++){
            String line = lines.get(i);
            if(line.isBlank()) continue;
            
        
        
        String[] t = line.split(",");
        
        for(int j = 0; j < t.length; j++){
            t[j] = t[j].trim();
        }
        
        
        int level = 0;
        try{
            level = Integer.parseInt(t[5]);
        } catch (NumberFormatException e){
            
        }
        
        
        Word v = new Word(t[0], t[1], t[2], t[3] ,t[4], level);
        
            words.add(v);
            posNum.put(v.getposnum(),v);
        
    }
    
    }
}
