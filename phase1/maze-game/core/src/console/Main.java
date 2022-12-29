package console;

import console.interfaces.AppUI;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[]args){
        AppUI appUI = new AppUI();
        try {
            appUI.run();
        } catch (ParseException | IOException e) {
            System.out.println("Exception caught:, " + e.getMessage());
        }
    }
}


