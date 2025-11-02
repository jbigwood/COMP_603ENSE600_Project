/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

/**
 *
 * @author wkv8299
 */
public class Word {
    
    private final String posnum;
    private final String spanishword;
    private final String englishmeaning;
    private final String wordreference;
    private final String type;
    private final int level;
    
    
    public Word(String posnum, String spanishword, String englishmeaning, String wordreference, String type, int level){
        
        this.posnum = posnum;
        this.spanishword = spanishword;
        this.englishmeaning = englishmeaning;
        this.wordreference = wordreference;
        this.type = type;
        this.level = level;
    }
    
    public String getposnum(){
        return posnum;
    }
    
    public String getSpanishword(){
        return spanishword;
    }
    
    public String getEnglishmeaning(){
        return englishmeaning;
    
    }
    
    public String getWordReference(){
        return wordreference;
    }
    
    public String getType(){
        return type;
    }
    
    public int getLevel(){
        return level;
    }   
    
    public String wordtype(){
        return "";
    }
}      
     
    
