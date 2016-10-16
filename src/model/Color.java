
package model;

/**
 * constructor
 * @author Robert
 */
public enum Color {
    red,blue,green,purple;
    
    /** This method returns a random color, what is part of the enum
     * @return a random color
     */
    public static Color getRandom(){
        return values()[(int) (Math.random()*values().length)];
    }
    
}
