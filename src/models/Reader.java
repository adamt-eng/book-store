package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService
{
    private String phoneNumber, address, paymentMethod;
    private ArrayList<Book> shoppingCart = new ArrayList<>();

    public int getShoppingCartSize()
    {
        return shoppingCart.size();
    }

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

    public void editInformation(boolean invalid)
    {
        if (!invalid)
            OutputPrinter.clearTerminal();

        ArrayList<String> readersinfo = FileManager.readFile(Constants.READERS_FILE_PATH);
        int index = -1;

        for (int i = 0; i < readersinfo.size(); i++)
        {
            if (readersinfo.get(i).contains(email))
            {
                index = i;
                break;
            }
        }

        OutputPrinter.printWithColor("Account Details\n", "94m");

        System.out.println("[0] Email: " + email);
        System.out.println("[1] Username: " + username);
        System.out.println("[2] Password: " + password);
        System.out.println("[3] Phone Number: " + phoneNumber);
        System.out.println("[4] Address: " + address);
        System.out.println("[5] Payment Method: " + paymentMethod);

        char check = 'Y';
        while (check == 'Y' || check == 'y')
        {
            OutputPrinter.printWithColor("\nWhich information would you like to edit? (1/2/3/4/5):", "94m");

            switch (InputReader.getIntInput())
            {
                case 0:
                    OutputPrinter.clearTerminal();
                    OutputPrinter.printWithColor("Email cannot be edited!", "31m");
                    editInformation(true);
                    return;
                case 1:
                    System.out.println("Enter the new username: ");
                    this.username = InputReader.getStringInput();
                    break;
                case 2:
                    System.out.println("Enter the new password: ");
                    this.password = InputReader.getStringInput();
                    break;
                case 3:
                    System.out.println("Enter the new phone number: ");
                    this.phoneNumber = InputReader.getStringInput();
                    break;
                case 4:
                    System.out.println("Enter the new address: ");
                    this.address = InputReader.getStringInput();
                    break;
                case 5:
                    System.out.println("Enter the new payment method: ");
                    this.paymentMethod = InputReader.getStringInput();
            }
            OutputPrinter.printWithColor("\nDo you want to edit another information? (Y/N):", "94m");
            check = InputReader.getStringInput().charAt(0);
        }

        if (check == 'N' || check == 'n')
        {
            readersinfo.set(index, email + "," + username + "," + password + "," + phoneNumber + "," + address +
                            "," +
                            paymentMethod);

            FileManager.writeFile(Constants.READERS_FILE_PATH, readersinfo);
        }
        else
        {
            OutputPrinter.invalidChoice();
            editInformation(true);
        }

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Your information has been successfully updated!\n", "32m");
        Menu.showReaderFunctions(this);
    }

    public void orderBook(String bookName)
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
        Menu.showReaderFunctions(this);
    }

    public void showReceipt()
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
            removeFromCart(shoppingCart.get(InputReader.getIntInput()));
        }

        while (f1)
        {
            System.out.println("\nThis is your current payment method: " + this.paymentMethod);
            OutputPrinter.printWithColor("Do you want to change it? (Y/N):", "94m");

            choice = InputReader.getStringInput().charAt(0);

            if (choice == 'y' || choice == 'Y')
            {
                System.out.println("Enter the new payment method: ");
                this.paymentMethod = InputReader.getStringInput();
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

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Books have been purchased successfully!", "32m");
        shoppingCart.clear();
        Menu.showReaderFunctions(this);
    }

    public void removeFromCart(Book bookToRemove)
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
        Menu.showReaderFunctions(this);
    }

    public static void register()
    {
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Register\n", "96m");

        System.out.print("Username: ");
        String username = InputReader.getStringInput();

        for (String user : FileManager.readFile(Constants.READERS_FILE_PATH))
        {
            if (user.contains(username))
            {
                OutputPrinter.clearTerminal();
                OutputPrinter.printWithColor("An account already exists with this username!\n", "31m");
                Menu.showReaderMenu(true);
            }
        }

        System.out.print("Email: ");
        String email = InputReader.getStringInput();

        System.out.print("Password: ");
        String password = InputReader.getStringInput();

        System.out.print("Phone Number: ");
        String phoneNumber = InputReader.getStringInput();

        System.out.print("Address: ");
        String address = InputReader.getStringInput();

        System.out.print("Payment Method: ");
        String paymentMethod = InputReader.getStringInput();

        FileManager.appendFile(Constants.READERS_FILE_PATH, email + "," + username + "," +
                        password + "," + phoneNumber + "," + address + "," + paymentMethod);

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Registration successful!\n", "32m");
    }
}
