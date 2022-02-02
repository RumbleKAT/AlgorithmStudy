import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int [] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int left = 0;
        int right = 0;
        int mid = 0;
        int ans = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for(int i =0;i<N;i++){
            left = i;
            right = N;

            while(left != N){
                mid = (left+right)/2;
                if(arr[mid] - arr[i] == M){
                    ans = M;
                    break;
                }
                if(arr[mid] - arr[i] < M){
                    left = mid + 1;
                }else if(left == right){
                    ans = Math.min(ans, arr[left] - arr[i]);
                    break;
                }else{
                    right = mid;
                }
            }
        }

        System.out.println(ans);
    }
}
