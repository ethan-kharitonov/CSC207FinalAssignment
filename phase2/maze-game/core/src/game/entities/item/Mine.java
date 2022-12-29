package game.entities.item;

import config.GameConstants;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.Entity;
import game.entities.abstractions.ICollidable;
import game.entities.characters.Player;
import geometry.Circle;
import geometry.Point;
import graphics.entityDrawers.mine.IMineDrawer;

public class Mine extends CollidableEntity {

    private IMineDrawer mineDrawer;
    private static float mineRadius = 10;
    private Entity creator;
    private boolean hasDetonated = false;

    public Mine(Point pos, IMineDrawer mineDrawer, Entity creator) {
        super(pos);
        this.mineDrawer = mineDrawer;
        this.creator = creator;
    }
    public Mine(int x, int y, IMineDrawer mineDrawer, Entity creator) {
        super(x, y);
        this.mineDrawer = mineDrawer;
        this.creator = creator;
    }

    @Override
    public Circle getCollisionBox() {
        return new Circle(pos, mineRadius);
    }

    @Override
    public void informCollision(ICollidable other){
        other.collideWith(this);
    }

    @Override
    public void collideWith(Player player){
        hasDetonated = true;
    }

    @Override
    public boolean shouldBeRemoved(){
        return hasDetonated;
    }

    public static float getMineRadius(){
        return mineRadius;
    }

    @Override
    public void draw(){
        mineDrawer.drawMine(pos);
    }

    @Override
    public void update(){
        if(creator.shouldBeRemoved()){
            hasDetonated = true;
        }
    }

    public int getDamage() { return GameConstants.MINE_DAMAGE; }

}
