package manager;

import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.Entity;


import java.util.ArrayList;
import java.lang.*;

/**
 * Represents an entity manager.
 * @author Ian
 * @author Jack Sun
 * @author Daniel
 * @author Ethan
 */
public class EntityManager implements IEntityManager{
    private ArrayList<Entity> Entities;
    private ArrayList<CollidableEntity> collidableEntities;
    private ArrayList<Entity> toBeAddedEntities;
    private ArrayList<Entity> toBeRemovedEntities;
    private ArrayList<CollidableEntity> toBeAddedCollidable;
    private ArrayList<CollidableEntity> toBeRemovedCollidable;

    private int gold = 0;



    /**
     * Create an entity manager
     */
    public EntityManager() {
        Entities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
        toBeAddedEntities = new ArrayList<>();
        toBeRemovedEntities = new ArrayList<>();
        toBeAddedCollidable = new ArrayList<>();
        toBeRemovedCollidable = new ArrayList<>();
    }

    /**
     * Get all entities
     * @return list of entities
     */
    public ArrayList<Entity> getEntities(){
        return this.Entities;
    }

    /**
     * Get all entities
     * @return list of collidable entities
     */
    public ArrayList<CollidableEntity> getCollidableEntities(){
        return this.collidableEntities;
    }

    /**
     * Add a non-collidable entity to manager
     * @param ent an entity
     */
    public void addNonCollidableEntity (Entity ent) {toBeAddedEntities.add(ent);}

    /**
     * Add a collidable entity to manager
     * @param ent a collidable entity
     */
    public void addCollidableEntity (CollidableEntity ent) {
        toBeAddedCollidable.add(ent);
        toBeAddedEntities.add(ent);
    }
    /**
     * Remove a collidable entity from manager
     * @param ent a collidable entity
     */
    public void removeEntity (CollidableEntity ent) {
        toBeRemovedCollidable.add(ent);
        toBeRemovedEntities.add(ent);
    }

    /**
     * Remove an entity to manager
     * @param ent an entity
     */
    public void removeEntity (Entity ent) {
        toBeRemovedEntities.add(ent);
    }

    public boolean isFinished(){
        for (Entity entity: Entities){
            if (entity.needToBeKilled){
                return false;
            }
        }

        for (Entity entity: toBeAddedEntities){
            if (entity.needToBeKilled){
                return false;
            }
        }

        for (Entity entity: toBeAddedCollidable){
            if (entity.needToBeKilled){
                return false;
            }
        }

        return true;
    }

    public void addGold(int gold){
        this.gold += gold;
    }
    public int getGold(){int value =gold;
        gold = 0;
        return value;}

    /**
     * Update the manager
     */
    @Override
    public void update(){
        for(Entity entity : Entities){
            if(entity.shouldBeRemoved()){
                entity.removeSelf(this);
            }
        }

        Entities.addAll(toBeAddedEntities);
        collidableEntities.addAll(toBeAddedCollidable);
        Entities.removeAll(toBeRemovedEntities);
        collidableEntities.removeAll(toBeRemovedCollidable);

        toBeAddedEntities.clear();
        toBeAddedCollidable.clear();
        toBeRemovedEntities.clear();
        toBeRemovedCollidable.clear();

        for(Entity entity: Entities){
            entity.update();
        }

        for(CollidableEntity e1: collidableEntities){
            for(CollidableEntity e2: collidableEntities){
                if(e1 == e2){
                    continue;
                }
                if(e1.getCollisionBox().intersects(e2.getCollisionBox())){
                    e1.informCollision(e2);}
            }
        }

    }

    /**
     * Draw the entities.
     */
    @Override
    public void draw() {
        for (Entity ent: Entities) {
            ent.draw();
        }
    }
}
