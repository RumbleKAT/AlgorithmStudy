import java.io.*;
import java.util.*;

class Main{
    static int MAX ;
    static class Point{
        public final Comparator<Point> POLAR_ORDER = new PolarOrder();
        long x;
        long y;

        private class PolarOrder implements Comparator<Point> {
            //현재 점과 다음 점, 다다음 점의 ccw값을 구하고 만약 0일 경우 (a,c) >= (a,b)의 거리일경우 -1, 1 
            
            @Override
            public int compare(Point o1, Point o2) {
                System.out.println("-------");
                System.out.println(Point.this.x + " " + Point.this.y);
                System.out.println("-------");
                
                //각정렬
                
                int o = ccw(Point.this, o1, o2);
                if (o == 0)
                    return (dist(Point.this, o2) >= dist(Point.this, o1)) ? -1 : 1;
                return o; 
            }
        }
    }
    static long dist(Point p1, Point p2){
        return ((p1.x - p2.x)*(p1.x - p2.x)) + ((p1.y - p2.y)*(p1.y - p2.y));
     }
    //거리공식 Math.pow((x1 - x2),2) + Math.pow((y1- y2),2)

    static int ccw(Point A, Point B, Point C) {
        long value = (A.x*B.y + B.x*C.y + C.x*A.y) - (A.y*B.x + B.y*C.x + C.y*A.x);
        if (value == 0)
            return 0;
        else if (value > 0) //ccw는 0보다 크면 -1 // 0보다 작으면 1
            return -1; // counter clock wise
        else
            return 1; // clock wise
    }

    static void swap(Point a, Point b) {
        Point temp = new Point();
        temp = a;
        a = b;
        b = temp;
    }

    static int size;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MAX = Integer.parseInt(br.readLine());

        Point[] points = new Point[MAX+1];
        for (int i = 0 ; i < MAX; i++){
            points[i] = new Point();
        }

        for (int i = 0 ; i < MAX; i++){
            String[] line = br.readLine().split(" ");
            points[i].x = Long.valueOf(line[0]);
            points[i].y = Long.valueOf(line[1]);
        }

        int min_index = 0;

        for (int i = 1; i < MAX; i++) {
            if (points[i].y < points[min_index].y) min_index = i;
            else if (points[i].y == points[min_index].y) {
                if (points[i].x < points[min_index].x) min_index = i;
            }
        }
        //y값이 가장 낮은 인덱스를 찾고 만약 y값 인덱스가 같으면 x의 인덱스가 가장 낮은 것을 찾는다.

        //최저점의 인덱스를 찾아서 첫번째 포인트와 바꿔준다.
        Point temp = new Point();
        temp = points[min_index];
        points[min_index] = points[0];
        points[0] = temp;


        Arrays.sort(points, 1 , MAX, points[0].POLAR_ORDER);             

        Point[] newPoints = new Point[MAX+1];
        newPoints[0] = points[MAX-1];

        for (int i = 1 ; i < MAX+1; i++){
            newPoints[i] = new Point();
            newPoints[i] = points[i-1];
        }

        int M = 1;

        for (int i = 2; i <= MAX ; i++) {
            while (ccw(newPoints[M - 1], newPoints[M], newPoints[i]) >= 0) { //clockwise
                if (M > 1) {
                    M -= 1;
                    continue;
                }
                else if (i == MAX) break;
                else i += 1;
            }

            M += 1;

            temp = new Point();
            temp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = temp;
        }

        System.out.println(M);

    }

}