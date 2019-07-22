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
        arr = new long [N+1];

        token = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++){
            arr[i] = Long.parseLong(token.nextToken());
        }

        System.out.println(histogram(1,N));
    }

   
    static long histogram(int s, int e){
        if(s == e) return arr[s] * arr[s];

        int mid = (s+e)/2;
        long result = Math.max(histogram(s,mid), histogram(mid+1 ,e));

        int left  = mid;
        int right = mid;
        long h = arr[mid];
        long sum = arr[mid];

        while(right - left <= e - s){
            long p = left > s ? Math.min(h, arr[left-1]) : -1;
            int left_index = (p == -1) ? 0 : left-1;
            long q = right <  e ? Math.min(h, arr[right+1]) : -1;
            int right_index = (q == -1) ? 0 : right+1;
            //System.out.println(p + " " + q + " " + s + " " + e);
            //더 높은 높이를 가진 쪽으로 확장

            long partialLeftValue = (sum + arr[left_index]) * p;
            long partialRightValue = (sum + arr[right_index]) * q;

            if(partialLeftValue > partialRightValue){
                h = p;
                sum += arr[left_index];
                left--;
            }
            else{
                h = q;
                sum += arr[right_index];
                right++;
            }
            result = Math.max(result, h * sum);
        }
        return result;
    }
}