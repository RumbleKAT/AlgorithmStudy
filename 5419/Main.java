import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int size;
    static ArrayList<Point> arr;
    static long [] seg;
    static long ans;
    static int H;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t = 1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            arr = new ArrayList<>();
            size = Integer.parseInt(token.nextToken());
            ans = 0;
            
            for(int i = 0;i<size;i++){
                token = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(token.nextToken());
                int b = Integer.parseInt(token.nextToken());
                arr.add(new Point(a, b));
            }

            Collections.sort(arr,new Comparator<Point>(){
                @Override
                public int compare(Point p1, Point p2){
                    return p1.y - p2.y;
                }
            });

            // for(int i =1;i<=size;i++){
            //     System.out.println(arr[i].x + " " + arr[i].y);
            // }

            int rank = 1;
            arr.get(0).r = rank;
            for(int i = 1;i<size;i++){
                if(arr.get(i-1).y != arr.get(i).y) rank++;
                arr.get(i).r = rank;
            }

            //System.out.println(rank);

            H = 1;
            while(H<=rank) H*=2;
            seg = new long[H*2];

            Collections.sort(arr,new Comparator<Point>(){
                @Override
                public int compare(Point p1, Point p2){
                    if(p1.x == p2.x){
                        return p2.y - p1.y;
                    }else{
                        return p1.x-p2.x;
                    }
                }
            });


            long ans = 0;
            for(int i=0;i<size;i++){
                //System.out.println();
                ans += sum(1,1,rank, arr.get(i).r,rank);
                update(arr.get(i).r,1,1,1,rank);
            }
            
            bw.write(ans+"");
            bw.newLine();
            
            bw.flush();
        }
    }

    static long update(int index, long val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] += val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(index, val, node*2, left, mid) + update(index, val, node*2+1, mid+1, right);
    }

    static long sum(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return sum(node*2, start, mid , left, right) + sum(node*2+1, mid+1, end , left, right); 
    }

}
class Point{
    public int x;
    public int y;
    public int r;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}