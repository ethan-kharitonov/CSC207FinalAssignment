package console.usecases;

import console.entities.Log;
import console.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
public class UserManager implements Serializable {
    private int lastId = 0;
    private Map<String, User> users = new HashMap<>();
    private ArrayList<Log> logHistory = new ArrayList<>();

    public UserManager(){
        // This constructor is only ever called in UserController.java
        // if there is an error reading UserManager.ser
        // and a new UserManager is created instead.
        addUser("admin", "123", true);
    }
    public void addUser(String userName, String password, boolean isAdmin) {
        User newUser = new User(userName, password, isAdmin, lastId);
        users.put(userName, newUser);
        ++lastId;
    }
    public User getUser(String username){
        return users.get(username);
    }
    public void delete(String userName){
        users.remove(userName);
    }

    public void recordLog(int userid, boolean launch){
        Log log = new Log(userid, new Date(), launch);
        logHistory.add(log);
    }

    public ArrayList<Log> getLogs(){
        return logHistory;
    }
}
