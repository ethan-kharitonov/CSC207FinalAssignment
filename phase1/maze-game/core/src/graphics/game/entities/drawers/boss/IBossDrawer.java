package graphics.game.entities.drawers.boss;

import geometry.Point;

public interface IBossDrawer {
    void drawBoss(Point pos);
    void setRadius(int radius);
}
