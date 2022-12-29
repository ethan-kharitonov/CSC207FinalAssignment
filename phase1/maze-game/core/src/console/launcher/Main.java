package console.launcher;

import console.interfaces.AppUI;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[]args){
        AppUI appUI = new AppUI();
        appUI.run();
    }
}


