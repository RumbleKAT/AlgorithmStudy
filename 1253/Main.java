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
            arr[i] = Integer.parseInt(token.nextToken());
        }

        Arrays.sort(arr);

        int goodCount = 0;
        
        for(int i =0;i<N;i++){
            if(isGoodNumber(arr, N, i)){
                goodCount++;
            }
        }
        System.out.println(goodCount);
    }
    private static boolean isGoodNumber(long [] A, int N, int index){
        long target = A[index];
        int left = 0;
        int right = N-1;

        while(left < right){
            if(left == index){
                left++;
                continue;
            }
            if(right == index){
                right--;
                continue;
            }

            long sum = A[left] + A[right];

            if(sum == target){
                return true;
            }else if(sum > target){
                right--;
            }else{
                left++;
            }
        }
        return false;
    }
}