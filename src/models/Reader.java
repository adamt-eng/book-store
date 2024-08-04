package models;

import java.util.List;
import services.*;
import utils.*;

public class Reader extends User implements ReaderService, BookService
{
    private String phoneNumber;
    private String address;
    private String paymentMethod;

    public Reader(String username, String email, String password, String phoneNumber,
                    String address, String paymentMethod)
    {
        super(username, email, password);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setPaymentMethod(paymentMethod);
    }

    // Getters and Setters
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public void editReader(Reader reader)
    {
        // Implement edit logic
    }

    public void displayBooks()
    {
        List<String> books = FileManager.readFile(BOOKS_FILE_PATH);
        // Filter books to show only available ones

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

    public void register()
    {
        FileManager.writeFile(READERS_FILE_PATH, username + "," + email + "," + password + ","
                        + phoneNumber + "," + address + "," + paymentMethod);
    }
}
