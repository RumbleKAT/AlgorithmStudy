import java.util.Scanner;

class Main{

    static int [] arr = new int [1000001];
    static int N;

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        int count = 0;
        
        int sum = dfs(N);
        System.out.println(arr[N]);
    }

    static int dfs(int n){
/*        
        for(int i =0;i<=N;i++){
            System.out.print(arr[i]+ " ");
        }System.out.println();
*/
        if(n == 1){
            return 0;
        }
        if(arr[n] != 0){
            return arr[n];
        }
        arr[n] = dfs(n-1)+1;

        if(n % 3 == 0){
            //System.out.println(arr[n] + " " + (dfs(n/3)+1));
            if(arr[n] > dfs(n/3)+1)
            arr[n] = dfs(n/3)+1;
        }
        if(n % 2 == 0){
            //System.out.println(arr[n] + " " + (dfs(n/2)+1));
            if(arr[n] > dfs(n/2)+1)
            arr[n] = dfs(n/2)+1;
        }
         
        return arr[n];
    }
}