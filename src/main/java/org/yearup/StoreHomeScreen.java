package org.yearup;
import java.util.Scanner;
public class StoreHomeScreen {
    public static void main(String[] args) throws InterruptedException {
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

    public static void availableBooks() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Book[] books = BookInventory.booksAvailable;

        System.out.println("Books Available: ");
        for (int i = 0; i < books.length; i++) {
            if (books[i].isCheckedOut() == false) {
                System.out.println(
                        "[" + i + "] " +
                                books[i].getTitle() + ", " + "ID: " +
                                books[i].getId() + ", ISBN: " +
                                books[i].getIsbn());
            }
        }
        System.out.println(
                "[0] " + "Return to Menu"
        );

        String input = scanner.nextLine();
        String choice = input;

        if (input.equals("0")) {
            main(null);
        } else {
            System.out.println("Enter your name to check out \"" + books[Integer.parseInt(choice)].getTitle() + "\"");
            input = scanner.nextLine();
            books[Integer.parseInt(choice)].checkOut(input);

            System.out.println("\"" + books[Integer.parseInt(choice)].getTitle() + "\""  + " has been checked out.");

            availableBooks();

        }

    }
    public static void checkedOut() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Book[] books = BookInventory.booksAvailable;

        System.out.println("Your Checked-Out Books:");

        for (int i = 0; i < books.length; i++){
            if (books[i].isCheckedOut() == true){
                System.out.println(
                        "[" + i + "] " +
                                books[i].getTitle() + ", " + "ID: " +
                                books[i].getId() + ", ISBN: " +
                                books[i].getIsbn() + ", checked out by " +
                                books[i].getCheckedOutTo());
            }
        }
        System.out.println("[1] Check In a book");
        System.out.println("[0] Exit To Main Menu");

        String input = scanner.nextLine();

        if (input.equals("1")){
            checkIn();
        }
        else{
        main(null);
        }
    }

    public static void checkIn() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Book[] books = BookInventory.booksAvailable;

        System.out.println("Enter ID of book you are checking in:");

        int input = scanner.nextInt();

        for (Book book : books){
            if (input == book.getId()){
                book.checkIn();
                System.out.println(book.getTitle() + " was successfully Checked In!");
            }
        }

        main(null);
    }
}
