package console.entities;
import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private int userid;
    private boolean admin;
    private boolean banned;

    public User(String userName, String password, boolean admin, int userid){
        this.userName = userName;
        this.password = password;
        this.admin = admin;
        this.userid = userid;
        this.banned = false;
    }

    //getters and setters
    public int getUserid() { return userid; }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public boolean isAdmin(){
        return admin;
    }
    public boolean isBanned() { return banned; }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

}
