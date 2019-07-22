import java.io.*;
import java.util.*;

class Main{
    static int MAX ;
    static class Point{
        public final Comparator<Point> POLAR_ORDER = new PolarOrder();
        long x;
        long y;

        private class PolarOrder implements Comparator<Point> {
            @Override
            public int compare(Point o1, Point o2) {
                int o = ccw(Point.this, o1, o2);
                if (o == 0)
                    return (dist(Point.this, o2) >= dist(Point.this, o1)) ? -1 : 1;
                return o; 
            }
        }
    }
    static long dist(Point p1, Point p2){
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static int ccw(Point A, Point B, Point C) {
        long value = (A.x*B.y + B.x*C.y + C.x*A.y) - (A.y*B.x + B.y*C.x + C.y*A.x);
        if (value == 0)
            return 0;
        else if (value > 0)
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
    static int L;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        MAX = Integer.parseInt(token.nextToken());
        L = Integer.parseInt(token.nextToken());

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

        //show(points);

        Point temp = new Point();
        temp = points[min_index];
        points[min_index] = points[0];
        points[0] = temp;

        Arrays.sort(points, 1 , MAX, points[0].POLAR_ORDER);      
        
        //show(points);
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

        //show(newPoints);

        //System.out.println(M);
        double ans = 0;

        for(int i = 0;i<M;i++){
            ans += get_dist(newPoints[i],newPoints[i+1]);
        }

        ans += 2 * L * Math.PI;
        System.out.println(Math.round(ans));

    }
    static double get_dist(Point p1, Point p2){
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y));
    }

    static void show(Point [] arr){
        System.out.println("----------------------------");
        for(int i = 0;i<arr.length-1;i++){
            System.out.println(arr[i].x + " "  +arr[i].y);
        }
        System.out.println("----------------------------");
    }

}