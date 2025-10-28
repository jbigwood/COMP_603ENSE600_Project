/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

/**
 *
 * @author wkv8299
 */


public final class exitoption {
    
    private static final String[] exit = {"q"};
    
    private exitoption(){};
    
    public static boolean isExitque(String input){
        
        if(input == null){
            return false;
        }
       
        return input.toLowerCase().equals("q");
        
            
      
    }
    
    
    
   
    }
