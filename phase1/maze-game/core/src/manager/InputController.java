package manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import config.GameConstants;
import config.KeyBindings;
import game.entities.characters.Merchant;
import game.entities.characters.Player;
import game.entities.rooms.Level;
import geometry.Point;

/**
 * Represents an input controller
 * @author Ethan
 * @author Ian
 * @author Daniel
 */
public class InputController {
    private Level level;
    private OrthographicCamera camera;

    /**
     * Create an input controller
     * @param level the level
     */
    public InputController(Level level){
        this.level = level;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
    }

    /**
     * Check for input
     */
    public void checkForInput(){
        boolean left = areAnyKeysInListPressed(KeyBindings.MOVE_LEFT_KEYS);
        boolean right = areAnyKeysInListPressed(KeyBindings.MOVE_RIGHT_KEYS);
        boolean up = areAnyKeysInListPressed(KeyBindings.MOVE_UP_KEYS);
        boolean down = areAnyKeysInListPressed(KeyBindings.MOVE_DOWN_KEYS);

        Point dir = new Point(dirCalc(left, right),dirCalc(down, up));
        level.movePlayer(dir);

        Point mousePos = new Point(Gdx.input.getX(), Gdx.input.getY());
        Vector3 unprotectedMousePos = camera.unproject(new Vector3(mousePos.getX(), mousePos.getY(), 0));
        mousePos = new Point(unprotectedMousePos.x, unprotectedMousePos.y);
        level.setMousePos(mousePos);

         if(Gdx.input.isButtonPressed(KeyBindings.USE_CURRENT_ITEM)){
            level.mouseClick(mousePos);
         }

         Player player = level.getPlayer();

//         if (player.hasCollideWithMerchant()){
//             Merchant merchant = player.getCurrMerchant();
//             if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
//                 player.buy(merchant.getItemOwned().get(0));
//             }
//             if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
//                 player.buy(merchant.getItemOwned().get(1));
//             }
//         }
//         if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
//             player.useArmour();
//         }
//         if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
//             player.restoreHealth();
//         }

    }

    private boolean areAnyKeysInListPressed(int[] keys){
        for(int key : keys){
            if(Gdx.input.isKeyPressed(key)){
                return true;
            }
        }

        return false;
    }

    /**
     * Calculate the direction the user wants to move on an axis.
     * @param left indicates whether the key to go left is pressed.
     * @param right indicates whether the key to go right is pressed.
     * @return 1 for right, -1 for left and 0 for no movment wanted.
     */
    private int dirCalc(boolean left, boolean right){
        if(left == right){
            return 0;
        }

        return right ? 1 : -1;
    }
}
