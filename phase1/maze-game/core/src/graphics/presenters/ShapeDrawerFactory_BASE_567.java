package graphics.presenters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import graphics.game.entities.drawers.merchant.CircleMerchantDrawer;
import graphics.game.entities.drawers.bullet.CircleBulletDrawer;
import graphics.game.entities.drawers.bullet.IBulletDrawer;
import graphics.game.entities.drawers.door.CircleDoorDrawer;
import graphics.game.entities.drawers.door.IDoorDrawer;
import graphics.game.entities.drawers.merchant.IMerchantDrawer;
import graphics.game.entities.drawers.enemy.CircleEnemyDrawer;
import graphics.game.entities.drawers.enemy.IEnemyDrawer;
import graphics.game.entities.drawers.gun.CircleGunDrawer;
import graphics.game.entities.drawers.gun.IGunDrawer;
import graphics.healthbar.HealthBarDrawer;
import graphics.healthbar.IHealthBarDrawer;
import graphics.level.ILevelDrawer;
import graphics.level.LevelDrawer;
import graphics.game.entities.drawers.player.CirclePlayerDrawer;
import graphics.game.entities.drawers.player.IPlayerDrawer;
import graphics.room.IRoomDrawer;
import graphics.room.SimpleShapeRoomDrawer;

public class ShapeDrawerFactory implements IDrawerFactory {
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private int screenWidth;
    private int screenHeight;

    public ShapeDrawerFactory(int screenWidth, int screenHeight){
        spriteBatch =  new SpriteBatch();
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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        spriteBatch.setProjectionMatrix(camera.combined);
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
    }

    @Override
    public IDoorDrawer getDoorDrawer() {
        return new CircleDoorDrawer(shapeRenderer);
    }

    @Override
    public IRoomDrawer getRoomDrawer() {
        return new SimpleShapeRoomDrawer(shapeRenderer, screenWidth, screenHeight);
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
        return new CircleEnemyDrawer(shapeRenderer);
    }

    public IMerchantDrawer getMerchantDrawer(){return new CircleMerchantDrawer(shapeRenderer, new BitmapFont());}

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
}

