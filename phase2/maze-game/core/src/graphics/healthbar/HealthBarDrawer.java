package graphics.healthbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBarDrawer implements IHealthBarDrawer{

    private final ShapeRenderer shapeRenderer;

    public HealthBarDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void drawHealthBar(int playerHealth) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(30, 15, playerHealth, 10);
    }
}
