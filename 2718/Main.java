import java.util.*;
import java.io.*;

class Main {

    static int MAX = 101;
    static int[] A;
    static int[] B;
    static int[] C;

    static int TC;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        A = new int[MAX];
        B = new int[MAX];
        C = new int[MAX];

        A[1] = 1;
        B[1] = 1;
        C[1] = 0;
        A[2] = 5;
        B[2] = 2;
        C[2] = 1;

        for (int i = 3; i < MAX; i++) {
            C[i] = A[i - 2] + C[i - 2];
            B[i] = B[i - 1] + A[i - 1];
            A[i] = B[i] + C[i - 2] + A[i - 2] + A[i - 2] + B[i - 1];
        }

        TC = Integer.parseInt(token.nextToken());

        for (int t = 1; t <= TC; t++) {
            String str = br.readLine();
            int temp = Integer.parseInt(str);
            System.out.println(A[temp]);
        }

    }
}