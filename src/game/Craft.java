/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author tricoman
 */
public class Craft extends Sprite {
      
    private int dx, dy;
    private ArrayList<Missile> missiles;
    private long lastMissileTime;
    
    public Craft(int x, int y) {
        super(x, y);
        
        initCraft();
        
    }
    
    private void initCraft() {
        
        missiles = new ArrayList<>();
        lastMissileTime = System.currentTimeMillis();
        loadImage("sprites/spaceCraft.png");
        getImageDimensions();
        
    }
    
    public void move() {

        x += dx;
        y += dy;
        
        if (x < 1) {
            
            x = 1;
        }
        
        if (y < 1) {
            
            y = 1;
        }

    }

    public ArrayList getMissiles() {
        return missiles;
    }
    
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_SPACE:
                fire();
                break;
            case KeyEvent.VK_LEFT:
                dx = -2;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 2;
                break;
            case KeyEvent.VK_UP:
                dy = -2;
                break;
            case KeyEvent.VK_DOWN:
                dy = 2;
                break;
        }
        
    }
    
    public void fire() {
        long millisSinceLastMissile = System.currentTimeMillis() - lastMissileTime;
        if (millisSinceLastMissile > 200) {
            missiles.add(new Missile(x + width , y + heigth / 2));
            lastMissileTime = System.currentTimeMillis();
        }
        
    }
    
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
        }
        
    }
}
