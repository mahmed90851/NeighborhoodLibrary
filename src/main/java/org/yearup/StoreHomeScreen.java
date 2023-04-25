package org.yearup;
import java.util.Scanner;
public class StoreHomeScreen {
    public static void main()  {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Main Menu:");
        System.out.println("[1] Show Available Books");
        System.out.println("[2] Show Checked Out Books");
        System.out.println("[0] Exit");

        String input = scanner.nextLine();

        if (input.equals("1")){
            availableBooks();
        }
        else if (input.equals("2")){
            checkedOut();
        }
        else{
            System.exit(0);
        }
    }

    public static void availableBooks() {
        Scanner scanner = new Scanner(System.in);
        Book[] booksAvailable = BookInventory.booksAvailable;

        System.out.println("Books Available: ");
        for (int i = 0; i < booksAvailable.length; i++) {
            if (booksAvailable[i].isCheckedOut() == false) {
                System.out.println(
                        "[" + i + "] " +
                                booksAvailable[i].getTitle() + ", " + "ID: " +
                                booksAvailable[i].getId() + ", ISBN: " +
                                booksAvailable[i].getIsbn());
            }
        }
        System.out.println(
                "[0] " + "Return to Menu"
        );

        String input = scanner.nextLine();
        String choice = input;

        if (input.equals("0")) {
            main();
        } else {
            System.out.println("Enter your name to check out \"" + booksAvailable[Integer.parseInt(choice)].getTitle() + "\"");
            input = scanner.nextLine();
            booksAvailable[Integer.parseInt(choice)].checkOut(input);

            System.out.println("\"" + booksAvailable[Integer.parseInt(choice)].getTitle() + "\""  + " has been checked out.");

            availableBooks();

        }

    }
    public static void checkedOut() {
        Scanner scanner = new Scanner(System.in);
        Book[] booksCheckedOut = BookInventory.booksAvailable;

        System.out.println("Your Checked-Out Books:");

        for (int i = 0; i < booksCheckedOut.length; i++){
            if (booksCheckedOut[i].isCheckedOut() == true){
                System.out.println(
                        "[" + i + "] " +
                                booksCheckedOut[i].getTitle() + ", " + "ID: " +
                                booksCheckedOut[i].getId() + ", ISBN: " +
                                booksCheckedOut[i].getIsbn() + ", checked out by " +
                                booksCheckedOut[i].getCheckedOutTo());
            }
        }
        System.out.println("[1] Check In a book");
        System.out.println("[0] Exit To Main Menu");

        String input = scanner.nextLine();

        if (input.equals("1")){
            checkIn();
        }
        else{
            main();
        }
    }

    public static void checkIn() {
        Scanner scanner = new Scanner(System.in);
        Book[] booksCheckedIn = BookInventory.booksAvailable;

        System.out.println("Enter the ID of book you are checking in: ");

        int input = scanner.nextInt();

        for (Book book : booksCheckedIn){
            if (input == book.getId()){
                book.checkIn();
                System.out.println(book.getTitle() + " was Checked In!");
            }
        }

        main();
    }
}
