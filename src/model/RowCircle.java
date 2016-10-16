
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class RowCircle implements Serializable{

   
    private ArrayList<String> color;
   

   /**
    * constructor
    */
    public RowCircle() {
        color = new ArrayList<String>();
    }
   
    /**
     * constructor
     */
    public RowCircle(String color1,String color2,String color3,String color4) {
       
        color = new ArrayList<String>();
        color.add(color1);
        color.add(color2);
        color.add(color3);
        color.add(color4);
        
    }
   /** toString
    * @return string
    */
    public String toString() {
       
        return color.toString();
    }
   
   /**This method returns the size of  the arrayList
    *
    * @return the number of color
    */
    public int Size(){
        return color.size();
    }
   
   /** This method returns the string saved at index in the arrayList
    *
    * @return the color of at index
    */
    public String getRowCircle(int index){
        return color.get(index).toString();
    }
    /** This method returns arrayList of strings
     * @return arrayLsit of strings
     */
    public ArrayList<String> getArrayList(){
        ArrayList<String> temp = new ArrayList<String>();
        temp.addAll(color);
        return temp;
    }
 
}
  
    

