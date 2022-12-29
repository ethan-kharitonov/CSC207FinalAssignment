package console.entities;
import java.util.Date;
import java.io.Serializable;

public class Log implements Serializable {
    private int userid;
    private Date loginDate;
    private boolean launch; // false if simply logging in, true if a game is being launched.
    public Log (int userid, Date loginDate, boolean launch){
        this.userid = userid;
        this.loginDate = loginDate;
        this.launch = launch;
    }

    public int getUserid() {
        return userid;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public boolean isLaunch() { return launch; }

}
