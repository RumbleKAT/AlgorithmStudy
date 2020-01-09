import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static long [] dist;
    static Node [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new Node[N+1];
        dist = new long [N+1];
        Arrays.fill(dist,987654321);

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            arr[i] = new Node(s,e, val); //벨만은 시작 끝 가치를 같이 저장
        }

        dist[1] = 0;

        boolean check = false;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                int s = arr[j].start;
                int e = arr[j].end;
                int val = arr[j].dist;

                if(dist[s] != 987654321 && dist[e] > dist[s] + val){
                    dist[e] = dist[s] + val;
                    if(i == N){
                        check = true;
                    }
                }
            }
        }

        if(check){
            System.out.println(-1);
        }else{
            for(int i =2;i<=N;i++){
                if(dist[i] != 987654321){
                    System.out.println(dist[i] + " ");
                }else{
                    System.out.println(-1 + " ");
                }
            }
        }


    }
}
class Node implements Comparable<Node>{
    public int start;
    public int end;
    public int dist;

    Node(int start, int end, int dist){
        this.start = start;
        this.end = end;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node n1){
        return this.dist - n1.dist;
    }
}