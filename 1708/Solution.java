import java.util.*;
import java.io.*;

class Solution{

    static int MAX ;
    static int size;

    static int ccw(Point p1, Point p2,Point p3){
        long tmp = (p1.x*p2.y)+(p2.x*p3.y)+(p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);

        if(tmp == 0) return 0;
        else if(tmp > 0) return -1;
        else return 1;
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
    }

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MAX = Integer.parseInt(br.readLine());

        Point[] points = new Point[MAX+1];

        for (int i = 0 ; i < MAX; i++){
            String[] line = br.readLine().split(" ");
            Long x = Long.valueOf(line[0]);
            Long y = Long.valueOf(line[1]);
            points[i] = new Point(x,y);
        }

        int minIdx = 0;
        for(int i = 1 ;i<MAX;i++){
            if(points[i].y < points[minIdx].y){
                minIdx = i;
            }else if(points[i].y == points[minIdx].y){
                if(points[i].x < points[minIdx].x){
                    minIdx = i;
                }
            }
        }

        Point tmp = points[0];
        points[0] = points[minIdx];
        points[minIdx] = tmp;

        Arrays.sort(points, 1, MAX, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                int c = ccw(points[0],p1,p2);
                if(c == 0){
                    return (dist(points[0], p2) - dist(points[0], p1) >= 0) ? -1 : 1;
                }return c;
            }
        });

        Point[] newPoints = new Point[MAX+1];
        newPoints[0] = points[MAX-1];

        for(int i = 1;i<=MAX;i++){
            newPoints[i] = points[i-1];
        }

        int M = 1;

        for(int i = 2;i<=MAX;i++){
            while(ccw(newPoints[M-1], newPoints[M], newPoints[i]) >= 0){
                if( M > 1){
                    M -= 1;
                    continue;
                }else if(i == MAX) break;
                else i += 1;
            }
            M++;

            Point temp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = temp;
        }

        System.out.println(M);

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