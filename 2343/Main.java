import java.util.*;
import java.io.*;

class Main {
    static int N,M;
    static int [] arr;
    static int maxLen;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        //high를 곡들의 길이 합으로 두고 mid를 최대 녹음 시간이라고 가정했을때, 여부를 판단

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int [N+1];
        token = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
            maxLen += arr[i]; //1부터 N번까지
        }
        int ans = 0;
        int low = 1;
        int high = maxLen;

        while(low<=high){
            int mid = (low+high)/2;
            if(check(mid)){
                ans = mid;
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        System.out.println(ans);
        
    }
    static boolean check(int mid){
        int sum = 0;
        int num = 1;

        for(int i=1;i<=N;i++){
            if(arr[i] > mid) return false;
            sum += arr[i];
            if(sum > mid){
                sum = arr[i];
                num++;
            }
        }
        return M >= num; 
    }
}