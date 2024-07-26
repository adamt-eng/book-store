package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import services.AdminService;
import services.ReaderService;

public class Menu
{
    public static void showMainMenu()
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            while (true)
            {
                System.out.println("[1] Admin");
                System.out.println("[2] Reader");
                System.out.println("[0] Exit");
                System.out.println("\nEnter your choice: ");

                int choice = 0;
                try
                {
                    choice = scanner.nextInt();
                }
                catch (InputMismatchException e)
                {
                    OutputManager.invalidChoice();
                    scanner.next();
                    continue;
                }

                switch (choice)
                {
                    case 1:
                        AdminService.showAdminMenu();
                        break;
                    case 2:
                        ReaderService.showReaderMenu();
                        break;
                    case 0:
                        OutputManager.exit();
                        break;
                    default:
                        OutputManager.invalidChoice();
                        break;
                }
            }
        }
    }
}
