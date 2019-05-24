import java.util.Scanner;

class Main{
    static int N;
    static int [][] arr; 
    static int [][] max;
    static int [][] min;

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        arr = new int [N+1][3];
        max = new int [N+1][3];
        min = new int [N+1][3];

        for(int i =1;i<=N;i++){
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
            arr[i][2] = scan.nextInt();

            max[i][0] = arr[i][0];
            max[i][1] = arr[i][1];
            max[i][2] = arr[i][2];

            min[i][0] = arr[i][0];
            min[i][1] = arr[i][1];
            min[i][2] = arr[i][2];

        }

        for(int i=2;i<=N;i++){
            max[i][0] += Math.max(max[i-1][0],max[i-1][1]);
            max[i][1] += Math.max(Math.max(max[i-1][0],max[i-1][1]),max[i-1][2]);
            max[i][2] += Math.max(max[i-1][1],max[i-1][2]);

            min[i][0] += Math.min(min[i-1][0],min[i-1][1]);
            min[i][1] += Math.min(Math.min(min[i-1][0],min[i-1][1]),min[i-1][2]);
            min[i][2] += Math.min(min[i-1][1],min[i-1][2]);
        }

        int result_max = Math.max(Math.max(max[N][0], max[N][1]),max[N][2]);
        int result_min = Math.min(Math.min(min[N][0], min[N][1]),min[N][2]);

        System.out.println(result_max + " " + result_min);
    }
}