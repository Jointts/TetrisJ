package tetris;

/**
 * Created by pagulane on 21.04.16.
 */
public class BlockS extends Block{
    public BlockS(){
        tileList[1][0] = new Tile("sBlock");
        tileList[2][0] = new Tile("sBlock");
        tileList[1][1] = new Tile("sBlock");
        tileList[0][1] = new Tile("sBlock");
    }
}
