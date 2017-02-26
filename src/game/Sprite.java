/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author tricoman
 */
public class Sprite {
    
    protected int x;
    protected int y;
    protected int width;
    protected int heigth;
    protected boolean vis;
    protected Image image;
    
    public Sprite(int x, int y) {
        
        this.x = x;
        this.y = y;
        vis = true;
        
    }
    
    protected void loadImage(String imageName) {
        
        ImageIcon imageIcon = new ImageIcon(imageName);
        image = imageIcon.getImage();
        
    }
    
    protected void getImageDimensions() {
        
        width = getImage().getWidth(null);
        heigth = getImage().getHeight(null);
        
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the vis
     */
    public boolean isVisible() {
        return vis;
    }

    /**
     * @param vis the vis to set
     */
    public void setVisible(boolean vis) {
        this.vis = vis;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, heigth);
    }
    
}

