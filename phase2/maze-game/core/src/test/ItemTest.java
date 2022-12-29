import game.entities.characters.Enemy;
import game.entities.item.*;
import geometry.Point;
import manager.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;

public class ItemTest {

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
        assertEquals((int)gun.pos.getX(),5);
        assertEquals((int)gun.pos.getY(),5);
    }

}
