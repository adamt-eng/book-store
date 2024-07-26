package services;

import java.util.List;
import utils.*;

public class UserService
{
    protected static final String USERS_FILE_PATH = "resources/users.txt";
    protected static final String BOOKS_FILE_PATH = "resources/books.txt";

    public static boolean Login()
    {
        InputManager inputManager = new InputManager();

        OutputManager.clearTerminal();

        System.out.println("Email: ");
        String email_ = inputManager.getStringInput();

        System.out.println("Password: ");
        String password_ = inputManager.getStringInput();

        List<String> users = FileManager.readFile(USERS_FILE_PATH);
        for (String user : users)
        {
            // Assuming en el data hatkoon comma seperated
            if (user.contains(email_ + "," + password_))
            {
                return true;
            }
        }

        return false;
    }
}
