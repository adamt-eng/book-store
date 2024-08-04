package models;

import utils.*;

public abstract class User
{
    protected String username, email, password;

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
    
    public static String login(String username, String password, String accountType)
    {
        for (String user : FileManager.readFile(accountType == "admin" ? Constants.ADMINS_FILE_PATH : Constants.READERS_FILE_PATH))
        {
            if (user.contains(username + "," + password))
            {
                return user;
            }
        }

        return "N/A";
    }
}
