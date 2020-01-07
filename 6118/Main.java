import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static PriorityQueue<Node> pq;
    static LinkedList<Node>[] arr;
    static long[] dists;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N + 1];
        dists = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new LinkedList<>();
            dists[i] = 987654321;
        }

        for (int i = 1; i <= M; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e, 0));
            arr[e].add(new Node(s, 0));
        }

        pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dists[1] = 0;

        long max = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIdx = current.idx;
            long dist = current.dist;

            if (dist < dists[currentIdx])
                continue;

            for (Node node : arr[currentIdx]) {
                if (dists[node.idx] > dists[currentIdx] + 1) {
                    dists[node.idx] = dists[currentIdx] + 1;
                    max = Math.max(max, dists[node.idx]);
                    pq.add(new Node(node.idx, dist + 1));
                }
            }
        }

        int idx = 0;
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (max == dists[i]) {
                if (idx == 0)
                    idx = i;
                cnt++;
            }
        }

        System.out.println(idx + " " + max + " " + cnt);

    }
}

class Node implements Comparable<Node> {
    public int idx;
    public long dist;

    Node(int idx, long dist) {
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node n1) {
        return (int) (this.dist - n1.dist);
    }
}