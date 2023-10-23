import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        
        List<Node> list = new ArrayList<>();

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            list.add(new Node(s,e));
        }

        Collections.sort(list);

        for(Node n : list){
            System.out.println(n.x + " " + n.y);
        }

    }
}

class Node implements Comparable<Node>{
    public int x;
    public int y;

    Node(int x, int y){
        this.y = y;
        this.x = x;
    }

    @Override
    public int compareTo(Node o) {
        if(o.x == this.x){
            return this.y - o.y;
        }
        return this.x - o.x;
    }

    
    
}