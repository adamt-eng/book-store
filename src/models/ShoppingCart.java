package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import utils.*;

public class ShoppingCart
{
    private ArrayList<Book> addedBooks;
    private Reader reader;

    public ShoppingCart(Reader reader)
    {
        addedBooks = new ArrayList<Book>();
        this.reader = reader;
    }

    public boolean isShoppingCartEmpty()
    {
        return addedBooks.isEmpty();
    }

    public void addToCart(Book book)
    {
        for (String line : FileManager.readFile(Constants.BOOKS_FILE_PATH))
        {
            if (line.split(",")[0].equals(book.getName()))
            {
                addedBooks.add(book);
                break;
            }
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book added to your shopping cart!\n", "32m");
        Menu.showReaderFunctions(reader);
    }

    public void removeFromCart(Book bookToRemove)
    {
        int i;
        for (i = 0; i < addedBooks.size(); i++)
        {
            if (addedBooks.get(i) == bookToRemove)
            {
                break;
            }
        }
        addedBooks.remove(i);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book removed from cart successfully!\n", "32m");
        Menu.showReaderFunctions(reader);
    }
    
    public void displayCart()
    {
        double total = 0;

        for (Book book : addedBooks)
        {
            total += book.getPrice();
        }

        // Print table header
        System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-50s | %-40s | %-20s | %-10s |%n", "Book ID", "Book Name", "Book Author",
                        "Book Category", "Book Price");
        System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------------");

        // Print table rows
        for (int i = 0; i < addedBooks.size(); i++)
        {
            Book book = addedBooks.get(i);

            System.out.printf("| %-8d | %-50s | %-40s | %-20s | %-10s |%n",
                            i, book.getName(), book.getAuthor(), book.getCategory(),
                            book.getPrice());
        }

        // Print table footer
        System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-127s | $%-10.2f|%n", "Total Payment", total); // Format total payment to 2 decimal places
        System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Do you want to remove any book from your cart? (Y/N): ");
        char choice = InputReader.getStringInput().charAt(0);
        if (choice == 'y' || choice == 'Y')
        {
            System.out.println("Please select a book using it's index: ");
            removeFromCart(addedBooks.get(InputReader.getIntInput()));
        }

        Order order = new Order(Math.abs(new Random().nextInt()), new Date(), reader, total);
        order.generateOrder(addedBooks);
        
        Menu.showReaderFunctions(reader);
    }
}
