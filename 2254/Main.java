import java.util.*;
import java.io.*;

class Main{

    static int N;
    static Point [] arrs;
    static Point [] newPoints;
    static Point goal;
    static boolean [] visited;
    static int result;
    static boolean flag = false;

    static int ccw(Point p1, Point p2,Point p3){
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

    static long dist(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y);
    }

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arrs = new Point[N+1];
        visited = new boolean[N+1];
        result =0;

        int a = Integer.parseInt(token.nextToken());
        int b = Integer.parseInt(token.nextToken());
        goal = new Point(a,b);

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            arrs[i] = new Point(x,y);
        }
        arrs[N] = goal;

        while(N > 0){
            System.out.println("N : " + N);
            if( N < 3 ) break;
            get_convexHull(arrs, N+1);
            if(flag){
                break;
            }    
        }

        System.out.println(result);

    }

    static void get_convexHull(Point [] arr, int MAX){

        // System.out.println("MAX : " +MAX  + " " + arr.length);

        int minIdx = 0;
        for(int i = 1;i<MAX-1;i++){
            // System.out.println(minIdx + " " + i + " ");
            // System.out.println(arr[minIdx].y + " " + arr[minIdx].x);
            // System.out.println(arr[i].y + " " + arr[i].x);
            // System.out.println(" I : " +i);
            if(arr[i].y < arr[minIdx].y){
                minIdx = i;
            }else if(arr[i].y == arr[minIdx].y){
                if(arr[i].x < arr[minIdx].x){
                    minIdx = i;
                }
            }
        }

        Point tmp = arr[0];
        arr[0] = arr[minIdx];
        arr[minIdx] = tmp;

        Arrays.sort(arr,1,MAX-1,new Comparator<Point>(){
            
            @Override
            public int compare(Point p1, Point p2){
                int c = ccw(arr[0], p1, p2);
                if(c == 0){
                    return dist(arr[0], p2) >= dist(arr[0],p1) ? -1 : 1;
                }
                return c;
            }
        });

        newPoints = new Point[MAX+1];
        newPoints[0] = arr[MAX-1];

        for(int i = 1;i<=MAX;i++){
            newPoints[i] = arr[i-1];
        }

        int M = 1;
        
        for(int i = 2;i<=MAX;i++){
            while(ccw(newPoints[M-1],newPoints[M],newPoints[i]) >= 0){
                if(M > 1){
                    M -=1;
                    continue;
                }else if(i == MAX) break;
                else i += 1;
            }

            M += 1;
            Point temp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = temp;
        }

        //배열 잘라야됨
       
        for(int i = 0;i<=M;i++){
            newPoints[i].check = true;
            if(newPoints[i].y == goal.y && newPoints[i].x == goal.x){
                flag = true;
                break;
            } 
        }

        if(!flag){
            LinkedList <Point> pList = new LinkedList<>();
            result++;

            for(int i =0;i<=MAX;i++){
                if(!newPoints[i].check){
                    pList.add(newPoints[i]);
                }
            }


            for(Point node : pList){
                System.out.println(node.y + " " + node.x);
            }


            arrs = pList.toArray(new Point[pList.size()]);
            System.out.println(arrs.length);

            for(int i = 0 ;i<arrs.length;i++){
                System.out.println(arrs[i].y + " " + arrs[i].x);
            }
            N = arrs.length;

            if(N == 1) flag = true;
        }
    }

}


class Point{
    public int x;
    public int y;
    public boolean check = false;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}