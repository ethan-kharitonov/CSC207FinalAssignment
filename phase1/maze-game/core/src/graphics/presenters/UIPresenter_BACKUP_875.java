package graphics.presenters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import config.GameConstants;
import graphics.healthbar.HealthBarDrawer;
import graphics.healthbar.IHealthBarDrawer;

import java.util.Collection;

public class UIPresenter implements IUIPresenter {

    //Game state which effects the UI
    private boolean isPlayerDead = false;
    private int playerShield;
    private int playerHealth;
    private int playerGold;
    private boolean playerWins = false;
    private Collection<IDrawble> drawbles;


    //Variables used to draw
    private final SpriteBatch spriteBatch;
<<<<<<< HEAD
    private ShapeRenderer shapeRenderer;
    private final BitmapFont font;
    private final OrthographicCamera camera;
=======
	private final Stage stage;
	private ShapeRenderer shapeRenderer;
	private final BitmapFont font;
	private final OrthographicCamera camera;
>>>>>>> 40f5f3303a3d749bf257afa31b6e4abb3dbc4441

    private final IHealthBarDrawer healthBarDrawer;

    public UIPresenter() {
        spriteBatch = new SpriteBatch();
<<<<<<< HEAD
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
=======
		stage = new Stage(new ScreenViewport());
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT	);
>>>>>>> 40f5f3303a3d749bf257afa31b6e4abb3dbc4441

        shapeRenderer = new ShapeRenderer();

        healthBarDrawer = new HealthBarDrawer(shapeRenderer);
    }

    @Override
    public void draw() {
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

<<<<<<< HEAD
        drawNoOverhead();

        shapeRenderer.end();
        spriteBatch.end();
    }

    private void drawNoOverhead() {
        for (IDrawble drawble : drawbles){
            drawble.draw();
        }

            if (isPlayerDead) {
                font.setColor(1, 0, 0, 1);
                font.draw(spriteBatch, "YOU DIED", GameConstants.SCREEN_WIDTH / 2f, GameConstants.SCREEN_HEIGHT / 2f);
                return;
            }
=======
		drawNoOverhead();
		stage.act();
		stage.draw();

		shapeRenderer.end();
		spriteBatch.end();
		stage.clear();
    }

	private void drawNoOverhead(){
		Label.LabelStyle style = new Label.LabelStyle();
		style.font = font;
		style.fontColor = Color.RED;

		if(isPlayerDead){
			Label label1 = new Label("YOU DIED", style);
			label1.setPosition(0, GameConstants.SCREEN_HEIGHT / 2f);
			label1.setAlignment(Align.center);
			stage.addActor(label1);
			return;
		}
		Label shieldLabel = new Label("Shield: " + playerShield, style);
		shieldLabel.setPosition(10, 50);
		stage.addActor(shieldLabel);

		Label goldLabel = new Label("Gold: " + playerGold, style);
		goldLabel.setPosition(900, 20);
		stage.addActor(goldLabel);
>>>>>>> 40f5f3303a3d749bf257afa31b6e4abb3dbc4441

        if (playerWins) {
            font.setColor(0, 1, 0, 1);
            font.draw(spriteBatch, "YOU WIN", GameConstants.SCREEN_WIDTH / 2f, GameConstants.SCREEN_HEIGHT / 2f);
            return;
        }

        font.draw(spriteBatch, "Shield: " + playerShield, 10, 50);
        font.draw(spriteBatch, "Gold: " + playerGold, 900, 20);

        healthBarDrawer.drawHealthBar(playerHealth);
    }

    @Override
    public void updatePlayerShield(int playerShield) {
        this.playerShield = playerShield;
    }

    @Override
    public void updateIsPlayerDead(boolean isPlayerDead) {
        this.isPlayerDead = isPlayerDead;
    }

<<<<<<< HEAD
    @Override
    public void updatePlayerGold(int playerGold) {
        this.playerGold = playerGold;
    }

    public void updatePlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    @Override
    public void playerWins() {
        playerWins = true;
    }

    public void setDrawbles(Collection<IDrawble> drawbles) {
        this.drawbles = drawbles;
    }

    public void dispose() {
        font.dispose();
        spriteBatch.dispose();
    }
=======
	public void dispose(){
		font.dispose();
		spriteBatch.dispose();
		stage.dispose();
	}
>>>>>>> 40f5f3303a3d749bf257afa31b6e4abb3dbc4441
}
