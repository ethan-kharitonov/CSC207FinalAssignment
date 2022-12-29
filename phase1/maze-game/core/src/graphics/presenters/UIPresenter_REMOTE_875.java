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

public class UIPresenter implements IUIPresenter {

	//Game state which effects the UI
    private boolean isPlayerDead = false;
	private int playerShield;
	private int playerHealth;
	private int playerGold;


	//Variables used to draw
    private final SpriteBatch spriteBatch;
	private final Stage stage;
	private ShapeRenderer shapeRenderer;
	private final BitmapFont font;
	private final OrthographicCamera camera;

	private final IHealthBarDrawer healthBarDrawer;
    public UIPresenter(){
        spriteBatch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT	);

		shapeRenderer = new ShapeRenderer();

		healthBarDrawer = new HealthBarDrawer(shapeRenderer);
    }

    @Override
    public void draw() {
		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

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

	@Override
	public void updatePlayerGold(int playerGold) {
		this.playerGold = playerGold;
	}

	public void updatePlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public void dispose(){
		font.dispose();
		spriteBatch.dispose();
		stage.dispose();
	}
}
