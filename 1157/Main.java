import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[26];
        int max = 0;
        char result = '?';

        String str = br.readLine().toUpperCase();
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'A']++;
            if (max < arr[str.charAt(i) - 'A']) {
                max = arr[str.charAt(i) - 'A'];
                result = str.charAt(i);
            } else if (max == arr[str.charAt(i) - 'A']) {
                result = '?';
            }
        }
        bw.write(result);
        bw.flush();
        bw.close();
        br.close();
    }
}
