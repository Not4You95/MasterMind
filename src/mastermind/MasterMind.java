/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import model.*;

/**
 *
 * @author jonas
 */
public class MasterMind extends Application {

   
    private HBox bottonBox;
    private GridPane grid, dotsbox,animoBox;
    private MasterController controller;
    private BorderPane pane;
    private int colum = 0, row = 6, dotcolum = 0, dotrow = 6;
    private boolean gameOver = true;
    private int circleSize = 20;
    private Button redbutton, greenButton, blueButton, purpleButton, okName;
    private MenuItem open, save, rules, newGame, newPlayer, aboutGame;
    private Scene scene;
    private Stage fileStage, nameStage;
    private TextField name = null;
    private Alert alert;
    private ArrayList<PathTransition> ptArray;
    

    @Override
    public void start(Stage primaryStage) {
        grid = new GridPane();
        pane = new BorderPane();
        controller = new MasterController(this);
        dotsbox = new GridPane();
        MenuBar menuBar = new MenuBar();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(30);
        dotsbox.setHgap(10);
        dotsbox.setVgap(50);
        dotsbox.setAlignment(Pos.CENTER_RIGHT);
///////////////////////IMAGE///////////////////////////////////////////////////////////////////
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("Wood2.jpg")));
        pane.getChildren().addAll(image);
   ////////////////////BUTTENS/////////////////////////////////////////////////// 
        redbutton = new Button(null);

        redbutton.setStyle("-fx-background-radius: 5em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; "
                + "-fx-max-width: 50px; "
                + "-fx-max-height: 50px;"
                + "-fx-background-color: red;");

        greenButton = new Button(null);
        greenButton.setStyle("-fx-background-radius: 5em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; "
                + "-fx-max-width: 50px; "
                + "-fx-max-height: 50px;"
                + "-fx-background-color: green;");
        blueButton = new Button(null);
        blueButton.setStyle("-fx-background-radius: 5em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; "
                + "-fx-max-width: 50px; "
                + "-fx-max-height: 50px;"
                + "-fx-background-color: blue;");
        purpleButton = new Button(null);
        purpleButton.setStyle("-fx-background-radius: 5em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; "
                + "-fx-max-width: 50px; "
                + "-fx-max-height: 50px;"
                + "-fx-background-color: purple;");

        redbutton.addEventHandler(ActionEvent.ACTION, new colorButton());
        greenButton.addEventHandler(ActionEvent.ACTION, new colorButton());
        blueButton.addEventHandler(ActionEvent.ACTION, new colorButton());
        purpleButton.addEventHandler(ActionEvent.ACTION, new colorButton());
//////////////////////////MENY////////////////////////////////////////////////////////
        Menu meny = new Menu("File");
        open = new MenuItem("Open");
        open.addEventHandler(ActionEvent.ACTION, new menuChoise());
        save = new MenuItem("Save");
        save.addEventHandler(ActionEvent.ACTION, new menuChoise());
        newGame = new MenuItem("New Game");
        newGame.addEventHandler(ActionEvent.ACTION, new menuChoise());
        newPlayer = new MenuItem("New Player");
        newPlayer.addEventHandler(ActionEvent.ACTION, new menuChoise());
////////////////////////////SECEND MENY/////////////////////////////////////////////////
        meny.getItems().addAll(newGame, open, save, newPlayer);
        Menu about = new Menu("About");
        rules = new MenuItem("Rules");
        aboutGame = new MenuItem("About us");
        aboutGame.addEventHandler(ActionEvent.ACTION, new menuChoise());
        rules.addEventHandler(ActionEvent.ACTION, new menuChoise());
        about.getItems().addAll(rules, aboutGame);
