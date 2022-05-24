import java.util.*;
import java.io.*;

class Main{

    static int N, K;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        arr = new int[N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[N-i] = Integer.parseInt(token.nextToken());
        }
        int idx = 0;
        int cnt = 0;

        while(K>0){
            int current = K;
            if(current/arr[idx] > 0){
                cnt += current / arr[idx];
                current %= arr[idx];
            }
            idx++;
            K = current;
        }
        System.out.println(cnt);
    }
}