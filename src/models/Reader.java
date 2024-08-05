package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService, BookService {
    private String phoneNumber, address, paymentMethod;
    InputManager in = new InputManager();
    FileManager fileforreader = new FileManager();
    ArrayList<Book> mybooks = new ArrayList<>();

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

    public void displayBooks() {
        OutputManager.clearTerminal();
        ArrayList<String> books = new ArrayList<>();
        String delimiter = ",";
        books = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        String[] allbooks = new String[books.size()];
        String[] bookdetails = new String[5];
        for (int i = 0; i < books.size(); i++) {
            allbooks[i] = books.get(i);
        }
        String[] headers = { "Book Name", "Book Author", "Book Price", "Book Stock", "Book Category" };

        // Define the column widths
        int[] columnWidths = { 50, 20, 10, 10, 15 };

        // Print the table header
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // Iterate through the book array and print details
        for (String book : allbooks) {
            bookdetails = book.split(delimiter);
            printRow(bookdetails, columnWidths);
        }
    }

    private static void printRow(String[] row, int[] columnWidths) {
        for (int i = 0; i < row.length; i++) {
            System.out.printf("| %-" + columnWidths[i] + "s ", row[i]);
        }
        System.out.println("|");
    }

    private static void printSeparator(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++) { // +2 for padding
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    public void editInformation() {
        ArrayList<String> readersinfo = new ArrayList<>();
        int choice;
        boolean f = false;
        int index = 0;
        readersinfo = FileManager.readFile(Constants.READERS_FILE_PATH);
        String s = this.email + "," + this.username + "," + this.password + "," + this.phoneNumber + "," + this.address
                + "," + this.paymentMethod;
        System.out.println(s);
        for (int i = 0; i < readersinfo.size(); i++) {
            if (readersinfo.get(i).equals(s)) {
                index = i;
                f = true;
                break;
            }
        }
        if (f) {
            char check = 'y';
            while (check == 'y') {
                System.out.println("which information you would like to edit:  ");
                System.out.println(
                        "1- user name        2- password     3- phone number     4- address      5- payment method");
                choice = in.getIntInput();
                switch (choice) {
                    case 1:
                        System.out.println("enter the new user name");
                        this.username = in.getStringInput();
                        break;
                    case 2:
                        System.out.println("enter the new password");
                        this.password = in.getStringInput();
                        break;
                    case 3:
                        System.out.println("enter the new phone number");
                        this.phoneNumber = in.getStringInput();
                        break;
                    case 4:
                        System.out.println("enter the new address");
                        this.address = in.getStringInput();
                        break;
                    case 5:
                        System.out.println("enter the new payment method");
                        this.paymentMethod = in.getStringInput();
                }
                System.out.println("do you want to edit another information(y/n)");
                check = in.getStringInput().charAt(0);
            }
            s = this.email + "," + this.username + "," + this.password + "," + this.phoneNumber + "," + this.address
                    + "," + this.paymentMethod;
            readersinfo.set(index, s);
            FileManager.writeFile(Constants.READERS_FILE_PATH, readersinfo);
        } else {
            System.out.println("user not found");
        }
        System.out.println("your information have been apdated Successfully");

    }

    public void orderBook(String bookName) {

    }

    public void register() {
        FileManager.appendFile(Constants.READERS_FILE_PATH, username + "," + email + "," + password
                + "," + phoneNumber + "," + address + "," + paymentMethod);
    }

}
