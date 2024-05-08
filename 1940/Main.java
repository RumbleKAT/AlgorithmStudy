import java.util.*;
import java.io.*;

class Main{

    static int N, M;
    static int [] arr;
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());
        
        token = new StringTokenizer(br.readLine());
        arr = new int [N+1];
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        Arrays.sort(arr,1,N+1);

        int left = 1;
        int right = N;
        int count = 0;

        while(left < right){
            int sum = arr[left] + arr[right];
            if(sum == M){
                count++;
                left++;
                right--;
            }else if(sum < M){
                left++;
            }else{
                right--;
            }
        }

        System.out.println(count);

    }
}