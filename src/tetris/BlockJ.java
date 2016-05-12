package tetris;

/**
 * Created by pagulane on 21.04.16.
 */
public class BlockJ extends Block{
    public BlockJ(){
        tileList[1][0] = new Tile("jBlock");
        tileList[1][1] = new Tile("jBlock");
        tileList[1][2] = new Tile("jBlock");
        tileList[0][2] = new Tile("jBlock");
    }
}
