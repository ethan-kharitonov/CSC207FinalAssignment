package graphics.entityDrawers.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import config.GameConstants;
import geometry.Point;

public class SquareEnemyDrawer implements IEnemyDrawer{

    private ShapeRenderer shapeRenderer;
    public SquareEnemyDrawer(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
    }
    @Override
    public void drawEnemy(Point pos) {
        shapeRenderer.setColor(Color.NAVY);
        shapeRenderer.rect(pos.getX(), pos.getY(), GameConstants.SQUARE_ENEMY_SIZE, GameConstants.SQUARE_ENEMY_SIZE);
    }
}
