package game.entities.characters;
import com.badlogic.gdx.Gdx;
import config.GameConstants;
import game.entities.item.Bullet;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import game.entities.abstractions.IPlayerObserver;
import geometry.Circle;
import geometry.Point;
import graphics.entityDrawers.enemy.IEnemyDrawer;
import com.badlogic.gdx.math.MathUtils;

import static config.GameConstants.ENEMY_DAMAGE;

/** Represents an enemy
 * @author Ethan
 * @author Ian Curtis Ewing\
 * @author Daniel
 */
public class Enemy extends CollidableEntity implements IPlayerObserver {

    private Point velocity = new Point(0,0);
    private IEnemyDrawer enemyDrawer;
    private int health = 100;
    private Point target = null;
    private int damage = ENEMY_DAMAGE;

    private int value = MathUtils.random(1,3);

    /**
     * Create an enemy
     * @param x the x-coordinate of enemy
     * @param y the y-coordinate of enemy
     * @param enemyDrawer the drawer of enemy
     */
    public Enemy(int x, int y, IEnemyDrawer enemyDrawer) {
        super(x, y, true);
        this.enemyDrawer = enemyDrawer;
    }

    /**
     * Create an enemy
     * @param pos the position of the enemy
     * @param enemyDrawer the drawer of enemy
     */
    public Enemy(Point pos, IEnemyDrawer enemyDrawer) {
        super(pos,true);
        this.enemyDrawer = enemyDrawer;
    }

    public int getHealth(){
        return health;
    }
    /**
     * Return the collision box of the enemy
     * @return the circle representation of the collision box.
     */
    public Circle getCollisionBox(){
        return new Circle(pos, GameConstants.ENEMY_RADIUS);
    }

    /**
     * Inform the other objects being collided with.
     */
    @Override
    public void informCollision(ICollidable other) {
        other.collideWith(this);
    }

    @Override
    public void collideWith(Merchant merchant) {

    }
    /**
     * Collide with the player.
     * @param player player being collided
     */
    public void collideWith(Player player) {
        Point dir = player.pos.distanceVector(pos).normalized();
        dir.multiply(-1f);
        velocity.add(dir);
    }

    /**
     * Collide with the enemy
     * @param enemy the enemy being collided with the enemy
     */
    @Override
    public void collideWith(Enemy enemy) {
        Point dir = enemy.pos.distanceVector(pos).normalized();
        dir.multiply(-1f);
        velocity.add(dir);
    }

    /**
     * Collide with the bullet
     * @param bullet the bullet being collided with the enemy
     */
    @Override
    public void collideWith(Bullet bullet) {
        this.health -= bullet.getDamage();
    }

    /**
     * Draw the enemy
     */
    @Override
    public void draw() {
        enemyDrawer.drawEnemy(pos);
    }

    /**
     * Determine whether the enemy should be removed.
     * @return whether the health is non-positive.
     */
    @Override
    public boolean shouldBeRemoved() {
        return health <= 0;
    }

    /**
     * Update the movement of the enemy.
     */
    public void update() {
        if(target == null){
            return;
        }

        Point dirVector = target.distanceVector(pos).normalized();
        dirVector.multiply(GameConstants.ENEMY_ACCELERATION * Gdx.graphics.getDeltaTime());
        if(pos.getX()> GameConstants.SCREEN_WIDTH && velocity.getX() > 0){
            velocity.setX(0);
        }
        if(pos.getY() > GameConstants.SCREEN_HEIGHT && velocity.getY() > 0){
            velocity.setY(0);
        }
        if(pos.getX() < 0 && velocity.getX() < 0){
            velocity.setX(0);
        }
        if(pos.getY() < 0 && velocity.getY() <0){
            velocity.setY(0);
        }
        velocity.add(dirVector);
        velocity.multiply(1 - GameConstants.ENEMY_FRICTION);
        if(velocity.isZero()){
            return;
        }
        Point newDir = velocity.normalized();
        if(newDir.length() > GameConstants.ENEMY_MAX_SPEED){
            newDir.multiply(GameConstants.ENEMY_MAX_SPEED);
            velocity = newDir;
        }

        pos.add(velocity);

    }
    public void setDamage(int damage){
        this.damage = damage;
    }

    /**
     * Get the damage that the enemy can deal
     * @return the damage
     */
    public int getDamage(){
        return damage;
    }

    /**
     * Set the target point of the enemy
     * @param newTarget The target of enemy.
     */
    public void setTarget(Point newTarget){
        target = newTarget;
    }

    public int getValue(){return value;}
}
