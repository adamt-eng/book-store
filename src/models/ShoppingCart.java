package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import utils.*;

public class ShoppingCart {
    private ArrayList<Book> shoppingCart;

    public ShoppingCart()
    {
        shoppingCart = new ArrayList<Book>();
    }
    public boolean isShoppingCartEmpty()
    {
        return shoppingCart.isEmpty();
    }
    public void addToCart(String bookName, Reader reader)
    {
        for (String book : FileManager.readFile(Constants.BOOKS_FILE_PATH))
        {
            String[] bookDetails = book.split(",");
            if (bookDetails[0].contains(bookName))
            {
                shoppingCart.add(new Book(bookDetails[0], bookDetails[1], Double.parseDouble(bookDetails[2]),
                                Integer.parseInt(bookDetails[3]), bookDetails[4]));
                break;
            }
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book added to your shopping cart!\n", "32m");
        Menu.showReaderFunctions(reader);
    }

    public void displayCart(Reader reader)
    {
        double total = 0;

        for (Book book : shoppingCart)
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
        for (int i = 0; i < shoppingCart.size(); i++)
        {
            Book book = shoppingCart.get(i);

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
        boolean f1 = true;

        System.out.println("Do you want to remove any book from your cart? (Y/N): ");
        char choice = InputReader.getStringInput().charAt(0);
        if (choice == 'y' || choice == 'Y')
        {
            System.out.println("Please select a book using it's index: ");
            removeFromCart(shoppingCart.get(InputReader.getIntInput()), reader);
        }

        while (f1)
        {
            System.out.println("\nThis is your current payment method: " + reader.getPaymentMethod());
            OutputPrinter.printWithColor("Do you want to change it? (Y/N):", "94m");

            choice = InputReader.getStringInput().charAt(0);

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
        for (Book orderedBook : shoppingCart)
        {
            for (int i = 0; i < bookData.size(); i++)
            {
                String[] bookDetails = bookData.get(i).split(",");

                if (bookDetails[0].trim().equals(orderedBook.getName().trim()))
                {
                    bookDetails[3] = String.valueOf(Integer.parseInt(bookDetails[3]) - 1);
                    bookData.set(i, String.join(",", bookDetails));
                    FileManager.writeFile(Constants.BOOKS_FILE_PATH, bookData);
                    break;
                }
            }
        }

        Order order = new Order(Math.abs(new Random().nextInt()), new Date(), reader, total);
        FileManager.appendFile(Constants.ORDERS_FILE_PATH, order.getOrderID() + "," + order.getDateCreated() + "," + order.getReader().getEmail() + "," + order.getTotal());
        
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Books have been purchased successfully!", "32m");
        shoppingCart.clear();
        Menu.showReaderFunctions(reader);
    }

    public void removeFromCart(Book bookToRemove, Reader reader)
    {
        int i;
        for (i = 0; i < shoppingCart.size(); i++)
        {
            if (shoppingCart.get(i) == bookToRemove)
            {
                break;
            }
        }
        shoppingCart.remove(i);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Book removed from cart successfully!\n", "32m");
        Menu.showReaderFunctions(reader);
    }
}
