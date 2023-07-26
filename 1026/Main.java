import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] A;
    static Integer [] B;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        
        A = new int[N];
        B = new Integer[N];

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            B[i] = Integer.parseInt(token.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B,Collections.reverseOrder());
        int sum = 0;
        for(int i=0;i<A.length;i++){
            sum += (A[i] *B[i]);
        }
        System.out.println(sum);
    }
}