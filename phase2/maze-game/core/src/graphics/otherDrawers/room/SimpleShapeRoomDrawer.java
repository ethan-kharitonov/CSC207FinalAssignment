package graphics.otherDrawers.room;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SimpleShapeRoomDrawer implements IRoomDrawer{

    private ShapeRenderer shapeRenderer;
    private int screenWidth;
    private int screenHeight;
    public SimpleShapeRoomDrawer(ShapeRenderer shapeRenderer, int screenWidth, int screenHeight){
        this.shapeRenderer = shapeRenderer;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    @Override
    public void drawRoom() {
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(0, 0, screenWidth, screenHeight);
    }

}
