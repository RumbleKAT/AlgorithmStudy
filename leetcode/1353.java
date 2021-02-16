class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        int i = 0;
        for (int day = 1; day <= 100000; day++) {

            while (i < events.length && events[i][0] == day) {
                System.out.println(i);
                pq.add(events[i++][1]);
            }

            while (!pq.isEmpty() && pq.peek() < day) {
                System.out.println(pq.poll());
            }

            if (!pq.isEmpty()) {
                System.out.println(pq.poll());
                cnt++;
            }
        }

        return cnt;

    }
}
