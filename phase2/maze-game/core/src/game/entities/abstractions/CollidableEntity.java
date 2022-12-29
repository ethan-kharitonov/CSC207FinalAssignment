package game.entities.abstractions;

import game.entities.item.Mine;
import game.entities.characters.Enemy;
import game.entities.characters.MineDropperEnemy;
import game.entities.characters.Player;
import game.entities.item.Bullet;
import game.entities.item.Door;
import game.entities.item.Item;
import game.entities.characters.Merchant;
import geometry.Circle;
import manager.IEntityManager;
import geometry.Point;

public abstract class CollidableEntity extends Entity implements ICollidable{

    /**
     * Create a collidable entity with the position
     * @param pos position of the entity
     */
    public CollidableEntity(Point pos) {
        super(pos);
    }

    /**
     * Create a collidable entity with coordinate (x,y)
     * @param x the horizontal coordinate of the entity
     * @param y the vertical coordinate of the entity
     */
    public CollidableEntity(float x, float y) {
        super(x, y);
    }
    public CollidableEntity(Point pos, boolean needToBeKilled){super(pos, needToBeKilled);}
    public CollidableEntity(float x, float y, boolean needToBeKilled){super(x,y,needToBeKilled);}

    @Override
    public void draw() {

    }

    abstract public Circle getCollisionBox();

    @Override
    public void collideWith(Player player) {

    }

    @Override
    public void collideWith(Enemy enemy) {

    }

    @Override
    public void collideWith(Door door) {

    }

    @Override
    public void collideWith(Bullet bullet) {

    }

    @Override
    public void collideWith(Item item) {

    }

    @Override
    public void informCollision(ICollidable other) {

    }

    @Override
    public void collideWith(Merchant merchant) {

    }

    @Override
    public void collideWith(MineDropperEnemy mineDropperEnemy){

    }

    @Override
    public void removeSelf(IEntityManager entityManager){
        entityManager.removeEntity(this);
    }

    @Override
    public void collideWith(Mine mine){

    }
}
