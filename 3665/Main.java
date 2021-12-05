import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int TC;
    static int N,M;
    static int [] arr;
    static int [] indegree;
    static ArrayList<Integer> [] nodes;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("/Users/songmyeongjin/Desktop/3665/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());

            nodes = new ArrayList[N+1];
            indegree = new int[N+1];
            for(int i=1;i<=N;i++){
                nodes[i] = new ArrayList<>();
            }

            arr = new int[N+1];
            token = new StringTokenizer(br.readLine());

            //작년에 1등을 한 수
            for(int i=1;i<=N;i++){
                arr[i] = Integer.parseInt(token.nextToken());
            }

            for(int i=1;i<=N;i++){
                int front = arr[i];
                for(int j=i+1;j<=N;j++){
                    nodes[front].add(arr[j]);
                    indegree[arr[j]]++;
                }
            }

            token = new StringTokenizer(br.readLine());
            M = Integer.parseInt(token.nextToken());

            for(int i=1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int front = Integer.parseInt(token.nextToken());
                int back = Integer.parseInt(token.nextToken());

                if(nodes[front].contains(back)){
                    nodes[front].remove((Integer)back);
                    nodes[back].add(front);
                    indegree[front]++;
                    indegree[back]--;
                }else{
                    nodes[back].remove((Integer)front);
                    nodes[front].add(back);
                    indegree[back]++;
                    indegree[front]--;
                }
            }

            StringBuilder sb = new StringBuilder();
            Queue<Integer> queue = new LinkedList<>();

            int cnt = 0;
            for(int i=1;i<=N;i++){
                if(indegree[i] == 0){
                    queue.add(i);
                    cnt++;
                }
            }
            if(cnt > 1){
                System.out.println("?");
                continue;
            }

            int result = 0;
            boolean finishChecked = false;

            for(int i=1;i<=N;i++){
                if(queue.isEmpty()){
                    System.out.println("IMPOSSIBLE");
                    finishChecked = true;
                    break;
                }
                int cur = queue.poll();
                result++;
                sb.append(cur).append(" ");
                for(int next: nodes[cur]){
                    indegree[next] -= 1;
                    if(indegree[next] == 0){
                        queue.add(next);
                    }
                }
            }

            if(finishChecked)continue;
            System.out.println(sb.toString());
        }

    }
}
