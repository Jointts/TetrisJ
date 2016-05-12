package tetris;

/**
 * Created by pagulane on 19.04.16.
 */
public class InputHandler {
    public InputHandler(Game game){
        game.gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:     game.blockAction("right"); break;
                case LEFT:      game.blockAction("left"); break;
                case DOWN:      game.blockAction("down"); break;
                case UP:        game.blockAction("rotate"); break;
            }
        });
    }
}
