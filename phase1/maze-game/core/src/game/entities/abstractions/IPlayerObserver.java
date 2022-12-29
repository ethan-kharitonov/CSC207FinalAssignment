package game.entities.abstractions;

import geometry.Point;

/**
 * Represents the player observer interface
 * @author Ethan
 */
public interface IPlayerObserver {

    /**
     * Set the target
     * @param target the target of observer.
     */
    void setTarget(Point target);

}
