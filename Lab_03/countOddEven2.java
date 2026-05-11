import java.util.Scanner;

public class countOddEven2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n;

        int tc = 0;
        int totalEven = 0;
        int totalOdd = 0;

        int[][] arrays = new int[n][m]; 

        for (int t = 0; t < n; t++) {
            for (int i = 0; i < m; i++) {
                arrays[t][i] = sc.nextInt();
            }
        }

        for (int t = 0; t < n; t++) {
            tc++;
            int eCnt = 0, oCnt = 0;

            for (int i = 0; i < m; i++) {
                if (arrays[t][i] % 2 == 0) {
                    eCnt++;
                } else {
                    oCnt++;
                }
            }

            totalEven += eCnt;
            totalOdd += oCnt;
            System.out.println("Test case " + tc + ": Even = " + eCnt + ", Odd = " + oCnt);
        }

        System.out.println("Overall: Even = " + totalEven + ", Odd = " + totalOdd);
        sc.close();
    }
}
