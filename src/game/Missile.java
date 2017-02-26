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
public class Missile extends Sprite{
    
    private final int BOARD_WIDTH = 1014;
    private final int MISSILE_SPEED = 2;
    
    public Missile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    public void initMissile() {
        
        loadImage("sprites/missile.png");
        getImageDimensions();
    }
    
    public void move() {
        
        x += MISSILE_SPEED;
        
        if(x > BOARD_WIDTH) {
            vis = false;
        }
    }
}
