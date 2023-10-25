import java.util.*;
import java.io.*;


class Main{

    static int N;
    static int [] arr;
    static int max;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        arr = new int[N];
        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
        }
        max = 0;
        Arrays.sort(arr);

        for(int i=N-1;i>=0;i--){
            int current = arr[i] * (N - i) ;
            max = Math.max(max, current);
        }
        System.out.println(max);

    }
}