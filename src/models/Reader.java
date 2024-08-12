package models;

import java.util.ArrayList;

import utils.*;

public class Reader extends User
{
    private String phoneNumber, address, paymentMethod;

    private ShoppingCart shoppingCart;

    public Reader(String username, String email, String password, String phoneNumber,
                    String address, String paymentMethod)
    {
        super(username, email, password);

        this.phoneNumber = phoneNumber;
        this.address = address;
        this.paymentMethod = paymentMethod;

        this.shoppingCart = new ShoppingCart(this);
    }

    public ShoppingCart getShoppingCart()
    {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart)
    {
        this.shoppingCart = shoppingCart;
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
        {
            OutputPrinter.clearTerminal();
        }

        ArrayList<String> readersinfo = FileManager.readFile(Constants.READERS_FILE_PATH);
        int index = -1;

        for (int i = 0; i < readersinfo.size(); i++)
        {
            if (SecurityService.decrypt(readersinfo.get(i)).contains(email))
            {
                index = i;
                break;
            }
        }

        OutputPrinter.printWithColor("Account Details\n", "94m");

        System.out.println("[0] Email: " + email);
        System.out.println("[1] Username: " + username);
        System.out.println("[2] Password: ************");
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
                    break;
            }
            OutputPrinter.printWithColor("\nDo you want to edit another information? (Y/N):", "94m");
            check = InputReader.getStringInput().charAt(0);
        }

        if (check == 'N' || check == 'n')
        {
            readersinfo.set(index, SecurityService.encrypt(email + "," + username + "," + SecurityService.hash(password) + "," + phoneNumber + "," + address + "," +paymentMethod));

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

    public static void register()
    {
        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Register\n", "96m");

        System.out.print("Email: ");
        String email = InputReader.getStringInput();

        for (String user : FileManager.readFile(Constants.READERS_FILE_PATH))
        {
            if (SecurityService.decrypt(user).contains(email))
            {
                OutputPrinter.clearTerminal();
                OutputPrinter.printWithColor("An account already exists with this email!\n", "31m");
                Menu.showReaderMenu(true);
            }
        }

        System.out.print("Username: ");
        String username = InputReader.getStringInput();

        System.out.print("Password: ");
        String password = InputReader.getStringInput();

        System.out.print("Phone Number: ");
        String phoneNumber = InputReader.getStringInput();

        System.out.print("Address: ");
        String address = InputReader.getStringInput();

        System.out.print("Payment Method: ");
        String paymentMethod = InputReader.getStringInput();

        FileManager.appendFile(Constants.READERS_FILE_PATH, SecurityService.encrypt(email + "," + username + "," +
		SecurityService.hash(password) + "," + phoneNumber + "," + address + "," + paymentMethod));

        OutputPrinter.clearTerminal();
        OutputPrinter.printWithColor("Registration successful!\n", "32m");
    }

    public void showPreviousOrders()
    {
        String[] headers = { "Order ID", "Date Created", "Order Total" };
        int[] columnWidths = { 15, 25, 10, 1 };

        for (int i = 0; i < headers.length; i++)
        {
            System.out.printf("| %-" + columnWidths[i] + "s ", headers[i]);
        }
        System.out.println("|");

        for (int width : columnWidths)
        {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++)
            {
                System.out.print("-");
            }
        }
        System.out.println("+");

        ArrayList<String> orders = FileManager.readFile(Constants.ORDERS_FILE_PATH);
        for (int i = 0; i < orders.size(); i++)
        {
            String[] orderDetails = SecurityService.decrypt(orders.get(i)).split(",");
            if (orderDetails[2].equals(this.email))
            {
                for (int j = 0; j < orderDetails.length; j++)
                {
                    // Skip showing email (Index: 2)
                    if (j != 2)
                    {
                        System.out.printf("| %-" + columnWidths[j] + "s ", orderDetails[j]);
                    }
                }
                System.out.println("|");
            }
        }
    }
}
