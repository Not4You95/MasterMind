/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    private RowCirckle cirkelRow;
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
    }

    public void addColors(ArrayList temp) {
        cirkelRow = new RowCirckle(temp.get(0).toString(), temp.get(1).toString(), temp.get(2).toString(), temp.get(3).toString());
        Colors.add(cirkelRow);

        System.out.println("User: " + Colors.get(row).toString());
        row++;
    }

    public String[] guessOfColors() {
        String[] test = new String[4];
        int svart = 0;
        for (int i = row - 1; i < row; i++) {

            for (int j = 0; j < 4; j++) {

                if (Colors.get(i).getRowCircel(j).equals(secretColors.getRowCircel(j))) {

                    test[j] = "svart";
                    svart++;

                } else if (secretColors.toString().contains(Colors.get(i).getRowCircel(j))) {

                    test[j] = "vit";

                } else {
                    test[j] = "inget";
                }

            }

        }
        System.out.println("--------------------------------------");

        for (String test1 : test) {
            System.out.println(test1);
        }
        if (svart == 4) {
            String[] vinst = new String[1];
            vinst[0] = "vinst";
            return vinst;

        }

        return test;
    }

    public void colorGenerated() {
        String[] temp = new String[4];

        for (int i = 0; i < 4; i++) {
            Color randomLight = Color.getRandom();
            temp[i] = randomLight.toString();
        }
        secretColors = new RowCirckle(temp[0], temp[1], temp[2], temp[3]);
        System.out.println("Slump: " + secretColors.toString());
    }

    public void newPlayer(String name) {
        
        Players.setName(name);
        PlayerNumer++;

    }

    public void getPlayers() {

        System.out.println(Players.getUserName());
    }

    public void saveToFile() throws IOException, AlertToUser {
        
        if (PlayerNumer == 0) throw new AlertToUser("You need to creat a player!");
            Players.lastGame(secretColors, Colors);
            boolean temp2 = file.writeToFile(Players,filename);
            System.out.println(temp2);

        
    }

    public ArrayList<String> ReadFromFile(File open) throws ClassNotFoundException, IOException, AlertToUser {
        filename = open;
        Players=(file.readFromFile(open));
        ArrayList<String> temp = new ArrayList<>();
        secretColors = Players.getSecretClass();
        Colors.addAll(Players.getColors());

        for (int i = 0; i < Colors.size(); i++) {
            for (int j = 0; j < 4; j++) {
                temp.add(Colors.get(i).getRowCircel(j));

            }
        }
        System.out.println("name: " + Players.getUserName());
        return temp;
    }

}
