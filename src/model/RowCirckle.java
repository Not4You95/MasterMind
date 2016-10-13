/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class RowCirckle implements Serializable{

   
    private ArrayList<String> color;
    private int colum = 0;

    public RowCirckle() {
        color = new ArrayList<String>();
    }

    /**
     * @return the color
     */
    

    /**
     * @param color the color to set
     */
    public RowCirckle(String color1,String color2,String color3,String color4) {
       
        color = new ArrayList();
        color.add(color1);
        color.add(color2);
        color.add(color3);
        color.add(color4);
        
    }

    public String toString() {
       
        return color.toString();
    }
    public int Size(){
        return color.size();
    }
    public String getRowCircel(int index){
        return color.get(index).toString();
    }

   
        
    }
  
    

