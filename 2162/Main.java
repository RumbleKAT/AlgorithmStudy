import java.util.*;
import java.io.*;

class Main{

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long tmp = x1 * y2 + x2 * y3 + x3 * y1;
        tmp -= (y1 * x2 + y2 * x3 + y3 * x1);
    
        if (tmp < 0)
          return -1;
        if (tmp > 0)
          return 1;
        return 0;
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

    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;   
        parent[rootA] = rootB;
    }

    static int N;
    static Line [] lines;
    static int [] count;
    static int [] parent;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());

        lines = new Line[N+1];
        count = new int[N+1];
        parent = new int[N+1];
        
        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(token.nextToken());
            long y1 = Long.parseLong(token.nextToken());
            long x2 = Long.parseLong(token.nextToken());
            long y2 = Long.parseLong(token.nextToken());

            lines[i] = new Line(x1,y1,x2,y2);
        }

        for(int i = 1;i<=N;i++){
            parent[i] = i;
        }

        for(int i = 1;i<=N;i++){
            for(int j = 1;j<=N;j++){
                if(i != j){
                    // System.out.println(i +  " " + j);
                    int a = find(i);
                    int b = find(j);

                    if(a == b){
                        continue;
                    }

                    Line l1 = lines[i];
                    Line l2 = lines[j];
 
                    if(cross_line(l1,l2)){
                        //겹침
                        union(i, j);
                    }
                }
            }
        }
        int groupCnt = 0;

        for(int i = 1;i<=N;i++){
            count[parent[i]]++;
        }

        Arrays.sort(count);

        for(int i = 1;i<=N;i++){
            if(count[i]!=0){
                groupCnt++;
            }
        }
        System.out.println(groupCnt);
        System.out.println(count[N]);
    }
}
class Line {
    long x1;
    long y1;
    long x2;
    long y2;
  
    public Line(long x1, long y1, long x2, long y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
}