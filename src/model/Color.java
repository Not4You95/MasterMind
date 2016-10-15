
package model;

/**
 *
 * @author Robert
 */
public enum Color {
    red,blue,green,purple;
    
    /**
     * @return a random enum
     */
    public static Color getRandom(){
        return values()[(int) (Math.random()*values().length)];
    }
    
}
