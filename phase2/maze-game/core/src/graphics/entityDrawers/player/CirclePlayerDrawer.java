package graphics.entityDrawers.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConstants;
import geometry.Point;

public class CirclePlayerDrawer implements IPlayerDrawer{

    private ShapeRenderer shapeRenderer;
    public CirclePlayerDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
    }
    @Override
    public void drawPlayer(Point pos, Point gunDirection) {
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle(pos.getX(), pos.getY(), GameConstants.PLAYER_RADIUS);
    }

}
