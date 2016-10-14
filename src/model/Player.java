
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
    private RowCirckle secretColor;
    private ArrayList<RowCirckle> userColors;
    private ArrayList<RowCirckle> dots;
/**
 *
 * constructor
 */
    public Player() {
        userColors = new ArrayList<RowCirckle>();
        dots  =new ArrayList<RowCirckle>();
    }
    
/**
 *
 * constructor
 */
    public Player(String name) {
        this.userName = name;
        userColors = new ArrayList<RowCirckle>();

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
    public void lastGame(RowCirckle secret, ArrayList<RowCirckle> colors, ArrayList<RowCirckle> dots) {
        secretColor = secret;
        this.userColors.addAll(colors);
        this.dots.addAll(dots);

    }

    /**
     *
     * @return secret code
    */
    public RowCirckle getSecretClass() {
        return secretColor;
    }

    /**
    * @return
    */
    public ArrayList<RowCirckle> getColors() {
        ArrayList<RowCirckle> temp = new ArrayList<RowCirckle>();
        temp.addAll(userColors);
        return temp;
    }
    
    /**
     *
     * 
     */
    public void setDots(ArrayList<RowCirckle> newDots){
        this.dots.addAll(newDots);
    }
    
    /**
     *
     * 
     */
    public ArrayList<RowCirckle> getDots(){
        ArrayList<RowCirckle> temp = new ArrayList<RowCirckle>();
        temp.addAll(dots);
        return temp;
    }
}
