import java.util.*;
import java.io.*;

class Main{
    
    static int N;
    static long [] arr; 
    
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        arr = new long[N];

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Long.parseLong(token.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N-1;
        long MIN = Integer.MAX_VALUE;
        int res_left = left, res_right = right;

        while(left<right){
            long sum = arr[left] + arr[right];
            if(Math.abs(sum) < Math.abs(MIN)){
                MIN = sum;
                res_left = left;
                res_right = right;
            } 
            if(sum < 0){
                left++;
            }else{
                right--;
            }
        }

        System.out.println(arr[res_left] + " " + arr[res_right]);

    }
}