package models;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService, BookService
{
    private String phoneNumber, address, paymentMethod;

    public Reader(String username, String email, String password, String phoneNumber,
                    String address, String paymentMethod)
    {
        super(username, email, password);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setPaymentMethod(paymentMethod);
    }

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

    public void displayBooks()
    {
        // Reads file and displays the books and in the end shows an action menu (order)
    }

    public void editInformation()
    {

    }

    public void orderBook(String bookName)
    {
        // Confirm order and then minus one from the stock
    }

    public void register()
    {
        FileManager.appendFile(Constants.READERS_FILE_PATH, username + "," + email + "," + password
                        + "," + phoneNumber + "," + address + "," + paymentMethod);
    }
}
