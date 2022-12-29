package console;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import config.GameConstants;
import entities.Level;
import entities.Player;
import geometry.Point;
import graphics.IPresenter;
import graphics.ShapePresenter;
import manager.InputController;

/**
 * Represents a mazegame
 * @author Ethan
 * @author Daniel
 */
public class MazeGame extends ApplicationAdapter {
	private Stage stage;
	ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;

	private Player player;
	private Level level;
	private IPresenter presenter;

	private SpriteBatch batch;
	private BitmapFont font;
	private InputController controller;

	/**
	 * Create a new game
	 */
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		font = new BitmapFont();


		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		presenter = new ShapePresenter(shapeRenderer, stage, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

		player = new Player(new Point(GameConstants.SCREEN_WIDTH/2f, GameConstants.SCREEN_HEIGHT/2f), presenter.getPlayerDrawer());
		level = new Level(presenter, player, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

		controller = new InputController(camera, level);
	}

	/**
	 * Do update and draw
	 */
	@Override
	public void render () {
		update();
		stage.act();

		draw();
		stage.draw();
		stage.clear();
	}

	/**
	 * Dispose the shape renderer, batch and font
	 */
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
		stage.dispose();
	}

	/**
	 * Update the player, controller and level status
	 */
	private void update(){
		if(level.isOver()){
			return;
		}
		controller.checkForInput();
		level.update();
	}

	/**
	 * Draw the game
	 */
	private void draw() {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		presenter.start(camera);

		level.draw();
		player.draw();
		presenter.end();

		batch.begin();
		Label.LabelStyle label1Style = new Label.LabelStyle();
		label1Style.font = font;
		label1Style.fontColor = Color.RED;

		if(level.isOver()){
			Label label1 = new Label("YOU DIED", label1Style);
			label1.setSize(Gdx.graphics.getWidth(), 20);
			label1.setPosition(0, GameConstants.SCREEN_HEIGHT / 2f);
			label1.setAlignment(Align.center);
			stage.addActor(label1);
		}else{
			Label label2 = new Label("Health: " + player.getHealth(), label1Style);
			label2.setPosition(10, 20);
			stage.addActor(label2);

			Label label3 = new Label("Shield: " + player.getShield(), label1Style);
			label3.setPosition(10, 50);
			stage.addActor(label3);

			Label label4 = new Label("Gold: " + player.getGold(), label1Style);
			label4.setPosition(900, 20);
			stage.addActor(label4);
		}
		batch.end();

	}
}
