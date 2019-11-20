import java.util.*;
import java.io.*;

class Main{

    static final int MAX = 201;
    static int N;
    static String [] cache;

    static String bigNumAdd(String num1 , String num2){
        long sum = 0;
        String result;


        while(!num1.isEmpty() || !num2.isEmpty() || sum){
            if(!num1.isEmpty()){
                sum += 
            }
        }

    }

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader("./sample.txt"));
        
        cache = new String [MAX];
        cache[0] = cache[1] = '1';

        for(int i =2;i<=250;i++){
            cache[i] = bigNumAdd(bigNumAdd(cache[i-2],cache[i-2]),cache[i-1]);
        }

        while(true){
            String str = br.readLine();
            if(str.isEmpty()) break;
            
        }
    }
}