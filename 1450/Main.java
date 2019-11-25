import java.io.*;
import java.util.*;

class Main{

    static int N,C;
    static int [] arr;
    static long cnt;
    static ArrayList<Integer> leftList;
    static ArrayList<Integer> rightList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        leftList = new ArrayList<>();
        rightList = new ArrayList<>();

        cnt = 0;
        arr = new int [33];

        token = new StringTokenizer(br.readLine());
        for(int i =0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        
        left_dfs(0, N/2-1, 0);

        right_dfs(N/2, N-1, 0);

        Collections.sort(leftList);
        Collections.sort(rightList);

        int e = rightList.size()-1;
        // System.out.println(cnt);

        for(int s =0; s< leftList.size() ; s++){
            while( e >=  0 && (leftList.get(s) + rightList.get(e)) > C)
                e--;
                cnt += e + 1;
        }
        
        System.out.println(cnt);

    }
    
    static void left_dfs(int s, int e, int sum){
        if(sum > C) return;
        if(s > e){
            leftList.add(sum);
            return;
        }
        left_dfs(s+1,e ,sum);
        left_dfs(s+1,e ,sum+arr[s]);
    }

    static void right_dfs(int s, int e, int sum){
        if(sum > C) return;
        if(s > e){
            rightList.add(sum);
            return;
        }
        right_dfs(s+1,e ,sum);
        right_dfs(s+1,e ,sum+arr[s]);
    }
}