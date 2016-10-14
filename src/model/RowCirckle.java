
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

   /**
    *
    * constructor
    */
    public RowCirckle() {
        color = new ArrayList<String>();
    }
   
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
   /**
    *
    *@return
    */
    public String toString() {
       
        return color.toString();
    }
   
   /**
    *
    * @return the number of color
    */
    public int Size(){
        return color.size();
    }
   
   /**
    *
    * @return tthe color of an index
    */
    public String getRowCircel(int index){
        return color.get(index).toString();
    }
 
}
  
    

