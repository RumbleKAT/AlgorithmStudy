import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int [] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr, N);
        StringBuilder sb = new StringBuilder();
        for(int i=M-1;i<=N-M;i++){
            sb.append(segmentTree.max(1,i - (M - 1),i + (M - 1),0,arr.length-1) + " ");
        }   
        System.out.println(sb.toString());
    }
}
class SegmentTree{
    public long [] segmentTree;
    public int [] args;
    public int size;

    SegmentTree(int [] args, int size){
        this.args = args;
        this.size = size;
        this.segmentTree = new long[this.args.length*4];
        init(1, 0 ,this.args.length-1);
    }

    public void print(){
        System.out.println(Arrays.toString(segmentTree));
    }

    long init(int index, int left, int right){
        if(left == right) return segmentTree[index] = args[left];
        int mid = (left + right)/2;
        return segmentTree[index] = Math.max(init(index*2, left, mid), init(index*2+1, mid+1, right));
    }

    public long max(int index, int L, int R, int leftIdx, int rightIdx){
        if(R < leftIdx || rightIdx < L) return Integer.MIN_VALUE;
        if(L <= leftIdx && rightIdx <= R){
            return segmentTree[index];
        }
        int mid = (leftIdx + rightIdx) / 2;
        return Math.max(max(index*2, L, R, leftIdx, mid),max(index*2+1, L, R, mid+1, rightIdx)); 
    }
}