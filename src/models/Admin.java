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
        FileManager.appendFile(Constants.BOOKS_FILE_PATH, book.getName() + ", " + book.getAuthor() + ", " + book.getPrice()
        + ", " + book.getStock() + ", " + book.getPrice());

    }

    public void editBook(String bookName)
    {
        // Selected after Search Book or Display Books, takes input on which book to edit
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
