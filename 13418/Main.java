import java.util.*;
import java.io.*;

class Main{

    static boolean [] visited;
    static int N,M;
    static LinkedList<Node>[] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N+1];
        visited = new boolean [N+1];
        for(int i=0;i<=N;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i=1;i<=M+1;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());
            
            // if(c == 1){
            //     c = 0;
            // }else{
            //     c = 1;
            // }
            arr[s].add(new Node(e,c));
            arr[e].add(new Node(s,c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                return (int)(n1.val - n2.val);
            }
        });

        long ans_1 = 0;
        pq.add(new Node(0,1));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            // System.out.println(current.idx + " " + current.val);

            if(visited[current.idx]) continue;
            visited[current.idx] = true;

            if(current.val == 0){
                // System.out.println(current.idx + "!!");
                ans_1++;
            }

            for(Node node : arr[current.idx]){
                if(!visited[node.idx]){
                    pq.add(node);
                    // break;
                }
            }
        }

        // System.out.println(ans_1);

        Arrays.fill(visited, false);

        pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                return (int)(n2.val - n1.val);
            }
        });

        long ans_2 = 0;
        pq.add(new Node(0,1));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            // System.out.println(current.idx + " " + current.val);

            if(visited[current.idx]) continue;
            visited[current.idx] = true;

            if(current.val == 0){ //오르막 길인 경우
                ans_2++;
            }

            for(Node node : arr[current.idx]){
                if(!visited[node.idx]){
                    pq.add(node);
                    // break;
                }
            }
        }

        System.out.println(ans_1*ans_1 - ans_2*ans_2);


    }
}
class Node{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}