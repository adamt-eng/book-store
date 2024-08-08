package models;

import java.util.ArrayList;
import services.BookService;
import utils.*;

public abstract class User implements BookService
{
    protected String username, email, password;

    public User(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
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
            String[] userDetails = user.split(",");
            if (userDetails[1].equals(username) && userDetails[2].equals(password))
            {
                OutputPrinter.clearTerminal();
                OutputPrinter.printWithColor("Login successful!\n", "32m");

                if (accountType == "admin")
                {
                    return new Admin(userDetails[1], userDetails[0], userDetails[2]);
                }
                else
                {
                    return new Reader(userDetails[1], userDetails[0], userDetails[2], userDetails[3], userDetails[4],
                                    userDetails[5]);
                }
            }
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Incorrect username or password!\n", "31m");

        return null;
    }

    public void searchBook(boolean invalid)
    {
        if (!invalid)
        {
            OutputPrinter.clearTerminal();
        }

        OutputPrinter.printWithColor("Search for book using it's name:", "94m");
        
        String query = InputReader.getStringInput();

        Book book = null;

        for (String line : FileManager.readFile(Constants.BOOKS_FILE_PATH))
        {
            if (line.contains(query))
            {
                OutputPrinter.clearTerminal();

                String[] bookDetails = line.split(",");
                int stock = Integer.parseInt(bookDetails[3]);

                if (stock != 0 || this instanceof Admin)
                {
                    OutputPrinter.printWithColor("Book Details\n", "94m");

                    book = new Book(bookDetails[0], bookDetails[1], Double.parseDouble(bookDetails[2]), stock,
                                    bookDetails[4]);

                    System.out.println("Name: " + bookDetails[0]);
                    System.out.println("Author: " + bookDetails[1]);
                    System.out.println("Price: " + bookDetails[2]);
                    System.out.println("Stock: " + stock);
                    System.out.println("Category: " + bookDetails[4]);

                    break;
                }
            }
        }

        if (book != null)
        {
            book.showBookActions(this);
        }
        else
        {
            OutputPrinter.printWithColor("Book is unavailable!\n", "31m");
            this.searchBook(true);
        }
    }

    public void displayBooks(boolean invalid)
    {
        if (!invalid)
        {
            OutputPrinter.clearTerminal();
        }

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

            if (this instanceof Admin || !book.contains(",0,"))
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
                if (this instanceof Admin admin)
                {
                    Menu.showAdminFunctions(admin);
                }
                else if (this instanceof Reader reader)
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
                    String[] bookDetails = books.get(choice).split(",");
                    new Book(bookDetails[0], bookDetails[1], Double.parseDouble(bookDetails[2]), Integer.parseInt(
                                    bookDetails[3]), bookDetails[4]).showBookActions(this);
                }
                else
                {
                    OutputPrinter.invalidChoice();
                    displayBooks(true);
                }
                break;
            }
            default: {
                OutputPrinter.invalidChoice();
                displayBooks(true);
                break;
            }
        }
    }
}
