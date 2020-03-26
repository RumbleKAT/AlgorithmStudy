import java.util.*;
import java.io.*;

class Main {

    static int N;
    static long[] seg;
    static int[] arr;
    static int[] ans;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        seg = new long[N * 4];
        arr = new int[N + 1];
        ans = new int[N + 1];

        token = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(token.nextToken());
            update(1, i, 1, 0, N - 1);
        }

        System.out.println(Arrays.toString((seg)));

        for (int i = N - 1; i >= 0; i--) {
            int get = (int) query(1, 0, N - 1, arr[i] + 1);
            ans[get + 1] = i + 1;
        }

        for (int i = N; i >= 1; i--) {
            bw.write(ans[i] + "\n");
            bw.flush();
        }

    }

    static long query(int node, int start, int end, long val) {

        long get;
        if (start == end) {
            seg[node]--;
            return start;
        }

        int mid = (start + end) / 2;
        if (seg[node * 2] >= val) {
            get = query(node * 2, start, mid, val);
        } else {
            get = query(node * 2 + 1, mid + 1, end, val - seg[node * 2]);
        }
        seg[node]--;
        return get;
    }

    static void update(int node, int index, int val, int left, int right) {
        if (!(left <= index && index <= right)) {
            return;
        }
        seg[node] += val;

        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, index, val, left, mid);
            update(node * 2 + 1, index, val, mid + 1, right);
        }
    }

}