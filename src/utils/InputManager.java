package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager
{
    private Scanner scanner;

    public InputManager()
    {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput()
    {
        try
        {
            if (scanner.hasNextInt())
            {
                return scanner.nextInt();
            }
            else
            {
                OutputManager.invalidChoice();
                scanner.next();
            }
        }
        catch (InputMismatchException e)
        {
            OutputManager.invalidChoice();
            scanner.next();
        }

        return -1;
    }

    public  String getStringInput()
    {
        return scanner.next();
    }
}
