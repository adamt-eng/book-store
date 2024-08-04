package models;

import services.*;
import utils.Constants;
import utils.FileManager;

public class Admin extends User implements AdminService, BookService
{
    public Admin(String username, String email, String password)
    {
        super(username, email, password);
    }

    public void searchBook()
    {
        // Shows "Search for book name:" and then when user presses enter do displayBook(String bookName);
    }

    public void displayBook(String bookName)
    {
        // Displays book info (one book)
    }

    public void displayBooks()
    {
        // Reads file and displays the books and in the end shows an action menu (delete/edit)
    }

    public void addBook(Book book)
    {
        FileManager.writeFile(Constants.BOOKS_FILE_PATH, book.getName() + ", " + book.getAuthor() + ", " + book.getPrice()
        + ", " + book.getStock() + ", " + book.getPrice());

    }

    public void editBook()
    {
        // Selected after Search Book or Display Books, takes input on which book to edit
    }

    public void deleteBook()
    {
        // Selected after Search Book or Display Books, takes input on which book to delete
    }
    
}
