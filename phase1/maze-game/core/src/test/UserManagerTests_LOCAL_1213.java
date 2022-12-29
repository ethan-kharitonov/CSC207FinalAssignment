//import console.usecases.UserController;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class UserManagerTests {
//
//    private UserController userController = new UserController();
//    @Test(timeout = 50)
//    public void testLoginNonAdmin(){
//        userController.addUser("Alice", "AlicePass", false);
//        assertNotNull("Was unable to add non-admin user",
//                userController.login("Alice", "AlicePass"));
//    }
//    @Test(timeout = 50)
//    public void testLoginAdmin(){
//        userController.addUser("Alice", "AlicePass", true);
//        assertNotNull("Was unable to add non-admin user",
//                userController.login("Alice", "AlicePass"));
//    }
//    @Test(timeout = 50)
//    public void testLoginInvalidUser() {
//        assertNull(userController.login("Charlie the Ugly Duckling", "Quack"));
//    }
//
//    @Test(timeout = 50)
//    public void testDeleteUser(){
//        userController.addUser("Bob the Vanquished", "BobPass", false);
//        String response = String.valueOf(userController.deleteUser("Bob the Vanquished"));
//        assertEquals("SUCCESS", response);
//    }
//
//    @Test(timeout = 200)
//    public void testSuspendUser(){
//        // For some reason this test takes longer than 50ms
//        userController.addUser("Bob the Banned", "BobPass", false);
//        String response = String.valueOf(userController.banUser("Bob the Banned"));
//        assertEquals("SUCCESS", response);
//    }
//}
