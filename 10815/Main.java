import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;
    static int M;

    public static void main(String [] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());

        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());    
        }
        Arrays.sort(arr);
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            int search = Arrays.binarySearch(arr, Integer.parseInt(token.nextToken()));
            if(search < 0){
                sb.append("0 " );
            }else{
                sb.append("1 ");
            }
        }
        System.out.println(sb.toString());
    }
}