import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static long [] arr;
    static long sum;
    static long max;
    static ArrayList<Long> alist;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        arr = new long[1010];
        /*
            수열 A가 주어졌을때 A[i] + A[j] + A[k] = A[l]을 만족하는 가장 큰수
            A[i] + A[j] = A[l] - A[k]
            A[i] + A[j]를 배열에 저장하고 A[l]-A[k]와 같은 값이 있는 지 찾기
        */

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(token.nextToken());
        }        

        Arrays.sort(arr,0,N);
        alist = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<=i;j++){
                alist.add(arr[i]+arr[j]);
            }
        }

        Collections.sort(alist);

        long max = 0;

        for(int i=N-1;i>=0;i--){
            for(int j=i;j>=0;j--){
                if(Collections.binarySearch(alist, arr[i] - arr[j])!=-1)
                max = Math.max(max,arr[i]);
            }
            if(max != 0) break;
        }
        System.out.println(max);

    }
  
        //가능한 모든 조합을 사용한다.
}