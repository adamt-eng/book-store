package models;

import java.util.ArrayList;
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
        for (String user : FileManager.readFile(accountType == "admin" ? Constants.ADMINS_FILE_PATH
                        : Constants.READERS_FILE_PATH))
        {
            if (user.contains(username + "," + password))
            {
                return user;
            }
        }

        return "N/A";
    }

    public void searchBook(User user, boolean invalid)
    {
        if (!invalid)
            OutputManager.clearTerminal();
        OutputManager.printWithColor("Search for book using it's name:", "94m");

        var bookName = new InputManager().getStringInput();

        boolean found = false;
        for (String book : FileManager.readFile(Constants.BOOKS_FILE_PATH))
        {
            if (book.contains(bookName))
            {
                found = true;

                OutputManager.clearTerminal();

                String[] data = book.split(",");

                double stock = Double.parseDouble(data[3]);

                if (stock == 0)
                {
                    OutputManager.printWithColor("Book is unavailable!", "31m");
                }
                else
                {
                    OutputManager.printWithColor("Book Details\n", "94m");

                    System.out.println("Name: " + data[0]);
                    System.out.println("Author: " + data[1]);
                    System.out.println("Price: " + data[2]);
                    System.out.println("Stock: " + stock);
                    System.out.println("Category: " + data[4]);
                }

                break;
            }
        }

        Admin admin = null;
        Reader reader = null;

        if (user instanceof Admin)
        {
            admin = (Admin) user;
        }
        else
        {
            reader = (Reader) user;
        }

        if (found)
        {
            if (admin != null)
            {
                System.out.println("\n[1] Delete Book");
                System.out.println("[2] Edit Book");
                System.out.println("[0] Return To Previous Menu");

                switch (new InputManager().getIntInput())
                {
                    case 1:
                        admin.deleteBook(bookName);
                        break;
                    case 2:
                        admin.editBook(bookName);
                        break;
                    case 0:
                        Menu.showAdminFunctions(admin);
                        break;
                }
            }
            else
            {

                System.out.println("\n[1] Order Book");
                System.out.println("[0] Return To Previous Menu");

                switch (new InputManager().getIntInput())
                {
                    case 1:
                        reader.orderBook(bookName);
                        break;
                    case 0:
                        OutputManager.clearTerminal();
                        Menu.showReaderFunctions(reader);
                        break;
                }
            }
        }
        else
        {
            OutputManager.printWithColor("Book is unavailable!", "31m");
            user.searchBook(user, true);
        }
    }

    public void displayBooks(String accountType)
    {
        OutputManager.clearTerminal();

        String[] headers = { "Book Name", "Book Author", "Book Price", "Book Stock", "Book Category" };
        int[] columnWidths = { 50, 20, 10, 10, 15 };

        // Print the table header
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // Iterate through the book array and print details

        ArrayList<String> books = FileManager.readFile(Constants.BOOKS_FILE_PATH);

        for (int i = 0; i < books.size(); i++)
        {
            String book = books.get(i);

            if (accountType == "admin" || !book.contains(", 0,"))
            {
                printRow(book.split(","), columnWidths);
            }
        }
    }

    private static void printRow(String[] row, int[] columnWidths)
    {
        for (int i = 0; i < row.length; i++)
        {
            System.out.printf("| %-" + columnWidths[i] + "s ", row[i]);
        }
        System.out.println("|");
    }

    private static void printSeparator(int[] columnWidths)
    {
        for (int width : columnWidths)
        {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++)
            { // +2 for padding
                System.out.print("-");
            }
        }
        System.out.println("+");
    }
}
