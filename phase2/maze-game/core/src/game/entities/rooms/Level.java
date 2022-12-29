package game.entities.rooms;

import config.GameConstants;
import game.entities.item.Door;
import game.entities.item.Gun;
import game.entities.abstractions.IRoomContainer;
import game.entities.characters.Player;
import geometry.Point;
import graph.PlanarGraph;
import graph.PlanarNode;
import graph.TestGraphGenerator;
import graphics.presenters.IDrawable;
import graphics.presenters.IUIPresenter;
import graphics.presenters.IDrawerFactory;
import graphics.entityDrawers.door.IDoorDrawer;
import graphics.otherDrawers.level.ILevelDrawer;

import java.util.*;

/**
 * Represents a level
 *
 * @author Daniel
 * @author Ethan
 */
public class Level implements IRoomContainer, IDrawable {

    private IDrawerFactory drawerFactory;
    private Room currentRoom;
    private Collection<Room> rooms;
    private ILevelDrawer levelDrawer;
    private IUIPresenter Presenter;
    private Random rnd = new Random();
    private int screenWidth;
    private int screenHeight;
    private Player player;

    /**
     * Create a level
     * @param drawerFactory    the drawerFactory in Clean architecture
     * @param screenWidth  the width of screen
     * @param screenHeight the height of screen
     */
    public Level(IDrawerFactory drawerFactory, IUIPresenter Presenter, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.Presenter = Presenter;
        this.drawerFactory = drawerFactory;
        levelDrawer = drawerFactory.getLevelDrawer();

        player = new Player(new Point(GameConstants.SCREEN_WIDTH/2f, GameConstants.SCREEN_HEIGHT/2f), drawerFactory.getPlayerDrawer());
        Gun gun = new Gun(new Point(screenWidth / 2f, screenHeight / 2f), drawerFactory.getGunDrawer(), drawerFactory.getBulletDrawer());
        player.setGun(gun);

        PlanarGraph levelLayout = new TestGraphGenerator().generate();
        rooms = getRoomsFromGraph(levelLayout, drawerFactory);
        currentRoom = rooms.iterator().next();

        gun.setEntityManager(currentRoom.getEntityManager());

        Collection<IDrawable> drawables = new ArrayList<>();
        drawables.add(this);
        Presenter.setDrawables(drawables);
    }

    private Collection<Room> getRoomsFromGraph(PlanarGraph levelLayout, IDrawerFactory presenter) {
        Map<Set<PlanarNode>, Boolean> edges = getEdgeMap(levelLayout);
        Map<PlanarNode, Room> nodeToRoom = new HashMap<>();
        for (PlanarNode node : levelLayout) {
            nodeToRoom.put(node, new Room(presenter, player, screenWidth, screenHeight));
        }

        for (PlanarNode node : levelLayout) {
            for (PlanarNode neighbour : node.getNeighbors()) {
                Set<PlanarNode> pair = new HashSet<>();
                pair.add(node);
                pair.add(neighbour);
                if (!edges.get(pair)) {
                    Room r1 = nodeToRoom.get(node);
                    Room r2 = nodeToRoom.get(neighbour);

                    IDoorDrawer doorDrawer = presenter.getDoorDrawer();
                    Door door1 = new Door(getRandomPointOnScreen(), doorDrawer, this);
                    Door door2 = new Door(getRandomPointOnScreen(), doorDrawer, this);

                    door1.setRoom(r1);
                    door2.setRoom(r2);

                    door1.setCorrespondingDoor(door2);
                    door2.setCorrespondingDoor(door1);
                    r1.addDoor(door1);
                    r2.addDoor(door2);

                    edges.put(pair, true);
                }
            }

        }

        return nodeToRoom.values();
    }

    /**
     * Set a new room
     *
     * @param room a room that will be in container.
     */
    public void setNewRoom(Room room) {
        if (currentRoom.allEnemiesKilled()) {
            currentRoom = room;
            player.setGunEntityManager(currentRoom.getEntityManager());
        }
    }

    public Player getPlayer(){
        return player;
    }
    /**
     * Update the current room
     */
    public void update() {
        currentRoom.update();

        boolean playerWins = true;
        for(Room room : rooms){
            if(!room.allEnemiesKilled()){
                playerWins = false;
            }
        }

        if(playerWins){
            Presenter.playerWins();
        }

        Presenter.updatePlayerShield(player.getShield());
        Presenter.updateIsPlayerDead(player.getHealth() <= 0);
        Presenter.updatePlayerHealth(player.getHealth());
        Presenter.updatePlayerGold(player.getGoldOwned());

        if (currentRoom.allEnemiesKilled()){player.addGold(currentRoom.getEntityManager().getGold());}

    }

    /**
     * Draw the level
     */
    public void draw() {
        drawerFactory.onStartRender();
        levelDrawer.drawLevel(currentRoom);
        player.draw();
        drawerFactory.onEndRender();
    }

    /**
     * Return an edge map
     *
     * @param levelLayout the layout of level
     * @return a map of edges
     */
    private Map<Set<PlanarNode>, Boolean> getEdgeMap(PlanarGraph levelLayout) {
        Map<Set<PlanarNode>, Boolean> edges = new HashMap<>();
        for (PlanarNode n1 : levelLayout) {
            for (PlanarNode n2 : levelLayout) {
                Set<PlanarNode> pair = new HashSet<>();
                pair.add(n1);
                pair.add(n2);
                if (n1 != n2 && !edges.containsKey(pair) && n1.getNeighbors().contains(n2)) {
                    edges.put(pair, false);
                }
            }
        }

        return edges;
    }

    /**
     * Generate a random point
     *
     * @return a random point on screen
     */
    private Point getRandomPointOnScreen() {
        return new Point(rnd.nextInt(screenWidth), rnd.nextInt(screenHeight));
    }

    /**
     * Move player in a given direction
     *
     * @param dir direction
     */
    public void movePlayer(Point dir) {
        player.move(dir);
    }

    /**
     * Update the position of mouse
     *
     * @param mousePos position of mouse
     */
    public void setMousePos(Point mousePos) {
        player.setMousePos(mousePos);
    }

    /**
     * Click mouse to fire
     *
     * @param mouseDir direction of fire
     */
    public void mouseClick(Point mouseDir) {
        player.fire(mouseDir);
    }

    public void updateMerchantNumberKeys(boolean[] keys){
        currentRoom.updateMerchantNumberKeys(keys);
    }

    /*
     * Returns tru if player is dead, false otherwise
     * */
    public boolean isOver() {
        return player.getHealth() <= 0;
    }

}
