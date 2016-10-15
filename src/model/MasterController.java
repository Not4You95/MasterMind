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
     * constructor
     */
    public MasterController(MasterMind MasterClass) {
        color = new ArrayList<String>();
        MasterModel = new MasterMindModel();
        scen = MasterClass;

    }

    /**
     * checks if player color is equal with secret code and alerts player if win
     * or lose
     *
     * @param String
     */
    public void equalColor(String color) {
        String[] temp = new String[4];
        this.color.add(color);
        if (this.color.size() == 4) {
            MasterModel.addColors(this.color);
            temp = MasterModel.guessOfColors();
            this.color.clear();
            scen.updatedots(getDots());
        }
        if (temp[0] == "winner") {
            scen.alertToUserScen("Congratilazen you are a code breaker!", "Winner", "Winner!");
            updateScore();
            scen.setGame(Boolean.TRUE);

        } else if (temp[0] == "lost") {
            scen.alertToUserScen("You lost, what a loser!", "Warning: Loser alert", "Loser");
            updateScore();
            scen.setGame(Boolean.TRUE);

        }

    }

    /**
     * @shows the number of wins and number of games played
     */
    private void updateScore() {
        scen.score(MasterModel.getNrWins(), MasterModel.getNrGames());
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
        scen.setGame(Boolean.TRUE);

        if (MasterModel.hasGamePlayer()) {
            scen.stopAnimation();
            scen.printButtens();
            scen.setGame(Boolean.FALSE);
            scen.makeBord();
            updateScore();
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
                + "Each row is made up by four colors that is your guess of the secret code.+"
                + " If one of the color match the secret code and it is in the right place, the codemaster will mark it with a black circle on the right side of th board. "
                + "If the guess has right color but in the wrong order the codemaster will mark it with a white circle. You have seven trys to figure out the secret code before your mind explodes.", "Rules", "Rules");
    }

    /**
     * @param File
     */
    public void openFile(File file) throws AlertToUser, ClassNotFoundException, IOException {
        if (file != null) {
            scen.stopAnimation();
            scen.printButtens();
            System.out.println(file.toString());
            MasterModel.readFromFile(file);
            scen.makeBord();
            updateScore();
            scen.updateCircle(getColors());
            System.out.println("Dots: " + getDots().toString());
            scen.updatedots(getDots());
            scen.setGame(Boolean.FALSE);
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
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param String sets new player name
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
