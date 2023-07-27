import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int [][] items;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        int[][] items = new int[2][N];
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[0][i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[1][i] = Integer.parseInt(token.nextToken());
        }
        // Calculate the total memory to decide the size of the DP array
        int totalMemory = 0;
        for (int i = 0; i < N; i++) {
            totalMemory += items[0][i];
        }

        // Initialize the DP array with maximum values
        int[] dp = new int[totalMemory + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = totalMemory; j >= items[0][i]; j--) {
                if (dp[j - items[0][i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - items[0][i]] + items[1][i]);
                }
            }
        }

        // Find the minimum cost for memory equal to or larger than minMemory
        int minCost = Integer.MAX_VALUE;
        for (int i = M; i <= totalMemory; i++) {
            minCost = Math.min(minCost, dp[i]);
        }

        System.out.println(minCost);
    }
}