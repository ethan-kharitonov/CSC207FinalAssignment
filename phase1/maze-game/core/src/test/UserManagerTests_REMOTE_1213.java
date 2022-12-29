import console.usecases.UserController;
import config.Msg;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTests {

    private UserController userController = new UserController();
    @Test(timeout = 50)
    public void testLoginNonAdmin(){
        userController.addUser("Alice", "AlicePass", false);
        assertNotNull("Was unable to add non-admin user",
                userController.login("Alice", "AlicePass"));
    }
    @Test(timeout = 50)
    public void testLoginAdmin(){
        userController.addUser("Alice", "AlicePass", true);
        assertNotNull("Was unable to add non-admin user",
                userController.login("Alice", "AlicePass"));
    }
    @Test(timeout = 50)
    public void testLoginExisting(){
        userController.addUser("Alice", "AlicePass", false);
        Msg response = userController.addUser("Alice", "AlicePassImpostor", false);
        assertEquals(Msg.FAILURE_USERNAME_TAKEN, response);
    }
    @Test(timeout = 50)
    public void testLoginWeak(){
        Msg response = userController.addUser("willywonka", "willywonka", true);
        assertEquals(Msg.FAILURE_WEAK_PASSWORD, response);
    }
    @Test(timeout = 50)
    public void testLoginInvalidUser() {
        assertNull(userController.login("Charlie the Ugly Duckling", "Quack"));
    }
    @Test(timeout = 50)
    public void testDeleteInvalidUser() {
        assertEquals(Msg.FAILURE_USER_NOT_FOUND, userController.deleteUser("Charlie the Ugly Duckling"));
    }

    @Test(timeout = 50)
    public void testDeleteUser(){
        userController.addUser("Bob the Vanquished", "BobPass", false);
        Msg response = userController.deleteUser("Bob the Vanquished");
        assertEquals(Msg.SUCCESS, response);
        assertNull(userController.login("Bob the Vanquished", "BobPass"));
    }

    @Test(timeout = 50)
    public void testSuspendUser(){
        userController.addUser("Bob the Banned", "BobPass", false);
        Msg response = userController.banUser("Bob the Banned");
        assertEquals(Msg.SUCCESS, response);
        assertNull(userController.login("Bob the Banned", "BobPass"));
        response = userController.banUser("Bob the Banned");
        assertEquals(Msg.NO_CHANGE, response);
        assertNull(userController.login("Bob the Banned", "BobPass"));
        //unban:
        response = userController.unbanUser("Bob the Banned");
        assertEquals(Msg.SUCCESS, response);
        assertNotNull(userController.login("Bob the Banned", "BobPass"));
        response = userController.unbanUser("Bob the Banned");
        assertEquals(Msg.NO_CHANGE, response);
        assertNotNull(userController.login("Bob the Banned", "BobPass"));
    }
    @Test(timeout = 50)
    public void testDeleteAdmin(){
        userController.addUser("Mike the Mighty", "MikePass", true);
        Msg response = userController.deleteUser("Mike the Mighty");
        assertEquals(Msg.FAILURE_USER_IS_ADMIN, response);
        assertNotNull(userController.login("Mike the Mighty", "MikePass"));
    }
}
