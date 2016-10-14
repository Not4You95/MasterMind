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

    private int colorNuber = 0;
    private ArrayList<String> color;
    private MasterMindModel MasterModel;
    private MasterMind scen;

    public MasterController(MasterMind MasterClass) {
        color = new ArrayList<String>();
        MasterModel = new MasterMindModel();
        scen = MasterClass;

    }

    public void equalColor(Color c) {

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
            scen.score();

        } else if (temp[0] == "los") {
            scen.score();

        }

    }

    public ArrayList<String> getColors() {
        return MasterModel.getColors();
    }

    public ArrayList<String> getDots() {
        return MasterModel.getDots();
    }

    public void newGame() {
        System.out.println("new game");
        boolean round = true;
        scen.userInputName();
        System.out.println(hasGamePlayers());
        while (round) {

            if (hasGamePlayers()) {
                System.out.println(hasGamePlayers());
                scen.SetGame(Boolean.FALSE);
                scen.makeBord();
                scen.score();
                MasterModel.colorGenerated();
                round = false;
            }
        }

    }

    public void openFile(File file) throws AlertToUser, ClassNotFoundException, IOException {
        if (file != null) {
            System.out.println(file.toString());
            MasterModel.ReadFromFile(file);
            scen.makeBord();
            scen.score();
            scen.uppdateCirckel(getColors());
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

    public String getNrWins() {
        return MasterModel.getNrWins();

    }

    public String getNrGames() {
        return MasterModel.GetNrGames();

    }

    public boolean hasGamePlayers() {
        return MasterModel.hasGamePlayer();
    }

}
