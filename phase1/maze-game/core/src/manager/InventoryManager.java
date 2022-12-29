//package manager;
//
//import entities.characters.Player;
//import entities.item.Armour;
//import entities.item.HealthFlask;
//import entities.item.Item;
//
//import java.util.ArrayList;
//
//public class InventoryManager {
//    private Player player;
//    private ArrayList<Item> items;
//    public InventoryManager(Player player, ArrayList<Item> items)
//    {this.player = player;
//    this.items = items;}
//
//    public Player getPlayer(){
//        return this.player;
//    }
//
//    public ArrayList<Item> getItems(){
//        return this.items;
//    }
//
//    public void addItem(Item item) {
//        items.add(item);
//    }
//
//    public void removeItem(Item item) {
//        items.remove(item);
//    }
//
//    public void use(Armour armour) {
//        player.setArmour(armour);
//    }
//
//    public boolean hasArmour() {
//        for (Item item : items) {
//            if (item instanceof Armour) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Item getArmour() {
//        for (Item item : items) {
//            if (item instanceof Armour) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public boolean hasHealthFlask() {
//        for (Item item : items) {
//            if (item instanceof HealthFlask) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Item getHealthFlask() {
//        for (Item item : items) {
//            if (item instanceof HealthFlask) {
//                return item;
//            }
//        }
//        return null;
//    }
//}
