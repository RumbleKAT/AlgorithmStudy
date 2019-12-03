import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

    static int N,M;
    static long [] prefix;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        prefix = new long [N+2];
        arr = new long [N+1];

        token = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
            prefix[i+1] = arr[i] + prefix[i];
        }

        System.out.println(Arrays.toString(prefix));

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            long res = prefix[e] - prefix[s] + arr[e];
            System.out.println(res);
        }

    }
}