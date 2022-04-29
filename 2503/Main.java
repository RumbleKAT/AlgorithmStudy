
import java.util.*;
import java.io.*;

public class Main {

    static int [] arr;
    static boolean [] visited;
    static int [] res;
    static int N;
    static ArrayList<Node> questions;
    static int resCnt;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2503/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int[9];
        for(int i=1;i<=9;i++){
            arr[i-1] = i;
        }
        visited = new boolean[3];
        res = new int[3];
        questions = new ArrayList<>();
        resCnt = 0;

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            String str = token.nextToken();
            int strike = Integer.parseInt(token.nextToken());
            int ball = Integer.parseInt(token.nextToken());
            questions.add(new Node(str,strike,ball));
        }
        dfs(0);
        System.out.println(resCnt);
    }
    static void dfs(int param){
        if(param == 3){
            HashMap<Integer, Integer> map = new HashMap<>();
            boolean flag = false;
            for(int i : res){
                if(map.containsKey(i)){
                    flag = true;
                    map.put(i,map.get(i)+1);
                }
                map.put(i,1);
            }

            if(!flag){
                int tmpCnt = 0;
                for(Node node : questions){
                    int strikecnt = 0;
                    int ballcnt = 0;
                    if(node.str.charAt(0) - 48 == res[0]){
                        strikecnt++;
                    }
                    if(node.str.charAt(1) - 48 == res[1]){
                        strikecnt++;
                    }
                    if(node.str.charAt(2) - 48 == res[2]){
                        strikecnt++;
                    }

                    if(node.str.charAt(0) - 48 != res[0] && map.containsKey(node.str.charAt(0) - 48)){
                        ballcnt++;
                    }

                    if(node.str.charAt(1) - 48 != res[1] && map.containsKey(node.str.charAt(1) - 48)){
                        ballcnt++;
                    }

                    if(node.str.charAt(2) - 48 != res[2] && map.containsKey(node.str.charAt(2) - 48)){
                        ballcnt++;
                    }

                    if(ballcnt == node.ball && strikecnt == node.strike){
                        tmpCnt++;
                    }
                }

                if(tmpCnt == questions.size()){
                    resCnt++;
                }
            }
            return;
        }
        for(int i=1;i<=9;i++) {
            if (!visited[param]) {
                visited[param] = true;
                res[param] = i;
                dfs(param + 1);
                visited[param] = false;
            }
        }
    }
}
class Node{
    public String str;
    public int strike;
    public int ball;

    Node(String str, int strike, int ball){
        this.str = str;
        this.strike = strike;
        this.ball = ball;
    }
}
