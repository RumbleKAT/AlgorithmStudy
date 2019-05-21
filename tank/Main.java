import java.util.*;
import java.io.*;

class Main{
    static int TC;
    static int N;

    static Node [] Arr;
    static Node [] xAxisArr;
    static Node [] yAxisArr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;        
        TC = Integer.parseInt(br.readLine());

        for(int t=1;t<=TC;t++){
            N = Integer.parseInt(br.readLine());
            
            Arr = new Node [N+1];
            xAxisArr = new Node[N+1];
            yAxisArr = new Node[N+1];

            for(int n =1;n<=N;n++){
                token = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(token.nextToken());
                int y = Integer.parseInt(token.nextToken());
                int val = Integer.parseInt(token.nextToken());

                Arr[n] = new Node(x,y,val);
                xAxisArr[n] = new Node(x,y,val);
                yAxisArr[n] = new Node(x,y,val);
            }            

            Arrays.sort(xAxisArr,1,N+1,new Comparator<Node>(){
                @Override
                public int compare(Node o1, Node o2){
                    if(o1.x >= o2.x){
                        return 1;
                    }else{
                        return -1;
                    }
                }
            });

            Arrays.sort(yAxisArr,1,N+1,new Comparator<Node>(){
                @Override
                public int compare(Node o1, Node o2){
                    if(o1.y >= o2.y){
                        return 1;
                    }else{
                        return -1;
                    }
                }
            });

            //y축으로 기준 값을 먼저 정하고
            //그 값보다 큰 값을 모두 배열로 
            //x축 기준으로 기준 값보다 큰 값을 구함 
            
            //배열 이내에 없는 값을 추출

            System.out.println(Arrays.toString(yAxisArr));
            System.out.println(Arrays.toString(xAxisArr));

            //y축의 값을 기준으로 세그먼트 트리를 그리고
            //해당 인덱스 보다 x축이 작은 애들을 빼준다. 0번 부터 N번 까지 리스트를 넣고
            //새그먼트 부분 합을 구할 때 delete 리스트에 들어가는 애들인게 확인 되면 제외한다. (binary search)

            // for(int i = 1;i<yAxisArr.length;i++){
            //     Node current = yAxisArr[i];
            //     Arrays.binarySearch(, key)
            // }

        }
    }

}
class SegmentTree{
    public Node [] args;
    public Node [] segmentTree;
    public int size;

    public SegmentTree(Node [] args){
        this.args = args;
        this.size = this.args.length * 4;
        segmentTree = new Node [this.size];
        init(1,0,this.args.length-1);
    }

    long init(int index, int left, int right){
        if(left == right){
            return segmentTree[index].value = args[left].value;
        }
        int mid = (left+right)/2;
        return segmentTree[index].value = init(index*2, left, mid) + init(index*2+1,mid+1, right);
    }

    public long sum(int index, int L, int R, int left, int right){
        if(R < left || L > right) return 0;
        if(L <= left && right <= R){ 
            return segmentTree[index].value;
        }
        int mid = (left+right)/2;
        return segmentTree[index].value = sum(index*2, L, R, left, mid) + sum(index*2+1, L, R, mid+1, right);
    }

}


class Node{
    public long x = -1;
    public long y = -1;
    public long value = -1;

    Node(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
    //각 좌표 점 대로 정렬
    public String toString(){
        if(this.x == -1){
            return "\n";
        }else{
            return value + " ";
        }
    }
}