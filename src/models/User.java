package models;

import java.util.List;
import utils.FileManager;
import utils.InputManager;
import utils.OutputManager;

public abstract class User
{
    protected String username;
    protected String email;
    protected String password;

    public User(String username, String email, String password)
    {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    protected static final String USERS_FILE_PATH = "resources/users.txt";
    protected static final String BOOKS_FILE_PATH = "resources/books.txt";

    public static boolean Login()
    {
        InputManager inputManager = new InputManager();

        OutputManager.clearTerminal();

        System.out.print("Email: ");
        String email_ = inputManager.getStringInput();

        System.out.print("Password: ");
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
