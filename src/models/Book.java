package models;

import services.BookService;

public class Book implements BookService
{
    private String name;
    private String author;
    private double price;
    private int stock;
    private String category;

    public Book(String name, String author, double price, int stock, String category)
    {
        setName(name);
        setAuthor(author);
        setPrice(price);
        setStock(stock);
        setCategory(category);
    }

    // Getters and Setters
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

    public void searchBook()
    {
        // Shows "Search for book name:" and then when user presses enter do displayBook(String bookName);
    }

    public void displayBook(String bookName)
    {
        // Displays book info (one book)
    }

    public void displayBooks(boolean unavailableBooks)
    {
        // Reads file and displays the books and in the end shows an action menu (delete/edit)
    }
}
