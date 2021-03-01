class Solution {

    public int smallestRangeII(int[] A, int K) {
        int N = A.length;
        Arrays.sort(A);
        int max = A[N - 1] - K;
        int min = A[0] + K;

        int ans = A[N - 1] - A[0];

        for (int i = 0; i < A.length - 1; i++) {
            int temp_Max = Math.max(max, A[i] + K);
            int temp_Min = Math.min(min, A[i + 1] - K);

            ans = Math.min(ans, temp_Max - temp_Min);
        }

        return ans;
    }
}