package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Player implements Serializable {

    private String userName = null;
    private int numberOfWins = 0;
    private int numberOfGames = 0;
    private boolean gameover = false;
    private RowCircle secretColor;
    private ArrayList<RowCircle> userColors;
    private ArrayList<RowCircle> dots;

    /**
     *
     * constructor
     */
    public Player() {
        userColors = new ArrayList<RowCircle>();
        dots = new ArrayList<RowCircle>();
    } 

    /**
     *
     * set player name
     */
    public void setName(String name) {
        this.userName = name;
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
     * adds the numberOfWins with 1
     */
    public void addNumberOfWins() {
        numberOfWins++;
    }

    /**
     * @return the numberOfGames
     */
    public int getNumberOfGames() {
        return numberOfGames;
    }

    /**
     * adds the numberOfGames with 1
     */
    public void addNumberOfGames() {
        numberOfGames++;
    }

    /**
     * sets the color, dots and secret code from a previous game
     */
    public void lastGame(RowCircle secret, ArrayList<RowCircle> colors, ArrayList<RowCircle> dots) {
        this.userColors.clear();
        this.dots.clear();
        secretColor = secret;
        this.userColors.addAll(colors);
        this.dots.addAll(dots);

    }

    /**
     * @return secret color code
     */
    public RowCircle getSecretClass() {
        return secretColor;
    }

    /**
     * @return the color user have chosen
     */
    public ArrayList<RowCircle> getColors() {
        ArrayList<RowCircle> temp = new ArrayList<RowCircle>();
        temp.addAll(userColors);
        return temp;
    }

    /**
     * Get the dots
     *
     * @return arrayList of dots
     */
    public ArrayList<RowCircle> getDots() {
        ArrayList<RowCircle> temp = new ArrayList<RowCircle>();
        temp.addAll(dots);
        return temp;
    }

    /** returns the value if last game was finished or not
     * @return the gameover
     */
    public boolean isGameover() {
        return gameover;
    }

    /** Set if the game is over or not 
     */
    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
    

}
