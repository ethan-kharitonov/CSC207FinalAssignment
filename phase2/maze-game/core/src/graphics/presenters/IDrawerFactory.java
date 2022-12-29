package graphics.presenters;

import graphics.entityDrawers.mine.IMineDrawer;
import graphics.entityDrawers.mineDropperEnemy.IMineDropperDrawer;
import graphics.entityDrawers.bullet.IBulletDrawer;
import graphics.entityDrawers.door.IDoorDrawer;
import graphics.entityDrawers.merchant.IMerchantDrawer;
import graphics.entityDrawers.enemy.IEnemyDrawer;
import graphics.entityDrawers.gun.IGunDrawer;
import graphics.otherDrawers.healthbar.IHealthBarDrawer;
import graphics.otherDrawers.level.ILevelDrawer;
import graphics.entityDrawers.player.IPlayerDrawer;
import graphics.otherDrawers.room.IRoomDrawer;

public interface IDrawerFactory {

    void onStartRender();
    void onEndRender();
    void dispose();
    IDoorDrawer getDoorDrawer();
    IRoomDrawer getRoomDrawer();

    ILevelDrawer getLevelDrawer();

    IPlayerDrawer getPlayerDrawer();

    IEnemyDrawer getEnemyDrawer();
    IGunDrawer getGunDrawer();

    IMerchantDrawer getMerchantDrawer();

    IBulletDrawer getBulletDrawer();

    IHealthBarDrawer getHealthBarDrawer();

    IMineDropperDrawer getMineDropperEnemyDrawer();

    IMineDrawer getMineDrawer();
}
