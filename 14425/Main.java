import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        int count = 0;
        HashMap<String, Integer> map = new HashMap<>();
        
        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            map.put(token.nextToken(), 1);
        }

        for(int i=0;i<M;i++){
            token = new StringTokenizer(br.readLine());
            if(map.containsKey(token.nextToken())){
                count++;
            }
        }
        System.out.println(count);

    }
}