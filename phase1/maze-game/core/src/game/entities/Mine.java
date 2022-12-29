package game.entities;

import com.badlogic.gdx.math.MathUtils;
import config.GameConstants;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import geometry.Circle;
import geometry.Point;

public class Mine extends CollidableEntity {

    private static float mineRadius = 10;

    public Mine(Point pos) {
        super(pos);
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
    public void update(){

    }

    public static float getMineRadius(){
        return mineRadius;
    }
}
