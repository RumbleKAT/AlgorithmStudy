import java.util.*;
import java.io.*;

class Main{
    
    static int N,d,k,c;
    static int [] sushi;
    static int [] checked;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        d = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());
        c = Integer.parseInt(token.nextToken());
        
        sushi = new int[N];
        checked = new int[d+1];
        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            sushi[i] = Integer.parseInt(token.nextToken());            
        }

        int count = 0;
        for(int i=0;i<k;i++){
            if(checked[sushi[i]] == 0){
                count++;
            }
            checked[sushi[i]]++;
        }
        
        int max = count;
        int start = 1;
        int end = k; //4 개씩
        
        while(true){
            if(checked[sushi[start-1]]==1) count--;
            checked[sushi[start-1]]--;

            if(checked[sushi[end]]==0) count++;
            checked[sushi[end]]++;
            
            if(checked[c] == 0) max = Math.max(max, count+1);
            else max = Math.max(max,count);

            start++;
            end++;
            if(end == N) end =0;
            if(start == N) break;
        }
        System.out.println(max);
    }
}