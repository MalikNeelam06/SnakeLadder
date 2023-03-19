package com.example.snakeladdergame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize =40 ,width = 10 , height=10 ,
    buttonLine =height*tileSize+50 , infoLine =height*tileSize+20;
Player playerFirst, playerSecond;
boolean firstPlayerTurn = true, gameStart = false;
int diceValue;

    private Pane createContent(){

        Pane root = new Pane();
        root.setPrefSize(width*tileSize ,height*tileSize+100);
        for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {

                Tile tile = new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().addAll(tile);
            }
        }
        Image img =new Image("C:\\Users\\LENOVO\\IdeaProjects\\SnakeLadderGame\\src\\img.png");
        ImageView borImage = new ImageView();
        borImage.setFitWidth(width*tileSize);
        borImage.setFitHeight(height*tileSize);
        borImage.setImage(img);
        root.getChildren().addAll(borImage);
        //button create
        Button startButton=new Button("Start");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);
        Button playerOneButton=new Button("Playerone");
        playerOneButton.setTranslateX(10);
        playerOneButton.setTranslateY(buttonLine);
        Button playerTwoButton=new Button("Playertwo");
        playerTwoButton.setTranslateX(330);
        playerTwoButton.setTranslateY(buttonLine);
        Label diceLabel =new Label("StartGame");
       diceLabel .setTranslateX(180);
       diceLabel .setTranslateY(infoLine);
playerFirst = new Player("Neelam", Color.BLACK, tileSize/2);
playerSecond = new Player("Mukesh",Color.WHITE,tileSize/2-5);
playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        if(gameStart){
            if(firstPlayerTurn){
                diceValue =rollDice();
                diceLabel.setText("Dice :"+diceValue);
                playerFirst.movePlayer(diceValue);
                firstPlayerTurn =!firstPlayerTurn;
                if(playerFirst.checkWinner()){
                    diceLabel.setText("winner is "+ playerFirst.getName());
                    startButton.setText("restart");
                    startButton.setDisable(false);
                    firstPlayerTurn = true;
                    gameStart =false;
                }
            }
        }

    }
});
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(!firstPlayerTurn){
                        diceValue =rollDice();
                        diceLabel.setText("Dice :"+diceValue);
                        playerSecond.movePlayer(diceValue);
                        firstPlayerTurn =!firstPlayerTurn;
                        if(playerSecond.checkWinner()){
                            diceLabel.setText("winner is "+ playerSecond.getName());
                            startButton.setText("restart");
                            startButton.setDisable(false);
                            firstPlayerTurn = true;
                            gameStart =false;
                        }
                    }
                }

            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart =true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();
            }
        });
      root.getChildren().addAll(startButton,playerOneButton,playerTwoButton,diceLabel,playerFirst.getCoin(),
              playerSecond.getCoin()); //p1.getCoin(), p2.getCoin());
        return root;
    }
     private int rollDice(){
        return (int)(Math.random()*6+1);
     }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}