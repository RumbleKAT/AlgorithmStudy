import java.util.*;
import java.util.Map.Entry;
import java.io.*;

class Main{

    static HashMap<String, Integer> map;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        map = new HashMap<>();

        for(int i=0;i<N+M;i++){
            String str = br.readLine();
            if(map.get(str) == null){
                map.put(str, 1);
            }else{
                map.put(str, map.get(str)+1);
            }
        }
        PriorityQueue<String> rQueue = new PriorityQueue<>();
        int cnt = 0;
        for(Entry<String, Integer> temp : map.entrySet()){
            if(temp.getValue() == 2){
                cnt++;
                rQueue.add(temp.getKey());
            }
        }
        System.out.println(cnt);
        while(!rQueue.isEmpty()){
            System.out.println(rQueue.poll());
        }

    }
}