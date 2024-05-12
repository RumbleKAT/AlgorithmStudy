import java.util.*;
import java.io.*;

class Main{
    static int K;
    static int [] arr;
    static List<List<Integer>> levels;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());
        int numsNodes = (int)Math.pow(2,K)-1;
        arr = new int[numsNodes];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        levels = new ArrayList<>();
        for(int i =0;i<K;i++){
            levels.add(new ArrayList<>());
        }
        buildTree(0, numsNodes -1, 0);

        for(List<Integer> level: levels){
            for(int num: level){
                System.out.print(num + " ");
            }
            System.out.println();
        }
        
    }
    static void buildTree(int start, int end, int level){
        if(start > end) return;
        int mid = (start + end) / 2;
        levels.get(level).add(arr[mid]);
        buildTree(start, mid -1, level+1);
        buildTree(mid+1, end, level+1);
    }

}