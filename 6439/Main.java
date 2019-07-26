import java.util.*;
import java.io.*;

class Main{
    
    static int TC;
    static Point [] arr;
    static Line line;
    static Line [] line_check;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        TC = Integer.parseInt(token.nextToken());

        for(int t= 1; t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            long xs = Long.parseLong(token.nextToken()); 
            long ys = Long.parseLong(token.nextToken()); 

            long xe = Long.parseLong(token.nextToken()); 
            long ye = Long.parseLong(token.nextToken()); 

            long sx = Long.parseLong(token.nextToken()); 
            long sy = Long.parseLong(token.nextToken()); 

            long ex = Long.parseLong(token.nextToken()); 
            long ey = Long.parseLong(token.nextToken()); 

            long t1x = sx;
            long t1y = ey;

            long t2x = ex;
            long t2y = sy;

            line = new Line(xs, ys, xe, ye);
            line_check = new Line [5];

            arr = new Point[5];

            arr[0] = new Point(sx,sy);
            arr[1] = new Point(ex,ey);
            arr[2] = new Point(t1x,t1y);
            arr[3] = new Point(t2x,t2y);
            
            line_check[1] = new Line(arr[0].x, arr[0].y , arr[1].x, arr[1].y);
            line_check[2] = new Line(arr[1].x, arr[1].y , arr[2].x, arr[2].y);
            line_check[3] = new Line(arr[2].x, arr[2].y , arr[3].x, arr[3].y);
            line_check[4] = new Line(arr[3].x, arr[3].y , arr[0].x, arr[0].y);
            boolean flag = false;
            
            for(int i = 1;i<line_check.length;i++){
                if(cross_line(line_check[i],line)){
                    flag = true;
                    // System.out.println("겹침");
                    break;
                }
            }
            
            if(flag){
                System.out.println("T");
                continue;
            }

            arr[4] = new Point(xs,ys);
        
            Point [] result = convexHull(arr);  
            int count = 0;          

            for(Point next : result){
                if(next.x == xs && next.y == ys){
                    //점이 외부에 있다.
                    //System.out.println("외부에 있음");
                    count++;
                }
            }

            arr[4] = new Point(xe,ye);
       
            result = convexHull(arr);            


            for(Point next : result){
                if(next.x == xe && next.y == ye){
                    //점이 외부에 있다.
                    //System.out.println("외부에 있음");
                    count++;
                }
            }

            if(count == 0){
                flag = true;
            }
        
            if(flag){
                System.out.println("T");
            }else{
                System.out.println("F");
            }



        }
    }

    static boolean cross_line(Line l1, Line l2){
        long line1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        long line2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);
        
        if(line1 == 0 && line2 == 0){
           return check(l1, l2);
        }
        return line1 <= 0 && line2 <= 0;
    }

    static boolean check(Line l1, Line l2){
        if(Math.max(l1.x1,l1.x2) < Math.min(l2.x1,l2.x2)) return false;
        if(Math.min(l1.x1,l1.x2) > Math.max(l2.x1,l2.x2)) return false;
        if(Math.max(l1.y1,l1.y2) < Math.min(l2.y1,l2.y2)) return false;
        if(Math.min(l1.y1,l1.y2) > Math.max(l2.y1,l2.y2)) return false;
        return true;
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long tmp = x1 * y2 + x2 * y3 + x3 * y1;
        tmp -= (y1 * x2 + y2 * x3 + y3 * x1);
    
        if (tmp < 0)
          return -1;
        if (tmp > 0)
          return 1;
        return 0;
    }

    static Point [] convexHull(Point [] P){
        if(P.length > 1){
            int n = P.length;
            int k = 0;
            Point [] H = new Point[n*2];

            Arrays.sort(P);

            for(int i = 0;i<n;i++){
                while(k >= 2 && cross(H[k-2], H[k-1], P[i]) <= 0)
                k--;
                H[k++] = P[i];
            }

            for(int i =n-2,t = k+1;i>=0;i--){
                while(k >= t && cross(H[k-2], H[k-1], P[i]) <= 0)
                    k--;
                 H[k++] = P[i];
            }

            if(k > 1){
                H = Arrays.copyOfRange(H,0,k-1);
            }
            return H;
        }
        else if(P.length <= 1){
            return P;
        }else{
            return null;
        }
    
    }
    static long cross(Point p1, Point p2, Point p3){
        long tmp = (p1.x * p2.y)+(p2.x * p3.y)+(p3.x * p1.y);
        tmp -= (p1.y * p2.x)+(p2.y * p3.x)+(p3.y * p1.x);
        return tmp;
    }

    static boolean intersect(long x1, long y1, long x2, long y2, long x3, long y3){
        long minX = Math.min(x1, x2);
        long maxX = Math.max(x1, x2);

        long minY = Math.min(y1, y2);
        long maxY = Math.max(y1, y2);

        if(minX <= x3 && x3 <= maxX && minY <= y3 && y3 <= maxY){
            return true;
        }return false;
    }
}

class Line{
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
    
    Point(long x , long y){
        this.x = x;
        this.y = y;
    }

    Point(){}

    @Override
    public int compareTo(Point p1){
        if(this.x == p1.x){
            return this.y - p1.y >= 0 ? 1 : -1;
        }else{
            return this.x - p1.x >= 0 ? 1 : -1;   
        }
    }
}