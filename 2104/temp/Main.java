import java.util.Scanner;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] arr = new int [n+1];

        for(int i = 1;i<=n;i++){
            arr[i] = scan.nextInt();
        }

        int [] result = new int [arr.length+2];
        int [][] result_map = new int [arr.length+2][arr.length+2];

        int start_idx = -1;
        int end_idx = -1;

        int max = Integer.MIN_VALUE;
        for(int i =0;i<arr.length-1;i++){
            result[i+1] = Math.max(result[i]+arr[i+1],arr[i+1]);
            
            if(result[i]+arr[i+1] > arr[i+1]){
                
            }

            // if(result[i+1] > max){
            //     end_idx = i; //끝점
            // }

            max = Math.max(result[i+1],max);
    
            // if(arr[i+1] > result[i] + arr[i+1] || result[i] < 0){
            //     start_idx = i; //시작점
            // }

        }

        // System.out.println(start_idx + " " + end_idx);

        System.out.println(max);
    }
}