import java.util.*;
import java.io.*;

public class Main {

    static int TC;
    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\7662\\src\\com\\company\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int t=1;t<=TC;t++) {
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());

            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 1; i <= N; i++) {
                token = new StringTokenizer(br.readLine());
                char order = token.nextToken().charAt(0);

                if(order == 'I'){
                    int num = Integer.parseInt(token.nextToken());
                    treeMap.put(num,treeMap.getOrDefault(num,0)+1);
                }else{
                    if(treeMap.size() == 0) continue;
                    int type = Integer.parseInt(token.nextToken());
                    int num;
                    if(type == 1){
                        num = treeMap.lastKey();
                    }else{
                        num = treeMap.firstKey();
                    }
                    if(treeMap.put(num,treeMap.get(num)-1)==1){
                        treeMap.remove(num);
                    }
                }
            }
            if(treeMap.size() == 0){
                sb.append("EMPTY\n");
            }else{
                sb.append(treeMap.lastKey() + " " + treeMap.firstKey()+"\n");
            }
        }
        System.out.println(sb.toString());
    }
}
