import java.util.*;
import java.io.*;

class Main{

    static int N;
    static Point [] arr;
    static Point [] newPoints;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        arr = new Point [N+1];
        newPoints = new Point[N+1];

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());
            arr[i] = new Point(x, y);
        }
        //N까지
        Arrays.sort(arr,0,N,new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                if(p1.x == p2.x) return p1.y - p2.y >= 0 ? 1 : -1;
                else return p1.x - p2.x >= 0 ? 1 : -1;
            }
        });

        LinkedList<Point> low = new LinkedList<>();
        LinkedList<Point> high = new LinkedList<>();

        for(int i = 0 ;i<N;i++){
            while(low.size()>=2 &&ccw_2(low.get(low.size()-2), low.getLast() , arr[i]) <= 0){
                low.removeLast();
            }
            low.add(arr[i]);
        }

        for(int i = N-1 ;i>=0;i--){
            while(high.size()>=2 &&ccw_2(high.get(high.size()-2), high.getLast() , arr[i]) <= 0){
                high.removeLast();
            }
            high.add(arr[i]);
        }

        low.poll();
        high.poll();

        low.addAll(high);

        System.out.println(low.size());

    }

    static int ccw(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);

        if(tmp == 0) return 0;
        else if(tmp > 0) return -1;
        else return 1;
    }

    static int ccw_2(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);

        if(tmp == 0) return 0;
        else if(tmp > 0) return 1;
        else return -1;
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
    }

    static long cross(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);
        return tmp;
    }
}
class Point{
    public long x;
    public long y;

    Point(long x, long y){
        this.x = x;
        this.y = y;
    }
}