package services;

import models.Book;

public interface BookService 
{
    public void displayBooks();
    public Book searchBook(String bookName);
}
