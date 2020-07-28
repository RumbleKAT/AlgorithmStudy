import java.util.*;
import java.io.*;

class Main{

    static Node [] node;
    static int [] seg;
    static int H;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(token.nextToken());
        
        node = new Node[N];

        token = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int val = Integer.parseInt(token.nextToken());
            node[i] = new Node(val, i);
        }

        /*
         배열을 순서대로 방문하면서, 새그먼트 트리를 업데이트
          원소값, 인덱스 역순으로 정렬
         마지막에 루트에 남은 값이 LIS의 길이가 된다. 
        */

        Arrays.sort(node,0,N,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.val == n2.val){
                    return n2.idx - n1.idx; //크기가 같으면 크기별로 내림차순
                }
                return n1.val - n2.val; //크기에 대해서 오름차순 정렬
            }
        });
        
        seg = new int [N*4];

        for(int i=0;i<N;i++){
            int idx = node[i].idx;
            int temp_max = getMax(1, 0, N-1, 0, idx);
            update(1, idx, temp_max+1, 0, N-1);

            /*
             정리 -> 정렬이 관건이 문제 (약간 탱크와 비슷함)
            
             6
             10 20 10 30 20 50
            
             로 입력을 받을 때,
             10 10 20 20 30 50 으로 정렬하되, 10 10 의 경우 인덱스 순으로 내림차순을 해준다.
             2  0  4  1  3  5  

             예를 들어, 첫번째 10인 경우, 
             0번 부터 2번까지 LIS 를 구간합으로 구한다. -> 당연히 0
             
             0 0 1 0 0 0 0 으로 새그먼트 트리 업데이트

             예를 들어, 두번째 10인 경우, 
             0번 부터 0번까지 LIS 를 구간합으로 구한다. -> 당연히 0

             1 0 1 0 0 0 0 으로 새그먼트 트리 업데이트

             20인경우 
             0부터 4까지 LIS를 구간합으로 구하면, 이전 최대값이 1이기에 2로 업데이트
             
             1 0 1 2 0 0 0 

             20인 경우 
             0부터 1까지 LIS를 구간합으로 구하면, 이전 최대값이 1이기에 2로 업데이트

             1 2 1 2 0 0 0

             30인 경우

             0부터 3까지 LIS를 구간합으로 구하면, 이전 최대값이 2로 업데이트 되어있음
             
             1 2 1 2 3 0 0 

              .....


             결국 

             0번부터 5번까지 최대값에 대한 갯수 구간합을 통해 LIS를 구할수 있음
            
            */


        }

        System.out.println(seg[1]);

    }
    static int update(int node, int index, int val, int start, int end){
        if(index < start || index > end) return seg[node];
        if(start == end) return seg[node] = Math.max(seg[node], val);
        int mid = (start + end)/2;
        return seg[node] = Math.max(update(node*2, index, val, start, mid),update(node*2+1, index, val, mid+1, end));
    }

    static int getMax(int node, int start, int end, int left, int right){
        if(start > right || end < left) return -1;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return Math.max(getMax(node*2, start, mid, left, right), getMax(node*2+1, mid+1, end, left, right));
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