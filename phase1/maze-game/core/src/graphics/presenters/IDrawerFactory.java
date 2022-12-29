package graphics.presenters;

import graphics.game.entities.drawers.MineDropperEnemy.IMineDropperDrawer;
import graphics.game.entities.drawers.bullet.IBulletDrawer;
import graphics.game.entities.drawers.door.IDoorDrawer;
import graphics.game.entities.drawers.merchant.IMerchantDrawer;
import graphics.game.entities.drawers.enemy.IEnemyDrawer;
import graphics.game.entities.drawers.gun.IGunDrawer;
import graphics.healthbar.IHealthBarDrawer;
import graphics.level.ILevelDrawer;
import graphics.game.entities.drawers.player.IPlayerDrawer;
import graphics.room.IRoomDrawer;

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
}
