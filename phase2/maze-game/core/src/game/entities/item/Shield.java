package game.entities.item;

import game.entities.characters.Player;

public class Shield extends Item{

    private final int shieldToAdd = 25;

    public Shield(){
        super("Small Shield", 20);
    }
    @Override
    public void operateOnPlayer(Player player) {
        player.addShield(25);
    }
}
