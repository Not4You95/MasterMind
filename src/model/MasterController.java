
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.application.Application.launch;
import mastermind.MasterMind;
import java.lang.RuntimeException;
import javafx.scene.paint.Color;

/**
 *
 * @author jonas
 */
public class MasterController {

    private ArrayList<String> color;
    private MasterMindModel MasterModel;
    private MasterMind scen;
    
    /**
     *constructor
     */
    public MasterController(MasterMind MasterClass) {
        color = new ArrayList<String>();
        MasterModel = new MasterMindModel();
        scen = MasterClass;

    }
    
    /**
     *checks if player color is equal with secret code and alerts
     *player if win or lose
     * @param String 
     */
    public void equalColor(String color) {
        String[] temp = new String[4];
        this.color.add(color);
        if (this.color.size() == 4) {
            MasterModel.addColors(this.color);
            temp = MasterModel.guessOfColors();
            this.color.clear();
            scen.uppdatedots(getDots());
        }
        if (temp[0] == "vinst") {
            scen.alertToUserScen("Congratilazen you are a code braker!", "Winner", "Winner!");
            uppdateScore();
            scen.SetGame(Boolean.TRUE);

        } else if (temp[0] == "los") {
            scen.alertToUserScen("You lost, what a losser!", "Warning: Losser alert", "Loser");
            uppdateScore();
            scen.SetGame(Boolean.TRUE);

        }

    }
    
    /**
     * @shows the number of wins and number of games played
     */
    private void uppdateScore() {
        scen.score(MasterModel.getNrWins(), MasterModel.GetNrGames());
    }

    /**
     * @return Arraylist of colors
     */
    public ArrayList<String> getColors() {
        return MasterModel.getColors();
    }
    
   /**
     * @return Arraylist of dots
     */
    public ArrayList<String> getDots() {
        return MasterModel.getDots();
    }
    
    /**
     * sets new gane
     */
    public void newGame() {
        scen.SetGame(Boolean.TRUE);

        if (MasterModel.hasGamePlayer()) {

            scen.SetGame(Boolean.FALSE);
            scen.makeBord();
            uppdateScore();
            MasterModel.colorGenerated();

        } else {
            scen.userInputName();
        }

    }
    
    /**
     * @ shows a window with the rules of the game
     */
    public void showRules() {
        scen.alertToUserScen("Its your job as the codebreaker to figure out the 4 colored secret code that the codemaster has generated. \n \n"
                + "Each row is made up by four colors that is your guess of the secret code. If one of the color match the secret code and it is in the right place, the codemaster will mark it with a black circle on the right side of th board. If the guess has right color but in the wrong order the codemaster will mark it with a white circle. You have seven trys to figure out the secret code before your mind explodes.", "Rules", "Rules");
    }

    /**
     * @param File
     */
    public void openFile(File file) throws AlertToUser, ClassNotFoundException, IOException {
        if (file != null) {

            System.out.println(file.toString());
            MasterModel.ReadFromFile(file);
            scen.makeBord();
            uppdateScore();
            scen.uppdateCirckel(getColors());
            System.out.println("Dots: " + getDots().toString());
            scen.uppdatedots(getDots());
            scen.SetGame(Boolean.FALSE);
        }

    }

    /**
     * saves to file
     */
    public void saveToFile() throws IOException, AlertToUser {
        MasterModel.saveToFile();

    }
    
    /**
     * main
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @param String 
     *sets new player name
     */
    public void newPlayer(String name) {
        MasterModel.newPlayer(name);

    }

    /**
     * @return boolean
     */
    public boolean hasGamePlayers() {
        return MasterModel.hasGamePlayer();
    }

}
