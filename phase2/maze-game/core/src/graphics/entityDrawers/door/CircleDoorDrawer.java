package graphics.entityDrawers.door;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConstants;
import geometry.Point;

import java.util.Random;

public class CircleDoorDrawer implements IDoorDrawer {
    private ShapeRenderer shapeRenderer;
    private Color color;

    public CircleDoorDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;

        Random rand = new Random();
        color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(), 1);
    }

    @Override
    public void drawDoor(Point pos) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(pos.getX(), pos.getY(), GameConstants.DOOR_RADIUS);
    }
}
