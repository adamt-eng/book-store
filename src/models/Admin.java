package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Admin extends User implements AdminService, BookService
{
    public Admin(String username, String email, String password)
    {
        super(username, email, password);
    }

    public void displayBooks()
    {
        // Reads file and displays the books and in the end shows an action menu (delete/edit)
    }

    public void addBook(Book book)
    {
        FileManager.appendFile(Constants.BOOKS_FILE_PATH, book.getName() + ", " + book.getAuthor() + ", $" + book.getPrice()
        + ", " + book.getStock() + ", " + book.getCategory());

    }

    public void editBook(String bookName)
    {
        InputManager inputManager = new InputManager();
        ArrayList<String> booksinfo = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        int index = -1;
        for (int i = 0; i < booksinfo.size(); i++)
        {
            if (booksinfo.get(i).contains(bookName))
            {
                index = i;
                break;
            }
        }
        String book = booksinfo.get(index);
        String[] tokens = book.split(", ");
        if (index >= 0)
        {
                OutputManager.printWithColor("\nWhich information would you like to edit? (1 book's name/2 author/3 price/4 stock number/5 category):", "94m");

                switch (inputManager.getIntInput())
                {
                    case 1:
                        System.out.println("Enter the new name: ");
                        tokens[0] = inputManager.getStringInput();
                        break;
                    case 2:
                        System.out.println("Enter the new author: ");
                        tokens[1] = inputManager.getStringInput();
                        break;
                    case 3:
                        System.out.println("Enter the new price: ");
                        tokens[2] = "$" + inputManager.getStringInput();
                        break;
                    case 4:
                        System.out.println("Enter the new stock number: ");
                        tokens[3] = inputManager.getStringInput();
                        break;
                    case 5:
                        System.out.println("Enter the new category: ");
                        tokens[4] = inputManager.getStringInput();
            }
            booksinfo.remove(index);
            book = ( tokens[0] + ", " + tokens[1] + ", " + tokens[2]
            + ", " + tokens[3] + ", " + tokens[4]) ;
            booksinfo.add(index, book);

            FileManager.writeFile(Constants.BOOKS_FILE_PATH, booksinfo);
            OutputManager.clearTerminal();
            OutputManager.printWithColor("Your information has been successfully updated!\n", "32m");
        }
        else
        {
            OutputManager.printWithColor("Book not found!", "31m");
        }
    }

    public void deleteBook(String bookName)
    {
        ArrayList<String> books = FileManager.readFile(Constants.BOOKS_FILE_PATH);

        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).contains(bookName))
            {
                books.remove(i);
                break;
            }
        }

        FileManager.writeFile(Constants.BOOKS_FILE_PATH, books);
    }
    
}
