/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz;

import io.Word;

/**
 *
 * @author wkv8299
 */


public class Verbs extends Word{
    
    public Verbs(String id, String term, String translation, String tag, int level){
        super(id,term,translation,"verb", tag, level);
    }
    
    @Override
    public String wordtype(){
        return " (verb)";
    }
}
