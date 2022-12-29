package game.entities.rooms;

import com.badlogic.gdx.math.MathUtils;

import game.entities.characters.MineDropperEnemy;
import game.entities.item.Door;
import game.entities.characters.Enemy;
import game.entities.characters.Merchant;
import game.entities.characters.Player;
import game.entities.item.ExampleArmour;
import game.entities.item.HealthFlask;
import game.entities.item.Item;
import geometry.Point;
import graphics.presenters.IDrawable;
import graphics.presenters.IDrawerFactory;
import manager.EntityManager;
import manager.IEntityManager;

import java.util.ArrayList;

/**
 * Represents a room
 */
public class Room implements IDrawable {
    private IDrawerFactory presenter;
    private IEntityManager entityManager = new EntityManager();

    public Room(IDrawerFactory presenter, Player player, int screenWidth, int screenHeight) {
        this.presenter = presenter;
        entityManager.addCollidableEntity(player);

        int numEnemies = MathUtils.random(1, 3);
        for (int i = 0; i < numEnemies; ++i) {
            Point enemyPos = new Point(MathUtils.random(0, screenWidth), MathUtils.random(0, screenHeight));
            Enemy enemy = new Enemy(enemyPos, presenter.getEnemyDrawer());
            player.addObserver(enemy);
            entityManager.addCollidableEntity(enemy);
            entityManager.addGold(enemy.getValue());
        }
        float padding = 0.25f;
        Point merchantPos = new Point(MathUtils.random(screenWidth * padding, screenWidth * (1 - padding)), MathUtils.random(screenHeight * padding, screenHeight * (1 - padding)));
        addMerchant(merchantPos);
        Point mineDropperPos = new Point(MathUtils.random(0, screenWidth), MathUtils.random(0, screenHeight));
        MineDropperEnemy mineDropper = new MineDropperEnemy(mineDropperPos, presenter.getMineDropperEnemyDrawer(), presenter.getMineDrawer());
        mineDropper.setEntityManager(entityManager);
        entityManager.addCollidableEntity(mineDropper);
        entityManager.addGold(mineDropper.getValue());

    }

    /**
     * Update the entity manager
     */
    public void update(){
        entityManager.update();
    }

    /**
     * Add a door
     * @param door a door
     */
    public void addDoor(Door door){
        entityManager.addCollidableEntity(door);
    }

    public void addMerchant(Point merchantPos) {
        ArrayList<Item> itemOwned = new ArrayList<>();
        ExampleArmour weakArmour = new ExampleArmour(0, 0);
        HealthFlask health = new HealthFlask(0, 0);
        itemOwned.add(health);
        itemOwned.add(weakArmour);
        Merchant merchant = new Merchant(merchantPos.getX(), merchantPos.getY(), itemOwned, presenter.getMerchantDrawer());
        entityManager.addCollidableEntity(merchant);
    }
    /**
     * Draw the room
     */
    public void draw(){
        presenter.getRoomDrawer().drawRoom();
        entityManager.draw();
    }

    public IEntityManager getEntityManager(){
        return entityManager;
    }

    public boolean allEnemiesKilled(){
        return entityManager.isFinished();
    }

}
