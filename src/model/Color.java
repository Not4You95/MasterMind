/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Robert
 */
public enum Color {
    red,blue,green,purpil;
    
    
    public static Color getRandom(){
        return values()[(int) (Math.random()*values().length)];
    }
    
}
