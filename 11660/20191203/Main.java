import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

    static int N,M;
    static int [][] map;
    static long [][] prefix;

    //prefix[i+1][j+1] = prefix[i][j+1] + prefix[i+1][j] - prefix[i][j] + arr[i][j]

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new int[N+3][N+3];
        prefix = new long[N+3][N+3];

        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            
            for(int j =1;j<=N;j++){
                map[i][j] = Integer.parseInt(token.nextToken());
                prefix[i+1][j+1] = prefix[i][j+1] + prefix[i+1][j] - prefix[i][j] + map[i][j];
            }

        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(token.nextToken());
            int e1 = Integer.parseInt(token.nextToken());

            int s2 = Integer.parseInt(token.nextToken());
            int e2 = Integer.parseInt(token.nextToken());

            s1 +=1;
            e1 +=1;
            s2 +=1;
            e2 +=1;
            
            long sum = prefix[s2][e2] - prefix[s1-1][e2] - prefix[s2][e1-1] + prefix[s1-1][e1-1];
            
            System.out.println(sum);
        }

    }
    static void showMap(long [][] param){
        for(int i=1;i<=N;i++){
            for(int j =1;j<=N;j++){
                System.out.print(param[i][j] + " ");
            }System.out.println();
        }
    }

    static void showMap(int [][] param){
        for(int i=1;i<=N;i++){
            for(int j =1;j<=N;j++){
                System.out.print(param[i][j] + " ");
            }System.out.println();
        }
    }
    
}