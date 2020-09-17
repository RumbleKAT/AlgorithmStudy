import java.util.*;
import java.io.*;

public class Main {
    //위상 정렬 문제 -> 순서가 있는 위상 정렬 

    static int N,M;
    static int [] indegree;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        indegree = new int[N+1];
        arr = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            indegree[e]++;
            arr[s].add(e);
        }

        PriorityQueue<Node> que = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            if(indegree[i]==0){
                que.add(new Node(i));
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!que.isEmpty()){
            Node cur = que.poll();

            sb.append(cur.val + " ");

            for(int next : arr[cur.val]){
                indegree[next]--;
                if(indegree[next]==0){
                    que.add(new Node(next));
                }
            }

        }

        System.out.println(sb.toString());

    }    
}
class Node implements Comparable<Node>{
    public int val;
    
    Node(int val){
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return this.val - n1.val;
    }
}