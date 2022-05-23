import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static int [] arr;
    static int [] cnt;
    static int [] count;
    static int [] ans;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int[N+1];
        cnt = new int[N+1];
        count = new int[N+1];
        ans = new int[N+1];

        //counting
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            cnt[arr[i]]++;
        }
        //counting sum 누적합 구함
        count[0] = cnt[0];
        for(int i=1;i<=N;i++){
            count[i] = count[i-1] + cnt[i];
        }
        
        //sorting
        for(int i=N;i>=1;i--){
            ans[count[arr[i]]] = arr[i];
          //  System.out.println(Arrays.toString(ans));

            count[arr[i]]--;
        }

        //System.out.println();
        //System.out.println(Arrays.toString(ans));

        for(int i=1;i<=N;i++){
            System.out.println(ans[i]);
        }

    }    
}
