import java.util.*;
import java.io.*;

class Main{

    static int [] arr;
    static int [] seg;
    static int [] ans;
    static int N;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        
        arr = new int[N+1];
        seg = new int [N*4];
        ans = new int [N+1];

        for(int i =0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            update(1, i, 1, 0, N-1);
        }
        // System.out.println(Arrays.toString(seg));

        for(int i =0;i<N;i++){
            int temp = query(1,0,N-1, arr[i]+1);
            ans[temp+1] = i+1;
        }

        // System.out.println(Arrays.toString(ans));

        for(int i=1;i<=N;i++){
            bw.write(ans[i] + "\n");
        }
        bw.flush();
        
    }
    static int query(int node, int start, int end, int val){
        int ret = 0;
        if(start == end){
            seg[node]--;
            return start;
        } 
        int mid =(start+end)/2;
        if(seg[node*2] >= val){
            ret = query(node*2, start, mid, val);
        }else{
            ret = query(node*2+1, mid+1, end, val-seg[node*2]);
        }
        seg[node]--;
        return ret;
    }
    static void update(int node, int index, int val, int start, int end){
        if(!(start <= index && index <= end)) return;
        seg[node] += val;
        if(start != end){
            int mid = (start+end)/2;
            update(node*2, index, val, start, mid);
            update(node*2+1, index, val, mid+1, end);
        }
    }
}