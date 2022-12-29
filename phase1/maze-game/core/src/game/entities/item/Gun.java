package game.entities.item;

import config.GameConstants;
import manager.IEntityManager;
import geometry.Point;
import graphics.game.entities.drawers.bullet.IBulletDrawer;
import graphics.game.entities.drawers.gun.IGunDrawer;

public class Gun extends Weapon {

    private IGunDrawer gunDrawer;
    private IBulletDrawer bulletDrawer;
    private IEntityManager entityManager;
    private long lastAttack = 0;

    private String name = "Default Gun";

    public Gun(Point pos, IGunDrawer gunDrawer, IBulletDrawer bulletDrawer){
        super(pos);
        this.gunDrawer = gunDrawer;
        this.bulletDrawer = bulletDrawer;
    }

    public void setEntityManager(IEntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void fire(Point direction){
        long time = System.currentTimeMillis();
        if (time >= lastAttack + GameConstants.GUN_COOLDOWN) {
            Bullet bullet = new Bullet(pos, direction.distanceVector(pos).normalized(), bulletDrawer);
            entityManager.addCollidableEntity(bullet);
            lastAttack = time;
        }
    }

    public void setPlayerPosition(Point playerPos){
        pos = playerPos;
    }

    @Override
    public void draw() {
        gunDrawer.drawGun(pos);
    }
}
