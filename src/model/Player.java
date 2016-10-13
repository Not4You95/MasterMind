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
public class Player implements Serializable {

    private String userName = null;
    private double score = 0.0;
    private int numberOfWins = 0;
    private int numberOfGames = 0;
    private RowCirckle secretColor;
    private ArrayList<RowCirckle> userColors;

    public Player() {
        userColors = new ArrayList<RowCirckle>();
    }

    public Player(String name) {
        this.userName = name;
        userColors = new ArrayList<RowCirckle>();

    }

    public void setName(String name) {
        this.userName = name;
    }

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

    public void lastGame(RowCirckle secret, ArrayList<RowCirckle> colors) {
        secretColor = secret;
        userColors.addAll(colors);

    }

    public RowCirckle getSecretClass() {
        return secretColor;
    }

    public ArrayList<RowCirckle> getColors() {
        ArrayList<RowCirckle> temp = new ArrayList<RowCirckle>();
        temp.addAll(userColors);
        return temp;
    }

}
