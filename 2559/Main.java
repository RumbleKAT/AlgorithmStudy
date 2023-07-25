import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int K;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        int s = 0;
        int e = 0;
        long sum = arr[s];
        long max = Integer.MIN_VALUE;
        while(true){
            if(s+K > N || e >= N) break;
            if((e - s)+1 < K){
                sum += arr[++e];
            }
            else if((e-s)+1 == K){
                max = Math.max(max,sum);
                sum -= arr[s];
                s++;
            }
        }
        System.out.println(max);
    }
}