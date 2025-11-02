/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz;


import java.util.List;


/**
 *
 * @author wkv8299
 */
public final class Quiz {
    
        private Quiz(){}


        public static int totalquestions(io.masterlist words){
            if(words == null){
                return 0;
            }
            List<io.Word> pool =words.all();
            return pool.size();
        }

        public static boolean checkAnswer(io.Word word, String ans){
            if(ans == null){
                return false;
            }

            String correct = word.getEnglishmeaning().toLowerCase();
            return ans.toLowerCase().equals(correct);
        }

        public static io.Word currentquestion(io.masterlist words, int num){ 
            List<io.Word> pool = words.all();
            
            
            return pool.get(num-1);
            }
    }
    

