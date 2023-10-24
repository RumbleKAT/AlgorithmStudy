import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int cur = Integer.parseInt(token.nextToken());
        int result = -1;

        for (int i = cur / 5; i >= 0; i--) {
            int remaining = cur - i * 5;
            if (remaining % 2 == 0) {
                result = i + remaining / 2;
                break;
            }
        }
        System.out.println(result);
    }
}