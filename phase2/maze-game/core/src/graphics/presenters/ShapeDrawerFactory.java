package graphics.presenters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import graphics.entityDrawers.mine.IMineDrawer;
import graphics.entityDrawers.mine.SimpleShapeMineDrawer;
import graphics.entityDrawers.mineDropperEnemy.IMineDropperDrawer;
import graphics.entityDrawers.mineDropperEnemy.ShapeMineDropperDrawer;
import graphics.entityDrawers.enemy.CatEnemyDrawer;
import graphics.entityDrawers.merchant.CircleMerchantDrawer;
import graphics.entityDrawers.bullet.CircleBulletDrawer;
import graphics.entityDrawers.bullet.IBulletDrawer;
import graphics.entityDrawers.door.CircleDoorDrawer;
import graphics.entityDrawers.door.IDoorDrawer;
import graphics.entityDrawers.merchant.IMerchantDrawer;
import graphics.entityDrawers.enemy.IEnemyDrawer;
import graphics.entityDrawers.gun.CircleGunDrawer;
import graphics.entityDrawers.gun.IGunDrawer;
import graphics.otherDrawers.healthbar.HealthBarDrawer;
import graphics.otherDrawers.healthbar.IHealthBarDrawer;
import graphics.otherDrawers.level.ILevelDrawer;
import graphics.otherDrawers.level.LevelDrawer;
import graphics.entityDrawers.player.CirclePlayerDrawer;
import graphics.entityDrawers.player.IPlayerDrawer;
import graphics.otherDrawers.room.IRoomDrawer;
import graphics.otherDrawers.room.SandRoomDrawer;

public class ShapeDrawerFactory implements IDrawerFactory {
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private int screenWidth;
    private int screenHeight;

    public ShapeDrawerFactory(Stage stage, int screenWidth, int screenHeight){
        spriteBatch = new SpriteBatch();
        this.stage = stage;
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void onStartRender() {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        spriteBatch.begin();
    }

    @Override
    public void onEndRender() {
        shapeRenderer.end();
        spriteBatch.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
        spriteBatch.dispose();
        stage.dispose();
    }

    @Override
    public IDoorDrawer getDoorDrawer() {
        return new CircleDoorDrawer(shapeRenderer);
    }

    @Override
    public IRoomDrawer getRoomDrawer() {
        return new SandRoomDrawer(stage, screenWidth, screenHeight);
    }

    @Override
    public ILevelDrawer getLevelDrawer() {
        return new LevelDrawer();
    }

    @Override
    public IPlayerDrawer getPlayerDrawer() {
        return new CirclePlayerDrawer(shapeRenderer);
    }

    @Override
    public IEnemyDrawer getEnemyDrawer() {
        return new CatEnemyDrawer(stage);
    }

    public IMerchantDrawer getMerchantDrawer(){return new CircleMerchantDrawer(shapeRenderer, stage);}

    @Override
    public IGunDrawer getGunDrawer() {
        return new CircleGunDrawer(shapeRenderer);
    }

    @Override
    public IBulletDrawer getBulletDrawer() {
        return new CircleBulletDrawer(shapeRenderer);
    }

    @Override
    public IHealthBarDrawer getHealthBarDrawer() {
        return new HealthBarDrawer(shapeRenderer);
    }

    @Override
    public IMineDropperDrawer getMineDropperEnemyDrawer() {
        return new ShapeMineDropperDrawer(shapeRenderer);
    }

    @Override
    public IMineDrawer getMineDrawer() {
        return new SimpleShapeMineDrawer(shapeRenderer);
    }
}

