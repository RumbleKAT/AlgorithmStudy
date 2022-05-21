import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,C;
    static int [] dist;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("C:\\Users\\reki3\\desktop\\Spring_Boot_Mission\\mission4\\2110\\src\\com\\company\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        dist = new int[N];

        for(int i=0;i<dist.length;i++){
            token = new StringTokenizer(br.readLine());
            dist[i] = Integer.parseInt(token.nextToken());
        }

        Arrays.sort(dist);

        int low = 1;
        int high = dist[N-1] - dist[0];
        int result = 0;
        while(low <= high){
            int mid = (low+high)/2;
            if(possible(mid)){
                low = mid + 1;
                result = Math.max(result, mid);
            }else{
                high = mid - 1;
            }
        }
        System.out.println(result);
    }
    private static boolean possible(int mid) {
        int cnt =1;
        int prev = dist[0];
        for(int i=1;i<N;i++){
            if(dist[i] - prev >= mid){
                cnt++;
                prev = dist[i];
            }
        }
        if(cnt >= C){
            return true;
        }
        return false;
    }
}
