package game.entities.item;

import geometry.Circle;

public class HealthFlask extends Item{
    public HealthFlask(float x, float y){
        super(x,y,8, "Health Flask");
    }

    @Override
    public void draw() {

    }

    @Override
    public Circle getCollisionBox() {
        return null;
    }
}
