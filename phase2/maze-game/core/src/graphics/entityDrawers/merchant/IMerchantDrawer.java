package graphics.entityDrawers.merchant;

import game.entities.item.Item;
import geometry.Point;

import java.util.ArrayList;

public interface IMerchantDrawer {

    void drawMerchant(Point pos, boolean showMenu, ArrayList<Item> items);
}
