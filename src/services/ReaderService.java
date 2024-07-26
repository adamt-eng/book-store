package services;

import models.Book;
import models.Reader;
import utils.FileManager;
import utils.OutputManager;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import main.Main;

public class ReaderService
{

    private static final String USERS_FILE_PATH = "resources/users.txt";
    private static final String BOOKS_FILE_PATH = "resources/books.txt";

    public void registerReader(Reader reader)
    {
        List<String> users = FileManager.readFile(USERS_FILE_PATH);
        users.add(reader.toString()); // You can format this string as needed
        FileManager.writeFile(USERS_FILE_PATH, users);
    }

    public void editReader(Reader reader)
    {
        // Implement edit logic
    }

    public List<String> displayAvailableBooks()
    {
        List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
        // Filter books to show only available ones
        return books;
    }

    public Book searchBook(String bookName)
    {
        // Implement search logic
        return null;
    }

    public void orderBook(String bookName)
    {
        // Implement order logic
    }

    public static boolean Login(String email, String password)
    {
        return true;
    }

    public static void showReaderMenu()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            OutputManager.clearTerminal();

            while (true)
            {
                System.out.println("[1] Login");
                System.out.println("[2] Register");
                System.out.println("[0] Exit");
                System.out.println("\nEnter your choice: ");

                int choice = 0;
                try
                {
                    choice = scanner.nextInt();
                }
                catch (InputMismatchException e)
                {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Invalid choice!\n", "31m");
                    scanner.next();
                    continue;
                }

                switch (choice)
                {
                    case 1:
                        if (Login("", ""))
                        {
                            OutputManager.clearTerminal();
                            OutputManager.printWithColor("Login successful!\n", "32m");

                            while (true)
                            {
                                System.out.println("[1] Edit Account Information");
                                System.out.println("[2] Display Available Books");
                                System.out.println("[3] Order Book");
                                System.out.println("[4] Search For A Book");
                                System.out.println("[0] Logout");
                                System.out.println("\nEnter your choice: ");

                                try
                                {
                                    choice = scanner.nextInt();
                                }
                                catch (InputMismatchException e)
                                {
                                    OutputManager.invalidChoice();
                                    scanner.next();
                                    continue;
                                }

                                switch (choice)
                                {
                                    case 0:
                                        // Logout and dispose of all logged in user data
                                        OutputManager.clearTerminal();
                                        Main.showMainMenu();
                                        continue;
                                    default:
                                        OutputManager.invalidChoice();
                                        break;
                                }
                            }
                        }
                        break;
                    case 2:
                        // Register
                        break;
                    case 0:
                        OutputManager.exit();
                        break;
                    default:
                        OutputManager.invalidChoice();
                        break;
                }
            }
        }
    }
}
