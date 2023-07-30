import java.util.*;
import java.io.*;

class Main{

    static long X,Y;
    static int MAX = 1000000000;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        X = Long.parseLong(token.nextToken());
        Y = Long.parseLong(token.nextToken());
        long Z = (Y*100) / X;

        if(Z >= 99){
            System.out.println("-1");
            return;
        }

        int low = 0;
        int high = MAX;
        int result = -1;
        
        while(low <= high){
            int mid = (low + high)/2;
            long tempZ = (100*(Y+mid)) / (X+mid);
            if(Z >= tempZ){
                result = mid + 1;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}