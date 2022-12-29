package console.launcher;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import config.GameConstants;
import game.entities.rooms.Level;
import game.entities.characters.Player;
import graphics.presenters.IUIPresenter;
import graphics.presenters.UIPresenter;
import graphics.presenters.IDrawerFactory;
import graphics.presenters.ShapeDrawerFactory;
import manager.InputController;

/**
 * Represents a mazegame
 * @author Ethan
 * @author Daniel
 */
public class MazeGame extends ApplicationAdapter {
	private Player player;
	private Level level;
	private IDrawerFactory drawerFactory;
	private IUIPresenter Presenter;
	private InputController controller;

	/**
	 * Create a new game
	 */
	@Override
	public void create () {
		Stage stage = new Stage(new ScreenViewport());
		Presenter = new UIPresenter(stage);
		drawerFactory = new ShapeDrawerFactory(stage, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

		level = new Level(drawerFactory, Presenter, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		controller = new InputController(level);
	}

	/**
	 * Do update and draw
	 */
	@Override
	public void render () {
		update();
		draw();
	}

	/**
	 * Dispose the shape renderer, batch and font
	 */
	@Override
	public void dispose () {
		drawerFactory.dispose();
		Presenter.dispose();
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
		Presenter.draw();
	}
}
