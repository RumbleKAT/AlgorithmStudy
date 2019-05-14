import java.util.Scanner;

class Main{
    public static void main(String [] args){

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] arr = new int [n+1];

        for(int i = 1;i<=n;i++){
            arr[i] = scan.nextInt();
        }

        int [] result = new int [arr.length+2];

        int max = 0;
        for(int i =0;i<arr.length-1;i++){
            result[i+1] = Math.max(result[i]+arr[i+1],arr[i+1]);
            max = Math.max(result[i+1],max);
        }

        System.out.println(max);
    }
}