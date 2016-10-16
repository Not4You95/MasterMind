package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class MasterMindModel {

    private ArrayList<RowCircle> Colors, dots;
    private int row = 0, losNr = 7;
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
     * adding color to the rows
     *
     * @param Arraylist
     */
    public void addColors(ArrayList temp) {
        RowCircle circleRow = new RowCircle(temp.get(0).toString(), temp.get(1).toString(), temp.get(2).toString(), temp.get(3).toString());
        Colors.add(circleRow);
    }

    /**
     * compares the choosen colors and marks the dots with the right color, and
     * increment row. If the user have choosen it returns the string "winner"
     * and then removes data in arrayList colors and dots. If the user looses
     * then it returns String "lost" and then removes data in arrayList colors
     * and dots
     */
    public ArrayList<String> guessOfColors() {
        ArrayList<String> dotsString = new ArrayList<String>();
        int black = 0;

        for (int j = 0; j < 4; j++) {

            if (Colors.get(row).getRowCircle(j).equals(secretColors.getRowCircle(j))) {

                dotsString.add("black");
                black++;

            } else if (secretColors.toString().contains(Colors.get(row).getRowCircle(j))) {

                dotsString.add("white");

            } else {
                dotsString.add("empty");
            }

        }
        RowCircle newdots = new RowCircle(dotsString.get(0), dotsString.get(1), dotsString.get(2), dotsString.get(3));
        dots.add(newdots);
        row++;

        if (black == 4) {

            dotsString.add(0, "winner");
            addNrGames();
            addNrWins();
            players.setGameover(true);
            dots.clear();
            Colors.clear();

            return dotsString;

        } else if (row == losNr) {

            dotsString.add(0, "lost");
            addNrGames();
            players.setGameover(true);
            dots.clear();
            Colors.clear();

            return dotsString;
        }
        return dotsString;
    }

    /**
     * creats an RowCircle with colors (secret code)
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
     * sets the name of the player and creat an text file whid the same name
     *
     * @param String
     */
    public void newPlayer(String name) {

        players.setName(name);
        filename = new File(name + ".txt");

    }

    /**
     * saves the secret code, player guess, player name and dots to file (Same
     * as the users);
     */
    public void saveToFile() throws IOException, AlertToUser {

        if (players.getUserName() != null) {
            players.lastGame(secretColors, Colors, dots);
            file.writeToFile(players, filename);
        } else {
            throw new AlertToUser("You need to create a player!");
        }

    }

    /**
     * @return number of wins in a String
     */
    public String getNrWins() {

        return "" + players.getNumberOfWins();
    }

    /**
     * @return number of games in a string
     */
    public String getNrGames() {

        return "" + players.getNumberOfGames();
    }

    /**
     * add wins with 1
     */
    public void addNrWins() {
        players.addNumberOfWins();
    }

    /**
     * add games with 1
     */
    public void addNrGames() {
        players.addNumberOfGames();
    }

    /**
     * @param File reads from file and sets the color back from saved game
     */
    public void readFromFile(File open) throws ClassNotFoundException, IOException, AlertToUser {
        filename = open;
        players = file.readFromFile(open);

        if (players.isGameover()) {
            colorGenerated();
            row = 0;
            players.setGameover(false);

        } else {
            secretColors = players.getSecretClass();
            Colors.addAll(players.getColors());
            dots.addAll(players.getDots());

            row = Colors.size();

        }

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
     * Returns the colors in arrayList colors
     *
     * @return Arraylist of colors in string
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
     * Returns the dots in arrayList Dots
     *
     * @return Arraylist of color dots in string
     */
    public ArrayList<String> getDots() {
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < dots.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(dots.get(i).getRowCircle(j));
            }
        }

        return temp;
    }

    /**
     * This method returns boolean in the player have lost or win      *
     */

    public boolean isGameover() {
        return players.isGameover();
    }

    /**
     * Set the the value of game over
     */
    public void setGameover(Boolean game) {
        players.setGameover(game);
    }

    /**
     * Remove all data in arrayList colors and dots, and row=0;
     */
    public void clear() {
        Colors.clear();
        dots.clear();
        row = 0;
    }

}
