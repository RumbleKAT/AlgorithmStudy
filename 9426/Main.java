import java.util.*;
import java.io.*;

class Main{

    static int N, K;
    static int [] arr;
    static int [] cnt;
    static int [] seg_c;
    static int [] segIdx;
    static int midIdx = 0;
    static int maxValue = 0;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        arr = new int[250001];
        cnt = new int[250001];
        seg_c = new int[1000004];
        segIdx = new int[1000004];

        int count = 0;
        long ans =0;

        for(int i = 1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(token.nextToken());
            arr[i] = tmp;
            maxValue = Math.math(maxValue,arr[i]);
            maxValue += 1;
            update(arr[i], 1, 1, 0, maxValue-1);
            if(i >= K){
                //중앙값을 구해야 됨
                if(i != K) update(arr[i-K], -1, 1, 0, maxValue-1);
                int pos = sum(1, 0, maxValue-1,(K+1)/2);
                ans += (long)pos;
            }

        }
        System.out.println(ans);
    }

  
    static int update(int index, int newValue, int node, int left, int right){
        if(left > index || right < index) return seg_c[node];
        else if(left == right){
            return seg_c[node] += newValue;
        }
        int mid = (left + right)/2;
        return seg_c[node] = update(index, newValue, node*2, left, mid) + update(index, newValue, node*2+1, mid+1, right);
    }

    static int sum(int index, int left,int right,int k){
        // System.out.println(left + " " + right);
        if(left == right){
            //리프 노드 일 때
            return left;
        }
        int mid = (left+right)/2;
        if(k <= seg_c[index*2]) return sum(index*2, left, mid,k);
        else{
            return sum(index*2+1, mid+1, right, k-seg_c[index*2]);
        }
    }

}