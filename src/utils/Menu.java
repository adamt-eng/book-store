package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import models.Reader;
import models.User;

public class Menu
{
    public static void showMainMenu()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            while (true)
            {
                System.out.println("[1] Admin");
                System.out.println("[2] Reader");
                System.out.println("[0] Exit");
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
                        showAdminMenu();
                        break;
                    case 2:
                        showReaderMenu();
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
                    if (User.Login("admin"))
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
                    if (User.Login("reader"))
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
                Reader reader = new Reader();
                reader.register();
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
