import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[] arr;
    static int[] seg;
    static int MAX = 1000100;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arr = new int[MAX + 1];
        seg = new int[MAX * 4];

        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());
            if (temp == 1) {
                int ans = Integer.parseInt(token.nextToken());
                int te = query(1, 0, MAX - 1, ans);
                bw.write(te + "\n");
                update(1, 0, MAX - 1, te, -1);
            } else {
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                update(1, 0, MAX - 1, s, e);
            }
        }

        bw.flush();

    }

    static int query(int node, int start, int end, long val) {
        if (start == end)
            return start;
        int mid = (start + end) / 2;
        if (seg[node * 2] >= val)
            return query(node * 2, start, mid, val);
        else
            return query(node * 2 + 1, mid + 1, end, val - seg[node * 2]);
    }

    static void update(int node, int start, int end, int index, int val) {
        if (!(start <= index && index <= end))
            return;
        seg[node] += val;

        int mid = (start + end) / 2;
        if (start != end) {
            update(node * 2, start, mid, index, val);
            update(node * 2 + 1, mid + 1, end, index, val);
        }
    }
}