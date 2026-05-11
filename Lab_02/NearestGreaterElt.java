import java.util.Scanner;

public class NearestGreaterElt {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] stack = new int[n];
        int[] result = new int[n];

        int top = -1;
        System.out.println("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = (n-1); i >= 0; i--) {
            while (top >= 0 && stack[top] <= arr[i]) {
                top--;
            }
                            
            if (top == -1) {
                result[i] = -1;
            } else {
                result[i] = stack[top];
            }

            stack[++top] = arr[i];
        }

        System.out.println("Nearest Greater Elements:");
            for (int x : result) {
                System.out.print(x + " ");
            }
        sc.close();
    }
}