//////////////////////////////MEDNY DESIGN//////////////////////////////////////////////////////////        
        menuBar.setStyle("-fx-background-color: #a6b5c9,linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);"
                + "-fx-background-insets: 0 0 -1 0,0,1;"
                + "-fx-background-radius: 5,5,4;"
                + "-fx-padding: 7 30 7 30;"
                + "-fx-text-fill: #242d35;"
                + "-fx-font-family: Helvetica;"
                + "-fx-font-size: 14px;"
                + "-fx-text-fill: Withe;");
        menuBar.getMenus().addAll(meny, about);
        ////////////////////////////////////////

        bottonBox = new HBox(redbutton, greenButton, blueButton, purpleButton);
        bottonBox.setAlignment(Pos.CENTER);
        bottonBox.setSpacing(20);

        ////////////////////////////////////////////   
       
        pane.setRight(dotsbox);
        pane.setTop(menuBar);

        BorderPane.setAlignment(bottonBox, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setAlignment(dotsbox, Pos.CENTER);
        animation();

        scene = new Scene(pane, 720, 800);
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(720);
        primaryStage.setMinHeight(720);
        primaryStage.setMaxWidth(800);
        primaryStage.setTitle("MasterMind");
        primaryStage.setScene(scene);
       
        primaryStage.show();

    }

    public void printButtens() {

        pane.setBottom(bottonBox);
    }

    public void alertToUserScen(String info, String head, String titel) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(head);
        alert.setTitle(titel);
        alert.setContentText(info);
        alert.show();
    }

    public void ColorButtenChoise(Color c) {

        if (!gameOver) {

            Circle ci = new Circle(circleSize, c);
            grid.add(ci, colum, row);
            colum++;

            if (colum == 4 && row != 0) {
                colum = 0;
                row--;
            }

        }

        // System.out.println("colum: " + colum + "\n row: " + row);
    }

    public void score(String winsNumer, String gamesnumer) {
        Label winsNr, gamesNr, wins, games;
        GridPane scorePane = new GridPane();
        wins = new Label("Wins");
        wins.setTextFill(Color.WHITE);

        games = new Label("Games");
        games.setTextFill(Color.WHITE);
        /////////////////////////////////
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(30);
        line.setEndX(0);
        line.setEndY(0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(3);
        ///////////////////////////////
        winsNr = new Label(winsNumer);
        winsNr.setTextFill(Color.WHITE);
        gamesNr = new Label(gamesnumer);
        gamesNr.setTextFill(Color.WHITE);
        ///////////////////////////////
        scorePane.setVgap(10);
        scorePane.setHgap(10);
        scorePane.add(wins, 0, 0);
        scorePane.add(line, 1, 0);
        scorePane.add(games, 3, 0);
        scorePane.add(winsNr, 0, 1);
        scorePane.add(gamesNr, 3, 1);
        pane.setLeft(scorePane);
        //////////////////////////////////
    }

    public void makeBord() {
         pane.setCenter(grid);
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 4; j++) {
                Circle temp = new Circle(circleSize, Color.AQUA);
                grid.add(temp, j, i);
                Circle dots = new Circle(10, Color.CHOCOLATE);
                dotsbox.add(dots, j, i);
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
                    gameOver = false;
                    File tempfile = null;
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    tempfile = fileChooser.showOpenDialog(fileStage);
                    controller.openFile(tempfile);

                } catch (AlertToUser ex) {
                    alertToUserScen(ex.getMessage(), "Error", "Error!");
                } catch (ClassNotFoundException ex) {
                    alertToUserScen(ex.getMessage(), "Error", "Error!");
                } catch (IOException ex) {
                    alertToUserScen(ex.getMessage(), "Error", "Error!");
                }
            } else if (event.getSource() == save) {
                try {
                    controller.saveToFile();
                } catch (IOException ex) {
                    alertToUserScen(ex.getMessage(), "Error", "Error!");
                } catch (AlertToUser ex) {
                    alertToUserScen(ex.getMessage(), "Error", "Error!");
                }

            } else if (event.getSource() == rules) {
                controller.showRules();
            } else if (event.getSource() == newGame) {
                controller.newGame();
            } else if (event.getSource() == newPlayer) {
                userInputName();
            } else if (event.getSource() == okName) {
                controller.newPlayer(name.getText().toString());
                nameStage.close();

            } else if (event.getSource() == aboutGame) {
                animation();

            }

        }

    }

    public void userInputName() {
        int with = 300, hight = 150;
        BorderPane pane2 = new BorderPane();
        nameStage = new Stage();
        Scene temp = new Scene(pane2, with, hight);
        name = new TextField("Name");
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

    public void updateCircle(ArrayList<String> temp) {

        for (int i = 0; i < temp.size(); i++) {

            if (temp.get(i).contains("red")) {

                ColorButtenChoise(Color.RED);

            } else if (temp.get(i).contains("green")) {

                ColorButtenChoise(Color.GREEN);
            } else if (temp.get(i).contains("purple")) {

                ColorButtenChoise(Color.PURPLE);
            } else if (temp.get(i).contains("blue")) {

                ColorButtenChoise(Color.BLUE);
            }

        }
    }

    public class colorButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Color c = null;
            String co = "";
            if (!gameOver) {

                if (event.getSource() == redbutton) {
                    c = Color.RED;
                    co = "red";
                } else if (event.getSource() == blueButton) {
                    c = Color.BLUE;
                    co = "blue";
                } else if (event.getSource() == greenButton) {
                    c = Color.GREEN;
                    co = "green";
                } else if (event.getSource() == purpleButton) {
                    c = Color.PURPLE;
                    co = "purple";
                }
                ColorButtenChoise(c);
                controller.equalColor(co);

            }
        }
    }

    public void updatedots(ArrayList<String> dots) {

        for (int i = 0; i < dots.size(); i++) {
            System.out.println(dots.get(i));
            if (dots.get(i).equals("black")) {

                MakeDots(Color.BLACK);

            } else if (dots.get(i).equals("white")) {
                MakeDots(Color.WHITE);
            } else if (dots.get(i).equals("empty")) {
                MakeDots(Color.CHOCOLATE);
            }

        }
      
    }

    public void MakeDots(Color c) {

        if (!gameOver) {

            Circle ci = new Circle(10, c);
            dotsbox.add(ci, dotcolum, dotrow);
            dotcolum++;
            if (dotcolum == 4 && dotrow != 0) {
                dotcolum = 0;
                dotrow--;
            }

        }
    }

    public void setGame(Boolean game) {
        gameOver = game;
        if (game) {
            row = 6;
            colum = 0;
            dotrow = 6;
            dotcolum = 0;

        }
    }

    public void animation() {
        ptArray = new ArrayList<PathTransition>();
        animoBox = new GridPane();
        makeAnimation(Color.RED, 0, 1);
        makeAnimation(Color.GREEN, 0, 2);
        makeAnimation(Color.BLUE, 0, 3);
        makeAnimation(Color.PURPLE, 0, 4);
        animoBox.setHgap(10);

        for (int i = 0; i < ptArray.size(); i++) {
            ptArray.get(i).play();
        }
        pane.setCenter(animoBox);
        animoBox.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(animoBox, Pos.CENTER);

    }

    private void makeAnimation(Color c, int arow, int acolum) {
        Circle colorCirckel = new Circle(30, c);

        // Create a circle
        Circle circle = new Circle(125, 100, 50);

        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(0);
        circle.setStroke(Color.BLACK);
        Line line = new Line(0, -250, 0, 250);

        // Add circle and rectangle to the pane
        /* pane.getChildren().add(circle);
        pane.getChildren().addAll(red);*/
        animoBox.add(colorCirckel, acolum, arow);
        // Create a path transition
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(line);
        pt.setNode(colorCirckel);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        ptArray.add(pt);

    }

    public void stopAnimation() {
        for (int i = 0; i < ptArray.size(); i++) {
            ptArray.get(i).stop();
        }
        
    }
}
