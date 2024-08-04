package services;

import models.*;
import java.util.List;

public interface ReaderService
{
    public void editReader(Reader reader);
    public void orderBook(String bookName);
    public void register();
}