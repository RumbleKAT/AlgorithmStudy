import java.util.*;
import java.io.*;

class Main{

    static int N = 10;
    static int [] arr = new int[N+1];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = null;
        
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
        }
        int closest_sum = 0;
        int current_sum = 0;

        for(int i=1;i<=N;i++){
            current_sum += arr[i];
            if(Math.abs(100 - current_sum) < Math.abs(100 - closest_sum)){
                closest_sum = current_sum;
            }else if(Math.abs(100 - current_sum) == Math.abs(100 - closest_sum)){
                closest_sum = Math.max(current_sum, closest_sum);
            }
            if(current_sum > 100 && (current_sum - 100) > Math.abs(100 - closest_sum)) break;
        }
        System.out.println(closest_sum);
    }
}