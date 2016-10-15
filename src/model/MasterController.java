/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public MasterController(MasterMind MasterClass) {
        color = new ArrayList<String>();
        MasterModel = new MasterMindModel();
        scen = MasterClass;

    }

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

    private void uppdateScore() {
        scen.score(MasterModel.getNrWins(), MasterModel.GetNrGames());
    }

    public ArrayList<String> getColors() {
        return MasterModel.getColors();
    }

    public ArrayList<String> getDots() {
        return MasterModel.getDots();
    }

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

    public void showRules() {
        scen.alertToUserScen("Its your job as the codebreaker to figure out the 4 colored secret code that the codemaster has generated. \n \n"
                + "Each row is made up by four colors that is your guess of the secret code. If one of the color match the secret code and it is in the right place, the codemaster will mark it with a black circle on the right side of th board. If the guess has right color but in the wrong order the codemaster will mark it with a white circle. You have seven trys to figure out the secret code before your mind explodes.", "Rules", "Rules");
    }

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

    public void saveToFile() throws IOException, AlertToUser {
        MasterModel.saveToFile();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void newPlayer(String name) {
        MasterModel.newPlayer(name);

    }

    public boolean hasGamePlayers() {
        return MasterModel.hasGamePlayer();
    }

}
