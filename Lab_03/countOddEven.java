import java.util.Scanner;

public class countOddEven {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n;

        int tc = 0;
        int totalEven = 0;
        int totalOdd = 0;

        while(n-- > 0) {
            tc++;
            
            int[] a = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = sc.nextInt();
            }
            
            int eCnt = 0;
            int oCnt = 0;

            for (int i = 0; i < m; i++) {
                if (a[i] % 2 == 0) {
                    eCnt++;
                } else {
                    oCnt++;
                }
            }
            
            totalEven += eCnt;
            totalOdd += oCnt;
            System.out.println("Test case " + (tc) + ": Even = " + eCnt + ", Odd = " + oCnt);


        }

            System.out.println("Overall: Even = " + totalEven + ", Odd = " + totalOdd);
        sc.close();
    }
}
