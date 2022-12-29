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
import graphics.presenters.IUIPresenter;
import graphics.presenters.IDrawerFactory;
import graphics.game.entities.drawers.door.IDoorDrawer;
import graphics.level.ILevelDrawer;
import manager.IEntityManager;

import java.util.*;

/**
 * Represents a level
 *
 * @author Daniel
 * @author Ethan
 */
public class Level implements IRoomContainer {

    private IDrawerFactory drawerFactory;
    private Room currentRoom;
    private final ILevelDrawer levelDrawer;
    private final IUIPresenter UIPresenter;
    private final Random rnd = new Random();
    private final int screenWidth;
    private final int screenHeight;
    private final Player player;

    /**
     * Create a level
     *
     * @param drawerFactory    the drawerFactory in Clean architecture
     * @param screenWidth  the width of screen
     * @param screenHeight the height of screen
     */
    public Level(IDrawerFactory drawerFactory, IUIPresenter UIPresenter, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.UIPresenter = UIPresenter;
        this.drawerFactory = drawerFactory;
        levelDrawer = drawerFactory.getLevelDrawer();

        player = new Player(new Point(GameConstants.SCREEN_WIDTH/2f, GameConstants.SCREEN_HEIGHT/2f), drawerFactory.getPlayerDrawer());
        Gun gun = new Gun(new Point(screenWidth / 2f, screenHeight / 2f), drawerFactory.getGunDrawer(), drawerFactory.getBulletDrawer());
        player.setGun(gun);

        PlanarGraph levelLayout = new TestGraphGenerator().generate();
        Collection<Room> rooms = getRoomsFromGraph(levelLayout, drawerFactory);
        currentRoom = rooms.iterator().next();

        gun.setEntityManager(currentRoom.getEntityManager());
    }

    private Collection<Room> getRoomsFromGraph(PlanarGraph levelLayout, IDrawerFactory presenter) {
        Map<Set<PlanarNode>, Boolean> edges = getEdgeMap(levelLayout);
        Map<PlanarNode, Room> nodeToRoom = new HashMap<>();
        for (PlanarNode node : levelLayout) {
            nodeToRoom.put(node, new Room(presenter, player, screenWidth, screenHeight));
        }

        for (PlanarNode node : levelLayout) {
            for (PlanarNode neighbour : node.getNeighboors()) {
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
        //this line of code is for convenience of testing
//        currentRoom = new entities.rooms.BossRoom(presenter, player, screenWidth, screenHeight);
    }

    /**
     * Set a new room
     *
     * @param room a room that will be in container.
     */
    public void setNewRoom(Room room) {
        IEntityManager entityManager = currentRoom.getEntityManager();
        if (entityManager.isFinished()) {
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

        UIPresenter.updatePlayerShield(player.getHealth());
        UIPresenter.updateIsPlayerDead(player.getHealth() <= 0);
        UIPresenter.updatePlayerHealth(player.getHealth());
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
                if (n1 != n2 && !edges.containsKey(pair) && n1.getNeighboors().contains(n2)) {
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

    /*
     * Returns tru if player is dead, false otherwise
     * */
    public boolean isOver() {
        return player.getHealth() <= 0;
    }

}
