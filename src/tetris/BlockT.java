package tetris;

/**
 * Created by pagulane on 21.04.16.
 */
public class BlockT extends Block{
    public BlockT(){
        tileList[1][0] = new Tile("tBlock");
        tileList[0][1] = new Tile("tBlock");
        tileList[1][1] = new Tile("tBlock");
        tileList[2][1] = new Tile("tBlock");
    }
}
