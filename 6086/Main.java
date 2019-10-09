import java.util.*;
import java.io.*;

class Main{
    static int N;
    static LinkedList<Node> [] arr;
    static int [] prev;
    static int MAX = 1005;
    static int [][] flow;
    static int [][] capacity;

    static int CtoI(char c){
        if(c<='Z') return c - 'A';
        return c - 'a' +26;
    }

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        arr = new LinkedList[MAX];
        for(int i =0;i<MAX;i++){
            arr[i] = new LinkedList<>();
        }

        capacity = new int [MAX][MAX];
        flow = new int [MAX][MAX];

        N = Integer.parseInt(token.nextToken());
        for(int i =1;i<=N;i++){
            String [] temp = br.readLine().split(" ");
            int s = CtoI(temp[0].charAt(0));
            int e = CtoI(temp[1].charAt(0));
            int val = Integer.parseInt(temp[2]);

            capacity[s][e] += val;
            capacity[e][s] += val;

            // System.out.println(s + " " + e + " " + val);
            // System.out.println(arr.length);
            arr[s].add(new Node(e,val));
            arr[e].add(new Node(s,val));
        }


        int S = CtoI('A');
        int E = CtoI('Z');
        int total = 0;

        // System.out.println(S + " " + E); 

        //증가 경로를 못찾을 때까지 돌린다.
        while(true){
            int [] past = new int[MAX]; //경로 기억
            Arrays.fill(past, -1);
            
            Queue<Node> que = new LinkedList<>();
            que.add(new Node(S,0));//처음 시작점에서 계속 돌려야됨

            while(!que.isEmpty() && past[E] == -1){
                Node current = que.poll();
                System.out.println(current.idx);
                for(Node node : arr[current.idx]){
                    if(capacity[current.idx][node.idx] - flow[current.idx][node.idx]> 0 && past[node.idx] == -1){
                        past[node.idx] = current.idx;
                        que.add(new Node(node.idx,0));
                        if(node.idx == E) {
                            break; //목표점 도달시 나옴
                        }
                    }                    
                }
            }

            if(past[E] == -1) break; //가는 곳이 더 없다면 루프 탈출
            
            int minValue = Integer.MAX_VALUE;
            for(int i =E;i!=S;i=past[i]){
                //끝에서 시작점으로 움직임
                minValue = Math.min(minValue, capacity[past[i]][i] - flow[past[i]][i]);
            }

            for(int i =E;i!=S;i=past[i]){

                flow[past[i]][i] += minValue;
                flow[i][past[i]] -= minValue;
                System.out.println("min : " +minValue);
            }

            total += minValue;
        }

        System.out.println(total);

    }
}
class Node {
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}
