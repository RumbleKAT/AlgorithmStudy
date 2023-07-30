import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        arr = new int[1001];
        for(int i=2;i<=Math.sqrt(1000);i++){
            if(arr[i] == 0){
                for(int j=2;j<=1000;j++){
                    if(i*j <= 1000) arr[j*i] = 1;
                }
            }
        }
        N = Integer.parseInt(token.nextToken());
        int cnt = 0;
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(token.nextToken());
            if(temp == 1) continue;
            if(arr[temp] == 0){
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}