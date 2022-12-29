package config;
/**
 * config file for errors and other messages to be displayed in console UI.
 * @author Ivan Vorobyov
 */
public enum Msg {
    FAILURE_USER_NOT_FOUND("No user with this name can be found."),
    FAILURE_USER_IS_ADMIN("This user is an admin and cannot be altered."),
    FAILURE_USERNAME_TAKEN("A user already exists with that name."),
    FAILURE_WEAK_PASSWORD("Password cannot be same as username."),
    FAILURE_CANNOT_LOG_IN("Login failed."),
    NO_CHANGE("No change: user is already as requested."),
    SUCCESS("Success!");

    private final String msg; //Enum type fields are always made final

    Msg(String english) {
        this.msg = english;
    }
    public String out(){
        return msg;
    }

}