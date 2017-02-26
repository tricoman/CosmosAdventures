/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tricoman
 */
public class Board extends JPanel implements ActionListener{

    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private boolean inGame;
    private final int I_CRAFT_X = 40;
    private final int I_CRAFT_Y = 60;
    private final int B_WIDTH = 1024;
    private final int B_HEIGHT = 768;
    private final int DELAY = 15;
    
    private final int[][] pos = {
        {1054,295}, {1120,706}, {1170,480}, {1240,306}, {1296,137}, {1360,283},
        {1410,129}, {1450,350}, {1510,322}, {1570,458}, {1640,269}, {1704,596}, 
        {1762,517}, {1813,666}, {1860,546}, {1539,77}, {1552,396}, {1594,451}, 
        {1704,664}, {1719,281}, {1742,520}, {1762,66}, {1770,389}, {1782,97}, 
        {1791,131}, {1815,191}, {1827,315}, {1862,199}, {1914,491}, {1929,437}, 
        {1952,572}, {1957,206}, {1959,121}, {1966,413}, {1976,672}, {2019,125}, 
        {2733,468}, {2270,610}, {2973,418}, {2055,123}, {2622,333}, {2348,370}, 
        {2250,358}, {2460,130}, {2467,320}, {2871,431}, {2618,299}, {2759,128}, 
        {2578,634}, {2468,192}, {2771,629}, {2878,271}, {2411,584}, {2019,339}, 
        {2558,453}, {2099,268}, {2761,468}, {2369,330}, {2273,464}, {2628,493}, 
        {2306,630}, {2807,255}, {2420,550}, {2346,364}, {2644,667}, {2218,333}, 
        {2006,104}, {2263,222}, {2504,592}, {2801,37}, {2826,284}, {3572,285}, 
        {3452,362}, {3310,322}, {3740,469}, {3663,544}, {3841,643}, {3540,384}, 
        {3399,389}, {3449,461}, {3625,469}, {3255,299}, {3125,709}, {3154,298}, 
        {3700,104}, {3531,601}, {3070,608}, {3270,701}, {3459,55}, {3714,650}, 
        {3403,702}, {3903,292}, {3536,572}, {3690,266}, {3035,642}, {3017,705}, 
        {3575,627}, {3125,391}, {3116,603}, {3393,192}, {3779,707}, {3098,40}, 
        {3246,616}, {3055,653}, {3737,681}, {3087,597}, {4236,530}, {4059,561}, 
        {4054,622}, {4124,125}, {4389,372}, {4324,135}, {4424,145}, {4102,262}, 
        {4263,338}, {4108,373}, {4345,449}, {4115,411}, {4003,330}, {4225,242}, 
        {4482,95}, {4179,706}, {4203,527}, {4124,280}, {4162,319}
    };
    
    public Board() {
        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        inGame = true;
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        craft = new Craft(I_CRAFT_X, I_CRAFT_Y);
        
        initAliens();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void initAliens() {
        aliens = new ArrayList<>();
        
        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        if (inGame) {
            
            drawObjects(g);
            
        } else {
            
            drawGameOver(g);
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics g) {
        
        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        }
        
     
        ArrayList<Missile> missiles = craft.getMissiles();
        
        for (Missile missile : missiles) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            }
            
        }
        
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }
        
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }
    
    private void drawGameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        
        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        
        
        updateCraft();
        updateMissiles();
        updateAliens();
        
        checkCollissions();
        
        repaint();
        
    }
    
    private void inGame() {
        
        if (!inGame) {
            timer.stop();
        }
    }
    
    private void updateCraft() {
        
        if (craft.isVisible()) {
            craft.move();
        }
    }
    
    private void updateMissiles() {
        
        ArrayList<Missile> missiles = craft.getMissiles();
        
        for (int item = 0; item < missiles.size(); item++) {
            
            Missile missile = missiles.get(item);
            
            if (missile.isVisible()) {
                
                missile.move();
                
            } else {
                
                missiles.remove(item);
            }
        }
    }
    
    private void updateAliens() {
        
        if(aliens.isEmpty()) {
            
            inGame = false;
            return;
        }
        //pot fallar
        for (int item = 0; item < aliens.size(); item++) {
            
            Alien alien = aliens.get(item);
            
            if (alien.isVisible()){
                alien.move();
            } else {
                aliens.remove(item);
            }
        }
    }
    
    private void checkCollissions() {
        
        Rectangle craftArea = craft.getBounds();
        
        for (Alien alien : aliens) {
            
            Rectangle alienArea = alien.getBounds();
            
            if (craftArea.intersects(alienArea)) {
                craft.setVisible(false);
                alien.setVisible(false);
                inGame = false;
            }
        }
        
        ArrayList<Missile> missiles = craft.getMissiles();
        
        for (Missile missile : missiles) {
            
            Rectangle missileArea = missile.getBounds();
            
            for (Alien alien : aliens) {
            
                Rectangle alienArea = alien.getBounds();

                if (missileArea.intersects(alienArea)) {
                    missile.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }
   
    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
    
    
}
