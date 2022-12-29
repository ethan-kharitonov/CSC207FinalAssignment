package graphics.entityDrawers.mine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConstants;
import geometry.Point;

public class SimpleShapeMineDrawer implements IMineDrawer{
    private ShapeRenderer shapeRenderer;

    private boolean isLightOn = true;
    private long lastChangeFlash;

    public SimpleShapeMineDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
        lastChangeFlash = System.currentTimeMillis();
    }
    @Override
    public void drawMine(Point pos) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(pos.getX(), pos.getY(), GameConstants.MINE_RADIUS);

        if(System.currentTimeMillis() - lastChangeFlash > GameConstants.MINE_FLASHING_PERIOD_MS){
            lastChangeFlash = System.currentTimeMillis();
            isLightOn = !isLightOn;
        }

        if(isLightOn){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(pos.getX(), pos.getY(), GameConstants.MINE_LIGHT_RADIUS);
        }

    }
}
