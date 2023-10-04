import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] parents;
    static List<Integer> know = new ArrayList<>();
    static List<List<Integer>> v = new ArrayList<>();

    public static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parents[x] = y;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        token = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(token.nextToken());
        for (int i = 0; i < k; i++) {
            know.add(Integer.parseInt(token.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(token.nextToken());
            
            List<Integer> party = new ArrayList<>();
            int num, prev = 0;
            for (int j = 0; j < p; j++) {
                num = Integer.parseInt(token.nextToken());
                if (j >= 1) {
                    union(prev, num);
                } else {
                    prev = num;
                }
                party.add(num);
            }
            v.add(party);
        }

        for (List<Integer> list : v) {
            boolean flag = false;
            for (Integer person : list) {
                if (flag) break;
                for (Integer t : know) {
                    if (find(person) == find(t)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) m--;
        }
        System.out.println(m);
    }
}
