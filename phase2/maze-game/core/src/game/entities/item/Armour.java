package game.entities.item;

import game.entities.characters.Player;

public class Armour extends Item{
    private float armourDamageFactor;
    public Armour(float armourDamageFactor, String name, int price) {
        super(name, price);
        this.armourDamageFactor = armourDamageFactor;
    }

    @Override
    public void operateOnPlayer(Player player) {
        player.setArmour(armourDamageFactor);
    }
}
