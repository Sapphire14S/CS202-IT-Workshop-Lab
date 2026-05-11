import java.util.*;

class Addition {

    int add() {
        int n; 
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the no.of arguments: ");
        n = s.nextInt();
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        System.out.println("Result is: "+ res);
        s.close();
        return 0;
    }

    int add(int a, int b) {
        System.out.println("Sum of a & b is: " + (a+b));
        return 0;
    }

    int add(int a, int b, int c) {
        System.out.println("Sum of a, b & c is: " + (a+b+c));
        return 0;
    }

}

public class Sum {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Addition obj = new Addition();
        int choice;
        int a, b, c;
        System.out.print("Enter choice: ");
        choice = s.nextInt();
    
        switch (choice) {
            case 1:
                obj.add();
                break;
            
            case 2:
                System.out.print("Enter two arguments: ");
                a = s.nextInt();
                b = s.nextInt();
                obj.add(a, b);
            break;

            case 3:
                System.out.print("Enter three arguments: ");
                a = s.nextInt();
                b = s.nextInt();
                c = s.nextInt();
                obj.add(a, b, c);
            break;

            default:
                System.out.println("Invalid Choice! \nCheck your choice again.");
                break;
        }

        s.close();
    }
}