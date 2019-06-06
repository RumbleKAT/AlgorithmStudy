import java.util.*;
import java.io.*;

class Main{

    //가운데에서 그리디 알고리즘으로 점점 넓혀짐 

    static int N;
    static long [] arr;
    static long [] dp;
    static long result;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        while(true){
            token = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(token.nextToken());

            if(N == 0){
                break;
            }

            arr = new long[N];
            for(int i =0;i<N;i++){
                arr[i] = Long.parseLong(token.nextToken());
            }

            System.out.println(histogram(0, N));

        }
               
    }

    static long histogram(int s, int e){
        if(s == e) return 0;
        if(s+1 == e) return arr[s];

        int mid = (s+e)/2;
        long result = Math.max(histogram(s,mid), histogram(mid, e));
    
        int w = 1;
        long h = arr[mid];
        int left  = mid;
        int right = mid;
        long sum = arr[mid];

        while(right - left +1 < e - s){
            long p = left > s ? Math.min(h, arr[left-1]) : -1;
            long q = right <  e - 1 ? Math.min(h, arr[right+1]) : -1;
            //System.out.println(p + " " + q + " " + s + " " + e);
            //더 높은 높이를 가진 쪽으로 확장
            if(p >= q){
                h = p;
                left--;
            }
            else{
                h = q;
                right++;
            }
            result = Math.max(result, ++w*h);
        }
        return result;
    }
}