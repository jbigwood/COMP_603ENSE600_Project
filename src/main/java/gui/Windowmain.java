/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Rebornsunrise
 */


import db.Database;
import db.users;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Windowmain extends JPanel {
    
    //Window structure
    private final JTextArea outputArea = new JTextArea(14,48);
    
    //login menu setup
    private final JButton loginbutton = new JButton("Login");
    private final JButton signupbutton = new JButton("Signup");
    private final JButton quitbutton1 = new JButton("Quit");
    
    //main menu setup
    private final JButton quizbutton = new JButton("Start Quiz");
    private final JButton logoutbutton = new JButton("Log out");
    private final JButton quitbutton2 = new JButton("Quit");
    
    
    
    //quiz setup
    private final JButton quizquitbutton = new JButton("End Quiz");
    private final JTextField answerarea = new JTextField(20);
    private final JButton userinput = new JButton("Submit");
    private final JPanel anspanel = new JPanel(new BorderLayout());
    private final JPanel answerwindow = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel buttonarea = new JPanel(); 
    
    //menu swap
    private final JPanel cards = new JPanel(new CardLayout());
    private final String logincard = "login";
    private final String menucard = "menu";
    
    private String currentUser = null;
    private int currentLevel = 0;
    private boolean quizrun = false;
    
    private io.masterlist words = new io.masterlist();
    private final quiz.quizguisync bridge = new quiz.quizguisync(this, outputArea);
    
    public Windowmain(){
        
        //phyisical window 
        setPreferredSize(new Dimension(900,650));
        setLayout(new BorderLayout());
        
        
        outputArea.setEditable(false);
        
        
        //text area
        JScrollPane textarea = new JScrollPane(outputArea);
        textarea.setBackground(Color.green);
        textarea.getViewport().setBackground(Color.green);
        textarea.getViewport().setOpaque(true);
        textarea.setOpaque(true);
        add(textarea, BorderLayout.CENTER);
        
        
        //login menu layout and colours
        JPanel loginwindow = new JPanel(new GridLayout(3,1,8,8));
        loginwindow.setBackground(Color.green);
        loginbutton.setBackground(Color.white);
        signupbutton.setBackground(Color.white);
        quitbutton1.setBackground(Color.white);
        
        loginwindow.add(loginbutton);
        loginwindow.add(signupbutton);
        loginwindow.add(quitbutton1);
        
        //main menu layout and colours
        JPanel mainmenu = new JPanel(new GridLayout(3,1,8,8));
        mainmenu.setBackground(Color.cyan);
        quizbutton.setBackground(Color.white);
        logoutbutton.setBackground(Color.white);
        quitbutton2.setBackground(Color.white);
        
        
        mainmenu.add(quizbutton);
        mainmenu.add(logoutbutton);
        mainmenu.add(quitbutton2);
        
        
        
        
        //cards
        cards.setBackground(Color.green);
        cards.add(loginwindow, logincard);
        cards.add(mainmenu,menucard);
        
        
        //quiz layout
        buttonarea.setLayout(new BoxLayout(buttonarea, BoxLayout.Y_AXIS));
        buttonarea.add(userinput);
        buttonarea.add(Box.createVerticalStrut(8));
        buttonarea.add(quizquitbutton);
        
        answerwindow.add(new JLabel("Answer:"));
        answerwindow.add(answerarea);
        anspanel.add(answerwindow, BorderLayout.CENTER);
        anspanel.add(buttonarea, BorderLayout.EAST);
        
        anspanel.setVisible(false);
        
        JPanel ioarea = new JPanel(new BorderLayout());
        ioarea.add(cards, BorderLayout.CENTER);
        ioarea.add(anspanel, BorderLayout.SOUTH);
        add(ioarea,BorderLayout.SOUTH);
        
        
        
        //actions for the buttons
        loginbutton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                clicklogin();
                }
        });
        
        signupbutton.addActionListener(new ActionListener(){
           @Override public void actionPerformed(ActionEvent e){
               clicksignup();
           } 
        });
                
                
                
        quizbutton.addActionListener(new ActionListener(){
           @Override public void actionPerformed(ActionEvent e){
               clickquiz();
           } 
            
            
        });
        
        logoutbutton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                clicklogout();
            }
        });
                
                
        quitbutton1.addActionListener(new ActionListener(){
           @Override public void actionPerformed(ActionEvent e){
               
               System.exit(0);
           } 
        });
        
        quitbutton2.addActionListener(new ActionListener(){
        @Override public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        quizquitbutton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                clickquizquit();
            }
        });
        
        userinput.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                String answer = answerarea.getText();
                if(answer == null){
                    answer = "";
                    
                }
                boolean continuing = bridge.in(answer);
                answerarea.setText("");
                
                if(!continuing && !bridge.qrun()){
                    try{
                        users.changelvl(currentUser, bridge.currentLevel());
                    }catch(SQLException ea){
                        println("Issue saving level to account: " + ea.getMessage());
                    }
                    currentLevel = bridge.currentLevel();
                    quizquitbutton.setEnabled(false);
                    logoutbutton.setEnabled(true);
                    quizbutton.setEnabled(true);
                    anspanel.setVisible(false);
                    showmainmenu();
                }
            }
        });
        
        answerarea.addActionListener(new ActionListener(){
        @Override public void actionPerformed(ActionEvent e){
            userinput.doClick();
        }
    });
        
        //start the database and login menu 
        try{
            Database.get();
            
        }catch(SQLException e){
            println("Error loading database: "+ e.getMessage());
        }
        
        try{
            words.loadfile(io.infostore.mlistlocate());
            
        }catch(java.io.IOException e){
            println("Issue loading word list: " + e.getMessage());
        }
        showlogin();
        
    }
        
    
    private void showlogin(){
        currentUser = null;
        currentLevel = 0;
        
        outputArea.setBackground(Color.green);
        answerarea.setVisible(false);
        anspanel.setVisible(false);       
        CardLayout card = (CardLayout) cards.getLayout();
        card.show(cards, logincard);
        println("Please Login or Signup to begin");
    }
    
    private void showmainmenu(){
        
        outputArea.setBackground(Color.cyan);
        answerarea.setVisible(false);
        anspanel.setVisible(false);
        CardLayout card = (CardLayout) cards.getLayout();
        card.show(cards, menucard);
    }
    
    
    
    //login button actions after click
    public void clicklogin(){
        String username = JOptionPane.showInputDialog(this, "Please enter your Username:");
        if(username == null){
            return;
        }
        
        String password = JOptionPane.showInputDialog(this, "Please enter your Password:");
        if(password == null){
            return;
        }
        
        try{
                       
            if(!db.users.verifyuser(username, password)){
                JOptionPane.showMessageDialog(this,"Inncorrect  Username or Password\nPlease try again");
                return;
            }
            
            //set user info
            this.currentUser = username;
            this.currentLevel = db.users.lvl(username);
            
            //shift to main menu as login cleared
            outputArea.setText(outputArea.getText() + "Welcome " + currentUser + " You are level: " +currentLevel+"\n");
            showmainmenu();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Login issue: "+ex.getMessage());
        }
    }
    
    //signup button actions after click
    public void clicksignup(){
        String username = JOptionPane.showInputDialog(this, "Please enter a Username of your choice: ");
        if(username == null){
            return;
        }
        
        String password = JOptionPane.showInputDialog(this, "Please choose a Password with 8 characters: ");
        if(password == null){
            return;
        }
        
        if(password.length() < 8){
            JOptionPane.showMessageDialog(this,"The password you entered is too short, please try again");
            return;
        }
        
        try{
            
            if(!db.users.newuser(username, password)){
                JOptionPane.showMessageDialog(this, "The username you entered already exists, please try again");
                return;
            }
            this.currentUser = username;
            this.currentLevel = 0;
            
           //shift to main menu as acc created so loged in on the new acc automatically
            outputArea.setText(outputArea.getText() + "Account created. Welcome " + currentUser + "\n");
            showmainmenu();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Accoun creation failed: " + ex.getMessage());
        }
    }
    
    //resets current user to nothing and shifts back to the login menu, locking the user out of the mainmenu until login again
    private void clicklogout(){
        println("Logging out...");
        this.currentUser = null;
        this.currentLevel = 0;
        showlogin();
    }
    
    private void clickquiz(){
        
        quizrun = true;
        quizbutton.setEnabled(false);
        logoutbutton.setEnabled(false);
        quizquitbutton.setEnabled(true);
        anspanel.setVisible(true);
        answerarea.setVisible(true);
        answerarea.setText("");
        
        answerarea.requestFocusInWindow();
        
        bridge.reset();
        bridge.run(words, currentLevel, 50);
    }
    
    private void clickquizquit(){
        
        bridge.quit();
        
        bridge.in("");
        
        int newLevel = bridge.currentLevel();
        try{
            users.changelvl(currentUser, newLevel);
        }catch(SQLException e){
            println("Issue saving level: " + e.getMessage());
        }
        
        currentLevel = newLevel;
        
        
        anspanel.setVisible(false);
        
        showmainmenu();
        
        String results = bridge.endingmesg();
        outputArea.setText(results + "\n");
        outputArea.setText(outputArea.getText() + "Welcome " + currentUser + " You are level: " +currentLevel+"\n");
        
        quizbutton.setEnabled(true);
        quizquitbutton.setEnabled(false);
        anspanel.setVisible(false);
        logoutbutton.setEnabled(true);
    }
    
    private void println(String output){
        
        outputArea.setText(outputArea.getText() + output + "\n");
    }
    
    public static void displayframe(){
        
        JFrame frame = new JFrame("Vocab App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Windowmain());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
    }
    
}
