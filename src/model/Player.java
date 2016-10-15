
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Player implements Serializable {

    private String userName = null;
    private double score = 0.0;
    private int numberOfWins = 0;
    private int numberOfGames = 0;
    private RowCircle secretColor;
    private ArrayList<RowCircle> userColors;
    private ArrayList<RowCircle> dots;
/**
 *
 * constructor
 */
    public Player() {
        userColors = new ArrayList<RowCircle>();
        dots  =new ArrayList<RowCircle>();
    }
    
/**
 *
 * constructor
 */
    public Player(String name) {
        this.userName = name;
        userColors = new ArrayList<RowCircle>();

    }
/**
 *
 * set player name
 */
    
    public void setName(String name) {
        this.userName = name;
    }

/**
 *
 * @return the score
 */    
    public double getScore() {
        return score;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the numberOfWins
     */
    public int getNumberOfWins() {
        return numberOfWins;
    }

    /**
     * @param numberOfWins the numberOfWins to set
     */
    public void setNumberOfWins() {
        numberOfWins++;
    }

    /**
     * @return the numberOfGames
     */
    public int getNumberOfGames() {
        return numberOfGames;
    }

    /**
     * @param numberOfGames the numberOfGames to set
     */
    public void setNumberOfGames() {
        numberOfGames++;
    }

/**
 *
 * sets the color, dots and secret code from a previous game
 */
    public void lastGame(RowCircle secret, ArrayList<RowCircle> colors, ArrayList<RowCircle> dots) {
        secretColor = secret;
        this.userColors.addAll(colors);
        this.dots.addAll(dots);

    }

    /**
     *
     * @return secret code
    */
    public RowCircle getSecretClass() {
        return secretColor;
    }

    /**
    * @return
    */
    public ArrayList<RowCircle> getColors() {
        ArrayList<RowCircle> temp = new ArrayList<RowCircle>();
        temp.addAll(userColors);
        return temp;
    }
    
    /**
     *
     * 
     */
    public void setDots(ArrayList<RowCircle> newDots){
        this.dots.addAll(newDots);
    }
    
    /**
     *
     * 
     */
    public ArrayList<RowCircle> getDots(){
        ArrayList<RowCircle> temp = new ArrayList<RowCircle>();
        temp.addAll(dots);
        return temp;
    }
}
