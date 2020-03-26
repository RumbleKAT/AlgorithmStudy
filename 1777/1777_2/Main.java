import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[] arr;
    static int[] ans;
    static int[] seg;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        arr = new int[N + 1];
        ans = new int[N + 1];
        seg = new int[N * 4];

        token = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(token.nextToken());
            arr[i] = temp;
            update(1, 0, N - 1, i, 1);
        }

        for (int i = N - 1; i >= 0; i--) {
            int q = query(1, 0, N - 1, arr[i] + 1);
            ans[q + 1] = i + 1;
            // update(1, 1, N-1, q, 1);
        }

        for (int i = N; i >= 1; i--) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();

    }

    static int query(int node, int start, int end, int val) {
        int get;
        if (start == end) {
            seg[node]--;
            return start;
        }
        int mid = (start + end) / 2;
        if (seg[node * 2] >= val)
            get = query(node * 2, start, mid, val);
        else
            get = query(node * 2 + 1, mid + 1, end, val - seg[node * 2]);
        seg[node]--;
        return get;
    }

    static void update(int node, int start, int end, int index, int val) {
        if (!(start <= index && index <= end))
            return;
        seg[node] += val;
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, val);
            update(node * 2 + 1, mid + 1, end, index, val);
        }
    }
}