package services;

import java.util.List;
import models.Book;

public interface BookService 
{
    public List<String> displayAvailableBooks();
    public Book searchBook(String bookName);
}
