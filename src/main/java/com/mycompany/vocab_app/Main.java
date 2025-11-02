/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vocab_app;


/**
 *
 * @author wkv8299
 */




import db.Database;
import java.sql.SQLException;




public class Main{

    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override public void run(){
                gui.Windowmain.displayframe();
            }
        });
    }
}
