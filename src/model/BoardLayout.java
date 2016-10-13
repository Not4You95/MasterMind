/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jonas
 */
public class BoardLayout {
    
    public BoardLayout(){
        
    }
    
    public Rectangle get(){
        Rectangle n1 = new Rectangle(220, 40, 300, 100);
        n1.setArcWidth(30);
        n1.setArcHeight(30);
        n1.setStroke(Color.BLUE);
        n1.setStrokeWidth(5);
        n1.setFill(null);
        return n1;
    }
    
}
