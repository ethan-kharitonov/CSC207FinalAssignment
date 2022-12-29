package game.entities.item;

import config.GameConstants;
import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import game.entities.abstractions.IRoomContainer;
import game.entities.characters.Player;
import game.entities.rooms.Room;
import geometry.Circle;
import geometry.Point;
import graphics.game.entities.drawers.door.IDoorDrawer;
/** Represents a door
 * @author Ethan
 * @author Ian Curtis Ewing\
 * @author Daniel
 */
public class Door extends CollidableEntity {

    public Room room;
    public Door door;
    private IDoorDrawer doorDrawer;
    private IRoomContainer roomContainer;

    /**
     * Create a door
     * @param pos Position of the door.
     * @param doorDrawer Drawer of the door.
     * @param roomContainer Container of the door.
     */
    public  Door(Point pos, IDoorDrawer doorDrawer, IRoomContainer roomContainer){
        super(pos);
        this.doorDrawer = doorDrawer;
        this.roomContainer = roomContainer;
    }

    /**
     * Set the door.
     * @param door The target door.
     */
    public void setCorrespondingDoor(Door door){
        this.door = door;
    }

    /**
     * Set the room
     * @param room The target room
     */
    public void setRoom(Room room){
        this.room = room;
    }

    /**
     * Draw the door
     */
    public void draw(){
        doorDrawer.drawDoor(pos);
    }

    /**
     * Return the collision box of the door.
     * @return Circle the circle representation of the collision box.
     */
    public Circle getCollisionBox(){
        return new Circle(pos, GameConstants.DOOR_RADIUS);
    }

    /**
     * Inform other object being collided with the door
     * @param other the object being collided.
     */

    //No other entities implemented collidewith(Door)!
//    @Override
//    public void informCollision(ICollidable other) {
//        other.collideWith(this);
//    }

    /**
     * Collide with the other player
     * @param player the player being collided
     */
    public void collideWith(Player player){
        roomContainer.setNewRoom(door.room);
    }
}


