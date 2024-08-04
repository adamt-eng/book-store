package services;

import models.*;
import java.util.List;

public interface ReaderService
{
    public void registerReader(Reader reader);
    public void editReader(Reader reader);
    public List<String> displayAvailableBooks();
    public Book searchBook(String bookName);
    public void orderBook(String bookName);
    public void register();
}