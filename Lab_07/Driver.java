import java.util.*;

class Addition {

    Scanner s = new Scanner(System.in);
    int add() {
        System.out.print("Enter number of arguments: ");
        int n = s.nextInt();

        int sum = 0;
        System.out.print("Enter " + n + " numbers: ");
        for (int i = 0; i < n; i++) {
            sum += s.nextInt();
        }
        return sum;
    }

    int add(int a, int b) {
        return (a + b);
    }

    int add(int a, int b, int c) {
        return (a + b + c);
    }

}

public class Driver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Addition obj = new Addition();

        int result = 0;
        int choice;
        do {
            System.out.print("Enter choice (1: no args, 2: two args, 3: three args, 0: Exit): "); 
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    result = obj.add();
                    System.out.println("Result is: " + result);
                    break;
                case 2:
                    System.out.print("Enter two numbers: ");
                    result = obj.add(s.nextInt(), s.nextInt());
                    System.out.println("Result is: " + result);
                    break;
                case 3:
                    System.out.print("Enter three numbers: ");
                    result = obj.add(s.nextInt(), s.nextInt(), s.nextInt());
                    System.out.println("Result is: " + result);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        } while (choice != 0);

        s.close();
    }
}
