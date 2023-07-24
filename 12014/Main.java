import java.util.*;
import java.io.*;

class Main{
    
    static int TC;
    static int N, K;
    static int [] arr;
    static int length;
    
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<Integer> arr = new ArrayList<>();
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr.add(Integer.parseInt(st2.nextToken()));
            }

            ArrayList<Integer> LIS = new ArrayList<>();
            LIS.add(arr.get(0));
            for (int i = 1; i < N; i++) {
                if (LIS.get(LIS.size() - 1) < arr.get(i)) {
                    LIS.add(arr.get(i));
                } else {
                    int pos = Collections.binarySearch(LIS, arr.get(i));
                    if (pos < 0) pos = -(pos + 1);
                    LIS.set(pos, arr.get(i));
                }
            }

            System.out.println("Case #" + (t + 1));
            if (LIS.size() >= K) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}