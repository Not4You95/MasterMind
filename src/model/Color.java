
package model;

/**
 *
 * @author Robert
 */
public enum Color {
    red,blue,green,purpil;
    
    /**
     * @return a random enum
     */
    public static Color getRandom(){
        return values()[(int) (Math.random()*values().length)];
    }
    
}
