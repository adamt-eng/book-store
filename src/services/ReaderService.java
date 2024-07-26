package services;

import models.*;
import utils.*;
import java.util.List;

public class ReaderService extends UserService
{
    public void registerReader(Reader reader)
    {
        FileManager.writeFile(USERS_FILE_PATH, reader.toString());
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

    public static void showReaderMenu()
    {
        InputManager inputManager = new InputManager();

        OutputManager.clearTerminal();

        while (true)
        {
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[0] Exit");
            System.out.println("\nEnter your choice: ");

            switch (inputManager.getIntInput())
            {
                case 1:
                    if (Login())
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

                            switch (inputManager.getIntInput())
                            {
                                case 0:
                                    // Logout and dispose of all logged in user data
                                    OutputManager.clearTerminal();
                                    Menu.showMainMenu();
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