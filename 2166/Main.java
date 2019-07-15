import java.util.*;
import java.io.*;

class Main{

    static Point [] arr;
    static int N;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());

        arr = new Point[N+1];

        for(int i = 0 ;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            Point point = new Point(a,b);
            arr[i] = point;
        }

        long ans = 0;
        double re = 0;

        for(int i = 1;i< N-1;i++){
            ans += ccw(arr[i-1],arr[i],arr[i+1]);
        }

        ans = Math.abs(ans)/2;

        System.out.print(ans);
        if(ans%2 == 0){
            System.out.println(".0");
        }else{
            System.out.println(".5");
        }

    }

    static long ccw(Point A, Point B, Point C){
        long ans = (A.x*B.y+B.x*C.y + C.x*A.y);
        long ans2 = (A.y*B.x+B.y*C.x+C.y*A.x);
        
        long result = ans-ans2;

        return result;
    }
}

class Point{
    public int y;
    public int x;

    Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}