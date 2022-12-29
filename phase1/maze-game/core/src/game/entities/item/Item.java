package game.entities.item;

import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import geometry.Point;

public abstract class Item extends CollidableEntity {
    //Item is an object that can be picked up by the player, and has a value. This is not a class we wrote in phase 1
    //An item has a value, a name, and can be picked up or dropped.

    private int value;
    private String name;
    public Item(float x, float y, int value, String name) {
        super(x, y);
        this.value = value;
        this.name = name;
    }
    public Item(Point pos, int value, String name){
        super(pos);
        this.value = value;
        this.name = name;
    }
    public Item(Point pos){
        super(pos);
    }

    public Item(float x, float y) {
        super(x,y);
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString(){
        return name;
    }
    @Override
    public void informCollision(ICollidable other) {
        other.collideWith(this);
    }

}
