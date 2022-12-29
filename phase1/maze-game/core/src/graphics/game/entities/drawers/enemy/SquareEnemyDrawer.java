package graphics.game.entities.drawers.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import geometry.Point;

public class SquareEnemyDrawer implements IEnemyDrawer{

    private int sideLength = 20;
    private ShapeRenderer shapeRenderer;
    public SquareEnemyDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
    }
    @Override
    public void drawEnemy(Point pos) {
        shapeRenderer.setColor(Color.NAVY);
        shapeRenderer.rect(pos.getX(), pos.getY(), sideLength, sideLength);
    }
}
