import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static HashMap<String, Integer> map; 
    static String [] pokemon;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new HashMap<String, Integer>();
        pokemon = new String[N+1];


        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            String cur = token.nextToken();
            pokemon[i] = cur;
            String tmpIdx = cur.toLowerCase();
            map.put(tmpIdx, i);
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            String cur = token.nextToken();
            try{
                int isInt = Integer.parseInt(cur);       
                System.out.println(pokemon[isInt]);
            }catch(Exception e){
                System.out.println(map.get(cur.toLowerCase()));
            }
        }
    }
}