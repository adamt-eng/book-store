package services;

import models.*;

public interface AdminService
{
    public void addBook(Book book);
    public void editBook(String bookName);
    public void deleteBook(String bookName);
}