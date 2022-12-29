import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.item.Bullet;
import geometry.Point;
import graphics.entityDrawers.player.CirclePlayerDrawer;
import graphics.otherDrawers.healthbar.HealthBarDrawer;
import manager.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;

public class ManagerTest {
    @Test(timeout=50)
    public void EntityManagerAddEntityTest(){
        CirclePlayerDrawer draw = new CirclePlayerDrawer(null);
        HealthBarDrawer health = new HealthBarDrawer(null);
        Point pos = new Point(1,1);
        Player player = new Player(pos, draw);
        EntityManager entityManager = new EntityManager();
        entityManager.addCollidableEntity(player);
        entityManager.update();
        assertTrue(entityManager.getEntities().contains(player));
    }

    @Test(timeout=50)
    public void EntityManagerRemoveEntityTest(){
        Point pos = new Point(1,1);
        Player player = new Player(pos, null);
        EntityManager entityManager = new EntityManager();
        entityManager.addCollidableEntity(player);
        entityManager.update();
        entityManager.update();
        assertTrue(entityManager.getEntities().contains(player));
    }

    @Test(timeout=50)
    public void EntityManagerRemoveEntityTest2() {
        EntityManager manager = new EntityManager();
        Bullet bullet = new Bullet(new Point(0, 0), new Point(1, 0), null);
        bullet.update();
        manager.addCollidableEntity(bullet);
        Point q1 = new Point(0, 0);
        Enemy e1 = new Enemy(q1, null);
        bullet.collideWith(e1);
        e1.collideWith(bullet);
        assertTrue(bullet.shouldBeRemoved());
        manager.update();
        manager.update();
        assertFalse(manager.getEntities().contains(bullet));
    }
    @Test(timeout=50)
    public void EntityManagerIsFinishedTest(){
        EntityManager manager = new EntityManager();
        Point q1 = new Point(0,0);
        Enemy e1 = new Enemy(q1, null);
        manager.addCollidableEntity(e1);
        manager.update();
        assertFalse(manager.isFinished());
    }

    @Test(timeout=50)
    public void EntityManagerIsFinishedTest2(){
        EntityManager manager = new EntityManager();
        manager.update();
        assertTrue(manager.isFinished());
    }

}
