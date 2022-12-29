
import com.badlogic.gdx.utils.TimeUtils;
import game.entities.abstractions.IRoomContainer;
import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.item.*;
import game.entities.characters.Merchant;
import game.entities.rooms.Level;
import geometry.Point;
import graphics.game.entities.drawers.player.CirclePlayerDrawer;
import graphics.healthbar.HealthBarDrawer;
import graphics.presenters.IDrawerFactory;
import graphics.presenters.IUIPresenter;
import graphics.presenters.ShapeDrawerFactory;
import graphics.presenters.UIPresenter;
import manager.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ItemTest {
    @Test(timeout=50)
    public void ArmourTest(){
        EntityManager manager = new EntityManager();
        ExampleArmour armour = new ExampleArmour(1,1);
        Point q1 = new Point(0,0);
        Point q2 = new Point(1,0);
        Enemy e1 = new Enemy(q1, null);
        e1.setDamage(100);
        Player p2 = new Player(q2, null);
        p2.setArmour(armour);
        assertEquals(100, p2.getHealth());
        assertEquals(30, p2.getShield());
        assertEquals(10, (int)armour.getArmourPoint());
        p2.collideWith(e1);
        assertEquals(10, p2.getHealth());
        assertEquals(20,p2.getShield());
    }

    @Test(timeout=50)
    public void BulletTest() {
        Bullet bullet = new Bullet(new Point(0, 0), new Point(1, 0), null);
        bullet.update();
        assertEquals(30.0, bullet.getCollisionBox().getCenter().getX(), 0.01);
        assertEquals(0.0, bullet.getCollisionBox().getCenter().getY(), 0.01);
        Point q1 = new Point(0,0);
        Enemy e1 = new Enemy(q1, null);
        bullet.collideWith(e1);
        e1.collideWith(bullet);
        assertTrue(bullet.shouldBeRemoved());
        assertEquals(85, e1.getHealth());
    }

    @Test(timeout=50)
    public void DoorTest(){
        Door door = new Door(new Point(0,0), null, null);
        assertEquals(0, (int)door.getCollisionBox().getCenter().getX());
        assertEquals(0, (int)door.getCollisionBox().getCenter().getY());
    }
    @Test
    public void GunTest(){
        Gun gun = new Gun(new Point(0,0), null, null);
        EntityManager m = new EntityManager();
        gun.setEntityManager(m);
        gun.fire(new Point(2,2));
        assertTrue(m.getEntities().isEmpty());
        gun.fire(new Point(2,2));
        m.update();
        assertFalse(m.getEntities().isEmpty());
        gun.setPlayerPosition(new Point(5,5));
        assertEquals((int)gun.getCollisionBox().getCenter().getX(),5);
        assertEquals((int)gun.getCollisionBox().getCenter().getY(),5);


    }

    @Test(timeout=50)
    public void HealthFlaskTest(){
        HealthFlask flask = new HealthFlask(0,0);
        HealthFlask flask2 = new HealthFlask(0,0);
        Player p = new Player(new Point(0,0),null);
        p.addItem(flask);
        p.addItem(flask);
        Enemy enemy = new Enemy(new Point(0,0),null);
        enemy.setDamage(50);
        p.collideWith(enemy);
        assertEquals(50, p.getHealth());
        p.restoreHealth();
        assertEquals(80, p.getHealth());
        p.restoreHealth();
        assertEquals(100, p.getHealth());
    }
    @Test(timeout=50)
    public void WeaponTest(){
        Weapon weapon = new Weapon(1,1,10,"weapon", 10);
        assertEquals(10, weapon.getValue());
        assertEquals(10, weapon.getDamage());
        assertEquals(1, (int)weapon.getCollisionBox().getCenter().getX());
    }

}
