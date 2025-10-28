/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vocab_app;

import java.util.Scanner;
import java.io.IOException;
import io.exitoption;





/**
 *
 * @author wkv8299
 */
public class Main{

    public static void main(String[] args) {
        
       
        String NewUser = null;
        String NewPass = null;
        String ExistingUN = null;
        String ExistingPW = null;
        String acc = null;
        int accLevel = 0;
        java.nio.file.Path userFile = null;
        
        
        Scanner input = new Scanner(System.in);
        
        io.masterlist word = new io.masterlist();
        try {
            word.loadfile(io.infostore.mlistlocate());
        } catch(IOException e){
            System.out.println("Error loading words: " + e.getMessage());
        }
        
        System.out.println("Hello and welcome to the Spanish language learning app");
        System.out.println();
        
        
        try{
            io.infostore.createFolders();
        } catch (IOException e){
            System.out.println("error setting up folders: " + e.getMessage());
            return;
        }
        
       
       
        while(true){
            if(acc == null){
                System.out.println("Enter '1' for Login");
                System.out.println("Enter '2' for Signup");
                System.out.println("To Exit, Enter 'q' at any time");
            } 
            else{
                System.out.println("Enter '1' for Word Test");
                System.out.println("To Exit, Enter 'q' at any time");
            }
            
            
           String choice = input.nextLine();
            
          
           if(exitoption.isExitque(choice)){
               System.out.println("Exiting application...Goodbye");
               break;
           } 
           
           if(acc == null){
                switch (choice) {
                    case "1":
                        
                        System.out.println("Enter User name: ");
                        ExistingUN = input.nextLine();
                        userFile = io.infostore.accinfo().resolve(ExistingUN + ".txt");
                        
                        if(!io.Users.userExists(userFile)){
                            System.out.println("Unknown username, please signup");
                            continue;
                        }        System.out.println("Enter User password: ");
                        ExistingPW = input.nextLine();
                        
                        try{
                            if(!io.Users.verify(userFile, ExistingUN, ExistingPW)){
                                System.out.println("Incorrect Username or Password");
                                continue;
                            }
                            acc = ExistingUN;
                            accLevel = io.Users.acclevel(userFile);
                            
                            System.out.println("Hello " + acc + ", Welcome back!" + "(Level: " + accLevel + ")");
                        }catch(java.io.IOException e){
                            System.out.println("login issue: " + e.getMessage());
                            acc = null;
                            userFile = null;
                            accLevel = 0;
                        }        
                        break;
                        
                    case "2":
                        
                        System.out.println("Please Enter desired Username: ");
                        NewUser = input.nextLine();
                        
                        userFile = io.infostore.accinfo().resolve(NewUser + ".txt");
                        if(io.Users.userExists(userFile)){
                            System.out.println("Sorry that username already exists");
                            continue;
                        }       
                        
                        System.out.println("Please enter desired Password with minimum 8 characters: ");
                        NewPass = input.nextLine();
                        
                        if(NewPass == null || NewPass.length() < 8){
                            System.out.println("Sorry that password is too short. Please enter 8 characters.");
                            continue;
                        }        try{
                            
                            io.Users.New(userFile, NewUser, NewPass);
                            acc = NewUser;
                            accLevel = 0;
                            
                            
                            System.out.println("Account created! Welcome " + NewUser);
                            
                        }catch(java.io.IOException e){
                            System.out.println("Signup failed: " + e.getMessage());
                            
                            acc = null;
                            userFile = null;
                            accLevel = 0;
                        }       break;
                    default:
                        System.out.println("Im sorry that input is invalid, please try again");
                        break;
                }
           }
           else {
                    if(choice.equals("1")){
                        
                        
                    int newLevel = quiz.Quiz.run(input, word, accLevel);
                    
                  
                    if(newLevel != accLevel){
                        accLevel = newLevel;
                    }
                    
                    
                    try{
                        io.Users.ammendLevel(userFile, accLevel);
                    } catch(java.io.IOException e){
                        System.out.println("Leveling Error: " + e.getMessage());
                        }
                    }
                    else{
                         System.out.println("Im sorry that input is invalid, please try again");
                        }
               
                }
        
        }
            
    
        
        
        input.close();
    }
}
