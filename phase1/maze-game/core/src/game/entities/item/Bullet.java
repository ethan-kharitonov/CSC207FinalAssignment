package game.entities.item;

import config.GameConstants;
import game.entities.abstractions.ICollidable;
import game.entities.characters.Enemy;
import geometry.Circle;
import geometry.Point;
import graphics.game.entities.drawers.bullet.IBulletDrawer;
/** Represents a bullet.
 * @author Daniel Saragih
 * @author Ian Curtis Ewing
 */
public class Bullet extends Weapon {

    private Point velocity;
    private boolean isHit = false;
    private int damage;

    private IBulletDrawer bulletDrawer;

    /** Creates a bullet with the specified name.
     * @param pos Position of the bullet.
     * @param direction Direction vector of the bullet.
     * @param bulletDrawer Drawer of the bullet.
     */
    public Bullet(Point pos, Point direction, IBulletDrawer bulletDrawer) {
        super(pos);
        direction.multiply(GameConstants.BULLET_SPEED);
        this.velocity = direction;
        this.bulletDrawer = bulletDrawer;
        this.damage = GameConstants.BULLET_DAMAGE;
    }

    /** Draw the bullet
     */
    @Override
    public void draw() {
        bulletDrawer.drawBullet(pos);
    }

    /** Get the Collision Box
     * @return a circle representation of the collision box.
     */
    @Override
    public Circle getCollisionBox() {
        return new Circle(this.pos, GameConstants.BULLET_RADIUS);
    }

    /** Collide with enemy player
     * @param enemy The enemy being hit by the bullet.
     */
    public void collideWith(Enemy enemy) {
        isHit = true;
    }

    /** Inform the object that it's hit by the bullet
     * @param other the object being hit by this bullet
     */
    @Override
    public void informCollision(ICollidable other) {
        other.collideWith(this);
    }

    /** Update the position of bullet
     */
    public void update() {
        pos.add(velocity);
    }

    /** Get the bullet damage
     * @return The damage of the bullet
     */

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Return true if it hits another object, false otherwise.
     * @return whether the bullet hits another abject
     */
    @Override
    public boolean shouldBeRemoved() {
        return isHit;
    }
}
