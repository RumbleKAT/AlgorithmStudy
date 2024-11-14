import java.util.*;
import java.io.*;


class Main{
    static int N;
    static int weight;
    static int [] arr;

    public static void main(String[] args) throws Exception{
        ////System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            weight = (int)(Math.round(Double.parseDouble(token.nextToken())*100));

            if(N == 0 && weight == 0){
                break;
            }

            arr = new int[100000];
    
            for(int i=1;i<=N;i++){
                token = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(token.nextToken());
                int weightIdx =  (int)(Math.round(Double.parseDouble(token.nextToken())*100));
    
                for(int j=weightIdx;j<=weight;j++){
                    arr[j] = Math.max(arr[j - weightIdx] + value, arr[j]);
                }
            }
            // System.out.println(Arrays.toString(arr));
            System.out.println(arr[weight]);
        }
    }
}