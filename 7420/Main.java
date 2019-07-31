import java.util.*;
import java.io.*;

class Main{

    static int N,L;
    static Point [] arr;
    static Point [] newPoints;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        L = Integer.parseInt(token.nextToken());

        arr = new Point[N+1];

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            arr[i] = new Point(x, y);
        }

        int minIndex = 0;

        for(int i = 1;i<N;i++){
            if(arr[i].y < arr[minIndex].y){
                minIndex = i;
            }else if(arr[i].y == arr[minIndex].y){
                if(arr[i].x < arr[minIndex].x){
                    minIndex = i;
                }
            }
        }

        //swap
        Point temp = arr[0];
        arr[0] = arr[minIndex];
        arr[minIndex] = temp;

        //System.out.println(arr[0].y + " " + arr[0].x + " " + minIndex);
        //show();

        //compare
        Arrays.sort(arr,1,N,new Comparator<Point>(){
            
            @Override
            public int compare(Point p1, Point p2){
                int c = ccw(arr[0], p1, p2);
                if(c == 0){
                    return dist(arr[0], p2) >= dist(arr[0],p1) ? -1 : 1;
                }
                return c;
            }
        });

        newPoints = new Point[N+1];
        newPoints[0] = arr[N-1];

        for(int i = 1;i<=N;i++){
            newPoints[i] = arr[i-1];
        }

        int M = 1;

        for(int i =2;i<=N;i++){
            while(ccw(newPoints[M-1], newPoints[M], newPoints[i]) >= 0 ){//시계 방향 일때
                if(M > 1){
                    M -= 1;
                    continue;
                }else if(i == N)break;
                else i += 1;
            }

            M += 1;

            Point tmp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = tmp;
        }
       System.out.println("M : " + M);

        showA(M);        

        //볼록껍질 다구함
        double ans = 0;

        for(int i = 0;i<M;i++){
            ans += get_dist(newPoints[i], newPoints[i+1]);    
        }
        ans += (2*L*Math.PI);

        System.out.println(Math.round(ans));
    }

    static void show(){
        for(int i = 0 ; i<N;i++){
            System.out.println(arr[i].y + " " + arr[i].x );
        }
    }

    static void showA(int M){
        for(int i = 0 ; i<=M;i++){
            System.out.println(newPoints[i].y + " " + newPoints[i].x );
        }
    }

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y);
    }

    static double get_dist(Point p1, Point p2){
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y));
    }

    static int ccw(Point p1,Point p2,Point p3){
        long tmp = (p1.x*p2.y)+(p2.x*p3.y)+(p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);

        if(tmp > 0){
            return -1;
        }else if(tmp < 0){
            return 1;
        }else{
            return 0;
        }
    }



}

class Point{
    public int x;
    public int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    Point(){

    }
}