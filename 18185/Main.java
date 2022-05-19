import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        arr = new int[100001];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        
        int answer = 0;
        for(int i=0;i<N;i++){
            if(arr[i+1] > arr[i+2]){
                int a = Math.min(arr[i], arr[i+1] - arr[i+2]);
                answer += 5*a;
                arr[i] -= a;
                arr[i+1] -= a;

                int b = Math.min(arr[i], Math.min(arr[i+1],arr[i+2]));
                answer += 7 * b;
                arr[i] -= b;
                arr[i+1] -= b;
                arr[i+2] -= b;
            }else{
                int b = Math.min(arr[i], Math.min(arr[i+1],arr[i+2]));
                answer += 7 * b;
                arr[i] -= b;
                arr[i+1] -= b;
                arr[i+2] -= b;

                int a = Math.min(arr[i], arr[i+1]);
                answer += 5 * a;
                arr[i] -= a;
                arr[i+1] -= a;
            }
            answer += 3 * arr[i];
        }
        System.out.println(answer);
    }
}