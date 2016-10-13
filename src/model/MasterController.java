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

    public MasterController() {
        color = new ArrayList<String>();
        MasterModel = new MasterMindModel();

    }

    public void equalColor(Color c) {

    }

    public void equalColor(String color) {
        this.color.add(color);
        System.out.println("hej");
        if (this.color.size() == 4) {
            MasterModel.addColors(this.color);
            System.out.println("hej!!!!!!!!!!!!!!!");
            MasterModel.guessOfColors();
            this.color.clear();
        }

    }

    public void makeDot() {

    }

    public void newGame() {
        MasterModel.colorGenerated();
    }

    public ArrayList<String> openFile(File file) throws ClassNotFoundException, IOException,AlertToUser {
        ArrayList<String> temp = new ArrayList<String>();
        
            temp.addAll(MasterModel.ReadFromFile(file));
            
        

        return temp;
    }

    public void saveToFile() throws IOException,AlertToUser {

        
             MasterModel.saveToFile();
        

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void newPlayer(String name) {
        MasterModel.newPlayer(name);

    }
    
   

}
