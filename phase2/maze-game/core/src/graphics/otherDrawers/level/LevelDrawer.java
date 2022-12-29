package graphics.otherDrawers.level;

import game.entities.rooms.Room;

public class LevelDrawer implements ILevelDrawer{
    @Override
    public void drawLevel(Room room) {
        room.draw();
    }
}
