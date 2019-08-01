import java.util.*;
import java.io.*;

class Main{

    static int N, M;
    static final int MAX = 1025;
    static long [][] arr = new long[MAX][MAX];
    static long [][] seg = new long [MAX*4][MAX*4];
    static int h =1;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        while(h < N) h <<= 1;


        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j = 1;j<=N;j++){
                long temp = Long.parseLong(token.nextToken());
                arr[i][j] = temp;

                update(j, i, arr[i][j]);
            }
        }

    
        for(int i= 1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(token.nextToken());
            
            if(order == 1){
                int start = Integer.parseInt(token.nextToken());
                int end = Integer.parseInt(token.nextToken()); 

                int start_2 = Integer.parseInt(token.nextToken());
                int end_2 = Integer.parseInt(token.nextToken());

                if(start > start_2){
                    int tmp = start;
                    start = start_2;
                    start_2 = tmp;
                }
                if(end > end_2){
                    int tmp = end;
                    end = end_2;
                    end_2 = tmp;
                }
                System.out.println(queryY(1, 1, h, start, start_2, end, end_2));


            }else{
                int start = Integer.parseInt(token.nextToken());
                int end = Integer.parseInt(token.nextToken());   

                long val = Long.parseLong(token.nextToken());  

                update(end, start, val);
            }


        }

    }
    static void update(int x, int y, long d){
        int i = y + h-1;
        int j = x + h-1;
        seg[i][j] = d;
        while( j > 1){
            j /= 2;
            seg[i][j] = seg[i][j*2] + seg[i][j*2+1];
        }
        while(i>1){
            j = x + h -1;
            i /= 2;
            seg[i][j] = seg[i*2][j] + seg[i*2+1][j];
            while(j >1){
                j /=2;
                seg[i][j] = seg[i][j*2] + seg[i][j*2+1];
            }
        }
        return;
    }

    static long queryX(int y, int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[y][node];
        int mid = (start+end)/2;
        return queryX(y,node*2, start, mid, left, right) + queryX(y,node*2+1, mid+1, end, left, right); 
    }

    static long queryY(int node, int start, int end, int left, int right, int x1 , int x2){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return queryX(node,1, 1, h, x1, x2);
        int mid = (start+end)/2;
        return queryY(node*2,start,mid,left, right,x1,x2) + queryY(node*2+1,mid+1,end,left, right,x1,x2);
    }
    
}