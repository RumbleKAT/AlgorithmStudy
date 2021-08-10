import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 21;
    static int [][][] dp;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;
        dp = new int[MAX][MAX][MAX];

        while(true){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());

            if(a == -1 && b == -1 && c == -1){
                break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("w(");
            sb.append(a+", ");
            sb.append(b+", ");
            sb.append(c+") = ");
            System.out.println(sb.toString() +dfs(a,b,c));
        }
    }
    static int dfs(int a, int b, int c){
        if(inRange(a,b,c) && dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(a > 20 || b > 20 || c > 20) return dp[20][20][20] = dfs(20,20,20);

        if(a < b && b < c){
            return dp[a][b][c] = dfs(a,b,c-1) + dfs(a,b-1,c-1) - dfs(a, b-1, c);
        }

        return dp[a][b][c] = dfs(a-1,b,c) + dfs(a-1,b-1,c) + dfs(a-1, b, c-1) - dfs(a-1,b-1,c-1);

    }

    static boolean inRange(int a, int b ,int c){
        return 0 <= a && a<= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20;
    }
}