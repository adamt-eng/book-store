package models;

import utils.*;

public abstract class User
{
    protected String username, email, password;
    protected static final String READERS_FILE_PATH = "resources/readers.txt";
    protected static final String ADMINS_FILE_PATH = "resources/admins.txt";
    protected static final String BOOKS_FILE_PATH = "resources/books.txt";

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

    public static boolean Login(String accountType)
    {
        InputManager inputManager = new InputManager();

        OutputManager.clearTerminal();
        OutputManager.printWithColor("Login\n", "96m");

        System.out.print("Email: ");
        String email_ = inputManager.getStringInput();

        System.out.print("Password: ");
        String password_ = inputManager.getStringInput();

        for (String user : FileManager.readFile(accountType == "admin" ? ADMINS_FILE_PATH : READERS_FILE_PATH))
        {
            if (user.contains(email_ + "," + password_))
            {
                return true;
            }
        }

        return false;
    }
}
