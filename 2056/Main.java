import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer> [] list;
    static int n;
    static int [] cost;
    static int [] indegree;
    static int [] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        list = new ArrayList[N+1];
        indegree = new int[N+1];
        times = new int[N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            times[i] = Integer.parseInt(token.nextToken());
            indegree[i] = Integer.parseInt(token.nextToken());

            for(int j=0;j<indegree[i];j++){
                int cur = Integer.parseInt(token.nextToken());
                list[cur].add(i);
            }
        }

        cost = new int[N+1];

        Queue<Integer> que = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(indegree[i] == 0){
                que.add(i);
                cost[i] = times[i];
            }
        }

        while(!que.isEmpty()){
            int cur = que.poll();

            for(int next : list[cur]){
                cost[next] = Math.max(cost[next], cost[cur] + times[next]);
                indegree[next]--;
                if(indegree[next] == 0){
                    que.add(next);
                }
            }
        }

        int result = 0;
        for(int i=1;i<=N;i++){
            result = Math.max(result, cost[i]);
        }
        System.out.println(result);
    }
}