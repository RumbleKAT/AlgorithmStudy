import java.util.*;
import java.io.*;

class Main{
    
    static int N;
    static Point [] arr;
    static Point [] newPoints;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        arr = new Point [N+1];
        newPoints = new Point [N+1];
        
        for(int i = 0 ;i<N;i++){
            token = new StringTokenizer(br.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());
            arr[i] = new Point(x,y);    
        }

        int minIdx = 0;

        for(int i = 1;i<N;i++){
            if(arr[i].y < arr[minIdx].y){
                minIdx = i;
            }else if(arr[i].y == arr[minIdx].y){
                if(arr[i].x < arr[minIdx].x){
                    minIdx = i;
                }
            }
        }

        Point tmp = arr[minIdx];
        arr[minIdx] = arr[0];
        arr[0] = tmp;

        Arrays.sort(arr, 1,N,new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                int c = ccw(arr[0],p1,p2);
                if(c == 0){
                    return dist(arr[0], p2) - dist(arr[0], p1) >= 0 ? -1 : 1;
                }return c;
            }
        });

        newPoints[0] = arr[N-1];
        for(int i = 1;i<=N;i++){
            newPoints[i] = arr[i-1];
        }

        int M = 1;

        for(int i = 2;i<=N;i++){
            while(ccw(newPoints[M-1], newPoints[M], newPoints[i]) >= 0){
                if( M > 1){
                    M -= 1;
                    continue;
                }else if(i == M)break;
                else i += 1;
            }
            M += 1;
            Point temp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = temp;
        }

        System.out.println(M);


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

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
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