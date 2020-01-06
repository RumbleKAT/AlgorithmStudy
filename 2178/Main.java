import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
    static int[][] MAP;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        MAP = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = " " + br.readLine();
            for (int j = 1; j <= M; j++) {
                MAP[i][j] = str.charAt(j) - 48;
            }
        }

        Queue<Node> que = new LinkedList<Node>();
        que.add(new Node(1, 1, 0));

        while (!que.isEmpty()) {
            Node current = que.poll();
            int currentX = current.x;
            int currentY = current.y;

            if (currentX == M && currentY == N) {
                System.out.println(current.dist + 1);
                break;
            }

            if (visited[currentY][currentX])
                continue;
            visited[currentY][currentX] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dir[i][0];
                int nextY = currentY + dir[i][1];
                if (1 <= nextY && nextY <= N && 1 <= nextX && nextX <= M) {
                    if (MAP[nextY][nextX] == 1) {
                        if (!visited[nextY][nextX]) {
                            que.add(new Node(nextX, nextY, current.dist + 1));
                        }
                    }
                }
            }

        }

    }
}

class Node {
    public int x;
    public int y;
    public int dist;

    Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}