package models;

import java.util.ArrayList;
import services.BookService;
import utils.*;

public abstract class User implements BookService
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

    private void showBookActions(Admin admin, Reader reader, String bookName)
    {
        if (admin != null)
        {
            System.out.println("\n[1] Delete Book");
            System.out.println("[2] Edit Book");
            System.out.println("[0] Return To Previous Menu");

            switch (InputReader.getIntInput())
            {
                case 1:
                    admin.deleteBook(bookName);
                    break;
                case 2:
                    admin.editBook(bookName);
                    break;
                case 0:
                    OutputPrinter.clearTerminal();
                    Menu.showAdminFunctions(admin);
                    break;
            }
        }
        else
        {
            System.out.println("\n[1] Order Book");
            System.out.println("[0] Return To Previous Menu");

            switch (InputReader.getIntInput())
            {
                case 1:
                    reader.orderBook(bookName);
                    break;
                case 0:
                    OutputPrinter.clearTerminal();
                    Menu.showReaderFunctions(reader);
                    break;
            }
        }
    }

    public static User login(String accountType)
    {
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Login\n", "96m");

        System.out.print("Username: ");
        String username = InputReader.getStringInput();

        System.out.print("Password: ");
        String password = InputReader.getStringInput();

        for (String user : FileManager.readFile(accountType == "admin" ? Constants.ADMINS_FILE_PATH
                        : Constants.READERS_FILE_PATH))
        {
            String[] userData = user.split(",");
            if (userData[1].equals(username) && userData[2].equals(password))
            {
                OutputPrinter.clearTerminal();
                OutputPrinter.printWithColor("Login successful!\n", "32m");

                if (accountType == "admin")
                {
                    return new Admin(userData[1], userData[0], userData[2]);
                }
                else
                {
                    return new Reader(userData[1], userData[0], userData[2], userData[3], userData[4], userData[5]);
                }
            }
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Incorrect username or password!\n", "31m");

        return null;
    }

    public void searchBook(User user, boolean invalid)
    {
        if (!invalid)
            OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Search for book using it's name:", "94m");

        var bookName = InputReader.getStringInput();

        boolean found = false;
        for (String book : FileManager.readFile(Constants.BOOKS_FILE_PATH))
        {
            if (book.contains(bookName))
            {
                found = true;

                OutputPrinter.clearTerminal();

                String[] data = book.split(",");

                int stock = Integer.parseInt(data[3]);

                if (stock == 0)
                {
                    found = false;
                }
                else
                {
                    OutputPrinter.printWithColor("Book Details\n", "94m");

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
            showBookActions(admin, reader, bookName);
        }
        else
        {
            OutputPrinter.printWithColor("Book is unavailable!\n", "31m");
            user.searchBook(user, true);
        }
    }

    public void displayBooks(User user, boolean invalid)
    {
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

        if (!invalid)
            OutputPrinter.clearTerminal();

        String[] headers = { "Book ID", "Book Name", "Book Author", "Book Price (USD)", "Book Stock", "Book Category" };
        int[] columnWidths = { 10, 40, 30, 20, 10, 30 };

        for (int i = 0; i < headers.length; i++)
        {
            System.out.printf("| %-" + columnWidths[i] + "s ", headers[i]);
        }
        System.out.println("|");

        for (int width : columnWidths)
        {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++)
            {
                System.out.print("-");
            }
        }
        System.out.println("+");

        ArrayList<String> books = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < books.size(); i++)
        {
            String book = i + "," + books.get(i);

            if (admin != null || !book.contains(",0,"))
            {
                indices.add(i);
                String[] bookDetails = book.split(",");
                for (int j = 0; j < bookDetails.length; j++)
                {
                    System.out.printf("| %-" + columnWidths[j] + "s ", bookDetails[j]);
                }
                System.out.println("|");
            }
        }

        System.out.println("\n[0] Return to previous menu");
        System.out.println("[1] Show Book Actions");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput())
        {
            case 0: {
                OutputPrinter.clearTerminal();
                if (admin != null)
                {
                    Menu.showAdminFunctions(admin);
                }
                else
                {
                    Menu.showReaderFunctions(reader);
                }
                break;
            }
            case 1: {
                OutputPrinter.printWithColor("\nSelect book using its index to show actions:", "94m");
                int choice = InputReader.getIntInput();
                if (indices.contains(choice))
                {
                    showBookActions(admin, reader, books.get(choice).split(",")[0]);
                }
                else
                {
                    OutputPrinter.invalidChoice();
                    displayBooks(user, true);
                }
                break;
            }
            default: {
                OutputPrinter.invalidChoice();
                displayBooks(user, true);
                break;
            }
        }
    }
}
