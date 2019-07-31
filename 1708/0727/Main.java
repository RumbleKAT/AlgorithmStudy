import java.util.*;
import java.io.*;

class Main{

    static int N;
    static Point [] arr;

        public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        arr = new Point[N];

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());

            arr[i] = new Point(x, y);
        }

        Point [] result = convenHull(arr);
        Point [] re = new Point [result.length+1];
        for(int i = 1;i< re.length;i++){
            re[i] = result[i-1];
        }

        rotateCalipers(re, result.length);
    }


    static void rotateCalipers(Point [] P, int sp){
        int j,ni,nj;
        j = 2;
        long maxDist = -1;
        int n = sp;
        Point p = new Point();
        Point q = new Point();
        for(int i = 1;i<=n;i++){
            ni = i % n +1;
            while(true){
                nj = j % n +1;
                int c = ccw(new Point(0,0), new Point(P[ni].x - P[i].x,P[ni].y - P[i].y), new Point(P[nj].x - P[j].x,P[nj].y - P[j].y));
                if (c > 0){
                    j = nj;
                }else break;
            }
            long dist = dist(P[i], P[j]);
            if(dist > maxDist){
                maxDist = dist;
                p = P[i];
                q = P[j];
            }
        }

        System.out.println(Math.sqrt(maxDist));

    }

    static Point [] convenHull(Point [] P){
        if(P.length > 1){
            int n = P.length;
            int k = 0;
            Point [] H = new Point[n*2];
            
            Arrays.sort(P);

            for(int i = 0;i<n;i++){
                while(k >= 2 && cross(H[k-2], H[k-1], P[i]) <= 0){
                    k--;
                }
                H[k++] = P[i];
            }

            for(int i = n-1,t=k+1 ;i>=0;i--){
                while(k >= t && cross(H[k-2], H[k-1], P[i]) <= 0){
                    k--;
                }
                H[k++] = P[i];
            }

            if(H.length > 1){
                H = Arrays.copyOfRange(H, 0, k-1);
            }
            return H;
        }else if(P.length <= 1){
            return P;
        }else{
            return null;
        }
    }

    static int ccw(Point p1, Point p2, Point p3){
        long tmp = (p1.x*p2.y)+(p2.x*p3.y)+(p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);
        if(tmp > 0) return 1;
        else if(tmp < 0 ) return -1;
        else return 0;
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) +(p1.y - p2.y)*(p1.y - p2.y);
    }

    static int ccw_2(Point p1, Point p2, Point p3){
        long tmp = (p1.x*p2.y)+(p2.x*p3.y)+(p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);
        if(tmp < 0) return 1;
        else if(tmp > 0 ) return -1;
        else return 0;
    }

    static long cross(Point p1, Point p2, Point p3){
        long tmp = (p1.x*p2.y)+(p2.x*p3.y)+(p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);
        return tmp;
    }

}
class Point implements Comparable<Point>{
    public long x;
    public long y;

    Point(long x, long y){
        this.x = x;
        this.y = y; 
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
}