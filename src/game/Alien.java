/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author tricoman
 */
public class Alien extends Sprite {
    
    private final int INITIAL_X = 1024;
    
    public Alien(int x, int y) {
        super(x, y);
        
        initAlien();
    }
    
    private void initAlien() {
        
        loadImage("sprites/alien1.png");
        getImageDimensions();
    }
    
    public void move() {
        
        if (x < 0) {
            x = INITIAL_X;
        }
        
        x -= 1;
    }
    
}
