import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int [] arr;
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N+1];

        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int left = 1;
        int right = 200000000;
        int max = 0;

        while(left <= right){
           int mid = (left+right)/2;
           
           int res = ps(mid);
           if(res < M){ //떡의 양이 부족한경우
               right = mid - 1;
           }else{
               max = Math.max(max,mid);   //높이의 최대값 
               left = mid +1;
           }
        }       
        
        System.out.println(max);
        
    }
    static int ps(int n){
        int sum = 0;
        for(int i=1;i<=N;i++){
            if(arr[i] - n  > 0){
                sum += arr[i] -n;
            }
        }
        return sum;
    }
}