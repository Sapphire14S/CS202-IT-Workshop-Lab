import java.util.Scanner;

class Book {
    int bookId;
    String bookTitle;
    int yearOfPublication;
    String authorName;
    String publisherName;
    int numberOfAvailableCopies;
    int totalCopies;

    Book() {}

    Book(int totalCopies) {
        this.totalCopies = totalCopies;
        this.numberOfAvailableCopies = totalCopies;
    }

    void setDetails(int id, String title, int year, String author, String publisher, int count) {
        this.bookId = id;
        this.bookTitle = title;
        this.yearOfPublication = year;
        this.authorName = author;
        this.publisherName = publisher;
        this.totalCopies = count;
        this.numberOfAvailableCopies = count;
    }

    void getDetails(int id) {
        if (this.bookId == id) {
            System.out.println("Book ID: " + bookId);
            System.out.println("Title: " + bookTitle);
            System.out.println("Year: " + yearOfPublication);
            System.out.println("Author: " + authorName);
            System.out.println("Publisher: " + publisherName);
            System.out.println("Available Copies: " + numberOfAvailableCopies);
            System.out.println("Total Copies: " + totalCopies);
        }
    }
    
    void issue(int id) {
        if (this.bookId == id) {
            if (numberOfAvailableCopies > 0) {
                numberOfAvailableCopies--;
                System.out.println("Book issued successfully!");
            } else {
                System.out.println("No copies available to issue.");
            }
        }
    }

    void returnBook(int id) {
        if (this.bookId == id) {
            if (numberOfAvailableCopies < totalCopies) {
                numberOfAvailableCopies++;
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("All copies are already in the library.");
            }
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book[] books = new Book[5];
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book();
        }

        while (true) {

            System.out.println("\n   Library Menu     ");
            System.out.println("1. Set Details");
            System.out.println("2. Get Details");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Year of Publication: ");
                    int year = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Publisher Name: ");
                    String publisher = sc.nextLine();

                    System.out.print("Enter Total Copies: ");
                    int count = sc.nextInt();

                    books[id].setDetails(id, title, year, author, publisher, count);
                    System.out.println("Book details set successfully!");
                    break;

                case 2:
                    books[id].getDetails(id);
                    break;

                case 3:
                    books[id].issue(id);
                    break;

                case 4:
                    books[id].returnBook(id);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
