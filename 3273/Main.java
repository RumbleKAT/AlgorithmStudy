import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;
    static int X;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int[N];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        token = new StringTokenizer(br.readLine());
        X = Integer.parseInt(token.nextToken());

        Arrays.sort(arr);

        // System.out.println(Arrays.toString(arr));
        int s= 0;
        int e = N-1;
        int cnt = 0;
        while(s < e){
            int sum = arr[s] + arr[e];
            if(sum == X){
                System.out.println(arr[s] + " " + arr[e]);
                cnt++;
            }
            if(sum <= X){
                s++;
            }else{
                e--;
            }
        }
        System.out.println(cnt);

    }
}

/*
import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;
    static int X;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int[N];
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        token = new StringTokenizer(br.readLine());
        X = Integer.parseInt(token.nextToken());

        Arrays.sort(arr);

        // System.out.println(Arrays.toString(arr));
        int cnt = 0;
        for(int i=0;i<N;i++){
            int s = Arrays.binarySearch(arr, i+1, N, X-arr[i]);
            if(s >= 0){
                // System.out.println(arr[s] + " " + arr[i]);
                cnt++;
            }
        }
        System.out.println(cnt);
        

    }
}
*/