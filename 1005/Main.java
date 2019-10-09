import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int N,K;
    static LinkedList<Integer> []arr;
    static Stack <Integer> stack;
    static long [] dist;
    static boolean [] visited;
    static long [] times;
    static int [] past;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(token.nextToken());
            int K = Integer.parseInt(token.nextToken());
            
            arr = new LinkedList[N+1];
            visited = new boolean[N+1];
            past = new int[N+1];
            for(int i=1;i<=N;i++){
                arr[i] = new LinkedList<>();
            }

            stack = new Stack<>();
            dist = new long [N+1];
            times = new long[N+1];

            token = new StringTokenizer(br.readLine());
            for(int i =1;i<=N;i++){
                times[i] = Long.parseLong(token.nextToken());
            }

            for(int i =1;i<=K;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());

                arr[s].add(e);
                past[e]++;
            }


            Queue<Integer>que = new LinkedList();

            for(int i=1;i<=N;i++){
                if(past[i]==0)que.add(i); 
            }

            token = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(token.nextToken());

            while(past[d]>0){
                int n = que.poll();
                
                for(int next : arr[n]){
                    dist[next] = Math.max(dist[next],dist[n]+times[n]);
                    if(--past[next] == 0) que.add(next);
                }
            }

            long ans = dist[d] + times[d];
            
            bw.write(ans+"\n");
            bw.flush();
            }
        }
}
