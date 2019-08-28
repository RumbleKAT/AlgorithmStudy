import java.util.*;

import com.sun.source.tree.Tree;

import java.io.*;

class Main{

    static int N;
    static int [] seg;
    static Node [] arr;
    static TreeSet<Integer> ySet;
    static HashMap<Integer, Integer> yMap;
    static int [] S;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        arr = new Node[N*2];
        S = new int[N];
        ySet = new TreeSet<>();
        yMap = new HashMap<>();

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int y1 = Integer.parseInt(token.nextToken());

            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());

            arr[i] = new Node(x1,y1,-1,i);
            arr[i+N] = new Node(x2,y2,1,i);
            ySet.add(y1);
            ySet.add(y2);
        }

        Arrays.sort(arr,0,N*2,new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                if(n1.x == n2.x) return n1.z - n2.z;
                else return n1.x - n2.x;
            }
        });

        int ys = ySet.size();
        seg = new int [ys*4+1];

        for(int i = 1;i<=ys;i++){
            yMap.put(ySet.pollFirst(), i);
        }

        int max = 0;

        for(int i = 0;i<N*2;i++){
            Node current = arr[i];
            int yPos = yMap.get(current.y);

            if(current.z == -1){
                int cnt = sum(1,1,ys,1,yPos-1);
                S[current.idx] = cnt+1;
            }else{
                update(yPos, S[current.idx],1,1,ys);
                max = Math.max(max, S[current.idx]);
            }
        }

        bw.write(max+"\n");
        bw.flush();
    }

    static int sum(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return Math.max(sum(node*2, start, mid, left, right),sum(node*2+1, mid+1, end, left, right));
    }

    static int update(int index, int val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = Math.max(seg[node],val);
        }
        int mid = (left+right)/2;
        return seg[node] = Math.max(update(index, val, node*2, left, mid), update(index, val, node*2+1, mid+1, right));
    }
}
class Node{
    public int x;
    public int y;
    public int z;
    public int idx;

    Node(int x, int y, int z, int idx){
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}