package console.interfaces;

import config.Msg;
import console.launcher.DesktopLauncher;
import console.entities.User;
import console.usecases.UserController;

import java.io.IOException;
import java.util.Scanner;

public class AppUI {
    private static int MIN = 1;//smallest acceptable username/password
    private User user;
    private UserController userController = new UserController();
    private Scanner in = new Scanner(System.in);

    public void run() {
        while (true) {
            mainMenu();
            if (user == null) {
                //Saves UserManager.ser and exits to main, where the program terminates.
                try {
                    userController.saveUserManager();
                } catch (IOException e) {
                    writeln("Error saving: " + e.getMessage());
                }
                return;
            }
            writeln("");
            writeln("Welcome " + user.getUserName());

            loggedInMenu();

            user = null; // could also go right before the call to mainMenu()
            writeln("You have been logged out");
            writeln("========================");
        }

    }

    private void mainMenu() {
        int state = -1;
        while (user == null) {
            switch (state) {
                case -1: {
                    displayTitle("Welcome");
                    writeln("Choose from the following commands:");
                    writeln("Exit (0) or Play Demo (1) or Login (2) or Signup (3)");
                    state = getNumUpTo(3);
                    break;
                }
                case 0: {
                    return;
                }
                case 1: {
                    DesktopLauncher.main(new String[]{});
                    state = -1;
                    break;
                }
                case 2: {
                    displayTitle("Login");
                    user = userController.login(enter("username"), enter("password"));
                    if (user == null) {
                        writeln(Msg.FAILURE_CANNOT_LOG_IN.out());
                    }
                    state = -1;
                    break;
                }
                case 3: {
                    displayTitle("Signup");
                    String username = enter("username");
                    String password = enter("password");
                    Msg outcome = userController.addUser(username, password, false);
                    writeln(outcome.out());
                    user = userController.login(username, password);
                    state = -1;
                }
            }
        }
    }

    private void loggedInMenu() {
        while (true) {
            displayTitle("Menu");
            writeln("Choose from the following commands:");
            writeln("0) Log Out");
            writeln("1) Play Game");
            writeln("2) View Activity");
            writeln("3) AddUser");
            if (user.isAdmin()) {
                writeln("4) AddAdminUser (Admin only)");
                writeln("5) DeleteUser (Admin only)");
                writeln("6) BanUser (Admin only)");
                writeln("7) UnbanUser (Admin only)");
            }
            int input = getNumUpTo(user.isAdmin() ? 7 : 3);

            switch (input) {
                case 0:
                    return;
                case 1:
                    playGame();
                    break;
                case 2:
                    log();
                    break;
                case 3:
                    addUser(false);
                    break;
                case 4:
                    addUser(true);
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    banUser();
                    break;
                case 7:
                    unbanUser();
            }
        }
    }

    private void playGame() {
        userController.logLaunch(user.getUserid());
        DesktopLauncher.main(new String[]{});
    }

    private void log() {
        displayTitle("Activity Log");
        String outcome = userController.getLogsOf(user.getUserid());
        writeln(outcome);
    }

    private void addUser(boolean admin) {
        if (admin) displayTitle("Add admin user");
        else displayTitle("Add user");
        Msg outcome = userController.addUser(enter("username"), enter("password"), admin);
        writeln(outcome.out());
    }

    private void deleteUser() {
        displayTitle("Permanently delete user");
        Msg outcome = userController.deleteUser(enter("username"));
        writeln(outcome.out());
    }

    private void banUser() {
        displayTitle("Ban user");
        Msg outcome = userController.banUser(enter("username"));
        writeln(outcome.out());
    }

    private void unbanUser() {
        displayTitle("Unban user");
        Msg outcome = userController.unbanUser(enter("username"));
        writeln(outcome.out());

    }

    private String enter(String what) {
        String str;
        do {
            writeln("Enter " + what + ": ");
            str = in.nextLine();
        }
        while ((str == null || str.length() < MIN) && tooShort());
        return str;
    }

    private boolean tooShort() {
        writeln("Too short! Must be at least " + MIN + " characters.");
        return true;
    }

    private int getNumUpTo(int max) {
        int num;

        while (true) {
            writeln("Please enter a number between 0 and " + max + ": ");
            String input = in.nextLine();

            try {
                num = Integer.parseInt(input);
                if (num > max) {
                    writeln("Invalid input -- number too large.");
                } else if (num < 0) {
                    writeln("Invalid input -- number can't be negative.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                writeln("Invalid input -- not a number.");
            }
        }
        return num;
    }


    private void writeln(Object obj) {
        System.out.println(obj);
    }

    private void writeSeparator() {
        writeln("======================================================================");
    }

    private void displayTitle(String text) {
        writeln("");
        writeln(text);
        writeSeparator();
    }

}
