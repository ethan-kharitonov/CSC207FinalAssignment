package game.entities.characters;

import com.badlogic.gdx.math.MathUtils;
import config.GameConstants;
import game.entities.Mine;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import geometry.Circle;
import geometry.Point;
import graphics.game.entities.drawers.MineDropperEnemy.IMineDropperDrawer;
import graphics.game.entities.drawers.enemy.IEnemyDrawer;
import manager.IEntityManager;

public class MineDropperEnemy extends CollidableEntity {
    private IEntityManager entityManager;
    private IMineDropperDrawer mineDropperDrawer;
    private Point targetPoint;
    private float speed = 2;

    public MineDropperEnemy(Point pos, IMineDropperDrawer mineDropperDrawer) {
        super(pos);
        targetPoint = getNewTargetPoint();
        this.mineDropperDrawer = mineDropperDrawer;
    }

    public void SetEntityManager(IEntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Circle getCollisionBox() {
        return new Circle(pos, 30);
    }

    @Override
    public void informCollision(ICollidable other){
        other.collideWith(this);
    }

    @Override
    public void update(){
        float dist = pos.distanceVector(targetPoint).length();
        if(dist < speed){
            pos = targetPoint;
            targetPoint = getNewTargetPoint();
        }else{
            Point dir = pos.distanceVector(targetPoint).normalized();
            dir.multiply(-speed);
            pos.add(dir);
        }
    }

    private Point getNewTargetPoint(){
        return new Point(MathUtils.random(Mine.getMineRadius()/2, GameConstants.SCREEN_WIDTH - Mine.getMineRadius()/2),
                MathUtils.random(Mine.getMineRadius()/2, GameConstants.SCREEN_HEIGHT - Mine.getMineRadius()/2));
    }

    @Override
    public void draw(){
        mineDropperDrawer.drawMineDropper(pos);
    }
}
