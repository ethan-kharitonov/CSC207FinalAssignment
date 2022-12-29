
import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.item.Bullet;
import game.entities.item.ExampleArmour;
import game.entities.characters.Merchant;
import game.entities.item.Item;
import game.entities.rooms.Room;
import geometry.Point;
import graphics.game.entities.drawers.player.CirclePlayerDrawer;
import graphics.healthbar.HealthBarDrawer;
import graphics.presenters.IDrawerFactory;
import graphics.presenters.ShapeDrawerFactory;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import config.GameConstants;
import manager.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
public class RoomTest{
    @Test(timeout = 50)
    public void testConstructor1(){
        Stage stage = new Stage(new ScreenViewport());
        IDrawerFactory presenter = new ShapeDrawerFactory(stage, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        Point pos = new Point(0,0);
        Player player = new Player(pos,presenter.getPlayerDrawer());
         Room room = new Room(presenter, player, GameConstants.SCREEN_WIDTH,  GameConstants.SCREEN_HEIGHT);
         //test that the room automatically generate an entitymanager
         assertTrue(room.getEntityManager() != null);
         assertTrue(room.allEnemiesKilled() == false);
    }
}
