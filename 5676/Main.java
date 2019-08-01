import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int K;
    static long [] seg;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        seg = new long [N*4];
        arr = new long [N+1];

        token = new StringTokenizer(br.readLine());
        for(int i = 1;i<=N;i++){
            int temp = Integer.parseInt(token.nextToken());
            if(temp > 0){
                temp = 1;
            }else if(temp == 0){
                temp = 0;
            }else{
                temp = -1;
            }
            //update(i, temp, 1, 1, N);
            arr[i] = temp;
        }
        // System.out.println(Arrays.toString(seg));

        init(1,1,N);

        for(int i =1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            char Order = token.nextToken().charAt(0);
            
            if(Order == 'C'){
                int s = Integer.parseInt(token.nextToken());
                int val = Integer.parseInt(token.nextToken());

                if(val > 0){
                    val = 1;
                }else if(val == 0){
                    val = 0;
                }else{
                    val = -1;
                }

                update(s, val, 1, 1, N);

            }else{
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());

                if(s > e){
                    int temp = s;
                    s = e;
                    e = temp;
                }

                long ans = query(1, 1, N,s,e);
                // System.out.println();
                // System.out.println("ans : " + ans + " " + s + " " +e);

                if(ans > 0){
                    System.out.print("+");
                }else if(ans == 0){
                    System.out.print("0");
                }else{
                    System.out.print("-");
                }
            }

        }
        
        System.out.println();
    }

    static long init(int node, int left, int right){
        if(left == right){
            return seg[node] = arr[left];
        }
        int mid = (left+right)/2;
        return seg[node] = init(node*2, left, mid)*init(node*2+1, mid+1, right);
    }

    static long update(int index, int val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(index, val, node*2, left, mid) * update(index, val, node*2+1, mid+1, right);
    }

    static long query(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 1;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return query(node*2, start, mid, left, right) * query(node*2+1, mid+1, end, left, right);
    }
}