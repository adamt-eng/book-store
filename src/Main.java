
import java.util.ArrayList;

import models.Reader;
import utils.Menu;
import utils.OutputManager;

public class Main {
    public static void main(String[] args) {
        OutputManager.printWithColor("Welcome to our Book Store!\n", "96m");
        Menu.showMainMenu();
    }
}
