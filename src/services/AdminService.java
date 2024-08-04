package services;

import java.util.List;
import models.Book;

public interface AdminService
{
    public void addBook(Book book);
    public void editBook(Book book);
    public void deleteBook(String bookName);
    public List<String> displayAllBooks();
    public Book searchBook(String bookName);
}