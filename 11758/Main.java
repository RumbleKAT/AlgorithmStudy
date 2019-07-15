import java.util.*;
import java.io.*;


class Main{
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        Point [] points = new Point[4];

        for(int i =1;i<=3;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            points[i] = new Point(x,y);
        }

        long tmp_1 =  (points[1].x * points[2].y + points[2].x * points[3].y + points[3].x * points[1].y);
        long tmp_2 = (points[1].y * points[2].x + points[2].y * points[3].x + points[3].y * points[1].x);

        long result = tmp_1 - tmp_2;

        if(result > 0){
            System.out.println(1);
        }else if(result == 0){
            System.out.println(0);
        }else{
            System.out.println(-1);
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
}