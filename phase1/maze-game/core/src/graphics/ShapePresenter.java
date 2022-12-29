package graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import graphics.entities.boss.CircleBossDrawer;
import graphics.entities.boss.IBossDrawer;
import graphics.entities.merchant.CircleMerchantDrawer;
import graphics.bullet.CircleBulletDrawer;
import graphics.bullet.IBulletDrawer;
import graphics.door.CircleDoorDrawer;
import graphics.door.IDoorDrawer;
import graphics.entities.merchant.IMerchantDrawer;
import graphics.entities.enemy.CircleEnemyDrawer;
import graphics.entities.enemy.IEnemyDrawer;
import graphics.gun.CircleGunDrawer;
import graphics.gun.IGunDrawer;
import graphics.level.ILevelDrawer;
import graphics.level.LevelDrawer;
import graphics.entities.player.CirclePlayerDrawer;
import graphics.entities.player.IPlayerDrawer;
import graphics.room.IRoomDrawer;
import graphics.room.SandRoomDrawer;

public class ShapePresenter implements IPresenter {
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private int screenWidth;
    private int screenHeight;

    public ShapePresenter(ShapeRenderer shapeRenderer, Stage stage, int screenWidth, int screenHeight){
        this.stage = stage;
        this.shapeRenderer = shapeRenderer;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void start(Camera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    public void end() {
        shapeRenderer.end();
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
        return new CircleEnemyDrawer(shapeRenderer);
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
    public IBossDrawer getBossDrawer(){return new CircleBossDrawer(shapeRenderer);}
    }

