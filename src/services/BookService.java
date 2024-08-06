package services;

import models.User;

public interface BookService
{
    public void searchBook(User user, boolean invalid);
    public void displayBooks(User user, boolean invalid);
}