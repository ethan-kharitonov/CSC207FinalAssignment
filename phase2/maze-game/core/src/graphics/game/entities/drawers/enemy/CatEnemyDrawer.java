package graphics.game.entities.drawers.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import geometry.Point;

import javax.swing.*;

public class CatEnemyDrawer implements IEnemyDrawer {
    Stage stage;
    public CatEnemyDrawer(Stage stage){
        this.stage = stage;
    }
    @Override
    public void drawEnemy(Point pos) {
        Texture texture = new Texture(Gdx.files.internal("cat.png"));
        Image image = new Image(texture);
        image.setPosition(pos.getX()-16, pos.getY()-16);
        stage.addActor(image);
    }
}
