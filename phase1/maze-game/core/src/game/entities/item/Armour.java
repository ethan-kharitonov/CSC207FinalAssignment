package game.entities.item;

import geometry.Circle;
import geometry.Point;

import static config.GameConstants.ARMOUR_RADIUS;

public class Armour extends Item{
    private float armourPoint;
    private int shield;
    public Armour(float x, float y, int value, float armourPoint, String name, int shield) {
        super(x, y, value, name);
        //armour point reduce the damage taken by a percentage
        this.armourPoint = Math.min(100, armourPoint);
        //shield take certain amount of damage
        this.shield = shield;
    }
    public Armour(Point pos){
        super(pos);
    }

    @Override
    public void draw() {

    }

    public Armour(Point pos, int value, float armourPoint, String name, int shield) {
        super(pos, value, name);
        //armour point reduce the damage taken by a percentage
        this.armourPoint = Math.min(100, armourPoint);
        //shield take certain amount of damage
        this.shield = shield;
    }

    public Armour(float x, float y, int value, float armourPoint, int shield, String name) {
        super(x, y, value, name);
        //armour point reduce the damage taken by a percentage
        this.armourPoint = Math.min(99, armourPoint);
        //shield take certain amount of damage
        this.shield = shield;
    }

    @Override
    public Circle getCollisionBox() {
        return new Circle(pos, ARMOUR_RADIUS);
    }
    public int getShield(){return shield;}
    public float getArmourPoint(){return armourPoint;}
}
