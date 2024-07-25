import services.AdminService;
import services.ReaderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        ReaderService readerService = new ReaderService();

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. Reader");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Admin operations
                    break;
                case 2:
                    // Reader operations
                    break;
                case 3:
                    System.out.println("Thanks for visiting our awesome Book Store.\nEXITING...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}