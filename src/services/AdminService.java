package services;

import models.Book;
import utils.FileManager;

import java.util.List;

public class AdminService {

  private static final String BOOKS_FILE_PATH = "resources/books.txt";

  public void addBook(Book book) {
    List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
    books.add(book.toString()); // You can format this string as needed
    FileManager.writeFile(BOOKS_FILE_PATH, books);
  }

  public void editBook(Book book) {
    // Implement edit logic
  }

  public void deleteBook(String bookName) {
    // Implement delete logic
  }

  public List<String> displayAllBooks() {
    return FileManager.readFile(BOOKS_FILE_PATH);
  }

  public Book searchBook(String bookName) {
    // Implement search logic
    return null;
  }
}