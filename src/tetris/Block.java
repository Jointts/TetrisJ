package tetris;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pagulane on 17.04.16.
 */
class Block extends Region{
    public int xOffset = 0;
    public int yOffset = 0;
    public Tile[][] tileList = new Tile[4][4];
    public int length = 0;

    public void rotate(){
        for (int i = 0; i <= (4-1)/2; i++) {
            for (int j = i; j < 4-i-1; j++) {
                Tile p1 = tileList[i][j];
                Tile p2 = tileList[j][4-i-1];
                Tile p3 = tileList[4-i-1][4-j-1];
                Tile p4 = tileList[4-j-1][i];

                tileList[j][4-i-1] = p1;
                tileList[4-i-1][4-j-1] = p2;
                tileList[4-j-1][i] = p3;
                tileList[i][j] = p4;
            }
        }
    }

    public void shiftDown(){
        for(Tile tile : getMatter()){
            tile.setTranslateY(tile.getTranslateY()+30);
        }
    }

    public void shiftLeft(){
        for(Tile tile : getMatter()){
            tile.setTranslateX(tile.getTranslateX()-30);
        }
    }

    public void shiftRight(){
        for(Tile tile : getMatter()){
            tile.setTranslateX(tile.getTranslateX()+30);
        }
    }

    public Tile[][] getTiles(){
        return tileList;
    }

    public void freezeTiles(){
        for(Tile tile : getMatter()){
            tile.setFilled(true);
        }
    }

    public List<Tile> getMatter(){
        List<Tile> tiles = new ArrayList<>();
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                if(tileList[x][y] == null){continue;}
                tiles.add(tileList[x][y]);
            }
        }
        return tiles;
    }

}
