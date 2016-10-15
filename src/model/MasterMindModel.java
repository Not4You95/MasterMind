package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class MasterMindModel {

    private ArrayList<RowCircle> Colors;
    private ArrayList<RowCircle> dots;
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
     * adding color to the rows and increment row
     *
     * @param Arraylist
     */
    public void addColors(ArrayList temp) {
        RowCircle circleRow = new RowCircle(temp.get(0).toString(), temp.get(1).toString(), temp.get(2).toString(), temp.get(3).toString());
        Colors.add(circleRow);
    }

    /**
     * compares the choosen colors and marks the dots with the right color
     */
    public ArrayList<String> guessOfColors() {
        ArrayList<String> dotsString = new ArrayList<String>();
        int black = 0;
        System.out.println(secretColors.toString());
        System.out.println("guess line: " + Colors.get(row).toString());
        System.out.println("guess nr row: " + row + " nr color: " + Colors.size());
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

        System.out.println("--------------------------------------");

        if (black == 4) {

            dotsString.add(0, "winner");
            players.setNumberOfGames();
            players.setNumberOfWins();
            players.setGameover(true);
            dots.clear();
            Colors.clear();

            return dotsString;

        } else if (row == losNr) {

            dotsString.add(0, "lost");
            players.setNumberOfGames();
            players.setGameover(true);
            dots.clear();
            Colors.clear();

            return dotsString;
        }
        System.out.println("model dots: " + dotsString.toString());
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
     *
     * @param String
     */
    public void newPlayer(String name) {

        players.setName(name);
        filename = new File(name + ".txt");

    }

    /**
     * prints the player name
     */
    public void getPlayers() {

        System.out.println(players.getUserName());
    }

    /**
     * saves the secret code, player guess, player name and dots to file
     */
    public void saveToFile() throws IOException, AlertToUser {
       
        if (players.getUserName() != null) {
            
            
            System.out.println("Den sparade datan: "+Colors.toString());
            players.lastGame(secretColors, Colors, dots);
            file.writeToFile(players, filename);
        } else {
            throw new AlertToUser("You need to create a player!");
        }

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
    public void setNrWins() {
        players.setNumberOfWins();
    }

    /**
     * @param args the command line arguments
     */
    public void setNrGames() {
        players.setNumberOfGames();
    }

    /**
     * @param File reads from file and sets the color back from saved game
     */
    public void readFromFile(File open) throws ClassNotFoundException, IOException, AlertToUser {
        filename = open;
        players = file.readFromFile(open);
         secretColors = players.getSecretClass();
            Colors.addAll(players.getColors());
            dots.addAll(players.getDots());
            System.out.println("Läst data: "+Colors.toString());
            
            row = Colors.size();

       /* if (players.isGameover()) {
            System.out.println("new game!!!!!!!!!!!!!!!!!!");
            colorGenerated();
            row = 0;
            players.setGameover(false);
            System.out.println("guess nr row: " + row + " nr color: " + Colors.size());

        } else {
            System.out.println("lkhkjnlkmlkmlk-");
            secretColors = players.getSecretClass();
            Colors.addAll(players.getColors());
            dots.addAll(players.getDots());
            System.out.println("Läst data: "+Colors.toString());
            
            row = Colors.size();

        }*/

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
        int nr;
        if (row == 0) {
            nr = 1;
        } else {
            nr = 1;
        }
        for (int i = nr - 1; i < Colors.size(); i++) {
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

        int nr;
        if (row == 0) {
            nr = 1;
        } else {
            nr = 1;
        }

        for (int i = nr - 1; i < dots.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(dots.get(i).getRowCircle(j));
            }
        }

        return temp;
    }

    public boolean gameover() {
        return players.isGameover();
    }

    public void setGameover(Boolean game) {
        System.out.println("GameOver: " + game);
        players.setGameover(game);
    }
    
    public void clear(){
        Colors.clear();
        dots.clear();
    }

}
