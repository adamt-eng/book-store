package services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.Book;
import utils.FileManager;
import utils.OutputManager;
import main.Main;

public class AdminService
{

    private static final String BOOKS_FILE_PATH = "resources/books.txt";

    public void addBook(Book book)
    {
        List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
        books.add(book.toString()); // You can format this string as needed
        FileManager.writeFile(BOOKS_FILE_PATH, books);
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

    public static boolean Login(String email, String password)
    {
        return true;
    }

    public static void showAdminMenu()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            OutputManager.clearTerminal();

            while (true)
            {
                System.out.println("[1] Login");
                System.out.println("[0] Return to menu");
                System.out.println("\nEnter your choice: ");

                int choice = 0;
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
                    case 1:
                        if (Login("", ""))
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
                    case 0:
                        OutputManager.clearTerminal();
                        Main.showMainMenu();
                        continue;
                    default:
                        OutputManager.invalidChoice();
                        break;
                }
            }
        }
    }
}
