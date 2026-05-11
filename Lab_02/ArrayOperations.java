import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("A. Find the sum of all elements");
            System.out.println("B. Find the maximum element");
            System.out.println("C. Search for a given element");
            System.out.println("D. Exit");
            System.out.print("Enter your choice: ");

            char choice = sc.next().toUpperCase().charAt(0);
            
            switch (choice) {
                case 'A':
                    int sum = 0;
                    for (int num : arr) {
                        sum += num;
                    }
                    System.out.println("Sum of all elements: " + sum);
                    break;

                case 'B':
                    int max = arr[0];
                    for (int num : arr) {
                        if (num > max) {
                            max = num;
                        }
                    }
                    System.out.println("Maximum element: " + max);
                    break;

                case 'C':
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == key) {
                            System.out.println("Element " + key + " found at index " + i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Element " + key + " not found in the array.");
                    }
                    break;

                case 'D':
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
