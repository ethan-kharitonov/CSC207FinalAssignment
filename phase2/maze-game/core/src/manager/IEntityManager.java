package manager;

import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.Entity;

// Represents an entity manager interface
// @author Daniel
// @author Ethan
// @author Ian

public interface IEntityManager {

    /**
     * Add a non collidable entity to manager
     * @param ent entity being added
     */
    void addNonCollidableEntity(Entity ent);

    /**
     * Add a collidable entity to manager
     * @param ent entity being added
     */
    void addCollidableEntity(CollidableEntity ent);

    /**
     * remove an entity from manager
     * @param ent entity being removed
     */
    void removeEntity(Entity ent);
    void removeEntity(CollidableEntity ent);

    void update();

    void draw();
    boolean isFinished();

    void addGold(int value);

    int getGold();
}
