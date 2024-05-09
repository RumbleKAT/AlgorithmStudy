import java.util.*;
import java.io.*;


class Main{

    static int N;
    static int [] arr;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        pq = new PriorityQueue<>();
        arr = new int[N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int current = Integer.parseInt(token.nextToken());
            pq.add(current);
        }

        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll(); 
            int second = pq.poll();
            int sum = first + second;
            res += sum;
            pq.add(sum);
        }
        System.out.println(res);

    }  
}