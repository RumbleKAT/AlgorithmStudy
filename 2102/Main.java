import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[] arr;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N + 1];
        dp = new long[N + 2];

        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            dp[i + 1] = dp[i] + arr[i];
        }

        double low = 0;
        double high = 2000;
        double mid = 0;

        for (int i = 0; i < 100; i++) {
            mid = (low + high) / 2;
            if (find(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        int res = (int) (mid * 1000);

        System.out.println(res);

    }

    static boolean find(double x) {
        double left = 0;
        double right = 0;
        double res = 0;

        for (int i = 1; i < M; i++)
            right += arr[i] - x;

        for (int i = M; i <= N; i++) {
            right += arr[i] - x;
            if (left < res)
                res = left;
            if (right >= res)
                return true;
            left += arr[i - M + 1] - x;
        }
        return false;
    }
}