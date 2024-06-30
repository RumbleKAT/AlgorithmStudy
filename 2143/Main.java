import java.io.*;
import java.util.*;

public class Main {

    static long T;
    static int n, m;
    static int[] arrayA, arrayB;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // Read T
        T = Long.parseLong(br.readLine().trim());

        // Read size and elements of array A
        n = Integer.parseInt(br.readLine().trim());
        arrayA = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arrayA[i] = Integer.parseInt(st.nextToken());
        }

        // Read size and elements of array B
        m = Integer.parseInt(br.readLine().trim());
        arrayB = new int[m];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            arrayB[i] = Integer.parseInt(st.nextToken());
        }

        // Calculate all subarray sums for A
        ArrayList<Long> subArrayA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = arrayA[i];
            subArrayA.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += arrayA[j];
                subArrayA.add(sum);
            }
        }

        // Calculate all subarray sums for B
        ArrayList<Long> subArrayB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            long sum = arrayB[i];
            subArrayB.add(sum);
            for (int j = i + 1; j < m; j++) {
                sum += arrayB[j];
                subArrayB.add(sum);
            }
        }

        // Sort the subarray sums of B for binary search
        Collections.sort(subArrayB);

        // Count pairs that sum to T
        long count = 0;
        for (long sumA : subArrayA) {
            long target = T - sumA;
            long ub = upperBound(subArrayB, target);
            long lb = lowerBound(subArrayB, target);
            count += (ub - lb);
        }

        System.out.println(count);
    }

    // Function to find the lower bound
    public static long lowerBound(ArrayList<Long> array, long target) {
        int left = 0;
        int right = array.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (array.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Function to find the upper bound
    public static long upperBound(ArrayList<Long> array, long target) {
        int left = 0;
        int right = array.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (array.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
