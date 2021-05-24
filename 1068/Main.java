import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int [] arr;
    static ArrayList<Integer>[] nodes;
    static boolean [] visited;
    static int [] cnt;
    static int remove;
    static int ans;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/Users/songmyeongjin/IdeaProjects/socialServer/1068/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        arr = new int[N+1];
        cnt = new int[N+1];
        nodes = new ArrayList[N+1];
        visited = new boolean[N+1];

        token = new StringTokenizer(br.readLine());

        for(int i=0;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }

        int s = 0;
        for(int i=0;i<N;i++){
            int parent = Integer.parseInt(token.nextToken());
            if(parent == -1){
                s = i;
                continue;
            }
            nodes[parent].add(i);
            nodes[i].add(parent);
        }
        token = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(token.nextToken());
        ans = 0;
        visited[remove] = true;

        Queue<Integer> queue = new LinkedList<>();
        if(!visited[s]){
            queue.add(s);

            while(!queue.isEmpty()){
                int cur = queue.poll();
                if(!visited[cur]) visited[cur] = true;
                int childCnt = 0;
                for(int next : nodes[cur]){
                    if(!visited[next]){
                        cnt[cur]++;
                        childCnt++;
                        queue.add(next);
                    }
                }
                if(childCnt == 0){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
