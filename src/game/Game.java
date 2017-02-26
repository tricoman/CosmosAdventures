/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author tricoman
 */
public class Game extends JFrame {

    public Game() {
        
        initUI();
        
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Shooting missiles");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Game ex = new Game();
                ex.setVisible(true);

            }
        });

    }
    
}
