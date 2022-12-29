package console.entities;
import java.util.Date;

public class Log {
    private int userid;
    private Date loginDate;
    private boolean launch;
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
