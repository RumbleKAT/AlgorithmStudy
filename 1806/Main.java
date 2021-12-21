import java.util.*;
import java.io.*;

class Main{

    static int N,S;
    static int [] arr;
    
    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());

        arr = new int[N+1];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int min = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;

        int sum = arr[0];

        while(left <= right && right < N){
            if(sum < S){
                sum += arr[++right];
            }else if(sum == S){
                min = Math.min(min, (right - left + 1));
                sum += arr[++right];
            }else if(sum > S){
                min = Math.min(min, (right - left + 1)); //이상이 되는것..!
                sum -= arr[left++];
            }
        }
        
        if(min == Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(min);   
        }
    }
}