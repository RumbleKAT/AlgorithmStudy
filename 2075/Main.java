import java.io.*;
import java.util.*;

class Main{

    static int N;
    static long [][] arr;

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        arr = new long[N+1][N+1];

        PriorityQueue pq = new PriorityQueue<>(new Comparator<Long>(){
            @Override
            public int compare(Long o1, Long o2) {
                return (int)(o2 - o1);
            }
        });

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                arr[i][j] = Long.parseLong(token.nextToken());
                pq.add(arr[i][j]);
            }
        }

        for(int i =1;i<N;i++){
            pq.poll();
        }
        System.out.println(pq.poll());
    }
}