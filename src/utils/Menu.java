package utils;

import java.util.*;
import models.*;

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
                case 1: {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Login\n", "96m");

                    System.out.print("Username: ");
                    String username = inputManager.getStringInput();

                    System.out.print("Password: ");
                    String password = inputManager.getStringInput();

                    String loginResult = User.login(username, password, "admin");
                    if (loginResult != "N/A")
                    {
                        OutputManager.clearTerminal();
                        OutputManager.printWithColor("Login successful!\n", "32m");

                        Admin admin = new Admin(username, loginResult.split(",")[0], password);

                        while (true)
                        {
                            System.out.println("[1] Add Book");
                            System.out.println("[2] Search For A Book");
                            System.out.println("[3] Display All Books");
                            System.out.println("[0] Logout");
                            System.out.println("\nEnter your choice: ");

                            switch (inputManager.getIntInput())
                            {
                                case 1:
                                    admin.addBook();
                                    break;
                                case 2:
                                    admin.searchBook();
                                    break;
                                case 3:
                                    admin.displayBooks();
                                    break;
                                case 0:
                                    admin = null;
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
                case 1: {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Login\n", "96m");

                    System.out.print("Username: ");
                    String username = inputManager.getStringInput();

                    System.out.print("Password: ");
                    String password = inputManager.getStringInput();

                    String loginResult = User.login(username, password, "reader");
                    if (loginResult != "N/A")
                    {
                        OutputManager.clearTerminal();
                        OutputManager.printWithColor("Login successful!\n", "32m");

                        String data[] = loginResult.split(",");
                        String email = data[0];
                        String phoneNumber = data[3];
                        String address = data[4];
                        String paymentMethod = data[5];

                        Reader reader = new Reader(username, email, password, phoneNumber, address,
                                        paymentMethod);

                        while (true)
                        {
                            System.out.println("[1] Edit Account Information");
                            System.out.println("[2] Search For A Book");
                            System.out.println("[3] Display All Books");
                            System.out.println("[0] Logout");
                            System.out.println("\nEnter your choice: ");

                            switch (inputManager.getIntInput())
                            {
                                case 1:
                                    reader.editInformation();
                                    break;
                                case 2:
                                    reader.searchBook();
                                    break;
                                case 3:
                                    reader.displayBooks();
                                    break;
                                case 0:
                                    reader = null;
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
                }
                case 2: {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Register\n", "96m");

                    System.out.print("Username: ");
                    String username = inputManager.getStringInput();

                    System.out.print("Email: ");
                    String email = inputManager.getStringInput();

                    System.out.print("Password: ");
                    String password = inputManager.getStringInput();

                    System.out.print("Phone Number: ");
                    String phoneNumber = inputManager.getStringInput();

                    System.out.print("Address: ");
                    String address = inputManager.getStringInput();

                    System.out.print("Payment Method: ");
                    String paymentMethod = inputManager.getStringInput();

                    Reader reader = new Reader(username, email, password, phoneNumber, address,
                                    paymentMethod);
                    reader.register();

                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Registration successful!\n", "32m");
                }
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
