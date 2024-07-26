package utils;

public class OutputManager 
{
    public static void clearTerminal()
    {
        // Works on Windows, Linux, MacOS, does not work on IDE terminal I think.
        System.out.print("\033\143");
    }
    public static void printWithColor(String text, String colorCode)
    {
        // Red: 31m
        // Green: 32m
        System.out.println("\033[" + colorCode + text + "\033[0m");
    }
    public static void exit()
    {
        System.out.println("Thanks for visiting our awesome Book Store.\nEXITING...");
        System.exit(0);
    }
    public static void invalidChoice()
    {
        clearTerminal();
        printWithColor("Invalid choice!\n", "31m");
    }
}