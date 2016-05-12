package tetris;

import javafx.scene.layout.Region;

/**
 * Created by pagulane on 16.04.16.
 */
class Tile extends Region{

    private boolean filled = false;

    Tile(){
        setId("emptyBlock");
    }

    Tile(String id){
        setId(id);
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
