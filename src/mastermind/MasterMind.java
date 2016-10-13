/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.TokenType;
import model.*;

/**
 *
 * @author jonas
 */
public class MasterMind extends Application {

    private Circle red = new Circle(30, Color.RED);
    private Circle green = new Circle(30, Color.LIME);
    private Circle blue = new Circle(30, Color.BLUE);
    private Circle purpil = new Circle(30, Color.PURPLE);
    private Circle brown = new Circle(30, Color.BROWN);
    private HBox botBox;

    private GridPane grid, dotsbox;
    private BoardLayout board;
    private MasterController controller;
    private BorderPane pane;
    private int colum = 0, row = 6;
    private boolean gameOver = false;
    private int cirkelSize = 20;
    private Button redbutton, greenButton, blueButton, purpilButton, okName;
    private MenuItem open, save, rules, newGame, newPlayer, AboutGame;
    private Scene scene;
    private Stage test, nameStage;
    private TextField name = null;
    private Alert alert;

    @Override
    public void start(Stage primaryStage) {
        grid = new GridPane();
        pane = new BorderPane();
        controller = new MasterController();
        dotsbox = new GridPane();
        MenuBar menuBar = new MenuBar();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(30);
        dotsbox.setHgap(10);
        dotsbox.setVgap(50);
        dotsbox.setAlignment(Pos.CENTER_RIGHT);

        controller.newGame();

        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("Wood2.jpg")));
        pane.getChildren().addAll(image);

        redbutton = new Button(null, red);
        greenButton = new Button(null, green);
        blueButton = new Button(null, blue);
        purpilButton = new Button(null, purpil);
        redbutton.addEventHandler(ActionEvent.ACTION, new colorButton());
        greenButton.addEventHandler(ActionEvent.ACTION, new colorButton());
        blueButton.addEventHandler(ActionEvent.ACTION, new colorButton());
        purpilButton.addEventHandler(ActionEvent.ACTION, new colorButton());

        ProgressBar p2 = new ProgressBar();

        Menu meny = new Menu("File");
        open = new MenuItem("Open");
        open.addEventHandler(ActionEvent.ACTION, new menuChoise());
        save = new MenuItem("Save");
        save.addEventHandler(ActionEvent.ACTION, new menuChoise());
        newGame = new MenuItem("New Game");
        newGame.addEventHandler(ActionEvent.ACTION, new menuChoise());
        newPlayer = new MenuItem("New Player");
        newPlayer.addEventHandler(ActionEvent.ACTION, new menuChoise());

        meny.getItems().addAll(newGame, open, save, newPlayer);

        Menu about = new Menu("About");
        rules = new MenuItem("Rules");
        AboutGame = new MenuItem("About us");
        AboutGame.addEventHandler(ActionEvent.ACTION, new menuChoise());

        rules.addEventHandler(ActionEvent.ACTION, new menuChoise());
        about.getItems().addAll(rules, AboutGame);

        menuBar.getMenus().addAll(meny, about);
        ////////////////////////////////////////
        botBox = new HBox(redbutton, greenButton, blueButton, purpilButton);

        botBox.setAlignment(Pos.CENTER);
        botBox.setSpacing(10);
        ////////////////////////////////////////////   
        pane.setBottom(botBox);
        pane.setCenter(grid);
        pane.setRight(dotsbox);
        pane.setTop(menuBar);
        pane.setLeft(p2);
        BorderPane.setAlignment(botBox, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setAlignment(dotsbox, Pos.CENTER);

        scene = new Scene(pane, 720, 800);
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(720);
        primaryStage.setMinHeight(720);
        primaryStage.setMaxWidth(800);
        primaryStage.setTitle("MasterMind");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void alertToUserScen(String info) {
        System.out.println("hej");
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Error");
        alert.setTitle("Error!");
        System.out.println("hej");
        alert.setContentText(info);
        alert.show();
    }

    public void ColorButtenChoise(Color c, int a) {

        if (!gameOver) {

            Circle ci = new Circle(cirkelSize, c);
            grid.add(ci, colum, a);

            colum++;
            if (colum == 4 && row != 0) {
                colum = 0;
                row--;
            }

        }
        if (row == 0 && colum == 4) {
            gameOver = true;
        }

        // System.out.println("colum: " + colum + "\n row: " + row);
    }

    public void makeBord() {
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 4; j++) {
                Circle temp = new Circle(cirkelSize, Color.AQUA);
                grid.add(temp, j, i);
            }

        }
    }

    public void makeDots() {

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 4; j++) {
                Circle temp = new Circle(10, Color.CHOCOLATE);
                dotsbox.add(temp, j, i);
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    private class menuChoise implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == open) {
                try {

                    ArrayList<String> temp = new ArrayList<String>();
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showOpenDialog(test);
                    temp.addAll(controller.openFile(file));
                    makeBord();
                    makeDots();
                    uppDateCirckel(temp);

                } catch (ClassNotFoundException ex) {
                    System.out.println("Classnotfound");
                } catch (IOException ex) {
                    System.out.println("ioException");
                } catch (AlertToUser ex) {
                    alertToUserScen(ex.getMessage());
                }

            } else if (event.getSource() == save) {
                try {
                    controller.saveToFile();
                } catch (IOException ex) {
                } catch (AlertToUser ex) {
                    alertToUserScen(ex.getMessage());
                }

            } else if (event.getSource() == rules) {

            } else if (event.getSource() == newGame) {
                userInputName();
                makeBord();
                makeDots();

            } else if (event.getSource() == newPlayer) {
                userInputName();
            } else if (event.getSource() == okName) {
                controller.newPlayer(name.getText().toString());
                nameStage.close();

            } else if (event.getSource() == AboutGame) {

            }

        }
    }

    private void userInputName() {
        int with = 300, hight = 150;
        BorderPane pane2 = new BorderPane();
        nameStage = new Stage();
        Scene temp = new Scene(pane2, with, hight);
        name = new TextField();
        name.setMaxWidth(200);
        name.setMaxHeight(10);
        Label askname = new Label("Name: ");
        okName = new Button("Ok");

        okName.addEventFilter(ActionEvent.ACTION, new menuChoise());
        pane2.setLeft(askname);
        pane2.setCenter(name);
        pane2.setBottom(okName);
        BorderPane.setAlignment(okName, Pos.TOP_CENTER);
        BorderPane.setAlignment(name, Pos.CENTER);
        BorderPane.setAlignment(askname, Pos.CENTER_RIGHT);
        nameStage.setTitle("Name");
        nameStage.setMaxHeight(hight);
        nameStage.setMaxWidth(with);
        nameStage.setMinHeight(hight);
        nameStage.setMinWidth(with);
        nameStage.setScene(temp);
        nameStage.show();

    }

    private void uppDateCirckel(ArrayList<String> temp) {
        for (int i = 0; i < temp.size(); i++) {

            if (temp.get(i).contains("red")) {

                ColorButtenChoise(Color.RED, row);

            } else if (temp.get(i).contains("green")) {

                ColorButtenChoise(Color.GREEN, row);
            } else if (temp.get(i).contains("purpil")) {

                ColorButtenChoise(Color.PURPLE, row);
            } else if (temp.get(i).contains("blue")) {

                ColorButtenChoise(Color.BLUE, row);
            }

        }
    }

    private class colorButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Color c = null;
            String co = "";
            if (event.getSource() == redbutton) {
                c = Color.RED;
                co = "red";
            } else if (event.getSource() == blueButton) {
                c = Color.BLUE;
                co = "blue";
            } else if (event.getSource() == greenButton) {
                c = Color.GREEN;
                co = "green";
            } else if (event.getSource() == purpilButton) {
                c = Color.PURPLE;
                co = "purpil";
            }

            controller.equalColor(co);
            ColorButtenChoise(c, row);

        }
    }

}
