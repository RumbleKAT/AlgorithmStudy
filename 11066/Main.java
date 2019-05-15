import java.util.*;
import java.io.*;

class Main{

    static int [] arr;
    static int [][] result;
    static ArrayList <Integer> arrlist;

    static int TC ,n;

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);

         TC = scan.nextInt();

         for(int t=1;t<=TC;t++){

        // }

            n = scan.nextInt();

            arr = new int [n+1];
            result = new int [n+1][n+1];
            arrlist = new ArrayList<>();

            for(int i=1;i<=n;i++){
                int temp = scan.nextInt();
                arr[i] = arr[i-1] + temp;
            }

            for(int k = 1;k<n; k++){
                for(int i =1;i<=n-k;i++){
                    result[i][i+k] = Integer.MAX_VALUE;
                    for(int j=i;j<i+k;j++){
                        result[i][i+k] = Math.min(result[i][i+k],result[i][j] + result[j+1][i+k]);
                    }
                    result[i][i+k] += arr[i+k] - arr[i-1];
                }
            }

            System.out.println(result[1][n]);
        }

    }
}