
import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.item.*;
import game.entities.characters.Merchant;
import geometry.Point;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
public class CharactersTest {
    @Test
    public void MerchantTransactionTest(){
        ArrayList<Item> itemOwned = new ArrayList<>();
        itemOwned.add(new Armour((float)0.1, "Very expensive armour", 1000));
        itemOwned.add(new Armour((float)0.2, "cheap armour", 13));
        itemOwned.add(new HealthFlask());
        Merchant merchant = new Merchant(0,0, itemOwned, null);
        Player player = new Player(new Point(0,0), null);
        merchant.collideWith(player);
        boolean[] lst = new boolean[3];
        lst[1] = true;
        lst[2] = true;
        merchant.updateNumberKeysPressed(lst);
        assertEquals((float)0.2,player.getArmourDamageFactor(),0.01);
        assertEquals(77, player.getGoldOwned());
    }

    @Test
    public void MerchantAwayFromPlayerTest(){
        ArrayList<Item> itemOwned = new ArrayList<>();
        itemOwned.add(new Armour((float)0.1, "Very expensive armour", 10));
        Merchant merchant = new Merchant(0,0, itemOwned, null);
        Player player = new Player(new Point(0,0), null);
        merchant.collideWith(player);
        player.pos = new Point(100,100);
        merchant.update();
        boolean[] lst = new boolean[1];
        lst[0]=true;
        merchant.updateNumberKeysPressed(lst);
        assertEquals(1.0, player.getArmourDamageFactor(),0.01);

    }
    @Test(timeout=50)
    public void EnemyBeingDamagedTest(){

        Enemy e = new Enemy(0,0,null);
        assertEquals(1, e.getDamage());
        Player p = new Player(new Point(0,0), null);
        p.collideWith(e);
        assertEquals(99, p.getHealth());
        Bullet bullet = new Bullet(new Point(0,0), new Point(1,0), null);
        assertFalse(e.shouldBeRemoved());
        for(int i = 0; i<7; i++) e.collideWith(bullet);
        assertTrue(e.shouldBeRemoved());
    }

    @Test
    public void PlayerCollisionBoxTest(){
        Player player = new Player(new Point(0,0), null);
        assertEquals(0, (int)player.getCollisionBox().getCenter().getX());
        assertEquals(0, (int)player.getCollisionBox().getCenter().getY());
    }

    @Test(timeout=50)
    public void PlayerBeingDamagedTest(){
        Player player = new Player(new Point(0,0), null);
        player.setArmour((float)0.2);
        Enemy e = new Enemy(0,0, null);
        player.collideWith(e);
        assertEquals(99, player.getHealth());
        assertEquals(0, (int)player.getCollisionBox().getCenter().getX());
        assertEquals(0, (int)player.getCollisionBox().getCenter().getY());
    }
    @Test(timeout=50)
    public void PlayerBeingDamagedTest2(){
        Player player = new Player(new Point(0,0), null);
        Mine m = new Mine(new Point(0,0), null, null);
        player.setArmour((float)1.0);
        player.collideWith(m);
        assertEquals(75, player.getHealth());
        player.setArmour((float)0.8);
        player.collideWith(m);
        assertEquals(55, player.getHealth());
        player.setArmour((float)0.2);
        player.collideWith(m);
        assertEquals(50, player.getHealth());
        player.setArmour((float)0.0);
        player.collideWith(m);
        assertEquals(50, player.getHealth());
    }
    @Test(timeout=50)
    public void PlayerBeingHealedTest(){
        Player player = new Player(new Point(0,0), null);
        Mine m = new Mine(new Point(0,0), null, null);
        player.collideWith(m);
        HealthFlask flask = new HealthFlask();
        flask.operateOnPlayer(player);
        assertEquals(100, player.getHealth());
        player.collideWith(m);
        player.collideWith(m);
        player.collideWith(m);
        flask = new HealthFlask();
        flask.operateOnPlayer(player);
        assertEquals(75, player.getHealth());
    }
    @Test(timeout=50)
    public void PlayerBeingHealedTest2(){
        Player player = new Player(new Point(0,0), null);
        Enemy e = new Enemy(0,0, null);
        e.setDamage(90);
        player.collideWith(e);
        assertEquals(10, player.getHealth());
        HealthFlask flask = new HealthFlask();
        flask.operateOnPlayer(player);
        assertEquals(60, player.getHealth());
    }

}
