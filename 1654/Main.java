import java.util.*;
import java.io.*;

public class Main {

    static int K,N;
    static int [] arr;
    static int max;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\1654\\src\\com\\company\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());

        arr = new int[K+1];
        max = 0;
        
        for(int i=1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());
            arr[i] = temp;
            max = Math.max(max,arr[i]);
        }

        Arrays.sort(arr);
        System.out.println(ParametricSearch(N));
    }
    static long ParametricSearch(int N){
        long low = 1;
        long high = max;
        long mid  = 0;

        while(low <= high){
            mid = (low + high)/2;
            long cur = check(mid);

            if(cur < N){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }
    static long check(long param){
        long cnt = 0;
        for(int i=1;i<=K;i++){
            cnt += (arr[i] / param);
        }
        return cnt;
    }
}
