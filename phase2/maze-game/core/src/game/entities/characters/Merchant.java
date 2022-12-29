package game.entities.characters;

import game.entities.abstractions.CollidableEntity;
import game.entities.abstractions.ICollidable;
import game.entities.item.Item;
import geometry.Circle;
import geometry.Point;
import graphics.entityDrawers.merchant.IMerchantDrawer;

import java.util.ArrayList;

public class Merchant extends CollidableEntity {
    //A merchant will sell stuff to the player
    private ArrayList<Item> itemsOwned;
    private IMerchantDrawer drawer;
    private boolean showMenu = false;
    private Player player;
    private Point playerPosOnCollision;

    public Merchant(float x, float y, ArrayList<Item> itemOwned, IMerchantDrawer drawer) {
        super(x, y);
        this.itemsOwned = itemOwned;
        this.drawer = drawer;
    }

    @Override
    public void draw() {
        drawer.drawMerchant(pos, showMenu, itemsOwned);
    }

    @Override
    public Circle getCollisionBox() {
        return new Circle(pos, 15);
    }
    @Override
    public void collideWith(Player player) {
        showMenu = true;
        this.player = player;
        playerPosOnCollision = player.pos.clonePoint();
    }

    @Override
    public void informCollision(ICollidable other) {
    other.collideWith(this);
    }
    public ArrayList<Item> getItemsOwned(){return itemsOwned;}

    @Override
    public void update() {
        if(player == null){
            return;
        }
        if(player.pos.getX() != playerPosOnCollision.getX() || player.pos.getY() != playerPosOnCollision.getY()){
            player = null;
            playerPosOnCollision = null;
            showMenu = false;
        }
    }

    public void updateNumberKeysPressed(boolean[] keys){
        if(player == null){
            return;
        }

        for(int i = keys.length - 1; i >= 0; --i){
            if(i >= itemsOwned.size()){
                continue;
            }

            if(!keys[i]){
                continue;
            }

            Item item = itemsOwned.get(i);
            if(!player.tryToPay(item.getPrice())){
                continue;
            }

            item.operateOnPlayer(player);
            itemsOwned.remove(i);
        }
    }
}
