import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    
    static int [] box, cube;
    static long fullV;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        box = new int[3];
        fullV = 1;

        for(int i=0;i<3;i++){
            box[i] = Integer.parseInt(token.nextToken());
            fullV *= box[i];
        }

        token = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(token.nextToken());

        cube = new int[n];
        for(int i=0;i<n;i++){
            token = new StringTokenizer(br.readLine());
            cube[Integer.parseInt(token.nextToken())] = Integer.parseInt(token.nextToken());
        }
        System.out.println(solve(n-1, 0, 0));
    }
    static long solve(int blockSize, long fill, long cnt){
        long totalV = 1;
        for(int i=0;i<3;i++){
            totalV *= box[i] >> blockSize;
        }

        int curCub = cube[blockSize];
        totalV -= fill;
        long fillCube = Math.min(curCub, totalV);
        if(blockSize == 0){
            if(fill + fillCube != fullV){
                return -1;
            }
            return cnt + fillCube;
        }else{
            return solve(blockSize-1, fill+fillCube<<3, cnt+fillCube);
        }

    }
}