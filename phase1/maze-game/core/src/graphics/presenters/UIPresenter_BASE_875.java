package graphics.presenters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	private ShapeRenderer shapeRenderer;
	private final BitmapFont font;
	private final OrthographicCamera camera;

	private final IHealthBarDrawer healthBarDrawer;
    public UIPresenter(){
        spriteBatch = new SpriteBatch();
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

		shapeRenderer.end();
		spriteBatch.end();
    }

	private void drawNoOverhead(){
		if(isPlayerDead){
			font.setColor(1, 0, 0, 1);
			font.draw(spriteBatch, "YOU DIED", GameConstants.SCREEN_WIDTH / 2f, GameConstants.SCREEN_HEIGHT / 2f);
			return;
		}

		font.draw(spriteBatch, "Shield: " + playerShield,  10, 50);
		font.draw(spriteBatch, "Gold: " + playerGold,  900, 20);

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
	}
}
