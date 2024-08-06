package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService {
    private String phoneNumber, address, paymentMethod;
    private ArrayList<Book> shoppingCart = new ArrayList<>();

    public int getShoppingCartSize() {
        return shoppingCart.size();
    }

    public Reader(String username, String email, String password, String phoneNumber,
            String address, String paymentMethod) {
        super(username, email, password);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setPaymentMethod(paymentMethod);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void editInformation(boolean invalid) {
        if (!invalid)
            OutputPrinter.clearTerminal();

        ArrayList<String> readersinfo = FileManager.readFile(Constants.READERS_FILE_PATH);
        int index = -1;

        for (int i = 0; i < readersinfo.size(); i++) {
            if (readersinfo.get(i).contains(email)) {
                index = i;
                break;
            }
        }

        OutputPrinter.printWithColor("Account Details\n", "94m");

        System.out.println("[0] Email: " + email);
        System.out.println("[1] Username: " + username);
        System.out.println("[2] Password: " + password);
        System.out.println("[3] Phone Number: " + phoneNumber);
        System.out.println("[4] Address: " + address);
        System.out.println("[5] Payment Method: " + paymentMethod);

        char check = 'Y';
        while (check == 'Y' || check == 'y') {
            OutputPrinter.printWithColor("\nWhich information would you like to edit? (1/2/3/4/5):", "94m");

            switch (InputReader.getIntInput()) {
                case 0:
                    OutputPrinter.clearTerminal();
                    OutputPrinter.printWithColor("Email cannot be edited!", "31m");
                    editInformation(true);
                    return;
                case 1:
                    System.out.println("Enter the new username: ");
                    this.username = InputReader.getStringInput();
                    break;
                case 2:
                    System.out.println("Enter the new password: ");
                    this.password = InputReader.getStringInput();
                    break;
                case 3:
                    System.out.println("Enter the new phone number: ");
                    this.phoneNumber = InputReader.getStringInput();
                    break;
                case 4:
                    System.out.println("Enter the new address: ");
                    this.address = InputReader.getStringInput();
                    break;
                case 5:
                    System.out.println("Enter the new payment method: ");
                    this.paymentMethod = InputReader.getStringInput();
            }
            OutputPrinter.printWithColor("\nDo you want to edit another information? (Y/N):", "94m");
            check = InputReader.getStringInput().charAt(0);
        }

        if (check == 'N' || check == 'n') {
            readersinfo.set(index, email + "," + username + "," + password + "," + phoneNumber + "," + address +
                    "," +
                    paymentMethod);

            FileManager.writeFile(Constants.READERS_FILE_PATH, readersinfo);
        } else {
            OutputPrinter.invalidChoice();
            editInformation(true);
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Your information has been successfully updated!\n", "32m");
        Menu.showReaderFunctions(this);
    }

    public void orderBook(String bookName) {
        ArrayList<String> updatedfile = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        String[] mybookdetails = new String[5];
        int i;

        for (i = 0; i < FileManager.readFile(Constants.BOOKS_FILE_PATH).size(); i++) {
            if (FileManager.readFile(Constants.BOOKS_FILE_PATH).get(i).contains(bookName)) {
                break;
            }
        }

        mybookdetails = FileManager.readFile(Constants.BOOKS_FILE_PATH).get(i).split(",");

        shoppingCart.add(new Book(mybookdetails[0], mybookdetails[1], Double.parseDouble(mybookdetails[2]),
                Integer.parseInt(mybookdetails[3]), mybookdetails[4]));

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book added to your shopping cart!\n", "32m");
        updatedfile.set(i,
                (mybookdetails[0] + "," + mybookdetails[1] + "," + mybookdetails[2] + ","
                        + (Integer.parseInt(mybookdetails[3].trim()) - 1) + "," +
                        mybookdetails[4]));
        FileManager.writeFile(Constants.BOOKS_FILE_PATH, updatedfile);

        Menu.showReaderFunctions(this);
    }

    public void showReceipt() {
        double total = 0;

        for (Book book : shoppingCart) {
            total += book.getPrice();
        }

        // Print table header
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-50s | %-40s | %-20s | %-10s |%n", "Book ID", "Book Name", "Book Author",
                "Book Category", "Book Price");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");

        // Print table rows
        for (int i = 0; i < shoppingCart.size(); i++) {
            Book book = shoppingCart.get(i);

            System.out.printf("| %-8d | %-50s | %-40s | %-20s | %-10s |%n",
                    i, book.getName(), book.getAuthor(), book.getCategory(),
                    book.getPrice());
        }

        // Print table footer
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-127s | $%-10.2f|%n", "Total Payment", total); // Format total payment to 2 decimal places
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
        char choice = 'y';
        boolean f1 = true;

        System.out.println("Do you want to remove any book from your cart? (Y/N): ");
        choice = InputReader.getStringInput().charAt(0);
        if (choice == 'y' || choice == 'Y') {
            System.out.println("Please select a book using it's index: ");
            removeFromCart(shoppingCart.get(InputReader.getStringInput().charAt(0)));
        }

        while (f1) {
            System.out.println("This is your current payment method: " + this.paymentMethod);
            OutputPrinter.printWithColor("Do you want to change it? (Y/N):", "94m");

            choice = InputReader.getStringInput().charAt(0);

            if (choice == 'y' || choice == 'Y') {
                System.out.println("Enter the new payment method: ");
                this.paymentMethod = InputReader.getStringInput();
            } else if (choice == 'n' || choice == 'N') {
                f1 = false;
            }
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Books have been purchased successfully!", "32m");
        Menu.showReaderFunctions(this);
    }

    public void removeFromCart(Book bookToRemove) {
        ArrayList<String> allbooks = new ArrayList<>();
        allbooks = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        int i;
        for (i = 0; i < allbooks.size(); i++) {
            if (allbooks.get(i).contains(bookToRemove.getName()))
                ;
            {
                allbooks.remove(i);
                bookToRemove.setStock(bookToRemove.getStock() - 1);
                break;
            }
        }
        allbooks.set(i, bookToRemove.getName() + "," + bookToRemove.getAuthor() + "," + bookToRemove.getPrice() + ","
                + bookToRemove.getStock() + "," + bookToRemove.getCategory());
        FileManager.writeFile(Constants.BOOKS_FILE_PATH, allbooks);
        for (Book book : shoppingCart) {
            if (book == bookToRemove) {
                shoppingCart.remove(bookToRemove);
            }
        }

        OutputPrinter.printWithColor("Book removed from cart successfully!", "32m");
        Menu.showReaderFunctions(this);
    }

    public static void register() {
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Register\n", "96m");

        System.out.print("Username: ");
        String username = InputReader.getStringInput();

        for (String user : FileManager.readFile(Constants.READERS_FILE_PATH)) {
            if (user.contains(username)) {
                OutputPrinter.clearTerminal();
                OutputPrinter.printWithColor("An account already exists with this username!\n", "31m");
                Menu.showReaderMenu(true);
            }
        }

        System.out.print("Email: ");
        String email = InputReader.getStringInput();

        System.out.print("Password: ");
        String password = InputReader.getStringInput();

        System.out.print("Phone Number: ");
        String phoneNumber = InputReader.getStringInput();

        System.out.print("Address: ");
        String address = InputReader.getStringInput();

        System.out.print("Payment Method: ");
        String paymentMethod = InputReader.getStringInput();

        FileManager.appendFile(Constants.READERS_FILE_PATH, email + "," + username + "," +
                password + "," + phoneNumber + "," + address + "," + paymentMethod);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Registration successful!\n", "32m");
    }
}
