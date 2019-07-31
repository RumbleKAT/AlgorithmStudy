import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, y;
    
    Point(){

    }

	public int compareTo(Point p) {
		if (this.x == p.x) {
			return this.y - p.y;
		} else {
			return this.x - p.x;
		}
    }
    
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

	public String toString() {
		return "(" + x + "," + y + ")";
	}

}

public class Main {

	public static long cross(Point O, Point A, Point B) {
		return (A.x - O.x) * (long) (B.y - O.y) - (A.y - O.y) * (long) (B.x - O.x);
	}

	public static Point[] convex_hull(Point[] P) {

		if (P.length > 1) {
			int n = P.length, k = 0;
			Point[] H = new Point[2 * n];

			Arrays.sort(P);

			// Build lower hull
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}

			// Build upper hull
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}
			if (k > 1) {
				H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
			}
			return H;
		} else if (P.length <= 1) {
			return P;
		} else {
			return null;
		}
	}

	public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); 	// "hull.in"  Input Sample => size x y x y x y x y
        StringTokenizer st = new StringTokenizer(f.readLine());
        int TC = Integer.parseInt(st.nextToken());

        for(int t= 1;t<=TC;t++){
            st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        Point[] p = new Point[N];
		for (int i = 0; i < p.length; i++) {
            st = new StringTokenizer(f.readLine());
			p[i] = new Point();
			p[i].x = Integer.parseInt(st.nextToken()); // Read X coordinate 
			p[i].y = Integer.parseInt(st.nextToken()); // Read y coordinate
		}
		
        Point[] hull = convex_hull(p);
        Point [] rHull = new Point[hull.length+1];

        for(int i = 1;i<hull.length+1;i++){
            rHull[i] = hull[i-1];
        }
        rotateClipers(rHull,hull.length);
    }
	}
    static void rotateClipers(Point [] s,int sp){
        int i, j, ni, nj;
       j = 2;
       int cc;
       long rtn = -1;
       Point p = new Point();
       Point q = new Point();
       for (i = 1; i <= sp; i++) {
          ni = i % sp + 1;
          while (true) {
             nj = j % sp + 1;
             cc = ccw(new Point(0,0), new Point(s[ni].x - s[i].x,s[ni].y - s[i].y), new Point(s[nj].x - s[j].x,s[nj].y - s[j].y ));
             if (cc<0) {
                j = nj;
             }
             else break;
          }
          long dist = dist(s[i], s[j]);
          if (dist > rtn) {
             rtn = dist;
             p = s[i];
             q = s[j];
          }
       }
       System.out.println(p.x + " " + p.y + " " +q.x + " " +q.y);
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) +(p1.y - p2.y)*(p1.y - p2.y);
    }

    static int ccw(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y) + (p2.x * p3.y) + (p3.x * p1.y);
        tmp -= (p1.y * p2.x) + (p2.y * p3.x) + (p3.y * p1.x);
        if(tmp == 0){
            return 0;
        }else if(tmp > 0){
            return -1;
        }else{
            return 1;
        }
    }
}