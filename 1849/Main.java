import java.util.*;
import java.io.*;


class Main{

    static int MAX = 100001;
    static int N;
    static int [] seg = new int [MAX*4];
    static int [] arr = new int [MAX];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        for(int i =1;i<=N;i++){
            update(1,i,1,1,N);
        }//1로 넣고 구간합 미리 생성

        // System.out.println(Arrays.toString(seg));

        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());
            int sum = query(1,temp+1,1,N); //1부터 해당 등수 +1 구간합 
            arr[sum] = i;
            // System.out.println("sum : " + sum);
            // System.out.println(Arrays.toString(arr));
            update(1,sum,0,1,N); //구간합 index를 0으로 초기화
        }

        for(int i=1;i<=N;i++){
            bw.write(arr[i] + " ");
        }bw.flush();

    }
    static int update(int node, int index, int val, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right) return seg[node] = val;
        int mid = (left+right)/2;
        return seg[node] = update(node*2,index,val,left,mid) + update(node*2+1,index,val,mid+1,right);
    }

    static int query(int node, int val, int left, int right){ //순서 정하기 새그먼트 트리
        if(left == right) return left;
        int mid = (left+right)/2;
        if(seg[node*2] >= val) return query(node*2,val,left,mid);
        return query(node*2+1,val-seg[node*2],mid+1,right);
    }

}