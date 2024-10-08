package utils;

public class OutputPrinter 
{
    public static void clearTerminal()
    {
        System.out.print("\033\143");
    }
    public static void printWithColor(String text, String colorCode)
    {
        // Red: 31m
        // Green: 32m
        // Cyan: 94m
        System.out.println("\033[" + colorCode + text + "\033[0m");
    }
    public static void exit()
    {
        clearTerminal();
        printWithColor("Thanks for visiting!", "96m");
        System.exit(0);
    }
    public static void invalidChoice()
    {
        clearTerminal();
        printWithColor("Invalid choice!\n", "31m");
    }
}