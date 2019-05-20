import java.util.Scanner;

class Main{

    static int N,K;
    static bag [] arr;
    static int [] arr_result;
    static int [][] dp;

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        K = scan.nextInt();

        arr = new bag[N+1];    
        int max = 0;

        for(int i =1;i<=N;i++){
            int w = scan.nextInt();
            int v = scan.nextInt();

            arr[i] = new bag(w, v);
            arr_result[w] = v;
            max = Math.max(max, arr[i].w);
        }

        dp = new int[N+1][K+1];

        for(int j =1;j<=N;j++){
            for(int i=1;i<=K;i++){
                if(dp[j][i] + arr[j].w <= i){
                    dp[j][i] = Math.max(dp[j][i-1], (dp[j-1][i] + arr[j].v));
                    System.out.print(i + " " +dp[j][i] + " " + dp[j-1][i] + " " + dp[j][i-1] + " " + arr[j].w + " " + (i-arr[j].w) +" " + dp[(i-arr[j].w)][N-1]);
                }else{
                    dp[j][i] = dp[j][i-1];
                }
                System.out.println();
                // System.out.println(arr[j].v);
            }
        }

    }
}

class bag{
    public int w;
    public int v;

    public bag(int w, int v){
        this.w = w;
        this.v = v;
    }
}