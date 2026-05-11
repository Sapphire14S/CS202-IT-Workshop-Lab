import java.util.*;

class MovieHall {
    private int availableSeats;

    public MovieHall(int seats) {
        availableSeats = seats;
    }

    // Booking with Synchronization 
    public synchronized boolean bookTickets(int numSeats, String userName) {
        System.out.println("> " + userName + " trying to book " + numSeats + " seats...");

        if (numSeats <= availableSeats) {
            System.out.println("> Booking successful for " + userName);
            availableSeats -= numSeats;
            System.out.println("> Seats remaining: " + availableSeats);
            return true;
        } else {
            System.out.println("> Not enough seats for " + userName + ". Only " + availableSeats + " left.");
            return false;
        }
    }

    // Booking without Synchronization (for race condition) 
    public boolean bookTicketsWithoutSync(int numSeats, String userName) {
        System.out.println("> " + userName + " trying to book " + numSeats + " seats...");

        if (numSeats <= availableSeats) {
            System.out.println("> Booking successful for " + userName);
            availableSeats -= numSeats;
            System.out.println("> Seats remaining: " + availableSeats);
            return true;
        } else {
            System.out.println("> Not enough seats for " + userName + ". Only " + availableSeats + " left.");
            return false;
        }
    }

    // Booking with wait() and notifyAll() 
    public synchronized void waitAndBook(int numSeats, String userName) {
        while (numSeats > availableSeats) {
            System.out.println("> " + userName + " is waiting for seats to become available...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        availableSeats -= numSeats;
        System.out.println("> " + userName + " booked " + numSeats + " seats successfully!");
        System.out.println("> Seats remaining: " + availableSeats);
    }

    // Cancel tickets 
    public synchronized void cancelTickets(int numSeats, String userName) {
        availableSeats += numSeats;
        System.out.println("> " + userName + " cancelled " + numSeats + " seats.");
        System.out.println("> Seats now available: " + availableSeats);
        notifyAll(); // notify waiting threads
    }
}

// Thread Class 
class User extends Thread {
    private MovieHall hall;
    private int seatsToBook;
    private String userName;
    private boolean useSync;
    private boolean useWaitNotify;

    public User(MovieHall hall, int seatsToBook, String userName, boolean useSync, boolean useWaitNotify) {
        this.hall = hall;
        this.seatsToBook = seatsToBook;
        this.userName = userName;
        this.useSync = useSync;
        this.useWaitNotify = useWaitNotify;
    }

    public void run() {
        if (useWaitNotify) {
            hall.waitAndBook(seatsToBook, userName);
        } else if (useSync) {
            hall.bookTickets(seatsToBook, userName);
        } else {
            hall.bookTicketsWithoutSync(seatsToBook, userName);
        }
    }
}

// Main Class 
public class BookingSystem { 
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------");
        System.out.print("Enter total seats in Movie Hall: ");
        int totalSeats = sc.nextInt();

        MovieHall hall = new MovieHall(totalSeats);

        System.out.print("Enter number of users trying to book: ");
        int numUsers = sc.nextInt();

        System.out.println("\nChoose booking mode:");
        System.out.println("1. Without Synchronization (see race condition)");
        System.out.println("2. With Synchronization (safe booking)");
        System.out.println("3. With Wait & Notify (auto wait if full, cancel to free seats)");
        System.out.print("Enter choice: ");
        int mode = sc.nextInt();

        boolean useSync = (mode == 2);
        boolean useWaitNotify = (mode == 3);

        Thread[] users = new Thread[numUsers];

        for (int i = 0; i < numUsers; i++) {
            System.out.print("Enter seats User-" + (i + 1) + " wants to book: ");
            int seatsToBook = sc.nextInt();
            users[i] = new User(hall, seatsToBook, "User-" + (i + 1), useSync, useWaitNotify);
        }

        // Start all threads
        for (Thread t : users) t.start();

        // If using wait-notify mode, allow cancellation 
        if (mode == 3) {
            System.out.println("\nEnter number of seats to cancel: ");
            int cancel = sc.nextInt();
            hall.cancelTickets(cancel, "Admin");
        }

        // Wait for all threads to complete
        for (Thread t : users) t.join();

        sc.close();
        System.out.println("\n        Thanks for booking with MovieHall! Enjoy your show!");
        System.out.println("---------------------------------------------------------------------");
    }
}
