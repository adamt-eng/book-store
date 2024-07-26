package services;

import java.util.List;
import models.Book;
import utils.*;

public class AdminService extends UserService
{
    public void addBook(Book book)
    {
        FileManager.writeFile(BOOKS_FILE_PATH, book.toString());
    }

    public void editBook(Book book)
    {
        // Implement edit logic
    }

    public void deleteBook(String bookName)
    {
        // Implement delete logic
    }

    public List<String> displayAllBooks()
    {
        return FileManager.readFile(BOOKS_FILE_PATH);
    }

    public Book searchBook(String bookName)
    {
        // Implement search logic
        return null;
    }

    public static void showAdminMenu()
    {
        InputManager inputManager = new InputManager();

        OutputManager.clearTerminal();

        while (true)
        {
            System.out.println("[1] Login");
            System.out.println("[0] Return to menu");
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
                            System.out.println("[1] Add Book");
                            System.out.println("[2] Edit Book");
                            System.out.println("[3] Delete Book");
                            System.out.println("[4] Display All Books");
                            System.out.println("[5] Search For A Book");
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
                case 0:
                    OutputManager.clearTerminal();
                    Menu.showMainMenu();
                    continue;
                default:
                    OutputManager.invalidChoice();
                    break;
            }
        }
    }
}