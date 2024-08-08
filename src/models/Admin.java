package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Admin extends User implements AdminService
{
    public Admin(String username, String email, String password)
    {
        super(username, email, password);
    }



    public void addBook()
    {
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Add Book\n", "94m");

        System.out.print("Name: ");
        String name = InputReader.getStringInput();

        System.out.print("Author: ");
        String author = InputReader.getStringInput();

        double price = -1;
        while (price == -1)
        {
            System.out.print("Price: ");
            price = InputReader.getDoubleInput();
        }

        int stock = -1;
        while (stock == -1)
        {
            System.out.print("Stock: ");
            stock = InputReader.getIntInput();
        }

        System.out.print("Category: ");
        String category = InputReader.getStringInput();

        FileManager.appendFile(Constants.BOOKS_FILE_PATH, name + "," + author + "," + price + "," + stock + "," +
                        category);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book successfully added!\n", "32m");
        Menu.showAdminFunctions(this);
    }

    public void editBook(Book book)
    {
        OutputPrinter.clearTerminal();

        ArrayList<String> booksinfo = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        int i;

        for (i = 0; i < booksinfo.size(); i++)
        {
            if (booksinfo.get(i).contains(book.getName()))
            {
                break;
            }
        }

        String[] tokens = booksinfo.get(i).split(",");

        OutputPrinter.printWithColor("Book Details\n", "94m");

        System.out.println("[0] Name: " + tokens[0]);
        System.out.println("[1] Author: " + tokens[1]);
        System.out.println("[2] Price: " + tokens[2]);
        System.out.println("[3] Stock: " + tokens[3]);
        System.out.println("[4] Category: " + tokens[4]);

        OutputPrinter.printWithColor("\nWhich information would you like to edit? (0/1/2/3/4):", "94m");

        switch (InputReader.getIntInput())
        {
            case 0:
                System.out.println("Enter the new name: ");
                tokens[0] = InputReader.getStringInput();
                break;
            case 1:
                System.out.println("Enter the new author: ");
                tokens[1] = InputReader.getStringInput();
                break;
            case 2:
                System.out.println("Enter the new price: ");
                tokens[2] = InputReader.getStringInput();
                break;
            case 3:
                System.out.println("Enter the new stock number: ");
                tokens[3] = InputReader.getStringInput();
                break;
            case 4:
                System.out.println("Enter the new category: ");
                tokens[4] = InputReader.getStringInput();
        }
        booksinfo.remove(i);
        booksinfo.add(i, tokens[0] + "," + tokens[1] + "," + tokens[2] + "," + tokens[3] + "," + tokens[4]);

        FileManager.writeFile(Constants.BOOKS_FILE_PATH, booksinfo);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book successfully updated!\n", "32m");

        Menu.showAdminFunctions(this);
    }

    public void deleteBook(Book book)
    {
        ArrayList<String> books = FileManager.readFile(Constants.BOOKS_FILE_PATH);

        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).contains(book.getName()))
            {
                books.remove(i);
                break;
            }
        }

        FileManager.writeFile(Constants.BOOKS_FILE_PATH, books);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book successfully deleted!\n", "32m");
        Menu.showAdminFunctions(this);
    }
}
