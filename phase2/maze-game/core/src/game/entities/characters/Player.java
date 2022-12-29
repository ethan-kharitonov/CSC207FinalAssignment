package game.entities.characters;

import com.badlogic.gdx.Gdx;
import config.GameConstants;
import game.entities.item.Mine;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import game.entities.abstractions.IPlayerObserver;
import game.entities.item.Gun;
import manager.IEntityManager;
import geometry.Circle;
import geometry.Point;
import graphics.entityDrawers.player.IPlayerDrawer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the player
 * @author Ian
 * @author Ethan
 * @author Daniel.
 */
public class Player extends CollidableEntity {

    public static final int MAX_HEALTH = 100;
    private int health = MAX_HEALTH;

    public static final int MAX_SHIELD = 50;
    private int shield = 0;
    private final IPlayerDrawer playerDrawer;
    private Collection<IPlayerObserver> observers = new ArrayList<>();
    private Point gunDirection = new Point(0,0);
    private Gun gun;
    private float armourDamageFactor = 1;

    private int goldOwned = 100;
//    public ArrayList<Item> itemOwned = new ArrayList<>(Collections.singletonList(gun));

    /**
     * Create a player
     * @param pos The position of player
     * @param playerDrawer The drawer of player
     */
    public Player(Point pos, IPlayerDrawer playerDrawer){
        super(pos);
        this.playerDrawer = playerDrawer;
    }

    /**
     * Set up a gun manager for player
     * @param entityManager The manager of the player's guns
     */
    public void setGunEntityManager(IEntityManager entityManager){
        gun.setEntityManager(entityManager);
    }

    /**
     * Set player's attack direction
     * @param direction The direction of player
     */
    public void fire(Point direction){
        gun.fire(direction);
    }

    /**
     * Set player's move direction
     * @param direction The direction of player
     */
    public void move(Point direction){
        //move in the given direction
        direction.multiply(GameConstants.PLAYER_SPEED * Gdx.graphics.getDeltaTime());
        pos.add(direction);

        //keep player on the screen
        pos.setX(Math.max(Math.min(pos.getX(), GameConstants.SCREEN_WIDTH), 0));
        pos.setY(Math.max(Math.min(pos.getY(), GameConstants.SCREEN_HEIGHT), 0));

        for(IPlayerObserver observer: observers){
            observer.setTarget(pos);
        }
        gun.setPlayerPosition(calcGunPos());
    }

    private Point calcGunPos(){
        return new Point(pos.getX() + gunDirection.getX() * GameConstants.PLAYER_RADIUS, pos.getY() + gunDirection.getY() * GameConstants.PLAYER_RADIUS);
    }

    /**
     * Set the mouse position for player
     * @param mousePos the mouse position.
     */

    public void setMousePos(Point mousePos){
        gunDirection = mousePos.distanceVector(pos);
        if(!gunDirection.isZero()){
            gunDirection = gunDirection.normalized();
        }
    }

    /**
     * Set the gun that is hold by player
     * @param gun the gun hold by player
     */
    public void setGun(Gun gun){
        this.gun = gun;
    }
    public float getArmourDamageFactor(){
        return armourDamageFactor;
    }
    public void setArmour(float armourDamageFactor){
        this.armourDamageFactor = armourDamageFactor;
    }

    /**
     * Draw the player.
     */
    public void draw(){
        playerDrawer.drawPlayer(pos, gunDirection);
        gun.draw();
    }

    /**
     * Get the collision box
     * @return the circle representation of the collision box.
     */
    public Circle getCollisionBox(){
        return new Circle(pos, GameConstants.PLAYER_RADIUS);
    }

    /**
     * Collide with another enemy.
     * @param enemy the enemy collided with player
     */
    public void collideWith(Enemy enemy) {
        takeDamage(enemy.getDamage());
    }

    @Override
    public void collideWith(Mine mine){
        shield = 0;
        takeDamage(mine.getDamage());
    }

    private void takeDamage(int damage){
        float totalHealth = (health + shield) - damage * armourDamageFactor;
        health = Math.round(Math.max(0, Math.min(totalHealth, MAX_HEALTH)));
        shield = Math.round(Math.max(0, Math.min(totalHealth - health, MAX_SHIELD)));
    }
    /**
     * Inform others being collided by player.
     * @param other the object collided with the player
     */
    @Override
    public void informCollision(ICollidable other) {
        other.collideWith(this);
    }

    public boolean tryToPay(int priceInGold){
        if(priceInGold > goldOwned){
            return false;
        }

        goldOwned -= priceInGold;
        return true;
    }

    public void addObserver(IPlayerObserver observer){
        observers.add(observer);
    }


    public int getHealth(){
        return health;
    }
    public int getShield() { return shield; }
    public int getGoldOwned(){return goldOwned;}

    public void addHealth(int healthToAdd){
        health += healthToAdd;
        health = Math.min(health, MAX_HEALTH);
    }
    public void addGold(int gold){this.goldOwned += gold;}

    public void addShield(int shieldToAdd){
        this.shield += shieldToAdd;
    }

}
