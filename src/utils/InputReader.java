package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader
{
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput()
    {
        try
        {
            if (scanner.hasNextInt())
            {
                int x = scanner.nextInt();
                scanner.nextLine();
                return x;
            }
            else
            {
                OutputPrinter.invalidChoice();
                scanner.nextLine();
            }
        }
        catch (InputMismatchException e)
        {
            OutputPrinter.invalidChoice();
            scanner.nextLine();
        }

        return -1;
    }
    
    public static String getStringInput()
    {
        return scanner.nextLine();
    }
}
