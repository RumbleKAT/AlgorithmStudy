import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int [] arr;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("C:\\Users\\reki3\\desktop\\Spring_Boot_Mission\\mission4\\2110\\src\\com\\company\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arr = new int[N];
        token = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(arr);

        int s = 0;
        int e = N-1;
        long diff = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        while(s < e){
            long sum = (arr[s] + arr[e]);
            long temp = Math.abs(sum);
            if(diff > temp){
                diff = temp;
                a = arr[s];
                b = arr[e];
            }
            if(sum > 0){
                e--;
            }else{
                s++;
            }
        }
        System.out.println(a + " " + b);

    }
}
