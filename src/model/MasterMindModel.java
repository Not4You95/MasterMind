
package model;

import com.sun.scenario.effect.impl.Renderer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Color;
import model.Player;
import model.ReadAndWrite;
import model.RowCircle;
import java.lang.RuntimeException;

/**
 *
 * @author jonas
 */
public class MasterMindModel {

    private ArrayList<RowCircle> Colors;
    private ArrayList<RowCircle> dots;
    private int row = 0,losNr=7;   
    private RowCircle secretColors;
    private Player players = null;   
    private ReadAndWrite file;
    private File filename;
    
    /**
     * constructor
     */
    public MasterMindModel() {
        Colors = new ArrayList<RowCircle>();
        players = new Player();
        file = new ReadAndWrite();
        dots = new ArrayList<RowCircle>();
    }
    
   /**
     * adding color to the rows and increment row
     *@param Arraylist
     */
    public void addColors(ArrayList temp) {
        RowCircle circleRow = new RowCircle(temp.get(0).toString(), temp.get(1).toString(), temp.get(2).toString(), temp.get(3).toString());
        Colors.add(circleRow);
        System.out.println("User: " + Colors.get(row).toString());
        row++;
    }
    
    /**
     * compares the choosen colors and marks the dots with the right color
     */
    public String[] guessOfColors() {
        String[] dotsString = new String[4];
        int black = 0;
        System.out.println(secretColors.toString());
        for (int i = row - 1; i < row; i++) {

            for (int j = 0; j < 4; j++) {

                if (Colors.get(i).getRowCircle(j).equals(secretColors.getRowCircle(j))) {

                    dotsString[j] = "black";
                    black++;

                } else if (secretColors.toString().contains(Colors.get(i).getRowCircle(j))) {

                    dotsString[j] = "white";

                } else {
                    dotsString[j] = "empty";
                }

            }
            RowCircle newdots = new RowCircle(dotsString[0], dotsString[1], dotsString[2], dotsString[3]);
            dots.add(newdots);
        }
        System.out.println("--------------------------------------");

        System.out.println(dots.get(row - 1).toString());
        if (black == 4) {
            String[] vinst = new String[1];
            vinst[0] = "vinst";
            players.setNumberOfGames();
            players.setNumberOfWins();
            losNr=row+7;
            
            return vinst;

        } else if ((row) == losNr) {
            String[] lost = new String[1];
            lost[0] = "los";
            players.setNumberOfGames();
            losNr=row+7;
            System.out.println(players.getNumberOfGames());
            

            return lost;
        }

        return dotsString;
    }

    /**
     * creats an array with colors (secret code)
     */
    public void colorGenerated() {
        String[] temp = new String[4];

        for (int i = 0; i < 4; i++) {
            Color randomLight = Color.getRandom();
            temp[i] = randomLight.toString();
        }
        secretColors = new RowCircle(temp[0], temp[1], temp[2], temp[3]);
        

    }

     /**
     * sets the name of the player and increments number of players
     *@param String
     */
    public void newPlayer(String name) {

        players.setName(name);
        filename = new File(name + ".txt");        
        //playerNumer++;

    }
    
    
     /**
     *prints the player name
     */
    public void getPlayers() {

        System.out.println(players.getUserName());
    }

    /**
     * saves the secret code, player guess, player name and dots to file
     */
    public void saveToFile() throws IOException, AlertToUser {

        if (players.getUserName() == null) {
            throw new AlertToUser("You need to create a player!");
        }
        players.lastGame(secretColors, Colors, dots);
        boolean temp2 = file.writeToFile(players, filename);
        System.out.println(temp2);

    }

    /**
     * @return number of wins
     */
    public String getNrWins() {
        // return Integer.toString(Players.getNumberOfWins());
        return "" + players.getNumberOfWins();
    }

    /**
     * @return number of games
     */
    public String getNrGames() {
        //return Integer.toString(Players.getNumberOfGames());
        return "" + players.getNumberOfGames();
    }
    
    /**
     * set number of wins
     */
    public void setNrWins(){
        players.setNumberOfWins();
    }
    
    /**
     * @param args the command line arguments
     */
    public void setNrGames(){
        players.setNumberOfGames();
    }
    
    /**
     * @param File
     *reads from file and sets the color back from saved game
     */
    public void readFromFile(File open) throws ClassNotFoundException, IOException, AlertToUser {
        filename = open;
        players = file.readFromFile(open);
        ArrayList<String> temp = new ArrayList<>();
        secretColors = players.getSecretClass();
        Colors.addAll(players.getColors());
        dots.addAll(players.getDots());

        System.out.println("name: " + players.getUserName());

    }

      /**
     * @return boolean
     */
    public boolean hasGamePlayer() {
        if (players.getUserName() == null) {
            return false;
        }
        return true;
    }
    
    /**
     * @return Arraylist of colors
     */
    public ArrayList<String> getColors() {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < Colors.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(Colors.get(i).getRowCircle(j));

            }
        }
        return temp;
    }

    /**
     * @return Arraylist of color dots
     */
    public ArrayList<String> getDots() {
        ArrayList<String> temp = new ArrayList<String>();
        int nr = 1;
        
        if (row != 0) {
            nr = row;
        } 
        System.out.println("row: "+row+"nr: "+nr);
        for (int i = nr-1; i < dots.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(dots.get(i).getRowCircle(j));
            }
        }
        return temp;
    }

}
