import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int [] arr;
    static int target;
    static int max;

    public static void main(String[] args) throws Exception {
	   // System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        max = 0;
        arr = new int[N];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
            max = Math.max(max,arr[i]);
        }

        token = new StringTokenizer(br.readLine());
        target = Integer.parseInt(token.nextToken());

        System.out.println(ParametricSearch());
    }
    static int ParametricSearch(){
        int low = 1;
        int high = max;
        int ans = 0;
        int Psum = 0;

        while(low <= high){
            int mid = (low+high)/2;

            int sum =0;
            for(int i=0;i<arr.length;i++){
                if(arr[i] > mid){
                    sum += mid;
                }else{
                    sum += arr[i];
                }
            }

            if(sum > target){
                high = mid -1;
            }else{
                if(target >= sum){
                    if(Psum < sum){
                        Psum = sum;
                        ans = mid;
                    }
                }
                low = mid + 1;
            }
        }
        return ans;
    }
}
