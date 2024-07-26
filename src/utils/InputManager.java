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
        while (true)
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
        }
    }

    public String getStringInput()
    {
        return scanner.next();
    }
}
