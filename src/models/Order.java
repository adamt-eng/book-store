package models;

import java.util.ArrayList;
import java.util.Date;
import utils.*;

public class Order
{
    private int orderID;
    private Date dateCreated;
    private Reader reader;
    private double total;

    public Order(int orderID, Date dateCreated, Reader reader, double total)
    {
        this.orderID = orderID;
        this.dateCreated = dateCreated;
        this.reader = reader;
        this.total = total;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Reader getReader()
    {
        return reader;
    }

    public void setReader(Reader reader)
    {
        this.reader = reader;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public void generateOrder(ArrayList<Book> books)
    {
        boolean f1 = true;
        while (f1)
        {
            System.out.println("\nThis is your current payment method: " + reader.getPaymentMethod());
            OutputPrinter.printWithColor("Do you want to change it? (Y/N):", "94m");

            char choice = InputReader.getStringInput().charAt(0);

            if (choice == 'y' || choice == 'Y')
            {
                System.out.println("Enter the new payment method: ");
                reader.setPaymentMethod(InputReader.getStringInput());
            }
            else if (choice == 'n' || choice == 'N')
            {
                f1 = false;
            }
        }

        ArrayList<String> bookData = FileManager.readFile(Constants.BOOKS_FILE_PATH);
        for (Book book : books)
        {
            for (int i = 0; i < bookData.size(); i++)
            {
                String[] bookDetails = bookData.get(i).split(",");

                if (bookDetails[0].trim().equals(book.getName().trim()))
                {
                    bookDetails[3] = String.valueOf(Integer.parseInt(bookDetails[3]) - 1);
                    bookData.set(i, String.join(",", bookDetails));
                    FileManager.writeFile(Constants.BOOKS_FILE_PATH, bookData);
                    break;
                }
            }
        }

        FileManager.appendFile(Constants.ORDERS_FILE_PATH, SecurityService.encrypt(this.orderID + "," + this.dateCreated + "," +
                        this.reader.getEmail() + "," + this.total));

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Books have been purchased successfully!", "32m");
        books.clear();
    }
}
