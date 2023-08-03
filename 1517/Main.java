import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int [] arr;
    static int [] tmp;
    static int count;

    public static void main(String [] args)throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(token.nextToken());
        arr = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        Map<Integer, Integer> pos = new HashMap<>();
		for(int i=0; i<N; i++) {
			pos.put(arr[i], i);
		}

        tmp = arr.clone();
        Arrays.sort(tmp);
        
        int max = 1000000000;
        SegmentTree segmentTree = new SegmentTree(N);
        long swap = 0;
        for(int i=0;i<N;i++){
            int idx = pos.get(tmp[i]);
            swap += segmentTree.query(idx+1, max);
            segmentTree.update(idx, 1);
        }

        System.out.println(swap);
    }
   
}
class SegmentTree {
    int[] tree;
    int N;

    SegmentTree(int N) {
        this.N = N;
        tree = new int[N * 4];
    }

    void update(int i, int val) {
        update(0, 0, N - 1, i, val);
    }

    void update(int node, int start, int end, int i, int val) {
        if (i < start || end < i) return;
        tree[node] += val;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(node * 2 + 1, start, mid, i, val);
        update(node * 2 + 2, mid + 1, end, i, val);
    }

    int query(int l, int r) {
        return query(0, 0, N - 1, l, r);
    }

    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return query(node * 2 + 1, start, mid, l, r) + query(node * 2 + 2, mid + 1, end, l, r);
    }
}
