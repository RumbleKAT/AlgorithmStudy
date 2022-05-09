import java.util.*;
import java.io.*;

class Main{
    static int G;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        G = Integer.parseInt(token.nextToken());

        int s = 1;
        int e = 1;
        boolean existed = false;

        while(true){
            if(e*e-s*s==G){
                existed = true;
                System.out.println(e);
            }
            if(e-s==1 && e*e - s*s > G) break;
            if(e*e - s*s > G) s++;
            else e++;
        }

        if(!existed){
            System.out.println("-1");
        }
    }
}