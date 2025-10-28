/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz;

import io.Word;
import io.masterlist;
import io.exitoption;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author wkv8299
 */
public final class Quiz {
    
    private Quiz(){}
    
    public static int run(Scanner scan, masterlist bank, int currentLevel){
        
        List<Word> pool = bank.all();
        System.out.println("Begin Now, Good Luck!");
        
        int total = 50;
        int questionsasked = 0;
        int correctans = 0;
        int level = currentLevel;
        
        for(int i = 0; i < total; i++){
            
           
            Word word = pool.get(i);
            
            
            System.out.println("Question " + (i + 1) + ": What does " + word.getSpanishword() + word.wordtype() + " mean (input in English)  (Debugging: ans =  " + word.getEnglishmeaning()+ ")");
            String userinput = scan.nextLine();
            
           
            if(exitoption.isExitque(userinput)){
                System.out.println("Exiting quiz");
                break;
            }
            
            if(userinput.equals(word.getEnglishmeaning())){
                correctans++;
                if(correctans % 10 == 0){
                    level++;
                }
                        
            }
            else{
                System.out.println("Inncorect, the corerect answer was: " + word.getEnglishmeaning());
            }
            questionsasked++;
        }
        
        System.out.println("\n You got " + correctans + " correct! Level: " + level);
        return level;
    }
}
