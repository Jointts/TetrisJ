package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pagulane on 16.04.16.
 */
class Game {

    private int xSize;
    private int ySize;
    private int difficulty;
    private Stage primaryStage;
    public Scene gameScene;
    private GridPane boardPane = new GridPane();
    public Block fallingBlock = null;
    private int fallingTime;
    int counter = 0;
    List<Tile> droppedTiles = new ArrayList<>();
    private String[] blockTypes = {"BlockI", "BlockJ", "BlockL",
            "BlockO", "BlockS", "BlockT", "BlockZ"};

    Game(int xSize, int ySize, int difficulty, Stage primaryStage) throws IOException {
        this.xSize = xSize;
        this.ySize = ySize;
        this.difficulty = difficulty;
        this.primaryStage = primaryStage;
        generateBoard(xSize, ySize);
        //  Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene gameScene = new Scene(boardPane, 30*xSize, 30*ySize);
        this.gameScene = gameScene;
        gameScene.getStylesheets().add("css/game.css");
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(gameScene);
        new InputHandler(this);
        primaryStage.show();
        getBlock();
        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build()
                .play();
    }

    private void generateBoard(int xSize, int ySize){
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                Tile tile = new Tile();
                tile.setPrefHeight(500/xSize*30);
                tile.setPrefWidth(500/ySize*30);
                boardPane.add(new Tile(), x, y);
            }
        }
    }

    public void blockAction(String action){

        switch (action) {
            case "left":
                fallingBlock.shiftLeft();
                break;
            case "right":
                fallingBlock.shiftRight();
                break;
            case "down":
                if(canDrop()){
                    fallingBlock.shiftDown();
                    break;
                }
                droppedTiles.addAll(fallingBlock.getMatter());
                fallingBlock.freezeTiles();
                getBlock();
                break;
            case "rotate":
                fallingBlock.rotate();
                break;
        }
    }


    private void getBlock(){
        System.out.println("New block created!");
        System.out.println("------------------");
        String randomBlock = "tetris." + blockTypes[(int) Math.floor(Math.random() * 6)];
        try {
            Class block = Class.forName(randomBlock);
            fallingBlock = (Block) block.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderBlock();
    }

    private void renderBlock(){
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                if(fallingBlock.getTiles()[x][y] == null){continue;}
                Tile fallingTile = fallingBlock.getTiles()[x][y];
                boardPane.add(fallingTile, x + fallingBlock.xOffset, y + fallingBlock.yOffset);
            }
        }
    }

    private boolean canDrop(){
        for(Tile tileFalling : fallingBlock.getMatter()){
            if(tileFalling.getBoundsInParent().getMaxY() == ySize * 30){
                return false;
            }
            System.out.println(tileFalling.getBoundsInParent());
            for(Tile tileDropped : droppedTiles){
                if (tileDropped.getBoundsInParent().contains(tileFalling.getBoundsInParent())) {
                    return false;
                }
            }
        }
        return true;
    }


    private KeyFrame oneFrame = new KeyFrame(Duration.millis(1500), (EventHandler<ActionEvent>) event -> {
        blockAction("down");
    });



}
