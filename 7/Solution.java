import java.util.*;
import java.io.*;

class Solution{

    static int TC, N ,Q;
    static int start;
    static long seg[][],lazy [][];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        TC = Integer.parseInt(br.readLine());

        for(int t = 1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            Q = Integer.parseInt(token.nextToken());

            seg = new long [4][N*4];
            lazy = new long [4][N*4]; //항목이 1,2,3으로 들어가기 때문에 
            
            SegmentTree st = new SegmentTree(N);
            System.out.println(Arrays.toString(st.segIdx));
            
            long result = 0;

            for(int q=1;q<=Q;q++){
                token = new StringTokenizer(br.readLine());
                int method = Integer.parseInt(token.nextToken());

                if(method == 1){
                    //추가 해줌
                    int s = Integer.parseInt(token.nextToken());
                    int e = Integer.parseInt(token.nextToken());

                    int item = Integer.parseInt(token.nextToken());
                    long cnt = Long.parseLong(token.nextToken());

                    update_range(s, e, 1, 1, N, cnt, item);
                    long shop1 =  find(s, e, 1, 1, N, item);

                    System.out.println(shop1);
                }else if(method == 2){
                    //판매 
                    int s = Integer.parseInt(token.nextToken());
                    long c = Long.parseLong(token.nextToken());

                    // System.out.println("---------");
                    // System.out.println(s + " " + c);

                    long shop1 = seg[1][st.segIdx[s]];
                    long shop2 = seg[2][st.segIdx[s]];
                    long shop3 = seg[3][st.segIdx[s]];

                    long set = getMin(shop1, shop2, shop3);

                    System.out.println(shop1 + " " + shop2 + " " +shop3);

                    long pastSet = seg[0][st.segIdx[s]];
                   
                    long remain = set - pastSet;
                    // System.out.println("remain : " + remain);
                    if(remain >= c){
                        update_range(s,s,1,1,N,c,0);
                    }else{
                        // System.out.println("!!" + " " + remain);
                        update_range(s,s,1,1,N,remain,0);
                    }

                    // System.out.println(set + " " + pastSet);

                    // if(set - pastSet >= 0){
                    //     st.update(s, pastSet+remain, 1 , 0, N);
                    // }
                    // System.out.println();
                    // pastSet = st.get(1, 0, N, s, s);

                    // System.out.println("pastSet : " + pastSet);

                    // System.out.println(Arrays.toString(seg[1]));
                    // System.out.println(Arrays.toString(seg[2]));
                    // System.out.println(Arrays.toString(seg[3]));


                    // System.out.println(Arrays.toString(st.segmentTree));

                    // System.out.println("set : " + set + " " + shop1 + " " + shop2 + " " +shop3);

                    // System.out.println(shop1 + " " + shop2 + " " +shop3 + " " + set);
                    
                }else if(method == 3){
                    
                    int s = Integer.parseInt(token.nextToken());
                    int e = Integer.parseInt(token.nextToken());
                    //일반 세그먼트
                    // System.out.println();
                    // System.out.println(s + " " + e + "result : " + result + " " + " sum : " + st.sum(1,1,N,s,e));
                    // System.out.println();

                    //result += st.sum(1,1,N,s,e);
                    result += find(s, e, 1, 1, N, 0);
                    // System.out.println(Arrays.toString(st.segmentTree));
                }
            }

            System.out.println("#"+t + " " + result);
        }

    }
    static void update_range(int L, int R, int idx, int temp_L, int temp_R, long num, int item){
        if(lazy[item][idx] != 0){
            seg[item][idx] += (temp_R - temp_L+1) * lazy[item][idx];
            
            if(temp_R!=temp_L){
                lazy[item][idx*2] += lazy[item][idx];
                lazy[item][idx*2+1] += lazy[item][idx];
            }

            lazy[item][idx] = 0;
        }

        if(L > temp_R || R < temp_L) return;

        if(L <= temp_L && temp_R<=R){
            seg[item][idx] += (temp_R - temp_L+1)*num;

            if(temp_L != temp_R){
                lazy[item][idx*2] += num;
                lazy[item][idx*2+1] += num;
            }
            return;
        }
        int mid = (temp_L + temp_R)/2;

        update_range(L, R, idx*2, temp_L, mid, num, item);
        update_range(L, R, idx*2+1, mid+1, temp_R, num, item);
        seg[item][idx] = seg[item][idx*2] + seg[item][idx*2+1];
    }

    static long find(int L, int R, int idx, int temp_L, int temp_R, int item){
        if(lazy[item][idx] != 0){
            seg[item][idx] += (temp_R - temp_L+1) * lazy[item][idx];

            if(temp_R!= temp_L){
                lazy[item][idx*2] += lazy[item][idx];
                lazy[item][idx*2+1] += lazy[item][idx]; 
            }

            lazy[item][idx] = 0;
        }
        if(L > temp_R || R < temp_L) return 0;
        if(L <= temp_L && temp_R <= R) return seg[item][idx];
        int mid = (temp_L+temp_R)/2;
        return find(L,R,idx*2,temp_L,mid,item) + find(L,R,idx*2+1,mid+1,temp_R,item);
    }

    static long getMin(long a ,long b, long c){
        return Math.min(a,Math.min(b,c));
    }

}
class SegmentTree{
    public long [] segmentTree;
    public int [] segIdx;

    SegmentTree(int size){
        this.segmentTree = new long [size*4];
        this.segIdx = new int [size*4];
        
        init(1, 1, size);
    }

    private int init(int index, int left , int right){
        if(left == right){
            return segIdx[left] = index;
        }
        int mid = (left+right)/2;
        return init(index*2, left, mid) + init(index*2+1, mid+1, right);
    }

    public long update(int index, long newValue, int node, int left, int right){
        if(index < left || index > right) return segmentTree[node];
        if(left == right) return segmentTree[node] += newValue; //추가 차이만큼 더해줌
        int mid = (left+right)/2;
        return segmentTree[node] = update(index, newValue, node*2, left, mid) + update(index, newValue, node*2+1, mid+1, right);      
    }

    public long get(int node, int start, int end, int left, int right){
        
        if(start > right || end < left) return 0;
        if(left <= start && right >= end){
            // System.out.println("---");
            // System.out.println(left + " " +start + " "+ end + " " + node);
            return segmentTree[node];
        }
        int mid = (start+end)/2;
        return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }

    public long sum(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && right >= end) return segmentTree[node];
        int mid = (start+end)/2;
        return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
    }
}