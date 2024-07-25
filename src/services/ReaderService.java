package services;

import models.Book;
import models.Reader;
import utils.FileManager;

import java.util.List;

public class ReaderService {

  private static final String USERS_FILE_PATH = "resources/users.txt";
  private static final String BOOKS_FILE_PATH = "resources/books.txt";

  public void registerReader(Reader reader) {
    List<String> users = FileManager.readFile(USERS_FILE_PATH);
    users.add(reader.toString()); // You can format this string as needed
    FileManager.writeFile(USERS_FILE_PATH, users);
  }

  public void editReader(Reader reader) {
    // Implement edit logic
  }

  public List<String> displayAvailableBooks() {
    List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
    // Filter books to show only available ones
    return books;
  }

  public Book searchBook(String bookName) {
    // Implement search logic
    return null;
  }

  public void orderBook(String bookName) {
    // Implement order logic
  }
}