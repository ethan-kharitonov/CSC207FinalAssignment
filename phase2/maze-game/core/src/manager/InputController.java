package manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import config.GameConstants;
import config.KeyBindings;
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


         boolean keyPressed = false;
         boolean[] numberKeys = new boolean[10];
         for(int i = 0; i < numberKeys.length; ++i){
             numberKeys[i] = Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i);
             if(numberKeys[i]){
                 keyPressed = true;
             }
         }

         if(keyPressed){
             level.updateMerchantNumberKeys(numberKeys);
         }

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
     * @param left indicates that the key to go left is pressed.
     * @param right indicates that the key to go right is pressed.
     * @return 1 for right, -1 for left and 0 for no movement wanted.
     */
    private int dirCalc(boolean left, boolean right){
        if(left == right){
            return 0;
        }

        return right ? 1 : -1;
    }
}
