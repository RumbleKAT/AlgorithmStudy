import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[] price;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        price = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        token = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(token.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            union(s, e);
        }

        int cost = 0;
        boolean[] checked = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            if (!checked[root]) {
                cost += price[root];
                checked[root] = true;
            }
        }

        if (cost <= K) {
            System.out.println(cost);
        } else {
            System.out.println("Oh no");
        }
    }

    static int find(int num) {
        if (parent[num] == num) return num;
        return parent[num] = find(parent[num]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (price[rootA] < price[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootA] = rootB;
            }
        }
    }
}
