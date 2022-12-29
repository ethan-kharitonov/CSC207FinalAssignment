package graphics.presenters;

import java.util.Collection;

public interface IUIPresenter extends IDrawable {
    void draw();

    void updatePlayerShield(int playerShield);
    void updateIsPlayerDead(boolean isPlayerDead);
    void updatePlayerGold(int playerGold);
    void updatePlayerHealth(int playerHealth);

    void playerWins();

    void setDrawables(Collection<IDrawable> drawables);
    void dispose();
}
