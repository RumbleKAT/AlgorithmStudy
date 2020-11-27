import java.util.*;
import java.io.*;

class Main {

    static int N,L;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        L = Integer.parseInt(token.nextToken());
    
        arr = new int[N+1];
        token = new StringTokenizer(br.readLine());
        

        Deque<Node> deque = new LinkedList<>();

        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());

            while(!deque.isEmpty() && deque.getLast().val > arr[i]){
                deque.pollLast();
            }

            deque.add(new Node(arr[i], i));

            if(deque.getFirst().idx <= i - L){
                deque.pollFirst();
            }
            System.out.println(deque.getFirst().val);
            // bw.write(deque.getFirst().val + " ");
        }
        // bw.newLine();
        // bw.flush();

    }
}
class Node{
    public int val;
    public int idx;

    Node(int val, int idx){
        this.val = val;
        this.idx = idx;
    }
}