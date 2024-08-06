package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService {
    private String phoneNumber, address, paymentMethod;
    ArrayList<String> readerbooks = new ArrayList<>();

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
        InputManager inputManager = new InputManager();

        if (!invalid)
            OutputManager.clearTerminal();

        ArrayList<String> readersinfo = FileManager.readFile(Constants.READERS_FILE_PATH);
        int index = -1;

        for (int i = 0; i < readersinfo.size(); i++) {
            if (readersinfo.get(i).contains(email)) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            char check = 'Y';
            OutputManager.printWithColor("Account Details\n", "94m");

            System.out.println("[0] Email: " + email);
            System.out.println("[1] Username: " + username);
            System.out.println("[2] Password: " + password);
            System.out.println("[3] Phone Number: " + phoneNumber);
            System.out.println("[4] Address: " + address);
            System.out.println("[5] Payment Method: " + paymentMethod);
            while (check == 'Y') {
                OutputManager.printWithColor("\nWhich information would you like to edit? (1/2/3/4/5):", "94m");

                switch (inputManager.getIntInput()) {
                    case 0:
                        OutputManager.clearTerminal();
                        OutputManager.printWithColor("Email cannot be edited!", "31m");
                        editInformation(true);
                        return;
                    case 1:
                        System.out.println("Enter the new username: ");
                        this.username = inputManager.getStringInput();
                        break;
                    case 2:
                        System.out.println("Enter the new password: ");
                        this.password = inputManager.getStringInput();
                        break;
                    case 3:
                        System.out.println("Enter the new phone number: ");
                        this.phoneNumber = inputManager.getStringInput();
                        break;
                    case 4:
                        System.out.println("Enter the new address: ");
                        this.address = inputManager.getStringInput();
                        break;
                    case 5:
                        System.out.println("Enter the new payment method: ");
                        this.paymentMethod = inputManager.getStringInput();
                }
                OutputManager.printWithColor("\nDo you want to edit another information? (Y/N):", "94m");
                check = inputManager.getStringInput().charAt(0);
            }

            if (check == 'N' || check == 'n') {
                readersinfo.set(index, email + "," + username + "," + password + "," + phoneNumber + "," + address +
                        "," +
                        paymentMethod);

                FileManager.writeFile(Constants.READERS_FILE_PATH, readersinfo);
            } else {
                OutputManager.invalidChoice();
                editInformation(true);
            }
        } else {
            OutputManager.printWithColor("User not found!", "31m");
        }

        OutputManager.clearTerminal();
        OutputManager.printWithColor("Your information has been successfully updated!\n", "32m");
        Menu.showReaderFunctions(this);
    }

    public void orderBook(String bookName) {
        int bookstock;
        ArrayList<String> apdatedfile = new ArrayList<>();
        apdatedfile = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        String[] mybookdetails = new String[5];
        int i;
        for (i = 0; i < FileManager.readFile(Constants.BOOKS_FILE_PATH).size(); i++) {
            if (FileManager.readFile(Constants.BOOKS_FILE_PATH).get(i).contains(bookName)) {
                break;
            }
        }
        mybookdetails = FileManager.readFile(Constants.BOOKS_FILE_PATH).get(i).split(",");
        bookstock = Integer.parseInt(mybookdetails[3].trim());
        bookstock -= 1;
        readerbooks.add(mybookdetails[0] + "," + mybookdetails[1] + "," + mybookdetails[2] + "," + mybookdetails[4]);
        OutputManager.printWithColor("book adde to your Shopping Cart", "32m");
        apdatedfile.set(i, (mybookdetails[0] + "," + mybookdetails[1] + "," + mybookdetails[2] + "," + bookstock + ","
                + mybookdetails[4]));
        FileManager.writeFile(Constants.BOOKS_FILE_PATH, apdatedfile);
        Menu.showReaderFunctions(null);
    }

    public void showreceipt() {
        InputManager in = new InputManager();
        double totalpay = 0;
        String[][] readerbooksdetails = new String[readerbooks.size()][4];
        for (int i = 0; i < readerbooks.size(); i++) {
            readerbooksdetails[i] = readerbooks.get(i).split(",");
            try {
                totalpay += Double.parseDouble(readerbooksdetails[i][2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing price for book: " + readerbooksdetails[i][0]);
            }
        }
        // Print table header
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-50s | %-40s | %-20s | %-10s |%n", "Book Name", "Book Author", "Book Type", "Book Price");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");

        // Print table rows
        for (int i = 0; i < readerbooks.size(); i++) {
            System.out.printf("| %-50s | %-40s | %-20s | %-10s |%n",
                    readerbooksdetails[i][0], readerbooksdetails[i][1], readerbooksdetails[i][3],
                    readerbooksdetails[i][2]);
        }

        // Print table footer
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-116s | $%-10.2f|%n", "Total Payment", totalpay); // Format total payment to 2 decimal
                                                                                // places
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");
        char choice = 'y';
        boolean f1 = true;
        while (f1) {
            System.out.println("this is your payment method: " + this.paymentMethod);
            OutputManager.printWithColor("do you want to change it(y/n)", "94m");
            choice = in.getStringInput().charAt(0);
            if (choice == 'y' || choice == 'Y') {
                System.out.println("set your new payment method");
                this.paymentMethod = in.getStringInput();
            } else if (choice == 'n' || choice == 'N') {
                f1 = false;
            }
        }
        OutputManager.printWithColor("books have been purchased successfully", "32m");
        Menu.showReaderFunctions(null);
    }

    public void register() {
        FileManager.appendFile(Constants.READERS_FILE_PATH, email + "," + username + "," +
                password + "," + phoneNumber + "," + address + "," + paymentMethod);
    }

}
