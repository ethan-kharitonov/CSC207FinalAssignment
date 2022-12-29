package game.entities.abstractions;

import game.entities.Mine;
import game.entities.characters.Enemy;
import game.entities.characters.MineDropperEnemy;
import game.entities.characters.Player;
import game.entities.item.Bullet;
import game.entities.item.Door;
import game.entities.item.Item;
import game.entities.characters.Merchant;
import geometry.Circle;

/**
 * Represents the collidable interface
 * @author Ethan
 * @author Daniel
 */
public interface ICollidable {

    /**
     * Get the collision box.
     * @return the circle representation of collision box
     */
    Circle getCollisionBox();

    /**
     * Collide with player
     * @param player the player being collided.
     */
    void collideWith(Player player);

    /**
     * Collide with enemy
     * @param enemy the enemy being collided.
     */
    void collideWith(Enemy enemy);

    /**
     * Collide with door
     * @param door the door being collided.
     */
    void collideWith(Door door);

    /**
     * Collide with bullet
     * @param bullet the bullet being collided.
     */
    void collideWith(Bullet bullet);

    void collideWith(Item item);

    /**
     * Inform the ICollidable objects being collided
     * @param other object being collided
     */
    void informCollision(ICollidable other);

    void collideWith(Merchant merchant);

    void collideWith(MineDropperEnemy mineDropperEnemy);

    void collideWith(Mine mine);
}
