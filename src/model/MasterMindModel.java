/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.scenario.effect.impl.Renderer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Color;
import model.Player;
import model.ReadAndWrite;
import model.RowCirckle;
import java.lang.RuntimeException;

/**
 *
 * @author jonas
 */
public class MasterMindModel {

    /**
     * @param args the command line arguments
     */
    private ArrayList<RowCirckle> Colors;
    private ArrayList<RowCirckle> dots;
    private int row = 0;
    private boolean temp = false;
    private Color genrateColors;
    private RowCirckle secretColors;
    private Player Players=null;
    private int PlayerNumer = 0;
    private ReadAndWrite file;
    private File filename;

    public MasterMindModel() {
        Colors = new ArrayList<RowCirckle>();
        Players = new Player();
        file = new ReadAndWrite();
        dots = new ArrayList<RowCirckle>();
    }

    public void addColors(ArrayList temp) {
       RowCirckle cirkelRow = new RowCirckle(temp.get(0).toString(), temp.get(1).toString(), temp.get(2).toString(), temp.get(3).toString());
        Colors.add(cirkelRow);
        System.out.println("User: " + Colors.get(row).toString());
        row++;
    }

    public String[] guessOfColors() {
        String[] dotsString = new String[4];
        int svart = 0;
        System.out.println(secretColors.toString());
        for (int i = row - 1; i < row; i++) {

            for (int j = 0; j < 4; j++) {

                if (Colors.get(i).getRowCircel(j).equals(secretColors.getRowCircel(j))) {

                    dotsString[j] = "svart";
                    svart++;

                } else if (secretColors.toString().contains(Colors.get(i).getRowCircel(j))) {

                    dotsString[j] = "vit";

                } else {
                    dotsString[j] = "inget";
                }
                
            }
            RowCirckle newdots = new RowCirckle(dotsString[0], dotsString[1], dotsString[2], dotsString[3]);
            dots.add(newdots);
        }
        System.out.println("--------------------------------------");

        System.out.println(dots.get(row-1).toString());
        if (svart == 7) {
            String[] vinst = new String[1];
            vinst[0] = "vinst";
            Players.setNumberOfGames();
            Players.setNumberOfWins();
            return vinst;

        }else if(row == 7){
            String[] lost = new String[1];
            lost[0]="los";
            Players.setNumberOfGames();
            System.out.println(Players.getNumberOfGames());
            
            return lost;
        }

        return dotsString;
    }

    public void colorGenerated() {
        String[] temp = new String[4];

        for (int i = 0; i < 4; i++) {
            Color randomLight = Color.getRandom();
            temp[i] = randomLight.toString();
        }
        secretColors = new RowCirckle(temp[0], temp[1], temp[2], temp[3]);
       
    }

    public void newPlayer(String name) {
        
        Players.setName(name);
        filename  = new File(name+".txt");
        PlayerNumer++;

    }

    public void getPlayers() {

        System.out.println(Players.getUserName());
    }

    public void saveToFile() throws IOException, AlertToUser {
        
        if (Players.getUserName() == null) throw new AlertToUser("You need to creat a player!");
            Players.lastGame(secretColors, Colors,dots);
            boolean temp2 = file.writeToFile(Players,filename);
            System.out.println(temp2);

        
    }
    public String getNrWins(){
       // return Integer.toString(Players.getNumberOfWins());
       return ""+Players.getNumberOfWins();
    }
    public String GetNrGames(){
        //return Integer.toString(Players.getNumberOfGames());
        return ""+Players.getNumberOfGames();
    }

    public void ReadFromFile(File open) throws ClassNotFoundException, IOException, AlertToUser {
        filename = open;
        Players=(file.readFromFile(open));
        ArrayList<String> temp = new ArrayList<>();
        secretColors = Players.getSecretClass();
        Colors.addAll(Players.getColors());
        dots.addAll(Players.getDots());
       
        System.out.println("name: " + Players.getUserName());
        
    }
    public boolean hasGamePlayer(){
        if (Players.getUserName() == null) {
            return false;            
        }
        return true;
    }
    
    public ArrayList<String> getColors(){
        ArrayList<String> temp = new ArrayList<String>();
         for (int i = 0; i < Colors.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(Colors.get(i).getRowCircel(j));

            }
        }
        return temp;
    }
    
    public ArrayList<String> getDots(){
       ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(dots.get(i).getRowCircel(j));

            }
        }
        return temp;
    }

}
