import java.util.*;
import java.io.*;
import java.lang.*;

class Main{

    static int N;
    static Point [] arr;
    static Point [] newPoints;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new Point[N];

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());
            
            arr[i] = new Point(x,y);
        }
        
        Point [] result = convexHull(arr);
        Point [] re = new Point[result.length+1];

        for(int i = 1;i<re.length;i++){
            re[i] = result[i-1];
        }
        rotateCalipers(re, result.length);

    }

    static long ccw_2(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);
        // if(tmp == 0) return 0;
        // else if( tmp > 0) return 1;
        // else return -1;
        return tmp;
    }

    static int ccw_3(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);
        if(tmp == 0) return 0;
        else if( tmp > 0) return 1;
        else return -1;
    }

    static int ccw(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);
        if(tmp == 0) return 0;
        else if( tmp > 0) return -1;
        else return 1;
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y);
    }

    static Point [] convexHull(Point [] P){
        if(P.length > 1){
            int n = P.length;
            int k = 0;
            Point [] H = new Point[n*2];
            Arrays.sort(P);

            for(int i =0;i<n;i++){
                while(k >= 2 && ccw_2(H[k-2], H[k-1], P[i]) >= 0){
                    k--;
                }
                H[k++] = P[i];
            }

            for(int i =n-2, t = k+1 ;i>=0;i--){
                while(k >= t && ccw_2(H[k-2], H[k-1], P[i]) >= 0){
                    k--;
                }
                H[k++] = P[i];
            }

            if(k > 1){
                H = Arrays.copyOfRange(H, 0, k-1);
            }
            return H;

        }else if(P.length <= 1){
            return P;
        }else{
            return null;
        }
    }

    static void rotateCalipers(Point [] P, int sp){
        int j,ni,nj;
        int n = sp;
        j = 2;
        long maxDist = -1;
        Point p = new Point();
        Point q = new Point();

        for(int i = 1;i<=n;i++){
            ni = i % n + 1;
            while(true){
                nj = j % n + 1;
                int c = ccw_3(new Point(0,0), new Point(P[ni].x - P[i].x, P[ni].y - P[i].y), new Point(P[nj].x - P[j].x, P[nj].y - P[j].y));
                if(c < 0){
                    j = nj;
                }else break;
            }
            long dist = dist(P[i],P[j]);
            if(dist > maxDist){
                maxDist = dist;
                p = P[i];
                q = P[j];
            }
        }

        System.out.println(Math.sqrt(maxDist));
    }

    static boolean line_check(Line l1, Line l2){
        long a = ccw_4(l1.x1 , l1.y1, l1.x2,l1.y2,l2.x1,l2.y1) * ccw_4(l1.x1 , l1.y1, l1.x2,l1.y2,l2.x2,l2.y2);
        long b = ccw_4(l2.x1 , l2.y1, l2.x2,l2.y2,l1.x1,l1.y1) * ccw_4(l2.x1 , l2.y1, l2.x2,l2.y2,l1.x2,l1.y2);
        if( a == 0 && b == 0){
            return check(l1, l2);   
        }
        return a <= 0 && b <= 0;
    }

    static int ccw_4(long x1, long y1, long x2, long y2, long x3, long y3){
        long tmp = (x1*y2)+(x2*y3)+(x3*y1);
        tmp -= (y1*x2)+(y2*x3)+(y3*x1);
        if(tmp > 0 ) return 1;
        else if(tmp < 0) return -1;
        else return 0;
    }

    static boolean check(Line l1,Line l2){
        if(Math.max(l1.x1,l1.x2) < Math.min(l2.x1, l2.x2)) return false;
        if(Math.min(l1.x1,l1.x2) > Math.max(l2.x1, l2.x2)) return false;
        if(Math.max(l1.y1,l1.y2) < Math.min(l2.y1, l2.y2)) return false;
        if(Math.min(l1.y1,l1.y2) > Math.max(l2.y1, l2.y2)) return false;
        else return true;
    }

}

class Line {
    public long x1;
    public long y1;
    public long x2;
    public long y2;

    Line(long x1, long y1, long x2, long y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

class Point implements Comparable<Point>{
    public long x;
    public long y;
    public String tmp;

    Point(long x, long y){
        this.x = x;
        this.y = y;
        this.tmp = x+""+y;
    }

    Point(){}

    @Override
    public int compareTo(Point p){
        if (this.x == p.x) {
			return (int)(this.y - p.y);
		} else {
			return (int)(this.x - p.x);
		}
    }

    @Override
    public boolean equals(Object a) {
        Point obj = (Point) a;
        return (obj.x == this.x && obj.y == this.y);
    }

    @Override
    public int hashCode() {
        return this.tmp.hashCode();
    }
}