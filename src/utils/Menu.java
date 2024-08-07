package utils;

import models.*;

public class Menu {
    public static void showMainMenu() {
        System.out.println("[1] Admin");
        System.out.println("[2] Reader");
        System.out.println("[0] Exit");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput()) {
            case 1:
                showAdminMenu(false);
                break;
            case 2:
                showReaderMenu(false);
                break;
            case 0:
                OutputPrinter.exit();
                break;
            default:
                OutputPrinter.invalidChoice();
                showMainMenu();
                break;
        }
    }

    private static void showAdminMenu(boolean invalid) {
        if (!invalid)
            OutputPrinter.clearTerminal();

        OutputPrinter.printWithColor("Admin\n", "96m");
        System.out.println("[1] Login");
        System.out.println("[0] Return to menu");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput()) {
            case 1: {
                Admin admin = (Admin) User.login("admin");
                if (admin != null) {
                    showAdminFunctions(admin);
                } else {
                    showAdminMenu(true);
                }
            }
                break;
            case 0:
                OutputPrinter.clearTerminal();
                Menu.showMainMenu();
            default:
                OutputPrinter.invalidChoice();
                showAdminMenu(true);
                break;
        }
    }

    public static void showAdminFunctions(Admin admin) {
        System.out.println("[1] Add Book");
        System.out.println("[2] Search For A Book");
        System.out.println("[3] Display All Books");
        System.out.println("[0] Logout");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput()) {
            case 1:
                admin.addBook();
                break;
            case 2:
                admin.searchBook(admin, false);
                break;
            case 3:
                admin.displayBooks(admin, false);
                break;
            case 0:
                admin = null;
                OutputPrinter.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputPrinter.invalidChoice();
                break;
        }
    }

    public static void showReaderFunctions(Reader reader) {
        System.out.println("[1] Edit Account Information");
        System.out.println("[2] Show Previous Orders");
        System.out.println("[3] Search For A Book");
        System.out.println("[4] Display All Books");
        System.out.println("[5] Show Receipt");
        System.out.println("[0] Logout");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput()) {
            case 1:
                reader.editInformation(false);
                break;
            case 2:
                reader.showPreviousOrders();
                break;
            case 3:
                reader.searchBook(reader, false);
                break;
            case 4:
                reader.displayBooks(reader, false);
                break;
            case 5:
                if (!reader.shoppingCart.isShoppingCartEmpty()) {
                    reader.shoppingCart.displayCart(reader);
                } else {
                    OutputPrinter.clearTerminal();
                    OutputPrinter.printWithColor("Shopping cart is empty!\n", "96m");
                    showReaderFunctions(reader);
                }
                break;
            case 0:
                reader = null;
                OutputPrinter.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputPrinter.invalidChoice();
                showReaderFunctions(reader);
                break;
        }
    }

    public static void showReaderMenu(boolean invalid) {
        if (!invalid)
            OutputPrinter.clearTerminal();

        OutputPrinter.printWithColor("Reader\n", "96m");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[0] Return to menu");
        System.out.println("\nEnter your choice: ");

        switch (InputReader.getIntInput()) {
            case 1:
                Reader reader = (Reader) User.login("reader");
                if (reader != null) {
                    showReaderFunctions(reader);
                } else {
                    showReaderMenu(true);
                }
                break;
            case 2:
                Reader.register();
                showReaderMenu(true);
                break;
            case 0:
                OutputPrinter.clearTerminal();
                Menu.showMainMenu();
                break;
            default:
                OutputPrinter.invalidChoice();
                showReaderMenu(true);
                break;
        }
    }
}
