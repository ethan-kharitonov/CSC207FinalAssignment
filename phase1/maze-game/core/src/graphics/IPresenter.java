package graphics;

import com.badlogic.gdx.graphics.Camera;
import graphics.entities.boss.IBossDrawer;
import graphics.bullet.IBulletDrawer;
import graphics.door.IDoorDrawer;
import graphics.entities.merchant.IMerchantDrawer;
import graphics.entities.enemy.IEnemyDrawer;
import graphics.gun.IGunDrawer;
import graphics.level.ILevelDrawer;
import graphics.entities.player.IPlayerDrawer;
import graphics.room.IRoomDrawer;

public interface IPresenter {

    public void start(Camera camera);
    public void end();
    public IDoorDrawer getDoorDrawer();
    public IRoomDrawer getRoomDrawer();

    public ILevelDrawer getLevelDrawer();

    public IPlayerDrawer getPlayerDrawer();

    public IEnemyDrawer getEnemyDrawer();
    public IGunDrawer getGunDrawer();

    public IMerchantDrawer getMerchantDrawer();

    public IBulletDrawer getBulletDrawer();

    public IBossDrawer getBossDrawer();
}
