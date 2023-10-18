import java.util.*;
import java.io.*;

class Main{

    static int N,M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        int[] arr = new int[N];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        long answer = 0;
        long sum = 0;
        HashMap<Long, Long> remainderCounts = new HashMap<>();
        remainderCounts.put(0L, 1L); 

        for (int i = 0; i < N; i++) {
            sum += arr[i];
            long remainder = sum % M;
            remainder = (remainder + M) % M;  
            remainderCounts.put(remainder, remainderCounts.getOrDefault(remainder, 0L) + 1);
        }

        for (long count : remainderCounts.values()) {
            answer += (count * (count - 1)) / 2;
        }

        System.out.println(answer);

    }
}