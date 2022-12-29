package console.launcher;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import config.GameConstants;

// Please note that on macOS your application needs to be started with the -XStartOnFirstThread JVM argument

/**
 * The main class to launch desktop
 * @author Ethan
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("maze-game");
		config.setWindowedMode(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		config.useVsync(true);
		config.setForegroundFPS(GameConstants.FOREGROUND_FPS);
		new Lwjgl3Application(new MazeGame(), config);
	}
}
