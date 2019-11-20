import java.util.*;
import java.io.*;
import java.math.*;

class Main {

    static BigInteger[] arr;
    static int s, e;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new BigInteger[251];
        BigInteger one = new BigInteger("1");

        arr[0] = one;
        arr[1] = one;

        for (int i = 2; i <= 250; i++) {
            arr[i] = (arr[i - 2].add(arr[i - 2])).add(arr[i - 1]);
        }

        while (true) {
            String str = br.readLine();
            if (str == null)
                break;

            // System.out.println("STR : " + str);

            int temp = Integer.parseInt(str);
            System.out.println(arr[temp]);

        }

    }
}