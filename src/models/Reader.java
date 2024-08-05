package models;

import java.util.ArrayList;

import services.*;
import utils.*;

public class Reader extends User implements ReaderService
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

    public void editInformation(boolean invalid)
    {
        InputManager inputManager = new InputManager();

        if (!invalid)
            OutputManager.clearTerminal();

        OutputManager.printWithColor("Account Details\n", "94m");

        System.out.println("[0] Email: " + email);
        System.out.println("[1] Username: " + username);
        System.out.println("[2] Password: " + password);
        System.out.println("[3] Phone Number: " + phoneNumber);
        System.out.println("[4] Address: " + address);
        System.out.println("[5] Payment Method: " + paymentMethod);
        
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

        if (index >= 0)
        {
            char check = 'Y';
            while (check == 'Y')
            {
                OutputManager.printWithColor("\nWhich information would you like to edit? (1/2/3/4/5):", "94m");

                switch (inputManager.getIntInput())
                {
                    case 0:
                        OutputManager.clearTerminal();
                        OutputManager.printWithColor("Email cannot be edited!", "31m");
                        editInformation(true);
                        return;
                    case 1:
                        System.out.println("Enter the new username: ");
                        this.username = inputManager.getStringInput();
                        break;
                    case 2:
                        System.out.println("Enter the new password: ");
                        this.password = inputManager.getStringInput();
                        break;
                    case 3:
                        System.out.println("Enter the new phone number: ");
                        this.phoneNumber = inputManager.getStringInput();
                        break;
                    case 4:
                        System.out.println("Enter the new address: ");
                        this.address = inputManager.getStringInput();
                        break;
                    case 5:
                        System.out.println("Enter the new payment method: ");
                        this.paymentMethod = inputManager.getStringInput();
                }
                OutputManager.printWithColor("\nDo you want to edit another information? (Y/N):", "94m");
                check = inputManager.getStringInput().charAt(0);
            }

            if (check == 'N')
            {
                readersinfo.set(index, email + "," + username + "," + password + "," + phoneNumber + "," + address +
                                "," +
                                paymentMethod);

                FileManager.writeFile(Constants.READERS_FILE_PATH, readersinfo);
            }
            else
            {
                OutputManager.invalidChoice();
                editInformation(true);
            }
        }
        else
        {
            OutputManager.printWithColor("User not found!", "31m");
        }

        OutputManager.clearTerminal();
        OutputManager.printWithColor("Your information has been successfully updated!\n", "32m");
        Menu.showReaderFunctions(this);
    }

    public void orderBook(String bookName)
    {

    }

    public void register()
    {
        FileManager.appendFile(Constants.READERS_FILE_PATH, email + "," + username + "," +
                        password + "," + phoneNumber + "," + address + "," + paymentMethod);
    }

}
