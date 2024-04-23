import java.util.*;
import java.io.*;

class Main{

    static int N;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        int count = 0;

        for(Integer i=666;i<=100000000;i++){
            if(i.toString().indexOf("666") != -1){
                count++;
            }   
            if(count == N){
                System.out.println(i);
                break;
            }
        }

    }
}