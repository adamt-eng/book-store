package models;

import java.util.List;
import services.AdminService;
import utils.FileManager;

public class Admin extends User implements AdminService
{
    public Admin(String username, String email, String password)
    {
        super(username, email, password);
    }

    public void addBook(Book book)
    {
        FileManager.writeFile(BOOKS_FILE_PATH, book.toString());
    }

    public void editBook(Book book)
    {
        // Implement edit logic
    }

    public void deleteBook(String bookName)
    {
        // Implement delete logic
    }

    public List<String> displayAllBooks()
    {
        return FileManager.readFile(BOOKS_FILE_PATH);
    }

    public Book searchBook(String bookName)
    {
        // Implement search logic
        return null;
    }
}
