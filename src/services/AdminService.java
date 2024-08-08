package services;

import models.Book;

public interface AdminService
{
    public void addBook();
    public void editBook(Book book);
    public void deleteBook(Book book);
}