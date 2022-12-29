package game.entities.item;

import config.GameConstants;
import game.entities.abstractions.ICollidable;
import geometry.Circle;
import geometry.Point;


//public class Weapon extends Item implements ICollidable {
//    private int damage;
//    public Weapon(float x, float y, int value, String name, int damage) {
//        super(x, y, value, name);
//        this.damage = damage;
//    }
//    public Weapon(Point pos){
//        super(pos);
//    }
//    @Override
//    public void draw() {
//
//    }
//
//    @Override
//    public Circle getCollisionBox() {
//        return new Circle(pos, GameConstants.GUN_RADIUS);
//    }
//
//    @Override
//    public void informCollision(ICollidable other) {
//        other.informCollision(this);
//    }
//
//    public int getDamage(){
//        return damage;
//    }
//}
