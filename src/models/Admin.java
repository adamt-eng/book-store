package models;

import services.*;
import utils.*;

public class Admin extends User implements AdminService, BookService
{
    public Admin(String username, String email, String password)
    {
        super(username, email, password);
    }

    public void addBook()
    {

    }

    public void editBook(Book book)
    {
        // Implement edit logic
    }

    public void deleteBook(String bookName)
    {
        // Implement delete logic
    }

    public void displayBooks()
    {
        
    }

    public void searchBook()
    {
        
    }
}
