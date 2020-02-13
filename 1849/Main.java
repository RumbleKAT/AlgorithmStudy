import java.util.*;
import java.io.*;

class Main {

    static int N;
    static long[] seg;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        seg = new long[N * 4];
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++)
            update(1, i, 1, 1, N);

        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());
            int index = (int) query(1, 1, N, temp + 1);
            arr[index] = i;
            update(1, index, 0, 1, N);
        }

        for (int i = 1; i <= N; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();

    }

    static long query(int node, int start, int end, long val) {
        if (start == end)
            return start;
        int mid = (start + end) / 2;
        if (seg[node * 2] >= val)
            return query(node * 2, start, mid, val);
        else
            return query(node * 2 + 1, mid + 1, end, val - seg[node * 2]);

    }

    static long update(int node, int index, int val, int left, int right) {
        if (index < left || index > right)
            return seg[node];
        if (left == right) {
            return seg[node] = val;
        }
        int mid = (left + right) / 2;
        return seg[node] = update(node * 2, index, val, left, mid) + update(node * 2 + 1, index, val, mid + 1, right);
    }

}