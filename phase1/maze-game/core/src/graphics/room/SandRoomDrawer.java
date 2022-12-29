package graphics.room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SandRoomDrawer implements IRoomDrawer {
    private int screenWidth;
    private int screenHeight;
    Stage stage;
    SpriteBatch spriteBatch;
    TextureRegion textureRegion;
    public SandRoomDrawer(Stage stage, int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.stage = stage;

        Texture texture = new Texture(Gdx.files.internal("chiseled_sandstone.png"));
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0, 0, screenWidth, screenHeight);
    }
    @Override
    public void drawRoom() {
        stage.getBatch().begin();
        stage.getBatch().draw(textureRegion, 0, 0);
        stage.getBatch().end();
    }
}
