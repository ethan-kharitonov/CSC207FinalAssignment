package game.entities.item;

import geometry.Point;

public class ExampleArmour extends Armour{
    private float armourPoint;
    private int shield;
    private int value;
    private String name;
    public ExampleArmour(Point pos) {
        super(pos);
    }
    public ExampleArmour(float x, float y) {
        super(x,y,10,10,30,"ExampleArmour");
    }

}
