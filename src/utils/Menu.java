package utils;

import models.*;

public class Menu
{
    public static void showMainMenu()
    {
        System.out.println("[1] Admin");
        System.out.println("[2] Reader");
        System.out.println("[0] Exit");
        System.out.println("\nEnter your choice: ");

        switch (new InputManager().getIntInput())
        {
            case 1:
                showAdminMenu(false);
                break;
            case 2:
                showReaderMenu(false);
                break;
            case 0:
                OutputManager.exit();
                break;
            default:
                OutputManager.invalidChoice();
                showMainMenu();
                break;
        }
    }

    public static void showAdminMenu(boolean invalid)
    {
        if (!invalid)
            OutputManager.clearTerminal();
            
            OutputManager.printWithColor("Admin\n", "96m");
        System.out.println("[1] Login");
        System.out.println("[0] Return to menu");
        System.out.println("\nEnter your choice: ");

        InputManager inputManager = new InputManager();

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

                    showAdminFunctions(admin);
                }
                else
                {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Incorrect username or password!\n", "31m");
                    showAdminMenu(true);
                }
            }
                break;
            case 0:
                OutputManager.clearTerminal();
                Menu.showMainMenu();
            default:
                OutputManager.invalidChoice();
                showAdminMenu(true);
                break;
        }
    }

    public static void showAdminFunctions(Admin admin)
    {
        System.out.println("[1] Add Book");
        System.out.println("[2] Search For A Book");
        System.out.println("[3] Display All Books");
        System.out.println("[0] Logout");
        System.out.println("\nEnter your choice: ");

        switch (new InputManager().getIntInput())
        {
            case 1:
                // admin.addBook();
                break;
            case 2:
                admin.searchBook(admin, false);
                break;
            case 3:
                admin.displayBooks();
                break;
            case 0:
                admin = null;
                OutputManager.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputManager.invalidChoice();
                break;
        }
    }

    public static void showReaderFunctions(Reader reader)
    {
        System.out.println("[1] Edit Account Information");
        System.out.println("[2] Search For A Book");
        System.out.println("[3] Display All Books");
        System.out.println("[0] Logout");
        System.out.println("\nEnter your choice: ");

        switch (new InputManager().getIntInput())
        {
            case 1:
                reader.editInformation(false);
                break;
            case 2:
                reader.searchBook(reader, false);
                break;
            case 3:
                reader.displayBooks();
                break;
            case 0:
                reader = null;
                OutputManager.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputManager.invalidChoice();
                showReaderFunctions(reader);
                break;
        }
    }

    public static void showReaderMenu(boolean invalid)
    {
        InputManager inputManager = new InputManager();

        if (!invalid)
            OutputManager.clearTerminal();

            OutputManager.printWithColor("Reader\n", "96m");
            System.out.println("[1] Login");
        System.out.println("[2] Register");
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

                    showReaderFunctions(reader);

                }
                else
                {
                    OutputManager.clearTerminal();
                    OutputManager.printWithColor("Incorrect username or password!\n", "31m");
                    showReaderMenu(true);
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
                OutputManager.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputManager.invalidChoice();
                showReaderMenu(true);
                break;
        }
    }
}
