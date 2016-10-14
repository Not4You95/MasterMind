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

    public MasterController(MasterMindModel MasterModel) {
        color = new ArrayList<String>();
        MasterModel = MasterMindModel();
        scen = new MasterMind();
        

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
        }
        if (temp[0]=="vinst") {
           
            
        }else if(temp[0] == "los"){
            
        }
            

    }
    public ArrayList<String> getColors(){
        return MasterModel.getColors();
    }
    public ArrayList<String> getDots(){
        return MasterModel.getDots();
    }   

    public void newGame() {
        MasterModel.colorGenerated();
    }

    public void openFile(File file) throws ClassNotFoundException, IOException, AlertToUser {       
        MasterModel.ReadFromFile(file); 
        
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
