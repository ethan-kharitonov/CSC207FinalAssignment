package graphics.game.entities.drawers.Mine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import geometry.Point;

public class SimpleShapeMineDrawer implements IMineDrawer{
    private ShapeRenderer shapeRenderer;
    private int radius = 10;

    private boolean isLightOn = true;
    private final float flashFrequencyMillis = 250;
    private long lastChangeFlash;

    public SimpleShapeMineDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
        lastChangeFlash = System.currentTimeMillis();
    }
    @Override
    public void drawMine(Point pos) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(pos.getX(), pos.getY(), radius);

        if(System.currentTimeMillis() - lastChangeFlash > flashFrequencyMillis){
            lastChangeFlash = System.currentTimeMillis();
            isLightOn = !isLightOn;
        }

        if(isLightOn){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(pos.getX(), pos.getY(), 2);
        }

    }
}
