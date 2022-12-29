package console.usecases;

import config.Msg;
import console.entities.Log;
import console.entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    private TextFileLogger logger = new TextFileLogger("userManager.ser");
    public UserManager manager;

    public UserController() {
        UserManager storedManager = (UserManager) logger.readFromFile();
        this.manager = storedManager == null ? new UserManager() : storedManager;
    }

    public Msg addUser(String username, String password, boolean admin) {
        if (username.equalsIgnoreCase(password)) {
            return Msg.FAILURE_WEAK_PASSWORD;
        }
        if (manager.getUser(username) != null){
            return Msg.FAILURE_USERNAME_TAKEN;
        }
        User user = new User(username, password, false, 9000);
        manager.addUser(username, password, admin);
        return Msg.SUCCESS;
    }

    public Msg deleteUser(String username) {
        User user = manager.getUser(username);
        if (user == null){
            return Msg.FAILURE_USER_NOT_FOUND;
        }
        if (user.isAdmin()) {
            return Msg.FAILURE_USER_IS_ADMIN;
        }
        manager.delete(username);
        return Msg.SUCCESS;
    }

    public Msg banUser(String username) {
        User user = manager.getUser(username);
        if (user == null){
            return Msg.FAILURE_USER_NOT_FOUND;
        }
        if (user.isAdmin()) {
            return Msg.FAILURE_USER_IS_ADMIN;
        }
        if(user.isBanned()){
            return Msg.NO_CHANGE;
        }
        user.setBanned(true);
        return Msg.SUCCESS;
    }

    public Msg unbanUser(String username) {
        User user = manager.getUser(username);
        if (user == null){
            return Msg.FAILURE_USER_NOT_FOUND;
        }
        if(!user.isBanned()){
            return Msg.NO_CHANGE;
        }
        user.setBanned(false);
        return Msg.SUCCESS;
    }


    public User login(String userName, String password) {
        User user = manager.getUser(userName);
        if (user == null || !user.getPassword().equals(password) || user.isBanned()) {
            return null;
        }

        manager.recordLog(user.getUserid(), false);

        return user;
    }
    public void logLaunch(int userid){
        manager.recordLog(userid, true);
    }
    public String getLogsOf(int userid){
        ArrayList<Log> logs = manager.getLogs();
        StringBuilder logOutput = new StringBuilder("Your activity in chronological order:");
        for (Log log : logs) {
            if(log.getUserid() == userid) {
                logOutput.append("\n").append(log.getLoginDate());
                if(log.isLaunch()) logOutput.append(" - Launched Game.");
                else logOutput.append(" - Logged In.");
            }
        }
        return logOutput.toString();
    }
    public void saveUserManager() throws IOException {
        logger.logToFile(manager);
    }
}
