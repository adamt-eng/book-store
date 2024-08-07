package models;

import utils.*;

public class Book
{
    private String name;
    private String author;
    private double price;
    private int stock;
    private String category;

    public Book(String name, String author, double price, int stock, String category)
    {
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void showBookActions(User user)
    {
        if (user instanceof Admin admin)
        {
            System.out.println("\n[1] Delete Book");
            System.out.println("[2] Edit Book");
            System.out.println("[0] Return To Previous Menu");

            switch (InputReader.getIntInput())
            {
                case 1:
                    admin.deleteBook(this);
                    break;
                case 2:
                    admin.editBook(this);
                    break;
                case 0:
                    OutputPrinter.clearTerminal();
                    Menu.showAdminFunctions(admin);
                    break;
            }
        }
        else if (user instanceof Reader reader)
        {
            System.out.println("\n[1] Order Book");
            System.out.println("[0] Return To Previous Menu");

            switch (InputReader.getIntInput())
            {
                case 1:
                    reader.getShoppingCart().addToCart(this);
                    break;
                case 0:
                    OutputPrinter.clearTerminal();
                    Menu.showReaderFunctions(reader);
                    break;
            }
        }
    }
}
