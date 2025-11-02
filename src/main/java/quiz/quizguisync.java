/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz;

/**
 *
 * @author Rebornsunrise
 */


import javax.swing.*;


public class quizguisync {
    
    private final JComponent parent;
    private final JTextArea area;
    private boolean quit;
    private io.masterlist words;
    private int correct;
    private int num;
    private int level;
    private boolean running;
    
    public void quit(){
        quit = true;
        area.setText("");
    }
    public void reset(){
        quit = false;
    }
    public boolean qrun(){
        return running;
    }
    public int currentLevel(){
        return level;
    }
    
    public quizguisync(JComponent parent, JTextArea area){
        this.parent = parent;
        this.area = area;
    }
    
    public void println(String txt){
       
        area.setText(area.getText() + txt + "\n");
    }
    
    public int run(io.masterlist words, int startLevel, int count){
        this.words = words;
        this.level = startLevel;
        this.correct = 0;
        int total = quiz.Quiz.totalquestions(words);
        if(total <=0){
            println("Error no questions");
            return startLevel;
        }
        this.num = 1;
        this.running = true;
        this.quit = false;
        
        
        println("Starting now...");
        
        shownext();
        return startLevel;
    }
    public boolean in(String ans){
        if(!running){
            return false;
        }
        if(quit){
            end();
            return false;
        }
        
        io.Word question = quiz.Quiz.currentquestion(words, num);
        
        if(quiz.Quiz.checkAnswer(question, ans)){
                println("Correct!");
                correct++;
                if(correct % 10 == 0){
                    level++;
                }
            }
            else{
                println("Inncorect. the Correct answer was: " + question.getEnglishmeaning());
            }
        num++;
        
        if(num > 50){
            end();
            return false;
        }
        else{
            shownext();
            return true;
        }
    }   
    
    private void shownext(){
       
        io.Word qs = quiz.Quiz.currentquestion(words, num);
        println("Question: " + num + " What is " + qs.getSpanishword() + " in English?" + "...... English left for testing: ans = " + qs.getEnglishmeaning());
           
        }
            
    private void end(){
        println("\nYou got " + correct + " correct! Level: " + level);
        running = false;
    }        
}
