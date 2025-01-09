import java.util.*;

class Solution {
    static int [][] dir = {{0,1},{1,0}}; 
    static int [][] dist;

    public int minPathSum(int[][] grid) {
        dist = new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = grid[0][0];
        pq.add(new Node(0,0,grid[0][0]));


        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.y == grid.length -1 && cur.x == grid[0].length-1){
                return cur.val;
            }

            for(int i=0;i<dir.length;i++){
                int nextY = dir[i][0] + cur.y;
                int nextX = dir[i][1] + cur.x;

                if(nextY >= 0 && nextY < grid.length && nextX >= 0 && nextX < grid[0].length ){
                    if(dist[nextY][nextX] > grid[nextY][nextX] + dist[cur.y][cur.x]){
                        dist[nextY][nextX] = grid[nextY][nextX] + dist[cur.y][cur.x];
                        pq.add(new Node(nextY,nextX, grid[nextY][nextX] + dist[cur.y][cur.x]));
                    }
                }
            }
        }
        return 0;
    }
}
class Node implements Comparable<Node>{
    public int y;
    public int x;
    public int val;

    Node(int y, int x, int val){
        this.y = y;
        this.x = x;
        this.val = val;
    }
    @Override
    public int compareTo(Node n1){
        return this.val - n1.val;
    }
}