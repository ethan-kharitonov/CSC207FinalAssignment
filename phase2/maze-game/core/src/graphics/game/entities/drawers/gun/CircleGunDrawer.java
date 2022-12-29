package graphics.game.entities.drawers.gun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConstants;
import geometry.Point;

public class CircleGunDrawer implements IGunDrawer{

    private final ShapeRenderer shapeRenderer;
    public CircleGunDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
    }
    @Override
    public void drawGun(Point pos) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(pos.getX(), pos.getY(), GameConstants.GUN_RADIUS);
    }
}
