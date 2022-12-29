
import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.item.Bullet;
import game.entities.item.ExampleArmour;
import game.entities.characters.Merchant;
import game.entities.item.Gun;
import game.entities.item.Item;
import geometry.Point;
import graphics.game.entities.drawers.player.CirclePlayerDrawer;
import graphics.healthbar.HealthBarDrawer;
import manager.EntityManager;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
public class CharactersTest {
    @Test(timeout=50)
    public void MerchantTransactionTest(){
        ExampleArmour armour = new ExampleArmour(0,0);
        ArrayList<Item> items = new ArrayList<>();
        items.add(armour);
        Merchant merchant = new Merchant((float)0, (float)0,items, null);
        Player player = new Player(new Point(0,0), null);
        player.collideWith(merchant);
        player.buy(armour);
        player.setArmour(armour);
        assertEquals(player.getShield(), 30);
    }

    @Test(timeout=50)
    public void EnemyTest(){
        Enemy e = new Enemy(0,0,null);
        e.setDamage(10);
        assertEquals(10, e.getDamage());
        Player p = new Player(new Point(0,0), null);
        p.collideWith(e);
        assertEquals(90, p.getHealth());
        Bullet bullet = new Bullet(new Point(0,0), new Point(1,0), null);
        bullet.setDamage(100);
        e.collideWith(bullet);
        assertTrue(e.shouldBeRemoved());
    }


    @Test(timeout=50)
    public void PlayerTest(){
        Player player = new Player(new Point(0,0), null);
        assertEquals(100, player.getHealth());
        assertEquals(0, (int)player.getCollisionBox().getCenter().getX());
        assertEquals(0, (int)player.getCollisionBox().getCenter().getY());
        Merchant merchant = new Merchant(0,0,null, null);
        assertFalse(player.hasCollideWithMerchant());
        player.collideWith(merchant);
        assertTrue(player.hasCollideWithMerchant());
    }

}
