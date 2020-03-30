import java.util.*;

import java.io.*;

class Main{
    static Line [] lines;
    public static void main(final String [] args)throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        lines = new Line[2];

        for(int i=0;i<2;i++){
            token = new StringTokenizer(br.readLine());
            final int x1 = Integer.parseInt(token.nextToken());
            final int y1 = Integer.parseInt(token.nextToken());
            final int x2 = Integer.parseInt(token.nextToken());
            final int y2 = Integer.parseInt(token.nextToken());

            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        if(isIntersect(lines[0], lines[1])){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
    static int ccw(final Point p1, final Point p2, final Point p3){
        long tmp = (p1.x *p2.y) + (p2.x*p3.y) + (p3.x*p1.y);
        tmp -= (p1.y*p2.x)+(p2.y*p3.x)+(p3.y*p1.x);
        if(tmp > 0) return 1;
        else if(tmp < 0) return -1;
        else return 0;
    }

    static boolean isIntersect(Line l1, Line l2){
        Point p1 = l1.p1;
        Point p2 = l1.p2;
        Point p3 = l2.p1;
        Point p4 = l2.p2;

        int ab = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int cd = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        if(ab == 0 && cd == 0){
            if(p1.x > p2.y && p1.y > p2.y){
                int tempX = p1.x;
                int tempY = p1.y;

                p1.x = p2.x;
                p1.y = p2.y;

                p2.x = tempX;
                p2.y = tempY;
            }
            if(p3.x > p4.y && p3.y > p4.y){
                int tempX = p3.x;
                int tempY = p3.y;

                p3.x = p4.x;
                p3.y = p4.y;

                p4.x = tempX;
                p4.y = tempY;
            }
            return ((p3.x <= p2.x && p3.y <= p2.y) && (p1.x <= p4.x && p1.y<=p4.y));
        }
            return ab <= 0 && cd <= 0;
    }
}
class Line{
    public Point p1;
    public Point p2;

    Line(final Point p1, final Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
}

class Point{
    public int x;
    public int y;

    Point(final int x, final int y){
        this.x = x;
        this.y = y;
    }
}