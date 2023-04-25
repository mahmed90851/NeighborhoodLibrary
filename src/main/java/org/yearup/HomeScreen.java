package org.yearup;
import java.util.Scanner;
public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMainMenu();
        String input = scanner.next();

        switch (input) {
            case "1":
                availableBooks();
                break;
            case "2":
                checkedOut();
                break;
            case "0":
                exitProgram();
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                main(args);
        }
    }

    private static void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("[1] Show Available Books");
        System.out.println("[2] Show Checked Out Books");
        System.out.println("[0] Exit");
    }


    public static void availableBooks() {
        Scanner scanner = new Scanner(System.in);
        Book[] booksAvailable = BookInventory.booksAvailable;

        System.out.println("Books Available: ");
        for (Book book : booksAvailable) {
            if (!book.isCheckedOut()) {
                System.out.println(
                        "[" + book.getId() + "] " +
                                book.getTitle() + ", " + "ID: " +
                                book.getId() + ", ISBN: " +
                                book.getIsbn());
            }
        }

        System.out.println(
                "[0] Return to Main Menu"
        );

        String input = scanner.nextLine();

        if (input.equals("0")) {
            main(new String[]{});
        } else {
            try {
                int bookIndex = Integer.parseInt(input);
                if (bookIndex < 0 || bookIndex >= booksAvailable.length || booksAvailable[bookIndex].isCheckedOut()) {
                    System.out.println("Invalid book selection.");
                    availableBooks();
                } else {
                    System.out.println("Enter your name to check out \"" + booksAvailable[bookIndex].getTitle() + "\"");
                    input = scanner.nextLine();
                    booksAvailable[bookIndex].checkOut(input);

                    System.out.println("\"" + booksAvailable[bookIndex].getTitle() + "\""  + " has been checked out.");

                    availableBooks();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                availableBooks();
            }
        }
    }

    public static void checkedOut() {
        Scanner scanner = new Scanner(System.in);
        Book[] booksCheckedOut = BookInventory.booksAvailable;

        System.out.println("Your checked out books: ");

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
        System.out.println("[1] Check in a book");
        System.out.println("[0] Return to 'Main Menu'");

        String input = scanner.nextLine();

        if (input.equals("1")){
            checkIn();
        }
        else{
            main(null);
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

        main(null);
    }
    private static void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }

}
