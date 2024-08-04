package models;

import java.util.List;
import utils.FileManager;

public class Reader extends User {
  private String phoneNumber;
  private String address;
  private String paymentMethod;

  public Reader(String username, String email, String password, String phoneNumber, String address,
      String paymentMethod) {
    super(username, email, password);
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.paymentMethod = paymentMethod;
  }

  // Getters and Setters
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void registerReader(Reader reader)
    {
        FileManager.writeFile(USERS_FILE_PATH, reader.toString());
    }

    public void editReader(Reader reader)
    {
        // Implement edit logic
    }

    public List<String> displayAvailableBooks()
    {
        List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
        // Filter books to show only available ones
        return books;
    }

    public Book searchBook(String bookName)
    {
        // Implement search logic
        return null;
    }

    public void orderBook(String bookName)
    {
        // Implement order logic
    }
}