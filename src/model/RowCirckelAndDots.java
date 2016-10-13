/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class RowCirckelAndDots {
    private ArrayList<RowCirckle> colors;
    private ArrayList<RowCirckle> dots;
    
    public RowCirckelAndDots(){
        colors = new ArrayList<RowCirckle>();
        dots = new ArrayList<RowCirckle>();        
    }
    
    public RowCirckelAndDots(RowCirckle row){
        colors.add(row);
    }

    /**
     * @return the colors
     */
    public ArrayList<RowCirckle> getColors() {
        ArrayList<RowCirckle> temp = new ArrayList<RowCirckle>();
        temp.addAll(colors);
        return temp;
    }

    /**
     * @param colors the colors to set
     */
    public void setColors(ArrayList<RowCirckle> colors) {
        this.colors.addAll(colors);
    }

    /**
     * @return the dots
     */
    public ArrayList<RowCirckle> getDots() {
        ArrayList<RowCirckle> temp = new ArrayList<RowCirckle>();
        temp.addAll(dots);
        return temp;
    }

    /**
     * @param dots the dots to set
     */
    public void setDots(ArrayList<RowCirckle> dots) {
         this.dots.addAll(dots);
    }
    
}
